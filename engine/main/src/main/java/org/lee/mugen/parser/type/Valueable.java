package org.lee.mugen.parser.type;

import java.io.Serializable;


public interface Valueable extends Serializable {
	public Object getValue(String spriteId, Valueable...params);
}

