package org.lee.mugen.renderer.libgdx.core;

import org.lee.mugen.renderer.ImageContainer;

/**
 * Platform-specific image conversion for data formats that are not GWT-safe.
 */
public interface LGDXImageLoader {
    ImageContainer getImageContainer(Object imageData, int colors);
}
