package org.lee.mugen.renderer.libgdx;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import org.lee.mugen.imageIO.PCXLoader;
import org.lee.mugen.imageIO.PCXLoader.PCXHeader;
import org.lee.mugen.imageIO.PCXPalette;
import org.lee.mugen.imageIO.RawPCXImage;
import org.lee.mugen.renderer.ImageContainer;
import org.lee.mugen.renderer.libgdx.core.LGDXImageLoader;
import org.lee.mugen.renderer.libgdx.core.LGDXMugenDrawer;
import org.lee.mugen.util.Logger;

/**
 * Desktop-only image conversion for Java2D/PCX formats that GWT cannot compile.
 */
class LGDXDesktopImageLoader implements LGDXImageLoader {

    @Override
    public ImageContainer getImageContainer(Object imageData, int colors) {
        try {
            if (imageData instanceof RawPCXImage) {
                RawPCXImage pcxImage = (RawPCXImage) imageData;
                byte[] data = pcxImage.getData();
                PCXHeader header = new PCXHeader(data);
                BufferedImage bufferedImage = PCXLoader.loadImage(
                    BufferedImage.TYPE_INT_ARGB,
                    new ByteArrayInputStream(data),
                    new PCXPalette(),
                    false,
                    false,
                    false,
                    false,
                    colors
                );
                return toContainer(bufferedImage, header.getWidth(), header.getHeight());
            }

            if (imageData instanceof BufferedImage) {
                BufferedImage bufferedImage = (BufferedImage) imageData;
                return toContainer(
                    bufferedImage,
                    bufferedImage.getWidth(),
                    bufferedImage.getHeight()
                );
            }
        } catch (Exception e) {
            Logger.error("Error loading LibGDX desktop image container", e);
        }

        return null;
    }

    private ImageContainer toContainer(BufferedImage bufferedImage, int width, int height) {
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixmap.drawPixel(
                    x,
                    y,
                    LGDXMugenDrawer.argbToRgba(bufferedImage.getRGB(x, y))
                );
            }
        }

        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        return new ImageContainer(texture, width, height) {
            @Override
            public void free() {
                texture.dispose();
            }
        };
    }
}
