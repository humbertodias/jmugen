package org.lee.mugen.fight.system;

import org.lee.mugen.fight.intro.entity.Fade;
import org.lee.mugen.fight.section.Section;
import org.lee.mugen.fight.section.elem.Type;
import org.lee.mugen.fight.system.elem.Menu;

public class TitleInfo implements Section {

	
	public static final int NOTHING = -1;
	public static final int ENTER = 0;
	public static final int CURRENT = 1;
	public static final int LEAVE = 2;
	public static final int END = 3;

	private int phase = -1;
	private int lastPhase = -1;

	//
	
	private Fade fadein;
	private Fade fadeout;
	private Menu menu;
	
	//
	
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
			
		} else if (name.startsWith("menu.") || name.startsWith("cursor.")) {
			if (menu == null)
				menu = new Menu();
			if (name.startsWith("cursor.")) {
				menu.parse(root, name, value);
			} else {
				menu.parse(root, Type.getNext(name), value);
				
			}
			
		}
		
	}

	
	public Menu getMenu() {
		return menu;
	}


	public int getPhase() {
		return phase;
	}


	public void setPhase(int phase) {
		this.lastPhase = this.phase;
		this.phase = phase;
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
		
		if (phase == LEAVE) {
			fadeout.decrease();
			if (fadeout.getTime() <= 0)
				setPhase(END);
		}
		

	}

	public void init() {
		fadein.init();
		fadeout.init();
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
	
}
