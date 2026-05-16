package org.lee.mugen.background;

import static org.lee.mugen.sprite.parser.Parser.getFloatValue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.lee.mugen.background.bgCtrlFunction.BgCtrlFunctionDef;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.cns.Trigger;
import org.lee.mugen.sprite.cns.eval.function.MathFunction;
import org.lee.mugen.sprite.cns.eval.function.StateCtrlFunction;
import org.lee.mugen.sprite.cns.eval.operator.CnsOperatorsDef;
import org.lee.mugen.sprite.parser.ExpressionFactory;
import org.lee.mugen.sprite.parser.Parser;
import org.lee.mugen.sprite.parser.Parser.GroupText;
import org.lee.mugen.util.BeanTools;
import org.lee.mugen.util.Logger;

public class BGCtrlDef {
	public static final String _END = "(?:(?: *;.*$)|(?: *$))";
	public static final String _COMMENT_OR_EMPTY_REGEX = "^ *;.*$|^ *$";
	public static final String _FLOAT_REGEX = "((?:\\+|-)?(?:(?:\\.\\d+)|(?:\\d+(?:\\.\\d*)?)))";// "[+-]*(?:\\d+\\.\\d+)|(?:\\d+)";
	private static final String _TRIGGER_MAIN = "\\s*triggerall *=.*" + _END;

	private static final String _TRIGGER = "\\s*trigger(\\d+) *=.*" + _END;
	
	
	public static class BGCtrl {
	    protected List<StateCtrlFunction> executors;
	    protected Trigger mainTrigger;
	    protected List<Trigger> _triggers = new ArrayList<Trigger>();
	    protected int persistent = 1;
	    protected int persistentCounter = 1;
	    protected int ignorehitpause = 0;
	    protected int ignorehitpauseCounter = 0;
	    protected boolean hasInterrupFunction = false;
		private BGCtrlDef bgCtrlDef;
	    
		public List<StateCtrlFunction> getExecutors() {
			return executors;
		}

		public void setExecutors(List<StateCtrlFunction> executors) {
			this.executors = executors;
		}

		public Trigger getMainTrigger() {
			return mainTrigger;
		}

		public void setMainTrigger(Trigger mainTrigger) {
			this.mainTrigger = mainTrigger;
		}

		public List<Trigger> getTriggers() {
			return _triggers;
		}
		public int getPersistent() {
			return persistent;
		}

		public void setPersistent(int persistent) {
			this.persistent = persistent;
		}

		public int getIgnorehitpause() {
			return ignorehitpause;
		}

		public void setIgnorehitpause(int ignorehitpause) {
			this.ignorehitpause = ignorehitpause;
		}

		public int getIgnorehitpauseCounter() {
			return ignorehitpauseCounter;
		}

		public void setIgnorehitpauseCounter(int ignorehitpauseCounter) {
			this.ignorehitpauseCounter = ignorehitpauseCounter;
		}

		public boolean isHasInterrupFunction() {
			return hasInterrupFunction;
		}

		public void setHasInterrupFunction(boolean hasInterrupFunction) {
			this.hasInterrupFunction = hasInterrupFunction;
		}

		public int getPersistentCounter() {
			return persistentCounter;
		}

		public boolean testTriggered(String id) {
			boolean isTriggered = false;
//			if (getSprite(id).isPause() && getIgnorehitpause() == 0) {
//				return isTriggered = false;
//			}
//			if (getSprite(id).isPause() && getIgnorehitpause() > 0) {
//				ignorehitpause--;
//			}
			
			if (persistentCounter >= 0) {
				boolean mainTrig = true;
				try {
					if (mainTrigger != null)
						mainTrig = getFloatValue(mainTrigger.getValue(id)) != 0;
					
				} catch (Exception e) {
					mainTrig = false;
					// TODO: handle exception
				}
				if (mainTrig) {

					if (_triggers.size() == 0) {
						isTriggered = true;
						persistentCounter = persistentCounter == 0? 0: persistentCounter - 1;
					} else {
						for (Trigger trig: _triggers) {
							float trigValue = 0;
							Object o = null;
							try {
								o = trig.getValue(id);
									
							} catch (Exception e) {
								// TODO: handle exception
							}
							if (o != null)
								trigValue = getFloatValue(o);
							if (trigValue != 0) {
								isTriggered = true;
								persistentCounter = persistentCounter == 0? 0: persistentCounter - 1;
								break;
							}
						}
						
					}
				}
			}
			return isTriggered;
		}
		
		public boolean execute(int ctrlid) {
			if (testTriggered(ctrlid + "")){
				if (persistentCounter == 0) {
					if (persistent == 0)
						persistentCounter = -1;
					else
						persistentCounter = persistent;
					for (StateCtrlFunction f: executors) {
						f.getValue(ctrlid + "");
					}
				}
				return true;
			}
			return false;
		}

		//----------------//
		// Functions
		//----------------//
		private static final Comparator<Trigger> _TRIGGER_COMPARATOR = new Comparator<Trigger>() {

			public int compare(Trigger o1, Trigger o2) {
				return o1.getPrio() - o2.getPrio();
			}};
		
		public void addTrigger(Trigger trigger) {
			boolean find = false;
			for (Trigger trig: _triggers) {
				if (trig.getPrio() == trigger.getPrio()) {
					find = true;
					final Valueable v1 = trig.getValueable();
					final Valueable v2 = trigger.getValueable();
					
					final MathFunction and = CnsOperatorsDef.getOperator("&&");
					final Valueable v = new Valueable() {

						public Object getValue(String spriteId, Valueable... params) {
							return and.getValue(spriteId, new Valueable[] {v1, v2});
						}
						
					};
					trig.setValueable(v);
					break;
				}
			}
			if (!find) {
				_triggers.add(trigger);
				Collections.sort(_triggers, _TRIGGER_COMPARATOR);
			}
		}

		public void setTriggers(List<Trigger> triggers) {
			_triggers = triggers;
		}
		public void addExecutor(StateCtrlFunction executor) {
			if (executors == null)
				executors = new ArrayList<StateCtrlFunction>();
			this.executors.add(executor);
			hasInterrupFunction = hasInterrupFunction || executor.isInterrupt();
		}
		
		Background parent;
		public BGCtrl(BGCtrlDef bgCtrlDef, String id) {
			this.bgCtrlDef = bgCtrlDef;
		}

		public static BGCtrl parseBGCtrl(
				BGCtrlDef precedentStateDefObj, 
				String bgDefId, 
				String bgCtrlId,
				GroupText grp) throws Exception {
			StringTokenizer strToken = new StringTokenizer(grp.getText().toString(), "\r\n");
			BGCtrl bgCtrl = new BGCtrl(precedentStateDefObj, bgCtrlId);
			StateCtrlFunction bgCtrlFunction = null;
			
			while (strToken.hasMoreTokens()) {
				String f = strToken.nextToken();
				String line =  Parser.toLowerCase(f);
				if (org.lee.mugen.util.MugenPatterns.matchesInsensitive(_COMMENT_OR_EMPTY_REGEX, line.trim())) {
					continue;
				}
				String[] keyValue = getSeparateKeyValue(line, strToken);
				final String name = keyValue[0];
				String value = keyValue[1];

				
				if (org.lee.mugen.util.MugenPatterns.matchesInsensitive(_TRIGGER_MAIN, line)) {
//					boolean isTarget = false;
//					isTarget = value.startsWith("target");
//					if (isTarget)
//						value = value.replaceFirst("target *,", "");
					String[] tokens = ExpressionFactory.expression2Tokens(value);
					final Valueable[] valueables = ExpressionFactory.evalExpression(tokens, false, true);
					assert valueables != null && valueables.length > 0 : "parseStateCtrl valueables is empty";
					Valueable valueToBeTrig = valueables[0];
					Trigger trigger = new Trigger(valueToBeTrig);
					bgCtrl.setMainTrigger(trigger);

				} else if (org.lee.mugen.util.MugenPatterns.matchesInsensitive(_TRIGGER, line)) {
					String[] tokens = ExpressionFactory.expression2Tokens(value);
					Matcher m = org.lee.mugen.util.MugenPatterns.matcherInsensitive(_TRIGGER, line);
					m.find();
					int prio = Integer.parseInt(m.group(1));

					try {
						ExpressionFactory.evalExpression(tokens, false, true);
					} catch (Exception e) {
						System.err.println("Exception skip error : " + " bgCtrlDef = " + bgDefId + " bgCtrl" + bgCtrlId + ">" + line);
					}
					final Valueable[] valueables = ExpressionFactory.evalExpression(tokens, false, true);
					assert valueables != null && valueables.length > 0 : "parseBgCtrl valueables is empty";
					Valueable valueToBeTrig = valueables[0];
					Trigger trigger = new Trigger(valueToBeTrig);

					trigger.setPrio(prio);
					bgCtrl.addTrigger(trigger);
				} else if ("type".equals(name)) {
					bgCtrlFunction = BgCtrlFunctionDef.getStateCtrlFunc(value);
					if (bgCtrlFunction == null) {
						Logger.log("TODO : type = " + value);
						return null; // TODO remove
					}

					bgCtrl.addExecutor(bgCtrlFunction);
				} else if ("time".equals(name)) {
					try {
						ExpressionFactory.evalExpression(ExpressionFactory.expression2Tokens(line), false, true);
					} catch (Exception e) {
						System.err.println("Exception skip error : " + " bgCtrlDef = " + bgDefId + " bgCtrl" + bgCtrlId + ">" + line);
					}
					final Valueable[] valueables = ExpressionFactory.evalExpression(ExpressionFactory.expression2Tokens(line), false, true);
					assert valueables != null && valueables.length > 0 : "parseBgCtrl valueables is empty";
					Valueable valueToBeTrig = valueables[0];
					Trigger trigger = new Trigger(valueToBeTrig);

					trigger.setPrio(1);
					bgCtrl.addTrigger(trigger);
				} else if ("persistent".equals(name)) {
					bgCtrl.setPersistent(Integer.parseInt(value));
				} else if ("ignorehitpause".equals(name)) {
					bgCtrl.setIgnorehitpause(value == null || value.trim().length() == 0? 1: Integer.parseInt(value));
//				} 
//				else if ("ctrl".equals(name)) {
//					String[] tokens = ExpressionFactory.expression2Tokens(value);
	//
//					final Valueable[] vals = ExpressionFactory.evalExpression(tokens, false, true);
//					Ctrlset ctrlset = new Ctrlset();
//					ctrlset.addParam("value", vals);
//					
//					stateCtrl.addExecutor(ctrlset);
				} else {
					try {

						Valueable[] valueables = bgCtrlFunction.parseValue(name, value);
						bgCtrlFunction.addParam(name, valueables);
						
					} catch (RuntimeException e) {
						System.err.println(
								"Parsing BGCtrlDef for Type : " + bgCtrlFunction.getFunctionName() + 
								" in bgDefId = " + bgDefId + 
								" - bgCtrlId = " + bgCtrlId + 
								" doesn't take this argument >> " + line);
					}
				}
			}
			try {
				if (!bgCtrlFunction.control()) {
					throw new IllegalStateException("Some  of mandatory field aren't filled for this " + bgCtrlFunction.getFunctionName());
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			precedentStateDefObj.addBgCtrl(bgCtrl);
//			spriteInfo.addStateCtrl(stateCtrl);
			return bgCtrl;
		}

		public void process() {
			
			execute(getBgCtrlDef().getCtrlid());
			
		}

		public BGCtrlDef getBgCtrlDef() {
			return bgCtrlDef;
		}

		public void setBgCtrlDef(BGCtrlDef bgCtrlDef) {
			this.bgCtrlDef = bgCtrlDef;
		}
	}
	Background parent;
	protected BGCtrlDef(Background parent, String id) {
		this.id = id;
		this.parent = parent;
	}

	public void addBgCtrl(BGCtrl bgCtrl) {
		bGCtrList.add(bgCtrl);	
	}

	private String id;
	private int looptime = 0;
	private int counttime = 0;
	private int ctrlid = 0;
	private List<BGCtrl> bGCtrList = new ArrayList<BGCtrl>();
	private ArrayList<BG> bgCopys = null;
	


	public ArrayList<BG> getBgCopys() {
		return bgCopys;
	}

	public void setBgCopys(ArrayList<BG> bgCopys) {
		this.bgCopys = bgCopys;
	}

	public void process() {
		if (counttime > looptime && looptime != 0) {
			counttime = 0;
		}
		ArrayList<BG> bgs = parent.getBgsMap().get(getCtrlid());
		bgs = (ArrayList<BG>) bgs.clone();
		if (counttime == 0 || (counttime == 0 && looptime == 0)) {
			for (BG bg: bgs) {
				bg.getAnimManager().setAction(bg.getActionno());
			}
			setBgCopys(bgs);
		}
		for (BGCtrl bgCtrl: bGCtrList) {
			bgCtrl.process();
		}
		for (BG bg: bgs) {

			bg.process();
		}
		
		counttime++;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getLooptime() {
		return looptime;
	}

	public void setLooptime(int looptime) {
		this.looptime = looptime;
	}

	public int getCtrlid() {
		return ctrlid;
	}

	public void setCtrlid(int ctrlid) {
		this.ctrlid = ctrlid;
	}

	public List<BGCtrl> getBGCtrList() {
		return bGCtrList;
	}

	public void setBGCtrList(List<BGCtrl> ctrList) {
		bGCtrList = ctrList;
	}

	private static String[] getSeparateKeyValue(String line,
			StringTokenizer tokens) {
		String[] keyValue = new String[2];
		if (org.lee.mugen.util.MugenPatterns.matchesInsensitive(_COMMENT_OR_EMPTY_REGEX, line))
			return null;
		line = Parser.toLowerCase(line.trim());
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
		keyValue[1].replaceAll(_END, "");
		if (keyValue[1].endsWith("\\n")) {
			int index = keyValue[1].indexOf("\\n");
			keyValue[1] = keyValue[1].substring(0, index);
			String addLine = "\\n";
			while (tokens.hasMoreTokens() && addLine.endsWith("\\n")) {
				addLine = tokens.nextToken();
				addLine.replaceAll(_END, "");
				keyValue[1] += addLine;
				index = keyValue[1].indexOf("\\n");
				if (index != -1)
					keyValue[1] = keyValue[1].substring(0, index);
			}
		}
		return keyValue;
	}

	// /////////////:
	public static BGCtrlDef parseBGCtrlDef(Background parent, String bGCtrlDefId, GroupText grp)
			throws Exception {

		BGCtrlDef bgCtrlDef = new BGCtrlDef(parent, bGCtrlDefId);

		StringTokenizer strToken = new StringTokenizer(
				grp.getText().toString(), "\r\n");
		while (strToken.hasMoreTokens()) {
			String line = strToken.nextToken().toLowerCase();
			if (org.lee.mugen.util.MugenPatterns.matchesInsensitive(_COMMENT_OR_EMPTY_REGEX, line)) {
				continue;
			}
			String[] keyValue = getSeparateKeyValue(line, strToken);
			final String name = keyValue[0];
			final String value = keyValue[1];

			String[] tokens = ExpressionFactory.expression2Tokens(value);
			final Valueable[] values;

			try {
				values = ExpressionFactory.evalExpression(tokens, false, true);

			} catch (Exception e) {
				System.err.println("This line is not good for bGCtrlDefId "
						+ bGCtrlDefId + "\n" + line);
				continue;
			}
			Object[] objectValues = new Object[values.length];
			for (int i = 0; i < objectValues.length; ++i) {
				objectValues[i] = values[i].getValue(null);
			}

			try {
				if (objectValues.length == 1) {
					BeanTools.setObject(bgCtrlDef, name, objectValues[0]);
				} else if (objectValues.length > 1) {
					BeanTools.setObject(bgCtrlDef, name, objectValues);

				}
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(bgCtrlDef.getClass().getName()
						+ " setProperties cause problem ! >" + name + " = "
						+ objectValues);
			}
			
		}
		return bgCtrlDef;
	}

	public int getCounttime() {
		return counttime;
	}
}
