package org.lee.mugen.renderer.libgdx;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Basic tests for LibGDX renderer components
 */
public class LGDXMugenDrawerTest {
    
    private LGDXGameWindow gameWindow;
    
    @Before
    public void setUp() {
        gameWindow = new LGDXGameWindow();
    }
    
    @Test
    public void testGameWindowCreation() {
        assertNotNull(gameWindow);
        gameWindow.setTitle("Test Window");
        gameWindow.setResolution(640, 480);
    }
    
    @Test
    public void testTimerInitialization() {
        assertNotNull(gameWindow.getTimer());
        assertTrue(gameWindow.getTimer() instanceof LGDXMugenTimer);
    }

    @Ignore
    @Test
    public void testMouseStatus() {
        assertNotNull(gameWindow.getMouseStatus());
    }
}
