package org.lee.mugen.renderer.jogl;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import org.lee.mugen.renderer.AngleDrawProperties;
import org.lee.mugen.renderer.DrawProperties;
import org.lee.mugen.renderer.Trans;

import com.sun.opengl.util.j2d.TextureRenderer;
import composite.BlendComposite;

public class JoglTextureDrawer {
	private TextureRenderer backBuffer;
	private Graphics2D getGraphics() {
		return getBackBuffer().createGraphics();
	}


	
	public TextureRenderer getBackBuffer() {
		if (backBuffer == null) {
			backBuffer = new TextureRenderer(640,480,true);
			backBuffer.setSmoothing(false);
		}
		return backBuffer;
	}



	private void processRotationProperties(AngleDrawProperties dp) {
		if (dp != null) {
			Graphics2D g = getGraphics();
			AffineTransform rot = AffineTransform.getRotateInstance(dp.getAngleset(), dp.getXAnchor(), dp.getYAnchor());
			g.setTransform(rot);
		}

	}
	
	float alpha = 1;
	public float getAlpha() {
		return alpha;
	}



	public void setAlpha(float alpha) {
		this.alpha = alpha;
	}



	public void draw(DrawProperties dp) {
		Graphics2D g = (Graphics2D) getGraphics();
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
	public void drawLine(int x1, int y1, int x2, int y2) {
		Graphics2D g = getGraphics();
		g.setColor(c);
		g.drawLine(x1, y1, x2, y2);
		
	}

	public void drawRect(float x, float y, float width, float height) {
		Graphics2D g = getGraphics();
		g.setColor(c);
		g.drawRect((int)x, (int)y, (int)width, (int)height);
	}

	public void fillRect(float x, float y, float width, float height) {
		Graphics2D g = getGraphics();
		g.setColor(c);
		g.fillRect((int)x, (int)y, (int)width, (int)height);
		
	}
	private Color c = Color.BLACK;
	public void setColor(float r, float g, float b, float a) {
		Graphics2D g2D = getGraphics();
		
		g2D.setColor(new Color((int)r, (int)g, (int)b, (int)a));
		c = new Color((int)r, (int)g, (int)b, (int)a);
	}


	public void setColor(float r, float g, float b) {
		Graphics2D g2D = getGraphics();
		
		g2D.setColor(new Color((int)r, (int)g, (int)b));
		c = new Color((int)r, (int)g, (int)b, (int)255);
		
	}

	private float xScale = 1;
	private float yScale = 1;
	public void scale(float x, float y) {
		xScale = x;
		yScale = y;
		
	}

	
}
