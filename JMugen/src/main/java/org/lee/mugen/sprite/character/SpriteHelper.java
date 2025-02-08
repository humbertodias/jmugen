package org.lee.mugen.sprite.character;

import org.lee.mugen.core.FightEngine;
import org.lee.mugen.core.GameFight;
import org.lee.mugen.renderer.PalFxSub;
import org.lee.mugen.sprite.entity.HelperSub;
import org.lee.mugen.sprite.entity.PointF;
import org.lee.mugen.sprite.entity.Postype;


public class SpriteHelper extends Sprite {
	private HelperSub helperSub;
	public SpriteHelper(String spriteId, final Sprite sprite, HelperSub helperSub) {
		super();
		Sprite clone = null;
		try {
			clone = (Sprite) sprite.clone();
		} catch (CloneNotSupportedException e) {
		}
		assert clone != null;
		
		this.helperSub = helperSub;
		
		this.cmds = clone.cmds;

		this.definition = clone.definition;

		this.info = clone.info;

		this.spriteSnd = clone.spriteSnd;

		this.spriteState = clone.spriteState;
		

		this.palfx = new PalFxSub();

		this.pause = 0;

		this.priority = clone.getPriority();

		this.sprAnimMng = clone.getSprAnimMng();

		this.spriteSFF = clone.getSpriteSFF();

		setSpriteId(spriteId);
	}
	public void initSpriteHelper() {
		Sprite firstEnnemy = FightEngine.getNearestEnnemies(helperSub.getSpriteFrom());
		boolean isFlipH = helperSub.getSpriteFrom().isFlip();
		Sprite sprite = helperSub.getSpriteFrom();
		if (helperSub.getPostype() == Postype.p2) {
			isFlipH = firstEnnemy.isFlip();
		}
		if (helperSub.getFacing() == -1)
			getInfo().setFlip(!isFlipH);
		
		PointF pos = helperSub.getPostype().computePos(sprite, firstEnnemy, helperSub.getPos(), helperSub.getFacing());
		
		getSprAnimMng().setAction(-1);
		getInfo().setXPos(pos.getX());
		getInfo().setYPos(pos.getY());
	}
	public HelperSub getHelperSub() {
		return helperSub;
	}
	@Override
	public void process() {
//		if (helperSub.getBindtime() == -1) {
//			PointF pos = helperSub.getPostype().computePos(helperSub.getSpriteFrom(), FightEngine.getNearestEnnemies(this), helperSub.getPos(), helperSub.getFacing());
//			getInfo().setXPos(pos.getX());
//			getInfo().setYPos(pos.getY());
//
//			boolean isFlipH = helperSub.getSpriteFrom().isFlip();
//			if (helperSub.getPostype() == Postype.p2) {
//				Sprite s = FightEngine.getNearestEnnemies(helperSub.getSpriteFrom());
//				isFlipH = s.isFlip();
//			}
//			if (helperSub.getFacing() == -1) {
//				isFlipH = !isFlipH;
//			}
//			getInfo().setFlip(isFlipH);
//		}
			
		super.process();
	}
	
	@Override
	public boolean remove() {
		if (GameFight.getInstance().getSpriteInstance(getSpriteId()) == null) {
			return true; // Security : mean that spritehelper is destroyed
		}
		return super.remove();
	}
	
	@Override
	public boolean isFlip() {
		return getInfo().isFlip();
	}
}
