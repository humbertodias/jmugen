package org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.cns.eval.function.SpriteCnsTriggerFunction;

public class Teamside extends SpriteCnsTriggerFunction {

	// TODO : Teamside
	public Teamside() {
		super("teamside", new String[] {});
	}
	public void addParam(String name, Valueable[] param) {
		
	}
	@Override
	public Object getValue(String spriteId, Valueable... params) {
		return GameFight.getInstance().getTeamOne().containsKey(GameFight.getInstance().getRoot(spriteId).getSpriteId()) ? 1 : 2;
	}
	public Valueable[] parseValue(String name, String value) {
		return null;
	}

}
