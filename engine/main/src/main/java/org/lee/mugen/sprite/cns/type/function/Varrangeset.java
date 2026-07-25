package org.lee.mugen.sprite.cns.type.function;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.character.SpriteState;
import org.lee.mugen.sprite.cns.eval.function.StateCtrlFunction;
import org.lee.mugen.sprite.parser.Parser;

public class Varrangeset extends StateCtrlFunction {

	public Varrangeset() {
		super("varrangeset", new String[] {"value", "fvalue", "first", "last"});
	}

	@Override
	public Object getValue(String spriteId, Valueable... params) {
		int valueIndex = getParamIndex("value");
		int fvalueIndex = getParamIndex("fvalue");
		int firstIndex = getParamIndex("first");
		int lastIndex = getParamIndex("last");
		
		Valueable value = valueableParams[valueIndex] != null? valueableParams[valueIndex][0]: null;
		Valueable fvalue = valueableParams[fvalueIndex] != null? valueableParams[fvalueIndex][0]: null;
		Valueable first = valueableParams[firstIndex] != null? valueableParams[firstIndex][0]: null;
		Valueable last = valueableParams[lastIndex] != null? valueableParams[lastIndex][0]: null;
		
		int ifirst = 0;
		int ilast = 59;
		if (first != null) {
			ifirst = Parser.getIntValue(first.getValue(spriteId));
		}
		if (last != null) {
			ilast = Parser.getIntValue(last.getValue(spriteId));
		}
		
		Sprite sprite = GameFight.getInstance().getSpriteInstance(spriteId);
		
		SpriteState spriteState = sprite.getSpriteState();
		
		if (value != null) {
			final int intValue = Parser.getIntValue(value.getValue(spriteId));
//			final int intV = Parser.getIntValue(value.getValue(spriteId));
			while (ifirst <= ilast) {
				spriteState.getVars().setVar(ifirst + "", new Valueable() {

					public Object getValue(String spriteId, Valueable... params) {
						return intValue;
					}});
				ifirst++;
			}
		} else if (fvalue != null) {
			final float floatValue = Parser.getFloatValue(fvalue.getValue(spriteId));
//			int intV = Parser.getIntValue(fvalue.getValue(spriteId));
			while (ifirst <= ilast) {
				spriteState.getVars().setFVar(ifirst + "", new Valueable() {
	
					public Object getValue(String spriteId, Valueable... params) {
						return floatValue;
					}});
				ifirst++;
			}
		} else {
			throw new IllegalStateException("This state musn't be reached : Varset none of v or vf");
		}
		
		return null;
	}
}
