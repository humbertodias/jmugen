package org.lee.mugen.sprite.entity;

import org.lee.mugen.core.FightEngine;
import org.lee.mugen.core.GameFight;
import org.lee.mugen.renderer.ImageContainer;
import org.lee.mugen.sprite.base.AbstractSprite;
import org.lee.mugen.sprite.baseForParse.ImageSpriteSFF;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.character.SpriteAnimManager;
import org.lee.mugen.stage.Stage;
import org.lee.mugen.util.MugenRandom;

public class ExplodSprite extends AbstractSprite {

	private ExplodSub explod;

	private PointF realPos = new PointF();
	
	@Override
	public int getPriority() {
		return explod.getSprpriority();
	}

	boolean error;

	@Override
	public PointF getPosToDraw() {
		AbstractSprite sprite = this;
		Postype postype = getExplod().getPostype();
		Stage stage = GameFight.getInstance().getStage();
		int _mvX = stage.getCamera().getX();
		int _mvY = stage.getCamera().getY();
		float xCam = _mvX + stage.getCamera().getWidth()/2f;
		int yCam = stage.getStageinfo().getZoffset() + _mvY;
//		realPos = postype.computePos(explod.getSprite(), FightEngine.getNearestEnnemies(explod.getSprite()), explod.getPos(), explod.getFacing());
		if (postype == Postype.p1) {

			float x = sprite.getRealXPos();
			float y = sprite.getRealYPos();
			
			float xOffSet = x;
			float yOffSet = y;
			
			float xScale = getExplod().getScale().getX();
			float yScale= getExplod().getScale().getY();
			
			int facing = getExplod().getFacing();
			
			ImageSpriteSFF imgSprSff = sprite.getCurrentImageSpriteSFF();
			SpriteAnimManager sprAnimMng = sprite.getSprAnimMng();
			ImageContainer currentImage = sprite.getCurrentImage();
			
			int xAxis = 0;
			int yAxis = 0;
			int sprWidth = 0;
			int sprHeight = 0;
			if (imgSprSff != null) {
				xAxis = imgSprSff.getXAxis();
				yAxis = imgSprSff.getYAxis();
				sprWidth = currentImage.getWidth();
				sprHeight = currentImage.getHeight();
			}
			
			float xoffset = sprAnimMng.getCurrentImageSprite().getXOffSet() * xScale;
			int w = (int) (sprWidth * xScale);

			boolean isFilpForRealH = sprAnimMng.getCurrentImageSprite().isMirrorH() ^ facing == -1;

			float xRes = xCam + xOffSet
			- (
					this.isFlip() ^ isFilpForRealH? 
							w - xAxis * getXScale() + (xoffset * (this.isFlip()? -1: 1))
							: xAxis * getXScale() + (xoffset * (this.isFlip()? 1: -1)));
			int h = (int) (sprHeight * yScale);

			float yoffset = sprAnimMng.getCurrentImageSprite().getYOffSet() * yScale;
			boolean isFilpForRealV = sprAnimMng.getCurrentImageSprite().isMirrorV();
			float yRes = yCam + yOffSet - (
						isFilpForRealV?
								h - yAxis * yScale + yoffset
								:yAxis * yScale - yoffset);
			return new PointF(xRes, yRes);
		}
		if (postype == Postype.p2) {
			
			float x = sprite.getRealXPos();
			float y = sprite.getRealYPos();
			
			float xOffSet = x;
			float yOffSet = y;
			
			float xScale = getExplod().getScale().getX();
			float yScale= getExplod().getScale().getY();
			
			int facing = getExplod().getFacing();
			
			ImageSpriteSFF imgSprSff = sprite.getCurrentImageSpriteSFF();
			SpriteAnimManager sprAnimMng = sprite.getSprAnimMng();
			ImageContainer currentImage = sprite.getCurrentImage();
			
			int xAxis = 0;
			int yAxis = 0;
			int sprWidth = 0;
			int sprHeight = 0;
			if (imgSprSff != null) {
				xAxis = imgSprSff.getXAxis();
				yAxis = imgSprSff.getYAxis();
				sprWidth = currentImage.getWidth();
				sprHeight = currentImage.getHeight();
			}
			
			float xoffset = sprAnimMng.getCurrentImageSprite().getXOffSet() * xScale;
			int w = (int) (sprWidth * xScale);

			boolean isFilpForRealH = sprAnimMng.getCurrentImageSprite().isMirrorH() ^ facing == -1;
			float xRes = xCam + xOffSet
			- (
					this.isFlip() ^ isFilpForRealH? 
							w - xAxis * getXScale() + (xoffset * (isFilpForRealH? -1: 1))
							: xAxis * getXScale() + (xoffset * (isFilpForRealH? 1: -1)));
			int h = (int) (sprHeight * yScale);

			float yoffset = sprAnimMng.getCurrentImageSprite().getYOffSet() * yScale;
			boolean isFilpForRealV = sprAnimMng.getCurrentImageSprite().isMirrorV();
			float yRes = yCam + yOffSet - (
						isFilpForRealV?
								h - yAxis * yScale + yoffset
								:yAxis * yScale - yoffset);
			return new PointF(xRes, yRes);
		}
		if (postype == Postype.left) {
			
			float x = sprite.getRealXPos();
			float y = sprite.getRealYPos();
			
			float xOffSet = x;
			float yOffSet = y;
			
			float xScale = getExplod().getScale().getX();
			float yScale= getExplod().getScale().getY();
			
			int facing = getExplod().getFacing();
			
			ImageSpriteSFF imgSprSff = sprite.getCurrentImageSpriteSFF();
			SpriteAnimManager sprAnimMng = sprite.getSprAnimMng();
			ImageContainer currentImage = sprite.getCurrentImage();
			
			int xAxis = 0;
			int yAxis = 0;
			int sprWidth = 0;
			int sprHeight = 0;
			if (imgSprSff != null) {
				xAxis = imgSprSff.getXAxis();
				yAxis = imgSprSff.getYAxis();
				sprWidth = currentImage.getWidth();
				sprHeight = currentImage.getHeight();
			}
			
			float xoffset = sprAnimMng.getCurrentImageSprite().getXOffSet() * xScale;
			int w = (int) (sprWidth * xScale);

			boolean isFilpForRealH = sprAnimMng.getCurrentImageSprite().isMirrorH() ^ facing == -1;
			float xRes = xCam + xOffSet
			- (
					isFilpForRealH? 
							(isFilpForRealH?w:0) - xAxis * getXScale() + (xoffset * (this.isFlip()? -1: 1))
							: xAxis * getXScale() + (xoffset * (this.isFlip()? 1: -1)));
				
			int h = (int) (sprHeight * yScale);

			float yoffset = sprAnimMng.getCurrentImageSprite().getYOffSet() * yScale;
			boolean isFilpForRealV = sprAnimMng.getCurrentImageSprite().isMirrorV();
//			int _mvY = stage.getCamera().getY();
//			int yCam = stage.getStageinfo().getZoffset() + _mvY;
			float yRes = _mvY + yOffSet - (
						isFilpForRealV?
								h - yAxis * yScale + yoffset
								:yAxis * yScale - yoffset);
			return new PointF(xRes, yRes);
		}
		if (postype == Postype.right) {
			
			float x = sprite.getRealXPos();
			float y = sprite.getRealYPos();
			
			float xOffSet = x;
			float yOffSet = y;
			
			float xScale = getExplod().getScale().getX();
			float yScale= getExplod().getScale().getY();
			
			int facing = getExplod().getFacing();
			
			ImageSpriteSFF imgSprSff = sprite.getCurrentImageSpriteSFF();
			SpriteAnimManager sprAnimMng = sprite.getSprAnimMng();
			ImageContainer currentImage = sprite.getCurrentImage();
			
			int xAxis = 0;
			int yAxis = 0;
			int sprWidth = 0;
			int sprHeight = 0;
			if (imgSprSff != null) {
				xAxis = imgSprSff.getXAxis();
				yAxis = imgSprSff.getYAxis();
				sprWidth = currentImage.getWidth();
				sprHeight = currentImage.getHeight();
			}
			
			float xoffset = sprAnimMng.getCurrentImageSprite().getXOffSet() * xScale;
			int w = (int) (sprWidth * xScale);

			boolean isFilpForRealH = sprAnimMng.getCurrentImageSprite().isMirrorH() ^ facing == -1;
			float xRes = xCam + xOffSet
			- (
					isFilpForRealH? 
							w - xAxis * getXScale() + (xoffset * (this.isFlip()? -1: 1))
							: xAxis * getXScale() + (xoffset * (this.isFlip()? 1: -1)));
			
				
			int h = (int) (sprHeight * yScale);

			float yoffset = sprAnimMng.getCurrentImageSprite().getYOffSet() * yScale;
			boolean isFilpForRealV = sprAnimMng.getCurrentImageSprite().isMirrorV();
			float yRes = _mvY  + yOffSet - (
						isFilpForRealV?
								h - yAxis * yScale + yoffset
								:yAxis * yScale - yoffset);
			return new PointF(xRes, yRes);
		}
		
		if (postype == Postype.front || postype == Postype.back) {

			float x = sprite.getRealXPos();
			float y = sprite.getRealYPos();
			
			float xOffSet = x;
			float yOffSet = y;
			
			float xScale = getExplod().getScale().getX();
			float yScale= getExplod().getScale().getY();
			
			int facing = getExplod().getFacing();
			
			ImageSpriteSFF imgSprSff = sprite.getCurrentImageSpriteSFF();
			SpriteAnimManager sprAnimMng = sprite.getSprAnimMng();
			ImageContainer currentImage = sprite.getCurrentImage();
			
			int xAxis = 0;
			int yAxis = 0;
			int sprWidth = 0;
			int sprHeight = 0;
			if (imgSprSff != null) {
				xAxis = imgSprSff.getXAxis();
				yAxis = imgSprSff.getYAxis();
				sprWidth = currentImage.getWidth();
				sprHeight = currentImage.getHeight();
			}
			
			float xoffset = sprAnimMng.getCurrentImageSprite().getXOffSet() * xScale;
			int w = (int) (sprWidth * xScale);

			boolean isFilpForRealH = sprAnimMng.getCurrentImageSprite().isMirrorH() ^ facing == -1;
			if (postype == Postype.front)
				isFilpForRealH = isFilpForRealH ^ this.isFlip();

			float xRes = 0;
			
			
			if (postype == Postype.back) {
				if (this.isFlip())
					isFilpForRealH = !isFilpForRealH;
				xRes = xCam + xOffSet - (
								isFilpForRealH? 
										w - xAxis * xScale - xoffset
										: xAxis * xScale - xoffset);
//				xRes = xCam + xOffSet
//				- (
//						isFilpForRealH? 
//								(isFilpForRealH?w:0) - xAxis * getXScale() + (xoffset * (this.isFlip()? -1: 1))
//								: (isFilpForRealH?w:0) - xAxis * getXScale() + (xoffset * (this.isFlip()? 1: -1)));
				
			} else if (postype == Postype.front) {
				xRes = xCam + xOffSet - (
						isFilpForRealH? 
								xAxis * xScale - xoffset
								: xAxis * xScale - xoffset);
			}
			int h = (int) (sprHeight * yScale);

			float yoffset = sprAnimMng.getCurrentImageSprite().getYOffSet() * yScale;
			
			boolean isFilpForRealV = sprAnimMng.getCurrentImageSprite().isMirrorV();

			
			float yRes = _mvY + yOffSet - (
			isFilpForRealV?
					h - yAxis * yScale + yoffset
					:yAxis * yScale - yoffset);

//			float yRes = _mvY - yOffSet - (
//					isFilpForRealV?
//							h - yAxis * yScale + yoffset
//							:yAxis * yScale - yoffset);
			return new PointF(xRes, yRes);
		}
		throw new IllegalArgumentException();
	}
	private boolean forceRemove;

	
	@Override
	public float getXScale() {
		return getExplod().getScale().getX();
	}
	
	@Override
	public float getYScale() {
		return getExplod().getScale().getY();
	}
	
	@Override
	public void process() {
		if (forceRemove)
			return;
		this.sprAnimMng.process();
		if (explod.getBindtime() > 0 || explod.getBindtime() == -1) {
			Postype postype = explod.getPostype();
			realPos = postype.computePos(explod.getSprite(), FightEngine.getNearestEnnemies(explod.getSprite()), explod.getPos(), explod.getFacing());
			isFlipH = explod.getSprite().isFlip();
			if (explod.getPostype() == Postype.p2) {
				Sprite s = FightEngine.getNearestEnnemies(explod.getSprite());
				isFlipH = s.isFlip();
			}
		}

		int mulFace = isFlipH? -1: 1;
		explod.getVel().addX(explod.getAccel().getX());
		explod.getVel().addY(explod.getAccel().getY());
		
		realPos.addX(explod.getVel().getX() * mulFace);
		realPos.addY(explod.getVel().getY());
		
		if (explod.getBindtime() > 0 && explod.getBindtime() != -1) 
			explod.decreaseBindtime();
		
		if (explod.getRemovetime() > 0) {
			explod.decreaseRemovetime();
		}
		explod.decreaseSuperPauseMoveTime();
		explod.decreasePauseMoveTime();
	}

	@Override
	public boolean remove() {
		if (!GameFight.getInstance().getOtherSprites().contains(this))
			return true;
		if (forceRemove)
			return true;
		try {
			if (explod.isRemoveongethit()) {
				if (explod.getSprite().getInfo().getLastHitdef() != null) {
					if (explod.getSprite().getInfo().getLastHitdef().getLastTimeHitSomething() == GameFight.getInstance().getGameState().getGameTime())
					return true;
				}
			}
			if (explod.isSupermove() && GameFight.getInstance().getGlobalEvents().isSuperPause()
					&& explod.getRemovetime() != -2) {
				return false;
			}

			if (explod.getRemovetime() == -1) {
				return error || forceRemove;// || !StateMachine.getInstance().getGlobalEvents().isSuperPause();
			} else if (explod.getRemovetime() == -2) {
				if (this.sprAnimMng.getCurrentGroupSprite() == null)
					return true;
				return error 
					// cas ou l'explod est creer par un helper et que le helper a ete detruit
//					|| StateMachine.getInstance().getSpriteInstance(explod.getSprite().getSpriteId()) == null 
					|| (this.sprAnimMng.getAnimTime() == 0);
//					|| this.sprAnimMng.getCurrentImageSprite().getDelay() == -1;
				
			} else if (explod.getRemovetime() == 0) {
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	private Integer stateWhenExplod;
	private boolean isFlipH;
	public ExplodSprite(ExplodSub explod) {
		stateWhenExplod = explod.getSprite().getSpriteState().getCurrentState().getIntId();
		this.explod = explod;

		final SpriteAnimManager spriteAnimManager = new SpriteAnimManager(
				"Explod" + explod.hashCode());

		if (explod.getAnim() != null && explod.getAnim().isSpriteUse()) {
			spriteAnimManager.setGroupSpriteMap(explod.getSprite().getSprAnimMng()
					.getGroupSpriteMap());
			this.spriteSFF = explod.getSprite().getSpriteSFF();

			spriteAnimManager.setAction(explod.getAnim().getAction());
		} else if (explod.getAnim() != null) {
			spriteAnimManager.setGroupSpriteMap(getMugenFightfx().getAir()
					.getGroupSpriteMap());
			this.spriteSFF = getMugenFightfx().getSff();

			spriteAnimManager.setAction(explod.getAnim().getAction());

		} else {
			this.spriteSFF = null;
			System.err.println("pas de hit spark");
		}
		this.sprAnimMng = spriteAnimManager;

		isFlipH = explod.getSprite().isFlip();
		if (explod.getPostype() == Postype.p2) {
			Sprite s = FightEngine.getNearestEnnemies(explod.getSprite());
			isFlipH = s.isFlip();
		}
		if (getExplod().getFacing() == -1) {
			isFlipH = !isFlipH;
		}
		
		// /
		Postype postype = explod.getPostype();
		PointF result = new PointF(explod.getPos());
		if (explod.getRandom().getX() != 0) {
			result.addX(MugenRandom.getRandomNumber(0, (int) explod.getRandom().getX()) - (explod.getRandom().getX()/2));
		}
		if (explod.getRandom().getY() != 0) {
			result.addY(MugenRandom.getRandomNumber(0, (int) explod.getRandom().getY()) - (explod.getRandom().getY()/2));
		}
		
		realPos = postype.computePos(explod.getSprite(), FightEngine.getNearestEnnemies(explod.getSprite()), result, explod.getFacing());

	}

	@Override
	public float getRealXPos() {
		return realPos.getX(); // Not need for drawing an explod
	}

	@Override
	public float getRealYPos() {
		return realPos.getY(); // Not need for drawing an explod
	}

	@Override
	public boolean isFlip() {
		return isFlipH;
	}

	public boolean isForceRemove() {
		return forceRemove;
	}

	public void setForceRemove(boolean forceRemove) {
		this.forceRemove = forceRemove;
	}

	public ExplodSub getExplod() {
		return explod;
	}

	public void setExplod(ExplodSub explod) {
		this.explod = explod;
	}
	boolean process = true;
	public boolean isProcess() {
		return process;
	}

	public void setProcess(boolean b) {
		process = b;
		
	}

}
