package org.lee.mugen.fight.section.elem;

import org.lee.mugen.geom.MugenPoint;
import java.util.HashMap;
import java.util.Map;

import org.lee.mugen.util.BeanTools;

public class SimpleElement {
	MugenPoint pos;
	Map<Integer, Type> bg = new HashMap<Integer, Type>();
	public MugenPoint getPos() {
		return pos;
	}
	public void setPos(MugenPoint pos) {
		this.pos = pos;
	}
	public Map<Integer, Type> getBg() {
		return bg;
	}
	public void setBg(Map<Integer, Type> bg) {
		this.bg = bg;
	}
	
	public void parse(Object root, String name, String value) {
		if (name.equalsIgnoreCase("pos") || name.equalsIgnoreCase("offset")) {
			pos = (MugenPoint) BeanTools.getConvertersMap().get(MugenPoint.class).convert(value);
		} else if (name.startsWith("bg")) {
			String sNum = name.substring(2, name.indexOf("."));
			int num = 0;
			if (sNum.length() > 0) {
				num = Integer.parseInt(sNum);
			}
			Type elem = bg.get(num);
			if (elem == null) {
				elem = new Type();
				bg.put(num, elem);
			}
			elem.setType(Type.getNext(name), elem, value, root);
			elem.parse(Type.getNext(name), value);
			
		}
	}
	public void init() {
		for (Type t: bg.values())
			t.init();
		
	}
}
