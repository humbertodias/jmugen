package org.lee.mugen.renderer.lwjgl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.lee.mugen.imageIO.PCXLoader;
import org.lee.mugen.imageIO.RawPCXImage;
import org.lee.mugen.imageIO.PCXLoader.PCXHeader;
import org.lee.mugen.object.Rectangle;
import org.lee.mugen.renderer.AngleDrawProperties;
import org.lee.mugen.renderer.DrawProperties;
import org.lee.mugen.renderer.GameWindow;
import org.lee.mugen.renderer.ImageContainer;
import org.lee.mugen.renderer.MugenDrawer;
import org.lee.mugen.renderer.RGB;
import org.lee.mugen.renderer.Trans;
import org.lee.mugen.renderer.lwjgl.shader.AfterImageShader;
import org.lee.mugen.renderer.lwjgl.shader.PalFxShader;
import org.lee.mugen.util.Logger;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;

public class LMugenDrawer extends MugenDrawer {
	private boolean isScaleByForMeDebug() {
		return false;
	}
	private void processRotationProperties(AngleDrawProperties dp) {
		if (dp != null) {
			GL11.glTranslatef(dp.getXAnchor(), dp.getYAnchor(), 0);
			GL11.glRotatef(dp.getAngleset(), 0, 0, 1);
			GL11.glTranslatef(-dp.getXAnchor(), -dp.getYAnchor(), 0);

		}

	}

	public void drawRect(float x, float y, float width, float height) {
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glBegin(GL11.GL_LINE_STRIP);
		GL11.glVertex2f(x, y);
		GL11.glVertex2f(x + width, y);
		GL11.glVertex2f(x + width, y + height);
		GL11.glVertex2f(x, y + height);
		GL11.glVertex2f(x, y);
		GL11.glEnd();
		GL11.glEnable(GL11.GL_TEXTURE_2D);

	}

	public void fillRect(float x1, float y1, float width, float height) {
		GL11.glTexEnvi(GL11.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_MODE, GL11.GL_MODULATE);
		GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		// GL11.glColor4f(rgb.getA(), rgb.getG(), rgb.getB(), rgb.getA());

		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2f(x1, y1);
		GL11.glVertex2f(x1 + width, y1);
		GL11.glVertex2f(x1 + width, y1 + height);
		GL11.glVertex2f(x1, y1 + height);
		GL11.glEnd();
		GL11.glEnable(GL11.GL_TEXTURE_2D);

	}

	private void drawImage(float xlDst, float xrDst, float ytDst, float ybDst,
			float xlSrc, float xrSrc, float ytSrc, float ybSrc,
			DrawProperties dp) {
		float xScale = 1f;
		float yScale = 1f;

		if (dp.getAngleDrawProperties() != null) {
			xScale = dp.getAngleDrawProperties().getXScale();
			yScale = dp.getAngleDrawProperties().getYScale();
		}
//		GL11.glColor4f(1, 1, 1, 1);
//		GL11.glTexEnvi(GL11.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_MODE, GL11.GL_MODULATE);
//		GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);
		// draw a quad textured to match the sprite
		GL11.glBegin(GL11.GL_QUADS);
		{
			GL11.glNormal3f(0.0f, 0.0f, 1.0f);
			// Left Bottom
			GL11.glTexCoord2f(xlSrc, ytSrc);
			// GL11.glVertex2f(0, 0);
			GL11.glVertex2f(xlDst, ytDst);

			// Left Top

			GL11.glTexCoord2f(xlSrc, ybSrc);
			GL11.glVertex2f(xlDst, (ybDst - ytDst) * dp.getYScaleFactor()
					* yScale + ytDst);

			// Right Top
			GL11.glTexCoord2f(xrSrc, ybSrc);
			GL11.glVertex2f((xrDst - xlDst) * dp.getXScaleFactor() * xScale
					+ xlDst, (ybDst - ytDst) * dp.getYScaleFactor() * yScale
					+ ytDst);

			// Right Bottom
			GL11.glTexCoord2f(xrSrc, ytSrc);
			GL11.glVertex2f((xrDst - xlDst) * dp.getXScaleFactor() * xScale
					+ xlDst, ytDst);
		}
		GL11.glEnd();

	}


	static PalFxShader palFxShader = null;

	private PalFxShader getPalFxShader() {
		if (palFxShader == null) {
			palFxShader = new PalFxShader();
			palFxShader.compileShader();
		}
		return palFxShader;
	}
	
	
	static AfterImageShader afterImageShader = null;

	private AfterImageShader getAfterImageShader() {
		if (afterImageShader == null) {
			afterImageShader = new AfterImageShader();
			afterImageShader.compileShader();
		}
		return afterImageShader;
	}

	private void drawWithPropertiesColor(DrawProperties dp) {
		Texture texture = (Texture) dp.getIc().getImg();
		float xlDst = dp.getXLeftDst();
		float xrDst = dp.getXRightDst();
		float ytDst = dp.getYTopDst();
		float ybDst = dp.getYBottomDst();

		float xlSrc = (dp.getXLeftSrc() / texture.getImageWidth())
				* texture.getWidth();
		float xrSrc = (dp.getXRightSrc() / texture.getImageWidth())
				* texture.getWidth();

		float ytSrc = (dp.getYTopSrc() / texture.getImageHeight())
				* texture.getHeight();
		float ybSrc = (dp.getYBottomSrc() / texture.getImageHeight())
				* texture.getHeight();

		ImageContainer ic = dp.getIc();
		float type = 0;
		Trans trans = dp.getTrans();
		if (dp.getImageProperties() != null) {
			// Afterimage
			trans = dp.getImageProperties().getTrans();
		}

		if (trans == Trans.ADD || trans == Trans.ADDALPHA) {
			GL11.glTexEnvf(GL11.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_MODE,
					GL11.GL_MODULATE);
			// GL11.glTexEnvf(GL11.GL_TEXTURE_ENV, GL11.GL_COMBINE_RGB, GL11.GL_ADD);
			// GL11.glTexEnvf(GL11.GL_TEXTURE_ENV, GL11.GL_COMBINE_ALPHA, GL11.GL_ADD);
			// GL11.glTexEnvf(GL11.GL_TEXTURE_ENV, GL11.GL_SRC0_RGB, GL11.GL_PREVIOUS);
			// GL11.glTexEnvf(GL11.GL_TEXTURE_ENV, GL11.GL_SRC1_RGB, GL11.GL_TEXTURE);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_DST_ALPHA);
			type = 1;
		} else if (trans == Trans.ADDALPHA) {
			GL11.glTexEnvf(GL11.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_MODE,
					GL11.GL_ADD);
			// GL11.glTexEnvf(GL11.GL_TEXTURE_ENV, GL11.GL_COMBINE_RGB, GL11.GL_ADD);
			// GL11.glTexEnvf(GL11.GL_TEXTURE_ENV, GL11.GL_COMBINE_ALPHA, GL11.GL_ADD);
			// GL11.glTexEnvf(GL11.GL_TEXTURE_ENV, GL11.GL_SRC0_RGB, GL11.GL_PREVIOUS);
			// GL11.glTexEnvf(GL11.GL_TEXTURE_ENV, GL11.GL_SRC1_RGB, GL11.GL_TEXTURE);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_DST_ALPHA);
			type = 1;
		} else if (trans == Trans.ADD1) {
			GL11.glTexEnvf(GL11.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_MODE,
					GL11.GL_MODULATE);
			// GL11.glTexEnvf(GL11.GL_TEXTURE_ENV, GL11.GL_COMBINE_RGB, GL11.GL_ADD);
			// GL11.glTexEnvf(GL11.GL_TEXTURE_ENV, GL11.GL_COMBINE_ALPHA, GL11.GL_ADD);
			// GL11.glTexEnvf(GL11.GL_TEXTURE_ENV, GL11.GL_SRC0_RGB, GL11.GL_PREVIOUS);
			// GL11.glTexEnvf(GL11.GL_TEXTURE_ENV, GL11.GL_SRC1_RGB, GL11.GL_TEXTURE);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_DST_ALPHA);
			type = 2;
		} else if (trans == Trans.SUB) {
			GL11.glTexEnvf(GL11.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_MODE, GL13.GL_COMBINE);
			GL11.glTexEnvf(GL11.GL_TEXTURE_ENV, GL13.GL_COMBINE_RGB, GL13.GL_SUBTRACT);
			GL11.glTexEnvf(GL11.GL_TEXTURE_ENV, GL13.GL_COMBINE_ALPHA, GL13.GL_SUBTRACT);
			GL11.glTexEnvf(GL11.GL_TEXTURE_ENV, GL15.GL_SRC0_RGB, GL13.GL_PREVIOUS);
			GL11.glTexEnvf(GL11.GL_TEXTURE_ENV, GL15.GL_SRC1_RGB, GL11.GL_TEXTURE);
			GL11.glBlendFunc(GL11.GL_DST_COLOR, GL11.GL_SRC_ALPHA);
			GL11.glColor4f(1, 1, 1, 1f);
			drawImage(xlDst, xrDst, ytDst, ybDst, xlSrc, xrSrc, ytSrc, ybSrc, dp);
			drawImage(xlDst, xrDst, ytDst, ybDst, xlSrc, xrSrc, ytSrc, ybSrc, dp);
			
			drawImage(xlDst, xrDst, ytDst, ybDst, xlSrc, xrSrc, ytSrc, ybSrc, dp);

			type = 3;
		} else {
//			GL11.glTexEnvi(GL11.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_MODE, GL11.GL_MODULATE);
//			GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);

		}

		if (dp.getPalfx() != null) {
			final float alpha = (float) (Math.PI * dp.getPalfx().getTimeActivate() / dp
					.getPalfx().getSinadd().getPeriod());

			final int rPlus = (int) (dp.getPalfx().getSinadd().getAmpl_r() * Math
					.sin(2 * alpha));
			final int gPlus = (int) (dp.getPalfx().getSinadd().getAmpl_g() * Math
					.sin(2 * alpha));
			final int bPlus = (int) (dp.getPalfx().getSinadd().getAmpl_b() * Math
					.sin(2 * alpha));

			final RGB ampl = new RGB(rPlus, gPlus, bPlus, 255f);
			final RGB bits = new RGB(1f / 255f, 1f / 255f, 1f / 255f, 1f / 255f);
			getPalFxShader().render(dp.getPalfx().getAdd().mul(bits),
					dp.getPalfx().getMul().mul(bits), ampl.mul(bits),
					dp.getAlpha());
			drawImage(xlDst, xrDst, ytDst, ybDst, xlSrc, xrSrc, ytSrc, ybSrc,
					dp);
			getPalFxShader().endRender();

		} else if (dp.getImageProperties() != null) {
			final RGB bits = new RGB(1f / 255f, 1f / 255f, 1f / 255f, 1f);
			
			getAfterImageShader().render(
					dp.getImageProperties().getPalbright().mul(bits),
					dp.getImageProperties().getPalcontrast().mul(bits),
					dp.getImageProperties().getPalpostbright().mul(bits),
					dp.getImageProperties().getPaladd().mul(bits),
					dp.getImageProperties().getPalmul(), type, 1f);
			drawImage(xlDst, xrDst, ytDst, ybDst, xlSrc, xrSrc, ytSrc, ybSrc, dp);
			getAfterImageShader().endRender();
		} else {
			drawImage(xlDst, xrDst, ytDst, ybDst, xlSrc, xrSrc, ytSrc, ybSrc, dp);
		}

		GL11.glDisable(GL11.GL_ALPHA_TEST);
	}


	public void draw(DrawProperties dp) {

		
		Texture text = (Texture) dp.getIc().getImg();
		
		GL11.glColor4f(1f, 1f, 1f, 1f);
		GL11.glTexEnvi(GL11.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_MODE,
				GL11.GL_MODULATE);
		GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);
		text.bind();
		drawChild(dp);

		
	}
	public void drawChild(DrawProperties dp) {
		GL11.glEnable(GL11.GL_TEXTURE_2D);

		GL11.glColorMask(true, true, true, true);
		GL11.glDisable(GL11.GL_COLOR_LOGIC_OP);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_ALPHA_TEST);


		GL11.glEnable(GL11.GL_BLEND);

		// store the current model matrix
		GL11.glPushMatrix();

		// bind to the appropriate texture for this sprite
		
		if (isScaleByForMeDebug()) {
			scale(0.5f, 0.5f); // This scale help me to see out of screen
			GL11.glTranslated(160, 240, 0);
		}

		
		processRotationProperties(dp.getAngleDrawProperties());

		drawWithPropertiesColor(dp);
		GL11.glPopMatrix();

	}

	@Override
	public void drawLine(int x1, int y1, int x2, int y2) {
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		// make sure the start and end points are drawn - GL implementations
		// seem a bit flakey on this
		
		if (isScaleByForMeDebug()) {
			scale(0.5f, 0.5f); // This scale help me to see out of screen
			GL11.glTranslated(160, 240, 0);
		}
		
		GL11.glBegin(GL11.GL_POINTS);
		GL11.glVertex2f(x1, y1);
		GL11.glVertex2f(x2, y2);
		GL11.glEnd();
		GL11.glBegin(GL11.GL_LINES);
		GL11.glVertex2f(x1, y1);
		GL11.glVertex2f(x2, y2);
		GL11.glEnd();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		
		if (isScaleByForMeDebug()) {
			GL11.glTranslated(-160, -240, 0);
			scale(2f, 2f);
		}

		
	}

	@Override
	public void setColor(float r, float g, float b, float a) {
		GL11.glColor4f(r, g, b, a);

	}

	@Override
	public void setColor(float r, float g, float b) {
		GL11.glColor3f(r, g, b);

	}

	@Override
	public void scale(float x, float y) {
		GL11.glScaled(x, y, 0);
	}



	public class ImageContainerText extends ImageContainer {

		private int color;
		public ImageContainerText(Object img, int width, int height) {
			super(img, width, height);
		}
		public ImageContainerText(int color, Object img, int width, int height) {
			super(img, width, height);
			this.color = color;
		}
		
		
		private static final int RAW_PCX = 0;
		private static final int BYTE = 1;
		private static final int TEXTURE = 2;
		
		AtomicInteger imageStatus = new AtomicInteger(0);
		@Override
		public Object getImg() {
			synchronized (this) {
				if (imageStatus.get() == TEXTURE) {
					return img;
				} else if (imageStatus.get() == BYTE) {
					try {
						img = TextureLoader.getTextureLoader().getTexture((ByteBuffer) img, width, height);
						imageStatus.set(TEXTURE);
						return img;
					} catch (Exception e) {
						throw new IllegalStateException("Ca ne devrait pas arrive", e);
					}
				} else if (imageStatus.get() == RAW_PCX) {
					RawPCXImage pcx = (RawPCXImage) img;
					try {
						img = getDefaultImageType(pcx, color);
						img = TextureLoader.getTextureLoader().getTexture((BufferedImage) img);
						imageStatus.set(TEXTURE);
						return img;
					} catch (IOException e) {
						throw new IllegalStateException("Ca ne devrait pas arrive");
					}
				}
			}
			throw new IllegalStateException();
		}

		public void free() {
			synchronized (this) {
				if (imageStatus.get() == TEXTURE && img != null)
					TextureLoader.getTextureLoader().free((Texture) img);
			}
		}
		
		@Override
		public void reload(ImageContainer img) {
			synchronized (this) {
				ImageContainerText imgText = (ImageContainerText) img;
				this.img = imgText.img;
				this.width = img.getWidth();
				this.height = img.getHeight();
				imageStatus.set(imgText.imageStatus.get());
			}

		}
		public void prepareImageToTexture() {
			synchronized (this) {
				if (imageStatus.get() == RAW_PCX) {
					RawPCXImage pcx = (RawPCXImage) img;
					
					img = getDefaultImageType(pcx, color);
					img =TextureLoader.getTextureLoader().convertImageData((BufferedImage) img);
					
					imageStatus.set(BYTE);
					
					
					

				}
			}
		}
		
	}
	private BufferedImage getDefaultImageType(RawPCXImage pcx, int color) {
		try {
			final BufferedImage image = PCXLoader.loadImage(
					BufferedImage.TYPE_INT_ARGB,
					new ByteArrayInputStream(pcx.getData()),
					pcx.getPalette(), false, true, false, false, color);
			return image;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		throw new IllegalStateException();
	}
	
	
	private static final int LIST_IMG_TO_PROCESS_COUNT = 0;
	private static final int LIST_IMG_TO_PROCESS_THREAD_YELD_TIME = 10;
	private static List<ImageContainerText>[] IMAGE_TO_PROCESS_LIST = null;
	private static boolean[] jobFinish = new boolean[LIST_IMG_TO_PROCESS_COUNT];
	private static int currentListToAdd = 0;
	
	
	private static void addToImageToProcess(ImageContainerText img) {
		if (LIST_IMG_TO_PROCESS_COUNT <= 0)
			return;
		if (IMAGE_TO_PROCESS_LIST == null) {
			IMAGE_TO_PROCESS_LIST = new List[LIST_IMG_TO_PROCESS_COUNT];
			for (int i = 0; i < LIST_IMG_TO_PROCESS_COUNT; i++) {
				IMAGE_TO_PROCESS_LIST[i] = new LinkedList<ImageContainerText>();
			}
		}
		if (currentListToAdd > IMAGE_TO_PROCESS_LIST.length - 1) {
			currentListToAdd = 0;
		}
		IMAGE_TO_PROCESS_LIST[currentListToAdd].add(img);
		currentListToAdd++;
	}
	

	private static void prepareImageToProcess(List<ImageContainerText> list) {
		Collections.sort(list, IMAGE_CONTAINER_COMPARATOR);
		for (Iterator<LMugenDrawer.ImageContainerText> iter = list.iterator(); iter.hasNext();) {
			iter.next().prepareImageToTexture();
			iter.remove();
			try {
				Thread.sleep(LIST_IMG_TO_PROCESS_THREAD_YELD_TIME);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private static Comparator<ImageContainerText> IMAGE_CONTAINER_COMPARATOR = new Comparator<ImageContainerText>() {

		@Override
		public int compare(ImageContainerText o1, ImageContainerText o2) {
			return -(o1.getWidth() * o1.getHeight()) + (o2.getWidth() * o2.getHeight());
		}};
	public static void createImageToTextPreparer() {
		
		for (int i = 0; i < IMAGE_TO_PROCESS_LIST.length; ++i) {
			final int pos = i;
			new Thread() {
				@Override
				public void run() {
					prepareImageToProcess(IMAGE_TO_PROCESS_LIST[pos]);
					jobFinish[pos] = true;
				}
			}.start();
			
		}
	}
	
	public static void newThreadJob() {
		jobFinish = new boolean[LIST_IMG_TO_PROCESS_COUNT];
	}
	
	public static boolean isConverImageToBufferFinish() {
		boolean result = true;
		for (boolean b: jobFinish) {
			result = result && b;
		}
		if (result)
			Logger.log("All Textures are loaded.");
		return result;
	}

	@Override
	public ImageContainer getImageContainer(Object imageData) {
		return getImageContainer(imageData, 0);
	}


	
	private LwjgGameWindow gameWindow = new LwjgGameWindow();

	@Override
	public GameWindow getInstanceOfGameWindow() {
		return gameWindow;
	}


	private float alpha = 1;
	@Override
	public float getAlpha() {
		// TODO Auto-generated method stub
		return alpha;
	}
	@Override
	public ImageContainer getImageContainer(Object imageData, int colors) {
		RawPCXImage pcx = (RawPCXImage) imageData;
		
		PCXHeader header = null;

    	byte[] data = pcx.getData();
        
        try {
			header = new PCXHeader(data);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        int width = header.getWidth();
        int height = header.getHeight();
        
        ImageContainerText result = new ImageContainerText(colors, pcx , width, height);
        addToImageToProcess(result);
		return result;
			
			
	}
	@Override
	public void setAlpha(float a) {
		alpha = a;		
	}
	@Override
	public void setClip(Rectangle r) {
		if (r != null) {
			r = (Rectangle) r.clone();
			final int xl = r.getX1() * 2;
			final int yt = r.getY1() * 2;
			final int xr = r.getX2() * 2;
			final int yb = r.getY2() * 2;

			GL11.glMatrixMode(GL11.GL_PROJECTION);
			GL11.glLoadIdentity();
			GL11.glOrtho(0, xr - xl, (yb - yt), 0, -10000, 10000);
			GL11.glViewport(xl, 480 - yb, xr - xl, (yb - yt));
			//			
			GL11.glScaled(2f, 2f, 0);

		} else {
			GL11.glMatrixMode(GL11.GL_PROJECTION);
			GL11.glLoadIdentity();
			GL11.glViewport(0, 0, 640, 480);
			GL11.glOrtho(0, 640, 640, 0, -10000, 10000);
			GL11.glScaled((float) 640 / 320, (float) 640 / 240, 0);
		}

	}	
	
	
   
    

}
