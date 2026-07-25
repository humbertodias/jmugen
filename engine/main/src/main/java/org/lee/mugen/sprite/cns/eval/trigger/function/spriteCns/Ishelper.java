package org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.character.SpriteHelper;
import org.lee.mugen.sprite.cns.eval.function.SpriteCnsTriggerFunction;
import org.lee.mugen.sprite.parser.Parser;

public class Ishelper extends SpriteCnsTriggerFunction {

	public Ishelper() {
		super("ishelper", new String[] {});
	}
	@Override
	public Object getValue(String spriteId, Valueable... params) {
		Integer id = null;
		if (params.length > 0)
			id = Parser.getIntValue(params[0].getValue(spriteId));
		if (id != null) {
			Sprite spr = GameFight.getInstance().getSpriteInstance(spriteId);
			if (spr instanceof SpriteHelper) {
				SpriteHelper help = (SpriteHelper) spr;
				return help.getHelperSub().getId() == id.intValue()? 1: 0;
			}
			return 0;
		}
		return (GameFight.getInstance().getSpriteInstance(spriteId) instanceof SpriteHelper)? 1: 0;
	}

}
