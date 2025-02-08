package org.lee.mugen.core.debug;

public abstract class BreakType {
	int stateDef;
	public BreakType(int stateDef) {
		this.stateDef = stateDef;
	}
	public int getStateDef() {
		return stateDef;
	}

	public void setStateDef(int stateDef) {
		this.stateDef = stateDef;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj != null && (obj instanceof BreakType)) {
			BreakType b = (BreakType) obj;
			return b.stateDef == stateDef;
		}
		return false;
	}
	@Override
	public int hashCode() {
		return (stateDef + "").hashCode();
	}
}