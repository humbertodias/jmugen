package org.lee.mugen.sprite.character;

import java.util.HashMap;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.air.AirParser;
import org.lee.mugen.sprite.base.AbstractAnimManager;
import org.lee.mugen.sprite.base.AbstractSprite;

public class SpriteAnimManager extends AbstractAnimManager {
	private String spriteId;
	private boolean changeAnim2 = false;
	private String spriteIdAnim = null;
	
	public void setAction(int value) {
		if (value == _action)
			return;
		if (groupSpriteMap.get(value) == null && value != -1) {
//			System.err.println("l'action " + value + " n'existe pas." );
			_action = -1;
			return;
		}
		if (lastAction != _action)
			lastAction = _action;
		_action = value;
		_animElem = 0;
		_imgCount = 0;
		_animElemTime = 0;
		changeAnim2 = false;
		isFirstChange = true;
		
		_animElemTimeCount = 0;
		try {
			animTime = getAnimTimeReal();
		} catch (Exception e) {
			// TODO: handle exception
		}
		

	}

	public void setAction(int value, boolean changeAnim2, String spriteIdAnim) {
		if (value == _action)
			return;
		lastAction = _action;
		_action = value;
		_animElem = 0;
		_imgCount = 0;
		_animElemTime = 0;
		this.changeAnim2 = changeAnim2;
		this.spriteIdAnim = spriteIdAnim;
		isFirstChange = true;
		
		_animElemTimeCount = 0;
		try {
			animTime = getAnimTimeReal();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}


	public void build(String spriteId, AirParser airParser) {
		this.spriteId = spriteId;
		super.build(airParser);
	}
	public SpriteAnimManager(String spriteId) {
		groupSpriteMap = new HashMap<Integer, AnimGroup>();
		this.spriteId = spriteId;
	}
	public SpriteAnimManager(String spriteId, AirParser airParser) {
		build(spriteId, airParser);
	}


	public SpriteAnimManager(String spriteId, HashMap<Integer, AnimGroup> groupSpriteMap) {
		this(spriteId);
		setGroupSpriteMap(groupSpriteMap);
	}

	public AnimGroup getCurrentGroupSprite(int action) {
		if (changeAnim2) {
			Sprite spr = GameFight.getInstance().getSpriteInstance(GameFight.getInstance().getRootId(spriteIdAnim));
			try {
				spr.getSprAnimMng().getGroupSprite(action);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// TODO Fix this because the helper is clean out so it can't find
			if (spr == null)
				spr = GameFight.getInstance().getSpriteInstance(spriteIdAnim.substring(0, 1));
			return spr.getSprAnimMng().getGroupSprite(action);
		} else {
			return groupSpriteMap.get(action);
		}
		
	}

	public AnimGroup getGroupSprite(int action) {
		if (changeAnim2) {
			Sprite spr = GameFight.getInstance().getSpriteInstance(spriteIdAnim);
			return spr.getSprAnimMng().getGroupSprite(action);
		} else {
			return groupSpriteMap.get(action);
		}
	}
	
	public void process() {
//		if (!StateMachine.getInstance().getGlobalEvents().canGameProcessWithPause((Sprite)getSprite()) && StateMachine.getInstance().getGlobalEvents().isSuperPause())
//			return;
		if (getSprite() != null && getSprite().isPause()) {
			return;
		}
		
		super.process();
	}
	private AbstractSprite getSprite() {
		return GameFight.getInstance().getSpriteInstance(spriteId);
	}


	public boolean isChangeAnim2() {
		return changeAnim2;
	}


	public void setSpriteId(String id) {
		spriteId = id;
	}


	public boolean isAnimExist(int anim) {
		if (changeAnim2) {
			Sprite spr = GameFight.getInstance().getSpriteInstance(spriteIdAnim);
			return spr.getSprAnimMng().isSelfAnimExist(anim);
		} else {
			return groupSpriteMap.containsKey(anim);
		}
	}


	public void setChangeAnim2(boolean changeAnim2, String spriteIdAnim) {
		this.changeAnim2 = changeAnim2;
		this.spriteIdAnim = spriteIdAnim;
	}
	public void setChangeAnim2(boolean changeAnim2) {
		this.changeAnim2 = changeAnim2;
	}
	public void setAnim(int value) {
//		if (value == _action)
	//		return;
		if (getGroupSprite(value) == null && value != -1) {
//			System.err.println("l'action " + value + " n'existe pas." );
			_action = -1;
			return;
		}
	//	if (lastAction != _action)
	//		lastAction = _action;
		_action = value;
		_animElem = 0;
		_imgCount = 0;
		_animElemTime = 0;
//		changeAnim2 = false;
		isFirstChange = true;
		
		_animElemTimeCount = 0;
		animTime = getAnimTimeReal();
	}

}
