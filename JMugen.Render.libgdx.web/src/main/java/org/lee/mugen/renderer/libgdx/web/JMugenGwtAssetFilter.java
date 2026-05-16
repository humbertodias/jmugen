package org.lee.mugen.renderer.libgdx.web;

import com.badlogic.gdx.backends.gwt.preloader.AssetFilter;
import com.badlogic.gdx.backends.gwt.preloader.DefaultAssetFilter;

/**
 * LibGDX {@link DefaultAssetFilter} treats {@code .fnt} as text (AngelCode bitmap fonts). MUGEN fonts are
 * binary {@code ElecbyteFnt} files; loading them as text makes the browser report XML parse errors.
 */
public final class JMugenGwtAssetFilter extends DefaultAssetFilter {

    @Override
    public AssetType getType(String file) {
        if (file != null && file.toLowerCase().endsWith(".fnt")) {
            return AssetType.Binary;
        }
        return super.getType(file);
    }
}
