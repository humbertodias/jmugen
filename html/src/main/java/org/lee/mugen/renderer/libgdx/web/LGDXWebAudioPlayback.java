package org.lee.mugen.renderer.libgdx.web;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.typedarrays.shared.ArrayBuffer;
import com.google.gwt.typedarrays.shared.Int8Array;
import com.google.gwt.typedarrays.shared.TypedArrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import org.lee.mugen.core.sound.AudioPlayback;

/**
 * Web {@link AudioPlayback}.
 *
 * <p>BGM uses LibGDX {@link Music} (internal assets). SFX cannot use {@code Gdx.audio.newSound} with
 * {@code FileHandle.local} — GWT's WebAudio backend only XHRs preloaded asset URLs — so WAV bytes from
 * {@code .snd} are decoded with the Web Audio API directly.
 */
public final class LGDXWebAudioPlayback implements AudioPlayback {

    private static final int MAX_SFX_CACHE = 96;
    private static final String FALLBACK_BGM = "data/sound/intro.mp3";

    private volatile Music music;
    private float musicVolume = 1f;
    private volatile boolean unlocked;
    private volatile String pendingMusicPath;

    private JavaScriptObject sfxContext;
    private JavaScriptObject sfxGain;
    private final Map<String, JavaScriptObject> sfxBuffers = new HashMap<String, JavaScriptObject>();
    private final Map<String, Boolean> sfxPendingDecode = new HashMap<String, Boolean>();

    private static String sfxKey(byte[] data) {
        long h = data.length;
        for (int i = 0; i < data.length; i++) {
            h = h * 31 + (data[i] & 0xff);
        }
        return Long.toHexString(h) + "_" + data.length;
    }

    private static String resolveMusicPath(String path) {
        if (path == null) {
            return null;
        }
        String p = path.trim().replace('\\', '/');
        if (p.isEmpty() || p.endsWith("/")) {
            return FALLBACK_BGM;
        }
        String lower = p.toLowerCase(Locale.ROOT);
        if (lower.endsWith(".adx") || lower.endsWith(".bin")) {
            Gdx.app.log("JMugenWebAudio", "ADX/BIN unsupported on web, using " + FALLBACK_BGM);
            return FALLBACK_BGM;
        }
        return LGDXWebGdxData.toInternalPath(p);
    }

    public void onUserGesture() {
        if (unlocked) {
            resumeSfxContext();
            return;
        }
        unlocked = true;
        Gdx.app.log("JMugenWebAudio", "Audio unlocked by user gesture");
        resumeSfxContext();
        String pending = pendingMusicPath;
        if (pending != null) {
            startMusic(pending);
        }
    }

    public boolean isUnlocked() {
        return unlocked;
    }

    @Override
    public void playMusic(String path) {
        if (Gdx.app == null) {
            return;
        }
        final String internal = resolveMusicPath(path);
        if (internal == null) {
            return;
        }
        pendingMusicPath = internal;
        if (!unlocked) {
            Gdx.app.log("JMugenWebAudio", "Queue BGM until user gesture: " + internal);
        }
        startMusic(internal);
    }

    private void startMusic(final String internal) {
        Gdx.app.postRunnable(() -> {
            disposeMusic();
            FileHandle fh = Gdx.files.internal(internal);
            if (!fh.exists()) {
                Gdx.app.error("JMugenWebAudio", "BGM missing: " + internal);
                if (!FALLBACK_BGM.equals(internal)) {
                    FileHandle fb = Gdx.files.internal(FALLBACK_BGM);
                    if (fb.exists()) {
                        pendingMusicPath = FALLBACK_BGM;
                        playMusicHandle(fb, FALLBACK_BGM);
                    }
                }
                return;
            }
            playMusicHandle(fh, internal);
        });
    }

    private void playMusicHandle(FileHandle fh, String label) {
        try {
            music = Gdx.audio.newMusic(fh);
            music.setLooping(true);
            music.setVolume(musicVolume);
            music.play();
            Gdx.app.log("JMugenWebAudio", "Playing BGM: " + label);
        } catch (Throwable t) {
            Gdx.app.error("JMugenWebAudio", "BGM failed: " + label + " — " + t);
            disposeMusic();
        }
    }

    private void disposeMusic() {
        if (music != null) {
            try {
                music.stop();
            } catch (Throwable ignored) {
            }
            try {
                music.dispose();
            } catch (Throwable ignored) {
            }
            music = null;
        }
    }

    @Override
    public void stopMusic() {
        pendingMusicPath = null;
        if (Gdx.app == null) {
            return;
        }
        Gdx.app.postRunnable(this::disposeMusic);
    }

    @Override
    public void setMusicVolume(float volume) {
        musicVolume = Math.max(0f, Math.min(1f, volume));
        Music m = music;
        if (m != null) {
            m.setVolume(musicVolume);
        }
        setSfxGain(musicVolume);
    }

    @Override
    public float getMusicVolume() {
        return musicVolume;
    }

    @Override
    public void playSfx(byte[] soundData) {
        playSfx(soundData, null);
    }

    @Override
    public void playSfx(byte[] soundData, AtomicBoolean isPlaying) {
        if (soundData == null || soundData.length == 0 || Gdx.app == null) {
            if (isPlaying != null) {
                isPlaying.set(false);
            }
            return;
        }
        final byte[] data = soundData;
        final AtomicBoolean flag = isPlaying;
        Gdx.app.postRunnable(() -> {
            try {
                playSfxWebAudio(data);
            } catch (Throwable t) {
                Gdx.app.error("JMugenWebAudio", "SFX failed: " + t);
            } finally {
                if (flag != null) {
                    flag.set(false);
                }
            }
        });
    }

    private void playSfxWebAudio(byte[] data) {
        ensureSfxContext();
        if (sfxContext == null) {
            Gdx.app.error("JMugenWebAudio", "No AudioContext for SFX");
            return;
        }
        resumeSfxContext();
        String key = sfxKey(data);
        JavaScriptObject buffer = sfxBuffers.get(key);
        if (buffer != null) {
            playDecodedBuffer(sfxContext, sfxGain, buffer, musicVolume);
            return;
        }
        // MUGEN .snd samples are usually 8-bit PCM WAV. decodeAudioData is unreliable for
        // those on some browsers — build an AudioBuffer from PCM directly when possible.
        buffer = createBufferFromPcmWav(sfxContext, data);
        if (buffer != null) {
            if (sfxBuffers.size() >= MAX_SFX_CACHE) {
                evictOneSfxBuffer();
            }
            sfxBuffers.put(key, buffer);
            playDecodedBuffer(sfxContext, sfxGain, buffer, musicVolume);
            return;
        }
        if (sfxPendingDecode.containsKey(key)) {
            return;
        }
        if (sfxBuffers.size() >= MAX_SFX_CACHE) {
            evictOneSfxBuffer();
        }
        sfxPendingDecode.put(key, Boolean.TRUE);
        ArrayBuffer ab = toArrayBuffer(data);
        decodeSfx(sfxContext, ab, key);
    }

    /**
     * Build a Web Audio buffer from a PCM WAV (fmt=1). Returns null for non-PCM / invalid data.
     */
    private static JavaScriptObject createBufferFromPcmWav(JavaScriptObject ctx, byte[] wav) {
        if (ctx == null || wav == null || wav.length < 44) {
            return null;
        }
        if (wav[0] != 'R' || wav[1] != 'I' || wav[2] != 'F' || wav[3] != 'F') {
            return null;
        }
        if (wav[8] != 'W' || wav[9] != 'A' || wav[10] != 'V' || wav[11] != 'E') {
            return null;
        }
        int pos = 12;
        int audioFormat = -1;
        int channels = 0;
        int sampleRate = 0;
        int bits = 0;
        int dataOff = -1;
        int dataLen = 0;
        while (pos + 8 <= wav.length) {
            char c0 = (char) (wav[pos] & 0xff);
            char c1 = (char) (wav[pos + 1] & 0xff);
            char c2 = (char) (wav[pos + 2] & 0xff);
            char c3 = (char) (wav[pos + 3] & 0xff);
            int chunkSize = readIntLe(wav, pos + 4);
            if (chunkSize < 0 || pos + 8 + chunkSize > wav.length + 1) {
                break;
            }
            boolean isFmt = c0 == 'f' && c1 == 'm' && c2 == 't' && c3 == ' ';
            boolean isData = c0 == 'd' && c1 == 'a' && c2 == 't' && c3 == 'a';
            if (isFmt && chunkSize >= 16) {
                audioFormat = readShortLe(wav, pos + 8);
                channels = readShortLe(wav, pos + 10);
                sampleRate = readIntLe(wav, pos + 12);
                bits = readShortLe(wav, pos + 22);
            } else if (isData) {
                dataOff = pos + 8;
                dataLen = chunkSize;
                if (dataOff + dataLen > wav.length) {
                    dataLen = wav.length - dataOff;
                }
                break;
            }
            pos += 8 + chunkSize;
            if ((chunkSize & 1) != 0) {
                pos++;
            }
        }
        if (audioFormat != 1 || channels < 1 || sampleRate < 1 || dataOff < 0 || dataLen < 1) {
            return null;
        }
        if (bits != 8 && bits != 16) {
            return null;
        }
        int bytesPerSample = bits / 8;
        int frameSize = bytesPerSample * channels;
        if (frameSize <= 0) {
            return null;
        }
        int frameCount = dataLen / frameSize;
        if (frameCount < 1) {
            return null;
        }
        // Mix to mono for simplicity (MUGEN fight/system samples are mono).
        com.google.gwt.typedarrays.shared.Float32Array samples =
                TypedArrays.createFloat32Array(frameCount);
        if (bits == 8) {
            for (int i = 0; i < frameCount; i++) {
                int unsigned = wav[dataOff + i * frameSize] & 0xff;
                samples.set(i, (unsigned - 128) / 128f);
            }
        } else {
            for (int i = 0; i < frameCount; i++) {
                int lo = wav[dataOff + i * frameSize] & 0xff;
                int hi = wav[dataOff + i * frameSize + 1];
                short s = (short) (lo | (hi << 8));
                samples.set(i, s / 32768f);
            }
        }
        return createMonoBuffer(ctx, sampleRate, samples);
    }

    private static int readIntLe(byte[] data, int off) {
        return (data[off] & 0xff)
                | ((data[off + 1] & 0xff) << 8)
                | ((data[off + 2] & 0xff) << 16)
                | ((data[off + 3] & 0xff) << 24);
    }

    private static int readShortLe(byte[] data, int off) {
        return (data[off] & 0xff) | ((data[off + 1] & 0xff) << 8);
    }

    private static native JavaScriptObject createMonoBuffer(
            JavaScriptObject ctx,
            int sampleRate,
            com.google.gwt.typedarrays.shared.Float32Array samples) /*-{
        var buffer = ctx.createBuffer(1, samples.length, sampleRate);
        buffer.getChannelData(0).set(samples);
        return buffer;
    }-*/;

    /** Called from JSNI when {@code decodeAudioData} succeeds. */
    void onSfxDecoded(String key, JavaScriptObject buffer) {
        sfxPendingDecode.remove(key);
        if (buffer == null) {
            Gdx.app.error("JMugenWebAudio", "SFX decode returned null for key " + key);
            return;
        }
        sfxBuffers.put(key, buffer);
        playDecodedBuffer(sfxContext, sfxGain, buffer, musicVolume);
    }

    /** Called from JSNI when decode fails. */
    void onSfxDecodeError(String key) {
        sfxPendingDecode.remove(key);
        Gdx.app.error("JMugenWebAudio", "SFX decodeAudioData failed key=" + key);
    }

    private void evictOneSfxBuffer() {
        if (sfxBuffers.isEmpty()) {
            return;
        }
        String first = sfxBuffers.keySet().iterator().next();
        sfxBuffers.remove(first);
    }

    private static ArrayBuffer toArrayBuffer(byte[] data) {
        Int8Array arr = TypedArrays.createInt8Array(data.length);
        for (int i = 0; i < data.length; i++) {
            arr.set(i, data[i]);
        }
        return arr.buffer();
    }

    private void ensureSfxContext() {
        if (sfxContext != null) {
            return;
        }
        sfxContext = createAudioContext();
        if (sfxContext != null) {
            sfxGain = createGain(sfxContext, musicVolume);
        }
    }

    private void resumeSfxContext() {
        if (sfxContext != null) {
            resumeContext(sfxContext);
        }
    }

    private void setSfxGain(float volume) {
        if (sfxGain != null) {
            setGainValue(sfxGain, volume);
        }
    }

    private static native JavaScriptObject createAudioContext() /*-{
        var AC = $wnd.AudioContext || $wnd.webkitAudioContext;
        if (!AC) {
            return null;
        }
        return new AC();
    }-*/;

    private static native JavaScriptObject createGain(JavaScriptObject ctx, float volume) /*-{
        var gain = ctx.createGain ? ctx.createGain() : ctx.createGainNode();
        gain.gain.value = volume;
        gain.connect(ctx.destination);
        return gain;
    }-*/;

    private static native void setGainValue(JavaScriptObject gain, float volume) /*-{
        if (gain && gain.gain) {
            gain.gain.value = volume;
        }
    }-*/;

    private static native void resumeContext(JavaScriptObject ctx) /*-{
        if (ctx && ctx.state === 'suspended' && ctx.resume) {
            ctx.resume();
        }
    }-*/;

    private native void decodeSfx(JavaScriptObject ctx, ArrayBuffer data, String key) /*-{
        var self = this;
        ctx.decodeAudioData(
            data,
            function(buffer) {
                self.@org.lee.mugen.renderer.libgdx.web.LGDXWebAudioPlayback::onSfxDecoded(Ljava/lang/String;Lcom/google/gwt/core/client/JavaScriptObject;)(key, buffer);
            },
            function(err) {
                self.@org.lee.mugen.renderer.libgdx.web.LGDXWebAudioPlayback::onSfxDecodeError(Ljava/lang/String;)(key);
            }
        );
    }-*/;

    private static native void playDecodedBuffer(
            JavaScriptObject ctx, JavaScriptObject gain, JavaScriptObject buffer, float volume) /*-{
        if (!ctx || !buffer) {
            return;
        }
        if (ctx.state === 'suspended' && ctx.resume) {
            ctx.resume();
        }
        var src = ctx.createBufferSource();
        src.buffer = buffer;
        var g = ctx.createGain ? ctx.createGain() : ctx.createGainNode();
        g.gain.value = volume;
        src.connect(g);
        if (gain) {
            g.connect(gain);
        } else {
            g.connect(ctx.destination);
        }
        if (src.start) {
            src.start(0);
        } else {
            src.noteOn(0);
        }
    }-*/;

    @Override
    public boolean isBackgroundStopped() {
        Music m = music;
        if (m == null) {
            return true;
        }
        try {
            return !m.isPlaying();
        } catch (Throwable t) {
            return true;
        }
    }

    @Override
    public void shutdown() {
        pendingMusicPath = null;
        stopMusic();
        sfxBuffers.clear();
        sfxPendingDecode.clear();
        sfxContext = null;
        sfxGain = null;
    }
}
