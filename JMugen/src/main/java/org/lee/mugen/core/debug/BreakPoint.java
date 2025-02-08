package org.lee.mugen.core.debug;

public class BreakPoint {
	public BreakPoint(String spriteId, BreakType type, BreakPosition breakPosition) {
		this.spriteId = spriteId;
		this.type = type;
		this.position = breakPosition;
	}
	public void setSpriteId(String spriteId) {
		this.spriteId = spriteId;
	}

	public String getSpriteId() {
		return spriteId;
	}

	private String spriteId;
	private boolean isReach;
	private BreakPosition position;
	
	private BreakType type;

	@Override
	public boolean equals(Object obj) {
		if (obj != null && (obj instanceof BreakPoint)) {
			BreakPoint b = (BreakPoint) obj;
			return b.spriteId.equals(spriteId)
					&& b.type.equals(type) 
					&& b.isReach == isReach 
					&& b.position.equals(position);
		} 
		return false;
	}

	public BreakPosition getPosition() {
		return position;
	}

	public BreakType getType() {
		return type;
	}

	@Override
	public int hashCode() {
		int isReachHash = isReach? 11: 0;
		int setToBeInitHash = isReach? 13: 0;
		int typeHash = type.hashCode();
		int positionHash = position.hashCode();
		int spriteIdHash = spriteId.hashCode();
		return isReachHash * setToBeInitHash * typeHash * positionHash * spriteIdHash;
	}

	public void init() {
		isReach = false;
	}

	public boolean isReach() {
		return isReach;
	}


	public void setPosition(BreakPosition position) {
		this.position = position;
	}
	
	public void setReach(boolean isReach) {
		this.isReach = isReach;
	}
	
	public void setType(BreakType type) {
		this.type = type;
	}
	
}
