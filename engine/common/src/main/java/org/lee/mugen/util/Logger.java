package org.lee.mugen.util;

import java.util.Date;

public class Logger {

    private Logger() {
    }

    public static void log(String str, Object... args) {
        System.out.println(time() + " - " + format(str, args));
    }

    public static void error(String str, Object... args) {
        System.err.println(time() + " - " + format(str, args));
    }

    private static String time() {
        Date d = new Date();
        return pad2(d.getHours()) + ":" + pad2(d.getMinutes()) + ":" + pad2(d.getSeconds());
    }

    private static String pad2(int n) {
        return n < 10 ? "0" + n : String.valueOf(n);
    }

    /** GWT has no {@link String#format(String, Object[])}. */
    private static String format(String str, Object[] args) {
        if (args == null || args.length == 0) {
            return str;
        }
        String out = str;
        for (Object arg : args) {
            int i = out.indexOf("%s");
            if (i < 0) {
                break;
            }
            out = out.substring(0, i) + String.valueOf(arg) + out.substring(i + 2);
        }
        return out;
    }
}
