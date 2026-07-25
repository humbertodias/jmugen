package org.lee.mugen.fight.section.elem;

import java.util.HashMap;
import java.util.Map;

public class PlayerPowerbar extends PlayerLifebar {
	Type counter;
	Map<Integer, Type> level = new HashMap<Integer, Type>();
	public Type getCounter() {
		return counter;
	}
	public void setCounter(Type counter) {
		this.counter = counter;
	}

	
	public Map<Integer, Type> getLevel() {
		return level;
	}
	public void setLevel(Map<Integer, Type> level) {
		this.level = level;
	}
	@Override
	public void parse(Object root, String name, String value) {
		super.parse(root, name, value);
		if (name.startsWith("level")) {
			String sNum = name.substring(5, name.indexOf("."));
			int num = 0;
			if (sNum.length() > 0) {
				num = Integer.parseInt(sNum);
			}
			Type elem = level.get(num);
			if (elem == null) {
				elem = new Type();
				level.put(num, elem);
			}
			elem.setType(Type.getNext(name), elem, value, root);
			elem.parse(Type.getNext(name), value);
			
		}
		
	}
	
	@Override
	public void process(boolean isLeft, int power, int maxPower,
			boolean processMid) {
        float rangeStart = getRange().getX().x;
        float rangeEnd = getRange().getX().y;
        
        int speed = rangeStart > rangeEnd? -1: 1;
        
        float newRangeFrontStart = rangeStart + (((float)power / maxPower) * (rangeEnd - rangeStart));
        int velocity = 1;
        if (getRangeFront().getX().y  > Math.floor(newRangeFrontStart))
        	getRangeFront().getX().y -= velocity;
        else if (getRangeFront().getX().y  < Math.floor(newRangeFrontStart))
        	getRangeFront().getX().y += velocity;
        
        
        if (processMid) {
            if (getRangeMid().getX().y  > Math.floor(newRangeFrontStart))
            	getRangeMid().getX().y -= velocity;
            else if (getRangeMid().getX().y  < Math.floor(newRangeFrontStart))
            	getRangeMid().getX().y += velocity;
        }
        
        
        
	}
}
