package org.lee.mugen.sprite.entity;

import java.awt.Point;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.sprite.base.AbstractSprite;
import org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns.Backedgebodydist;
import org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns.Frontedgebodydist;
import org.lee.mugen.stage.Stage;

public enum Postype {
	p1, p2, front, back, left, right;

	public PointF computePos(AbstractSprite p1, AbstractSprite p2, Point offset, int facing) {
		return computePos(p1, p2, new PointF(offset), facing);
	} 
	
	public PointF computePos(AbstractSprite p1, AbstractSprite p2, PointF offset, int facing) {
		Postype postype = this;
		offset = new PointF(offset);
		Stage stage = GameFight.getInstance().getStage();
		int _mvX = stage.getCamera().getX();
		int _mvY = stage.getCamera().getY();
		float x = _mvX + stage.getCamera().getWidth()/2f;
		int y = stage.getStageinfo().getZoffset() + _mvY;
 
		PointF pos = new PointF();

		switch (postype) {
		case back:
			if (p1.isFlip()) {
				pos.setX(p1.getRealXPos() + Backedgebodydist.compute(p1) - offset.getX());
			} else {
				pos.setX(p1.getRealXPos() - Backedgebodydist.compute(p1) + offset.getX());
			}
			pos.setY(offset.getY());
			break;
		case front:
			if (p1.isFlip()) {
				pos.setX(p1.getRealXPos() - Frontedgebodydist.compute(p1) + offset.getX());
			} else {
				pos.setX(p1.getRealXPos() + Frontedgebodydist.compute(p1) + offset.getX());
			}
			pos.setY(offset.getY());
			break;
		case left:
			if (p1.isFlip()) {
				pos.setX(p1.getRealXPos() - Frontedgebodydist.compute(p1) + offset.getX());
			} else {
				pos.setX(p1.getRealXPos() - Backedgebodydist.compute(p1) + offset.getX());
			}
			pos.setY(offset.getY());
			break;
		case right:
			if (p1.isFlip()) {
				pos.setX(p1.getRealXPos() + Backedgebodydist.compute(p1) + offset.getX());

			} else {
				pos.setX(p1.getRealXPos() + Frontedgebodydist.compute(p1) + offset.getX());
			}
			pos.setY(offset.getY());
			break;
		case p1:
			int mul = 1;
			if (p1.isFlip())
				mul = -1;
			pos.setX(p1.getRealXPos() + offset.getX() * mul);
			pos.setY(p1.getRealYPos() + offset.getY());
			break;
		case p2:
			int mul2 = 1;
			if (p2.isFlip())
				mul2 = -1;
			pos.setX(p2.getRealXPos() + offset.getX() * mul2);
			pos.setY(p2.getRealYPos() + offset.getY());
			break;

		}
		return pos;
	}

}
