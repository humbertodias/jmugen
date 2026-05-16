package org.lee.mugen.renderer.libgdx.android;

import org.lee.mugen.renderer.ImageContainer;
import org.lee.mugen.renderer.libgdx.core.LGDXImageLoader;

/**
 * Android image path (PCX / Java2D pipeline is not wired yet). Desktop uses {@code java.awt}; extend here when needed.
 */
final class LGDXAndroidImageLoader implements LGDXImageLoader {

    @Override
    public ImageContainer getImageContainer(Object imageData, int colors) {
        throw new UnsupportedOperationException("Android image decode not implemented for this type: " + imageData);
    }
}
