package org.lee.mugen.sprite.cns.type.function;

import java.util.Collection;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.cns.eval.function.StateCtrlFunction;
import org.lee.mugen.sprite.entity.ExplodSprite;

public class Explodbindtime extends StateCtrlFunction {

    // TODO : explodbindtime
    public Explodbindtime() {
        super("explodbindtime", new String[] {"id", "value", "time"});
    }
    @Override
    protected Object[] getDefaultValues(String name) {
    	if ("id".equals(name)) {
    		return new Object[] {-1};
    	}
    	return null;
    }
    
    @Override
    public Object getValue(String spriteId, Valueable... params) {
    	Object[] objId = getValueFromName(spriteId, "id");
    	Object[] objTime = getValueFromName(spriteId, "time");
    	Object[] objValue = getValueFromName(spriteId, "time");

    	Integer id = (Integer) objId[0];
    	Integer time = (Integer) objTime[0];
    	Integer value = (Integer) objValue[0];
    	if (value == null) {
    		value = time;
    	}
    	if (time == null) {
    		time = value;
    	}
    	if (time == null) {
    		time = 1;
    	}
    	Collection<ExplodSprite> explods = GameFight.getInstance().getExplodeSprites(id);
    	for (ExplodSprite es: explods) {
    		es.getExplod().setBindtime(time);
    	}
    	
    	return null;
    }
}
