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
	
	private void processRotationProperties(AngleDrawProperties dp) {
		if (dp != null) {
			JGameWindow window = (JGameWindow) getInstanceOfGameWindow();
			Graphics2D g = (Graphics2D) window.getDrawGraphics();
			AffineTransform rot = AffineTransform.getRotateInstance(dp.getAngleset(), dp.getXAnchor(), dp.getYAnchor());
			g.setTransform(rot);
		}

	}
	
	
	@Override
	public void draw(DrawProperties dp) {
		JGameWindow window = (JGameWindow) getInstanceOfGameWindow();
		Graphics2D g = (Graphics2D) window.getDrawGraphics();
		g.scale(xScale, yScale);
		BufferedImage img = (BufferedImage) dp.getIc().getImg();
		g.setClip((int)dp.getXLeftDst(), (int)dp.getYTopDst(), img.getWidth(), img.getHeight());
		
		Composite composite = null;
		if (dp.getTrans() == Trans.ADD) {
			if(alpha == 1)
				composite = BlendComposite.Add;
			else
				composite = BlendComposite.Add.derive(alpha);
				
		} else if (dp.getTrans() == Trans.ADD1) {
			if(alpha == 1)
				composite = BlendComposite.Add;
			else
				composite = BlendComposite.Add.derive(alpha);

		} else if (dp.getTrans() == Trans.SUB) {
			if(alpha == 1)
				composite = BlendComposite.Subtract.derive(0.5f);
			else
				composite = BlendComposite.Subtract.derive(0.5f*alpha);

		}
		if (composite != null) {
			g.setComposite(composite);
		} else if (alpha != 1) {
			composite = AlphaComposite.getInstance(AlphaComposite.SRC, alpha);
			g.setComposite(composite);
		}
		

		
		processRotationProperties(dp.getAngleDrawProperties());
		
		if (null != dp.getPalfx()) {
//			palfxFilter.setFxSub(dp.getPalfx());
//			img = palfxFilter.filter(img, null);
		}

		float xScale = 1f;
		float yScale = 1f;
		
		if (dp.getAngleDrawProperties() != null) {
			xScale = dp.getAngleDrawProperties().getXScale();
			yScale = dp.getAngleDrawProperties().getYScale();
		}
		g.setClip((int)dp.getXLeftDst(), (int)dp.getYTopDst(), img.getWidth(), img.getHeight());
		g.drawImage(
				img, 
				(int)dp.getXLeftDst(), 
				(int)dp.getYTopDst(), 
				(int) ((dp.getXRightDst() - dp.getXLeftDst()) * dp.getXScaleFactor() * xScale + dp.getXLeftDst()), 
				(int) ((dp.getYBottomDst() - dp.getYTopDst()) * dp.getYScaleFactor() * yScale + dp.getYTopDst()), 
				(int)dp.getXLeftSrc(), 
				(int)dp.getYTopSrc(), 
				(int)dp.getXRightSrc(), 
				(int)dp.getYBottomSrc(), null);
		
		
	}
	@Override
	public void drawLine(int x1, int y1, int x2, int y2) {
		JGameWindow window = (JGameWindow) getInstanceOfGameWindow();
		Graphics2D g = (Graphics2D) window.getDrawGraphics();
		g.setColor(c);
		g.drawLine(x1, y1, x2, y2);
		
	}

	@Override
	public void drawRect(float x, float y, float width, float height) {
		JGameWindow window = (JGameWindow) getInstanceOfGameWindow();
		Graphics2D g = (Graphics2D) window.getDrawGraphics();
		g.setColor(c);
		g.drawRect((int)x, (int)y, (int)width, (int)height);
	}

	@Override
	public void fillRect(float x, float y, float width, float height) {
		JGameWindow window = (JGameWindow) getInstanceOfGameWindow();
		Graphics2D g = (Graphics2D) window.getDrawGraphics();
		g.setColor(c);
		g.fillRect((int)x, (int)y, (int)width, (int)height);
		
	}
	private Color c = Color.BLACK;
	@Override
	public void setColor(float r, float g, float b, float a) {
		JGameWindow window = (JGameWindow) getInstanceOfGameWindow();
		Graphics2D g2D = (Graphics2D) window.getDrawGraphics();
		
		g2D.setColor(new Color((int)r, (int)g, (int)b, (int)a));
		c = new Color((int)r, (int)g, (int)b, (int)a);
	}

	@Override
	public void setColor(float r, float g, float b) {
		JGameWindow window = (JGameWindow) getInstanceOfGameWindow();
		Graphics2D g2D = (Graphics2D) window.getDrawGraphics();
		
		g2D.setColor(new Color((int)r, (int)g, (int)b));
		
	}

	private float xScale = 1;
	private float yScale = 1;
	@Override
	public void scale(float x, float y) {
		xScale *= x;
		yScale *= y;
		
	}

	@Override
	public ImageContainer getImageContainer(Object imageData) {
		RawPCXImage pcx = (RawPCXImage) imageData;
		PCXHeader header = null;

		try {
			header = new PCXHeader(pcx.getData());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		int width = header.getWidth();
		int height = header.getHeight();
		
		return new ImageContainer(imageData, width, height) {
			@Override
			public Object getImg() {
				if (img instanceof BufferedImage) {
					return img;
				}
				RawPCXImage pcx = (RawPCXImage) img;
				BufferedImage image = null;
				try {
					image = (BufferedImage) PCXLoader.loadImageColorIndexed(new ByteArrayInputStream(
							pcx.getData()), pcx.getPalette(), false, true);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return img = image;
			}
		};
		
	}

	
	private JGameWindow gameWindow;
	@Override
	public GameWindow getInstanceOfGameWindow() {
		if (gameWindow == null)
			gameWindow = new JGameWindow();
		return gameWindow;
	}


	@Override
	public ImageContainer getImageContainer(Object imageData, int colors) {
		RawPCXImage pcx = (RawPCXImage) imageData;
		try {
//			BufferedImage image = (BufferedImage) PCXLoader.loadImage(new ByteArrayInputStream(
//					pcx.getData()), pcx.getPalette(), false, true);

			BufferedImage image = (BufferedImage) PCXLoader.loadImageColorIndexed(new ByteArrayInputStream(
					pcx.getData()), pcx.getPalette(), false, true, colors);

			return new ImageContainer(image, image.getWidth(), image.getHeight());
		} catch (IOException e) {
			throw new IllegalArgumentException();
		}
		
	}


	@Override
	public void setClip(Rectangle r) {
		// TODO Auto-generated method stub
		
	}

	float alpha;
	@Override
	public float getAlpha() {
		return alpha;
		
	}


	@Override
	public void setAlpha(float a) {
		this.alpha = a;
	}

}
