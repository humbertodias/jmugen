package org.lee.mugen.renderer.libgdx.web;

/**
 * Web entry point for the shared LibGDX drawer.
 */
public class LGDXMugenDrawer extends org.lee.mugen.renderer.libgdx.core.LGDXMugenDrawer {

    public LGDXMugenDrawer() {
        this(getFactoryWindow());
    }

    public LGDXMugenDrawer(LGDXWebGameWindow gameWindow) {
        super(gameWindow);
    }

    private static LGDXWebGameWindow getFactoryWindow() {
        if (!LibGDXWebRendererFactory.isInitialized()) {
            LibGDXWebRendererFactory.initialize();
        }
        return LibGDXWebRendererFactory.getGameWindow();
    }
}
