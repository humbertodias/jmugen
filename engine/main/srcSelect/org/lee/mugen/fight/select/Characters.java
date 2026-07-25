package org.lee.mugen.fight.select;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.lee.mugen.core.JMugenConstant;
import org.lee.mugen.fight.section.Section;
import org.lee.mugen.fight.system.MugenSystem;
import org.lee.mugen.imageIO.PCXPalette;
import org.lee.mugen.imageIO.RawPCXImage;
import org.lee.mugen.renderer.GraphicsWrapper;
import org.lee.mugen.renderer.ImageContainer;
import org.lee.mugen.sff.SffReader;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.character.SpriteDef;

public class Characters implements Section {
	MugenSystem root;
	private Map<String, String> characterStageMap = new HashMap<String, String>();
	private Map<String, String> characterSFFMap = new HashMap<String, String>();
	private Map<String, String> characterNameMap = new HashMap<String, String>();
	private List<String> charactersOrder = new ArrayList<String>();
	private Map<String, SpriteDef> charactersMap = new HashMap<String, SpriteDef>();
	
	
	public Map<String, String> getCharacterStageMap() {
		return characterStageMap;
	}

	public List<String> getCharactersOrder() {
		return charactersOrder;
	}

	public SpriteDef getSpritedef(String character) {
		SpriteDef spriteDef = charactersMap.get(character);
		if (spriteDef == null) {
			try {
				spriteDef = SpriteDef.parseSpriteDef(new File(JMugenConstant.RESOURCE + "chars/" + character + "/" + character + ".def").getAbsolutePath());
			} catch (Exception e) {
				e.printStackTrace();
			}
			charactersMap.put(character, spriteDef);
		}
		return spriteDef;
	}

	public void setCharactersOrder(List<String> charactersOrder) {
		this.charactersOrder = charactersOrder;
	}
	private Map<String, ImageContainer> portraitsMap = new HashMap<String, ImageContainer>();
	private Map<String, ImageContainer> bigPortraitsMap = new HashMap<String, ImageContainer>();
	
	private void doSpriteInformation(String character) throws IOException {
		File path = new File(JMugenConstant.RESOURCE + "chars/" + character + "/" + character + ".def");
		if (!path.exists()) {
			System.out.println("File " + path.getAbsolutePath() + " does not exist");
			return;
		}
		BufferedReader br = new BufferedReader(new FileReader(path));
		String line;
		boolean findFiles = false;
		boolean findInfo = false;
		boolean isFindDisplayname = false;
		boolean isFindSprite = false;
		while ((line = br.readLine()) != null) {
			line = line.replaceAll(" ", "").replaceAll("\t", "").toLowerCase();
			if (line.indexOf("[files]") == 0) {
				findFiles = true;
			} else if (line.indexOf("sprite") == 0 && findFiles ) {
				int index = line.indexOf(";");
				if (index != -1) {
					line = line.substring(line.indexOf("=") + 1, line.indexOf(";"));
				} else {
					line = line.substring(line.indexOf("=") + 1);
					
				}

				characterSFFMap.put(character, line);
				isFindSprite = true;
			} else if (line.indexOf("[info]") == 0) {
				findInfo = true;
			} else if (line.indexOf("displayname") == 0 && findInfo) {

				int index = line.indexOf(";");
				if (index != -1) {
					line = line.substring(line.indexOf("=") + 1, line.indexOf(";"));
				} else {
					line = line.substring(line.indexOf("=") + 1);
					
				}
				if (line.startsWith("\"") && line.endsWith("\""))
					line = line.substring(1, line.length() - 1);
				characterNameMap.put(character, line);
				isFindDisplayname = true;
			}
			if (isFindDisplayname && isFindSprite)
				return;
		}
	}
	final String charCacheDir = JMugenConstant.RESOURCE + "cache/char/";

	public ImageContainer getPortrait(String character) {
		ImageContainer ic = portraitsMap.get(character);
		if (ic == null) {
			String sff = characterSFFMap.get(character);
			File file = new File(JMugenConstant.RESOURCE + "chars/" + character + "/" + sff);

			
			
			new File(charCacheDir + character).mkdirs();
			File cache = new File(charCacheDir + character + "/" + character + ".sraw");
			File cachePal = new File(charCacheDir + character + "/" + character + ".spal");
			File sprSff = file;
			
			RawPCXImage img = null;
			if (cache.exists() && sprSff.lastModified() <= cache.lastModified()) {
				PCXPalette pal = new PCXPalette();
				try {
					pal.load(new FileInputStream(cachePal));
					img = new RawPCXImage(IOUtils.toByteArray(new FileInputStream(cache)), pal);	
				} catch (Exception e) {
					e.printStackTrace();
				}

				
			}
			if (img == null) {
				String path = file.getAbsolutePath();
				img = getImageFromSpriteSFF(path, 9000, 0);
				try {
					FileOutputStream fos = new FileOutputStream(cache);
					fos.write(img.getData());
					fos.close();
					
					fos = new FileOutputStream(cachePal);
					img.getPalette().save(fos);
					fos.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			ic = GraphicsWrapper.getInstance().getImageContainer(img);
			portraitsMap.put(character, ic);
		}
		return ic;
	}
	
	public ImageContainer getBigPortrait(String character) {
		ImageContainer ic = bigPortraitsMap.get(character);
		if (ic == null) {
			String sff = characterSFFMap.get(character);
			File file = new File(JMugenConstant.RESOURCE + "chars/" + character + "/" + sff);
			
			new File(charCacheDir + character).mkdirs();
			File cache = new File(charCacheDir + character + "/" + character + ".braw");
			File cachePal = new File(charCacheDir + character + "/" + character + ".bpal");
			File sprSff = file;
			
			RawPCXImage img = null;
			if (cache.exists() && sprSff.lastModified() <= cache.lastModified()) {
				PCXPalette pal = new PCXPalette();
				try {
					pal.load(new FileInputStream(cachePal));
					img = new RawPCXImage(IOUtils.toByteArray(new FileInputStream(cache)), pal);	
				} catch (Exception e) {
					e.printStackTrace();
				}

				
			}
			if (img == null) {
				String path = file.getAbsolutePath();
				img = getImageFromSpriteSFF(path, 9000, 1);
				try {
					FileOutputStream fos = new FileOutputStream(cache);
					fos.write(img.getData());
					fos.close();
					
					fos = new FileOutputStream(cachePal);
					img.getPalette().save(fos);
					fos.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			ic = GraphicsWrapper.getInstance().getImageContainer(img);
			bigPortraitsMap.put(character, ic);
		}
		return ic;
	}
	
	private RawPCXImage getImageFromSpriteSFF(String filename, int grp, int num) {
		try {
			byte[] data = SffReader.getImage(new FileInputStream(filename), grp, num, null);
			RawPCXImage raw = new RawPCXImage(data, new PCXPalette());
			return raw;
//			return PCXLoader.loadImageColorIndexed(new ByteArrayInputStream(data), new PCXPalette(), false, true);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalArgumentException(e);
		}
	}
	
	@Override
	public void parse(Object root, String name, String value) throws Exception {
		if (name.replaceAll(" ", "").length() > 0) {
			String[] charStage = {name};
			if (name.indexOf(',') > 0)
				charStage = name.split(" *, *");
			doSpriteInformation(charStage[0]);
			charactersOrder.add(charStage[0]);
			if (charStage.length > 1)
				characterStageMap.put(charStage[0], charStage[1]);
			else
				characterStageMap.put(charStage[0], null);

		}
	}
	public String getSpriteName(String spr) {
		return characterNameMap.get(spr);
	}
	
	public Sprite getInstanceOfSprite(String name) {
		return new Sprite("undefine", getSpritedef(name), 0);
	}
	

}
