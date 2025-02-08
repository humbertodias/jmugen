package org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.cns.eval.function.SpriteCnsTriggerFunction;

public class Time extends SpriteCnsTriggerFunction {

	public Time() {
		super("time", new String[0]);
	}
	@Override
	public Object getValue(String spriteId, Valueable... params) {

		Sprite sprite = GameFight.getInstance().getSpriteInstance(spriteId);
		long time = sprite.getSpriteState().getTimeInState();
		return time < 0? 0: time;
	}
}
