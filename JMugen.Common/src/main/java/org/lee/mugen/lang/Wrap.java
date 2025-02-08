package org.lee.mugen.lang;

import java.io.Serializable;

public interface Wrap<T> extends Serializable {
	void setValue(T o);
	T getValue();
}
