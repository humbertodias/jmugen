package org.lee.mugen.sprite.entity;

import java.awt.Point;
import java.io.Serializable;

import org.lee.mugen.sprite.character.Sprite;

public class SuperpauseSub implements Serializable {
	public static final int DARKEN = 1;
	public static final int INVERT = 2;
	public static final int BLUR = 3;

//	private static InvertFilter iFilter = new InvertFilter();
//	private static ContrastFilter cFilter = new ContrastFilter();
//	static {
//		cFilter.setBrightness(0.3f);
//		cFilter.setContrast(1);
//	}
//	private static BlurFilter bFilter = new BlurFilter();
//
//	
//	private static final Map<Integer, AbstractBufferedImageOp> FILTERMAP = new HashMap<Integer, AbstractBufferedImageOp>();
//	static {
//		FILTERMAP.put(DARKEN, cFilter);
//		FILTERMAP.put(INVERT, iFilter);
//		FILTERMAP.put(BLUR, bFilter);
//	}
//	public AbstractBufferedImageOp getFilter(int type) {
//		return FILTERMAP.get(type);
//	}
	
	public boolean isSuperpauseMoveTime() {
		return getMovetime() > 0;
	}
	private Sprite sprite;
	private int time;
	private Sparkno anim;
	private boolean isUseSpriteAnim;
	private SndGrpNum sound;
	private Point pos;
	private int movetime;
	private int[] darken;
	private float p2defmul;
	private int poweradd;
	private int unhittable;

	
	public int[] getDarken() {
		return darken;
	}
	public void setDarken(int[] darken) {
		this.darken = darken;
	}
	public boolean isUseSpriteAnim() {
		return isUseSpriteAnim;
	}
	public void setUseSpriteAnim(boolean isUseSpriteAnim) {
		this.isUseSpriteAnim = isUseSpriteAnim;
	}
	public int getMovetime() {
		return movetime;
	}
	public void setMovetime(int movetime) {
		this.movetime = movetime;
	}
	public float getP2defmul() {
		return p2defmul;
	}
	public void setP2defmul(float p2defmul) {
		this.p2defmul = p2defmul;
	}
	public Point getPos() {
		return pos;
	}
	public void setPos(Point pos) {
		this.pos = pos;
	}
	public int getPoweradd() {
		return poweradd;
	}
	public void setPoweradd(int poweradd) {
		this.poweradd = poweradd;
	}
	public SndGrpNum getSound() {
		return sound;
	}
	public void setSound(SndGrpNum sound) {
		this.sound = sound;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public int getUnhittable() {
		return unhittable;
	}
	public void setUnhittable(int unhittable) {
		this.unhittable = unhittable;
	}
	public Sparkno getAnim() {
		return anim;
	}
	public void setAnim(Sparkno anim) {
		this.anim = anim;
	}
	public void decreaseTime() {
		if (time >= 0)
			time--;
		if (movetime >= 0)
			movetime--;
		if (time < movetime)
			movetime = time;

	}
	public Sprite getSprite() {
		return sprite;
	}
	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}
	
}
