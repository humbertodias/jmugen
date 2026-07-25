package org.lee.mugen.fight.system;

import org.lee.mugen.fight.intro.entity.Fade;
import org.lee.mugen.fight.section.Section;
import org.lee.mugen.fight.section.elem.Type;
import org.lee.mugen.fight.system.elem.PlayerVsScreen;

public class VsScreen implements Section {
	public static final int NOTHING = -1;
	public static final int ENTER = 0;
	public static final int CURRENT = 1;
	public static final int LEAVE = 2;
	public static final int END = 3;

	private int phase = -1;
	private int lastPhase = -1;
	////////////////
	int time;
	int originalTime;
	
	Fade fadein;

	Fade fadeout;
	
	PlayerVsScreen p1;
	PlayerVsScreen p2;
		

	@Override
	public void parse(Object root, String name, String value) throws Exception {
		if (name.startsWith("fadein.")) {
			if (fadein == null)
				fadein = new Fade();
			fadein.parse(Type.getNext(name), value);
		} else if (name.startsWith("fadeout.")) {
			if (fadeout == null)
				fadeout = new Fade();
			fadeout.parse(Type.getNext(name), value);
		} else if ("time".equalsIgnoreCase(name)) {
			originalTime = Integer.parseInt(value);
			time = Integer.parseInt(value);
			
		} else if (name.startsWith("p1.")) {
			if (p1 == null)
				p1 = new PlayerVsScreen();
			p1.parse(root, Type.getNext(name), value);
		} else if (name.startsWith("p2.")) {
			if (p2 == null)
				p2 = new PlayerVsScreen();
			p2.parse(root, Type.getNext(name), value);
		}
		
	}

	public void init() {
		time = originalTime;
		fadein.init();
		fadeout.init();
		phase = NOTHING;
	}

	public int getTime() {
		return time;
	}


	public void setTime(int time) {
		this.time = time;
	}
	public void decreaseTime() {
		time--;
	}
	

	public int getOriginalTime() {
		return originalTime;
	}


	public void setOriginalTime(int originalTime) {
		this.originalTime = originalTime;
	}


	public Fade getFadein() {
		return fadein;
	}


	public void setFadein(Fade fadein) {
		this.fadein = fadein;
	}


	public Fade getFadeout() {
		return fadeout;
	}


	public void setFadeout(Fade fadeout) {
		this.fadeout = fadeout;
	}


	public PlayerVsScreen getP1() {
		return p1;
	}


	public void setP1(PlayerVsScreen p1) {
		this.p1 = p1;
	}


	public PlayerVsScreen getP2() {
		return p2;
	}


	public void setP2(PlayerVsScreen p2) {
		this.p2 = p2;
	}

	public void process() {
		if (phase == NOTHING && (lastPhase == END || lastPhase == NOTHING)) {
			init();
		}
		if (phase == ENTER) {
			fadein.decrease();
			if (fadein.getTime() <= 0)
				setPhase(CURRENT);
		}
		if (phase == CURRENT) {
			decreaseTime();
		}
		if (phase == LEAVE) {
			fadeout.decrease();
			if (fadeout.getTime() <= 0)
				setPhase(END);
		}
		
		
	}
	public int getPhase() {
		return phase;
	}
	public void setPhase(int phase) {
		this.phase = phase;
	}
}
