package org.lee.mugen.sprite.baseForParse;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;

import org.lee.mugen.imageIO.PCXLoader;
import org.lee.mugen.imageIO.RawPCXImage;
import org.lee.mugen.object.RawImage;
import org.lee.mugen.object.Rectangle;
import org.lee.mugen.renderer.GraphicsWrapper;
import org.lee.mugen.renderer.ImageContainer;

public class ImageSpriteSFF implements Serializable {
	private transient ImageContainer _image;
    private int _grpNum;
    private int _imgNum;
    private Rectangle _rect;

    private int _xAxis;
    private int _yAxis;
    private void writeObject(ObjectOutputStream out) throws IOException {
		out.defaultWriteObject();
		try {
			Field f = ImageContainer.class.getDeclaredField("img");
			f.setAccessible(true);
			RawPCXImage pcx = (RawPCXImage) f.get(_image);
			BufferedImage image = PCXLoader.loadImageARGB(
					new ByteArrayInputStream(pcx.getData()), pcx.getPalette(), true, true);
			int[] data = (int[]) ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
			out.writeObject(new RawImage(data, image.getWidth(), image.getHeight()));
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
	private void readObject(ObjectInputStream in) throws IOException,
			ClassNotFoundException {
		in.defaultReadObject();
		try {
			RawImage ri = (RawImage) in.readObject();
			_image = GraphicsWrapper.getInstance().getImageContainer(ri);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    public ImageSpriteSFF(int grpNum, int imgNum, RawPCXImage pcx, int xAxis, int yAxis) throws IOException {
        _grpNum = grpNum;
         _imgNum = imgNum;
        _xAxis = xAxis;
        _yAxis = yAxis;
       	_image = GraphicsWrapper.getInstance().getImageContainer(pcx);
        _rect = new Rectangle(0, 0, _image.getWidth(), _image.getHeight());
    }
    
    public ImageSpriteSFF(int grpNum, int imgNum, ImageContainer image, int xAxis, int yAxis) throws IOException {
        _grpNum = grpNum;
         _imgNum = imgNum;
        _xAxis = xAxis;
        _yAxis = yAxis;
       	_image = image;
        _rect = new Rectangle(0, 0, _image.getWidth(), _image.getHeight());
    }
    public int getGrpNum() {
        return _grpNum;
    }
    public int getHeight() {
        return _image.getHeight();
    }

    public ImageContainer getImage() {
    	return _image;
    }

    public int getImgNum() {
        return _imgNum;
    }
    public int getWidth() {
        return _image.getWidth();
    }
    public Rectangle getRect() {
        return _rect;
    }
    public int getXAxis() {
        return _xAxis;
    }


    public int getYAxis() {
        return _yAxis;
    }
}