package org.lee.mugen.renderer.libgdx.web;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import org.lee.mugen.core.sound.AudioPlayback;

/**
 * Web {@link AudioPlayback}: music from internal assets; SFX cached in local storage (no {@code CRC32} on GWT).
 */
public final class LGDXWebAudioPlayback implements AudioPlayback {

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
        long h = data.length;
        for (int i = 0; i < data.length; i++) {
            h = h * 31 + (data[i] & 0xff);
        }
        return h;
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

    @Override
    public void playMusic(String path) {
        if (Gdx.app == null || path == null) {
            return;
        }
        String lower = path.toLowerCase(Locale.ROOT);
        if (lower.endsWith(".adx") || lower.endsWith(".bin")) {
            return;
        }
        final String internal = LGDXWebGdxData.toInternalPath(path);
        Gdx.app.postRunnable(() -> {
            disposeMusic();
            FileHandle fh = Gdx.files.internal(internal);
            if (!fh.exists()) {
                return;
            }
            try {
                music = Gdx.audio.newMusic(fh);
                music.setLooping(true);
                music.setVolume(musicVolume);
                music.play();
            } catch (Throwable ignored) {
                disposeMusic();
            }
        });
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
        Gdx.app.postRunnable(() -> {
            try {
                synchronized (sfxLock) {
                    long key = sfxKey(soundData);
                    Sound snd = sfxByKey.get(key);
                    if (snd == null) {
                        String ext = extFor(soundData);
                        FileHandle fh = Gdx.files.local("jmugen_sfx/" + Long.toHexString(key) + ext);
                        fh.parent().mkdirs();
                        fh.writeBytes(soundData, false);
                        snd = Gdx.audio.newSound(fh);
                        sfxByKey.put(key, snd);
                    }
                    snd.play(musicVolume);
                }
            } catch (Throwable ignored) {
            } finally {
                if (isPlaying != null) {
                    isPlaying.set(false);
                }
            }
        });
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
        stopMusic();
        if (Gdx.app == null) {
            return;
        }
        Gdx.app.postRunnable(() -> {
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
