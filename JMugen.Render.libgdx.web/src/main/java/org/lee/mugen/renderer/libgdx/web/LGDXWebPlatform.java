package org.lee.mugen.renderer.libgdx.web;

import org.lee.mugen.core.ResourceRoots;
import org.lee.mugen.io.MugenDataStreams;
import org.lee.mugen.renderer.GraphicsWrapper;

/**
 * One-time browser setup before {@link org.lee.mugen.core.GameMenu} loads Mugen data.
 */
public final class LGDXWebPlatform {

    /** Gdx internal prefix: assets mirror repo {@code data/} under {@code data/} (see web {@code pom.xml}). */
    public static final String DATA_PREFIX = "data/";

    private static boolean prepared;

    private LGDXWebPlatform() {
    }

    public static void prepare() {
        if (prepared) {
            return;
        }
        ResourceRoots.setResourcePrefixOverride(DATA_PREFIX);
        LGDXWebGdxData.install();
        GraphicsWrapper.installDrawer(LibGDXWebRendererFactory.getDrawer());
        prepared = true;
    }

    static void resetForTests() {
        prepared = false;
        MugenDataStreams.resetDefaults();
    }
}
