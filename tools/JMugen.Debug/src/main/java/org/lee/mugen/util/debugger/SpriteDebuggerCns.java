//package org.lee.mugen.util.debugger;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.lee.mugen.core.StateMachine;
//import org.lee.mugen.sprite.character.Sprite;
//import org.lee.mugen.sprite.parser.CnsParse;
//import org.lee.mugen.sprite.parser.Parser;
//import org.lee.mugen.sprite.parser.Parser.GroupText;
//
//public class SpriteDebuggerCns {
//	private static final Map<String, SpriteDebuggerCns> spriteIdSprDebugMap = new HashMap<String, SpriteDebuggerCns>();
//	public static String getRaw(String spriteId, String action) {
//		if (!IS_DEBUG)
//			return null;
//		SpriteDebuggerCns sprDebug = null;
//		sprDebug = spriteIdSprDebugMap.get(spriteId);
//		
//		if (sprDebug != null) {
//			return sprDebug.getActionContentRaw(action);
//		}
//		return null;
//	}
//	
//	public static void load(String spriteId, String action, String raw) throws Exception {
//		if (!IS_DEBUG)
//			return;
//		SpriteDebuggerCns sprDebug = null;
//		sprDebug = spriteIdSprDebugMap.get(spriteId);
//		
//		if (sprDebug != null) {
//			sprDebug.load(action, raw);
//			
//		}
//		
//	}
//	
//	
//	public static void addStatedef(String spriteId, String action, String stateRaw) {
//		if (!IS_DEBUG)
//			return;
//		
//		SpriteDebuggerCns sprDebug = null;
//		sprDebug = spriteIdSprDebugMap.get(spriteId);
//		
//		if (sprDebug == null) {
//			sprDebug = new SpriteDebuggerCns(spriteId);
//			spriteIdSprDebugMap.put(spriteId, sprDebug);
//		}
//		sprDebug.delete(action);
//		sprDebug.addState(action, stateRaw);
//	}
//	public static void addStateCtrl(String spriteId, String action, String stateRaw) {
//		if (!IS_DEBUG)
//			return;
//		SpriteDebuggerCns sprDebug = null;
//		sprDebug = spriteIdSprDebugMap.get(spriteId);
//		
//		if (sprDebug == null) {
//			sprDebug = new SpriteDebuggerCns(spriteId);
//			spriteIdSprDebugMap.put(spriteId, sprDebug);
//		}
//		
//		sprDebug.addState(action, stateRaw);
//	}
//	public static String getStateCtrlRaw(String spriteId, String stateDef, int position) {
//		if (!IS_DEBUG)
//			return null;
//		SpriteDebuggerCns sprDebug = null;
//		sprDebug = spriteIdSprDebugMap.get(spriteId);
//		
//		if (sprDebug == null) {
//			sprDebug = new SpriteDebuggerCns(spriteId);
//			spriteIdSprDebugMap.put(spriteId, sprDebug);
//		}
//		
//		return sprDebug.getStateCtrlRaw(stateDef, position);
//	}
//	public static void delete(String spriteId, String action) {
//		if (!IS_DEBUG)
//			return;
//		SpriteDebuggerCns sprDebug = null;
//		sprDebug = spriteIdSprDebugMap.get(spriteId);
//		
//		if (sprDebug == null) {
//			sprDebug = new SpriteDebuggerCns(spriteId);
//			spriteIdSprDebugMap.put(spriteId, sprDebug);
//		}
//		
//		sprDebug.delete(action);
//	}
//	
//	private static final boolean IS_DEBUG = true;
//	
//	private String playerId;
//	private Map<String, List<String>> actionMapOfState;
//	
//	public SpriteDebuggerCns(String playerId) {
//		this.playerId = playerId;
//		actionMapOfState = new HashMap<String, List<String>>();
//	}
//	
//	public void addState(String action, String state) {
//		List<String> states = null;
//		if (actionMapOfState.containsKey(action)) {
//			states = actionMapOfState.get(action);
//		} else {
//			states = new ArrayList<String>();
//			actionMapOfState.put(action, states);
//		}
//		states.add(state);
//	}
//	public void delete(String action) {
//		List<String> states = null;
//		if (actionMapOfState.containsKey(action)) {
//			actionMapOfState.remove(action);
//		}
//	}
//	
//	public String getActionContentRaw(String action) {
//		List<String> list = actionMapOfState.get(action);
//		StringBuilder strBuff = new StringBuilder();
//		for (String s: list)
//			strBuff.append(s + "\n");
//		
//		return strBuff.toString();
//	}
////	public void reload(String action) throws Exception {
////		Sprite spr = StateMachine.getInstance().getSpriteInstance(playerId);
////		StringTokenizer strToken = new StringTokenizer(getActionContentRaw(action), "\n\r");
////		StateDef stateDef = CnsParse.parseStateDef(action, strToken, "");
////		if (stateDef.getIntId() < 0) {
////			throw new IllegalArgumentException("Negative statedef is not supported yet");
////		} else {
////			spr.getSpriteState().addStateDef(stateDef);
////		}
////	}
//	public void load(String action, String raw) throws Exception {
//		Sprite spr = StateMachine.getInstance().getSpriteInstance(playerId);
//		synchronized (spr.getSpriteState().getNegativeStateSef()) {
//			if (raw == null || raw.length() == 0) {
//				spr.getSpriteState().removeStateDef(action);
//				return;
//			}
//			if (Integer.parseInt(action) < 0) {
//				spr.getSpriteState().removeStateDef(action);
//			}
//			boolean caseSensitive = true;
//			List<GroupText> grps = Parser.getGroupTextMap(raw, caseSensitive);
//
////			StateDef stateDef = (StateDef) spr.getSpriteState().getStateDef(action).clone();
////			stateDef.setStateCtrlList(new ArrayList<StateCtrl>());
//			spr.getSpriteState().removeStateDef(action);
//			CnsParse.buildSpriteInfo(grps, spr, spr.getInfo(), spr.getSpriteState());
//
//			for (Sprite s: StateMachine.getInstance().getSprites()) {
//				if (s.getSpriteId().equals(spr.getSpriteId())) {
//					continue;
//				}
//			}
////			for (GroupText grp: grps) {
////				SpriteDebugger.addStatedef(spr.getSpriteId(), stateDef.getId(), grp.getSectionRaw() + "\n" + grp.getText().toString());
//////				CnsParse.parseStateCtrl(grp, stateDef, spr);
////			
////			}
////			if (stateDef.getIntId() < 0) {
////				throw new IllegalArgumentException("Negative statedef is not supported yet");
////			} else {
////				spr.getSpriteState().addStateDef(stateDef);
////			}
//			
//		}
//
//
//	}
//
//
//	public String getStateCtrlRaw(String stateDef, int position) {
//		List<String> list = actionMapOfState.get(stateDef);
//		if (list == null)
//			return "";
//		return list.get(position);
//	}
//
//	
//}
