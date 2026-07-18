package org.lee.mugen.core.sound;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * GWT client stub: no {@code javax.sound.sampled}; use {@link #installAudioPlayback(AudioPlayback)}.
 */
public final class SoundSystem {

    private SoundSystem() {
    }

    public static void installAudioPlayback(AudioPlayback playback) {
        AudioPlayback old = AudioPlaybacks.get();
        AudioPlaybacks.install(playback);
        if (old != null && old != playback) {
            try {
                old.shutdown();
            } catch (Exception ignored) {
            }
        }
    }

    public static void clearAudioPlayback() {
        installAudioPlayback(null);
    }

    public static AudioPlayback getAudioPlayback() {
        return AudioPlaybacks.get();
    }

    public static final class SoundBackGround {

        public static void stopMusic() {
            AudioPlayback playback = AudioPlaybacks.get();
            if (playback != null) {
                playback.stopMusic();
            }
        }

        public static void playMusic(String path) {
            AudioPlayback playback = AudioPlaybacks.get();
            if (playback != null) {
                playback.playMusic(path);
            }
        }

        public static boolean isStop() {
            AudioPlayback playback = AudioPlaybacks.get();
            return playback == null || playback.isBackgroundStopped();
        }
    }

    public static final class Sfx {

        public static void playSnd(byte[] sound) {
            AudioPlayback playback = AudioPlaybacks.get();
            if (playback != null) {
                playback.playSfx(sound);
            }
        }

        public static void playSnd(byte[] sound, AtomicBoolean isPlaying) {
            AudioPlayback playback = AudioPlaybacks.get();
            if (playback != null) {
                playback.playSfx(sound, isPlaying);
            } else if (isPlaying != null) {
                isPlaying.set(false);
            }
        }

        public static void playSnd(byte[] sound, boolean async) {
            playSnd(sound);
        }
    }
}
