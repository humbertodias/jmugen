package org.lee.mugen.fight.section;

import java.util.HashMap;
import java.util.Map;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.fight.section.elem.PlayerPowerbar;
import org.lee.mugen.fight.section.elem.Type;
import org.lee.mugen.sprite.character.Sprite;

public class Powerbar implements Section {
	PlayerPowerbar p1 = new PlayerPowerbar();
	PlayerPowerbar p2 = new PlayerPowerbar();
	
	Map<Integer, Type> level = new HashMap<Integer, Type>();

	public void parse(Object root, String name, String value) {
		if (name.startsWith("p1.")) {
			p1.parse(root, Type.getNext(name), value);
		} else if (name.startsWith("p2.")) {
			p2.parse(root, Type.getNext(name), value);
		}  else if (name.startsWith("level")) {
			String sNum = name.substring(5, name.indexOf("."));
			int num = 0;
			if (sNum.length() > 0) {
				num = Integer.parseInt(sNum);
			}
			Type elem = level.get(num);
			if (elem == null) {
				elem = new Type();
				level.put(num, elem);
			}
			elem.setType(Type.getNext(name), elem, value, root);
			elem.parse(Type.getNext(name), value);
			
		}
	}
	
	public PlayerPowerbar getP1() {
		return p1;
	}

	public void setP1(PlayerPowerbar p1) {
		this.p1 = p1;
	}

	public PlayerPowerbar getP2() {
		return p2;
	}

	public void setP2(PlayerPowerbar p2) {
		this.p2 = p2;
	}

	public Map<Integer, Type> getLevel() {
		return level;
	}

	public void setLevel(Map<Integer, Type> level) {
		this.level = level;
	}

	public void process() {
		{
			Sprite sprite = GameFight.getInstance().getSpriteInstance("1");
			int life = sprite.getInfo().getPower();
			int maxlife = sprite.getInfo().getData().getPower();
			p1.process(true, life, maxlife, true);
		}	
		{
			Sprite sprite = GameFight.getInstance().getSpriteInstance("2");
			int life = sprite.getInfo().getPower();
			int maxlife = sprite.getInfo().getData().getPower();
			p2.process(false, life, maxlife, true);
		}	
		
	}

	public void init() {
		p1.init();
		p2.init();
		for (Type t: level.values())
			t.init();
		
	}


	
}
