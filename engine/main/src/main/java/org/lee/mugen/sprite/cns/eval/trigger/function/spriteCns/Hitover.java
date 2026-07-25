package org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.character.spiteCnsSubClass.HitDefSub;
import org.lee.mugen.sprite.cns.eval.function.SpriteCnsTriggerFunction;

public class Hitover extends SpriteCnsTriggerFunction {

	public Hitover() {
		super("hitover", new String[] {});
	}
	@Override
	public Object getValue(String spriteId, Valueable... params) {
		Sprite sprite = GameFight.getInstance().getSpriteInstance(spriteId);
		HitDefSub hitdefFrom = sprite.getInfo().getLastHitdef();
		if (hitdefFrom == null)
			return 1;
		return hitdefFrom.getHittime() > 0? 0: 1;
	}
}
