package org.lee.mugen.renderer;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.lee.mugen.imageIO.PCXLoader;
import org.lee.mugen.imageIO.RawPCXImage;

public class DeferedImageLoader extends ImageContainer {

	public DeferedImageLoader(RawPCXImage img, int width, int height) {
		super(img, width, height);
	}

	@Override
	public Object getImg() {
		if (img instanceof RawPCXImage) {
			RawPCXImage pcx = (RawPCXImage) img;
			try {
				img = (BufferedImage) PCXLoader.loadImageColorIndexed(
						new ByteArrayInputStream(pcx.getData()), 
						pcx.getPalette(), false, true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return super.getImg();
	}
}
