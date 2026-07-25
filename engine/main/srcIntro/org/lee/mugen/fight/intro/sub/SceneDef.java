package org.lee.mugen.fight.intro.sub;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.lee.mugen.fight.intro.Intro;
import org.lee.mugen.fight.section.Section;
import org.lee.mugen.sff.SffReader;
import org.lee.mugen.sprite.baseForParse.SpriteSFF;

public class SceneDef implements Section {
	private Intro parent;
	private SpriteSFF spr;
	private int startscene = 0;
	private int currentScene = 0;
	
	public void init() {
		currentScene = startscene;
	}
	
	public int getCurrentScene() {
		return currentScene;
	}
	
	public void setCurrentScene(int currentScene) {
		this.currentScene = currentScene;
	}

	public SpriteSFF getSpr() {
		return spr;
	}

	public void setSpr(SpriteSFF spr) {
		this.spr = spr;
	}

	public int getStartscene() {
		return startscene;
	}

	public void setStartscene(int startscene) {
		this.startscene = startscene;
	}

	@Override
	public void parse(Object root, String name, String value) throws Exception {
		parent = (Intro) root;
		if (name.equals("spr")) {
			
			SffReader sffReader = new SffReader(new File(parent.getCurrentDir().getAbsolutePath(), value).getAbsolutePath(), null);
			spr = new SpriteSFF(sffReader, true, true);
		} else if (name.equals("startscene")) {
			startscene = Integer.parseInt(value);
		}
		
	}

	public void process() {
		List<Integer> keys = new ArrayList<Integer>();
		keys.addAll(parent.getScenes().keySet());
		Collections.sort(keys);
		
		Scene scene = parent.getScenes().get(currentScene);
		if (scene.process()) {
			int index = keys.indexOf(currentScene);
			if (index < keys.size() - 1)
				currentScene = keys.get(index + 1);
		}
			
	}

}
