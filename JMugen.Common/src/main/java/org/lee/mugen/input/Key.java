package org.lee.mugen.input;

/**
 * Definition of key enum
 * @author Dr Wong
 *
 */
public enum Key {

	B("BACK", 1), D("DOWN", 2), F("FORWARD", 4), U("UP", 8),
	DB("BACK_DOWN", 1|2), DF("FORWARD_DOWN", 2|4), UF("FORWARD_UP", 4|8), UB("BACK_UP", 1|8),
	a("a", 16), b("b", 32), c("c", 64), x("x", 128), y("y", 256), z("z", 512);

	Key(String desc, int bit) {
		this.bit = bit;
		this.desc = desc;
	}
	
	public final int bit;
	public final String desc;
	
	public static Key getKey(int bit) {
		for (Key k: Key.values()) {
			if (bit == k.bit)
				return k;
		}
		return null;
	}
}
