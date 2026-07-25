package org.lee.mugen.fight.section;

import org.lee.mugen.sprite.baseForParse.SpriteSFF;
import org.lee.mugen.sprite.character.SpriteAnimManager;

public class Fightfx {
	private SpriteSFF sff;
	private SpriteAnimManager air;
	public SpriteAnimManager getAir() {
		return air;
	}
	public void setAir(SpriteAnimManager air) {
		this.air = air;
	}
	public SpriteSFF getSff() {
		return sff;
	}
	public void setSff(SpriteSFF sff) {
		this.sff = sff;
	}
}
