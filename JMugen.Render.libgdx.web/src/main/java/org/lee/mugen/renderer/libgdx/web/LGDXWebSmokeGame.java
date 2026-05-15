package org.lee.mugen.renderer.libgdx.web;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import org.lee.mugen.core.Game;
import org.lee.mugen.renderer.GameWindow;

/**
 * Web-only smoke scene: proves the shared LibGDX core drawer under GWT and refreshes the host
 * page status overlay.
 */
public class LGDXWebSmokeGame implements Game {

    private LGDXMugenDrawer drawer;
    private int elapsed;

    @Override
    public void init(GameWindow container) throws Exception {
        drawer = LibGDXWebRendererFactory.getDrawer();
        updateStatus();
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
        if (drawer == null) {
            return;
        }

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

    private void updateStatus() {
        Element status = Document.get().getElementById("jmugen-status");
        if (status != null) {
            status.setInnerHTML(
                "<strong>JMugen Web rodando.</strong><br>"
                    + "Render LibGDX/GWT ligado ao drawer compartilhado de core. "
                    + "O jogo completo ainda precisa da camada web de assets e audio.");
            status.getStyle().setDisplay(Style.Display.NONE);
        }
    }
}
