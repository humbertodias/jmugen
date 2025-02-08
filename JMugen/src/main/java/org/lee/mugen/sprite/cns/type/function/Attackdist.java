package org.lee.mugen.sprite.cns.type.function;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.character.spiteCnsSubClass.HitDefSub;
import org.lee.mugen.sprite.cns.eval.function.StateCtrlFunction;
import org.lee.mugen.sprite.parser.Parser;

public class Attackdist extends StateCtrlFunction {
    public Attackdist() {
        super("attackdist", new String[] {"value"});
    }

    @Override
    public Object getValue(String spriteId, Valueable... params) {
    	GameFight sm = GameFight.getInstance();
    	
    	HitDefSub hitDefSub = sm.getFightEngine().getFirstHitdefBySpriteHitter(spriteId);
    	if (hitDefSub != null) {
    		int valueIndex = getParamIndex("value");
    		Valueable v = valueableParams[valueIndex][0];
    		int value = Parser.getIntValue(v.getValue(spriteId, params));
    		
    		hitDefSub.getGuard().setDist(value);
    	}
    	return null;
    }

    
}
