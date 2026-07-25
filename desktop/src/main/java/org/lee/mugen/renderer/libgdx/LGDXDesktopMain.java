package org.lee.mugen.renderer.libgdx;

import java.io.File;
import org.lee.mugen.core.GameMenu;
import org.lee.mugen.renderer.GraphicsWrapper;

/**
 * LibGDX desktop entry: same {@link GameMenu} flow as Android / web (not the drawer smoke test).
 */
public final class LGDXDesktopMain {

    public static void main(String[] args) throws Exception {
        System.setProperty(
                "jmugen.renderClass",
                "org.lee.mugen.renderer.libgdx.LGDXMugenDrawer");
        ensureResourceRoot();

        LibGDXRendererFactory.initialize();
        GraphicsWrapper.init();
        LibGDXRendererFactory.configureWindow("JMugen - LibGDX", 640, 480, true);
        LibGDXRendererFactory.startGame(GameMenu.getInstance());
    }

    /**
     * {@code data.properties} defaults to {@code ./assets/}. Walk up from {@code user.dir} until
     * {@code assets/data/system.def} is found (or honor an existing {@code jmugen.resource}).
     */
    static void ensureResourceRoot() {
        String existing = System.getProperty("jmugen.resource");
        if (existing != null && !existing.isEmpty()) {
            return;
        }
        File dir = new File(System.getProperty("user.dir", ".")).getAbsoluteFile();
        for (int i = 0; i < 8 && dir != null; i++) {
            File assetsRoot = new File(dir, "assets");
            if (new File(assetsRoot, "data/system.def").isFile()) {
                System.setProperty("jmugen.resource", assetsRoot.getAbsolutePath() + File.separator);
                return;
            }
            dir = dir.getParentFile();
        }
    }

    private LGDXDesktopMain() {
    }
}
