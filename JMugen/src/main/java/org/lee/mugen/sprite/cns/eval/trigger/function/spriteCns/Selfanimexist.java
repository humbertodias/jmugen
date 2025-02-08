package org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.cns.eval.function.SpriteCnsTriggerFunction;
import org.lee.mugen.sprite.parser.Parser;

/**
 * 
 * @author Dr Wong
 * @category Trigger : Complete
 */
public class Selfanimexist extends SpriteCnsTriggerFunction {

	public Selfanimexist() {
		super("selfanimexist", new String[] {"value"});
	}
	@Override
	public Object getValue(String spriteId, Valueable... params) {
		Sprite sprite = GameFight.getInstance().getSpriteInstance(spriteId);
		return sprite.getSprAnimMng().isSelfAnimExist(Parser.getIntValue(params[0].getValue(spriteId)))? 1: 0;
	}

}
