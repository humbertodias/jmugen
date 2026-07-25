package org.lee.mugen.sprite.cns.type.function;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.character.SpriteCns.Type;
import org.lee.mugen.sprite.character.spiteCnsSubClass.HitDefSub;
import org.lee.mugen.sprite.cns.eval.function.StateCtrlFunction;
import org.lee.mugen.sprite.parser.Parser;

public class Hitfallset extends StateCtrlFunction {

	public Hitfallset() {
		super("hitfallset", new String[] {"value", "xvel", "yvel"});
	}

	@Override
	public Object getValue(String spriteId, Valueable... params) {
		int valueIndex = getParamIndex("value");
		int xIndex = getParamIndex("xvel");
		int yIndex = getParamIndex("yvel");

		Sprite sprOne = GameFight.getInstance().getSpriteInstance(spriteId);
		HitDefSub hitdefFrom = GameFight.getInstance().getSpriteInstance(spriteId).getInfo().getLastHitdef();
				
		if (valueableParams[valueIndex] != null) {
			Valueable valueVl = valueableParams[valueIndex][0];
			int value = 0;
			if (valueVl != null) {
				value = Parser.getIntValue(valueVl.getValue(spriteId));
				if (value != -1) {
					hitdefFrom.setFall(value);
				}
			}
		}
		
		if (valueableParams[xIndex] != null) {
			Valueable x = valueableParams[xIndex][0];
			float xVel = 0;
			if (x == null || Parser.getFloatValue(x.getValue(spriteId)) == 0) {
				
			} else if (Parser.getFloatValue(x.getValue(spriteId)) != 0) {
				if (hitdefFrom.getAttr().getType() == Type.A.getBit()) {
					xVel = hitdefFrom.getAir().getVelocity().getX();
					
				} else {
					xVel = hitdefFrom.getGround().getVelocity().getX();
				}
				Sprite two = null;
				for (Sprite s: GameFight.getInstance().getSprites())
					if (!s.getSpriteId().equals(spriteId))
						two = s;
				if (sprOne.getInfo().isFlip() == two.getInfo().isFlip())
					xVel *= -1;
			}
			hitdefFrom.getFall().setXvelocity(xVel);

		}

		if (valueableParams[yIndex] != null) {
			Valueable y = valueableParams[yIndex][0];
			float yVel = 0;
			if (y == null || Parser.getFloatValue(y.getValue(spriteId)) == 0) {
				
			} else if (Parser.getFloatValue(y.getValue(spriteId)) != 0) {
				if (hitdefFrom.getAttr().getType() == Type.A.getBit()) {
					yVel = hitdefFrom.getAir().getVelocity().getY();
					
				} else {
					yVel = hitdefFrom.getGround().getVelocity().getY();
				}
			}
			hitdefFrom.getFall().setXvelocity(yVel);

		}
		return null;
	}
	
}
