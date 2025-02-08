package org.lee.mugen.core.debug;

public class StatectrlBreakPoint extends BreakType {
	private int stateCtrlPosition;
	public StatectrlBreakPoint(int stateDef, int stateCtrlPosition) {
		super(stateDef);
		this.stateCtrlPosition = stateCtrlPosition;
	}
	public int getStateCtrlPosition() {
		return stateCtrlPosition;
	}
	public void setStateCtrlPosition(int stateCtrlPosition) {
		this.stateCtrlPosition = stateCtrlPosition;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj != null && (obj instanceof StatectrlBreakPoint)) {
			StatectrlBreakPoint b = (StatectrlBreakPoint) obj;
			return b.stateDef == stateDef && stateCtrlPosition == b.stateCtrlPosition;
		}
		return false;
	}
	@Override
	public int hashCode() {
		return (stateDef + "" + stateCtrlPosition).hashCode();
	}
}