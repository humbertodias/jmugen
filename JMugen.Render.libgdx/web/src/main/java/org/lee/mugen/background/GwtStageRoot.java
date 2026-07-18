package org.lee.mugen.background;

import java.io.File;

/** GWT: resource root for a stage {@code .def} (parent directory of the file). */
public class GwtStageRoot {

    private final File currentDir;

    public GwtStageRoot(String resourcePath) {
        String path = resourcePath.replace('\\', '/');
        int slash = path.lastIndexOf('/');
        currentDir = new File(slash >= 0 ? path.substring(0, slash) : "");
    }

    public File getCurrentDir() {
        return currentDir;
    }
}
