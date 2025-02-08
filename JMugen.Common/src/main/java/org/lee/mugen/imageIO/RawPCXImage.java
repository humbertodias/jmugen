package org.lee.mugen.imageIO;

import java.nio.ByteBuffer;

public class RawPCXImage {
	ByteBuffer buffer;
	PCXPalette palette;
	public RawPCXImage(byte[] byteArray, PCXPalette pal) {
		buffer = ByteBuffer.wrap(byteArray);
		palette = pal;
	}
	public byte[] getData() {
		return buffer.array();
	}
	public void setData(byte[] data) {
		buffer = ByteBuffer.wrap(data);
	}
	public PCXPalette getPalette() {
		return palette;
	}
	public void setPalette(PCXPalette palette) {
		this.palette = palette;
	}
	public ByteBuffer getBuffer() {
		return buffer;
	}
	
}