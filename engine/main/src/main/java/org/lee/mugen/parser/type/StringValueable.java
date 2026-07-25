package org.lee.mugen.parser.type;

public class StringValueable implements Valueable {
	private String value;
	public StringValueable(String value) {
		this.value = value;
	}
	
	public String getValue(String spriteId, Valueable... params) {
		return value;
	}


}
