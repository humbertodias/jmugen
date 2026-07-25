package org.lee.mugen.renderer.libgdx.web;

import com.badlogic.gdx.Gdx;
import org.teavm.jso.browser.Window;
import org.teavm.jso.dom.html.HTMLElement;

/**
 * Small DOM helpers for the TeaVM web build (replaces GWT {@code Document}/{@code Scheduler} usage).
 */
final class WebDomHelper {

    private WebDomHelper() {
    }

    static void hideElementDeferred(String elementId) {
        if (Gdx.app == null) {
            return;
        }
        Gdx.app.postRunnable(() -> hideElement(elementId));
    }

    static void hideElement(String elementId) {
        HTMLElement el = Window.current().getDocument().getElementById(elementId);
        if (el != null) {
            el.getStyle().setProperty("display", "none");
        }
    }
}
