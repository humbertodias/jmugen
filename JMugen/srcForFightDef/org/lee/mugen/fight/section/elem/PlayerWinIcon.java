package org.lee.mugen.fight.section.elem;

import org.lee.mugen.geom.MugenPoint;

import org.lee.mugen.util.BeanTools;

public class PlayerWinIcon extends SimpleElement {
	MugenPoint iconoffset = new MugenPoint();
	Type counter;
	Type n; // Win by normal
	Type h; // Win by Hyper
	Type _throw;// Win by normal throw
	Type c;// Win by cheese
	Type t;// Win timeover
	Type sucide;// Win by suicide (here's a fun one)
	Type teammate;// Opponent beaten by his own teammate (another fun one)
	Type perfect;// Win by perfect (overlay icon)
	
	@Override
	public void parse(Object root, String name, String value) {
		super.parse(root, name, value);
		if (name.equalsIgnoreCase("iconoffset")) {
			iconoffset = (MugenPoint) BeanTools.getConvertersMap().get(MugenPoint.class).convert(value);
		} else if (name.startsWith("counter.")) {
			if (counter == null) {
				counter = new Type();
			}
			counter.setType(Type.getNext(name), counter, value, root);
			counter.parse(Type.getNext(name), value);
		} else if (name.startsWith("n.")) {
			if (n == null) {
				n = new Type();
			}
			n.setType(Type.getNext(name), n, value, root);
			n.parse(Type.getNext(name), value);
		} else if (name.startsWith("h.")) {
			if (h == null) {
				h = new Type();
			}
			h.setType(Type.getNext(name), h, value, root);
			h.parse(Type.getNext(name), value);
		} else if (name.startsWith("throw.")) {
			if (_throw == null) {
				_throw = new Type();
			}
			_throw.setType(Type.getNext(name), _throw, value, root);
			_throw.parse(Type.getNext(name), value);
		} else if (name.startsWith("c.")) {
			if (c == null) {
				c = new Type();
			}
			c.setType(Type.getNext(name), c, value, root);
			c.parse(Type.getNext(name), value);
		} else if (name.startsWith("t.")) {
			if (t == null) {
				t = new Type();
			}
			t.setType(Type.getNext(name), t, value, root);
			t.parse(Type.getNext(name), value);
		} else if (name.startsWith("sucide.")) {
			if (sucide == null) {
				sucide = new Type();
				if (sucide == null) {
					throw new IllegalStateException("You Must specifie type anim, font, or spr first");
				}
			}
			sucide.setType(Type.getNext(name), sucide, value, root);
			sucide.parse(Type.getNext(name), value);
		} else if (name.startsWith("teammate.")) {
			if (teammate == null) {
				teammate = new Type();
			}
			teammate.setType(Type.getNext(name), teammate, value, root);
			teammate.parse(Type.getNext(name), value);
		} else if (name.startsWith("perfect.")) {
			if (perfect == null) {
				perfect = new Type();
			}
			perfect.setType(Type.getNext(name), perfect, value, root);
			perfect.parse(Type.getNext(name), value);
		}
	}
	
	public MugenPoint getIconoffset() {
		return iconoffset;
	}
	public void setIconoffset(MugenPoint iconoffset) {
		this.iconoffset = iconoffset;
	}
	public Type getCounter() {
		return counter;
	}
	public void setCounter(Type counter) {
		this.counter = counter;
	}
	public Type getN() {
		return n;
	}
	public void setN(Type n) {
		this.n = n;
	}
	public Type getH() {
		return h;
	}
	public void setH(Type h) {
		this.h = h;
	}
	public Type getThrow() {
		return _throw;
	}
	public void setThrow(Type throw1) {
		_throw = throw1;
	}
	public Type getC() {
		return c;
	}
	public void setC(Type c) {
		this.c = c;
	}
	public Type getT() {
		return t;
	}
	public void setT(Type t) {
		this.t = t;
	}
	public Type getSucide() {
		return sucide;
	}
	public void setSucide(Type sucide) {
		this.sucide = sucide;
	}
	public Type getTeammate() {
		return teammate;
	}
	public void setTeammate(Type teammate) {
		this.teammate = teammate;
	}
	public Type getPerfect() {
		return perfect;
	}
	public void setPerfect(Type perfect) {
		this.perfect = perfect;
	}

}
