package org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.cns.eval.function.SpriteCnsTriggerFunction;

/**
 * 
 * @author Dr Wong
 * @category Trigger : Complete
 */
public class Statetime extends SpriteCnsTriggerFunction {

	public Statetime() {
		super("statetime", new String[0]);
	}
	@Override
	public Object getValue(String spriteId, Valueable... params) {
		Sprite sprite = GameFight.getInstance().getSpriteInstance(spriteId);
		return sprite.getSpriteState().getTimeInState();
	}
}
