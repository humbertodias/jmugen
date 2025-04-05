package org.lee.mugen.renderer.lwjgl;

import org.lee.mugen.renderer.MugenTimer;

/**
 * Timer class Wrapper
 * 
 * @author Dr Wong
 * 
 */
public final class LMugenTimer implements MugenTimer {

	private long lastTime = System.nanoTime();
	private float tick;
	private float deltas;
	private int frames;
	private int fps;
	private boolean nextTimeReset;

	@Override
	public int getFps() {
		return fps;
	}

	@Override
	public long getFramerate() {
		return 60;
	}

	@Override
	public void setFramerate(long framerate) {
		// Not implemented
	}

	public float getDeltas() {
		return deltas;
	}

	public int getFrames() {
		return frames;
	}

	public void reset() {
		if (deltas > 0) {
			fps = (int) (frames / deltas);
		} else {
			fps = 0;
		}
		frames = 0;
		deltas = 0;
	}

	public void listen() {
		if (nextTimeReset) {
			nextTimeReset = false;
			reset();
		}

		long currentTime = System.nanoTime();
		tick = (currentTime - lastTime) / 1_000_000_000.0f; // convert to seconds
		deltas += tick;
		lastTime = currentTime;

		frames++;

		if (1f / getFramerate() <= getDeltas()) {
			nextTimeReset = true;
		}
	}

	@Override
	public int sleep() {
		// You can add Thread.sleep(...) here if needed
		return 0;
	}

	@Override
	public void sleep(long ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}
