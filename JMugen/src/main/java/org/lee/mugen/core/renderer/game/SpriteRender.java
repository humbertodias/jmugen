package org.lee.mugen.core.renderer.game;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.air.AirData;
import org.lee.mugen.parser.air.AirData.TypeBlit;
import org.lee.mugen.renderer.AngleDrawProperties;
import org.lee.mugen.renderer.DrawProperties;
import org.lee.mugen.renderer.GraphicsWrapper;
import org.lee.mugen.renderer.ImageContainer;
import org.lee.mugen.renderer.MugenDrawer;
import org.lee.mugen.renderer.Renderable;
import org.lee.mugen.renderer.Trans;
import org.lee.mugen.sprite.base.AbstractSprite;
import org.lee.mugen.sprite.base.AbstractAnimManager.SpriteDrawProperties;
import org.lee.mugen.sprite.baseForParse.ImageSpriteSFF;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.character.SpriteHelper;
import org.lee.mugen.sprite.entity.PointF;
import org.lee.mugen.stage.Stage;


public class SpriteRender implements Renderable {
	protected AbstractSprite sprite;

	public SpriteRender(AbstractSprite sprite) {
		this.sprite = sprite;

	}

	public void render(ImageContainer imageContainer,
			AirData air,
			ImageSpriteSFF imgSprite,
			PointF pos
	) {
		float xScale = sprite.getXScale();
		float yScale = sprite.getYScale();
		MugenDrawer md = GraphicsWrapper.getInstance();
		
		Stage stage = GameFight.getInstance().getStage();
		int _mvX = stage.getCamera().getX();
		int _mvY = stage.getCamera().getY();
		int x = _mvX + stage.getCamera().getWidth() / 2;
		int y = stage.getStageinfo().getZoffset() + _mvY;

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
//			adp.setXScale(dp.getXScale()); // This scale is now directly include in sprite.getXscale()
//			adp.setYScale(dp.getYScale());
			if (sprite.isFlip()) {
				adp.setAngleset(adp.getAngleset() * -1);
			}
			drawProperties.setAngleDrawProperties(adp);
			drawProperties.setXScaleFactor(dp.getXScale());
			drawProperties.setYScaleFactor(dp.getYScale());
//			md.scale(dp.getXScale(), dp.getYScale());
		}

		if (!sprite.getPalfx().isNoPalFx()) {
			sprite.getPalfx().setDrawProperties(drawProperties);

		}
//		drawProperties.setPalfx(new PalFxSub());
		drawProperties.setTrans(air.type == TypeBlit.ASD ? Trans.ADD: Trans.NONE);
		
		if (sprite instanceof Sprite) {
			drawProperties.setXScaleFactor(xScale);
			drawProperties.setYScaleFactor(yScale);
		}
		md.draw(drawProperties);

	}
	public void render() {
		ImageContainer imageContainer = null;
		try {
			imageContainer = sprite.getCurrentImage();
			
		} catch (Exception e) {
			System.err.println("img not exist");
		}
		if (imageContainer == null)
			return;
		AirData air = sprite.getSprAnimMng().getCurrentImageSprite()
				.getAirData();
		ImageSpriteSFF imgSprite = sprite.getCurrentImageSpriteSFF();

		
		PointF pos = sprite.getPosToDraw();
		render(imageContainer, air, imgSprite, pos);
	}

	public int getPriority() {
		return sprite.getPriority();
	}

	public boolean isProcess() {
		return sprite.isDebugRender();
	}

	public boolean remove() {
		return sprite.remove();
	}

	public void setPriority(int p) {

	}
}
