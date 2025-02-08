package org.lee.mugen.sprite.cns;

import static org.lee.mugen.sprite.parser.Parser.getFloatValue;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.core.debug.BreakPoint;
import org.lee.mugen.core.debug.BreakPosition;
import org.lee.mugen.core.debug.Debug;
import org.lee.mugen.parser.type.Functionable;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.renderer.GraphicsWrapper;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.cns.eval.function.MathFunction;
import org.lee.mugen.sprite.cns.eval.function.StateCtrlFunction;
import org.lee.mugen.sprite.cns.eval.operator.CnsOperatorsDef;
import org.lee.mugen.sprite.cns.type.function.Changestate;
import org.lee.mugen.sprite.cns.type.function.Selfstate;
import org.lee.mugen.sprite.cns.type.function.Targetstate;

public class StateCtrl implements Cloneable, Serializable {
    protected String stateDefId;
    protected String id;
    protected List<StateCtrlFunction> executors;
    protected Trigger mainTrigger;
    protected List<Trigger> _triggers = new ArrayList<Trigger>();
    protected int persistent = 1;
    protected int persistentCounter = 1;
    protected int ignorehitpause = 0;
    protected int ignorehitpauseCounter = 0;
    protected boolean hasInterrupFunction = false;
    
    @Override
    public String toString() {
    	return "StateId: " + stateDefId + " Trig. Count : " + _triggers.size();
    }
    
    public int getIgnorehitpause() {
		return ignorehitpause;
	}

	public void reset() {
    	persistentCounter = persistent;
    	ignorehitpause = ignorehitpauseCounter;
    	for (Functionable f: executors) {
    		f.reset();
    	}
    }
    
    public StateCtrl(String stateDefId, String stateCtrlId) {
    	this.stateDefId = stateDefId;
    	this.id = stateCtrlId;
    }


	public boolean testTriggered(String spriteId) {
		boolean isTriggered = false;
		if (getSprite(spriteId).isPause() && getIgnorehitpause() == 0 && Integer.parseInt(stateDefId) > 0) {
			return isTriggered = false;
		}
		if (getSprite(spriteId).isPause() && getIgnorehitpause() > 0 && getIgnorehitpause() != 1) {
			ignorehitpause--;
		}
		
		
		if (persistentCounter >= 0) {
			boolean mainTrig = true;
			try {
				if (mainTrigger != null)
					mainTrig = getFloatValue(mainTrigger.getValue(spriteId)) != 0;
				
			} catch (Exception e) {
				mainTrig = false;
				// TODO: handle exception
			}
			if (mainTrig) {

				if (_triggers.size() == 0) {
					isTriggered = true;
//					persistentCounter = persistentCounter == 0? 0: persistentCounter - 1;
				} else {
					for (Trigger trig: _triggers) {
						float trigValue = 0;
						Object o = null;
						try {
							o = trig.getValue(spriteId);
								
						} catch (Exception e) {
							e.printStackTrace();
						}
						if (o != null)
							trigValue = getFloatValue(o);
						if (trigValue != 0) {
							isTriggered = true;
//							persistentCounter = persistentCounter == 0? 0: persistentCounter - 1;
							break;
						}
					}
					
				}
			}
		}
		return isTriggered;
	}
	private int getPosition(String spriteId) {
		return GameFight.getInstance().getSpriteInstance(spriteId).getSpriteState().getStateDef(getStateDefId()).getStateCtrls().indexOf(this);
	}
	public boolean execute(String spriteId) {

		if (getSprite(spriteId).isPause())
			return false;
		////////// DEBUG BREAK POINT
		if (Debug.getDebug().isEnable()) {
			boolean isDebugStop = Debug.getDebug().isStop();

			BreakPoint bp = Debug.getDebug().getBreakPoint(spriteId, Integer.parseInt(getStateDefId()), getPosition(spriteId), BreakPosition.Before);
			Debug.getDebug().setStop(true);
			if (bp != null) {
				while (Debug.getDebug().isStop()) {
					GraphicsWrapper.getInstance().getInstanceOfGameWindow().render();
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		}
		////////// END DEBUG BREAK POINT
		if (testTriggered(spriteId)){
			////////// DEBUG BREAK POINT
			if (Debug.getDebug().isEnable()) {
				boolean isDebugStop = Debug.getDebug().isStop();

				BreakPoint bp = Debug.getDebug().getBreakPoint(spriteId, Integer.parseInt(getStateDefId()), getPosition(spriteId), BreakPosition.After);
				Debug.getDebug().setStop(true);
				if (bp != null) {
					while (Debug.getDebug().isStop()) {
						GraphicsWrapper.getInstance().getInstanceOfGameWindow().render();
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				
			}
			////////// END DEBUG BREAK POINT
			if (persistentCounter == 0 || (persistentCounter % persistent == 0 && persistentCounter != -1)) {
				for (StateCtrlFunction f: executors) {
					if (
							(Integer.parseInt(stateDefId) == -2)
						&&	
							(f.getClass() == Changestate.class
									||
									f.getClass() == Targetstate.class
									||
									f.getClass() == Selfstate.class
							))
					continue;
						
					f.getValue(spriteId);
				}
				
			} 
			if (persistent == 0)
				persistentCounter = -1;
			else {
				persistentCounter--;
				if (persistentCounter <= 0)
					persistentCounter = persistent;
			}
	
			return true;
		}

		return false;
	}
	private Sprite getSprite(String spriteId) {
		return GameFight.getInstance().getSpriteInstance(spriteId);
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

	//--------------------------//
	//  Getters And Setters
	//--------------------------//

	public List<Trigger> getTriggers() {
		return _triggers;
	}

	public void setTriggers(List<Trigger> triggers) {
		_triggers = triggers;
	}

	public List<StateCtrlFunction> getExecutors() {
		return executors;
	}

	public void setExecutors(List<StateCtrlFunction> executors) {
		this.executors = executors;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Trigger getMainTrigger() {
		return mainTrigger;
	}

	public void setMainTrigger(Trigger pTrigger) {
		if (mainTrigger == null) {
			this.mainTrigger = pTrigger;
		} else {
			final Valueable v1 = mainTrigger.getValueable();
			final Valueable v2 = pTrigger.getValueable();
			
			final MathFunction and = CnsOperatorsDef.getOperator("&&");
			final Valueable v = new Valueable() {

				public Object getValue(String spriteId, Valueable... params) {
					return and.getValue(spriteId, new Valueable[] {v1, v2});
				}
				
			};
			mainTrigger.setValueable(v);
		}
	}

	public int getPersistent() {
		return persistent;
	}

	public void setPersistent(int persistent) {
		this.persistent = persistent;
		persistentCounter = persistent;
	}

	public String getStateDefId() {
		return stateDefId;
	}

	public void setStateDefId(String stateDefId) {
		this.stateDefId = stateDefId;
	}

	public void setIgnorehitpause(int i) {
		ignorehitpause = i;
		ignorehitpauseCounter = i;
	}

	public void addExecutor(StateCtrlFunction executor) {
		if (executors == null)
			executors = new ArrayList<StateCtrlFunction>();
		this.executors.add(executor);
		hasInterrupFunction = hasInterrupFunction || executor.isInterrupt();
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
//		StateCtrl ctrl = new StateCtrl(stateDefId, id);
//		
//		ctrl.executors = executors;
//		ctrl.mainTrigger = mainTrigger;
//		ctrl._triggers = _triggers;
//		ctrl.persistent = persistent;
//		ctrl.persistentCounter = persistentCounter;
//		ctrl.ignorehitpause = ignorehitpause;
//	    
//	    
//		return ctrl;
	}

	public boolean isHasInterrupFunction() {
		return hasInterrupFunction;
	}


}
