package org.lee.mugen.core.renderer.game;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.air.AirData;
import org.lee.mugen.parser.air.AirData.TypeBlit;
import org.lee.mugen.renderer.DrawProperties;
import org.lee.mugen.renderer.GraphicsWrapper;
import org.lee.mugen.renderer.ImageContainer;
import org.lee.mugen.renderer.Trans;
import org.lee.mugen.sprite.base.AbstractSprite;
import org.lee.mugen.sprite.entity.MakeDustSpriteManager;
import org.lee.mugen.sprite.entity.PointF;
import org.lee.mugen.stage.Stage;

public class MakedustRender implements IMakedustRender {
	protected MakeDustSpriteManager manager;

	public MakedustRender(MakeDustSpriteManager manager) {
		this.manager = manager;
	}

	public void render() {
		Stage stage = GameFight.getInstance().getStage();
		int _mvX = stage.getCamera().getX();
		int _mvY = stage.getCamera().getY();
		int x = _mvX + stage.getCamera().getWidth() / 2;
		int y = stage.getStageinfo().getZoffset() + _mvY;

		for (AbstractSprite sprite: manager.getSparkSpritesToProcess()) {
			ImageContainer ic = sprite.getCurrentImage();

			AirData air = sprite.getSprAnimMng().getCurrentImageSprite()
					.getAirData();

			
			boolean isFlipH = air.isMirrorH();
			boolean isFlipV = sprite.isFlip() ^ air.isMirrorV();

			PointF pos = sprite.getPosToDraw();
			
			DrawProperties drawProperties = new DrawProperties(pos.getX() + x, pos.getY() + y, isFlipH, isFlipV, ic);
			if (!manager.getPalfx().isNoPalFx()) {
				manager.getPalfx().setDrawProperties(drawProperties);
				
			}
			// TODO
			if (TypeBlit.ASD == air.type) {
				if (air.getAddcolor() < air.getDestcolor()) {
				
				} else if (air.getAddcolor() > air.getDestcolor()) {
					
				}
				drawProperties.setTrans(Trans.ADD1);
			} else if (TypeBlit.A == air.type) {
				
			} else {
				
			}
			
			drawProperties.setXScaleFactor(sprite.getXScale());
			drawProperties.setXScaleFactor(sprite.getYScale());

			GraphicsWrapper.getInstance().draw(drawProperties);
		}
		
	}

	public int getPriority() {
		return manager.getPriority();
	}

	public boolean isProcess() {
		return manager.isProcess();
	}

	public boolean remove() {
		return manager.remove();
	}

	public void setPriority(int p) {
		manager.setPriority(p);
	}

	public MakeDustSpriteManager getMakeDustSpriteManager() {
		return manager;
	}

	

}
