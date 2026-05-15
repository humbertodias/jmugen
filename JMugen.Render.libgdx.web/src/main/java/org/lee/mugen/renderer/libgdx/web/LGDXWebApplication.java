package org.lee.mugen.renderer.libgdx.web;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;

/**
 * GWT entry point that attaches the JMugen web window to LibGDX.
 */
public class LGDXWebApplication extends GwtApplication {

    @Override
    public GwtApplicationConfiguration getConfig() {
        LGDXWebGameWindow window = LibGDXWebRendererFactory.getGameWindow();
        return new GwtApplicationConfiguration(window.getGameWidth(), window.getGameHeight());
    }

    @Override
    public ApplicationListener createApplicationListener() {
        LGDXWebGameWindow window = LibGDXWebRendererFactory.getGameWindow();
        window.setGameWindowCallback(new LGDXWebSmokeGame());
        return window;
    }
}
