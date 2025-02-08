package org.lee.mugen.sprite.cns.type.function;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.character.SpriteCns;
import org.lee.mugen.sprite.cns.eval.function.StateCtrlFunction;
import org.lee.mugen.sprite.parser.Parser;
/**
 * 
 * @author Dr Wong
 * @category Cns Function : Complete
 */
public class Velmul extends StateCtrlFunction {
	public Velmul() {
		super("velmul", new String[] {"x", "y", "z"});
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
				sprInfo.getVelset().mulX(fx);
			}
			
		}
		int yIndex = getParamIndex("y");
		if (valueableParams[yIndex] != null) {
			Valueable y = valueableParams[yIndex][0];
			if (y != null) {
				float fy = Parser.getFloatValue(y.getValue(spriteId));
				sprInfo.getVelset().mulY(fy);
			}		
		}
		int zIndex = getParamIndex("z");
		if (valueableParams[zIndex] != null) {
			
			Valueable z = valueableParams[zIndex][0];
			if (z != null) {
				float fz = Parser.getFloatValue(z.getValue(spriteId));
				sprInfo.getVelset().mulZ(fz);
			}
		}
		return null;
	}
}
