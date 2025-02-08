package org.lee.mugen.core.renderer.game;

import org.lee.mugen.parser.air.AirData;
import org.lee.mugen.parser.air.AirData.TypeBlit;
import org.lee.mugen.renderer.AngleDrawProperties;
import org.lee.mugen.renderer.DrawProperties;
import org.lee.mugen.renderer.GraphicsWrapper;
import org.lee.mugen.renderer.ImageContainer;
import org.lee.mugen.renderer.Renderable;
import org.lee.mugen.renderer.Trans;
import org.lee.mugen.renderer.GameWindow.MouseCtrl;
import org.lee.mugen.sprite.base.AbstractAnimManager.SpriteDrawProperties;
import org.lee.mugen.sprite.baseForParse.ImageSpriteSFF;
import org.lee.mugen.sprite.entity.ExplodSprite;
import org.lee.mugen.sprite.entity.PointF;
import org.lee.mugen.sprite.entity.Postype;

public class ExplodRender implements Renderable {
	protected ExplodSprite sprite;

	public ExplodRender(ExplodSprite sprite) {
		this.sprite = sprite;
	}
	
	public ExplodSprite getSprite() {
		return sprite;
	}

	public void render() {

		MouseCtrl mouse = GraphicsWrapper.getInstance().getInstanceOfGameWindow().getMouseStatus();
		float xScale = sprite.getExplod().getScale().getX();
		float yScale = sprite.getExplod().getScale().getY();
		final ImageContainer ic = sprite.getCurrentImage();
		if (ic == null)
			return;


		AirData air = sprite.getSprAnimMng().getCurrentImageSprite().getAirData();
		ImageSpriteSFF imgSprite = sprite.getCurrentImageSpriteSFF();

		boolean isFlipH = air.isMirrorH;

		boolean isFlipV = air.isMirrorV ^ sprite.getExplod().getVfacing() == -1;


		if (sprite.getExplod().getPostype() == Postype.back)
			isFlipH = isFlipH ^ sprite.isFlip();
		
		if (sprite.getExplod().getPostype() == Postype.p1)
			isFlipH = air.isMirrorH ^ getSprite().isFlip();
		if (sprite.getExplod().getPostype() == Postype.p2) 
			isFlipH = air.isMirrorH ^ getSprite().isFlip();
		
		if (sprite.getExplod().getFacing() == -1)
			isFlipH = !isFlipH;
		PointF pos = sprite.getPosToDraw();
		


		// rotate if
		SpriteDrawProperties dp = sprite.getSprAnimMng().getSpriteDrawProperties();

		float xPos = pos.getX();
		float yPos = pos.getY();
		
		DrawProperties drawProperties = new DrawProperties(xPos, yPos, isFlipH, isFlipV, ic);
		if (dp.isActive()) {
			AngleDrawProperties angle = new AngleDrawProperties();
			angle.setAngleset(-dp.getAngleset());
			angle.setXAnchor(pos.getX() + (imgSprite.getXAxis()) * dp.getXScale());
			angle.setXAnchor(pos.getY() + (imgSprite.getYAxis()) * dp.getYScale());
			angle.setXScale(dp.getXScale());
			angle.setYScale(dp.getYScale());
			
			
			drawProperties.setAngleDrawProperties(angle);

		}
		// TODO
		if (TypeBlit.ASD == air.type) {
			if (air.getAddcolor() < air.getDestcolor()) {
			
			} else if (air.getAddcolor() > air.getDestcolor()) {
				
			}
			drawProperties.setTrans(Trans.ADD1);
		} else if (TypeBlit.A == air.type) {
			drawProperties.setTrans(Trans.ADD);
		} else {
			
		}
		
		drawProperties.setXScaleFactor(xScale);
		drawProperties.setYScaleFactor(yScale);
		
		// TODO TypeBlit.ASD, TypeBlit.A

		if (!sprite.getPalfx().isNoPalFx()) {
			sprite.getPalfx().setDrawProperties(drawProperties);
		}
		GraphicsWrapper.getInstance().draw(drawProperties);

	}
	public int getPriority() {
		return sprite.getPriority();
	}

	public boolean isProcess() {
		return sprite.isProcess();
	}


	public boolean remove() {
		return sprite.remove() || sprite.isForceRemove();
	}
	public void setPriority(int p) {
		// TODO Auto-generated method stub
		
	}


}
