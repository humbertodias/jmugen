package org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.character.SpriteCns;
import org.lee.mugen.sprite.cns.eval.function.SpriteCnsTriggerFunction;

/**
 * 
 * @author Dr Wong
 * Test if the player is alive
 * @category Trigger : Complete
 */
public class Alive extends SpriteCnsTriggerFunction {

	public Alive() {
		super("alive", new String[0]);
	}
	@Override
	public Object getValue(String spriteId,Valueable... params) {
		Sprite sprite = GameFight.getInstance().getSpriteInstance(spriteId);
		SpriteCns spriteInfo = sprite.getInfo();
		return spriteInfo.getLife() > 0? 1: 0;
	}
}
