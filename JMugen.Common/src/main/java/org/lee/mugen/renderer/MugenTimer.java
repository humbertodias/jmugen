package org.lee.mugen.renderer;

public interface MugenTimer {
	public static final long DEFAULT_FPS = 60;

	public abstract long getFramerate();

	public abstract void setFramerate(long famerate);

	public abstract int sleep();
	public abstract void sleep(long ms);

	public abstract int getFps();

}