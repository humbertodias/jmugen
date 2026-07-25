package org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.character.SpriteCns.Type;
import org.lee.mugen.sprite.character.spiteCnsSubClass.HitDefSub;
import org.lee.mugen.sprite.cns.eval.function.SpriteCnsTriggerFunction;

public class Canrecover extends SpriteCnsTriggerFunction {
	// TODO TEST
	public Canrecover() {
		super("canrecover", new String[] {});
	}

	@Override
	public Object getValue(String spriteId, Valueable... params) {
		Sprite sprite = GameFight.getInstance().getSpriteInstance(spriteId);
		if (sprite.getInfo().getType() == Type.L) {
			HitDefSub hitdef = sprite.getInfo().getLastHitdef();
			if (hitdef == null || hitdef.getHittime() <= 0)
				return null;
			return hitdef.getFall().getRecover();
		}
		return null;
	}
	

}
