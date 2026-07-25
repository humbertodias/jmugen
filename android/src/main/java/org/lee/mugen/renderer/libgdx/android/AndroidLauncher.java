package org.lee.mugen.renderer.libgdx.android;

import android.os.Bundle;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import java.io.File;
import java.io.IOException;
import org.lee.mugen.core.GameMenu;
import org.lee.mugen.renderer.GraphicsWrapper;

/**
 * LibGDX Android entry: installs Mugen data, wires {@link GraphicsWrapper} to the Android drawer, then runs
 * the same {@link GameMenu} flow as desktop {@code TestMenu}.
 */
public class AndroidLauncher extends AndroidApplication {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        File dataRoot = new File(getFilesDir(), "jmugen_data");
        try {
            AndroidGameData.ensureInstalled(this, "data", dataRoot);
        } catch (IOException e) {
            throw new RuntimeException("Failed to extract game data from assets", e);
        }
        System.setProperty("jmugen.resource", dataRoot.getAbsolutePath() + "/");
        System.setProperty("jmugen.renderClass", "org.lee.mugen.renderer.libgdx.android.LGDXAndroidMugenDrawer");

        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        config.useImmersiveMode = true;
        config.useAccelerometer = false;
        config.useCompass = false;

        LibGDXAndroidRendererFactory.initialize();
        try {
            GraphicsWrapper.init();
        } catch (Exception e) {
            throw new RuntimeException("GraphicsWrapper.init failed", e);
        }

        LGDXAndroidGameWindow shell = LibGDXAndroidRendererFactory.getGameWindow();
        shell.setGameWindowCallback(GameMenu.getInstance());
        initialize(shell, config);
    }
}
