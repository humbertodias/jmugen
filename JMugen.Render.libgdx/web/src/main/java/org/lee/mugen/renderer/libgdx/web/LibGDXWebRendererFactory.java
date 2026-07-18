package org.lee.mugen.renderer.libgdx.web;

import org.lee.mugen.core.Game;

/**
 * Factory for the LibGDX GWT/Web renderer.
 */
public final class LibGDXWebRendererFactory {

    private static LGDXMugenDrawer drawerInstance;
    private static LGDXWebGameWindow windowInstance;
    private static boolean initialized;

    private LibGDXWebRendererFactory() {
    }

    public static void initialize() {
        if (initialized) {
            System.out.println("LibGDX web renderer already initialized");
            return;
        }

        windowInstance = new LGDXWebGameWindow();
        initialized = true;
        System.out.println("LibGDX web renderer initialized");
    }

    public static LGDXMugenDrawer getDrawer() {
        ensureInitialized();
        if (drawerInstance == null) {
            drawerInstance = new LGDXMugenDrawer(windowInstance);
        }
        return drawerInstance;
    }

    public static LGDXWebGameWindow getGameWindow() {
        ensureInitialized();
        return windowInstance;
    }

    public static void configureWindow(String title, int width, int height, boolean resizable) {
        ensureInitialized();
        windowInstance.setTitle(title);
        windowInstance.setResolution(width, height);
    }

    public static void startGame(Object gameCallback) throws Exception {
        ensureInitialized();
        if (gameCallback == null) {
            throw new IllegalArgumentException("Game callback cannot be null");
        }

        windowInstance.setGameWindowCallback((Game) gameCallback);
        windowInstance.start();
    }

    public static void shutdown() {
        if (drawerInstance != null) {
            drawerInstance.dispose();
            drawerInstance = null;
        }
        if (windowInstance != null) {
            windowInstance.dispose();
            windowInstance = null;
        }
        initialized = false;
        System.out.println("LibGDX web renderer shutdown complete");
    }

    public static boolean isInitialized() {
        return initialized;
    }

    public static String getConfigurationString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== LibGDX Web Renderer Configuration ===\n");
        sb.append("Status: ").append(initialized ? "Initialized" : "Not Initialized").append("\n");

        if (windowInstance != null) {
            sb.append("Window Title: ").append(windowInstance.getTitle()).append("\n");
            sb.append("Window Size: ").append(windowInstance.getGameWidth())
                .append("x").append(windowInstance.getGameHeight()).append("\n");
            sb.append("Initialization Complete: ").append(windowInstance.isFinishInit()).append("\n");
        }

        return sb.toString();
    }

    private static void ensureInitialized() {
        if (!initialized) {
            initialize();
        }
    }
}
