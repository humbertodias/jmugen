package org.lee.mugen.renderer;


public class ImageContainer {
	protected Object img;
	protected int width;
	protected int height;
	
	public ImageContainer(Object img, int width, int height) {
		this.img = img;
		this.width = width;
		this.height = height;
	}

	public int getHeight() {
		return height;
	}
	public Object getImg() {
		return img;
	}
	public int getWidth() {
		return width;
	}

	public void free() {
		
	}

	public void reload(ImageContainer img) {
		this.img = img.img;
		this.width = img.getWidth();
		this.height = img.getHeight();
	}
	
}





