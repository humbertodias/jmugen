package org.lee.mugen.core.renderer.game;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.air.AirData;
import org.lee.mugen.renderer.AngleDrawProperties;
import org.lee.mugen.renderer.DrawProperties;
import org.lee.mugen.renderer.GraphicsWrapper;
import org.lee.mugen.renderer.ImageContainer;
import org.lee.mugen.renderer.Trans;
import org.lee.mugen.sprite.base.AbstractSprite;
import org.lee.mugen.sprite.base.AbstractAnimManager.SpriteDrawProperties;
import org.lee.mugen.sprite.baseForParse.ImageSpriteSFF;
import org.lee.mugen.sprite.character.AnimElement;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.entity.PointF;
import org.lee.mugen.stage.Stage;

public class SparkhitRender extends SpriteRender {

	public SparkhitRender(AbstractSprite sprite) {
		super(sprite);
	}


	@Override
	public int getPriority() {
		return 3;
	}

	@Override
	public void render() {
		float xScale = sprite.getXScale();
		float yScale = sprite.getYScale();
		Stage stage = GameFight.getInstance().getStage();
		int _mvX = stage.getCamera().getX();
		int _mvY = stage.getCamera().getY();
		int x = _mvX + stage.getCamera().getWidth() / 2;
		int y = stage.getStageinfo().getZoffset() + _mvY;

		ImageContainer imageContainer = sprite.getCurrentImage();
		if (imageContainer == null)
			return;
		ImageSpriteSFF imgSprite = sprite.getCurrentImageSpriteSFF();
		AnimElement ae = sprite.getSprAnimMng().getCurrentImageSprite();
		AirData air = ae.getAirData();
		PointF pos = sprite.getPosToDraw();

		// rotate if
		final SpriteDrawProperties dp = sprite.getSprAnimMng().getSpriteDrawProperties();
		DrawProperties drawProperties = new DrawProperties(
				pos.getX() + x, 
				pos.getY() + y, 
				sprite.isFlip() ^ air.isMirrorH, 
				air.isMirrorV, imageContainer);

		if (dp.isActive()) {
			AngleDrawProperties adp = new AngleDrawProperties();
			adp.setAngleset(-dp.getAngleset());
			adp.setXAnchor(pos.getX() + x + imgSprite.getXAxis() * xScale);
			adp.setYAnchor(pos.getY() + y + imgSprite.getYAxis() * yScale);
			
			drawProperties.setAngleDrawProperties(adp);
		}

		if (!sprite.getPalfx().isNoPalFx()) {
			sprite.getPalfx().setDrawProperties(drawProperties);

		}
		if (sprite instanceof Sprite) {
			Sprite spr = (Sprite) sprite;

			drawProperties.setXScaleFactor(xScale);
			drawProperties.setYScaleFactor(yScale);
		}
		drawProperties.setTrans(Trans.ADD1);
//		
//		drawProperties.getPaladd().setA(256);
//		drawProperties.getPaladd().setR(256);
//		drawProperties.getPaladd().setG(0);
//		drawProperties.getPaladd().setB(0);
//		
//		drawProperties.getPalmul().setA(128);
//		drawProperties.getPalmul().setR(128);
//		drawProperties.getPalmul().setG(128);
//		drawProperties.getPalmul().setB(256);
//		
		
		org.lee.mugen.renderer.MugenDrawer md = GraphicsWrapper.getInstance();
		
		md.draw(drawProperties);

	}

}
