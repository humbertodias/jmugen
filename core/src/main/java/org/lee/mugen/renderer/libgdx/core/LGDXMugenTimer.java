package org.lee.mugen.renderer.libgdx.core;

import org.lee.mugen.renderer.MugenTimer;

/**
 * Timer implementation for LibGDX
 */
public class LGDXMugenTimer implements MugenTimer {
    
    private long framerate = DEFAULT_FPS;
    private long lastTime;
    private int fps = 0;
    private int frameCount = 0;
    private long fpsTime = 0;
    
    public LGDXMugenTimer() {
        this.lastTime = System.currentTimeMillis();
    }
    
    @Override
    public long getFramerate() {
        return framerate;
    }
    
    @Override
    public void setFramerate(long framerate) {
        this.framerate = framerate;
    }
    
    @Override
    public int sleep() {
        long now = System.currentTimeMillis();
        int delta = (int) (now - lastTime);
        lastTime = now;
        updateFps();
        return delta;
    }
    
    @Override
    public void sleep(long ms) {
        // LibGDX owns the frame pacing on desktop and web.
    }
    
    @Override
    public int getFps() {
        return fps;
    }
    
    private void updateFps() {
        frameCount++;
        long now = System.currentTimeMillis();
        
        if (now - fpsTime >= 1000) {
            fps = frameCount;
            frameCount = 0;
            fpsTime = now;
        }
    }
}
