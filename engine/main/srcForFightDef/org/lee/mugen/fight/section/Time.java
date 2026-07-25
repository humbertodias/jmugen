package org.lee.mugen.fight.section;

import org.lee.mugen.fight.section.elem.SimpleElement;
import org.lee.mugen.fight.section.elem.Type;

public class Time extends SimpleElement implements Section {
	Type counter;
	int framespercount;

	public int getFramespercount() {
		return framespercount;
	}

	public Type getCounter() {
		return counter;
	}

	public void setCounter(Type counter) {
		this.counter = counter;
	}

	public void setFramespercount(int framespercount) {
		this.framespercount = framespercount;
	}
	
	@Override
	public void parse(Object root, String name, String value) {
		super.parse(root, name, value);
		if (name.startsWith("counter.")) {
			if (counter == null) {
				counter = new Type();
			}
			counter.setType(Type.getNext(name), counter, value, root);
			counter.parse(Type.getNext(name), value);
		} else if (name.equalsIgnoreCase("framespercount")) {
			framespercount = Integer.parseInt(value);
		}
	}

	public void process() {
		// TODO Auto-generated method stub
		
	}
}
