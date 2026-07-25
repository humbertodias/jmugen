package org.lee.mugen.fight.section;

import org.lee.mugen.fight.section.elem.PlayerTurnsFace;
import org.lee.mugen.fight.section.elem.Type;

public class TurnsFace implements Section {
	PlayerTurnsFace p1 = new PlayerTurnsFace();
	PlayerTurnsFace p2 = new PlayerTurnsFace();
	public PlayerTurnsFace getP1() {
		return p1;
	}
	public void setP1(PlayerTurnsFace p1) {
		this.p1 = p1;
	}
	public PlayerTurnsFace getP2() {
		return p2;
	}
	public void setP2(PlayerTurnsFace p2) {
		this.p2 = p2;
	}
	@Override
	public void parse(Object root, String name, String value) throws Exception {
		 if (name.startsWith("p1.")) {
			p1.parse(root, Type.getNext(name), value);
		} else if (name.startsWith("p2.")) {
			p2.parse(root, Type.getNext(name), value);
		}
		
	}
	public void process() {
		// TODO Auto-generated method stub
		
	}
	public void init() {
		p1.init();
		p2.init();
				
	}
	
}
