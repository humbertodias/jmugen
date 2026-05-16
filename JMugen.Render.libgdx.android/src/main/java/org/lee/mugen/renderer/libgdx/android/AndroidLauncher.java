package org.lee.mugen.renderer.libgdx.android;

import android.os.Bundle;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import org.lee.mugen.renderer.libgdx.core.LGDXWebJMugenGame;

/**
 * LibGDX Android entry: same smoke {@link LGDXWebJMugenGame} as desktop/web, using shared core types.
 */
public class AndroidLauncher extends AndroidApplication {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        config.useImmersiveMode = true;
        config.useAccelerometer = false;
        config.useCompass = false;

        LibGDXAndroidRendererFactory.initialize();
        LGDXAndroidGameWindow shell = LibGDXAndroidRendererFactory.getGameWindow();
        shell.setGameWindowCallback(new LGDXWebJMugenGame(LibGDXAndroidRendererFactory.getDrawer()));
        initialize(shell, config);
    }
}
