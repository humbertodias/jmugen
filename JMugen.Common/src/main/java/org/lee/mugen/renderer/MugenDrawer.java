package org.lee.mugen.renderer;

import java.awt.Color;

import org.lee.mugen.object.Rectangle;


public abstract class MugenDrawer {
	
	
	
	public abstract GameWindow getInstanceOfGameWindow();
	public abstract ImageContainer getImageContainer(Object imageData);
	
	public abstract void scale(float x, float y);
	public abstract void draw(DrawProperties drawProperties);
	public abstract void drawRect(float x1, float y1, float width, float height);
    public abstract void drawLine(int x1, int y1, int x2, int y2);
    public abstract void fillRect(float x, float y, float width, float height);
    public void drawRect(int x, int y, int width, int height) {
    	if ((width < 0) || (height < 0)) {
    	    return;
    	}

    	if (height == 0 || width == 0) {
    	    drawLine(x, y, x + width, y + height);
    	} else {
    	    drawLine(x, y, x + width - 1, y);
    	    drawLine(x + width, y, x + width, y + height - 1);
    	    drawLine(x + width, y + height, x + 1, y + height);
    	    drawLine(x, y + height, x, y + 1);
    	}
    }
    
    
    
	public void setColor(Color color) {
		setColor(color.getRed()/255f, color.getGreen()/255f, color.getBlue()/255f, color.getAlpha()/255f);
		
	}
	public void draw(java.awt.Rectangle r) {
		drawRect(r.x, r.y, r.width, r.height);
		
	}
	public abstract void setColor(float r, float g, float b);
	public abstract void setColor(float r, float g, float b, float a);
	public abstract ImageContainer getImageContainer(Object imageData, int colors);
	public abstract void setClip(Rectangle r);
	public abstract void setAlpha(float a);
	public abstract float getAlpha();
}
