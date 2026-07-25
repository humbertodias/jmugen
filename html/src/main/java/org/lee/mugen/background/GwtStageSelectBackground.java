package org.lee.mugen.background;

import java.io.File;

/** GWT: stage preview backgrounds ({@code [BGdef]} / {@code [BG ...]} in stage.def). */
public class GwtStageSelectBackground extends GwtBackground {

    public GwtStageSelectBackground(GwtStageRoot root) {
        super(root, root.getCurrentDir(), "");
    }
}
