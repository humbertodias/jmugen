package org.lee.mugen.renderer.lwjgl;

import org.lee.mugen.renderer.MugenTimer;

import static org.lwjgl.glfw.GLFW.glfwGetTime;

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
		return 60;
	}

	@Override
	public void setFramerate(long framerate) {
		// TODO Auto-generated method stub

	}

	public float getDeltas() {
		return deltas;
	}

	public int getFrames() {
		return frames;
	}

	float lastTime = (float) glfwGetTime();
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
		float currentTime = (float) glfwGetTime();
		tick = currentTime - lastTime;
		deltas += tick;
		lastTime = currentTime;

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