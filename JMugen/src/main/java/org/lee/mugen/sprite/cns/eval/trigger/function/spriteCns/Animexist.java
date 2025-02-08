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
public class Animexist extends SpriteCnsTriggerFunction {

	public Animexist() {
		super("animexist", new String[] {});
	}
	@Override
	public Object getValue(String spriteId, Valueable... params) {
		Sprite sprite = GameFight.getInstance().getSpriteInstance(spriteId);
		int anim = Parser.getIntValue(params[0].getValue(spriteId));
		return sprite.getSprAnimMng().isAnimExist(anim)? 1: 0;
	}
}
