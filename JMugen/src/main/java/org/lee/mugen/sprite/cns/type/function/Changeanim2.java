package org.lee.mugen.sprite.cns.type.function;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.cns.eval.function.StateCtrlFunction;
import org.lee.mugen.sprite.parser.Parser;

public class Changeanim2 extends StateCtrlFunction {
	public Changeanim2() {
		super("changeanim2", new String[] {"value", "elem", "ignorehitpause", "persistent"});
	}
	
	@Override
	public Object getValue(String spriteId, Valueable... p) {
		Sprite sprite = GameFight.getInstance().getSpriteInstance(spriteId);
		Sprite sprOther = null;

		int valueIndex = getParamIndex("value");
		Valueable value = valueableParams[valueIndex][0];
		float fvalue = Parser.getFloatValue(value.getValue(spriteId));
		sprite.getSprAnimMng().setAction((int) fvalue, true, sprite.getSpriteState().getSpriteIdToBind());
		int elemIndex = getParamIndex("elem");
		Valueable elem = valueableParams[elemIndex] != null? valueableParams[elemIndex][0]: null;
		if (elem != null) {
			float felem = Parser.getFloatValue(elem.getValue(spriteId));
			sprite.getSprAnimMng().setAnimElem((int) felem - 1);

		}
		
		return null;
	}
}
