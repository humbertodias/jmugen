package org.lee.mugen.renderer.libgdx;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import java.awt.image.BufferedImage;
import org.lee.mugen.imageIO.RawPCXImage;
import org.lee.mugen.renderer.ImageContainer;
import org.lee.mugen.renderer.libgdx.core.LGDXImageLoader;
import org.lee.mugen.renderer.libgdx.core.LGDXMugenDrawer;
import org.lee.mugen.renderer.libgdx.core.PcxPixmapDecoder;
import org.lee.mugen.util.Logger;

/**
 * Desktop image conversion: PCX via shared {@link PcxPixmapDecoder} (same as Android).
 * Avoids AWT {@code BufferedImage#getRGB} per-pixel uploads that stall SFF load for minutes.
 */
class LGDXDesktopImageLoader implements LGDXImageLoader {

    @Override
    public ImageContainer getImageContainer(Object imageData, int colors) {
        try {
            if (imageData instanceof RawPCXImage) {
                RawPCXImage pcxImage = (RawPCXImage) imageData;
                Pixmap pixmap = PcxPixmapDecoder.decode(pcxImage.getData(), colors);
                return toTextureContainer(pixmap);
            }

            if (imageData instanceof BufferedImage) {
                BufferedImage bufferedImage = (BufferedImage) imageData;
                int width = bufferedImage.getWidth();
                int height = bufferedImage.getHeight();
                Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
                int[] row = new int[width];
                for (int y = 0; y < height; y++) {
                    bufferedImage.getRGB(0, y, width, 1, row, 0, width);
                    for (int x = 0; x < width; x++) {
                        pixmap.drawPixel(x, y, LGDXMugenDrawer.argbToRgba(row[x]));
                    }
                }
                return toTextureContainer(pixmap);
            }
        } catch (Exception e) {
            Logger.error("Error loading LibGDX desktop image container", e);
        }

        return null;
    }

    private static ImageContainer toTextureContainer(Pixmap pixmap) {
        int width = pixmap.getWidth();
        int height = pixmap.getHeight();
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
