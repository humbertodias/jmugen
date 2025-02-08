package org.lee.mugen.sprite.cns.type.function;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.character.spiteCnsSubClass.HitDefSub;
import org.lee.mugen.sprite.cns.eval.function.StateCtrlFunction;

public class Hitfalldamage extends StateCtrlFunction {

	public Hitfalldamage() {
		super("hitfalldamage", new String[] {});
	}

	@Override
	public Object getValue(String spriteId, Valueable... params) {
		Sprite sprite = GameFight.getInstance().getSpriteInstance(spriteId);
		HitDefSub hitdef = sprite.getInfo().getLastHitdef();
		if (hitdef != null && hitdef.getHittime() > 0) {
			int damage = hitdef.getFall().getDamage();
			sprite.getInfo().addLife(-damage);
		}
		return null;
	}
}
