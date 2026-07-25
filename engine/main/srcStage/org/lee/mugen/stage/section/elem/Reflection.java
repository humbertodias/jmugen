package org.lee.mugen.stage.section.elem;

import org.lee.mugen.fight.section.Section;
import org.lee.mugen.stage.Stage;

public class Reflection implements Section {
	private Stage parent = null;
	public Reflection(Stage stage) {
		parent = stage;
	}
	
	//Intensity of reflection (from 0 to 256). Set to 0 to have no
	//reflection. Defaults to 0.
	private int intensity = 0;

	public int getIntensity() {
		return intensity;
	}

	public void setIntensity(int intensity) {
		this.intensity = intensity;
	}

	@Override
	public void parse(Object root, String name, String value) throws Exception {
		if (name.equals("intensity")) {
			intensity = Integer.parseInt(value);
		}
		
	}
}
