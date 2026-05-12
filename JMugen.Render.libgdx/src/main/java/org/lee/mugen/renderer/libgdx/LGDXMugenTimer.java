package org.lee.mugen.renderer.libgdx;

import org.lee.mugen.renderer.MugenTimer;
import com.badlogic.gdx.Gdx;

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
        long sleepTime = (1000 / framerate) - (now - lastTime);
        
        if (sleepTime > 0) {
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        
        lastTime = System.currentTimeMillis();
        updateFps();
        
        return (int) (System.currentTimeMillis() - now);
    }
    
    @Override
    public void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
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
