package org.lee.mugen.core.sound;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * GWT client stub: no {@code javax.sound.sampled}; use {@link #installAudioPlayback(AudioPlayback)}.
 */
public final class SoundSystem {

    private static AudioPlayback audioPlayback;

    private SoundSystem() {
    }

    public static void installAudioPlayback(AudioPlayback playback) {
        audioPlayback = playback;
    }

    public static void clearAudioPlayback() {
        if (audioPlayback != null) {
            try {
                audioPlayback.shutdown();
            } catch (Exception ignored) {
            }
        }
        audioPlayback = null;
    }

    public static AudioPlayback getAudioPlayback() {
        return audioPlayback;
    }

    public static final class SoundBackGround {

        public static void stopMusic() {
            if (audioPlayback != null) {
                audioPlayback.stopMusic();
            }
        }

        public static void playMusic(String path) {
            if (audioPlayback != null) {
                audioPlayback.playMusic(path);
            }
        }

        public static boolean isStop() {
            return audioPlayback == null || audioPlayback.isBackgroundStopped();
        }
    }

    public static final class Sfx {

        public static void playSnd(byte[] sound) {
            if (audioPlayback != null) {
                audioPlayback.playSfx(sound);
            }
        }

        public static void playSnd(byte[] sound, AtomicBoolean isPlaying) {
            if (audioPlayback != null) {
                audioPlayback.playSfx(sound, isPlaying);
            } else if (isPlaying != null) {
                isPlaying.set(false);
            }
        }

        public static void playSnd(byte[] sound, boolean async) {
            playSnd(sound);
        }
    }
}
