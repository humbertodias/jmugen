package org.lee.mugen.renderer;

/**
 * GWT: no {@link Class#forName}; web/desktop must {@link #installDrawer} before use.
 */
public class GraphicsWrapper {

    private static MugenDrawer instanceOfMugenDrawer;

    private GraphicsWrapper() {
    }

    public static void installDrawer(MugenDrawer drawer) {
        if (drawer == null) {
            throw new IllegalArgumentException("drawer");
        }
        instanceOfMugenDrawer = drawer;
    }

    public static void init() throws Exception {
        if (instanceOfMugenDrawer != null) {
            return;
        }
        throw new IllegalStateException("Call installDrawer before init on GWT");
    }

    public static MugenDrawer getInstance() {
        if (instanceOfMugenDrawer == null) {
            throw new RuntimeException("Not initialize");
        }
        return instanceOfMugenDrawer;
    }
}
