package org.lee.mugen.fight.section.elem;

import java.util.HashMap;

import org.lee.mugen.sprite.base.AbstractAnimManager;
import org.lee.mugen.sprite.baseForParse.ImageSpriteSFF;
import org.lee.mugen.sprite.baseForParse.SpriteSFF;
import org.lee.mugen.sprite.character.AnimElement;
import org.lee.mugen.sprite.character.AnimGroup;


public class AnimType extends CommonType {
	int action;
	Object from;
	public AnimType(int action, Object animGetter) {
		this.action = action;
		this.from = animGetter;
	}
	public AnimType(Object animGetter) {
		this.from = animGetter;
	}
	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}
	@Override
	public void parse(String name, String value) {
		if (name.equals("anim")) {
			action = Integer.parseInt(value);
			
		}
	}
	AbstractAnimManager anim;

	public AbstractAnimManager getAnim() {
		if (anim == null) {
			try {
				AbstractAnimManager animManager = (AbstractAnimManager) from.getClass().getMethod("getAnim").invoke(from);
				HashMap<Integer, AnimGroup> aMap = animManager.getGroupSpriteMap();
				anim = new AbstractAnimManager(aMap);
				anim.setAction(action);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return anim;
	}
	
	public void process() {
		getAnim().process();
		
	}
	
	public ImageSpriteSFF getImage() {
		AnimType anim = this;
		AnimElement animElem = anim.getAnim().getCurrentImageSprite();
		if (animElem.getAirData().getGrpNum() == -1)
			return null;
		try {
			SpriteSFF sff = (SpriteSFF) from.getClass().getMethod("getSpriteSff").invoke(from);
			return sff.getGroupSpr(animElem.getAirData().getGrpNum()).getImgSpr(animElem.getAirData().getImgNum());
			
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
	}
	
	
}
