package org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.character.spiteCnsSubClass.HitDefSub;
import org.lee.mugen.sprite.cns.eval.function.SpriteCnsTriggerFunction;

public class Hitshakeover extends SpriteCnsTriggerFunction {

	public Hitshakeover() {
		super("hitshakeover", new String[] {});
	}

	@Override
	public Object getValue(String spriteId, Valueable... params) {
		HitDefSub hitdefFrom = GameFight.getInstance().getSpriteInstance(spriteId).getInfo().getLastHitdef();
		if (hitdefFrom == null)
			return 1;
		int result = (hitdefFrom.getLastTimeHitSomething() 
				+ hitdefFrom.getPausetime().getP2_shaketime()
				) - GameFight.getInstance().getGameState().getGameTime() > 0? 0: 1;

		return  result;
	}

}
