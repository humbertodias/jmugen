package org.lee.mugen.sprite.cns.type.function;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.cns.eval.function.StateCtrlFunction;
import org.lee.mugen.sprite.parser.Parser;

/**
 * OK
 * @author Dr Wong
 *
 */
public class Angleset extends StateCtrlFunction {

    public Angleset() {
        super("angleset", new String[] {"value"});
    }

    
    @Override
    public Object getValue(String spriteId, Valueable... params) {
		int valueIndex = Angleset.this.getParamIndex("value");
		if (Angleset.this.valueableParams[valueIndex] != null) {
			Valueable value = Angleset.this.valueableParams[valueIndex][0];
			int iValue = Parser.getIntValue(value.getValue(spriteId, params));
			Sprite sprite = GameFight.getInstance().getSpriteInstance(spriteId);
			sprite.getSprAnimMng().getSpriteDrawProperties().setAngleset(iValue);
		}
    	return null;
    }
}
