package org.lee.mugen.renderer.libgdx;

import org.lee.mugen.renderer.libgdx.core.LGDXMugenDrawer;

/**
 * Desktop convenience: same shared {@link org.lee.mugen.renderer.libgdx.core.LGDXWebJMugenGame}
 * wired to {@link LibGDXRendererFactory}.
 */
public final class LGDXWebJMugenGame extends org.lee.mugen.renderer.libgdx.core.LGDXWebJMugenGame {

    public LGDXWebJMugenGame() {
        super(resolveDrawer());
    }

    private static LGDXMugenDrawer resolveDrawer() {
        if (!LibGDXRendererFactory.isInitialized()) {
            try {
                LibGDXRendererFactory.initialize();
            } catch (Exception e) {
                throw new RuntimeException("Failed to initialize LibGDX renderer factory", e);
            }
        }
        return LibGDXRendererFactory.getDrawer();
    }
}
