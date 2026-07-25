package org.lee.mugen.stage.section.elem;

import org.lee.mugen.fight.section.Section;
import org.lee.mugen.stage.Stage;

public class Bound implements Section {
	private Stage parent = null;
	public Bound(Stage stage) {
		parent = stage;
	}
	
	 //Distance from left/right edge of screen that player can move to
	 //Typically 15
	private int screenleft = 15; //Dist from left of screen that player can move to
	private int screenright = 15;   //Right edge
	
	
	@Override
	public void parse(Object root, String name, String value) throws Exception {
		if (name.equals("screenleft")) {
			screenleft = Integer.parseInt(value);
		} else if (name.equals("screenright")) {
			screenright = Integer.parseInt(value);
			
		}
		
	}
	
	public int getScreenright() {
		return screenright;
	}
	public void setScreenright(int screenright) {
		this.screenright = screenright;
	}
	public int getScreenleft() {
		return screenleft;
	}
	public void setScreenleft(int screenleft) {
		this.screenleft = screenleft;
	}

}
