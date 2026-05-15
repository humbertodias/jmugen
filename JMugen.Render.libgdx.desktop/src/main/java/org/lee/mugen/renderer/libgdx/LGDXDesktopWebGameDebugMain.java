package org.lee.mugen.renderer.libgdx;

/**
 * Runs the same {@link LGDXWebJMugenGame} path as the GWT entry point, on LWJGL3 in the JVM,
 * so IDE Java breakpoints apply. GWT client code is compiled to JS and never executes in the
 * DevMode process, so use this launcher (or tests) to debug shared Java.
 */
public final class LGDXDesktopWebGameDebugMain {

    public static void main(String[] args) throws Exception {
        LibGDXRendererFactory.initialize();
        LibGDXRendererFactory.startGame(new LGDXWebJMugenGame());
    }
}
