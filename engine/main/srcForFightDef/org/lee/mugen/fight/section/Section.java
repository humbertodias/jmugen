package org.lee.mugen.fight.section;

import java.io.Serializable;


public interface Section extends Serializable {

	public abstract void parse(Object root, String name, String value) throws Exception;

	
}
