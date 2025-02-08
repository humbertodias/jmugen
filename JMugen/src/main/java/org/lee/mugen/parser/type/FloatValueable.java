package org.lee.mugen.parser.type;

public class FloatValueable implements Valueable {

	private float value;
	public FloatValueable(float value) {
		this.value = value;
	}
	
	public Float getValue(String spriteId, Valueable... params) {
		return value;
	}

}
