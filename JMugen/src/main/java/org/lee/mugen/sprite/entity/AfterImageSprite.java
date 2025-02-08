package org.lee.mugen.sprite.entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.renderer.ImageContainer;
import org.lee.mugen.renderer.RGB;
import org.lee.mugen.renderer.Trans;
import org.lee.mugen.sprite.base.AbstractSprite;
import org.lee.mugen.sprite.baseForParse.GroupSpriteSFF;
import org.lee.mugen.sprite.baseForParse.ImageSpriteSFF;
import org.lee.mugen.sprite.character.AnimElement;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.stage.Stage;


public class AfterImageSprite extends AbstractSprite {
	
	public static class InfoAfterImage {
		InfoAfterImage() {

		}
		private int time;
		private int totalTime;
		private boolean isFlip;
		private int x;
		private int y;
		private AnimElement animElement;
		
		public AnimElement getAnimElement() {
			return animElement;
		}
		public void setAnimElement(AnimElement animElement) {
			this.animElement = animElement;
		}
		public boolean isFlip() {
			return isFlip;
		}
		public void setFlip(boolean isFlip) {
			this.isFlip = isFlip;
		}
		
		public int getXToDraw() {
			Stage stage = GameFight.getInstance().getStage();
			int _mvX = stage.getCamera().getX();
			return x + stage.getCamera().getWidth()/2 + _mvX;
		}
		
		public int getYToDraw() {
			Stage stage = GameFight.getInstance().getStage();
			int _mvY = stage.getCamera().getY();
			return y + stage.getStageinfo().getZoffset() + _mvY;
		}
		
		public int getX() {
			return x;
		}
		public void setX(int x) {
			this.x = x;
		}
		public int getY() {
			return y;
		}
		public void setY(int y) {
			this.y = y;
		}
		public void setTotalTime(int totalTime) {
			this.totalTime = totalTime;
		}
		public int getTime() {
			return time;
		}
		public int decreaseTime() {
			return time--;
		}
		public void setTime(int time) {
			this.time = time;
			totalTime = time;
		}
		public int getTotalTime() {
			return totalTime;
		}
	}
	
	protected Stack<InfoAfterImage> animElementStack = new Stack<InfoAfterImage>();
	private Sprite sprite;
	private String statedef = null;
	private long count = 0;
	private long incForMod = 0;
	
	
	
	
	public Stack<InfoAfterImage> getAnimElementStack() {
		return animElementStack;
	}
	public long getCount() {
		return count;
	}
	public AfterImageSprite(Sprite sprite) {
		this.sprite = sprite;
		statedef = sprite.getSpriteState().getCurrentState().getId();
		
	}
	public Sprite getSprite() {
		return sprite;
	}

	public void setPriority(int p) {
		// TODO Auto-generated method stub
		
	}
	public int getPriority() {
		return getSprite().getInfo().getSprpriority() - 1;
	}

	public String getStatedef() {
		return statedef;
	}
	public void setStatedef(String statedef) {
		this.statedef = statedef;
	}
	public void setNewTimeForAll(int time) {
//		for (InfoAfterImage ia: animElementStack) {
//			ia.setTime(time);
//		}
		this.time = time;
	}
	public void rezetCount() {
//		time += count;
		count = 0;
	}
	
	
	
	@Override
	public float getRealXPos() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public float getRealYPos() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public boolean isFlip() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	
	
	
	//////////////////////////////
	
	public void process() {

		AnimElement animElement = sprite.getSprAnimMng().getCurrentImageSprite();

		
		if (canAdd()) {
			if (animElementStack.size() >= length - 1)
				animElementStack.remove(animElementStack.firstElement());
			if (animElementStack.size() >= length && incForMod%timegap == 0 && incForMod%framegap == 0) {
				animElementStack.remove(0);
			}
			if ((animElementStack.size() == 0) || (
					animElementStack.size() < length && incForMod%timegap%framegap == 0 
//					&& 
//					(animElementStack.size() > 0 && animElementStack.lastElement().animElement != animElement)
					)) {
//				count = 0;
				boolean add = true;
				PointF posToDraw = sprite.getPosToDraw();
				if (animElementStack.size() > 0) {
					InfoAfterImage lastInfo = animElementStack.lastElement();
					if (lastInfo.animElement == animElement && lastInfo.x == posToDraw.getX() && lastInfo.y == posToDraw.getY()) {
						add = false;
					} else {
						
					}
				} 
				if (add) {
					InfoAfterImage info = new InfoAfterImage();
					
					
					info.x = (int)(posToDraw.getX());
					info.y = (int) (posToDraw.getY());
					info.isFlip = sprite.isFlip();
					info.animElement = animElement;
					info.setTime(20);// * animElement.getAirData().delayTick;
					animElementStack.add(info);
					
				}
			}
		}
		Iterator<InfoAfterImage> iterAnimElem = animElementStack.iterator();
		List<InfoAfterImage> toRemove = new ArrayList<InfoAfterImage>();
		while (iterAnimElem.hasNext()) {
			InfoAfterImage info = iterAnimElem.next();

			if (info.getTotalTime() == 0) {
				toRemove.add(info);
//				continue;
			}
			///
			
			if (!(sprite.isPause() || !GameFight.getInstance().getGlobalEvents().canGameProcessWithSuperPause(sprite)))
				info.decreaseTime();
			if (info.getTime() < 0) {
				toRemove.add(info);
			}
		}
		
		animElementStack.removeAll(toRemove);
		if (!(sprite.isPause() || !GameFight.getInstance().getGlobalEvents().canGameProcessWithSuperPause(sprite))) {
			count++;
//			time--;
			incForMod++;
		}
	}
	
	public ImageContainer getImageContainerForInfo(InfoAfterImage info) {
		GroupSpriteSFF grp = sprite.getSpriteSFF().getGroupSpr(info.animElement.getAirData().grpNum);
		if (grp == null)
			return null;
		ImageSpriteSFF imgSprSff = grp.getImgSpr(info.animElement.getAirData().imgNum);
		if (imgSprSff == null)
			return null;
		ImageContainer img = imgSprSff.getImage();
		return img;
	}
	
	private boolean canAdd() {
		return ((!sprite.isPause() && GameFight.getInstance().getGlobalEvents().canGameProcessWithSuperPause(sprite)))
		 && (incForMod%timegap == 0 && incForMod%framegap == 0) && count < time;
	}
	public boolean remove() {
		return( count > time) &&
			(
//				!statedef.equals(sprite.getSpriteState().getCurrentState().getId())
//				&& 
				animElementStack.size() == 0
			) || remove;
				
	}
	//////////////////////////////
	
	private int time = 2; //= duration (int)
	private int length = 10; //= no_of_frames (int)

	
	// TODO : Afterimage's paladd palmul framegap trans
	private int palcolor = 256; //= col (int) 
	private int palinvertall; //= invertall (bool)
	private RGB palbright = new RGB(30, 30, 30); //= add_r, add_g, add_b (int)

	private RGB palcontrast = new RGB(120,120,220); //= mul_r,mul_g, mul_b (int)
	private RGB palpostbright = new RGB(); //= add2_r,add2_g, add2_b (int)


	private RGB paladd = new RGB(10, 10, 25); //= add_r, add_g, add_b (int)
	private RGB palmul = new RGB(0.65f, 0.65f, 0.75f); //= mul_r,mul_g, mul_b (float)


	private int timegap = 1; //= value (int)
	private int framegap = 1; //= value (int)
	private Trans trans = Trans.ADD; //= type (string)
	
	private boolean remove = false;
	
	public boolean isRemove() {
		return remove();
	}
	public void setRemove(boolean remove) {
		this.remove = remove;
	}
	public int getFramegap() {
		return framegap;
	}
	public void setFramegap(int framegap) {
		this.framegap = framegap;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public RGB getPaladd() {
		return paladd;
	}
	public void setPaladd(RGB paladd) {
		this.paladd = paladd;
	}
	public RGB getPalbright() {
		return palbright;
	}
	public void setPalbright(RGB palbright) {
		this.palbright = palbright;
	}
	public int getPalcolor() {
		return palcolor;
	}
	public void setPalcolor(int palcolor) {
		this.palcolor = palcolor;
	}
	public RGB getPalcontrast() {
		return palcontrast;
	}
	public void setPalcontrast(RGB palcontrast) {
		this.palcontrast = palcontrast;
	}
	public int getPalinvertall() {
		return palinvertall;
	}
	public void setPalinvertall(int palinvertall) {
		this.palinvertall = palinvertall;
	}
	public RGB getPalmul() {
		return palmul;
	}
	public void setPalmul(RGB palmul) {
		this.palmul = palmul;
	}
	public RGB getPalpostbright() {
		return palpostbright;
	}
	public void setPalpostbright(RGB palpostbright) {
		this.palpostbright = palpostbright;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public int getTimegap() {
		return timegap;
	}
	public void setTimegap(int timegap) {
		this.timegap = timegap;
	}
	public Trans getTrans() {
		return trans;
	}
	public void setTrans(Trans trans) {
		this.trans = trans;
	}

	
	
}
