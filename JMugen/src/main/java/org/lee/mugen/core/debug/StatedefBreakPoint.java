package org.lee.mugen.core.debug;

public class StatedefBreakPoint extends BreakType {

	public StatedefBreakPoint(int stateDef) {
		super(stateDef);
	}
	@Override
	public boolean equals(Object obj) {
		if (obj != null && (obj instanceof StatedefBreakPoint)) {
			return super.equals(obj);
		}
		return false;
	}
}