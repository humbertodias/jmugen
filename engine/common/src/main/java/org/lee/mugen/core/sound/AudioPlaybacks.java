package org.lee.mugen.core.sound;

/**
 * Holds the optional {@link AudioPlayback} backend (LibGDX / web). GWT-safe; no {@code javax.sound}.
 */
public final class AudioPlaybacks {

    private static AudioPlayback installed;

    private AudioPlaybacks() {
    }

    public static void install(AudioPlayback playback) {
        installed = playback;
    }

    public static AudioPlayback get() {
        return installed;
    }
}
