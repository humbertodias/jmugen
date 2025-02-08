package org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.cns.eval.function.SpriteCnsTriggerFunction;
import org.lee.mugen.sprite.cns.type.function.Assertspecial;

public class IsAssertSpecial extends SpriteCnsTriggerFunction {

	public IsAssertSpecial() {
		super("isassertspecial", new String[0]);
	}
	
	@Override
	public Object getValue(String spriteId, Valueable... params) {
		String key = params[0].getValue(spriteId).toString();
		Assertspecial.Flag flag = Assertspecial.Flag.valueOf(key.toLowerCase());
		return GameFight.getInstance().getGlobalEvents().isAssertSpecial(spriteId, flag)? 1: 0;
	}
}
