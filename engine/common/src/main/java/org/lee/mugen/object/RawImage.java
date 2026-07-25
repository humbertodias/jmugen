package org.lee.mugen.object;

import java.io.Serializable;

public class RawImage implements Serializable {
	int width;
	int height;
	int[] data;
	public RawImage(int[] data, int w, int h) {
		this.data = data;
		width = w;
		height = h;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int[] getData() {
		return data;
	}
	public void setData(int[] data) {
		this.data = data;
	}
}