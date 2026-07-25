package org.lee.mugen.sprite.character;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.FloatValueable;
import org.lee.mugen.parser.type.IntValueable;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.cns.StateCtrl;
import org.lee.mugen.sprite.cns.StateDef;
import org.lee.mugen.sprite.parser.Parser;

public class SpriteState implements Serializable {
	private StateDef currentStateDef;
	private StateDef previousStateDef;
	private String spriteId;
	private String spriteIdToBind;
	private long lastStateTime = 0;
	private boolean isJustChangeState = false;
	
	private boolean process = true;

	public boolean isProcess() {
		return process;
	}
	public void setProcess(boolean process) {
		this.process = process;
	}
	private Map<String, StateDef> stateDefMap = new HashMap<String, StateDef>();
	private Map<String, StateDef> originalStateDefMap = new HashMap<String, StateDef>();
	private List<StateDef> negativeStateSef = new ArrayList<StateDef>();
	private long timeInState;


	// Var
	private Var vars = new Var();
	private Var originalVars = vars;
	public class Var implements Serializable {
		
		private Map<String, Integer> varInt = new HashMap<String, Integer>();
		private Map<String, Float> varFloat = new HashMap<String, Float>();
		private Map<String, Integer> sysvarMap = new HashMap<String, Integer>();
		private Map<String, Float> sysfvarMap = new HashMap<String, Float>();
		public Map<String, Integer> getSysvarMap() {
			return sysvarMap;
		}
		Map<String, Float> getSysfvarMap() {
			return sysfvarMap;
		}

	///

		public void addVar(String key, Valueable valueable) {
			Integer o = getVar(key);
			int v = o == null ? 0 : o.intValue();
			final int value = Parser.getIntValue(valueable.getValue(spriteId)) + v;
			Valueable val = new IntValueable(value);
			setVar(key, val);
		}

		public void addSysVar(String key, Valueable valueable) {
			Integer o = getSysVar(key);
			int v = o == null ? 0 : o.intValue();
			final int value = Parser.getIntValue(valueable.getValue(spriteId)) + v;
			Valueable val = new IntValueable(value);
			setSysVar(key, val);
		}

		public void addSysFVar(String key, Valueable valueable) {
			Float o = getSysFVar(key);
			float v = o == null ? 0 : o.intValue();
			final float value = Parser.getFloatValue(valueable.getValue(spriteId))
					+ v;
			Valueable val = new FloatValueable(value);
			setSysFVar(key, val);
		}

		public void addVarFloat(String key, Valueable valueable) {
			Float o = getFVar(key);
			float v = o == null ? 0 : o.floatValue();
			final float value = Parser.getFloatValue(valueable.getValue(spriteId))
					+ v;
			Valueable val = new FloatValueable(value);
			setFVar(key, val);
		}

		public void setVar(String key, Valueable value) {
			final int v = Parser.getIntValue(value.getValue(spriteId));
			varInt.put(key, v);
		}

		public void setSysVar(String key, Valueable value) {
			sysvarMap.put(key, Parser.getIntValue(value.getValue(spriteId)));
		}

		public void setSysFVar(String key, Valueable value) {
			sysvarMap.put(key, Parser.getIntValue(value.getValue(spriteId)));
		}

		public Integer getVar(String key) {
			Integer v = varInt.get(key.toString());
			return v == null ? 0 : v;
		}

		public Integer getSysVar(String key) {
			Integer v = sysvarMap.get(key.toString());
			return v == null ? 0 : v;
		}

		public Float getSysFVar(String key) {
			Float v = sysfvarMap.get(key.toString());
			return v == null ? 0f : v;
		}

		public void setFVar(String key, Valueable value) {
			varFloat.put(key, Parser.getFloatValue(value.getValue(spriteId)));
		}

		public Float getFVar(String key) {
			Float v = varFloat.get(key.toString());
			return v == null ? 0 : v;
		}

		
		public Map<String, Integer> getVarInt() {
			return varInt;
		}

		public Map<String, Float> getVarFloat() {
			return varFloat;
		}
	}

	
	
/////////
	
	
	
	
	
	public void targetState(String spriteId, String state) {
		getSprite().getInfo().reset();
		isJustChangeState = true;
		spriteIdToBind = spriteId;
		stateDefMap = GameFight.getInstance().getInstanceOfStatedefFromOther(spriteId);
		previousStateDef = currentStateDef;
		currentStateDef = stateDefMap.get(state);
		currentStateDef.reset(spriteId);
		lastStateTime = GameFight.getInstance().getGameState().getGameTime();
		timeInState = -1;
		execute();
	}
	public void targetState(String spriteId, int state) {
		targetState(spriteId, state + "");
		
	}
	public void selfstate(String state) {
		getSprite().getInfo().reset();
		getSprite().getSprAnimMng().setChangeAnim2(false);
		isJustChangeState = true;
		spriteIdToBind = null; 
		stateDefMap = originalStateDefMap;
		previousStateDef = currentStateDef;

		currentStateDef = stateDefMap.get(state);
		currentStateDef.reset(spriteId);
		lastStateTime = GameFight.getInstance().getGameState().getGameTime();
		timeInState = -1;
		execute();
	}
	public void selfstate(int state) {
		selfstate(state + "");
	}
	
	public long getTimeInState() {
		return timeInState;
	}

	public SpriteState(String spriteId) {
		this.spriteId = spriteId;
	}
	public void changeStateDef(int key) {
		StateDef stateDef = stateDefMap.get(key + "");

		if (stateDef != null && !stateDef.equals(currentStateDef)) {

//			getSprite().getInfo().reset();
			isJustChangeState = true;
			lastStateTime = GameFight.getInstance().getGameState().getGameTime();
			timeInState = -1;
			previousStateDef = currentStateDef;

			currentStateDef = stateDef;
			currentStateDef.reset(spriteId);
			execute();
		}

	}
	private Sprite getSprite() {
		return GameFight.getInstance().getSpriteInstance(spriteId);
	}
	public StateDef getCurrentState() {
		return currentStateDef;
	}
	public void process() {
		if (!isProcess()) {
			return;
		}
		isJustChangeState = false;
		if (currentStateDef == null)
			changeStateDef(0);
		String old = currentStateDef.getId();
		synchronized (negativeStateSef) {
			for (StateDef stdef: negativeStateSef) {
				int id = stdef.getIntId();
				
				if (getSprite() instanceof SpriteHelper) {
					SpriteHelper spriteHelper = (SpriteHelper) getSprite();
					if (id == -1 && spriteHelper.getHelperSub().isKeyctrl())
						stdef.execute(spriteId);
				} else {
					if ((id == -3 && !isBindToOhterSprState()) 
							|| (id == -2 && !isBindToOhterSprState()) 
							|| (id == -1 && !isBindToOhterSprState())
							|| (id == -1)) {
						
						stdef.execute(spriteId);
						
					}
					
				}
				
			}
		}
		if (old.equals(currentStateDef.getId())) {
			execute();
		}

	}
	private void execute() {
		if (getSprite() == null || !getSprite().isPause()) {// || timeInState == 0)
			timeInState++;
			
		}
		currentStateDef.execute(spriteId);
	}
	

	public StateDef getStateDef(int i) {
		if (i < 0) {
			for (StateDef s: negativeStateSef)
				if (i == s.getIntId())
					return s;
			return null;
		} else {
			return stateDefMap.get(i + "");
		}
	}
	public StateDef getStateDef(String i) {
		return getStateDef(Integer.parseInt(i));
	}
	
	public String getPrevstateno() {
		if (previousStateDef == null)
			return "0";
		return previousStateDef.getId();
	}
	public List<StateDef> cloneNegativeState() {
		return (List<StateDef>) ((ArrayList)negativeStateSef).clone();
	}


	public Map<String, StateDef> cloneStateNormalDef() {
		return (Map<String, StateDef>) ((HashMap<String, StateDef>) originalStateDefMap).clone();
	}
	
	public void addStateDef(StateDef stateDef) {
		if (Integer.parseInt(stateDef.getId()) < 0) {
			negativeStateSef.add(stateDef);
			Collections.sort(negativeStateSef, new Comparator<StateDef>() {

				public int compare(StateDef o1, StateDef o2) {
					return o1.getIntId() - o2.getIntId();
				}});
		} else {
			stateDefMap.put(stateDef.getId(), stateDef);
			originalStateDefMap = stateDefMap;
		}
	}

	public void addStateCtrl(StateCtrl stateCtrl) {
		if (stateCtrl == null) {
			// TODO : Faire un logger propre
			System.err.println("you add a null stateCtrl");
			return;
		}
		StateDef stateDef = null;
		int statedefId = Integer.parseInt(stateCtrl.getStateDefId());
		if (statedefId < 0) {
			for (StateDef negStateDef: negativeStateSef) {
				
				if (Integer.parseInt(negStateDef.getId()) == statedefId) {
					negStateDef.addStateCtrl(stateCtrl);
					return;
				}
			}
		}
		stateDef = getStateDef(statedefId);

		assert stateDef != null : "stateDef musn't be null";
		if (stateDef == null) {
			System.err.println("StateDef " + stateCtrl.getStateDefId() + " not found. This controler " + stateCtrl.getId() + " will not be add");
			return;
		}
		stateDef.addStateCtrl(stateCtrl);
	}

	public List<StateDef> getAllStateDef() {
		List<StateDef> result = new ArrayList<StateDef>();
		result.addAll(stateDefMap.values());
		result.addAll(negativeStateSef);
		return result;
	}
	public boolean isBindToOhterSprState() {
		return getSpriteIdToBind() != null;
	}
	public String getSpriteIdToBind() {
		return spriteIdToBind;
	}
	public boolean isJustChangeState() {
		return isJustChangeState;
	}
	public void removeStateDef(String action) {
		if (Integer.parseInt(action) < 0) {
			for (Iterator<StateDef> iter = negativeStateSef.iterator(); iter.hasNext();) {
				StateDef current = iter.next();
				if (current.getId().equals(action))
					iter.remove();
			}
		}
		stateDefMap.remove(action);
	}
	public long getLastStateTime() {
		return lastStateTime;
	}
	public void setLastStateTime(long lastStateTime) {
		this.lastStateTime = lastStateTime;
	}
	public void setSpriteId(String id) {
		spriteId = id;
	}
	public void setStatedefMap(Map<String, StateDef> statedefMap) {
		this.stateDefMap = statedefMap;
		this.originalStateDefMap = statedefMap;
	}
	public List<StateDef> getNegativeStateSef() {
		return negativeStateSef;
	}
	public void setNegativeStateSef(List<StateDef> negativeStateSef) {
		this.negativeStateSef = negativeStateSef;
	}
	public Map<String, StateDef> getOriginalStateDefMap() {
		return originalStateDefMap;
	}
	public void setOriginalStateDefMap(Map<String, StateDef> originalStateDefMap) {
		this.originalStateDefMap = originalStateDefMap;
	}
	public Var getVars() {
		if (isBindToOhterSprState()) {
			return GameFight.getInstance().getSpriteInstance(spriteIdToBind).getSpriteState().getVars();
		} else {
			return vars;
		}
	}
	public void clearVars() {
		getVars().sysfvarMap.clear();
		getVars().sysvarMap.clear();
		getVars().varInt.clear();
		getVars().varFloat.clear();
		
		
		
	}
	public boolean isStateExist(int i) {
		
		return getStateDef(i) != null;
	}
	public void executeDebug() {
		currentStateDef.execute(spriteId);		
	}

}
