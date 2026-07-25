package org.lee.mugen.stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.lee.mugen.background.Background;
import org.lee.mugen.fight.section.Section;
import org.lee.mugen.sprite.parser.Parser;
import org.lee.mugen.sprite.parser.Parser.GroupText;
import org.lee.mugen.stage.section.elem.Bound;
import org.lee.mugen.stage.section.elem.Camera;
import org.lee.mugen.stage.section.elem.Info;
import org.lee.mugen.stage.section.elem.Music;
import org.lee.mugen.stage.section.elem.PlayerInfo;
import org.lee.mugen.stage.section.elem.Reflection;
import org.lee.mugen.stage.section.elem.Scaling;
import org.lee.mugen.stage.section.elem.Shadow;
import org.lee.mugen.stage.section.elem.StageInfo;

public class Stage implements Serializable {
	private String filename;
	private boolean forceImage;
	public Stage(String filename) throws Exception {
		this.filename = filename;
		parse();
	}
	public Stage(String filename, boolean forceBufferedimage) throws Exception {
		this.filename = filename;
		this.forceImage = forceBufferedimage;
		parse();
	}
	
	private Info info;
	private Camera camera;
	private PlayerInfo playerInfo;
	private Scaling scaling;
	private Bound bound;
	private StageInfo stageInfo;
	private Shadow shadow;
	private Reflection reflection;
	private Music music;
	
	private Background background;

	////////////////////
	public File getCurrentDir() {
		return new File(filename).getParentFile();
	}
	
	private void parse(Section section, GroupText grp) throws Exception {
		for (String key: grp.getKeysOrdered()) {
			String value = grp.getKeyValues().get(key);
			section.parse(this, key, value);
		}
	}
	
	private void parse() throws Exception {
		Reader r = new InputStreamReader(new FileInputStream(filename), "utf-8");
		List<GroupText> groups = Parser.getGroupTextMap(r, true);
		
		String bgdefRegex = " *bgdef *";
		String bgRegex = "( *bg +" + "(.*)\\s*)|(bg)";
		String bgctrldefRegex = " *bgctrldef +" + "(.*) *";
	    String bgCtrlRegex = " *bgctrl +" + "([a-zA-Z0-9\\.\\ \\-\\_]*) *";
		background = new Background(this, getCurrentDir(), bgdefRegex, bgRegex, bgctrldefRegex, bgCtrlRegex);
		background.setForceImage(forceImage);
		background.parse(this, groups);
		
		
		for (GroupText grp: groups) {
			if (grp.getSection().equals("info")) {
				info = new Info();
				parse(info, grp);
			} else if (grp.getSection().equals("camera")) {
				camera = new Camera(this);
				parse(camera, grp);
			} else if (grp.getSection().equals("playerinfo")) {
				playerInfo = new PlayerInfo(this);
				parse(playerInfo, grp);
			} else if (grp.getSection().equals("scaling")) {
				scaling = new Scaling(this);
				parse(scaling, grp);
			} else if (grp.getSection().equals("bound")) {
				bound = new Bound(this);
				parse(bound, grp);
			} else if (grp.getSection().equals("stageinfo")) {
				stageInfo = new StageInfo(this);
				parse(stageInfo, grp);
			} else if (grp.getSection().equals("shadow")) {
				shadow = new Shadow(this);
				parse(shadow, grp);
			} else if (grp.getSection().equals("reflection")) {
				reflection = new Reflection(this);
				parse(reflection, grp);
			} else if (grp.getSection().equals("music")) {
				music = new Music(this);
				parse(music, grp);
			}
		}
		
		
	}

	public String getFilename() {
		return filename;
	}

	public Info getInfo() {
		return info;
	}

	public Camera getCamera() {
		return camera;
	}

	public PlayerInfo getPlayerinfo() {
		return playerInfo;
	}

	public Scaling getScaling() {
		return scaling;
	}

	public Bound getBound() {
		return bound;
	}

	public StageInfo getStageinfo() {
		return stageInfo;
	}

	public Shadow getShadow() {
		return shadow;
	}

	public Reflection getReflection() {
		return reflection;
	}

	public Music getMusic() {
		return music;
	}

	public Background getBackground() {
		return background;
	}

	public void free() {
		background.free();
		
	}

	

	public void process() {
		getBackground().process();
		getCamera().process();
		
	}

	
	
	
}
