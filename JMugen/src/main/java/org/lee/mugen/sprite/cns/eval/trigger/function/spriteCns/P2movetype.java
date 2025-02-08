package org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.character.SpriteHelper;
import org.lee.mugen.sprite.cns.eval.function.SpriteCnsTriggerFunction;
/**
 * 
 * @author Dr Wong
 * @category Trigger : Complete
 */
public class P2movetype extends SpriteCnsTriggerFunction {

	public P2movetype() {
		super("p2movetype", new String[] {});
	}
	@Override
	public Object getValue(String spriteId, Valueable... params) {
		Sprite sprOne = GameFight.getInstance().getSpriteInstance(spriteId);
		Sprite sprTwo = null;
		for (Sprite spr : GameFight.getInstance().getEnnmies(sprOne)) {
			if (spr instanceof SpriteHelper)
				continue;
			if (!spr.equals(sprOne)) {
				sprTwo = spr;
			}
		}
		spriteId = sprTwo.getSpriteId();

		return sprTwo.getInfo().getMovetype().toString().toLowerCase();

	}
	

}
