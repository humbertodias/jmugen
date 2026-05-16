package org.lee.mugen.util;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * GWT: no {@code (?i)} and no {@link Pattern#CASE_INSENSITIVE}; lowercase ASCII text + regex.
 */
public final class MugenPatterns {

    private MugenPatterns() {
    }

    /** Prefer {@link #matcherInsensitive} / {@link #findInsensitive} on GWT. */
    public static Pattern compileInsensitive(String regex) {
        return Pattern.compile(regex.toLowerCase(Locale.ROOT));
    }

    public static Matcher matcherInsensitive(String regex, CharSequence input) {
        return Pattern.compile(regex.toLowerCase(Locale.ROOT)).matcher(lower(input));
    }

    public static boolean matchesInsensitive(String regex, CharSequence input) {
        return matcherInsensitive(regex, input).matches();
    }

    public static boolean findInsensitive(String regex, CharSequence input) {
        return matcherInsensitive(regex, input).find();
    }

    private static String lower(CharSequence input) {
        return input == null ? "" : input.toString().toLowerCase(Locale.ROOT);
    }
}
