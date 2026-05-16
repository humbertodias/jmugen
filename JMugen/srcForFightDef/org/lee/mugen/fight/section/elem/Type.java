package org.lee.mugen.fight.section.elem;

import org.lee.mugen.geom.MugenPoint;

import org.lee.mugen.fight.section.elem.FontType.ALIGNMT;
import org.lee.mugen.sprite.entity.PointF;
import org.lee.mugen.util.BeanTools;

public class Type implements Cloneable {
	
	public static String getNext(String name) {
		return name.substring(name.indexOf(".") + 1);
	}
	public void init() {
		displaytime = originalDisplaytime;
		sndtime = originalSndtime;
		starttime = originalStarttime;
	}
	public CommonType getType() {
		return type;
	}

	CommonType type;
	MugenPoint offset = new MugenPoint();
	MugenPoint pos = new MugenPoint();
	
	@Override
	public Object clone() {
		try {
			Type clone = (Type) super.clone();
			if (type != null)
				clone.type = (CommonType) type.clone();
			if (offset != null)
				clone.offset = (MugenPoint) offset.clone();
			if (pos != null)
				clone.pos = (MugenPoint) pos.clone();
			if (snd != null)
				clone.snd = (SndType) snd.clone();
			if (scale != null)
				clone.scale = (PointF) scale.clone();
			return clone;
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new IllegalStateException(e);
		}
	}
	
	int displaytime = -1;
	int originalDisplaytime = -1;
	int facing = 1;
	int vfacing = 1;
	SndType snd;
	int sndtime;
	int originalSndtime;
	int layerno = 0;
	PointF scale = new PointF(1, 1);
	int starttime = 0;
	int originalStarttime = 0;
	float alpha = 1f;
	
	

	public int getStarttime() {
		return starttime;
	}
	public void setStarttime(int starttime) {
		this.starttime = starttime;
	}
	public void decreaseDisplayTime() {
		if (displaytime >= 0)
			displaytime--;
	}
	
	
	public MugenPoint getOffset() {
		return offset;
	}

	public void setOffset(MugenPoint offset) {
		this.offset = offset;
	}

	public int getFacing() {
		return facing;
	}

	public void setFacing(int facing) {
		this.facing = facing;
	}

	public int getVfacing() {
		return vfacing;
	}

	public void setVfacing(int vfacing) {
		this.vfacing = vfacing;
	}

	public int getLayerno() {
		return layerno;
	}

	public void setLayerno(int layerno) {
		this.layerno = layerno;
	}

	public PointF getScale() {
		return scale;
	}

	public void setScale(PointF scale) {
		this.scale = scale;
	}

	public int getSndtime() {
		return sndtime;
	}

	public void setSndtime(int sndtime) {
		this.sndtime = sndtime;
	}

	public SndType getSnd() {
		return snd;
	}

	public void setSnd(SndType snd) {
		this.snd = snd;
	}



	public int getDisplaytime() {
		return displaytime;
	}

	public void setDisplaytime(int displaytime) {
		this.displaytime = displaytime;
	}

	public void parse(String name, String value) {
		if (getType() != null) {
			getType().parse(name, value);
		}
		if (name.equalsIgnoreCase("pos")) {
			pos = (MugenPoint) BeanTools.getConvertersMap().get(MugenPoint.class).convert(value);
		} else if (name.equalsIgnoreCase("offset")) {
			offset = (MugenPoint) BeanTools.getConvertersMap().get(MugenPoint.class).convert(value);
		} else if (name.equalsIgnoreCase("starttime")) {
			setStarttime((Integer) BeanTools.getConvertersMap().get(Integer.class).convert(value));
			originalStarttime = getStarttime();
		} else if (name.equalsIgnoreCase("displaytime")) {
			setDisplaytime((Integer) BeanTools.getConvertersMap().get(Integer.class).convert(value));
			originalDisplaytime = getDisplaytime();
		} else if (name.equalsIgnoreCase("facing")) {
			facing = (Integer) BeanTools.getConvertersMap().get(Integer.class).convert(value);
		} else if (name.equalsIgnoreCase("vfacing")) {
			vfacing = (Integer) BeanTools.getConvertersMap().get(Integer.class).convert(value);
		} else if (name.equalsIgnoreCase("snd")) {
			int[] sndGrpNum = (int[]) BeanTools.getConvertersMap().get(int[].class).convert(value);
			snd = new SndType();
			snd.setGrp(sndGrpNum[0]);
			snd.setNum(sndGrpNum[1]);
		} else if (name.equalsIgnoreCase("sndtime")) {
			sndtime = (Integer) BeanTools.getConvertersMap().get(Integer.class).convert(value);
			originalSndtime = sndtime;
		} else if (name.equalsIgnoreCase("layerno")) {
			layerno = (Integer) BeanTools.getConvertersMap().get(Integer.class).convert(value);
		} else if (name.equalsIgnoreCase("scale")) {
			scale = (PointF) BeanTools.getConvertersMap().get(PointF.class).convert(value);
		} else if (name.equalsIgnoreCase("alpha")) {
			alpha = (Float) BeanTools.getConvertersMap().get(Float.class).convert(value);
		}

	}


	public MugenPoint getPos() {
		return pos;
	}
	public void setPos(MugenPoint pos) {
		this.pos = pos;
	}
	public float getAlpha() {
		return alpha;
	}
	public int getOriginalDisplaytime() {
		return originalDisplaytime;
	}
	public int getOriginalSndtime() {
		return originalSndtime;
	}
	public int getOriginalStarttime() {
		return originalStarttime;
	}
	public void setType(String name, Type elem, String value, Object root) {

		if (name.equalsIgnoreCase("anim") ) {
			AnimType e = new AnimType(root);
			e.setAction(Integer.parseInt(value));
			type = e;
		} else if (name.equalsIgnoreCase("spr")) {
			SprType e = new SprType(root);
			int[] grpNo = (int[]) BeanTools.getConvertersMap().get(int[].class).convert(value);
			e.setSpritegrp(grpNo[0]);
			e.setSpriteno(grpNo[1]);
			type = e;
			
		} else if (name.equalsIgnoreCase("font")) {
			FontType e = new FontType(root);
			int[] nba = (int[]) BeanTools.getConvertersMap().get(int[].class).convert(value);
			int fontno = nba[0];
			e.setFontno(fontno);
			if (nba.length > 1) {
				int fontbank = nba[1];
				e.setFontbank(fontbank);
			}
			if (nba.length > 2) {
				int alignmt = nba[2];
				e.setAlignmt(ALIGNMT.getValue(alignmt));
			} else {
				e.setAlignmt(ALIGNMT.center);
			}
			type = e;
		}		
							
	}
	public void process() {
		getType().process();
	}


}
