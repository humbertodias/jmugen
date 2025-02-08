package org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.cns.eval.function.SpriteCnsTriggerFunction;

public class P3name extends SpriteCnsTriggerFunction {

	public P3name() {
		super("p3name", new String[] {});
	}
	@Override
	public Object getValue(String spriteId, Valueable... params) {
		if (GameFight.getInstance().getSpriteDef("3") == null)
			return "";
		return GameFight.getInstance().getSpriteDef("3").getInfo().getDisplayname();
	}

}
