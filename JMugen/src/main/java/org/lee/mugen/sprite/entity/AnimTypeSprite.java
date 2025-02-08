package org.lee.mugen.sprite.entity;

import java.awt.Point;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.fight.section.Fightdef;
import org.lee.mugen.fight.section.elem.AnimType;
import org.lee.mugen.fight.section.elem.Type;
import org.lee.mugen.sprite.base.AbstractSprite;
import org.lee.mugen.sprite.baseForParse.ImageSpriteSFF;
import org.lee.mugen.sprite.character.AnimElement;

public class AnimTypeSprite extends AbstractSprite {

	Type type;
	PointF pos;
	Point start;
	public AnimTypeSprite(Type type, Point start) {
		this.type = type;
		this.start = (Point) start.clone();
		
	}
	
	@Override
	public ImageSpriteSFF getCurrentImageSpriteSFF() {
		Fightdef fightdef = GameFight.getInstance().getFightdef();
		if (type.getType() instanceof AnimType) {
			AnimType anim = (AnimType) type.getType();
			AnimElement animElem = anim.getAnim().getCurrentImageSprite();
			return fightdef.getFiles().getSff().getGroupSpr(animElem.getAirData().getGrpNum()).getImgSpr(animElem.getAirData().getImgNum());
//			dp = anim.getAnim().getSpriteDrawProperties();
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	@Override
	public float getRealXPos() {
		return pos.getX();
	}

	@Override
	public float getRealYPos() {
		return pos.getY();
	}

	@Override
	public boolean isFlip() {
		return type.getFacing() == -1;
	}

	@Override
	public void process() {

		if (type.getType() instanceof AnimType) {
			AnimType anim = (AnimType) type.getType();
			anim.getAnim().process();
		}
		type.decreaseDisplayTime();
		
	}

}
