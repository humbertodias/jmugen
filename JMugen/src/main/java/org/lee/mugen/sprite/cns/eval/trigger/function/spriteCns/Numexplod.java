package org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.base.AbstractSprite;
import org.lee.mugen.sprite.cns.eval.function.SpriteCnsTriggerFunction;
import org.lee.mugen.sprite.entity.ExplodSprite;
import org.lee.mugen.sprite.parser.Parser;

public class Numexplod extends SpriteCnsTriggerFunction {

	public Numexplod() {
		super("numexplod", new String[] {});
	}
	@Override
	public Object getValue(String spriteId, Valueable... params) {
		int count = 0;
		if (params.length > 0) {
			int id = Parser.getIntValue(params[0].getValue(spriteId));

			for (AbstractSprite spr : GameFight.getInstance().getOtherSprites()) {
				if (spr instanceof ExplodSprite) {
					ExplodSprite espr = (ExplodSprite) spr;
					if (espr.getExplod().getId() == id && espr.getExplod().getSprite().getSpriteId().equals(spriteId)) {
						count++;
					}
				}
			}
		}
		return count;
	}

}
