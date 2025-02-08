package org.lee.mugen.sprite.cns.type.function;

import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.cns.eval.function.StateCtrlFunction;
import org.lee.mugen.util.Logger;

public class Print extends StateCtrlFunction {

	public Print() {
		super("print", new String[] {"value"});
	}

	@Override
	public Object getValue(String spriteId, Valueable... params) {
		int xIndex = getParamIndex("value");
		if (valueableParams[xIndex] != null) {
			Valueable x = valueableParams[xIndex][0];
			Logger.log(" >>> " + x.getValue(spriteId));
		}
		return null;
	}
}
