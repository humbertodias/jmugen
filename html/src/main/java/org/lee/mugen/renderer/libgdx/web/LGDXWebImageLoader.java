package org.lee.mugen.renderer.libgdx.web;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import org.lee.mugen.imageIO.RawPCXImage;
import org.lee.mugen.renderer.ImageContainer;
import org.lee.mugen.renderer.libgdx.core.LGDXImageLoader;
import org.lee.mugen.renderer.libgdx.core.LGDXMugenDrawer;
import org.lee.mugen.renderer.libgdx.core.PcxPixmapDecoder;
import org.lee.mugen.util.Logger;

/**
 * Browser PCX decode for SFF/fonts (same approach as Android, no AWT).
 */
final class LGDXWebImageLoader implements LGDXImageLoader {

    @Override
    public ImageContainer getImageContainer(Object imageData, int colors) {
        if (imageData instanceof RawPCXImage) {
            RawPCXImage pcxImage = (RawPCXImage) imageData;
            try {
                Pixmap pixmap = PcxPixmapDecoder.decode(pcxImage.getData(), colors);
                int w = pixmap.getWidth();
                int h = pixmap.getHeight();
                Texture texture = new Texture(pixmap);
                pixmap.dispose();
                return new ImageContainer(texture, w, h) {
                    @Override
                    public void free() {
                        texture.dispose();
                    }
                };
            } catch (Exception e) {
                Logger.error("Web PCX decode failed: %s", e);
                return null;
            }
        }
        Logger.error(
            "Web image decode not implemented for type: %s",
            imageData != null ? imageData.getClass().getName() : "null");
        return null;
    }
}
