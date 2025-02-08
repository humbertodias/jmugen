package org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.cns.eval.function.SpriteCnsTriggerFunction;

public class Hitpausetime extends SpriteCnsTriggerFunction {
	public Hitpausetime() {
		super("hitpausetime", new String[] {});
	}
	@Override
	public Object getValue(String spriteId, Valueable... params) {
		Sprite sprite = GameFight.getInstance().getSpriteInstance(spriteId);
		if (sprite.getTempPause() != null)
			return sprite.getTempPause();
		return sprite.getPause() > 0? sprite.getPause(): 0;
	}

}
