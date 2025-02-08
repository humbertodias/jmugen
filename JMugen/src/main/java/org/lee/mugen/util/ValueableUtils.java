package org.lee.mugen.util;

import org.lee.mugen.parser.type.Valueable;

public class ValueableUtils {
	public static Object[] convert(Valueable[] vals) {
		Object[] result = new Object[vals.length];
		for (int i = 0; i < vals.length; ++i)
			result[i] = vals[i] == null? null: vals[i].getValue(null);
		return result;
	}
}
