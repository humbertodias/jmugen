package org.lee.mugen.renderer.lwjgl;

import org.lee.mugen.renderer.MugenTimer;

import static org.lwjgl.glfw.GLFW.*;

public final class LMugenTimer implements MugenTimer {

	private float lastTime;
	private float tick;
	private float deltas;
	private int frames;
	private int fps;
	private boolean nextTimeReset;

	public LMugenTimer() {
		// Initialize GLFW, necessary for using glfwGetTime()
		if (!glfwInit()) {
			throw new IllegalStateException("Unable to initialize GLFW.");
		}

		// Start the timer
		lastTime = (float) glfwGetTime();
	}

	@Override
	public int getFps() {
		return fps;
	}

	@Override
	public long getFramerate() {
		return 100;  // Returns a fixed framerate value
	}

	@Override
	public void setFramerate(long framerate) {
		// You can add logic here to adjust the framerate
	}

	public float getDeltas() {
		return deltas;
	}

	public int getFrames() {
		return frames;
	}

	public void reset() {
		fps = (int) (frames / deltas);
		frames = 0;
		deltas = 0;
	}

	public void listen() {
		if (nextTimeReset) {
			nextTimeReset = false;
			reset();
		}

		// Get current time using glfwGetTime()
		float currentTime = (float) glfwGetTime();
		tick = currentTime - lastTime;
		deltas += tick;
		lastTime = currentTime;

		frames++;

		// Adjust the FPS if necessary
		if (frames == 50000) {
			// fps = (int) (frames / deltas);
			// frames = 0;
			// deltas = 0;
		}

		// Reset when the time exceeds the desired framerate
		if (1f / getFramerate() <= getDeltas()) {
			nextTimeReset = true;
		}
	}

	@Override
	public int sleep() {
		glfwPollEvents();  // Necessary to keep GLFW active
		return 0;
	}

	@Override
	public void sleep(long ms) {
		// Use glfwWaitEventsTimeout to wait for a specific amount of time
		glfwWaitEventsTimeout(ms / 1000.0);  // Convert milliseconds to seconds
	}
}
