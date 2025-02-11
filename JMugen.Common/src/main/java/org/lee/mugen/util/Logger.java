package org.lee.mugen.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
	private static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss S");
	public static void log(String str, Object ... args) {
		System.out.println(sdf.format(new Date()) + " - " + String.format(str, args));
	}
	public static void error(String str, Object ... args) {
		System.err.println(sdf.format(new Date()) + " - " + String.format(str, args));
	}
}
