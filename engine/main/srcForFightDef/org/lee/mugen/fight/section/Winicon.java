package org.lee.mugen.fight.section;

import org.lee.mugen.fight.section.elem.PlayerWinIcon;
import org.lee.mugen.fight.section.elem.Type;

public class Winicon implements Section {
	PlayerWinIcon p1 = new PlayerWinIcon();
	PlayerWinIcon p2 = new PlayerWinIcon();
	int useiconupto;
	public PlayerWinIcon getP1() {
		return p1;
	}
	public void setP1(PlayerWinIcon p1) {
		this.p1 = p1;
	}
	public PlayerWinIcon getP2() {
		return p2;
	}
	public void setP2(PlayerWinIcon p2) {
		this.p2 = p2;
	}
	public int getUseiconupto() {
		return useiconupto;
	}
	public void setUseiconupto(int useiconupto) {
		this.useiconupto = useiconupto;
	}
	@Override
	public void parse(Object root, String name, String value) throws Exception {
		if (name.startsWith("p1.")) {
			p1.parse(root, Type.getNext(name), value);
		} else if (name.startsWith("p2.")) {
			p2.parse(root, Type.getNext(name), value);
		} else if (name.equals("useiconupto")) {
			useiconupto = Integer.parseInt(value);
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
