package org.lee.mugen.fight.section.elem;

import org.lee.mugen.geom.MugenPoint;



public class PlayerLifebar extends Bar {
	Type mid;
	Type front;
	
	Range rangeMid = new Range();
	Range rangeFront = new Range();

	
	
	public Range getRangeMid() {
		return rangeMid;
	}
	public Range getRangeFront() {
		return rangeFront;
	}
	public Type getMid() {
		return mid;
	}
	public void setMid(Type mid) {
		this.mid = mid;
	}
	public Type getFront() {
		return front;
	}
	public void setFront(Type front) {
		this.front = front;
	}
	public void process(boolean isLeft, int life, int maxLife, boolean processMid) {
        float rangeStart = getRange().getX().x;
        float rangeEnd = getRange().getX().y;
        
        int speed = rangeStart > rangeEnd? -1: 1;
        
        float newRangeFrontStart = rangeStart + (((float)life / maxLife) * (rangeEnd - rangeStart));
        
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

	@Override
	public void parse(Object root, String name, String value) {
		super.parse(root, name, value);
		if (name.startsWith("mid.")) {
			if (mid == null) {
				mid = new Type();
			}
			mid.setType(Type.getNext(name), mid, value, root);
			mid.parse(Type.getNext(name), value);
		} else if (name.startsWith("front.")) {
			if (front == null) {
				front = new Type();
			}
			front.setType(Type.getNext(name), mid, value, root);
			front.parse(Type.getNext(name), value);
		}
	}
	public void init() {
		super.init();
		getRangeFront().x = (MugenPoint) getRange().x.clone();
		getRangeMid().x = (MugenPoint) getRange().x.clone();
		if (mid != null)
			mid.init();
		if (front != null)
			front.init();
	}
}
