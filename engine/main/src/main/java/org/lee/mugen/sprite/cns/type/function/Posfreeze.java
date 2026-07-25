package org.lee.mugen.sprite.cns.type.function;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.cns.eval.function.StateCtrlFunction;
import org.lee.mugen.sprite.parser.Parser;

public class Posfreeze extends StateCtrlFunction {

	// TODO : Posfreeze
	public Posfreeze() {
		super("posfreeze", new String[] {"value"});
	}
	
	

	@Override
	public Object getValue(String spriteId, Valueable... params) {
		Sprite sprite = GameFight.getInstance().getSpriteInstance(spriteId);
		
		int valueIndex = getParamIndex("value");
		if (valueableParams[valueIndex] != null) {
			Valueable value = valueableParams[valueIndex][0];
			if (value != null) {
				int iValue = Parser.getIntValue(value.getValue(spriteId));
				sprite.getInfo().setPosfreeze(iValue);
			}
			
		}
		return null;
	}
	
	
}
