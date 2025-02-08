package org.lee.mugen.sprite.cns.type.function;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.character.SpriteDef;
import org.lee.mugen.sprite.cns.eval.function.StateCtrlFunction;
import org.lee.mugen.sprite.parser.Parser;

public class Changepal extends StateCtrlFunction {

	public Changepal() {
		super("changepal", new String[] {"value"});
	}
	
	@Override
	public Object getValue(String spriteId, Valueable... p) {
		Sprite sprite = GameFight.getInstance().getSpriteInstance(spriteId);
		SpriteDef spriteDef = GameFight.getInstance().getSpriteDef(spriteId);

		
		int valueIndex = getParamIndex("value" + "");
		Valueable value = valueableParams[valueIndex][0];
		int ivalue = (int)Parser.getFloatValue(value.getValue(spriteId));
		
		if (ivalue == -1) {
			sprite.nextPal();
			return null;
		}
		if (ivalue == -2) {
			sprite.previousPal();
			return null;
		}
		if (ivalue == -3) {
			sprite.roundPal();
			return null;
		}

		if (ivalue == sprite.getPal() && ivalue < 0 && ivalue > spriteDef.getFiles().getPal().length - 1)
			return null;
		
		sprite.changePal(ivalue);

		return null;
	}
}
