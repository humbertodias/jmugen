package org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns;

import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.cns.eval.function.SpriteCnsTriggerFunction;
import org.lee.mugen.util.MugenRandom;
/**
 * 
 * @author Dr Wong
 * @category Trigger : Complete
 */
public class Random extends SpriteCnsTriggerFunction {

	public Random() {
		super("random", new String[0]);
	}
	@Override
	public Object getValue(String spriteId, Valueable... params) {
		return MugenRandom.getRandomNumber(0, 999);
	}
}
