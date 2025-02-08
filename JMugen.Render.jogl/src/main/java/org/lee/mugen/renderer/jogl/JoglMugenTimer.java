package org.lee.mugen.renderer.jogl;

import org.lee.mugen.renderer.MugenTimer;

public class JoglMugenTimer implements MugenTimer {
	long lastTime = 0;

	long ONE = 1000 / 60;
	private long frameRate = ONE;
	int frame = 0;
	int fps = 0;
	int TIME_TO_LISTEN_FPS = 500;

	@Override
	public int getFps() {
		return fps;
	}

	@Override
	public long getFramerate() {
		return frameRate;
	}

	@Override
	public void setFramerate(long famerate) {
		this.frameRate = famerate;
	}

	private long lastTimeForComputeFPS = 0;

	public void listen() {
		frame++;
		long currentTime = System.currentTimeMillis();
		long diff = currentTime - lastTimeForComputeFPS;
		if (diff > TIME_TO_LISTEN_FPS) {
			fps = (int) (frame / (diff / 1000f));
			frame = 0;
			lastTimeForComputeFPS = System.currentTimeMillis();

		}
	}

	@Override
	public int sleep() {
		listen();
		long currentTime = System.currentTimeMillis();
		long diff = currentTime - lastTime;
		int lack = 0;
		if (diff < frameRate && (frameRate - diff) > 0) {
			try {
				Thread.sleep((frameRate - diff));
				// System.out.println("wait " + (frameRate - diff));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			lack = (int) ((diff - frameRate) / frameRate);
			// System.out.println("lack " + (diff - frameRate));
			lastTime = System.currentTimeMillis();

		}
		return lack;
	}

	@Override
	public void sleep(long ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
		}

	}
}
