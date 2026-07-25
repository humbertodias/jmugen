package org.lee.mugen.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** GWT-safe case-insensitive {@link Pattern} (no {@link Pattern#CASE_INSENSITIVE}). */
public final class MugenPatterns {

    private MugenPatterns() {
    }

    public static Pattern compileInsensitive(String regex) {
        return Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    }

    public static boolean matchesInsensitive(String regex, CharSequence input) {
        return compileInsensitive(regex).matcher(input).matches();
    }

    public static Matcher matcherInsensitive(String regex, CharSequence input) {
        return compileInsensitive(regex).matcher(input);
    }

    public static boolean findInsensitive(String regex, CharSequence input) {
        return matcherInsensitive(regex, input).find();
    }
}
