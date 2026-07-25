package org.lee.mugen.sprite.entity;

import java.io.Serializable;

import org.lee.mugen.sprite.parser.Parser;

public class SndGrpNum implements Serializable {
	private boolean isPlaySpriteSnd = false;
	private int snd_grp = -1;
	private int snd_item;
	public boolean isPlaySpriteSnd() {
		return isPlaySpriteSnd;
	}
	public void setPlaySpriteSnd(boolean isPlaySpriteSnd) {
		this.isPlaySpriteSnd = isPlaySpriteSnd;
	}
	public int getSnd_item() {
		return snd_item;
	}
	public void setSnd_item(int snd_item) {
		this.snd_item = snd_item;
	}
	public int getSnd_grp() {
		return snd_grp;
	}
	public void setSnd_grp(int snd_grp) {
		this.snd_grp = snd_grp;
	}
	public static void setSound(SndGrpNum hitsound, Object...params) {
		if (params.length > 3) {
			throw new IllegalArgumentException("Set HiDef.XXXsound take 2 parameters");
		}
		if (params.length > 0) {
			Integer snd_grp = null;
			if (params[0] instanceof Number) {
				snd_grp = Parser.getIntValue(params[0]);
			} else {
				String res = params[0].toString();
				if (res.toLowerCase().startsWith("s")) {
					hitsound.setPlaySpriteSnd(true);
					res = res.substring(1);
				}
				snd_grp = new Integer(res.toString());
			}
			hitsound.setSnd_grp(snd_grp);
			if (snd_grp == -1)
				return;
		}
		if (params.length > 1) {
			Integer snd_item = params[1] instanceof Number? Parser.getIntValue(params[1]): new Integer(params[1].toString());
			hitsound.setSnd_item(snd_item);
		}
		if (params.length == 3) {
			Boolean isSprSound = params[2] == null? false: (Boolean)params[2];
			hitsound.setPlaySpriteSnd(isSprSound);
		}
	}
}