package org.lee.mugen.core.debug;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.lee.mugen.sprite.cns.StateCtrl;
import org.lee.mugen.sprite.cns.StateDef;


public class Debug {
	private static Debug debug = new Debug();

	public static Debug getDebug() {
		return debug;
	}
	Debug() {

	}
	private AtomicBoolean stop = new AtomicBoolean();

	private boolean enable = true;
	private List<BreakPoint> breakPoints = new LinkedList<BreakPoint>();

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public boolean isStop() {
		return stop.get();
	}

	public void setStop(boolean stop) {
		this.stop.set(stop);
	}
	
	public boolean haveBreakPoint(String spriteId, StateDef statedef, BreakPosition pos) {
		return getBreakPoint(spriteId, statedef.getIntId(), pos) != null;
	}

	public boolean haveBreakPoint(String spriteId, StateDef statedef, StateCtrl statectrl, BreakPosition pos) {
		return getBreakPoint(spriteId, statedef.getIntId(), statedef.getStateCtrls().indexOf(statectrl), pos) != null;
	}

	public void addBreakPoint(BreakPoint breakPoint) {
		for (BreakPoint bp: breakPoints)
			if (bp.getType().equals(breakPoint))
				return;
		breakPoints.add(breakPoint);
	}
	public BreakPoint getBreakPoint(String spriteId, int statedef, BreakPosition pos) {
		if (pos == null)
			throw new NullPointerException();
		for (Iterator<BreakPoint> iter = breakPoints.iterator(); iter.hasNext();) {
			BreakPoint bp = iter.next();
			if (bp.getSpriteId().equals(spriteId)) {
				if (bp.getPosition().equals(pos)) {
					if (bp.getType() instanceof StatedefBreakPoint) {
						StatedefBreakPoint sbp = (StatedefBreakPoint) bp.getType();
						if (sbp.getStateDef() == statedef) {
							return bp;
						}
					}
				}
			}
		}
		return null;
	}
	public BreakPoint getBreakPoint(String spriteId, int statedef, int indexStateCtrl, BreakPosition pos) {
		if (pos == null)
			throw new NullPointerException();
		for (Iterator<BreakPoint> iter = breakPoints.iterator(); iter.hasNext();) {
			BreakPoint bp = iter.next();
			if (bp.getSpriteId().equals(spriteId)) {
				if (bp.getPosition().equals(pos)) {
					if (bp.getType() instanceof StatectrlBreakPoint) {
						StatectrlBreakPoint sbp = (StatectrlBreakPoint) bp.getType();
						if (sbp.getStateDef() == statedef && sbp.getStateCtrlPosition() == indexStateCtrl) {
							return bp;
						}
					}
				}
			}
		}
		return null;
	}
	public boolean hasBreakPointInStateCtrl(String spriteId, int statedef) {
		for (Iterator<BreakPoint> iter = breakPoints.iterator(); iter.hasNext();) {
			BreakPoint bp = iter.next();
			if (bp.getSpriteId().equals(spriteId)) {
				if (bp.getType() instanceof StatectrlBreakPoint) {
					StatectrlBreakPoint sbp = (StatectrlBreakPoint) bp.getType();
					if (sbp.getStateDef() == statedef) {
						return true;
					}
				}
			}
		}
		return false;
	}
	public void removeBreakPoint(String spriteId, int statedef, int indexStateCtrl, BreakPosition pos) {
		for (Iterator<BreakPoint> iter = breakPoints.iterator(); iter.hasNext();) {
			BreakPoint bp = iter.next();
			if (bp.getSpriteId().equals(spriteId)) {
				if (pos == null || bp.getPosition().equals(pos)) {
					if (bp.getType() instanceof StatectrlBreakPoint) {
						StatectrlBreakPoint sbp = (StatectrlBreakPoint) bp.getType();
						if (sbp.getStateDef() == statedef && sbp.getStateCtrlPosition() == indexStateCtrl) {
							iter.remove();
//							Debug.getDebug().setGo(true);
							Debug.getDebug().setStop(false);
						}
					}
				}
			}
		}
	}
	
	public void removeBreakPoint(String spriteId, int statedef, BreakPosition pos) {
		for (Iterator<BreakPoint> iter = breakPoints.iterator(); iter.hasNext();) {
			BreakPoint bp = iter.next();
			if (bp.getSpriteId().equals(spriteId)) {
				if (pos == null || bp.getPosition().equals(pos)) {
					if (bp.getType() instanceof StatedefBreakPoint) {
						StatedefBreakPoint sbp = (StatedefBreakPoint) bp.getType();
						if (sbp.getStateDef() == statedef) {
							iter.remove();
//							Debug.getDebug().setGo(true);
							Debug.getDebug().setStop(false);
						}
					}
				}
			}
		}
	}
}
