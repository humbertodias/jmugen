package org.lee.mugen.sprite.cns.type.function;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.cns.eval.function.StateCtrlFunction;

public class Turn extends StateCtrlFunction {

	public Turn() {
		super("turn", new String[] {});
	}

	@Override
	public Object getValue(String spriteId, Valueable... params) {

		Sprite sprite = GameFight.getInstance().getSpriteInstance(spriteId);
		sprite.getInfo().setFlip(!sprite.getInfo().isFlip());
		sprite.getInfo().getVelset().mulX(-1);
		return null;
	}


}
