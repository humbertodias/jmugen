package org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.cns.eval.function.SpriteCnsTriggerFunction;

public class Hitfall extends SpriteCnsTriggerFunction {
	public Hitfall() {
		super("hitfall", new String[] {});
	}

	@Override
	public Object getValue(String spriteId, Valueable... params) {
		Sprite sprOne = GameFight.getInstance().getSpriteInstance(spriteId);
		
//		for (HitDefSub hitdefFrom: sprOne.getInfo().getHitdefs()) {
//			if (hitdefFrom == null)
//				continue;
//			if (hitdefFrom.getHittime() > 0) {
//				if ((sprOne.getInfo().getType() == Type.I 
//						|| sprOne.getInfo().getType() == Type.A) 
//						&& hitdefFrom.getAir().getFall() != 0)
//					return 1;
//				else if (hitdefFrom.fall() != 0)
//					return 1;
//			}
//		}
		return sprOne.getInfo().getLastHitdef().fall();
			
	}

	
}
