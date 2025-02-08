package org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.character.SpriteHelper;
import org.lee.mugen.sprite.cns.eval.function.SpriteCnsTriggerFunction;
import org.lee.mugen.sprite.parser.Parser;

public class Numhelper extends SpriteCnsTriggerFunction {
	public Numhelper() {
		super("numhelper", new String[] {});
	}

	@Override
	public Object getValue(String spriteId, Valueable... params) {
		int val = -1;
		int count = 0;
		if (params.length > 0) {
			val = Parser.getIntValue(params[0].getValue(spriteId));
		}
		Sprite sprite = GameFight.getInstance().getSpriteInstance(spriteId);
		for (Sprite s: GameFight.getInstance().getPartners(sprite)) {
			if (s instanceof SpriteHelper) {
				SpriteHelper helper = (SpriteHelper) s;
				if (helper.getHelperSub().getSpriteFrom() == sprite) {
					if (val != -1) {
						if (helper.getHelperSub().getId() == val)
							count++;
						
					} else {
						count++;
					}
					
				}
			}
		}
		return count;
	}
}
