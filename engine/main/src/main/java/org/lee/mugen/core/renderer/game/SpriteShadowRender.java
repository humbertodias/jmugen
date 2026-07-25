package org.lee.mugen.core.renderer.game;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.renderer.DrawProperties;
import org.lee.mugen.renderer.GraphicsWrapper;
import org.lee.mugen.renderer.ImageContainer;
import org.lee.mugen.renderer.MugenDrawer;
import org.lee.mugen.renderer.PalFxSub;
import org.lee.mugen.renderer.RGB;
import org.lee.mugen.renderer.Renderable;
import org.lee.mugen.renderer.Trans;
import org.lee.mugen.sprite.base.AbstractSprite;
import org.lee.mugen.sprite.cns.type.function.Assertspecial.Flag;
import org.lee.mugen.sprite.entity.PointF;
import org.lee.mugen.stage.Stage;

public class SpriteShadowRender implements Renderable {
	
	private AbstractSprite sprite;

	public SpriteShadowRender(AbstractSprite sprite, boolean isBehind) {
		this.sprite = sprite;
		this.isBehind = isBehind;
	}
	
	public int getPriority() {
		return sprite.getPriority() - 1;
	}

	public boolean isProcess() {
		if (GameFight.getInstance().getGlobalEvents().isAssertSpecial(Flag.globalnoshadow))
			return false;
		return true;
	}

	public boolean remove() {
		return sprite.remove();
	}
	private boolean isBehind = false;
	private float alpha = 0.3f;
	private int heightRatio = 8;
	private int rFilter = -255;
	private int gFilter = -255;
	private int bFilter = -255;
    
	
	public void render() {
		isBehind = true;
		if (isBehind) {
			renderBehind();
		} else {
			renderNotBehind();
		}
		
	}

	public void renderNotBehind() {
		MugenDrawer g = GraphicsWrapper.getInstance();
		
		ImageContainer img = null;
		img = (ImageContainer) sprite.getCurrentImage();
		
		Stage stage = GameFight.getInstance().getStage();
		int _mvX = stage.getCamera().getX();
		int _mvY = stage.getCamera().getY();
	
		PointF posToDraw = sprite.getPosToDraw();
		PointF posOfSpr = sprite.getSpriteRealPos();
		
		int x = 0;
		int y = 0;
		x = _mvX + stage.getCamera().getWidth()/2;
		y = stage.getStageinfo().getZoffset() + _mvY;

	    
	    

	    // Transformation
//        shearFilter.setXAngle(0.4f);
//        img = shearFilter.filter(img, null);
	    int hScaled = img.getHeight()/heightRatio;

        int yAddedPos = (int) (posOfSpr.getY());
        boolean isFlipH = sprite.isFlip() ^ sprite.getSprAnimMng().getCurrentImageSprite().isMirrorH();
        boolean isFlipV = sprite.getSprAnimMng().getCurrentImageSprite().isMirrorV() ^ isBehind;
        
        DrawProperties drawProperties = new DrawProperties(
        		x + posToDraw.getX(), 
        		(y + (int)(yAddedPos * 0.1) - hScaled) * heightRatio,
        		isFlipH,
        		isFlipV,
        		img);
        drawProperties.setXScaleFactor(1f);
        drawProperties.setYScaleFactor(1f/heightRatio);
        drawProperties.setTrans(Trans.ADD);
        PalFxSub fx = new PalFxSub();
        fx.setMul(new RGB(0,0,0,0));
        drawProperties.setPalfx(fx);
     
        // TODO
//        	drawProperties.getLeftBottomOffset().x = -25;
//        	drawProperties.getRightBottomOffset().x = -25;
        
        
        g.draw(drawProperties);
	}

	public void renderBehind() {
		MugenDrawer g = GraphicsWrapper.getInstance();
		
		ImageContainer img = null;
		img = (ImageContainer) sprite.getCurrentImage();
		
		if (img == null)
			return;
		Stage stage = GameFight.getInstance().getStage();
		int _mvX = stage.getCamera().getX();
		int _mvY = stage.getCamera().getY();
	
		PointF posToDraw = sprite.getPosToDraw();
		PointF posOfSpr = sprite.getSpriteRealPos();
		
		int x = 0;
		int y = 0;
		x = _mvX + stage.getCamera().getWidth()/2;
		y = stage.getStageinfo().getZoffset() + _mvY;


	    // Transformation
	    int hScaled = img.getHeight()/heightRatio;

        int yAddedPos = (int) (posOfSpr.getY());
        boolean isFlipH = sprite.isFlip() ^ sprite.getSprAnimMng().getCurrentImageSprite().isMirrorH();
        boolean isFlipV = sprite.getSprAnimMng().getCurrentImageSprite().isMirrorV() ^ isBehind;
        


        DrawProperties drawProperties = new DrawProperties(
        		x + posToDraw.getX(), 
        		(y - (int)(yAddedPos * 0.1)),
        		isFlipH,
        		isFlipV,
        		img);
        drawProperties.setXScaleFactor(1f  * sprite.getXScale());
        drawProperties.setYScaleFactor(0.25f * sprite.getYScale());
        drawProperties.setTrans(Trans.NONE);
        drawProperties.setAlpha(1f);
        
        PalFxSub fx = new PalFxSub();
        fx.setMul(new RGB(0,0,0,40f));
        fx.setAdd(new RGB(255,255,255,255f));
        fx.setColor(0);
        
        drawProperties.setPalfx(fx);
        if (drawProperties.getYTopDst() < stage.getStageinfo().getZoffset())
        	return;
        g.draw(drawProperties);
	}

	public void setPriority(int p) {
	}

	public AbstractSprite getSprite() {
		// TODO Auto-generated method stub
		return sprite;
	}

}
