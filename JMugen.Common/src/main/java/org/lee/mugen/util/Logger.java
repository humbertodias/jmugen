package org.lee.mugen.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
	private static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss S");
	public static void log(String str) {
		System.out.println(sdf.format(new Date()) + " - " + str);
	}
}
