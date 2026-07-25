package org.lee.mugen.fight.section;

import org.lee.mugen.fight.section.elem.FontType;
import org.lee.mugen.fight.section.elem.SimpleElement;
import org.lee.mugen.fight.section.elem.Start;
import org.lee.mugen.fight.section.elem.Type;

public class Combo extends SimpleElement implements Section {

	
	Start start = new Start();
	Type counter;
	FontType text;
	int displaytime;
	int originalDisplaytime;
	public int getOriginalDisplaytime() {
		return originalDisplaytime;
	}



	int counter$shake;
	
	@Override
	public void init() {
		super.init();
		displaytime = originalDisplaytime;
	}
	
	@Override
	public void parse(Object root, String name, String value) {
		super.parse(root, name, value);
		if (name.equals("displaytime")) {
			displaytime = Integer.parseInt(value);
			originalDisplaytime = displaytime;
		} else if (name.equals("text.")) {
			if (text == null) {
				text = new FontType(root);
			}
			text.parse(Type.getNext(name), value);
		} else if (name.equals("start.")) {
			getStart().setX(Integer.parseInt(value));
		} else if (name.startsWith("counter.")) {
			if (name.equals("counter.shake")) {
				counter$shake = Integer.parseInt(value);
			} else {
				if (counter == null) {
					counter = new Type();
				}
				counter.setType(Type.getNext(name), counter, value, root);
				counter.parse(Type.getNext(name), value);
				
			}
		}
	}
	
	
	
	public int getCounter$shake() {
		return counter$shake;
	}



	public void setCounter$shake(int counter$shake) {
		this.counter$shake = counter$shake;
	}



	public Start getStart() {
		return start;
	}
	public void setStart(Start start) {
		this.start = start;
	}
	public Type getCounter() {
		return counter;
	}
	public void setCounter(Type counter) {
		this.counter = counter;
	}
	public FontType getText() {
		return text;
	}
	public void setText(FontType text) {
		this.text = text;
	}
	public int getDisplaytime() {
		return displaytime;
	}
	public void setDisplaytime(int displaytime) {
		this.displaytime = displaytime;
	}



	public void process() {
		// TODO Auto-generated method stub
		
	}
}
