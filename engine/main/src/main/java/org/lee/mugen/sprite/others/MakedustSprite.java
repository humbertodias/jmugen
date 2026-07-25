package org.lee.mugen.sprite.others;

import org.lee.mugen.core.renderer.game.IMakedustRender;
import org.lee.mugen.core.renderer.game.SparkRender.SparkRenderFactory;
import org.lee.mugen.sprite.base.AbstractSprite;
import org.lee.mugen.sprite.character.SpriteAnimManager;
import org.lee.mugen.sprite.entity.PointF;

public class MakedustSprite extends AbstractSprite {
	private PointF _pt;
	private boolean _isFlip;
	
	
	public MakedustSprite(boolean isFlip, final PointF pt) {
		_pt = pt;
		this._isFlip = isFlip;
		
		SparkRenderFactory sparkRenderFactory = SparkRenderFactory.getInstanceOfFightFx();
		
		this.sprAnimMng = new SpriteAnimManager("SparkSprite" + hashCode(), getMugenFightfx().getAir().getGroupSpriteMap());
		this.spriteSFF = getMugenFightfx().getSff();
		sprAnimMng.setAction(IMakedustRender.ACTION_NUMBER);

	}
	@Override
	public float getRealXPos() {
		return _pt.getX();
	}
	
	@Override
	public float getRealYPos() {
		return _pt.getY();
	}

	@Override
	public boolean isFlip() {
		return _isFlip;
	}
	
	boolean error;
	@Override
	public void process() {
		try {
			if (isPause()) {
				pause--;
				return;
			}
			this.sprAnimMng.process();
			
			linearTime++;
		} catch (Exception e) {
			error = true;
			// TODO: handle exception
		}
	}
	
	
	@Override
	public boolean remove() {
		return error || this.sprAnimMng.getAnimTime() == 0;
	}
	
	
}
