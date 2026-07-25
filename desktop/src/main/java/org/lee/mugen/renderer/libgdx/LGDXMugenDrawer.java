package org.lee.mugen.renderer.libgdx;

/**
 * Desktop entry point kept for render.properties compatibility. Rendering logic lives in core.
 */
public class LGDXMugenDrawer extends org.lee.mugen.renderer.libgdx.core.LGDXMugenDrawer {

    public LGDXMugenDrawer() {
        this(getFactoryWindow());
    }

    public LGDXMugenDrawer(LGDXGameWindow gameWindow) {
        super(gameWindow);
    }

    private static LGDXGameWindow getFactoryWindow() {
        if (!LibGDXRendererFactory.isInitialized()) {
            try {
                LibGDXRendererFactory.initialize();
            } catch (Exception e) {
                throw new RuntimeException(
                    "Failed to initialize LibGDX renderer factory",
                    e
                );
            }
        }
        return LibGDXRendererFactory.getGameWindow();
    }
}
