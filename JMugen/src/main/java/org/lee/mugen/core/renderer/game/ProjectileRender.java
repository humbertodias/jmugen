package org.lee.mugen.core.renderer.game;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.air.AirData;
import org.lee.mugen.parser.air.AirData.TypeBlit;
import org.lee.mugen.renderer.DrawProperties;
import org.lee.mugen.renderer.GraphicsWrapper;
import org.lee.mugen.renderer.ImageContainer;
import org.lee.mugen.renderer.Renderable;
import org.lee.mugen.renderer.Trans;
import org.lee.mugen.sprite.character.SpriteAnimManager;
import org.lee.mugen.sprite.entity.PointF;
import org.lee.mugen.sprite.entity.ProjectileSprite;
import org.lee.mugen.sprite.entity.ProjectileSub;
import org.lee.mugen.stage.Stage;

public class ProjectileRender implements Renderable {
	
	private ProjectileSprite sprite;
	private ProjectileSub projectile;
	
	public ProjectileRender(ProjectileSub projectile) {
		this.sprite = projectile.getSpriteHitter();
		this.projectile = projectile;
		
	}
	
	public ProjectileSprite getSprite() {
		return sprite;
	}

	public void render() {
		Stage stage = GameFight.getInstance().getStage();
		int _mvX = stage.getCamera().getX();
		int _mvY = stage.getCamera().getY();
		int x = _mvX + stage.getCamera().getWidth()/2;
		int y = stage.getStageinfo().getZoffset() + _mvY;
		
		SpriteAnimManager sprAnimManager = sprite.getSprAnimMng();
		ImageContainer ic = sprite.getCurrentImage();
		if (ic ==  null) {
			return;
		}
		AirData air = sprAnimManager.getCurrentImageSprite().getAirData();
		boolean isFlip = sprite.isFlip();

		boolean isFlipH = air.isMirrorH() ^ isFlip;
		boolean isFlipV = air.isMirrorV();
		

		
		PointF pos = sprite.getPosToDraw();

		DrawProperties dp = new DrawProperties(pos.getX() + x, pos.getY() + y, isFlipH, isFlipV, ic);
		
		// TODO

		if (TypeBlit.ASD == air.type) {
			if (air.getAddcolor() < air.getDestcolor()) {
			
			} else if (air.getAddcolor() > air.getDestcolor()) {
				
			}
			dp.setTrans(Trans.ADD1);
		} else if (TypeBlit.A == air.type) {
			
		} else {
			
		}
		
		GraphicsWrapper.getInstance().draw(dp);
	}
	
	public int getPriority() {
		return projectile.getProjsprpriority();
	}

	public boolean isProcess() {
		return true;
	}


	public boolean remove() {
		if (sprite.isRemove())
			return true;
		return false;
	}

	public void setPriority(int p) {
	}
}
