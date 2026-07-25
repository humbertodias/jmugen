package org.lee.mugen.renderer.libgdx.android;

/**
 * Android entry for the shared LibGDX drawer (mirrors web {@code LGDXMugenDrawer} wiring).
 */
public class LGDXAndroidMugenDrawer extends org.lee.mugen.renderer.libgdx.core.LGDXMugenDrawer {

    public LGDXAndroidMugenDrawer() {
        this(factoryWindow());
    }

    public LGDXAndroidMugenDrawer(LGDXAndroidGameWindow gameWindow) {
        super(gameWindow);
        LibGDXAndroidRendererFactory.notifyDrawerCreated(this);
    }

    private static LGDXAndroidGameWindow factoryWindow() {
        if (!LibGDXAndroidRendererFactory.isInitialized()) {
            LibGDXAndroidRendererFactory.initialize();
        }
        return LibGDXAndroidRendererFactory.getGameWindow();
    }
}
