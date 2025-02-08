package org.lee.mugen.sprite.parser;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.SequenceInputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.input.MugenCommands;
import org.lee.mugen.parser.type.Functionable;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.character.SpriteCns;
import org.lee.mugen.sprite.character.SpriteState;
import org.lee.mugen.sprite.cns.StateCtrl;
import org.lee.mugen.sprite.cns.StateDef;
import org.lee.mugen.sprite.cns.Trigger;
import org.lee.mugen.sprite.cns.eval.function.StateCtrlFunction;
import org.lee.mugen.sprite.cns.eval.function.StateCtrlFunctionDef;
import org.lee.mugen.sprite.parser.Parser.GroupText;
import org.lee.mugen.util.BeanTools;
import org.lee.mugen.util.Logger;

public class CnsParse {
	/*
	 * Common Regex
	 */
	public static final String S_END = "(?:(?: *;.*$)|(?: *$))";
	public static final String S_COMMENT_OR_EMPTY_REGEX = "^ *;.*$|^ *$";
	public static final String S_FLOAT_REGEX = "((?:\\+|-)?(?:(?:\\.\\d+)|(?:\\d+(?:\\.\\d*)?)))";//"[+-]*(?:\\d+\\.\\d+)|(?:\\d+)";

	/*
	 * Data Regex
	 */
	private static final String S_DATA_TITLE_REGEX = " *\\[ *data *\\]" + S_END;
	/*
	 * 
	 */
	private static final String S_SIZE_TITLE_REGEX = " *\\[ *size *\\]" + S_END;
	/*
	 * 
	 */
	private static final String S_VELOCITY_TITLE_REGEX = " *\\[ *velocity *\\]" + S_END;
	/*
	 * 
	 */
	private static final String S_MOVEMENT_TITLE_REGEX = " *\\[ *movement *\\]" + S_END;

	/*
	 * StateDef
	 */
	public static final String S_STATE_DEF_TITLE_REGEX = "\\s*\\[ *statedef *" + S_FLOAT_REGEX + " *\\]" + S_END;
	public static final String S_STATE_CONTINUE_DEF_TITLE_REGEX = "\\s*\\[ *statedefcontinue *" + S_FLOAT_REGEX + " *\\]" + S_END;
	
	/*
	 * StateCtrl
	 */
	public static final String S_STATE_CTRL_TITLE_REGEX = "\\s*\\[ *state *" + "([^\\]]*)" + "\\]" + S_END;
//			+ _FLOAT_REGEX + " *,* *" + "([^\\]]*)" + "\\]" + _END;

	
	private static final String S_TRIGGER_MAIN = "\\s*triggerall *=.*" + S_END;

	private static final String S_TRIGGER = "\\s*trigger(\\d+) *=.*" + S_END;

	
	private static final Pattern P_COMMENT_OR_EMPTY_REGEX = Pattern.compile(S_COMMENT_OR_EMPTY_REGEX);
	private static final Pattern P_FLOAT_REGEX = Pattern.compile(S_FLOAT_REGEX);
	private static final Pattern P_DATA_TITLE_REGEX = Pattern.compile(S_DATA_TITLE_REGEX, Pattern.CASE_INSENSITIVE);
	private static final Pattern P_SIZE_TITLE_REGEX = Pattern.compile(S_SIZE_TITLE_REGEX, Pattern.CASE_INSENSITIVE);
	private static final Pattern P_VELOCITY_TITLE_REGEX = Pattern.compile(S_VELOCITY_TITLE_REGEX, Pattern.CASE_INSENSITIVE);
	private static final Pattern P_MOVEMENT_TITLE_REGEX = Pattern.compile(S_MOVEMENT_TITLE_REGEX, Pattern.CASE_INSENSITIVE);
	private static final Pattern P_STATE_DEF_TITLE_REGEX = Pattern.compile(S_STATE_DEF_TITLE_REGEX, Pattern.CASE_INSENSITIVE);
	private static final Pattern P_STATE_CONTINUE_DEF_TITLE_REGEX = Pattern.compile(S_STATE_CONTINUE_DEF_TITLE_REGEX);
	private static final Pattern P_STATE_CTRL_TITLE_REGEX = Pattern.compile(S_STATE_CTRL_TITLE_REGEX, Pattern.CASE_INSENSITIVE);
	private static final Pattern P_TRIGGER_MAIN = Pattern.compile(S_TRIGGER_MAIN, Pattern.CASE_INSENSITIVE);
	private static final Pattern P_TRIGGER = Pattern.compile(S_TRIGGER, Pattern.CASE_INSENSITIVE);
	
	private static boolean isMatch(Pattern reg, String input) {
		return reg.matcher(input).matches();
	}
	
	
	private static BufferedReader fileToReader(File f) throws UnsupportedEncodingException, FileNotFoundException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f), "utf-8"));
		return br;
	}
	
	private static BufferedReader fileToReader(String f) throws UnsupportedEncodingException, FileNotFoundException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(f)), "utf-8"));
		return br;
	}
	
	public static List<GroupText> getCnsGroup(String parentPath, String constPath, String commonCns, String[] cns) throws Exception {
		List<InputStream> cnsBuilder = new ArrayList<InputStream>();
		BufferedReader br = new BufferedReader(fileToReader(new File(parentPath, constPath).getAbsolutePath()));
		FileInputStream cnsData = new FileInputStream(new File(parentPath, constPath));
		
		FileInputStream cnsCommon = null;
		File dataDir = new File(new File(parentPath).getParentFile().getParentFile(), "data");
		if (new File(parentPath, commonCns).exists())
			cnsCommon = new FileInputStream(new File(parentPath, commonCns).getAbsolutePath());
		else if (new File(dataDir, commonCns).exists()) {
			cnsCommon = new FileInputStream(new File(dataDir, commonCns).getAbsolutePath());
		} else {
			throw new IllegalArgumentException("Can't find common cns");
		}

		cnsBuilder.add(cnsData);
		cnsBuilder.add(new ByteArrayInputStream("\n".getBytes()));
		

		for (String s: cns) {
			if (s.equals(constPath) || !new File(parentPath, s).isFile())
				continue;
			FileInputStream grpText = new FileInputStream(new File(parentPath, s).getAbsolutePath());
			cnsBuilder.add(grpText);
			cnsBuilder.add(new ByteArrayInputStream("\n".getBytes()));

		}
		cnsBuilder.add(cnsCommon);
		cnsBuilder.add(new ByteArrayInputStream("\n".getBytes()));

		Enumeration<InputStream> e = Collections.enumeration(cnsBuilder);
		SequenceInputStream sis = new SequenceInputStream(e);
		
		List<GroupText> grps = Parser.getGroupTextMap(new InputStreamReader(sis, "utf-8"), true);
		return grps;
	}
	
	public static void buildSpriteInfo(List<GroupText> groups, Sprite sprite, SpriteCns spriteInfo, SpriteState spriteState) throws Exception {
		String spriteId = sprite.getSpriteId();
		String precedentStateDef = null;
		StateDef precedentStateDefObj = null;
		boolean skip = false;
		for (GroupText grp : groups) {
				if (isMatch(P_DATA_TITLE_REGEX, grp.getSectionRaw())) {
					spriteInfo.addGroupData(grp);
					fillDirectSpriteInfoData(spriteInfo, spriteId, "data", grp);
				} else if (isMatch(P_SIZE_TITLE_REGEX, grp.getSectionRaw())) {
					spriteInfo.addGroupData(grp);
					fillDirectSpriteInfoData(spriteInfo, spriteId, "size", grp);
				} else if (isMatch(P_VELOCITY_TITLE_REGEX, grp.getSectionRaw())) {
					spriteInfo.addGroupData(grp);
					fillDirectSpriteInfoData(spriteInfo, spriteId, "velocity", grp);
				} else if (isMatch(P_MOVEMENT_TITLE_REGEX, grp.getSectionRaw())) {
					spriteInfo.addGroupData(grp);
					fillDirectSpriteInfoData(spriteInfo, spriteId, "movement", grp);
				} else if (isMatch(P_STATE_DEF_TITLE_REGEX, grp.getSectionRaw())) {

					Matcher m = P_STATE_DEF_TITLE_REGEX.matcher(grp.getSectionRaw());
					m.find();
					String stateDefId = m.group(1);
					if (spriteState.getStateDef(stateDefId) != null) {
						skip = true;
						continue;
					}
					skip = false;
					
					precedentStateDef = stateDefId;
					precedentStateDefObj = new StateDef(stateDefId);
					if (Integer.parseInt(stateDefId) < 0) {
						precedentStateDefObj = parseStateDef(stateDefId, grp, "");
						precedentStateDefObj.setCompiled();
					}
					precedentStateDefObj.addGroup(grp);
					spriteState.addStateDef(precedentStateDefObj);
					
					
				} else if (isMatch(P_STATE_CONTINUE_DEF_TITLE_REGEX, grp.getSectionRaw())) {
					Matcher m = P_STATE_CONTINUE_DEF_TITLE_REGEX.matcher(grp.getSectionRaw());
					m.find();
					String stateDefId = m.group(1);
					precedentStateDef = stateDefId;
					int id = Integer.parseInt(stateDefId);
					
					if (id < 0) {
						for (StateDef statedef : spriteState.getNegativeStateSef()) {
							if (statedef.getIntId() == id) {
								precedentStateDefObj = statedef;
								break;
							}
						}
					} else {
						precedentStateDefObj = spriteState.getStateDef(id);
					}
						
					
					if (precedentStateDefObj == null) {
						precedentStateDef = id + "";
						precedentStateDefObj.addGroup(grp);
					} else {
					}
				} else if (isMatch(P_STATE_CTRL_TITLE_REGEX, grp.getSectionRaw())) {
					if (skip) {
						continue;
					}
					Matcher m = P_STATE_CTRL_TITLE_REGEX.matcher(grp.getSectionRaw());
					m.find();
					String stateDefId = precedentStateDef;//;m.group(1);
					String stateCtrlId = m.group(1);
					try {
						if (Integer.parseInt(stateDefId) < 0) {
							parseStateCtrl(precedentStateDefObj, stateDefId, stateCtrlId, grp);
							
						}

						precedentStateDefObj.addGroup(grp);
					} catch (Exception e) {
//						e.printStackTrace();
					}
				} else if ("command".equalsIgnoreCase(grp.getSection())) {
					MugenCommands mugenCommands = CmdParser.interpretCmd(grp);
					sprite.getCmds().add(mugenCommands);
					sprite.addGroupCmd(grp);
				}
//				else if (isMatch(P_COMMENT_OR_EMPTY_REGEX, grp.getSectionRaw())) {
//
//				}
//			}
		}
//		StateCtrlFunction.endOfParsing();
	}
	
	
	
	
//	static final String[] defaultNamesStateDef = {"type", "movetype", "physics", "facep2", "movehitpersist", "hitcountpersist"};
//	static final String[] defaultValuesStateDef = {"S", "I", "N", "0", "0", "0"};
	
	static final String[] defaultNamesStateDef = {"type", "movetype", "physics", "facep2", "hitdefpersist"};
	static final String[] defaultValuesStateDef = {"S", "I", "N", "0", "0", };
	public static StateDef parseStateDef(String stateDefId,
			GroupText grp, final String prefix) throws Exception {
		return parseStateDef(null, stateDefId, grp, prefix);
	}
	public static StateDef parseStateDef(StateDef stateDef, String stateDefId,
			GroupText grp, final String prefix) throws Exception {

		if (stateDef == null)
			stateDef = new StateDef(stateDefId);
		
		// Valeur par default
		Map<String, String> defaultsMap = new HashMap<String, String>();
		for (int i = 0; i < defaultNamesStateDef.length; ++i) {
			defaultsMap.put(defaultNamesStateDef[i], defaultValuesStateDef[i]);
		}
		StringTokenizer strToken = new StringTokenizer(grp.getText().toString(), "\r\n");
		while (strToken.hasMoreTokens()) {
			String line = strToken.nextToken().toLowerCase();
			if (isMatch(P_COMMENT_OR_EMPTY_REGEX, line)) {
				continue;
			}
			String[] keyValue = getSeparateKeyValue(line, strToken);
			final String name = keyValue[0];
			final String value = keyValue[1];
			defaultsMap.remove(name.toLowerCase());

			String[] tokens = ExpressionFactory.expression2Tokens(value);
			final Valueable[] values;
			
			try {
				values = ExpressionFactory.evalExpression(tokens);
				
			} catch (Exception e) {
				System.err.println("This line is not good for stateDefId " + stateDefId + "\n" + line);
				continue;
			}

			if (name.equals("velset")) {
				StateCtrlFunction velset = StateCtrlFunctionDef.getStateCtrlFunc(name);
				velset.addParam("x", new Valueable[] {values[0]});
				if (values.length > 1)
					velset.addParam("y", new Valueable[] {values[1]});
				else
					velset.addParam("y", new Valueable[] {new Valueable() {

						public Object getValue(String spriteId,
								Valueable... params) {
							return 0f;
						}}});
				
				stateDef.addExecutor(velset);
				
			} else
			{
				Functionable functionable = new Functionable() {
					public void reset() {}

					public Object getValue(String spriteId, Valueable... params) {
						try {
							setSpriteInfoValue(spriteId, GameFight.getInstance().getSpriteInstance(spriteId).getInfo(), prefix, name, values);
						} catch (Exception e) {
							e.printStackTrace();
							throw new IllegalStateException(
									"functionable StateDef IllegalStateException");
						}
						return 1;
					}
				};
				stateDef.addExecutor(functionable);
			}
		}
		if (Integer.parseInt(stateDef.getId()) >= 0)
		for (String key: defaultsMap.keySet()) {
			final String name = key;
			String value = defaultsMap.get(name);
			String[] tokens = ExpressionFactory.expression2Tokens(value);
			final Valueable[] values = ExpressionFactory.evalExpression(tokens);
			Functionable functionable = new Functionable() {
				public void reset() {}

				public Object getValue(String spriteId, Valueable... params) {
					try {
						setSpriteInfoValue(spriteId, GameFight.getInstance().getSpriteInstance(spriteId).getInfo(), prefix, name, values);
					} catch (Exception e) {
						e.printStackTrace();
						throw new IllegalStateException(
								"functionable StateDef IllegalStateException");
					}
					return 1;
				}
			};
			stateDef.addExecutor(functionable);			
		}
		return stateDef;
	}




	public static void parseStateCtrl(
			StateDef precedentStateDefObj, 
			String stateDefId, 
			String stateCtrlId,
			GroupText grp) throws Exception {
		StringTokenizer strToken = new StringTokenizer(grp.getText().toString(), "\r\n");
		StateCtrl stateCtrl = new StateCtrl(stateDefId, stateCtrlId);
		StateCtrlFunction stateCtrlFunction = null;
		
		while (strToken.hasMoreTokens()) {
			String f = strToken.nextToken();
			String line =  Parser.toLowerCase(f);
			if (isMatch(P_COMMENT_OR_EMPTY_REGEX, line.trim())) {
				continue;
			}
			String[] keyValue = getSeparateKeyValue(line, strToken);
			final String name = keyValue[0];
			String value = keyValue[1];

			
			if (isMatch(P_TRIGGER_MAIN, line)) {
				try {
					
					String[] tokens = ExpressionFactory.expression2Tokens(value);
					final Valueable[] valueables = ExpressionFactory.evalExpression(tokens);
					assert valueables != null && valueables.length > 0 : "parseStateCtrl valueables is empty";
					Valueable valueToBeTrig = valueables[0];
					Trigger trigger = new Trigger(valueToBeTrig);
					stateCtrl.setMainTrigger(trigger);
				} catch (Exception e) {
					System.err.println("Exception skip error : " + " stateDef = " + stateDefId + " stateCtrl" + stateCtrlId + ">" + line);
				}


			} else if (isMatch(P_TRIGGER, line)) {
				String[] tokens = ExpressionFactory.expression2Tokens(value);
				Matcher m = P_TRIGGER.matcher(line);
				m.find();
				int prio = Integer.parseInt(m.group(1));

				try {
					final Valueable[] valueables = ExpressionFactory.evalExpression(tokens);
					assert valueables != null && valueables.length > 0 : "parseStateCtrl valueables is empty";
					Valueable valueToBeTrig = valueables[0];
					Trigger trigger = new Trigger(valueToBeTrig);

					trigger.setPrio(prio);
					stateCtrl.addTrigger(trigger);
				} catch (Exception e) {
					System.err.println("Exception skip error : " + " stateDef = " + stateDefId + " stateCtrl" + stateCtrlId + ">" + line);
				}

			} else if ("type".equals(name)) {
				stateCtrlFunction = StateCtrlFunctionDef.getStateCtrlFunc(value);
				if (stateCtrlFunction == null) {
					Logger.log("TODO : type = " + value);
					return; // TODO remove
				}

				stateCtrl.addExecutor(stateCtrlFunction);
			} else if ("persistent".equals(name)) {
				stateCtrl.setPersistent(Integer.parseInt(value));
			} else if ("ignorehitpause".equals(name)) {
				stateCtrl.setIgnorehitpause(value == null || value.trim().length() == 0? 1: Integer.parseInt(value));
//			} 
//			else if ("ctrl".equals(name)) {
//				String[] tokens = ExpressionFactory.expression2Tokens(value);
//
//				final Valueable[] vals = ExpressionFactory.evalExpression(tokens);
//				Ctrlset ctrlset = new Ctrlset();
//				ctrlset.addParam("value", vals);
//				
//				stateCtrl.addExecutor(ctrlset);
			} else {
				try {

					
					if (stateCtrlFunction != null && stateCtrlFunction.containsParam(name)) {
						Valueable[] valueables = stateCtrlFunction.parseValue(name, value);
						stateCtrlFunction.addParam(name, valueables);
					} else {
//						Logger.log(
//								" Warning : Statedef[" + stateDefId + "] - StateCtrl[" + stateCtrlId + "]" + 
//								" : Type = " + stateCtrlFunction.getFunctionName() + 
//								" doesn't take this argument >> " + line);
					}
					
				} catch (RuntimeException e) {
					System.err.println(
							" Err " + e.getClass() + " : Statedef[" + stateDefId + "] - StateCtrl[" + stateCtrlId + "]" + 
							" : Type = " + stateCtrlFunction.getFunctionName() + 
							" >> " + line);
				}
			}
		}
		try {
			if (!stateCtrlFunction.control()) {
				throw new IllegalStateException("Some of mandatory field aren't filled for this " + stateCtrlFunction.getFunctionName());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		precedentStateDefObj.addStateCtrl(stateCtrl);
//		spriteInfo.addStateCtrl(stateCtrl);
	}

	private static void fillDirectSpriteInfoData(SpriteCns spriteInfo,
			String spriteId, String prefix, GroupText grp) throws Exception {
		StringTokenizer strToken = new StringTokenizer(grp.getText().toString(), "\r\n");
		while (strToken.hasMoreTokens()) {
			String line = Parser.toLowerCase(strToken.nextToken());
			try {
				if (isMatch(P_COMMENT_OR_EMPTY_REGEX, line)) {
					continue;
				}
				String[] keyValue = getSeparateKeyValue(line, strToken);
				String[] tokens = ExpressionFactory.expression2Tokens(keyValue[1]);

				Valueable[] values = ExpressionFactory.evalExpression(tokens);
				if (spriteInfo != null)
					setSpriteInfoValue(spriteId, spriteInfo, prefix, keyValue[0], values);
			} catch (Exception e) {
				System.err.println("line error :  " + line);
			}
		}
	}


	public static String[] getSeparateKeyValue(String line, StringTokenizer tokens) {
		String[] keyValue = new String[2];
//		if (isMatch(P_COMMENT_OR_EMPTY_REGEX, line))
//			return null;
//		line = Parser.toLowerCase(line.trim());
		int indexEnd = line.indexOf(";"); // search comment
		indexEnd = indexEnd == -1 ? line.length() - 1 : indexEnd - 1;
		int indexEqual = line.indexOf("=");
		keyValue[0] = "";
		keyValue[1] = "";
		if (-1 == indexEqual) {
			keyValue[0] = line;
			return keyValue;
		}
		keyValue[0] = line.substring(0, indexEqual).trim();
		keyValue[1] = line.substring(indexEqual + 1,
				indexEqual + 1 + indexEnd - indexEqual).trim();
//		keyValue[1].replaceAll(S_END, "");
//		if (keyValue[1].endsWith("\\n") && tokens != null) {
//			int index = keyValue[1].indexOf("\\n");
//			keyValue[1] = keyValue[1].substring(0, index);
//			String addLine = "\\n";
//			while (tokens.hasMoreTokens() && addLine.endsWith("\\n")) {
//				addLine = tokens.nextToken();
//				addLine.replaceAll(S_END, "");
//				keyValue[1] += addLine;
//				index = keyValue[1].indexOf("\\n");
//				if (index != -1)
//					keyValue[1] = keyValue[1].substring(0, index);
//			}
//		}
		return keyValue;
	}
	

	private static void setSpriteInfoValue(String spriteId, SpriteCns sprInfo, String prefix, String property,
			Valueable... values) throws Exception {
		String realProperty = 
				(prefix == null || prefix.trim().length() == 0? "": prefix + ".") 
				+ property.toLowerCase();
			if (values.length > 0) {
				Object[] objs = new Object[values.length];
				for (int i = 0; i < objs.length; ++i) {
					objs[i] = values[i].getValue(spriteId);
				}
				try {
					if (objs.length == 1) {
						BeanTools.setObject(sprInfo, realProperty, objs[0]);
					} else if (objs.length > 1) {
						BeanTools.setObject(sprInfo, realProperty, objs);
					}
				} catch (Exception e) {
					System.err.println("Unknow Property for  " + realProperty + " object : " + sprInfo.getClass());
				}
			}

	}


	public static void parseStateCtrl(GroupText grp, StateDef stateDef,
			Sprite sprite) throws Exception {
		if (!(isMatch(P_STATECTRL, grp.getSection()))) {
			throw new IllegalArgumentException("this section : "
					+ grp.getSection() + " is not [command] section");
		}
		Matcher m = P_STATECTRL.matcher(grp.getSection());
		m.find();
		String id = m.group(1);
		CnsParse.parseStateCtrl(stateDef, stateDef.getId(), id, grp);
		
		StateCtrlFunction.endOfParsing();
		
	}
	
//	public static S
	/*
	 * 
	 * [Statedef -1] [State -1, command name]
	 * 
	 */
	public static final String S_STATEDEF = " *statedef +([a-zA-Z0-9\\ \\-\\+\\_\\(\\)\\{\\}\\,]*,.*) *";
	public static final String S_STATECTRL = " *state +([a-zA-Z0-9\\ \\-\\+\\_\\(\\)\\{\\}\\,]*,.*) *";
	
	public static final Pattern P_STATEDEF = Pattern.compile(S_STATEDEF, Pattern.CASE_INSENSITIVE);
	public static final Pattern P_STATECTRL = Pattern.compile(S_STATECTRL, Pattern.CASE_INSENSITIVE);


	public static void buildSpriteInfoForReal(List<GroupText> groups, Sprite sprite, SpriteCns spriteInfo, SpriteState spriteState) throws Exception {
		String spriteId = sprite.getSpriteId();
		String precedentStateDef = null;
		StateDef precedentStateDefObj = null;
		boolean skip = false;
		for (GroupText grp : groups) {
				if (isMatch(P_DATA_TITLE_REGEX, grp.getSectionRaw())) {
					fillDirectSpriteInfoData(spriteInfo, spriteId, "data", grp);
				} else if (isMatch(P_SIZE_TITLE_REGEX, grp.getSectionRaw())) {
					fillDirectSpriteInfoData(spriteInfo, spriteId, "size", grp);
				} else if (isMatch(P_VELOCITY_TITLE_REGEX, grp.getSectionRaw())) {
					fillDirectSpriteInfoData(spriteInfo, spriteId, "velocity", grp);
				} else if (isMatch(P_MOVEMENT_TITLE_REGEX, grp.getSectionRaw())) {
					fillDirectSpriteInfoData(spriteInfo, spriteId, "movement", grp);
				} else if (isMatch(P_STATE_DEF_TITLE_REGEX, grp.getSectionRaw())) {

					Matcher m = P_STATE_DEF_TITLE_REGEX.matcher(grp.getSectionRaw());
					m.find();
					String stateDefId = m.group(1);
//					if (spriteState.getStateDef(stateDefId) != null) {
//						skip = true;
//						continue;
//					}
					skip = false;
					
					precedentStateDef = stateDefId;
					precedentStateDefObj = spriteState.getStateDef(stateDefId);
					parseStateDef(precedentStateDefObj, stateDefId, grp, "");
//					spriteState.addStateDef(precedentStateDefObj);
					
				} else if (isMatch(P_STATE_CONTINUE_DEF_TITLE_REGEX, grp.getSectionRaw())) {
					Matcher m = P_STATE_CONTINUE_DEF_TITLE_REGEX.matcher(grp.getSectionRaw());
					m.find();
					String stateDefId = m.group(1);
					precedentStateDef = stateDefId;
					int id = Integer.parseInt(stateDefId);
					
					if (id < 0) {
						for (StateDef statedef : spriteState.getNegativeStateSef()) {
							if (statedef.getIntId() == id) {
								precedentStateDefObj = statedef;
								break;
							}
						}
					} else {
						precedentStateDefObj = spriteState.getStateDef(id);
					}
						
					
					if (precedentStateDefObj == null) {
						precedentStateDef = stateDefId;
						precedentStateDefObj = spriteState.getStateDef(Integer.parseInt(stateDefId));
						spriteState.addStateDef(precedentStateDefObj);
					
					} else {
					}
				} else if (isMatch(P_STATE_CTRL_TITLE_REGEX, grp.getSectionRaw())) {
					if (skip) {
						continue;
					}
					Matcher m = P_STATE_CTRL_TITLE_REGEX.matcher(grp.getSectionRaw());
					m.find();
					String stateDefId = precedentStateDef;//;m.group(1);

					String stateCtrlId = m.group(1);
					try {
						parseStateCtrl(precedentStateDefObj, stateDefId, stateCtrlId, grp);
						
					} catch (Exception e) {
//						e.printStackTrace();
					}
				} else if ("command".equalsIgnoreCase(grp.getSection())) {
					MugenCommands mugenCommands = CmdParser.interpretCmd(grp);
					sprite.getCmds().add(mugenCommands);
				}
//				else if (isMatch(P_COMMENT_OR_EMPTY_REGEX, grp.getSectionRaw())) {
//
//				}
//			}
		}
	}

}
