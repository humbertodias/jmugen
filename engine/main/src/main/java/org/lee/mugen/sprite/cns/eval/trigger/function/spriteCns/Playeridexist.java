package org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.cns.eval.function.SpriteCnsTriggerFunction;
import org.lee.mugen.sprite.parser.Parser;

public class Playeridexist extends SpriteCnsTriggerFunction {
	public Playeridexist() {
		super("playeridexist", new String[] {});
	}

	@Override
	public Object getValue(String spriteId, Valueable... params) {
		Integer id = Parser.getIntValue(params[0].getValue(spriteId));
		return GameFight.getInstance().getSpriteInstance(id.toString()) != null? 1: 0;
	}
}
