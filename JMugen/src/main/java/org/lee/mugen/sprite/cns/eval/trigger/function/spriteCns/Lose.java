package org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns;

import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.cns.eval.function.SpriteCnsTriggerFunction;

public class Lose extends SpriteCnsTriggerFunction {

	public Lose() {
		super("lose", new String[] {});
	}

	@Override
	public Object getValue(String spriteId, Valueable... params) {
		return isLose(spriteId)? 1: 0;
	}

	public static boolean isLose(String spriteId) {
		return Loseko.isLoseKo(spriteId) || Losetime.getResult(spriteId);
	}
}
