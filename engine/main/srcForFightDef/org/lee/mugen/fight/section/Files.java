package org.lee.mugen.fight.section;

import java.util.HashMap;
import java.util.Map;

import org.lee.mugen.parser.air.AirParser;
import org.lee.mugen.sff.SffReader;
import org.lee.mugen.snd.Snd;
import org.lee.mugen.snd.SndReader;
import org.lee.mugen.sprite.baseForParse.SpriteSFF;
import org.lee.mugen.sprite.character.SpriteAnimManager;
import org.lee.mugen.sprite.common.resource.FontParser;
import org.lee.mugen.sprite.common.resource.FontProducer;
import org.lee.mugen.sprite.parser.Parser;
import org.lee.mugen.sprite.parser.Parser.GroupText;

public class Files implements Section {
	private SpriteSFF sff;
	private Snd snd;
	private Map<Integer, FontProducer> font = new HashMap<Integer, FontProducer>();

	private Fightfx fightfx;
	private Common common;
	public Common getCommon() {
		return common;
	}
	public void setCommon(Common common) {
		this.common = common;
	}
	public Fightfx getFightfx() {
		return fightfx;
	}
	public void setFightfx(Fightfx fightfx) {
		this.fightfx = fightfx;
	}


	public Map<Integer, FontProducer> getFont() {
		return font;
	}
	public void setFont(Map<Integer, FontProducer> font) {
		this.font = font;
	}
	public SpriteSFF getSff() {
		return sff;
	}
	public void setSff(SpriteSFF sff) {
		this.sff = sff;
	}
	public Snd getSnd() {
		return snd;
	}
	public void setSnd(Snd snd) {
		this.snd = snd;
	}
	

	
	private Map<String, String> getAllFontKeys(GroupText grp) {
		Map<String, String> result = new HashMap<String, String>();
		for (String key: grp.getKeyValues().keySet()) {
			if (key.toLowerCase().startsWith("font")) {
				result.put(key, grp.getKeyValues().get(key));
			}
		}
		return result;
	}
	
	public void parse(GroupText grp) throws Exception {
	
		String sSff = Parser.getExistFile(grp.getKeyValues().get("sff"));
		String sSnd = Parser.getExistFile(grp.getKeyValues().get("snd"));
		sff = new SpriteSFF(new SffReader(sSff, null), true);
		
		snd = (SndReader.parse(sSnd));
		
		font = new HashMap<Integer, FontProducer>();
		Map<String, String> fontKeyValues = getAllFontKeys(grp);
		for (String key: fontKeyValues.keySet()) {
			// TODO : lazy convertion
			Integer iKey = Integer.parseInt(key.toLowerCase().replace("font", ""));
			String file = Parser.getExistFile(fontKeyValues.get(key));
			FontProducer fontProducer = FontParser.getFontProducer(file);
			// TODO : Make Lwjgl FontProducer
			
			font.put(iKey, fontProducer);
		}
		
		fightfx = new Fightfx();
		String fightfxSff = Parser.getExistFile(grp.getKeyValues().get("fightfx.sff"));
		String fightfxAir = Parser.getExistFile(grp.getKeyValues().get("fightfx.air"));
		SffReader sfr = new SffReader(fightfxSff, null);
		SpriteSFF fightFx = new SpriteSFF(sfr, true);
		AirParser airParser = new AirParser(fightfxAir);
		SpriteAnimManager fightAir = new SpriteAnimManager("fightfx.air", airParser);
		
		fightfx.setSff(fightFx);
		fightfx.setAir(fightAir);
		
		
		common = new Common();
		String commonSnd = Parser.getExistFile(grp.getKeyValues().get("common.snd"));
		Snd snd = SndReader.parse(commonSnd);
		common.setSnd(snd);
		
		
	}
	public boolean isParse() {
		return true;
	}
	@Override
	public void parse(Object root, String key, String string) {
		// TODO Auto-generated method stub
		
	}

	

	//
	
}
