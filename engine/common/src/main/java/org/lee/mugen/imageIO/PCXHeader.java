package org.lee.mugen.imageIO;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import org.lee.mugen.io.LittleEndianDataInputStream;

/**
 * PCX header block (no AWT). Extracted from {@link PCXLoader} so callers do not need to load AWT types.
 */
public class PCXHeader {
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
				new DataInputStream(new ByteArrayInputStream(data)));
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

	public int getWidth() {
		return xmax - xmin + 1;
	}

	public int getHeight() {
		return ymax - ymin + 1;
	}
}
