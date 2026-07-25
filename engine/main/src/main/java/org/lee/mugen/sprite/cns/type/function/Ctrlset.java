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
public class Ctrlset extends StateCtrlFunction {

	public Ctrlset() {
		super("ctrlset", new String[] {"value"});
	}
	
	@Override
	public Object getValue(String spriteId, Valueable... p) {
		Sprite sprite = GameFight.getInstance().getSpriteInstance(spriteId);
		
		SpriteCns spriteInfo = sprite.getInfo();
		int valueIndex = getParamIndex("value");
		Valueable value = valueableParams[valueIndex][0];
		int ivalue = Parser.getIntValue(value.getValue(spriteId));

		spriteInfo.setCtrl(ivalue);
		
		return null;
	}
}
