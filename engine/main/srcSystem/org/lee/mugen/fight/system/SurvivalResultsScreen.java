package org.lee.mugen.fight.system;

import org.lee.mugen.fight.intro.entity.Fade;
import org.lee.mugen.fight.section.Section;
import org.lee.mugen.fight.section.elem.Type;

public class SurvivalResultsScreen implements Section {
	boolean enabled;
	Type wintext;
	Fade fadein;
	Fade fadeout;
	int show$time;
	int originalShow$time;
	int roundstowin;
	int originalRoundstowin;
	
	@Override
	public void parse(Object root, String name, String value) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
