package org.lee.mugen.background;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

import org.lee.mugen.renderer.RGB;
import org.lee.mugen.sff.SffReader;
import org.lee.mugen.sprite.baseForParse.SpriteSFF;
import org.lee.mugen.util.BeanTools;
import org.lee.mugen.util.Logger;

public class BGdef implements Serializable {

	public SpriteSFF getSpr() {
		if (spr == null && sprValue != null) {
			File file = new File(root.getCurrentDir(), sprValue);
			if (!file.exists())
				file = new File(root.getCurrentDir().getParentFile(), sprValue);
			SffReader sffreader = null;
			try {
				sffreader = new SffReader(file.getAbsolutePath(), null);
				spr = new SpriteSFF(sffreader, true, sprForceImage);
			} catch (Exception e) {
				e.printStackTrace();
				throw new IllegalStateException();
			}
			
		}
		return spr;
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
}
