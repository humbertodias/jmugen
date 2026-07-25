package org.lee.mugen.renderer.libgdx.core;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.lee.mugen.imageIO.PCXConstants;
import org.lee.mugen.imageIO.PCXHeader;
import org.lee.mugen.imageIO.PCXPalette;

/**
 * Decodes 8-bit PCX (same RLE + palette rules as {@link org.lee.mugen.imageIO.PCXLoader}) into a LibGDX
 * {@link Pixmap} without AWT.
 */
public final class PcxPixmapDecoder {

	private PcxPixmapDecoder() {
	}

	/**
	 * @param colorDepth same as {@code color} passed to {@code PCXLoader.loadImage(...)} (e.g. font bank offset)
	 */
	public static Pixmap decode(byte[] data, int colorDepth) throws IOException {
		PCXHeader header = new PCXHeader(data);
		int width = header.getWidth();
		int height = header.getHeight();

		PCXPalette pal = new PCXPalette();
		pal.load(new ByteArrayInputStream(data));

		ByteArrayInputStream in = new ByteArrayInputStream(data);
		long skipped = in.skip(PCXConstants.HEADER_SIZE);
		if (skipped < PCXConstants.HEADER_SIZE) {
			throw new IOException("PCX truncated header");
		}

		int xp = 0;
		int yp = 0;
		final int scan = 1;
		byte[] arrays = new byte[width * height * scan];

		while (yp < height) {
			int value = in.read();
			if (value < 0) {
				throw new IOException("Unexpected EOF in PCX payload");
			}
			int count;
			if (value >= 192) {
				count = value - 192;
				value = in.read();
				if (value < 0) {
					throw new IOException("Unexpected EOF in PCX RLE");
				}
			} else {
				count = 1;
			}
			for (int i = 0; i < count; i++) {
				if (xp < width) {
					int index = value - colorDepth < 0 ? 0 : value - colorDepth;
					if (value != 0) {
						arrays[xp * scan + yp * width * scan] = (byte) index;
					}
				}
				xp++;
				if (xp == header.bytesPerLine) {
					xp = 0;
					yp++;
					break;
				}
			}
		}
		in.close();

		Pixmap pixmap = new Pixmap(width, height, Format.RGBA8888);
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int idx = arrays[x + y * width] & 0xff;
				int argb = (idx == 0) ? 0 : pal.getColor(idx);
				pixmap.drawPixel(x, y, LGDXMugenDrawer.argbToRgba(argb));
			}
		}
		return pixmap;
	}
}
