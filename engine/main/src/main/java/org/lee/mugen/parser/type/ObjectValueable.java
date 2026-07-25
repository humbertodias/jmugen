package org.lee.mugen.parser.type;

public class ObjectValueable implements Valueable {
	private Object value;
	public ObjectValueable(Object value) {
		this.value = value;
	}
	
	public Object getValue(String spriteId, Valueable... params) {
		return value;
	}

}
