package org.lee.mugen.sprite.base;

import java.awt.Rectangle;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.fight.section.Fightfx;
import org.lee.mugen.renderer.ImageContainer;
import org.lee.mugen.renderer.PalFxSub;
import org.lee.mugen.sprite.baseForParse.GroupSpriteSFF;
import org.lee.mugen.sprite.baseForParse.ImageSpriteSFF;
import org.lee.mugen.sprite.baseForParse.SpriteSFF;
import org.lee.mugen.sprite.character.AnimElement;
import org.lee.mugen.sprite.character.SpriteAnimManager;
import org.lee.mugen.sprite.entity.PointF;

public abstract class AbstractSprite implements Serializable {
	public static Fightfx getMugenFightfx() {
		return GameFight.getInstance().getFightdef().getFiles().getFightfx();
	}
	public static List<Rectangle> replaceCns(AbstractSprite spr, int xAxis,
			int yAxis, org.lee.mugen.object.Rectangle[] rects) {

		boolean isFlip = spr.isFlip();

		PointF pos = spr.getPosToDraw();
		boolean isMirrorH = spr.getSprAnimMng().getCurrentImageSprite()
				.isMirrorH();
		List<Rectangle> result = new ArrayList<Rectangle>();
		int sprWidth = 0;
		if (spr.getCurrentImage() != null) {
			sprWidth = spr.getCurrentImage().getWidth();
		}
		float xoffset = spr.getSprAnimMng().getCurrentImageSprite()
				.getXOffSet() * spr.getXScale();
		for (org.lee.mugen.object.Rectangle r : rects) {

			float xTopLeft = (Math.min((isFlip ? -1 : 1) * r.getX1(),
					(isFlip ? -1 : 1) * r.getX2()));
			float yTopLeft = (Math.min(r.getY1(), r.getY2()));
			float xTopRight = (Math.max((isFlip ? -1 : 1) * r.getX1(),
					(isFlip ? -1 : 1) * r.getX2()));
			float yTopRight = (Math.max(r.getY1(), r.getY2()));
			float width = Math.abs(xTopLeft) + Math.abs(xTopRight);
			width = width * spr.getXScale();
			
			if (xTopLeft < 0) {

				if (xTopRight < 0) {
					width = Math.abs(xTopRight - xTopLeft);
				} else {
					width = Math.abs(xTopLeft) + Math.abs(xTopRight);
				}
			} else {
				width = xTopRight - xTopLeft;
			}
			width = width * spr.getXScale();
			float height = Math.abs(yTopLeft - yTopRight);
			height = height * spr.getYScale();
			java.awt.Rectangle jr = new java.awt.Rectangle(
					Math.round(xTopLeft), Math.round(yTopLeft), Math
							.round(width), Math.round(height));
			

			
			jr.y = Math.round(pos.getY() + yTopLeft* spr.getYScale() + yAxis* spr.getYScale());
			jr.x = Math.round(pos.getX()
					+ xTopLeft* spr.getXScale()
					+ (isMirrorH ^ isFlip ? sprWidth * spr.getXScale()
							- xAxis* spr.getXScale() + xoffset : xAxis* spr.getXScale() - xoffset));
			result.add(jr);

		}
		return result;

	}
	protected long linearTime = 0;

	protected PalFxSub palfx = new PalFxSub();

	protected int pause = 0;

	protected int priority;

	// /////////////////////:
	protected AbstractAnimManager sprAnimMng;

	protected transient SpriteSFF spriteSFF;

	protected List<Rectangle> getCns(org.lee.mugen.object.Rectangle[] rects) {
		ImageSpriteSFF img = getCurrentImageSpriteSFF();

		int xAxis = img == null? 0: img.getXAxis();
		int yAxis = img == null? 0: img.getYAxis();
		return AbstractSprite.replaceCns(this, xAxis, yAxis, rects);
	}

	public List<Rectangle> getCns1() {
		AnimElement imgSpr = getSprAnimMng().getCurrentImageSprite();
		if (imgSpr == null)
			return Collections.EMPTY_LIST;

		return getCns(imgSpr.getAtacksRec());

	}

	public List<Rectangle> getCns2() {
		AnimElement imgSpr = getSprAnimMng().getCurrentImageSprite();
		return getCns(imgSpr.getCollisionsRec());

	}

	public ImageContainer getCurrentImage() {
		AnimElement ae = getSprAnimMng().getCurrentImageSprite();
		if (ae == null)
			return null;
		GroupSpriteSFF grpSpriteSff = getSpriteSFF().getGroupSpr(
				ae.getAirData().grpNum);
		if (grpSpriteSff == null)
			return null;
		ImageSpriteSFF imgSprSff = grpSpriteSff.getImgSpr(getSprAnimMng()
				.getCurrentImageSprite().getAirData().imgNum);
		if (imgSprSff == null)
			return null;

		ImageContainer img = imgSprSff.getImage();

		return img;
	}

	public ImageSpriteSFF getCurrentImageSpriteSFF() {
		if (getSprAnimMng().getCurrentImageSprite() == null)
			return null;
		GroupSpriteSFF grpSpriteSff = getSpriteSFF().getGroupSpr(getSprAnimMng().getCurrentImageSprite()
				.getAirData().grpNum);
		if (grpSpriteSff == null)
			return null;

		ImageSpriteSFF imgSprSff = grpSpriteSff.getImgSpr(getSprAnimMng()
				.getCurrentImageSprite().getAirData().imgNum);
		if (imgSprSff == null)
			return null;

		return imgSprSff;
	}

	public int getHeight() {
		return getCurrentImage().getHeight();
	}

	public long getLinearTime() {
		return linearTime;
	}

	public PalFxSub getPalfx() {
		return palfx;
	}

	public void setPalfx(PalFxSub palfx) {
		this.palfx = palfx;
	}

	public int getPause() {
		return pause;
	}

	public float getXScale() {
		return 1f;
	}
	public float getYScale() {
		return 1f;
	}
	
	public PointF getPosToDraw() {
		// TODO SCALE if

		float xRealPos = getRealXPos();
		float yRealPos = getRealYPos();

		ImageSpriteSFF imgSprSff = getCurrentImageSpriteSFF();

		int xAxis = 0;
		int yAxis = 0;
		int sprWidth = 0;
		int sprHeight = 0;
		if (imgSprSff != null) {
			xAxis = imgSprSff.getXAxis();
			yAxis = imgSprSff.getYAxis();
			sprWidth = getCurrentImage().getWidth();
			sprHeight = getCurrentImage().getHeight();
		}
		
		float xoffset = getSprAnimMng().getCurrentImageSprite().getXOffSet() * getXScale();
		int w = (int) (sprWidth * getXScale());

		boolean isFilpForRealH = getSprAnimMng().getCurrentImageSprite()
				.isMirrorH();

		float xRes = xRealPos
				- (
						isFlip() ^ isFilpForRealH? 
								w - xAxis * getXScale() + (xoffset * (isFilpForRealH? -1: 1))
								: xAxis * getXScale() + (xoffset * (isFilpForRealH? 1: -1)));

		AnimElement ae = getSprAnimMng().getCurrentImageSprite();

		int h = (int) (sprHeight * getYScale());

		float yOffset = ae.getYOffSet()  * getYScale();
		float Yaxis = yAxis * getYScale();
		float yPos = yRealPos;
		float yDiff = (yPos - yOffset - Yaxis);
		float yoffset = getSprAnimMng().getCurrentImageSprite().getYOffSet() * getYScale();
		
		boolean isFilpForRealV = getSprAnimMng().getCurrentImageSprite().isMirrorV();

		
		float yRes = yRealPos - (
					isFilpForRealV?
							h - yAxis * getYScale() + (yoffset * (isFilpForRealV? -1: 1))
							:yAxis * getYScale() + (yoffset * (isFilpForRealV? 1: -1)));


		PointF posToDraw = new PointF(xRes, yRes);
		return posToDraw;
	}

	public int getPriority() {
		return priority;
	}

	public abstract float getRealXPos();

	public abstract float getRealYPos();

	public SpriteAnimManager getSprAnimMng() {
		return (SpriteAnimManager) sprAnimMng;
	}

	public PointF getSpriteRealPos() {
		return new PointF(getRealXPos(), getRealYPos());
	}

	// ///////////////
	public SpriteSFF getSpriteSFF() {
		return spriteSFF;
	}



	public abstract boolean isFlip();

	public boolean isPause() {
		return pause > 0;
	}

	public abstract void process();

	public void processPause() {
		pause--;
	}

	public boolean remove() {
		return false;
	}

	public void setPause(int pause) {
		this.pause = pause;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public void setSprAnimMng(SpriteAnimManager sprAnimMng) {
		this.sprAnimMng = sprAnimMng;
	}

	// ///////////////

	public void setSpriteSFF(SpriteSFF spriteSFF) {
		this.spriteSFF = spriteSFF;
	}
	private boolean debugRender = true;
	public boolean isDebugRender() {
		return debugRender;
	}

	public void setDebugRender(boolean debugRender) {
		this.debugRender = debugRender;
	}
}
