package org.lee.mugen.fight;

import org.lee.mugen.sprite.baseForParse.SpriteSFF;

/** SprType root that resolves {@code fight.sff} on GWT. */
public final class GwtFightSprRoot {

    private final SpriteSFF sff;

    public GwtFightSprRoot(SpriteSFF sff) {
        this.sff = sff;
    }

    public SpriteSFF getSpr() {
        return sff;
    }
}
