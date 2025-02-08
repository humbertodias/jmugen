package org.lee.mugen.imageIO;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.IndexColorModel;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.lee.mugen.io.LittleEndianDataInputStream;

public class PCXLoader {
	public static final int HEADER_SIZE = 128;

	public static BufferedImage loadImageColorIndexed(InputStream file, PCXPalette pal,
			boolean isPalUse, boolean isUseColorKey, int colorDepth)
			throws IOException {
		byte[] data = ByteArrayBuilder.fromStream(file);
		file.close();

		PCXHeader header = new PCXHeader(data);
		if (!isPalUse)
			pal.load(new ByteArrayInputStream(data));

		InputStream in = new ByteArrayInputStream(data);

		int width = header.getWidth();
		int height = header.getHeight();
		in.skip(128);
		IndexColorModel icm = new IndexColorModel(8, 256,
				pal.r, pal.g,pal.b, 0);
		BufferedImage image = new BufferedImage(width, height,
										BufferedImage.TYPE_BYTE_INDEXED, icm);
		int xp = 0;
		int yp = 0;
		int value;
		int count;
		int scan = 1;
		byte[] arrays = new byte[width * height * scan];
		image.getRaster().getDataElements(0, 0, width, height, arrays);

		while (yp < height) {
			value = in.read();
			// if the byte has the top two bits set
			if (value >= 192) {
				count = (value - 192);
				value = in.read();
			} else {
				count = 1;
			}
			// update data
			for (int i = 0; i < count; i++) {
				if (xp < width) {
					int index = value - colorDepth < 0 ? 0 : value - colorDepth;
					if (value == 0) {
					} else {
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
		image.getRaster().setDataElements(0, 0, width, height, arrays);
		return image;
	}
	public static BufferedImage loadImageColorIndexed(
			ByteArrayInputStream byteArrayInputStream, PCXPalette palette,
			boolean isPalUse, boolean isUseColorKey) throws IOException {
		
		return loadImageColorIndexed(byteArrayInputStream,
				palette, isPalUse, isUseColorKey, 0);
	}
	public static BufferedImage loadImageARGB(InputStream file, PCXPalette pal,
			boolean isPalUse, boolean isUseColorKey) throws IOException {
		return loadImage(BufferedImage.TYPE_INT_ARGB, file, pal, isPalUse, isUseColorKey, false, false);
	}
	public static BufferedImage loadImageARGBPRE(InputStream file, PCXPalette pal,
			boolean isPalUse, boolean isUseColorKey) throws IOException {
		return loadImage(BufferedImage.TYPE_INT_ARGB_PRE, file, pal, isPalUse, isUseColorKey, false, false);
	}
	public static BufferedImage loadImageRGB(InputStream file, PCXPalette pal,
			boolean isPalUse, boolean isUseColorKey) throws IOException {
		return loadImage(BufferedImage.TYPE_INT_RGB, file, pal, isPalUse, isUseColorKey, false, false);
	}
	public static BufferedImage loadImageBGR(InputStream file, PCXPalette pal,
			boolean isPalUse, boolean isUseColorKey) throws IOException {
		return loadImage(BufferedImage.TYPE_3BYTE_BGR, file, pal, isPalUse, isUseColorKey, false, false);
	}
	public static BufferedImage loadImage(int type, InputStream file, PCXPalette pal,
			boolean isPalUse, boolean isUseColorKey) throws IOException {
		return loadImage(type, file, pal, isPalUse, isUseColorKey, false, false);
	}	
	public static BufferedImage loadImage(int type, InputStream file, PCXPalette pal,
			boolean isPalUse, boolean isUseColorKey, boolean isFlipH,
			boolean isFlipV) throws IOException {
		return loadImage(type, file, pal, isPalUse, isUseColorKey, isFlipH, isFlipV, 0);
	}
	public static BufferedImage loadImage(int type, InputStream file, PCXPalette pal,
			boolean isPalUse, boolean isUseColorKey, boolean isFlipH,
			boolean isFlipV, int color) throws IOException {
		BufferedImage image = (BufferedImage) loadImageColorIndexed(file, pal, isPalUse, isUseColorKey, color);
		int width = image.getWidth();
		int height = image.getHeight();
		BufferedImage result = new BufferedImage(width, height, type);
		if (isFlipH || isFlipV) {
			Graphics2D g = (Graphics2D) result.getGraphics();
			g.scale(isFlipH ? -1 : 1, isFlipV ? -1 : 1);
			g.drawImage(image, isFlipH ? -width : 0, isFlipV ? -height : 0, null);
		} else {
			result.getGraphics().drawImage(image, 0, 0, null);
		}
		return result;
	}

	
	protected static boolean isAboutTheSameColor(int r1, int g1, int b1, int r2,
			int g2, int b2, int[] alpha) {
		alpha[0] = 255;
		if (r1 == r2 && b1 == b2 && g1 == g2) {
			alpha[0] = 0;
			return true;
		} else
			return false;
	}

	/**
	 * The header data block
	 */
	public static class PCXHeader {
		public byte manufacturer;
		public byte version;
		public byte encoding;
		public byte bitsPerPixel;
		public int xmin;
		public int ymin;
		public int xmax;
		public int ymax;
		public int hdpi;
		public int vdpi;
		public byte[] colormap = new byte[48];
		public byte reserved;
		public byte planes;
		public int bytesPerLine;

		public PCXHeader(byte[] data) throws IOException {
			LittleEndianDataInputStream in = new LittleEndianDataInputStream(
					new DataInputStream((new ByteArrayInputStream(data))));
			manufacturer = (byte) in.read();
			version = (byte) in.read();
			encoding = (byte) in.read();
			bitsPerPixel = (byte) in.read();
			xmin = in.readUnsignedShort();
			ymin = in.readUnsignedShort();
			xmax = in.readUnsignedShort();
			ymax = in.readUnsignedShort();
			hdpi = in.readUnsignedShort();
			vdpi = in.readUnsignedShort();
			in.read(colormap);
			reserved = (byte) in.read();
			planes = (byte) in.read();
			bytesPerLine = in.readUnsignedShort();

			in.close();
		}
		public Dimension getDimension() {
			int width = xmax - xmin + 1;
			int height = ymax - ymin + 1;
			return new Dimension(width, height);
		}
		public int getWidth() {
			return xmax - xmin + 1;
		}
		public int getHeight() {
			return ymax - ymin + 1;
		}
	}

}
