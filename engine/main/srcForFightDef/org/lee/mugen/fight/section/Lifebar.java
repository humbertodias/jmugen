package org.lee.mugen.fight.section;

import java.util.List;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.fight.section.elem.PlayerLifebar;
import org.lee.mugen.fight.section.elem.Type;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.character.spiteCnsSubClass.HitDefSub;
import org.lee.mugen.sprite.parser.Parser;
import org.lee.mugen.sprite.parser.Parser.GroupText;

public class Lifebar implements Section {
	PlayerLifebar p1 = new PlayerLifebar();
	PlayerLifebar p2 = new PlayerLifebar();
	
	
	
	public void parse(Object root, String name, String value) {
		if (name.startsWith("p1.")) {
			p1.parse(root, Type.getNext(name), value);
		} else if (name.startsWith("p2.")) {
			p2.parse(root, Type.getNext(name), value);
		}
	}
	
	public PlayerLifebar getP1() {
		return p1;
	}
	public void setP1(PlayerLifebar p1) {
		this.p1 = p1;
	}
	public PlayerLifebar getP2() {
		return p2;
	}
	public void setP2(PlayerLifebar p2) {
		this.p2 = p2;
	}

	public void process() {
		{
			Sprite sprite = GameFight.getInstance().getSpriteInstance("1");
			int life = sprite.getInfo().getLife();
			int maxlife = sprite.getInfo().getData().getLife();
			HitDefSub lastHitdef = sprite.getInfo().getLastHitdef();
			p1.process(true, life, maxlife, lastHitdef == null || lastHitdef.getHittime() <= 0);
		}	
		{
			Sprite sprite = GameFight.getInstance().getSpriteInstance("2");
			int life = sprite.getInfo().getLife();
			int maxlife = sprite.getInfo().getData().getLife();
			HitDefSub lastHitdef = sprite.getInfo().getLastHitdef();
			p2.process(false, life, maxlife, lastHitdef == null || lastHitdef.getHittime() <= 0);
		}	
	}

	public void init() {
		p1.init();
		p2.init();
		
	}


	
}
