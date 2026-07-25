package org.lee.mugen.core;

import org.lee.mugen.renderer.PalFxSub;

/** GWT stub for title screen / background rendering. */
public class GameGlobalEvents {

    private final PalFxSub bGPalFX = new PalFxSub();

    public PalFxSub getBgpalfx() {
        return bGPalFX;
    }

    public boolean isSuperPause() {
        return false;
    }

    public boolean canGameProcessWithPause(Object sprite) {
        return true;
    }

    public boolean canGameProcessWithSuperPause(Object sprite) {
        return true;
    }
}
