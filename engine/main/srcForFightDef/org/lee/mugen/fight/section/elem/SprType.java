package org.lee.mugen.fight.section.elem;

import org.lee.mugen.sprite.baseForParse.ImageSpriteSFF;
import org.lee.mugen.sprite.baseForParse.SpriteSFF;
import org.lee.mugen.util.BeanTools;

public class SprType extends CommonType {
	int spritegrp;
	int spriteno;
	Object root;
	
	public SprType(int spritegrp, int spriteno, Object root) {
		this.spritegrp = spritegrp;
		this.spriteno = spriteno;
		this.root = root;
	}

	public SprType(Object root) {
		this.root = root;
	}

	public int getSpritegrp() {
		return spritegrp;
	}

	public void setSpritegrp(int spritegrp) {
		this.spritegrp = spritegrp;
	}

	public int getSpriteno() {
		return spriteno;
	}

	public void setSpriteno(int spriteno) {
		this.spriteno = spriteno;
	}

	@Override
	public void parse(String name, String value) {
		if (name.equals("spr")) {
			int[] res = (int[]) BeanTools.getConvertersMap().get(int[].class).convert(value);
			spritegrp = res[0];
			spriteno = res[1];
			
		}
		
	}
	
	public ImageSpriteSFF getImage() {
		SprType spr = this;
		try {
			SpriteSFF sff = (SpriteSFF) root.getClass().getMethod("getSpriteSff").invoke(root);
			if (sff.getGroupSpr(spr.getSpritegrp()) == null 
					|| sff.getGroupSpr(spr.getSpritegrp()).getImgSpr(spr.getSpriteno()) == null)
				return null;
			return sff.getGroupSpr(spr.getSpritegrp()).getImgSpr(spr.getSpriteno());
			
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}

		
	}
}
