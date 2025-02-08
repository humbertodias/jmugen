package org.lee.mugen.imageIO;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.DirectColorModel;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;

/**
 * A utility to perform the scale2x algorithm on a Java Image
 * 
 * @author Kevin Glass
 */
public class ImageScale2x
{
    /** The src data from the image */
    private int[] srcData;
    /** The width of the image */
    private int width;
    /** The height of the image */
    private int height;
	private BufferedImage srcImage;
	private MemoryImageSource _memImage;
	private int[] dstImage;
    
    /**
     * Create a new scaler that will scale the passed image
     *
     * @param srcImage The image to be scaled
     */
    public ImageScale2x(BufferedImage srcImage)
    {
    	this.srcImage = srcImage;
        width = srcImage.getWidth();
        height = srcImage.getHeight();
        
        srcData = new int[width*height];
        srcImage.getRGB(0,0,width,height,srcData,0,width);      
        
        dstImage = new int[srcData.length*4];
       	
        DirectColorModel colorModel =
            new DirectColorModel(32, 0xff0000, 65280, 255);
        _memImage =
            new MemoryImageSource(
                width * 2,
                height * 2,
                colorModel,
                dstImage,
                0,
                width * 2);
        image = Toolkit.getDefaultToolkit().createImage(_memImage);
    	
    }
    private Image image;
    /**
     * Retrieve the scaled image. Note this is the method that actually 
     * does the work so it may take some time to return
     * 
     * @return The newly scaled image
     */
    public Image getScaledImage() {
    	refreshSrcArray();
    	getScaled2x(srcData, dstImage, width, height);
    	image.flush();
        return image;
    }
    public static int[] getScaled2x(int[] pix, int[] pixel, int _width, int _height) {
        int i1, i2;
        int srcofs;
        int dstofs;
        int dx, dy;
        int x, y, e;

        // Scale2x algorithm (based on AdvanceMAME Scale2x)

        dx = _width;
        dy = _height;

        int finalRowOffset = _width * (_height - 1);
        int destinationFinalRowOffset = ((_width * _height) << 2) - (_width << 2);
        for (int c=0; c<dx; c++) {
          i1 = (c << 1);
          i2 = i1 + (_width << 1);
          e = pix[c];
          pixel[i1] = e;
          pixel[i1 + 1] = e;
          pixel[i2] = e;
          pixel[i2 + 1] = e;

          i1 += destinationFinalRowOffset;
          i2 += destinationFinalRowOffset;
          e = pix[c+finalRowOffset];
          pixel[i1] = e;
          pixel[i1 + 1] = e;
          pixel[i2] = e;
          pixel[i2 + 1] = e;
        }

        srcofs = dx + 1;
        dstofs = (_width << 2);

        for (y = 1; y < dy - 1; y++) {
          i1 = dstofs;
          i2 = i1 + (_width << 1);

          pixel[i1] = pix[srcofs - 1];
          pixel[i1 + 1] = pix[srcofs - 1];
          pixel[i2] = pix[srcofs - 1];
          pixel[i2 + 1] = pix[srcofs - 1];

          for (x = 1; x < dx - 1; x++) {
            int E0, E1, E2, E3;
            int A, B, C, D, E, F, G, H, I;

            A = pix[srcofs - dx - 1];
            B = pix[srcofs - dx];
            C = pix[srcofs - dx + 1];
            D = pix[srcofs - 1];
            E = pix[srcofs];
            F = pix[srcofs + 1];
            G = pix[srcofs + dx - 1];
            H = pix[srcofs + dx];
            I = pix[srcofs + dx + 1];

            //
            //	ABC
            //	DEF
            //	GHI

            //	E0E1
            //	E2E3
            //

            E0 = D == B && B != F && D != H ? D : E;
            E1 = B == F && B != D && F != H ? F : E;
            E2 = D == H && D != B && H != F ? D : E;
            E3 = H == F && D != H && B != F ? F : E;

            i1 = dstofs + (x << 1);
            i2 = i1 + (_width << 1);

            pixel[i1] = E0;
            pixel[i1 + 1] = E1;
            pixel[i2] = E2;
            pixel[i2 + 1] = E3;

            srcofs++;
          }

          // Row last pixel (just scale standard)
          i1 = dstofs + (_width << 1) - 2;
          i2 = i1 + (_width << 1);

          pixel[i1] = pix[srcofs];
          pixel[i1 + 1] = pix[srcofs];
          pixel[i2] = pix[srcofs];
          pixel[i2 + 1] = pix[srcofs];

          dstofs += (_width << 2);


          srcofs += 2;
        }

        return pixel;
      }
    public void refreshSrcArray() {
   		 PixelGrabber pixelgrabber =
    			new PixelGrabber(srcImage, 0, 0, width, height, srcData, 0, width);
        try {
            pixelgrabber.grabPixels();
        } catch (InterruptedException _ex) {
            _ex.printStackTrace();
        }

    }
}
