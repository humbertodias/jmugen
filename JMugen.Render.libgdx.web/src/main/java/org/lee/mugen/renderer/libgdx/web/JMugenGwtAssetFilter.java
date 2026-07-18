package org.lee.mugen.renderer.libgdx.web;

import com.badlogic.gdx.backends.gwt.preloader.DefaultAssetFilter;

/**
 * LibGDX {@link DefaultAssetFilter} treats {@code .fnt} as text (AngelCode bitmap fonts). MUGEN fonts are
 * binary {@code ElecbyteFnt} files; loading them as text makes the browser report XML parse errors.
 *
 * <p>Also treat {@code .mp3}/{@code .ogg} as {@link AssetType#Binary} so {@code Gdx.files.internal} can
 * open them for {@code Gdx.audio.newMusic} (Audio-typed assets are not always readable as FileHandles).
 */
public final class JMugenGwtAssetFilter extends DefaultAssetFilter {

    @Override
    public AssetType getType(String file) {
        if (file != null) {
            String lower = file.toLowerCase();
            if (lower.endsWith(".fnt")) {
                return AssetType.Binary;
            }
            if (lower.endsWith(".mp3") || lower.endsWith(".ogg") || lower.endsWith(".wav")) {
                return AssetType.Binary;
            }
        }
        return super.getType(file);
    }

    @Override
    public boolean preload(String file) {
        // Always preload the shared title/fight BGM so first play after gesture works.
        if (file != null && file.toLowerCase().endsWith("intro.mp3")) {
            return true;
        }
        return super.preload(file);
    }
}
