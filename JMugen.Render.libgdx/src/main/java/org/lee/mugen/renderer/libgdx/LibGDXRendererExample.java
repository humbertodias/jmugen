package org.lee.mugen.renderer.libgdx;

import org.lee.mugen.core.Game;
import org.lee.mugen.renderer.GameWindow;
import org.lee.mugen.renderer.GraphicsWrapper;
import org.lee.mugen.renderer.MugenDrawer;
import org.lee.mugen.util.Logger;

/**
 * Example application demonstrating how to use the LibGDX renderer backend
 * 
 * This is a minimal example showing the basic setup and integration pattern.
 * For a complete game implementation, extend this class with actual game logic.
 */
public class LibGDXRendererExample implements Game {
    
    private static final String GAME_TITLE = "JMugen - LibGDX Renderer Example";
    private static final int GAME_WIDTH = 640;
    private static final int GAME_HEIGHT = 480;
    
    private GameWindow window;
    private MugenDrawer drawer;
    private long startTime;
    private int frameCount;
    
    /**
     * Initialize the game (called by the renderer)
     */
    @Override
    public void init(GameWindow window) throws Exception {
        this.window = window;
        this.drawer = GraphicsWrapper.getInstance();
        this.startTime = System.currentTimeMillis();
        this.frameCount = 0;
        
        Logger.log("LibGDX Example Game initialized");
        Logger.log("Window: %dx%d", GAME_WIDTH, GAME_HEIGHT);
    }
    
    /**
     * Update game logic (called once per frame)
     */
    @Override
    public void update(int delta) throws Exception {
        frameCount++;
        
        // Calculate FPS every second
        long elapsed = System.currentTimeMillis() - startTime;
        if (elapsed >= 1000) {
            double fps = frameCount / (elapsed / 1000.0);
            Logger.log("FPS: %.2f", fps);
            frameCount = 0;
            startTime = System.currentTimeMillis();
        }
        
        // Example: Update game state
        updateGameState();
    }
    
    /**
     * Update game state (implement your game logic here)
     */
    private void updateGameState() {
        // Get mouse status
        GameWindow.MouseCtrl mouse = window.getMouseStatus();
        
        // Get timer
        long currentTime = System.currentTimeMillis();
        
        // Example: Simple animation
        float x = (float) Math.sin(currentTime / 1000.0) * 100 + 320;
        float y = (float) Math.cos(currentTime / 1000.0) * 100 + 240;
        
        // Update player positions, game logic, etc.
    }
    
    /**
     * Start the game
     */
    public void start() throws Exception {
        // Initialize graphics wrapper with LibGDX backend
        GraphicsWrapper.init();
        
        // Get the game window and drawer
        this.drawer = GraphicsWrapper.getInstance();
        this.window = drawer.getInstanceOfGameWindow();
        
        // Configure window
        window.setTitle(GAME_TITLE);
        window.setResolution(GAME_WIDTH, GAME_HEIGHT);
        
        // Set this class as the game callback
        window.setGameWindowCallback(this);
        
        // Start the game loop (blocking call)
        Logger.log("Starting game loop...");
        window.start();
    }
    
    @Override
    public void render() throws Exception {
        // Rendering is handled by the LibGDX backend via GraphicsWrapper and LGDXGameWindow
    }
    
    @Override
    public Game getNext() throws Exception {
        return null;
    }
    
    @Override
    public void reInit(GameWindow window) throws Exception {
        this.window = window;
    }
    
    @Override
    public void free() {
        if (drawer instanceof LGDXMugenDrawer) {
            ((LGDXMugenDrawer) drawer).dispose();
        }
    }
    
    /**
     * Stop the game
     */
    public void stop() {
        free();
    }
    
    /**
     * Main entry point
     */
    public static void main(String[] args) {
        try {
            LibGDXRendererExample example = new LibGDXRendererExample();
            example.start();
        } catch (Exception e) {
            Logger.error("Fatal error in game", e);
            System.exit(1);
        }
    }
}
