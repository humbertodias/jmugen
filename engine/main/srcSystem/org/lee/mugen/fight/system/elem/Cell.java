package org.lee.mugen.fight.system.elem;

import org.lee.mugen.geom.MugenPoint;
import org.lee.mugen.fight.section.Section;
import org.lee.mugen.fight.section.elem.Type;
import org.lee.mugen.util.BeanTools;

public class Cell implements Section {
	private MugenPoint size;
	private int spacing;
	private Type bg;
	private Type random;
	private int random$switchtime;
	
	@Override
	public void parse(Object root, String name, String value) throws Exception {
		if (name.equals("size")) {
			int[] dim = (int[]) BeanTools.getConvertersMap().get(int[].class).convert(value);
			size = new MugenPoint(dim[0], dim[1]);
		} else if (name.equals("spacing")) {
			spacing = Integer.parseInt(value);
		} else if (name.startsWith("bg")) {
			if (bg == null)
				bg = new Type();
			bg.setType(Type.getNext(name), bg, value, root);
			bg.parse(Type.getNext(name), value);
		} else if (name.equals("random")) {
			if (random == null)
				random = new Type();
			random.setType(Type.getNext(name), random, value, root);
			random.parse(Type.getNext(name), value);
		} else if (name.equals("random.switchtime")) {
			random$switchtime = Integer.parseInt(value);
		}
		
	}
	
	public MugenPoint getSize() {
		return size;
	}

	public void setSize(MugenPoint size) {
		this.size = size;
	}

	public int getSpacing() {
		return spacing;
	}

	public void setSpacing(int spacing) {
		this.spacing = spacing;
	}

	public Type getBg() {
		return bg;
	}

	public void setBg(Type bg) {
		this.bg = bg;
	}

	public Type getRandom() {
		return random;
	}

	public void setRandom(Type random) {
		this.random = random;
	}

	public int getRandom$switchtime() {
		return random$switchtime;
	}

	public void setRandom$switchtime(int random$switchtime) {
		this.random$switchtime = random$switchtime;
	}




}
