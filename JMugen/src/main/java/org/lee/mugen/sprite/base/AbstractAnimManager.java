package org.lee.mugen.sprite.base;

import java.io.Serializable;
import java.util.HashMap;

import org.lee.mugen.parser.air.AirGroup;
import org.lee.mugen.parser.air.AirParser;
import org.lee.mugen.sprite.character.AnimElement;
import org.lee.mugen.sprite.character.AnimGroup;

public class AbstractAnimManager implements Serializable {
	public static class SpriteDrawProperties implements Serializable {
		private float angleset;
		private boolean isActive = false;
		private boolean nextSetDrawPropertiesToFalse = false;
		private float xScale = 1;

		private float yScale = 1;

		public void deactivateNext() {
			if (nextSetDrawPropertiesToFalse) {
				nextSetDrawPropertiesToFalse = false;
				setActive(false);
			} else {
				nextSetDrawPropertiesToFalse = true;
				
			}
		}

		/**
		 * angle en degree sens contraire aiguille montre
		 * @return
		 */
		public float getAngleset() {
			return angleset;
		}

		public float getXScale() {
			return xScale;
		}

		public float getYScale() {
			return yScale;
		}

		public boolean isActive() {
			return isActive;
		}
		public void setActive(boolean isActive) {
			this.isActive = isActive;
		}

		public void setAngleadd(float angleset) {
			this.angleset += angleset;
		}

		public void setAnglemul(float angleset) {
			this.angleset *= angleset;
		}

		public void setAngleset(float angleset) {
			this.angleset = angleset;
		}

		
		public void setXScale(float scale) {
			xScale = scale;
		}
		public void setYScale(float scale) {
			yScale = scale;
		}

	}
	
	protected int _action = 0;

	protected int _animElem;

	protected int _animElemTime = 0;

	protected int _animElemTimeCount = 0;

	protected SpriteDrawProperties _drawProperties = new SpriteDrawProperties();
	
	protected int _imgCount;


	protected int animElemeTochange = 0;

	protected boolean changeToNewAnimElem = false;
	protected HashMap<Integer, AnimGroup> groupSpriteMap;
	protected boolean isFirstChange = true;
	
	protected int lastAction = 0;
	

	public AbstractAnimManager() {
		groupSpriteMap = new HashMap<Integer, AnimGroup>();
	}
	
	public AbstractAnimManager(AirParser airParser) {
		build(airParser);
	}

	public AbstractAnimManager(HashMap<Integer, AnimGroup> groupSpriteMap) {
		setGroupSpriteMap(groupSpriteMap);
	}

	public void build(AirParser airParser) {
		groupSpriteMap = new HashMap<Integer, AnimGroup>();

		for (AirGroup airGrp : airParser.getAirGroupMap().values()) {
			AnimGroup grpSprite = new AnimGroup(airGrp);
			if (!groupSpriteMap.containsKey(airGrp.action))
				groupSpriteMap.put(airGrp.action, grpSprite);
		}
	}

	public int getAction() {
		return _action;
	}
	public int getAnimElemNo() {
		return _animElem + 1;
	}

	
	public int getAnimElemNo(int tick) {
		if (tick >= 0) {
			return getAnimElemNoPositive(tick)[0];
		} else {
			return getAnimElemNoNegative(tick)[0];
		}
	}

	public int[] getAnimElemNoImgCount() {
		return new int[] {_animElem + 1, _imgCount};
	}

	public int[] getAnimElemNoImgCount(int tick) {
		if (tick > 0)
			return getAnimElemNoPositive(tick);
		return getAnimElemNoNegative(tick);
	}
	public int[] getAnimElemNoNegative(int tick) {
		tick = Math.abs(tick);

		int animElem = this._animElem;
		int imgCount = this._imgCount;
		AnimGroup grpSrp = getCurrentGroupSprite();
		while (tick-- > 0) {
			imgCount--;
			if (imgCount < 0) {
				AnimElement[] elem = grpSrp.getImgSprites();
				animElem = animElem <= 0? elem.length - 1: animElem - 1;
				imgCount = elem[animElem].getDelay();
			}
		}
		return new int[] {animElem + 1, imgCount};
	}
	public int[] getAnimElemNoPositive(int tick) {
		tick = Math.abs(tick);
		int animElem = this._animElem;
		int imgCount = this._imgCount;
		int animElemTime = this._animElemTime;
		AnimGroup grpSrp = getCurrentGroupSprite();
		while (tick > 0) {
			int delay = getCurrentImageSprite().getDelay();
			assert delay != 0;
			if (delay != -1) {
				int delta = delay - imgCount;
				int toAdd = 0;
				if (tick > delta) {
					tick -= delta;
					toAdd = delta;
				} else {
					toAdd = tick;
					delta = delta - tick;
					tick = 0;
					
				}
				imgCount += toAdd;
				animElemTime += toAdd;
				if (imgCount > delay - 1) {
					imgCount = 0;
					animElem++;
				}
			} else {
				animElemTime = getAnimTimeCount() - 1;
				imgCount++;
				return null;
				
			}
			if (animElem > grpSrp.getImgSprites().length - 1) {
				if (animElem > grpSrp.getImgSprites().length - 1) {
					if (grpSrp.getImgLoopStart() != -1) {
						animElem = grpSrp.getImgLoopStart();
						animElem = animElem < 0? 0: animElem;
					} else {
						animElem = 0;
					}
					
				}
				animElemTime = 0;
			}
		}
		return new int[] {animElem + 1, imgCount};
	}


	public int getAnimElemTime() {
		return getAnimTimeCount(_animElem) + _imgCount;
//		return _animElemTime + 1;
	}


	public Integer getAnimElemTime(int elem) {
		assert elem > 0;
		elem--;

		int countCurrent = getAnimeTimeCount(_animElem);
		int countElem = getAnimeTimeCount(elem);

		if (elem < _animElem) {
			return countCurrent + _imgCount - countElem;
			
		} else if (elem > _animElem) {
			return (countCurrent + _imgCount) - countElem;
		} else {
			return _imgCount;
		}
		
	}
	private int getAnimeTimeCount(int animElem) {
		return getAnimeTimeCount(animElem, false);
	}

	private int getAnimeTimeCount(int animElem, boolean countLast) {
		int animTime = 0;
		if (countLast)
				animElem++;
		for (int i = 0; i < animElem; i++) {
			AnimElement imgSpr = getCurrentGroupSprite().getImgSprites()[i];
			animTime += imgSpr.getDelay() == -1? 1: imgSpr.getDelay();
		}
		return animTime;

	}
	protected int animTime = 0;
	public int getAnimTime() {
		return animTime;
	}
	public int getAnimTimeReal() {
		int loop = getCurrentGroupSprite().getImgLoopStart() + 0;
		loop = 0 - getAnimTimeCount(loop) + getAnimTimeCount() - 1;
		return _animElemTimeCount - loop  ;
	}
	public int getAnimTimeCount() {
		AnimGroup grpSrp = getCurrentGroupSprite();
		AnimElement[] animeElems = grpSrp.getImgSprites();
		int count = 0;
		for (AnimElement ae: animeElems)
			count += ae.getDelay() == -1? 1: ae.getDelay();
		return count;
	}


	public int getAnimTimeCount(int img) {
		if (img == 0)
			return 0;
		if (img == -1)
			return 0;
		AnimElement[] animeElems = getCurrentGroupSprite().getImgSprites();
		int count = 0;
		for (AnimElement ae: animeElems) {
			img--;
			if (img < 0)
				return count;
		
			count += ae.getDelay() == -1? 1: ae.getDelay();
		}
		return count;
	}
	public AnimGroup getCurrentGroupSprite() {
		return getCurrentGroupSprite(getAction());
	}
	
	public AnimGroup getCurrentGroupSprite(int action) {
		return groupSpriteMap.get(action);
	}
	
	public AnimElement getCurrentImageSprite() {
		AnimElement imgSpr = null;
		AnimGroup ag = null;
		try {
			ag = getCurrentGroupSprite();
		} catch (Exception e) {
			System.err.println("Err in spriteanimmanager getCurrentImageSprite in getting current grp sprite");
		}
		if (ag == null)
			return null;
		AnimElement[] elem = getCurrentGroupSprite().getImgSprites();
		int index = _animElem > elem.length - 1?elem.length - 1: _animElem;
		imgSpr = elem[index];
		return imgSpr;
	}

	public AnimGroup getGroupSprite(int group) {
		return groupSpriteMap.get(group);
	}
	public HashMap<Integer, AnimGroup> getGroupSpriteMap() {
		return groupSpriteMap;
	}
	public int getImgCount() {
		return _imgCount;
	}
	
	
	public int getLastAction() {
		return lastAction;
	}
	public SpriteDrawProperties getSpriteDrawProperties() {
		return _drawProperties;
	}
	public boolean isAnimExist(int anim) {
		return groupSpriteMap.containsKey(anim);
	}


	public boolean isSelfAnimExist(int anim) {
		return groupSpriteMap.get(anim) != null;
	}

	public void process() {
		if (changeToNewAnimElem) {
			_animElem = animElemeTochange;
			changeToNewAnimElem = false;
			_imgCount = 0;
		}
		if (_action < 0)
			return;
		if (getCurrentGroupSprite() == null)
			return;
		animTime = getAnimTimeReal();
		AnimGroup grpSrp = null;
		try {
			grpSrp = getCurrentGroupSprite();
				
		} catch (Exception e) {
			System.err.println("Err Img not exist in Process animmanager");
		}
		if (grpSrp == null)
			return;

		
		if (getCurrentImageSprite().getDelay() == -1) { // infinite
			_animElemTimeCount--;
			_animElemTime++;
			_imgCount++;
		} else if (_imgCount >= getCurrentImageSprite().getDelay() - 1) {
			_animElem++;
			if (_animElem > grpSrp.getImgSprites().length - 1) {
				if (grpSrp.getImgLoopStart() != -1) {
					_animElem = grpSrp.getImgLoopStart();
					_animElem = _animElem < 0? 0: _animElem;
					_animElemTime = getAnimTimeCount(_animElem);
					_animElemTimeCount = getAnimTimeCount(_animElem);

				} else {
					animElemeTochange = 0;
					changeToNewAnimElem = true;
					_animElemTime = 0;
					_animElemTimeCount = 0;
					_animElem--;
				}
			} else {
				_imgCount = 0;
				_animElemTimeCount++;
			}
		} else {
			_imgCount++;
			_animElemTime++;
			_animElemTimeCount++;
			isFirstChange = false;

		}
		getSpriteDrawProperties().deactivateNext();
		lastAction = _action;
	}

	public void setAction(int value) {
		if (value == _action)
			return;
		if (getGroupSprite(value) == null && value != -1) {
//			System.err.println("l'action " + value + " n'existe pas." );
		}
		if (lastAction != _action)
			lastAction = _action;
		_action = value;
		_animElem = 0;
		_imgCount = 0;
		_animElemTime = 0;
		isFirstChange = true;
		
		_animElemTimeCount = 0;
		animTime = getAnimTimeReal();
	}



	public void setAnimElem(int value) {
		_animElem = value;
		_imgCount = 0;
		_animElemTime = getAnimeTimeCount(value);
		isFirstChange = true;
		
		_animElemTimeCount = getAnimTimeCount(_animElem);
		animTime = getAnimTimeReal();

}


	public void setGroupSpriteMap(HashMap<Integer, AnimGroup> groupSpriteMap) {
		this.groupSpriteMap = groupSpriteMap;
	}

}
