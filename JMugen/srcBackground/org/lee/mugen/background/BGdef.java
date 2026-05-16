package org.lee.mugen.background;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

import org.lee.mugen.renderer.RGB;
import org.lee.mugen.sff.SffReader;
import org.lee.mugen.sprite.baseForParse.SpriteSFF;
import org.lee.mugen.sprite.parser.Parser;
import org.lee.mugen.util.BeanTools;
import org.lee.mugen.util.Logger;

public class BGdef implements Serializable {

	public SpriteSFF getSpr() {
		if (spr == null && sprValue != null) {
			try {
				String path = resolveSprPath();
				SffReader sffreader = new SffReader(path, null);
				spr = new SpriteSFF(sffreader, true, sprForceImage);
			} catch (Exception e) {
				e.printStackTrace();
				throw new IllegalStateException(e);
			}
		}
		return spr;
	}

	private String resolveSprPath() throws Exception {
		String rel = sprValue.replace('\\', '/');
		String dir = root.getCurrentDir().getPath().replace('\\', '/');
		if (dir.length() > 0 && !dir.endsWith("/")) {
			dir = dir + "/";
		}
		String candidate = dir + rel;
		try {
			return Parser.getExistFile(candidate);
		} catch (IllegalArgumentException ignored) {
			File file = new File(root.getCurrentDir(), sprValue);
			if (!file.exists()) {
				file = new File(root.getCurrentDir().getParentFile(), sprValue);
			}
			return file.getAbsolutePath();
		}
	}

	public BGdef() {
	}

	private transient SpriteSFF spr;
	private String sprValue;
	private RGB bgclearcolor = new RGB();
	private int debugbg = 0;
	private Background root;
	private boolean sprForceImage;

	public int getDebugbg() {
		return debugbg;
	}

	public void setDebugbg(int debugbg) {
		this.debugbg = debugbg;
	}


	
	public void parse(Background root, String name, String value, boolean forceImage) throws FileNotFoundException, IOException {
		this.root = root;
		if (value.indexOf('\\') != -1)
			value = value.replaceAll("\\\\", "/");
		if (name.equals("spr")) {
			sprValue = value;
			this.sprForceImage = forceImage;
		} else if (name.equals("debugbg")) {
			debugbg = Integer.parseInt(value);
		} else if (name.equals("bgclearcolor")) {
			bgclearcolor = (RGB) BeanTools.getConvertersMap().get(RGB.class).convert(value);
		}
		
	}

	public void free() {
		if(spr == null) { Logger.log("spr is null"); return; }
		spr.free();
		
	}

	public RGB getBgclearcolor() {
		return bgclearcolor;
	}

	public void setBgclearcolor(RGB bgclearcolor) {
		this.bgclearcolor = bgclearcolor;
	}
}
