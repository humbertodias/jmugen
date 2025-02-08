package org.lee.mugen.sprite.character;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.lee.mugen.sprite.parser.CnsParse;
import org.lee.mugen.sprite.parser.Parser;
import org.lee.mugen.sprite.parser.Parser.GroupText;
import org.lee.mugen.util.BeanTools;

public class SpriteDef implements Serializable {
	private String parentPath;
	private Info info = new Info();
	private Files files = new Files();
	private Arcade arcade = new Arcade();
	private List<GroupText> cnsGroups;
	private static final Pattern MULTIPLE_OUT_BEAN = Pattern.compile("(^.*pal|^.*st)([0-9]*)$", Pattern.CASE_INSENSITIVE);
	public static SpriteDef parseSpriteDef(String def)
			throws Exception {

		SpriteDef spriteDef = new SpriteDef();
		String parentPath = new File(def).getParentFile().getAbsolutePath();
		spriteDef.setParentPath(parentPath);
        Reader r = new InputStreamReader(new FileInputStream(def), "utf-8");

		List<GroupText> groups = Parser.getGroupTextMap(r);

		for (GroupText grp : groups) {
			String section = grp.getSection();
			Map<String, String> kvs = grp.getKeyValues();
			for (String key : kvs.keySet()) {
				String value = kvs.get(key);

				Matcher m = MULTIPLE_OUT_BEAN.matcher(key);
				if (m.find()) {
					value = m.group(2) + "," + value;
					key = m.group(1);
				}
				try {
					BeanTools.setObject(spriteDef, section + "." + key, value);
				} catch (Exception e) {
					System.err.println(section + "." + key
							+ " is on error with value " + value);
				}

			}
		}
		List<GroupText> grps = CnsParse.getCnsGroup(parentPath, spriteDef
				.getFiles().getCns(), spriteDef.getFiles().getStcommon(),
				spriteDef.getFiles().getSt());
		spriteDef.setCnsGroups(grps);
		return spriteDef;
	}

	public static class Arcade implements Serializable {
		private StoryBoard intro = new StoryBoard();
		private StoryBoard ending = new StoryBoard();

		public static class StoryBoard implements Serializable {
			private String storyboard;

			public String getStoryboard() {
				return storyboard;
			}

			public void setStoryboard(String storyboard) {
				this.storyboard = storyboard;
			}
		}

		public StoryBoard getEnding() {
			return ending;
		}

		public StoryBoard getIntro() {
			return intro;
		}
	}

	public static class Pal implements Serializable {
		List<Integer> defaults = new ArrayList<Integer>();

		public void setDefaults(int... params) {
			for (int p : params)
				defaults.add(p);
		}

		public void setDefaults(Object... params) {
			for (Object p : params)
				defaults.add(new Float(p.toString()).intValue());
		}

		public void setDefaults(String... params) {
			if (params != null && params.length == 1) {
				if (params[0].indexOf(',') != -1)
					setDefaults(params[0]);
				return;
			}
			for (String p : params)
				defaults.add(new Float(p).intValue());
		}

		public Integer[] getDefaults() {
			return defaults.toArray(new Integer[0]);
		}

		public void setDefaults(String params) {
			params = params.trim();
			String[] ps = params.split(",");
			setDefaults(ps);
		}

	}

	public static class Info implements Serializable {
		private String name;
		private String displayname;
		private String versiondate;
		private String mugenversion;
		private String author;
		private Pal pal = new Pal();

		public String getAuthor() {
			return author.substring(1, author.length() - 1);
		}

		public void setAuthor(String author) {
			this.author = author;
		}

		public String getDisplayname() {
			return displayname;
		}

		public void setDisplayname(String displayname) {
			this.displayname = displayname;
		}

		public String getMugenversion() {
			return mugenversion;
		}

		public void setMugenversion(String mugenversion) {
			this.mugenversion = mugenversion;
		}

		public String getName() {
			if (name.startsWith("\"") && name.endsWith("\""))
				return name.substring(1, name.length()- 1);
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getVersiondate() {
			return versiondate;
		}

		public void setVersiondate(String versiondate) {
			this.versiondate = versiondate;
		}

		public Pal getPal() {
			return pal;
		}

	}

	public static class Files implements Serializable {
		private String cmd; // Command

		private String cns; // Const

		private List<String> st = new ArrayList<String>(); // State

		private String stcommon; // Common.cns

		private String sprite;

		private String anim;

		private String sound;

		private List<String> pal = new ArrayList<String>();

		public String getAnim() {
			return anim;
		}

		public void setAnim(String anim) {
			this.anim = anim;
		}

		public String getCmd() {
			return cmd;
		}

		public void setCmd(String cmd) {
			this.cmd = cmd;
		}

		public String getCns() {
			return cns;
		}

		public void setCns(String cns) {
			this.cns = cns;
		}

		public String[] getPal() {
			Collections.sort(pal);
			List<String> result = new ArrayList<String>();

			for (String s : pal) {
				String[] token = s.split(",");
				if (token.length == 2) {
					result.add(token[1]);
				}
			}

			return result.toArray(new String[0]);
		}

		public void setPal(String... pal) {
			addPal(pal);
		}

		public void addPal(String... pal) {
			this.pal.addAll(Arrays.asList(pal));
		}

		public String getSound() {
			return sound;
		}

		public void setSound(String sound) {
			this.sound = sound;
		}

		public String getSprite() {
			return sprite;
		}

		public void setSprite(String sprite) {
			this.sprite = sprite;
		}

		public String[] getSt() {
			Collections.sort(st);
			List<String> result = new ArrayList<String>();

			for (String s : st) {
				String[] token = s.split(",");
				if (token.length == 2) {
					result.add(token[1]);
				}
			}

			return result.toArray(new String[0]);
		}

		public void addSt(String... st) {
			this.st.addAll(Arrays.asList(st));
		}

		public void setSt(String... st) {
			addSt(st);
		}

		public String getStcommon() {
			return stcommon;
		}

		public void setStcommon(String stcommon) {
			this.stcommon = stcommon;
		}

	}

	public Files getFiles() {
		return files;
	}

	public Info getInfo() {
		return info;
	}

	public String getParentPath() {
		return parentPath;
	}
	
	public String getParentName() {
		return new File(parentPath).getParentFile().getName();
	}

	public void setParentPath(String path) {
		this.parentPath = path;
	}

	public Arcade getArcade() {
		return arcade;
	}

	public List<GroupText> getCnsGroups() {
		return cnsGroups;
	}

	public void setCnsGroups(List<GroupText> cnsGroups) {
		this.cnsGroups = cnsGroups;
	}
}
