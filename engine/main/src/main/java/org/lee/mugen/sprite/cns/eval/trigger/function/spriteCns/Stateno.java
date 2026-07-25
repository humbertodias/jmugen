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
public class Stateno extends SpriteCnsTriggerFunction {

	public Stateno() {
		super("stateno", new String[] {});
	}

	@Override
	public Object getValue(String spriteId, Valueable...params) {
		Sprite sprite = GameFight.getInstance().getSpriteInstance(spriteId);
		if (sprite.getSpriteState().getCurrentState() == null)
			return 0;
		return Integer.parseInt(sprite.getSpriteState().getCurrentState().getId());
		
	};
	public Valueable[] parseValue(String name, String value) {
		return null;
	}

}
