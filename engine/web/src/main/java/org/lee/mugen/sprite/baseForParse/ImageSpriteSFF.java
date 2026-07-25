package org.lee.mugen.sprite.baseForParse;

import java.io.IOException;
import java.io.Serializable;
import org.lee.mugen.imageIO.RawPCXImage;
import org.lee.mugen.object.Rectangle;
import org.lee.mugen.renderer.GraphicsWrapper;
import org.lee.mugen.renderer.ImageContainer;

/** GWT: no Java serialization streams. */
public class ImageSpriteSFF implements Serializable {

    private transient ImageContainer _image;
    private int _grpNum;
    private int _imgNum;
    private Rectangle _rect;
    private int _xAxis;
    private int _yAxis;

    public ImageSpriteSFF(int grpNum, int imgNum, RawPCXImage pcx, int xAxis, int yAxis) throws IOException {
        _grpNum = grpNum;
        _imgNum = imgNum;
        _xAxis = xAxis;
        _yAxis = yAxis;
        _image = GraphicsWrapper.getInstance().getImageContainer(pcx);
        if (_image == null) {
            throw new IOException("PCX image could not be decoded (grp=" + grpNum + " img=" + imgNum + ")");
        }
        _rect = new Rectangle(0, 0, _image.getWidth(), _image.getHeight());
    }

    public ImageSpriteSFF(int grpNum, int imgNum, ImageContainer image, int xAxis, int yAxis) {
        _grpNum = grpNum;
        _imgNum = imgNum;
        _xAxis = xAxis;
        _yAxis = yAxis;
        _image = image;
        if (_image == null) {
            throw new IllegalArgumentException("image");
        }
        _rect = new Rectangle(0, 0, _image.getWidth(), _image.getHeight());
    }

    public ImageContainer getImage() {
        return _image;
    }

    public int getGrpNum() {
        return _grpNum;
    }

    public int getImgNum() {
        return _imgNum;
    }

    public int getWidth() {
        return _image.getWidth();
    }

    public int getHeight() {
        return _image.getHeight();
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
