package org.lee.mugen.core.sound;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Optional audio backend (e.g. LibGDX). When installed on the engine sound system, it receives
 * music and SFX calls instead of {@code javax.sound.sampled}.
 */
public interface AudioPlayback {

	void playMusic(String absolutePath);

	void stopMusic();

	void setMusicVolume(float volume);

	float getMusicVolume();

	/**
	 * Short sound from encoded bytes (typically WAV in Mugen SND).
	 */
	void playSfx(byte[] soundData);

	/**
	 * Same as {@link #playSfx(byte[])}; {@code isPlaying} is cleared when playback has been handed off
	 * (LibGDX) or finished (desktop Java Sound).
	 */
	void playSfx(byte[] soundData, AtomicBoolean isPlaying);

	/**
	 * @return true when no background music is playing (or music was never started).
	 */
	boolean isBackgroundStopped();

	/**
	 * Stops music and disposes cached SFX; invoked when the game window shuts down.
	 */
	void shutdown();
}
