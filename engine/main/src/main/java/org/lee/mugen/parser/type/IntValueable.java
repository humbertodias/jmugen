package org.lee.mugen.parser.type;

public class IntValueable implements Valueable {
	public static final IntValueable Zero = new IntValueable(0);
	private int value;
	public IntValueable(int value) {
		this.value = value;
	}
	
	public Integer getValue(String spriteId, Valueable... params) {
		return value;
	}


}
