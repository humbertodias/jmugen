package org.lee.mugen.sprite.cns.type.function;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.cns.eval.function.StateCtrlFunction;
import org.lee.mugen.sprite.parser.Parser;

public class Attackmulset extends StateCtrlFunction {

    public Attackmulset() {
        super("attackmulset", new String[] {"value"});
    }
    @Override
    public Object getValue(String spriteId, Valueable... params) {
    	int index = getParamIndex("value");
    	if (valueableParams[index] != null) {
        	Valueable v = valueableParams[index][0];
        	float value = Parser.getFloatValue(v.getValue(spriteId, params));
        	Sprite sprite = GameFight.getInstance().getSpriteInstance(spriteId);
        	sprite.getInfo().setAttackmulset(value);
    		
    	} else {
//    		System.err.println("valeur null");
    		// TODO LOG
    	}
    	
    	return null;
    }
    
}
