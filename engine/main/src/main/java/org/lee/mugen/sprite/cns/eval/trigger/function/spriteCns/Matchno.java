package org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.cns.eval.function.SpriteCnsTriggerFunction;

public class Matchno extends SpriteCnsTriggerFunction {

	public Matchno() {
		super("matchno", new String[] {});
	}
	@Override
	public Object getValue(String spriteId, Valueable... params) {
		return GameFight.getInstance().getGameState().getRoundno();
	}

}
