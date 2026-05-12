package org.lee.mugen.renderer.libgdx;

import org.lee.mugen.util.Logger;

/**
 * Factory class for creating and managing LibGDX renderer instances
 * Centralizes renderer initialization and configuration
 */
public class LibGDXRendererFactory {
    
    private static LGDXMugenDrawer drawerInstance;
    private static LGDXGameWindow windowInstance;
    private static boolean isInitialized = false;
    
    private LibGDXRendererFactory() {
        // Prevent instantiation
    }
    
    /**
     * Initialize the LibGDX renderer
     * Should be called before any other methods
     */
    public static void initialize() throws Exception {
        if (isInitialized) {
            Logger.log("LibGDX renderer already initialized");
            return;
        }
        
        try {
            // Create game window
            windowInstance = new LGDXGameWindow();
            Logger.log("LibGDX GameWindow created");
            
            // Create drawer (will be instantiated properly when window is ready)
            Logger.log("LibGDX renderer initialized");
            isInitialized = true;
        } catch (Exception e) {
            Logger.error("Failed to initialize LibGDX renderer", e);
            throw e;
        }
    }
    
    /**
     * Get the MugenDrawer instance
     */
    public static LGDXMugenDrawer getDrawer() {
        if (!isInitialized) {
            throw new IllegalStateException("LibGDX renderer not initialized. Call initialize() first.");
        }
        
        if (drawerInstance == null) {
            drawerInstance = new LGDXMugenDrawer(windowInstance);
        }
        
        return drawerInstance;
    }
    
    /**
     * Get the GameWindow instance
     */
    public static LGDXGameWindow getGameWindow() {
        if (!isInitialized) {
            throw new IllegalStateException("LibGDX renderer not initialized. Call initialize() first.");
        }
        return windowInstance;
    }
    
    /**
     * Configure window properties
     */
    public static void configureWindow(String title, int width, int height, boolean resizable) {
        if (!isInitialized) {
            throw new IllegalStateException("LibGDX renderer not initialized. Call initialize() first.");
        }
        
        windowInstance.setTitle(title);
        windowInstance.setResolution(width, height);
    }
    
    /**
     * Start the game loop
     */
    public static void startGame(Object gameCallback) throws Exception {
        if (!isInitialized) {
            throw new IllegalStateException("LibGDX renderer not initialized. Call initialize() first.");
        }
        
        if (gameCallback == null) {
            throw new IllegalArgumentException("Game callback cannot be null");
        }
        
        org.lee.mugen.core.Game game = (org.lee.mugen.core.Game) gameCallback;
        windowInstance.setGameWindowCallback(game);
        windowInstance.start();
    }
    
    /**
     * Shutdown the renderer
     */
    public static void shutdown() {
        if (drawerInstance != null) {
            drawerInstance.dispose();
            drawerInstance = null;
        }
        
        isInitialized = false;
        windowInstance = null;
        
        Logger.log("LibGDX renderer shutdown complete");
    }
    
    /**
     * Check if renderer is initialized
     */
    public static boolean isInitialized() {
        return isInitialized;
    }
    
    /**
     * Get renderer configuration string
     */
    public static String getConfigurationString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== LibGDX Renderer Configuration ===\n");
        sb.append("Status: ").append(isInitialized ? "Initialized" : "Not Initialized").append("\n");
        
        if (windowInstance != null) {
            sb.append("Window Title: ").append("JMugen - LibGDX").append("\n");
            sb.append("Window Size: ").append(windowInstance.getGameWidth())
              .append("x").append(windowInstance.getGameHeight()).append("\n");
            sb.append("Initialization Complete: ").append(windowInstance.isFinishInit()).append("\n");
        }
        
        return sb.toString();
    }
}
