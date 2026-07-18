package org.lee.mugen.renderer.libgdx.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.zip.CRC32;
import org.lee.mugen.core.sound.AudioPlayback;
import org.lee.mugen.util.Logger;

/**
 * LibGDX {@link Music} / {@link Sound} implementation of {@link AudioPlayback} for any LibGDX backend
 * (desktop, Android, etc.).
 */
public final class LGDXAudioPlayback implements AudioPlayback {

    private static final int MAX_SFX_CACHE = 96;

    private volatile Music music;
    private float musicVolume = 1f;
    private final Object sfxLock = new Object();
    private final LinkedHashMap<Long, Sound> sfxByKey = new LinkedHashMap<Long, Sound>(32, 0.75f, true) {
        @Override
        protected boolean removeEldestEntry(Map.Entry<Long, Sound> eldest) {
            if (size() > MAX_SFX_CACHE) {
                try {
                    eldest.getValue().dispose();
                } catch (Throwable ignored) {
                }
                return true;
            }
            return false;
        }
    };

    private static long sfxKey(byte[] data) {
        CRC32 c = new CRC32();
        c.update(data);
        return c.getValue() ^ ((long) data.length << 1);
    }

    private static String extFor(byte[] data) {
        if (data.length >= 4 && data[0] == 'R' && data[1] == 'I' && data[2] == 'F' && data[3] == 'F') {
            return ".wav";
        }
        if (data.length >= 4 && data[0] == 'O' && data[1] == 'g' && data[2] == 'g' && data[3] == 'S') {
            return ".ogg";
        }
        return ".wav";
    }

    private void runOnGdx(Runnable r) {
        if (Gdx.app == null) {
            return;
        }
        Gdx.app.postRunnable(r);
    }

    @Override
    public void playMusic(String absolutePath) {
        runOnGdx(() -> {
            disposeMusicInternal();
            if (absolutePath == null) {
                return;
            }
            String lower = absolutePath.toLowerCase(Locale.ROOT);
            if (lower.endsWith(".adx") || lower.endsWith(".bin")) {
                Logger.log("LibGDX music: ADX/BIN not supported, skip %s", absolutePath);
                return;
            }
            FileHandle fh = Gdx.files.absolute(absolutePath);
            if (!fh.exists()) {
                Logger.log("LibGDX music: file not found %s", absolutePath);
                return;
            }
            try {
                music = Gdx.audio.newMusic(fh);
                music.setLooping(true);
                music.setVolume(musicVolume);
                music.play();
            } catch (Throwable t) {
                Logger.error("LibGDX music failed for %s: %s", absolutePath, t);
                disposeMusicInternal();
            }
        });
    }

    private void disposeMusicInternal() {
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
        runOnGdx(this::disposeMusicInternal);
    }

    @Override
    public void setMusicVolume(float volume) {
        musicVolume = Math.max(0f, Math.min(1f, volume));
        runOnGdx(() -> {
            if (music != null) {
                music.setVolume(musicVolume);
            }
        });
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
        if (soundData == null || soundData.length == 0) {
            if (isPlaying != null) {
                isPlaying.set(false);
            }
            return;
        }
        runOnGdx(() -> {
            try {
                playSfxOnGdxThread(soundData);
            } catch (Throwable t) {
                Logger.error("LibGDX SFX failed: %s", t);
            } finally {
                if (isPlaying != null) {
                    isPlaying.set(false);
                }
            }
        });
    }

    private void playSfxOnGdxThread(byte[] data) {
        synchronized (sfxLock) {
            long key = sfxKey(data);
            Sound snd = sfxByKey.get(key);
            if (snd == null) {
                String ext = extFor(data);
                FileHandle fh = Gdx.files.local("jmugen_sfx/" + Long.toHexString(key) + ext);
                fh.parent().mkdirs();
                fh.writeBytes(data, false);
                snd = Gdx.audio.newSound(fh);
                sfxByKey.put(key, snd);
            }
            snd.play(musicVolume);
        }
    }

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
        runOnGdx(() -> {
            disposeMusicInternal();
            synchronized (sfxLock) {
                for (Sound s : sfxByKey.values()) {
                    try {
                        s.dispose();
                    } catch (Throwable ignored) {
                    }
                }
                sfxByKey.clear();
            }
        });
    }
}
