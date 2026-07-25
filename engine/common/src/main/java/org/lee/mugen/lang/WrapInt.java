package org.lee.mugen.lang;

public class WrapInt implements Wrap<Integer>, Cloneable {
	private int value;
	public WrapInt(Integer o) {
		value = o;
	}
	public WrapInt() {}
	public void setValue(Integer o) {
		value = o;		
	}

	public Integer getValue() {
		return value;
	}

}
