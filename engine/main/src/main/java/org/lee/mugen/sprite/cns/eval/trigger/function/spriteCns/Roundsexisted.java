package org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.cns.eval.function.SpriteCnsTriggerFunction;

public class Roundsexisted extends SpriteCnsTriggerFunction {
	public Roundsexisted() {
		super("roundsexisted", new String[] {});
	}

	@Override
	public Object getValue(String spriteId, Valueable... params) {
		return GameFight.getInstance().getGameState().getRoundsExisted();
	}

}
