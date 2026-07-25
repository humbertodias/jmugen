package org.lee.mugen.sprite.cns.type.function;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.character.SpriteCns;
import org.lee.mugen.sprite.cns.eval.function.StateCtrlFunction;
import org.lee.mugen.sprite.parser.Parser;

public class Posset extends StateCtrlFunction {
	public Posset() {
		super("posset", new String[] {"x", "y"});
	}
	
	@Override
	public Object getValue(String spriteId, Valueable... p) {
		Sprite sprite = GameFight.getInstance().getSpriteInstance(spriteId);
		SpriteCns sprInfo = sprite.getInfo();
		int xIndex = getParamIndex("x");
		if (valueableParams[xIndex] != null) {
			Valueable x = valueableParams[xIndex][0];
			if (x != null) {
				float fx = Parser.getFloatValue(x.getValue(spriteId));
				sprInfo.setXPos(fx);
			}
		}
		int yIndex = getParamIndex("y");
			if (valueableParams[yIndex] != null) {
			Valueable y = valueableParams[yIndex][0];
			if (y != null) {
				float fy = Parser.getFloatValue(y.getValue(spriteId));
				sprInfo.setYPos(fy);
			}		
		}
		return null;
	}
}
