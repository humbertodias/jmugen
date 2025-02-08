package org.lee.mugen.sprite.cns.type.function;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.character.SpriteCns;
import org.lee.mugen.sprite.cns.eval.function.StateCtrlFunction;
import org.lee.mugen.sprite.parser.Parser;

public class Veladd extends StateCtrlFunction {
	public Veladd() {
		super("veladd", new String[] {"x", "y"});
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
//				if (sprInfo.isFlip())
//					fx = -fx;
				sprInfo.getVelset().addX(fx);
			}
		}
		int yIndex = getParamIndex("y");
		if (valueableParams[yIndex] != null) {
			Valueable y = valueableParams[yIndex][0];
			if (y != null) {
				float fy = Parser.getFloatValue(y.getValue(spriteId));
				sprInfo.getVelset().addY(fy);
			}		
			
		}
		int zIndex = getParamIndex("z");
		if (zIndex != -1 && valueableParams[zIndex] != null) {
			Valueable z = valueableParams[zIndex][0];
			if (z != null) {
				float fz = Parser.getFloatValue(z.getValue(spriteId));
				sprInfo.getVelset().addZ(fz);
			}
		}
		return null;
	}
}
