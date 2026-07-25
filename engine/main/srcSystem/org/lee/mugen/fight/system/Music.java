package org.lee.mugen.fight.system;

import org.lee.mugen.fight.section.Section;

public class Music implements Section {
	private MugenSystem ms;
	
	private String title$bgm;
	private int title$bgm$loop;

	private String select$bgm;
	private int select$bgm$loop;
		
	private String vs$bgm;
	private int vs$bgm$loop;

	@Override
	public void parse(Object root, String name, String value) throws Exception {
		this.ms = (MugenSystem) root;
		if (name.equals("title.bgm")) {
			title$bgm = value;
		} else if (name.equals("title.bgm.loop")) {
			title$bgm$loop = Integer.parseInt(value);
		} else if (name.equals("select.bgm")) {
			select$bgm = value;
		} else if (name.equals("select.bgm.loop")) {
			select$bgm$loop = Integer.parseInt(value);
		} else if (name.equals("vs.bgm")) {
			vs$bgm = value;
		} else if (name.equals("vs.bgm.loop")) {
			vs$bgm$loop = Integer.parseInt(value);
		}
	}

	public String getTitle$bgm() {
		return title$bgm;
	}

	public void setTitle$bgm(String title$bgm) {
		this.title$bgm = title$bgm;
	}

	public int getTitle$bgm$loop() {
		return title$bgm$loop;
	}

	public void setTitle$bgm$loop(int title$bgm$loop) {
		this.title$bgm$loop = title$bgm$loop;
	}

	public String getSelect$bgm() {
		return select$bgm;
	}

	public void setSelect$bgm(String select$bgm) {
		this.select$bgm = select$bgm;
	}

	public int getSelect$bgm$loop() {
		return select$bgm$loop;
	}

	public void setSelect$bgm$loop(int select$bgm$loop) {
		this.select$bgm$loop = select$bgm$loop;
	}

	public String getVs$bgm() {
		return vs$bgm;
	}

	public void setVs$bgm(String vs$bgm) {
		this.vs$bgm = vs$bgm;
	}

	public int getVs$bgm$loop() {
		return vs$bgm$loop;
	}

	public void setVs$bgm$loop(int vs$bgm$loop) {
		this.vs$bgm$loop = vs$bgm$loop;
	}

}
