package org.lee.mugen.fight.section;

import org.lee.mugen.fight.section.elem.PlayerFace;
import org.lee.mugen.fight.section.elem.Type;

public class SimulFace implements Section {
	PlayerFace p1 = new PlayerFace();
	PlayerFace p2 = new PlayerFace();
	PlayerFace p3 = new PlayerFace();
	PlayerFace p4 = new PlayerFace();
	
	
	
	public PlayerFace getP1() {
		return p1;
	}
	public void setP1(PlayerFace p1) {
		this.p1 = p1;
	}
	public PlayerFace getP2() {
		return p2;
	}
	public void setP2(PlayerFace p2) {
		this.p2 = p2;
	}
	public PlayerFace getP3() {
		return p3;
	}
	public void setP3(PlayerFace p3) {
		this.p3 = p3;
	}
	public PlayerFace getP4() {
		return p4;
	}
	public void setP4(PlayerFace p4) {
		this.p4 = p4;
	}
	@Override
	public void parse(Object root, String name, String value) throws Exception {
		if (name.startsWith("p1.")) {
			p1.parse(root, Type.getNext(name), value);
		} else if (name.startsWith("p2.")) {
			p2.parse(root, Type.getNext(name), value);
		} else if (name.startsWith("p3.")) {
			p2.parse(root, Type.getNext(name), value);
		} else if (name.startsWith("p4.")) {
			p2.parse(root, Type.getNext(name), value);
		}
	}
	public void process() {
		// TODO Auto-generated method stub
		
	}
	public void init() {
		p1.init();
		p2.init();
		p3.init();
		p4.init();
		
	}

}
