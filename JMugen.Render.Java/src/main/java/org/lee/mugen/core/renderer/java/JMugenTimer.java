package org.lee.mugen.core.renderer.java;

import org.lee.mugen.renderer.MugenTimer;

public class JMugenTimer implements MugenTimer {
	private long lastTime;
	private static final long FRAME_DURATION_NANOS = 1_000_000_000 / 60;  // 60 FPS
	private long frameRate = FRAME_DURATION_NANOS;
	private int frameCount = 0;
	private int fps = 0;
	private static final long TIME_TO_LISTEN_FPS = 500_000_000;  // 500 ms in nanoseconds

	private long lastTimeForComputeFPS = 0;

	public JMugenTimer() {
		lastTime = System.nanoTime();
	}

	@Override
	public int getFps() {
		return fps;
	}

	@Override
	public long getFramerate() {
		return frameRate;
	}

	@Override
	public void setFramerate(long framerate) {
		this.frameRate = 1_000_000_000 / framerate;  // Convert FPS to nanoseconds per frame
	}

	private void computeFPS() {
		frameCount++;
		long currentTime = System.nanoTime();
		long diff = currentTime - lastTimeForComputeFPS;

		if (diff >= TIME_TO_LISTEN_FPS) {
			fps = (int) (frameCount * 1_000_000_000L / diff);
			frameCount = 0;
			lastTimeForComputeFPS = currentTime;
		}
	}

	@Override
	public int sleep() {
		computeFPS();

		long currentTime = System.nanoTime();
		long elapsedTime = currentTime - lastTime;

		int lag = 0;

		if (elapsedTime < frameRate) {
			long sleepTime = (frameRate - elapsedTime) / 1_000_000;  // Convert to milliseconds
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		} else {
			lag = (int) ((elapsedTime - frameRate) / frameRate);
		}

		lastTime = System.nanoTime();  // Update the time after each cycle

		return lag;
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
