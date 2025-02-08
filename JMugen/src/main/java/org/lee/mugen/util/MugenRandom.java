package org.lee.mugen.util;

import java.util.Random;

public class MugenRandom {
	private static Random _random;
	
	public static Random getInstanceOf() {
		if (_random == null) {
			_random = new Random();
		}
		return _random;
	}
	
	public static int getRandomNumber() {
		return getInstanceOf().nextInt(59);
	}
	public static int getRandomNumber(int start, int end) {
		if (start == end)
			return 0;
		int realStart = start;
		if (start < 0) {
			start = -start;
			end += start;
			start = 0;
		} else {
			realStart = 0;
		}
		return (getInstanceOf().nextInt(end) + start) + realStart;
	}


}
