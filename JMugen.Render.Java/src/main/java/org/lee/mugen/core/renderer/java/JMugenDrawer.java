package org.lee.mugen.core.renderer.java;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.lee.mugen.imageIO.PCXLoader;
import org.lee.mugen.imageIO.RawPCXImage;
import org.lee.mugen.imageIO.PCXLoader.PCXHeader;
import org.lee.mugen.object.Rectangle;
import org.lee.mugen.renderer.AngleDrawProperties;
import org.lee.mugen.renderer.DrawProperties;
import org.lee.mugen.renderer.GameWindow;
import org.lee.mugen.renderer.ImageContainer;
import org.lee.mugen.renderer.MugenDrawer;
import org.lee.mugen.renderer.Trans;

import composite.BlendComposite;

public class JMugenDrawer extends MugenDrawer {

	private float alpha = 1.0f;
	private float xScale = 1.0f;
	private float yScale = 1.0f;
	private Color c = Color.BLACK;
	private JGameWindow gameWindow;

	private void processRotationProperties(AngleDrawProperties dp) {
		if (dp != null) {
			JGameWindow window = (JGameWindow) getInstanceOfGameWindow();
			Graphics2D g = window.getDrawGraphics();
			AffineTransform rot = AffineTransform.getRotateInstance(dp.getAngleset(), dp.getXAnchor(), dp.getYAnchor());
			g.setTransform(rot);
		}
	}

	@Override
	public void draw(DrawProperties dp) {
		JGameWindow window = (JGameWindow) getInstanceOfGameWindow();
		Graphics2D g =  window.getDrawGraphics();
		g.scale(xScale, yScale);
		BufferedImage img = (BufferedImage) dp.getIc().getImg();

		if (img == null) {
			return; // If the image is null, there is no point in continuing the drawing.
		}

		g.setClip((int) dp.getXLeftDst(), (int) dp.getYTopDst(), img.getWidth(), img.getHeight());

		Composite composite = getComposite(dp.getTrans());
		if (composite != null) {
			g.setComposite(composite);
		} else if (alpha != 1) {
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC, alpha));
		}

		processRotationProperties(dp.getAngleDrawProperties());

		if (null != dp.getPalfx()) {
//			palfxFilter.setFxSub(dp.getPalfx());
//			img = palfxFilter.filter(img, null);
		}

		float localXScale = 1f;
		float localYScale = 1f;

		if (dp.getAngleDrawProperties() != null) {
			localXScale = dp.getAngleDrawProperties().getXScale();
			localYScale = dp.getAngleDrawProperties().getYScale();
		}

		g.drawImage(img,
				(int) dp.getXLeftDst(),
				(int) dp.getYTopDst(),
				(int) ((dp.getXRightDst() - dp.getXLeftDst()) * dp.getXScaleFactor() * localXScale + dp.getXLeftDst()),
				(int) ((dp.getYBottomDst() - dp.getYTopDst()) * dp.getYScaleFactor() * localYScale + dp.getYTopDst()),
				(int) dp.getXLeftSrc(),
				(int) dp.getYTopSrc(),
				(int) dp.getXRightSrc(),
				(int) dp.getYBottomSrc(), null);
	}

	private Composite getComposite(Trans trans) {
		if (trans == null) return null;

		switch (trans) {
			case ADD:
			case ADD1:
				return (alpha == 1) ? BlendComposite.Add : BlendComposite.Add.derive(alpha);
			case SUB:
				return BlendComposite.Subtract.derive(0.5f * alpha);
			default:
				return null;
		}
	}

	@Override
	public void drawLine(int x1, int y1, int x2, int y2) {
		JGameWindow window = (JGameWindow) getInstanceOfGameWindow();
		Graphics2D g = window.getDrawGraphics();
		g.setColor(c);
		g.drawLine(x1, y1, x2, y2);
	}

	@Override
	public void drawRect(float x, float y, float width, float height) {
		JGameWindow window = (JGameWindow) getInstanceOfGameWindow();
		Graphics2D g = window.getDrawGraphics();
		g.setColor(c);
		g.drawRect((int) x, (int) y, (int) width, (int) height);
	}

	@Override
	public void fillRect(float x, float y, float width, float height) {
		JGameWindow window = (JGameWindow) getInstanceOfGameWindow();
		Graphics2D g = window.getDrawGraphics();
		g.setColor(c);
		g.fillRect((int) x, (int) y, (int) width, (int) height);
	}

	@Override
	public void setColor(float r, float g, float b, float a) {
		c = new Color((int) r, (int) g, (int) b, (int) a);
	}

	@Override
	public void setColor(float r, float g, float b) {
		c = new Color((int) r, (int) g, (int) b);
	}

	@Override
	public void scale(float x, float y) {
		xScale *= x;
		yScale *= y;
	}

	@Override
	public ImageContainer getImageContainer(Object imageData) {
		RawPCXImage pcx = (RawPCXImage) imageData;
		PCXHeader header;

		try {
			header = new PCXHeader(pcx.getData());
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		int width = header.getWidth();
		int height = header.getHeight();

		return new ImageContainer(imageData, width, height) {
			@Override
			public Object getImg() {
				if (img instanceof BufferedImage) {
					return img;
				}

				try {
					img = PCXLoader.loadImageColorIndexed(new ByteArrayInputStream(pcx.getData()), pcx.getPalette(), false, true);
				} catch (IOException e) {
					e.printStackTrace();
				}
				return img;
			}
		};
	}

	@Override
	public GameWindow getInstanceOfGameWindow() {
		if (gameWindow == null) {
			gameWindow = new JGameWindow();
		}
		return gameWindow;
	}

	@Override
	public ImageContainer getImageContainer(Object imageData, int colors) {
		RawPCXImage pcx = (RawPCXImage) imageData;
		try {
			BufferedImage image = PCXLoader.loadImageColorIndexed(new ByteArrayInputStream(pcx.getData()), pcx.getPalette(), false, true, colors);
			return new ImageContainer(image, image.getWidth(), image.getHeight());
		} catch (IOException e) {
			throw new IllegalArgumentException("Failed to load image", e);
		}
	}

	@Override
	public void setClip(Rectangle r) {
		// TODO Auto-generated method stub
	}

	@Override
	public float getAlpha() {
		return alpha;
	}

	@Override
	public void setAlpha(float a) {
		this.alpha = a;
	}
}
