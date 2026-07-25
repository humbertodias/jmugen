package org.lee.mugen.renderer.libgdx.android;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import org.lee.mugen.imageIO.RawPCXImage;
import org.lee.mugen.renderer.ImageContainer;
import org.lee.mugen.renderer.libgdx.core.LGDXImageLoader;
import org.lee.mugen.renderer.libgdx.core.PcxPixmapDecoder;
import org.lee.mugen.util.Logger;

/**
 * Android image decode: PCX via {@link PcxPixmapDecoder} (no AWT / no {@code java.awt.image}).
 */
final class LGDXAndroidImageLoader implements LGDXImageLoader {

	@Override
	public ImageContainer getImageContainer(Object imageData, int colors) {
		if (imageData instanceof RawPCXImage) {
			RawPCXImage pcxImage = (RawPCXImage) imageData;
			byte[] data = pcxImage.getData();
			try {
				Pixmap pixmap = PcxPixmapDecoder.decode(data, colors);
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
				Logger.error("Android PCX decode failed: %s", e);
				return null;
			}
		}
		Logger.error("Android image decode not implemented for type: %s",
				imageData != null ? imageData.getClass().getName() : "null");
		return null;
	}
}
