package org.lee.mugen.sprite.cns.type.function;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.cns.eval.function.StateCtrlFunction;

public class Gravity extends StateCtrlFunction {

    public Gravity() {
        super("gravity", new String[] {});
    }

	@Override
	public Object getValue(String spriteId, Object bean, String prefixFunction, Valueable... params) {
		Sprite sprite = GameFight.getInstance().getSpriteInstance(spriteId);
		float yaccel = sprite.getInfo().getMovement().getYaccel();
		sprite.getInfo().getVelset().addY(yaccel);
		return null;
	}

}
