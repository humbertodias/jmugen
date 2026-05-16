package org.lee.mugen.renderer.libgdx.web;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import org.lee.mugen.core.GameMenu;

/**
 * GWT entry point: same {@link GameMenu} flow as {@link org.lee.mugen.renderer.libgdx.android.AndroidLauncher}.
 */
public class LGDXWebApplication extends GwtApplication {

    @Override
    public GwtApplicationConfiguration getConfig() {
        LGDXWebGameWindow window = LibGDXWebRendererFactory.getGameWindow();
        return new GwtApplicationConfiguration(window.getGameWidth(), window.getGameHeight());
    }

    @Override
    public ApplicationListener createApplicationListener() {
        LibGDXWebRendererFactory.initialize();
        LGDXWebPlatform.prepare();
        LGDXWebGameWindow window = LibGDXWebRendererFactory.getGameWindow();
        window.setGameWindowCallback(GameMenu.getInstance());
        return window;
    }
}
