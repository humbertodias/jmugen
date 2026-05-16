package org.lee.mugen.renderer.libgdx.android;

import org.lee.mugen.core.Game;

/**
 * Factory for the LibGDX Android renderer (mirrors web/desktop wiring around {@link LGDXAndroidGameWindow}).
 */
public final class LibGDXAndroidRendererFactory {

    private static LGDXAndroidMugenDrawer drawerInstance;
    private static LGDXAndroidGameWindow windowInstance;
    private static boolean initialized;

    private LibGDXAndroidRendererFactory() {
    }

    public static void initialize() {
        if (initialized) {
            return;
        }
        windowInstance = new LGDXAndroidGameWindow();
        initialized = true;
    }

    public static LGDXAndroidMugenDrawer getDrawer() {
        ensureInitialized();
        if (drawerInstance == null) {
            drawerInstance = new LGDXAndroidMugenDrawer(windowInstance);
        }
        return drawerInstance;
    }

    public static LGDXAndroidGameWindow getGameWindow() {
        ensureInitialized();
        return windowInstance;
    }

    public static void startGame(Object gameCallback) throws Exception {
        ensureInitialized();
        if (gameCallback == null) {
            throw new IllegalArgumentException("game callback cannot be null");
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
    }

    public static boolean isInitialized() {
        return initialized;
    }

    private static void ensureInitialized() {
        if (!initialized) {
            initialize();
        }
    }
}
