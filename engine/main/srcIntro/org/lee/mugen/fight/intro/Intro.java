package org.lee.mugen.fight.intro;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;
import org.lee.mugen.fight.intro.sub.Scene;
import org.lee.mugen.fight.intro.sub.SceneDef;
import org.lee.mugen.fight.section.Section;
import org.lee.mugen.parser.air.AirParser;
import org.lee.mugen.sprite.base.AbstractAnimManager;
import org.lee.mugen.sprite.baseForParse.SpriteSFF;
import org.lee.mugen.sprite.parser.Parser;
import org.lee.mugen.sprite.parser.Parser.GroupText;

public class Intro {
	public static final String S_END = "(?:(?: *;.*$)|(?: *$))";

	public static final String S_SCENE_TITLE_REGEX = "\\s*\\[ *scene *" + "([^\\]]*)" + "\\]" + S_END;
	private static final Pattern P_SCENE_TITLE_REGEX = org.lee.mugen.util.MugenPatterns.compileInsensitive(S_SCENE_TITLE_REGEX);

	private String filename;
	
	public File getCurrentDir() {
		return currentDir;
	}


	public Intro(String filename) {
		this.filename = filename;
		currentDir = new File(new File(filename).getAbsolutePath()).getParentFile();
	}
	
	public SpriteSFF getSpriteSff() {
		return getScenedef().getSpr();
	}
	public SceneDef getScenedef() {
		return scenedef;
	}

	public Map<Integer, Scene> getScenes() {
		return scenes;
	}

	private File currentDir = new File(".");
	private SceneDef scenedef;
	private Map<Integer, Scene> scenes = new HashMap<Integer, Scene>();
	
	private AbstractAnimManager anim;

	public AbstractAnimManager getAnim() {
		return anim;
	}

	public void setAnim(AbstractAnimManager anim) {
		this.anim = anim;
	}
	
	public void parse(Section section, GroupText grp) throws Exception {
		for (String key: grp.getKeysOrdered()) {
			section.parse(this, key, grp.getKeyValues().get(key));
		}
	}
	public void parse(List<GroupText> groups) throws Exception {
		for (GroupText grp: groups) {
			if (grp.getSection().toLowerCase().equals("scenedef")) {
				scenedef = new SceneDef();
				parse(scenedef, grp);
			} else if (P_SCENE_TITLE_REGEX.matcher(grp.getSectionRaw()).find()) {
				
				Matcher m = P_SCENE_TITLE_REGEX.matcher(grp.getSectionRaw());
				m.find();
				Integer id = Integer.parseInt(m.group(1));
				
				Scene scene = new Scene();
				parse(scene, grp);
				
				scenes.put(id, scene);
				
			}
		}
		
	}
	
	public void parse() throws Exception {
        Reader r = new InputStreamReader(new FileInputStream(filename), "utf-8");

		List<GroupText> groups = Parser.getGroupTextMap(r);
        AirParser airParser = new AirParser(filename);
        anim = new AbstractAnimManager(airParser);
        parse(groups);
	
	}
	
	public void process() {
		scenedef.process();
	}


	public void init() {
		scenedef.init();
		for (Scene s: getScenes().values())
			s.init();
	}
	
	public boolean isStart() {
		List<Integer> keys = new ArrayList<Integer>();
		keys.addAll(getScenes().keySet());
		Collections.sort(keys);
		Scene scene = getScenes().get(keys.get(0));
		return scene.getEnd$time() == scene.getOriginalEnd$time() && keys.get(0) == getScenedef().getCurrentScene();
	}
	public boolean isDisplay() {
		return !isStart() && !isEnd();
	}
	public boolean isEnd() {
		int time = 0;
		for (Scene s: getScenes().values()) {
			time += s.getEnd$time();
		}
		return time == 0;
		
	}

}
