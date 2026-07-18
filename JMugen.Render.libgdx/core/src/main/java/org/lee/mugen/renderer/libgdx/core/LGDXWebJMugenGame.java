package org.lee.mugen.renderer.libgdx.core;

import org.lee.mugen.core.Game;
import org.lee.mugen.renderer.GameWindow;

/**
 * Shared LibGDX {@link Game} used to validate the core drawer on web and desktop.
 * Platform code supplies a {@link LGDXMugenDrawer} bound to the active {@link LGDXRenderContext}.
 */
public class LGDXWebJMugenGame implements Game {

    private final LGDXMugenDrawer drawer;
    private int elapsed;

    public LGDXWebJMugenGame(LGDXMugenDrawer drawer) {
        if (drawer == null) {
            throw new IllegalArgumentException("drawer");
        }
        this.drawer = drawer;
    }

    @Override
    public void init(GameWindow container) throws Exception {
        elapsed = 0;
    }

    @Override
    public void reInit(GameWindow container) throws Exception {
        init(container);
    }

    @Override
    public void update(int delta) throws Exception {
        elapsed += delta;
    }

    @Override
    public void render() throws Exception {
        float pulse = (float) ((Math.sin(elapsed / 300.0) + 1.0) * 0.5);
        drawer.setColor(0.03f, 0.04f, 0.08f, 1f);
        drawer.fillRect(0, 0, 320, 240);

        drawer.setColor(0.15f, 0.55f + pulse * 0.25f, 1f, 1f);
        drawer.drawRect(24, 24, 272, 192);
        drawer.drawLine(24, 120, 296, 120);
        drawer.drawLine(160, 24, 160, 216);

        drawer.setColor(1f, 0.75f, 0.2f, 0.92f);
        drawer.fillRect(60 + pulse * 120f, 88, 80, 64);
    }

    @Override
    public Game getNext() throws Exception {
        return this;
    }

    @Override
    public void free() {
    }
}
