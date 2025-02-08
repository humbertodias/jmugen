package org.lee.mugen.renderer.jogl;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferDouble;
import java.awt.image.DataBufferFloat;
import java.awt.image.DataBufferInt;
import java.awt.image.DataBufferShort;
import java.awt.image.DataBufferUShort;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.media.opengl.GL;

import org.lee.mugen.imageIO.PCXLoader;
import org.lee.mugen.imageIO.RawPCXImage;
import org.lee.mugen.imageIO.PCXLoader.PCXHeader;
import org.lee.mugen.object.RawImage;
import org.lee.mugen.object.Rectangle;
import org.lee.mugen.renderer.AngleDrawProperties;
import org.lee.mugen.renderer.DrawProperties;
import org.lee.mugen.renderer.GameWindow;
import org.lee.mugen.renderer.GraphicsWrapper;
import org.lee.mugen.renderer.ImageContainer;
import org.lee.mugen.renderer.MugenDrawer;
import org.lee.mugen.renderer.RGB;
import org.lee.mugen.renderer.Trans;
import org.lee.mugen.renderer.jogl.shader.AfterImageShader;
import org.lee.mugen.renderer.jogl.shader.BlendShader;
import org.lee.mugen.renderer.jogl.shader.PalFxShader;
import org.lee.mugen.util.Logger;

import com.sun.opengl.util.j2d.TextureRenderer;
import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureCoords;
import com.sun.opengl.util.texture.TextureData;
import com.sun.opengl.util.texture.TextureIO;

public class JoglMugenDrawer extends MugenDrawer {

	private final JoglGameWindow gameWindow = new JoglGameWindow();

	//
	private final RGB rgba = new RGB();

	private static PalFxShader palFxShader;
	private static AfterImageShader afterImageShader;
	private static final Map<String, BlendShader> blendShaderMap = new HashMap<String, BlendShader>();

	private BlendShader getBlendShader(String name) {
		BlendShader blendShader = blendShaderMap.get(name);
		if (blendShader == null) {
			blendShader = new BlendShader(name);
			blendShaderMap.put(name, blendShader);
			blendShader.compileShader(gameWindow.getGl());
		}
		return blendShader;
	}

	public PalFxShader getPalFxShader() {
		if (palFxShader == null) {
			palFxShader = new PalFxShader();
			palFxShader.compileShader(gameWindow.getGl());
		}
		return palFxShader;
	}

	public AfterImageShader getAfterImageShader() {
		if (afterImageShader == null) {
			afterImageShader = new AfterImageShader();
			afterImageShader.compileShader(gameWindow.getGl());
		}
		return afterImageShader;
	}

	private boolean isScaleByForMeDebug;

	public void setScaleByForMeDebug(boolean isScaleByForMeDebug) {
		this.isScaleByForMeDebug = isScaleByForMeDebug;
	}

	public boolean isScaleByForMeDebug() {
		return isScaleByForMeDebug;
	}

	private void drawImage(float xlDst, float xrDst, float ytDst, float ybDst,
			float xlSrc, float xrSrc, float ytSrc, float ybSrc,
			DrawProperties dp) {
		GL gl = getGl();
		if (gl == null)
			return;
		float xScale = 1f;
		float yScale = 1f;

		if (dp.getAngleDrawProperties() != null) {
			xScale = dp.getAngleDrawProperties().getXScale();
			yScale = dp.getAngleDrawProperties().getYScale();
		}
		// draw a quad textured to match the sprite
		Texture texture = (Texture) dp.getIc().getImg();
		TextureCoords coords = texture.getImageTexCoords();
		gl.glBegin(GL.GL_QUADS);
		{

			gl.glNormal3f(0.0f, 0.0f, 1.0f);
			// Left Bottom
			// gl.glTexCoord2f(coords.left(), coords.top());
			gl.glTexCoord2f(xlSrc / texture.getWidth(), ytSrc
					/ texture.getHeight());
			// gl.glVertex2f(0, 0);
			gl.glVertex2f(xlDst, ytDst);

			// Left Top
			// gl.glTexCoord2f(coords.left(), coords.bottom());
			gl.glTexCoord2f(xlSrc / texture.getWidth(), ybSrc
					/ texture.getHeight());
			gl.glVertex2f(xlDst, (ybDst - ytDst) * dp.getYScaleFactor()
					* yScale + ytDst);

			// Right Top
			// gl.glTexCoord2f(coords.right(), coords.bottom());
			gl.glTexCoord2f(xrSrc / texture.getWidth(), ybSrc
					/ texture.getHeight());
			gl.glVertex2f((xrDst - xlDst) * dp.getXScaleFactor() * xScale
					+ xlDst, (ybDst - ytDst) * dp.getYScaleFactor() * yScale
					+ ytDst);

			// Right Bottom
			// gl.glTexCoord2f(coords.right(), coords.top());
			gl.glTexCoord2f(xrSrc / texture.getWidth(), ytSrc
					/ texture.getHeight());
			gl.glVertex2f((xrDst - xlDst) * dp.getXScaleFactor() * xScale
					+ xlDst, ytDst);
		}
		gl.glEnd();
	}

	private void drawWithPropertiesColor(DrawProperties dp) {
		final GL gl = getGl();
		if (gl == null)
			return;

		final Texture texture = (Texture) dp.getIc().getImg();
		final float xlDst = dp.getXLeftDst();
		final float xrDst = dp.getXRightDst();
		final float ytDst = dp.getYTopDst();
		final float ybDst = dp.getYBottomDst();

		final float xlSrc = (dp.getXLeftSrc() / texture.getImageWidth())
				* texture.getWidth();
		final float xrSrc = (dp.getXRightSrc() / texture.getImageWidth())
				* texture.getWidth();

		final float ytSrc = (dp.getYTopSrc() / texture.getImageHeight())
				* texture.getHeight();
		final float ybSrc = (dp.getYBottomSrc() / texture.getImageHeight())
				* texture.getHeight();

		final ImageContainer ic = dp.getIc();
		float type = 0;
		Trans trans = dp.getTrans();
		if (dp.getImageProperties() != null) {
			// Afterimage
			trans = dp.getImageProperties().getTrans();
		}

		if (trans == Trans.ADD || trans == Trans.ADDALPHA) {
			gl.glTexEnvf(GL.GL_TEXTURE_ENV, GL.GL_TEXTURE_ENV_MODE,
					GL.GL_MODULATE);
			// gl.glTexEnvf(GL.GL_TEXTURE_ENV, GL.GL_COMBINE_RGB, GL.GL_ADD);
			// gl.glTexEnvf(GL.GL_TEXTURE_ENV, GL.GL_COMBINE_ALPHA, GL.GL_ADD);
			// gl.glTexEnvf(GL.GL_TEXTURE_ENV, GL.GL_SRC0_RGB, GL.GL_PREVIOUS);
			// gl.glTexEnvf(GL.GL_TEXTURE_ENV, GL.GL_SRC1_RGB, GL.GL_TEXTURE);
			gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_DST_ALPHA);
			type = 1;
		} else if (trans == Trans.ADDALPHA) {
			gl.glTexEnvf(GL.GL_TEXTURE_ENV, GL.GL_TEXTURE_ENV_MODE,
					GL.GL_ADD);
			// gl.glTexEnvf(GL.GL_TEXTURE_ENV, GL.GL_COMBINE_RGB, GL.GL_ADD);
			// gl.glTexEnvf(GL.GL_TEXTURE_ENV, GL.GL_COMBINE_ALPHA, GL.GL_ADD);
			// gl.glTexEnvf(GL.GL_TEXTURE_ENV, GL.GL_SRC0_RGB, GL.GL_PREVIOUS);
			// gl.glTexEnvf(GL.GL_TEXTURE_ENV, GL.GL_SRC1_RGB, GL.GL_TEXTURE);
			gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_DST_ALPHA);
			type = 1;
		} else if (trans == Trans.ADD1) {
			gl.glTexEnvf(GL.GL_TEXTURE_ENV, GL.GL_TEXTURE_ENV_MODE,
					GL.GL_MODULATE);
			// gl.glTexEnvf(GL.GL_TEXTURE_ENV, GL.GL_COMBINE_RGB, GL.GL_ADD);
			// gl.glTexEnvf(GL.GL_TEXTURE_ENV, GL.GL_COMBINE_ALPHA, GL.GL_ADD);
			// gl.glTexEnvf(GL.GL_TEXTURE_ENV, GL.GL_SRC0_RGB, GL.GL_PREVIOUS);
			// gl.glTexEnvf(GL.GL_TEXTURE_ENV, GL.GL_SRC1_RGB, GL.GL_TEXTURE);
			gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_DST_ALPHA);
			type = 2;
		} else if (trans == Trans.SUB) {
			gl.glTexEnvf(GL.GL_TEXTURE_ENV, GL.GL_TEXTURE_ENV_MODE, GL.GL_COMBINE);
			gl.glTexEnvf(GL.GL_TEXTURE_ENV, GL.GL_COMBINE_RGB, GL.GL_SUBTRACT);
			gl.glTexEnvf(GL.GL_TEXTURE_ENV, GL.GL_COMBINE_ALPHA, GL.GL_SUBTRACT);
			gl.glTexEnvf(GL.GL_TEXTURE_ENV, GL.GL_SRC0_RGB, GL.GL_PREVIOUS);
			gl.glTexEnvf(GL.GL_TEXTURE_ENV, GL.GL_SRC1_RGB, GL.GL_TEXTURE);
			gl.glBlendFunc(GL.GL_DST_COLOR, GL.GL_SRC_ALPHA);
			drawImage(xlDst, xrDst, ytDst, ybDst, xlSrc, xrSrc, ytSrc, ybSrc, dp);
			drawImage(xlDst, xrDst, ytDst, ybDst, xlSrc, xrSrc, ytSrc, ybSrc, dp);
			drawImage(xlDst, xrDst, ytDst, ybDst, xlSrc, xrSrc, ytSrc, ybSrc, dp);

			type = 3;
		} else {
			gl.glTexEnvi(GL.GL_TEXTURE_ENV, GL.GL_TEXTURE_ENV_MODE, GL.GL_REPLACE);
			gl.glBlendFunc(GL.GL_ONE, GL.GL_ONE_MINUS_SRC_ALPHA);

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
			getPalFxShader().render(gl, dp.getPalfx().getAdd().mul(bits),
					dp.getPalfx().getMul().mul(bits), ampl.mul(bits),
					dp.getAlpha());
			drawImage(xlDst, xrDst, ytDst, ybDst, xlSrc, xrSrc, ytSrc, ybSrc,
					dp);
			getPalFxShader().endRender(gl);

		} else if (dp.getImageProperties() != null) {
			final RGB bits = new RGB(1f / 255f, 1f / 255f, 1f / 255f, 1f);
			
			getAfterImageShader().render(gl,
					dp.getImageProperties().getPalbright().mul(bits),
					dp.getImageProperties().getPalcontrast().mul(bits),
					dp.getImageProperties().getPalpostbright().mul(bits),
					dp.getImageProperties().getPaladd().mul(bits),
					dp.getImageProperties().getPalmul(), type, 1f);
			drawImage(xlDst, xrDst, ytDst, ybDst, xlSrc, xrSrc, ytSrc, ybSrc, dp);
			getAfterImageShader().endRender(gl);
		} else {
			drawImage(xlDst, xrDst, ytDst, ybDst, xlSrc, xrSrc, ytSrc, ybSrc, dp);
		}

		gl.glDisable(GL.GL_ALPHA_TEST);
	}

	private void processRotationProperties(AngleDrawProperties dp) {
		final GL gl = getGl();
		if (gl == null)
			return;
		if (dp != null) {
			gl.glTranslatef(dp.getXAnchor(), dp.getYAnchor(), 0);
			gl.glRotatef(dp.getAngleset(), 0, 0, 1);
			gl.glTranslatef(-dp.getXAnchor(), -dp.getYAnchor(), 0);
		}
	}

	@Override
	public GameWindow getInstanceOfGameWindow() {
		return gameWindow;
	}

	private GL getGl() {
		return gameWindow.getGl();
	}

	TextureRenderer animRenderer = null;
	final JoglTextureDrawer joglTextureDrawer = new JoglTextureDrawer();

	public JoglTextureDrawer getJoglTextureDrawer() {
		return joglTextureDrawer;
	}
	private final List<DrawProperties> oldDp = new LinkedList<DrawProperties>();

	// need for movie
	public void drawBufferedImage(DrawProperties dp) {
		GL gl = getGl();
		if (gl == null)
			return;
		Graphics2D g = ((JoglMugenDrawer) GraphicsWrapper.getInstance())
				.getJoglTextureDrawer().getBackBuffer().createGraphics();
		g.setBackground(new Color(0, 0, 0, 0));
		g.clearRect(0, 0, 640, 480);

		joglTextureDrawer.draw(dp);

		for (DrawProperties d : oldDp) {
			((JoglMugenDrawer) GraphicsWrapper.getInstance())
					.getJoglTextureDrawer().getBackBuffer().markDirty(
							(int) (d.getXLeftDst()),
							(int) (d.getYTopDst()),
							(int) Math.abs(d.getXRightDst()
									- d.getXLeftDst()),
							(int) Math.abs(d.getYTopDst()
									- d.getYBottomDst()));
		}
		oldDp.clear();
		oldDp.add(dp);
		((JoglMugenDrawer) GraphicsWrapper.getInstance())
				.getJoglTextureDrawer().getBackBuffer().markDirty(
						(int) (dp.getXLeftDst()),
						(int) (dp.getYTopDst()),
						(int) Math
								.abs(dp.getXRightDst() - dp.getXLeftDst()),
						(int) Math
								.abs(dp.getYTopDst() - dp.getYBottomDst()));

		final TextureRenderer textRender = ((JoglMugenDrawer) GraphicsWrapper
				.getInstance()).getJoglTextureDrawer().getBackBuffer();

		Texture tex = textRender.getTexture();
		TextureCoords tc = tex.getImageTexCoords();
		final float tx1 = tc.left();
		final float ty1 = tc.top();
		final float tx2 = tc.right();
		final float ty2 = tc.bottom();

		// Enable blending, using the SrcOver rule
		gl.glEnable(GL.GL_BLEND);
		gl.glBlendFunc(GL.GL_ONE, GL.GL_ONE_MINUS_SRC_ALPHA);

		// Use the GL_MODULATE texture function to effectively multiply
		// each pixel in the texture by the current alpha value
		gl.glTexEnvi(GL.GL_TEXTURE_ENV, GL.GL_TEXTURE_ENV_MODE,
				GL.GL_MODULATE);

		final float x = 0;
		final float y = 0;
		final float w = 640;
		final float h = 480;

		tex.bind();
		tex.enable();
		gl.glBegin(GL.GL_QUADS);
		// Render image right-side up
		final float a = 1f;
		gl.glColor4f(a, a, a, a);
		gl.glTexCoord2f(tx1, ty2);
		gl.glVertex3f(x, y + h, 0f);
		gl.glTexCoord2f(tx2, ty2);
		gl.glVertex3f(x + w, y + h, 0f);
		gl.glTexCoord2f(tx2, ty1);
		gl.glVertex3f(x + w, y, 0f);
		gl.glTexCoord2f(tx1, ty1);
		gl.glVertex3f(x, y, 0f);
		gl.glEnd();
	
	}
		
	
	@Override
	public void draw(DrawProperties dp) {
		GL gl = getGl();
		if (gl == null)
			return;
		if (dp.getIc().getImg() instanceof BufferedImage) {
			drawBufferedImage(dp);
			return;
		}
		final Texture texture = (Texture) dp.getIc().getImg();

		// store the current model matrix
		gl.glPushMatrix();

		// bind to the appropriate texture for this sprite

		texture.bind();

		gl.glEnable(GL.GL_TEXTURE_2D);

		gl.glColorMask(true, true, true, true);
		gl.glDisable(GL.GL_COLOR_LOGIC_OP);
		gl.glDisable(GL.GL_BLEND);
		// gl.glDisable(GL.GL_ALPHA_TEST);

		gl.glTexEnvi(GL.GL_TEXTURE_ENV, GL.GL_TEXTURE_ENV_MODE, GL.GL_MODULATE);
		// gl.glTexEnvi(GL.GL_TEXTURE_ENV, GL.GL_TEXTURE_ENV_MODE,
		// GL.GL_REPLACE);

		gl.glEnable(GL.GL_BLEND);
		gl.glBlendFunc(GL.GL_SRC_COLOR, GL.GL_ONE_MINUS_SRC_ALPHA);
		gl.glColor4f(1f, 1f, 1f, 1f);

		if (isScaleByForMeDebug()) {
			scale(0.5f, 0.5f); // This scale help me to see out of screen
			gl.glTranslated(160, 240, 0);
		}

		//		
		// float coef = 1.5f;
		// scale(coef, coef);
		// gl.glTranslated(0, -120*(coef-1), 0);
		processRotationProperties(dp.getAngleDrawProperties());

		drawWithPropertiesColor(dp);
		gl.glPopMatrix();
		// texture.disable();

	}

	@Override
	public void drawLine(int x1, int y1, int x2, int y2) {
		final GL gl = getGl();
		if (gl == null)
			return;
		if (isScaleByForMeDebug()) {
			scale(0.5f, 0.5f); // This scale help me to see out of screen
			gl.glTranslated(160, 240, 0);
		}
		gl.glDisable(GL.GL_TEXTURE_2D);
		gl.glColor4f(rgba.getR(), rgba.getG(), rgba.getB(), rgba.getA());
		gl.glBegin(GL.GL_POINTS);
		gl.glVertex2f(x1, y1);
		gl.glVertex2f(x2, y2);
		gl.glEnd();
		gl.glBegin(GL.GL_LINES);
		gl.glVertex2f(x1, y1);
		gl.glVertex2f(x2, y2);
		gl.glEnd();
		gl.glEnable(GL.GL_TEXTURE_2D);

		if (isScaleByForMeDebug()) {
			gl.glTranslated(-160, -240, 0);
			scale(2f, 2f);
		}
	}

	@Override
	public void drawRect(float x, float y, float width, float height) {
		final GL gl = getGl();
		if (gl == null)
			return;
		gl.glColor4f(rgba.getR(), rgba.getG(), rgba.getB(), rgba.getA());
		gl.glDisable(GL.GL_TEXTURE_2D);
		gl.glBegin(GL.GL_LINE_STRIP);
		gl.glVertex2f(x, y);
		gl.glVertex2f(x + width, y);
		gl.glVertex2f(x + width, y + height);
		gl.glVertex2f(x, y + height);
		gl.glVertex2f(x, y);
		gl.glEnd();
		gl.glEnable(GL.GL_TEXTURE_2D);
	}

	@Override
	public void fillRect(float x1, float y1, float width, float height) {
		final GL gl = getGl();
		if (gl == null)
			return;
		gl.glTexEnvi(GL.GL_TEXTURE_ENV, GL.GL_TEXTURE_ENV_MODE, GL.GL_REPLACE);
		gl.glBlendFunc(GL.GL_ONE, GL.GL_ONE_MINUS_SRC_ALPHA);

		gl.glDisable(GL.GL_TEXTURE_2D);
		gl.glColor4f(rgba.getR(), rgba.getG(), rgba.getB(), rgba.getA());

		gl.glBegin(GL.GL_QUADS);
		gl.glVertex2f(x1, y1);
		gl.glVertex2f(x1 + width, y1);
		gl.glVertex2f(x1 + width, y1 + height);
		gl.glVertex2f(x1, y1 + height);
		gl.glEnd();
		gl.glEnable(GL.GL_TEXTURE_2D);
	}

	@Override
	public void scale(float x, float y) {
		final GL gl = getGl();
		if (gl == null)
			return;
		gl.glScaled(x, y, 0);
	}

	@Override
	public void setColor(float r, float g, float b, float a) {
		final GL gl = getGl();
		if (gl == null)
			return;
		gl.glColor4f(r / 255f, g / 255f, b / 255f, a / 255f);
		rgba.setA(a);
		rgba.setR(r);
		rgba.setG(g);
		rgba.setB(b);
		

	}

	@Override
	public void setColor(float r, float g, float b) {
		final GL gl = getGl();
		if (gl == null)
			return;
		gl.glColor3f(r / 255f, g / 255f, b / 255f);
		rgba.setA(1f);
		rgba.setR(r);
		rgba.setG(g);
		rgba.setB(b);

	}

	public class ImageContainerText extends ImageContainer {
		private final int color;

		public ImageContainerText(int color, Object img, int width, int height) {
			super(img, width, height);
			this.color = color;
		}

		public ImageContainerText(Object img, int width, int height) {
			this(0, img, width, height);
		}

		public ImageContainerText(RawImage ri) {
			this(0, ri, ri.getWidth(), ri.getHeight());
		}

		private static final int RAW_PCX = 0;
		private static final int DATA = 1;
		private static final int TEXTURE = 2;

		private final AtomicInteger imageStatus = new AtomicInteger(0);

		@Override
		public Object getImg() {

			synchronized (this) {
				if (img instanceof RawImage) {
					RawImage ri = (RawImage) img;
					img = getDefaultTextureData(IntBuffer.wrap(ri.getData()), ri.getWidth(), ri.getHeight());
					imageStatus.set(DATA);
				}
				TextureData data = null;
				switch (imageStatus.get()) {
				case TEXTURE:
					return img;
				case RAW_PCX:
					BufferedImage image = getDefaultImageType((RawPCXImage) img, color);
					data = getDefaultTextureData(image);
				case DATA:
					if (data == null) {
						data = (TextureData) img;
					}
					imageStatus.set(TEXTURE);
					return img = getInitializedTexture(data);
				default:
					throw new IllegalStateException();
				}
			}
		}

		public void free() {
			synchronized (this) {
				if (imageStatus.get() == TEXTURE && img != null)
					((Texture) img).dispose();
			}
		}
		
		@Override
		public void reload(ImageContainer img) {
			synchronized (this) {
				final ImageContainerText imgText = (ImageContainerText) img;
				this.img = imgText.img;
				this.width = img.getWidth();
				this.height = img.getHeight();
				imageStatus.set(imgText.imageStatus.get());
			}
		}
		public void prepareImageToTexture() {
			synchronized (this) {
				if (img instanceof RawImage) {
					RawImage ri = (RawImage) img;
					img = getDefaultTextureData(IntBuffer.wrap(ri.getData()), ri.getWidth(), ri.getHeight());
					imageStatus.set(DATA);
				}
				if (imageStatus.get() == RAW_PCX) {
					final RawPCXImage pcx = (RawPCXImage) img;
					img = getInitializedTexture(pcx, color);
					imageStatus.set(TEXTURE);
				}
			}
		}
	}

	@Override
	public ImageContainer getImageContainer(Object imageData) {
		return getImageContainer(imageData, 0);
	}

	@Override
	public ImageContainer getImageContainer(Object imageData, int colors) {
		if (imageData instanceof RawImage) {
			RawImage ri = (RawImage) imageData;
			final ImageContainerText result = new ImageContainerText(ri);
			return result;
		} else if (imageData instanceof RawPCXImage) {
			final RawPCXImage pcx = (RawPCXImage) imageData;
			PCXHeader header = null;
			final byte[] data = pcx.getData();

			try {
				header = new PCXHeader(data);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			final int width = header.getWidth();
			final int height = header.getHeight();

			final ImageContainerText result = new ImageContainerText(colors, pcx, width, height);
			addToImageToProcess(result);
			return result;
		}
		throw new IllegalArgumentException();

	}
	@Override
	public void setClip(Rectangle r) {
		final GL gl = getGl();
		if (gl == null)
			return;
		if (r != null) {
			r = (Rectangle) r.clone();
			final int xl = r.getX1() * 2;
			final int yt = r.getY1() * 2;
			final int xr = r.getX2() * 2;
			final int yb = r.getY2() * 2;

			gl.glMatrixMode(GL.GL_PROJECTION);
			gl.glLoadIdentity();
			gl.glOrtho(0, xr - xl, (yb - yt), 0, -10000, 10000);
			gl.glViewport(xl, 480 - yb, xr - xl, (yb - yt));
			//			
			gl.glScaled(2f, 2f, 0);

		} else {
			gl.glMatrixMode(GL.GL_PROJECTION);
			gl.glLoadIdentity();
			gl.glViewport(0, 0, 640, 480);
			gl.glOrtho(0, 640, 640, 0, -10000, 10000);
			gl.glScaled((float) 640 / 320, (float) 640 / 240, 0);
		}

	}
	private static BufferedImage getDefaultImageType(RawPCXImage pcx) {
		return getDefaultImageType(pcx, 0);
	}
	private static BufferedImage getDefaultImageType(int width, int height) {
		return new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	}
	private static BufferedImage getDefaultImageType(RawPCXImage pcx, int color) {
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
	private static Buffer wrapImageDataBuffer(DataBuffer data) {
		if (data instanceof DataBufferByte) {
			return ByteBuffer.wrap(((DataBufferByte) data).getData());
		} else if (data instanceof DataBufferDouble) {
			throw new RuntimeException(
					"DataBufferDouble rasters not supported by OpenGL");
		} else if (data instanceof DataBufferFloat) {
			return FloatBuffer.wrap(((DataBufferFloat) data).getData());
		} else if (data instanceof DataBufferInt) {
			return IntBuffer.wrap(((DataBufferInt) data).getData());
		} else if (data instanceof DataBufferShort) {
			return ShortBuffer.wrap(((DataBufferShort) data).getData());
		} else if (data instanceof DataBufferUShort) {
			return ShortBuffer.wrap(((DataBufferUShort) data).getData());
		} else {
			throw new RuntimeException("Unexpected DataBuffer type?");
		}
	}
	private static TextureData getDefaultTextureData(BufferedImage image) {
		final Buffer buffer = wrapImageDataBuffer(image.getRaster().getDataBuffer());
		return getDefaultTextureData(buffer, image.getWidth(), image.getHeight());
	}
	private static TextureData getDefaultTextureData(Buffer buffer, int width, int height) {
		final TextureData data;
		if (width * height < 200 * 100) {
			data = 
				new TextureData(GL.GL_RGBA,//GL.GL_COMPRESSED_RGBA_S3TC_DXT3_EXT,
					width,
					height,
	                0,
	                GL.GL_BGRA,
	                GL.GL_UNSIGNED_BYTE,
	                false,
	                false,
	                false,
	                buffer,
	                null);
		} else {
			data = 
				new TextureData(GL.GL_COMPRESSED_RGBA_S3TC_DXT3_EXT,
					width,
					height,
	                0,
	                GL.GL_BGRA,
	                GL.GL_UNSIGNED_BYTE,
	                false,
	                false,
	                false,
	                buffer,
	                null);
		}
		return data;
	}
	private static Texture getNewEmptyTexture(int width, int height) {
		final TextureData data = getDefaultTextureData(getDefaultImageType(width, height));
		final Texture texture = getInitializedTexture(data);
		data.setBuffer(null);
		return texture;
	}
	private static Texture getInitializedTexture(BufferedImage image) {
		final TextureData data = getDefaultTextureData(image);
		final Texture texture = getInitializedTexture(data);
		data.setBuffer(null);
		return texture;
	}
	private static Texture getInitializedTexture(RawPCXImage pcx) {
		return getInitializedTexture(pcx, 0);
	}
	private static Texture getInitializedTexture(RawPCXImage pcx, int color) {
		final BufferedImage image = getDefaultImageType(pcx, color);
		final TextureData data = getDefaultTextureData(image);
		return getInitializedTexture(data);
	}
	private static Texture getInitializedTexture(TextureData data) {
		final Texture texture = TextureIO.newTexture(data);
		data.setBuffer(null);
		texture.setTexParameteri(GL.GL_TEXTURE_WRAP_S, GL.GL_CLAMP_TO_EDGE);
		texture.setTexParameteri(GL.GL_TEXTURE_WRAP_T, GL.GL_CLAMP_TO_EDGE);
		texture.setTexParameteri(GL.GL_TEXTURE_MAG_FILTER, GL.GL_NEAREST);
		texture.setTexParameteri(GL.GL_TEXTURE_MIN_FILTER, GL.GL_NEAREST);
		return texture;
	}
	// ////////////////////
	private static final int LIST_IMG_TO_PROCESS_COUNT = 0;
	private static final int LIST_IMG_TO_PROCESS_THREAD_YELD_TIME = 0;
	private static List<ImageContainerText>[] IMAGE_TO_PROCESS_LIST = null;
	private static boolean[] jobFinish = new boolean[LIST_IMG_TO_PROCESS_COUNT];
	private static int currentListToAdd = 0;

	private static void addToImageToProcess(ImageContainerText img) {
		if (IMAGE_TO_PROCESS_LIST == null) {
			IMAGE_TO_PROCESS_LIST = new List[LIST_IMG_TO_PROCESS_COUNT];
			for (int i = 0; i < LIST_IMG_TO_PROCESS_COUNT; i++) {
				IMAGE_TO_PROCESS_LIST[i] = new LinkedList<ImageContainerText>();
			}
		}
		if (LIST_IMG_TO_PROCESS_COUNT <= 0)
			return;
		if (currentListToAdd > IMAGE_TO_PROCESS_LIST.length - 1) {
			currentListToAdd = 0;
		}
		IMAGE_TO_PROCESS_LIST[currentListToAdd].add(img);
		currentListToAdd++;
	}

	private static void prepareImageToProcess(final List<ImageContainerText> list) {
		Collections.sort(list, IMAGE_CONTAINER_COMPARATOR);
		final LinkedList<ImageContainerText> alist = new LinkedList<ImageContainerText>();
		alist.addAll(list);
		list.removeAll(alist);
//		 if (list.size() > 0) {
//			ImageContainerText c = list.iterator().next();
//			c.prepareImageToTexture();
//			list.remove(c);
//			System.out.println("rest " + list.size() + " to load");
//		}
		for (Iterator<ImageContainerText> iter = alist.iterator(); iter
				.hasNext();) {
			iter.next().prepareImageToTexture();
			if (LIST_IMG_TO_PROCESS_THREAD_YELD_TIME > 0) {
				try {
					Thread.sleep(LIST_IMG_TO_PROCESS_THREAD_YELD_TIME);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

	}

	private static Comparator<ImageContainerText> IMAGE_CONTAINER_COMPARATOR = new Comparator<ImageContainerText>() {
		@Override
		public int compare(ImageContainerText o1, ImageContainerText o2) {
			return -(o1.getWidth() * o1.getHeight())
					+ (o2.getWidth() * o2.getHeight());
		}
	};

	public static void createImageToTextPreparer() {
		for (int i = 0; IMAGE_TO_PROCESS_LIST != null
				&& i < IMAGE_TO_PROCESS_LIST.length; ++i) {
			final int pos = i;
			new Thread() {
				@Override
				public void run() {
					prepareImageToProcess(IMAGE_TO_PROCESS_LIST[pos]);
					jobFinish[pos] = true;
				}
			}.run();
		}
	}

	public static void newThreadJob() {
		jobFinish = new boolean[LIST_IMG_TO_PROCESS_COUNT];
	}

	public static boolean isConverImageToBufferFinish() {
		boolean result = true;
		for (boolean b : jobFinish) {
			result = result && b;
		}
		if (result)
			Logger.log("No More Texture To Load");
		return result;
	}
	float alpha = 1f;
	@Override
	public float getAlpha() {
		return alpha;
	}


	@Override
	public void setAlpha(float a) {
		this.alpha = a;
		getJoglTextureDrawer().setAlpha(a);
	}
}
