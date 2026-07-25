package org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.cns.eval.function.SpriteCnsTriggerFunction;
import org.lee.mugen.sprite.parser.Parser;

public class Numtarget extends SpriteCnsTriggerFunction {

	// TODO : Numtarget do better caus it's ugly
	public Numtarget() {
		super("numtarget", new String[] {});
	}
	@Override
	public Object getValue(String spriteId, Valueable... params) {
		int id = -1;
		if (params != null && params.length > 0) {
			id = Parser.getIntValue(params[0].getValue(spriteId));
		}
		Sprite sprite = GameFight.getInstance().getSpriteInstance(spriteId);
		return GameFight.getInstance().getFightEngine().getTargetsCount(sprite, id);
	}
}
