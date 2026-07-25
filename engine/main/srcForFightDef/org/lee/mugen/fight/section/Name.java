package org.lee.mugen.fight.section;

import org.lee.mugen.fight.section.elem.PlayerName;
import org.lee.mugen.fight.section.elem.Type;

public class Name implements Section {
	PlayerName p1 = new PlayerName();
	PlayerName p2 = new PlayerName();
	
	public void parse(Object root, String name, String value) {
		if (name.startsWith("p1.")) {
			p1.parse(root, Type.getNext(name), value);
		} else if (name.startsWith("p2.")) {
			p2.parse(root, Type.getNext(name), value);
		}
	}
	
	public PlayerName getP1() {
		return p1;
	}
	public void setP1(PlayerName p1) {
		this.p1 = p1;
	}
	public PlayerName getP2() {
		return p2;
	}
	public void setP2(PlayerName p2) {
		this.p2 = p2;
	}

	public void process() {
		// TODO Auto-generated method stub
		
	}

	public void init() {
		p1.init();
		p2.init();
				
	}
	
}
