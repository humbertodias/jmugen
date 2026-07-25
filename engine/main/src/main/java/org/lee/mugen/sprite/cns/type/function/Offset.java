package org.lee.mugen.sprite.cns.type.function;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.cns.eval.function.StateCtrlFunction;
import org.lee.mugen.sprite.parser.Parser;

public class Offset extends StateCtrlFunction {
	public Offset() {
		super("offset", new String[] {"x", "y"});
	}
	
	@Override
	public Object getValue(String spriteId, Valueable... p) {
		Sprite sprite = GameFight.getInstance().getSpriteInstance(spriteId);
		
		int xIndex = getParamIndex("x");
		Valueable x = valueableParams[xIndex][0];
		if (x != null) {
			float fx = Parser.getFloatValue(x.getValue(spriteId));
			sprite.getInfo().getOffset().setX(fx);
		}
		int yIndex = getParamIndex("y");
		Valueable y = valueableParams[yIndex][0];
		if (y != null) {
			float fy = Parser.getFloatValue(y.getValue(spriteId));
			sprite.getInfo().getOffset().setY(fy);
		}		
		return null;
	}
}
