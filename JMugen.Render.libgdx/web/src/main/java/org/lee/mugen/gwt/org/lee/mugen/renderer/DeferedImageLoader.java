package org.lee.mugen.renderer;

import org.lee.mugen.imageIO.RawPCXImage;

/** GWT: PCX stays as {@link RawPCXImage}; drawer converts at render time. */
public class DeferedImageLoader extends ImageContainer {

    public DeferedImageLoader(RawPCXImage img, int width, int height) {
        super(img, width, height);
    }

    @Override
    public Object getImg() {
        return super.getImg();
    }
}
