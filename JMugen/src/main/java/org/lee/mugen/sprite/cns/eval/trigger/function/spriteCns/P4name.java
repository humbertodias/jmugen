package org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.cns.eval.function.SpriteCnsTriggerFunction;

public class P4name extends SpriteCnsTriggerFunction {

	public P4name() {
		super("p4name", new String[] {});
	}
	@Override
	public Object getValue(String spriteId, Valueable... params) {
		if (GameFight.getInstance().getSpriteDef("4") == null)
			return "";
		return GameFight.getInstance().getSpriteDef("4").getInfo().getDisplayname();
	}

}
