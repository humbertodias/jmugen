package org.lee.mugen.fight.intro.sub;

import org.lee.mugen.geom.MugenPoint;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lee.mugen.core.sound.SoundSystem;
import org.lee.mugen.fight.intro.Intro;
import org.lee.mugen.fight.intro.entity.Fade;
import org.lee.mugen.fight.section.Section;
import org.lee.mugen.fight.section.elem.Type;
import org.lee.mugen.renderer.RGB;
import org.lee.mugen.util.BeanTools;

public class Scene implements Section {
	private Intro intro;
	private Fade fadein = new Fade();
	private Fade fadeout = new Fade();
	private RGB clearcolor;
	private MugenPoint layerall$pos;
	private Map<Integer, Type> layers = new HashMap<Integer, Type>();
	
	private String bgm;
	private int bgm$loop;
	private int end$time;
	private int originalEnd$time;
	
	public int getOriginalEnd$time() {
		return originalEnd$time;
	}

	public void init() {
		fadein.init();
		fadeout.init();
		for (Type t: layers.values())
			t.init();
		end$time = originalEnd$time;
	}
	
	public boolean process() {
		if (bgm != null && end$time == originalEnd$time) {
			File music = new File(intro.getCurrentDir(), bgm);
			if (music.exists() && !music.isDirectory())
				SoundSystem.SoundBackGround.playMusic(music.getAbsolutePath());
		}
		if (fadein.getTime() > 0) {
			fadein.setTime(fadein.getTime()-1);
		}
		
		for (Type t: layers.values())
			t.process();
		
		if (end$time > 0)
			end$time--;
		if (fadein.getTime() <= 0) {
			if (fadeout.getTime() > 0 && end$time <= 0)
				fadeout.setTime(fadeout.getTime()-1);
		}
		return end$time <= 0;
	}
	
	@Override
	public void parse(Object root, String name, String value) throws Exception {
		intro = (Intro) root;
		if (name.startsWith("layer") && !name.startsWith("layerall")) {
			String sNum = name.substring(5, name.indexOf("."));
			int num = 0;
			if (sNum.length() > 0) {
				num = Integer.parseInt(sNum);
			}
			Type elem = layers.get(num);
			if (elem == null) {
				elem = new Type();
				layers.put(num, elem);
			}
			elem.setType(Type.getNext(name), elem, value, root);
			elem.parse(Type.getNext(name), value);
		} else if (name.equals("layerall.pos"))	{
			setLayerall$pos((MugenPoint) BeanTools.getConvertersMap().get(MugenPoint.class).convert(value));
		} else if (name.equals("end.time")) {
			setEnd$time(Integer.parseInt(value));
			originalEnd$time = end$time;
		} else if ("bgm".equals(name)) {
			bgm = value;
		} else if ("bgm.loop".equals(name)) {
			bgm$loop = Integer.parseInt(value);
		} else if ("clearcolor".equals(name)) {
			clearcolor = (RGB) BeanTools.getConvertersMap().get(RGB.class).convert(value);
		} else if (name.startsWith("fadein.")) {
			fadein.parse(Type.getNext(name), value);
		} else if (name.startsWith("fadeout.")) {
			fadeout.parse(Type.getNext(name), value);
			
		}
	}
	
	
	
	
	public Fade getFadein() {
		return fadein;
	}
	public void setFadein(Fade fadein) {
		this.fadein = fadein;
	}
	public Fade getFadeout() {
		return fadeout;
	}
	public void setFadeout(Fade fadeout) {
		this.fadeout = fadeout;
	}
	public RGB getClearcolor() {
		if (clearcolor == null) {
			List<Integer> keys = new ArrayList<Integer>();
			keys.addAll(intro.getScenes().keySet());
			Collections.sort(keys);
			for (Integer key: keys) {
				if (intro.getScenes().get(keys.get(key)).getClearcolor() != null)
					clearcolor = intro.getScenes().get(keys.get(0)).getClearcolor();
			}
		}
		return clearcolor;
	}
	public void setClearcolor(RGB clearcolor) {
		this.clearcolor = clearcolor;
	}
	public MugenPoint getLayerall$pos() {
		if (layerall$pos == null) {
			List<Integer> keys = new ArrayList<Integer>();
			keys.addAll(intro.getScenes().keySet());
			Collections.sort(keys);
			for (Integer key: keys) {
				if (intro.getScenes().get(keys.get(key)).getLayerall$pos() != null)
					layerall$pos = intro.getScenes().get(keys.get(0)).getLayerall$pos();
			}
		}
		return layerall$pos;
	}
	public void setLayerall$pos(MugenPoint layerall$pos) {
		this.layerall$pos = layerall$pos;
	}
	public Map<Integer, Type> getLayers() {
		return layers;
	}
	public void setLayers(Map<Integer, Type> layers) {
		this.layers = layers;
	}
	public String getBgm() {
		return bgm;
	}
	public void setBgm(String bgm) {
		this.bgm = bgm;
	}
	public int getBgm$loop() {
		return bgm$loop;
	}
	public void setBgm$loop(int bgm$loop) {
		this.bgm$loop = bgm$loop;
	}
	public int getEnd$time() {
		return end$time;
	}
	public void setEnd$time(int end$time) {
		this.end$time = end$time;
	}






	
	
	
}
