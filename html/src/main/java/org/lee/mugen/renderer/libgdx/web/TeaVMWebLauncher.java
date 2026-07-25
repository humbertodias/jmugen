package org.lee.mugen.renderer.libgdx.web;

import com.github.xpenatan.gdx.teavm.backends.web.WebApplication;
import com.github.xpenatan.gdx.teavm.backends.web.WebApplicationConfiguration;
import org.lee.mugen.core.GameMenu;

/**
 * TeaVM entry point: same {@link GameMenu} flow as Android / desktop LibGDX.
 */
public final class TeaVMWebLauncher {

    public static void main(String[] args) {
        LibGDXWebRendererFactory.initialize();
        LGDXWebPlatform.prepare();
        LGDXWebGameWindow window = LibGDXWebRendererFactory.getGameWindow();
        window.setGameWindowCallback(GameMenu.getInstance());

        WebApplicationConfiguration config = new WebApplicationConfiguration();
        config.width = window.getGameWidth();
        config.height = window.getGameHeight();
        config.showDownloadLogs = true;
        new WebApplication(window, config);
    }

    private TeaVMWebLauncher() {
    }
}
