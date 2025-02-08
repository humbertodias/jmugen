package org.lee.mugen.renderer.lwjgl;

import org.lee.mugen.renderer.MugenTimer;
import org.lwjgl.util.Timer;

/**
 * Timer class Wrapper
 * 
 * @author Dr Wong
 * 
 */
public final class LMugenTimer implements MugenTimer {

	@Override
	public int getFps() {
		return fps;
	}

	@Override
	public long getFramerate() {
		return 100;
	}

	@Override
	public void setFramerate(long famerate) {
		// TODO Auto-generated method stub

	}

	public float getDeltas() {
		return deltas;
	}

	public int getFrames() {
		return frames;
	}

	Timer timer = new Timer();
	float lastTime = timer.getTime();
	float tick;
	float deltas;
	int frames;
	int fps;
	public void reset() {
		fps = (int) (frames / deltas);
		frames = 0;
		deltas = 0;
	}
	boolean nextTimeReset;
	public void listen() {
		if (nextTimeReset) {
			nextTimeReset = false;
			reset();
		}
		Timer.tick();
		tick = timer.getTime() - lastTime;
		deltas += tick;
		lastTime = timer.getTime();

		frames++;
		if (frames == 50000) {
//			fps = (int) (frames / deltas);
//			frames = 0;
//			deltas = 0;

		}
		if (1f/getFramerate() <= getDeltas()) {
			nextTimeReset = true;
		}

	}

	@Override
	public int sleep() {
		Timer.tick();
		return 0;
	}

	@Override
	public void sleep(long ms) {
		Timer.tick();

	}

}