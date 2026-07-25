package org.lee.mugen.fight.system;

import org.lee.mugen.fight.section.Section;

public class DemoMode implements Section {
	boolean enabled = true;
	boolean select$enabled; // Set to 1 to display select screen, 0 to disable
	boolean vsscreen$enabled; // Set to 1 to display versus screen, 0 to disable
	int title$waittime; // Time to wait at title before starting demo mode
	int fight$endtime; // Time to display the fight before returning to title
	int fight$playbgm; // Set to 1 to enable in-fight BGM, 0 to disable
	int fight$bars$display; // Set to 1 to display lifebar, 0 to disable
	int intro$waitcycles; // Cycles to wait before intro cutscene is played again
	int debuginfo; // Set to 0 to hide debugging info (debug mode only)

	
	@Override
	public void parse(Object root, String name, String value) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
