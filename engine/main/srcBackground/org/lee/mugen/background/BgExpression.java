package org.lee.mugen.background;

import java.util.ArrayList;
import java.util.List;
import org.lee.mugen.parser.type.FloatValueable;
import org.lee.mugen.parser.type.IntValueable;
import org.lee.mugen.parser.type.StringValueable;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.entity.SprGrpNum;

/**
 * Regex-free expression parsing for title-screen {@code .def} BG fields (GWT/JS safe).
 */
public final class BgExpression {

    private BgExpression() {
    }

    public static Object[] eval(String key, String value) {
        if (value == null || value.trim().isEmpty()) {
            return new Object[] { null };
        }
        String k = key.toLowerCase();
        if ("type".equals(k) || "trans".equals(k) || "spr".equals(k)) {
            return new Object[] { value.trim() };
        }
        if ("spriteno".equals(k)) {
            float[] nums = parseNumberList(value);
            if (nums.length >= 2) {
                return new Object[] { new SprGrpNum((int) nums[0], (int) nums[1]) };
            }
            if (nums.length == 1) {
                return new Object[] { new SprGrpNum((int) nums[0], 0) };
            }
            return new Object[] { value.trim() };
        }
        Valueable[] vals = evalValueables(value);
        Object[] out = new Object[vals.length];
        for (int i = 0; i < vals.length; i++) {
            out[i] = vals[i].getValue(null);
        }
        return out;
    }

    private static Valueable[] evalValueables(String value) {
        String[] tokens = tokenize(value.trim());
        List<Valueable> out = new ArrayList<Valueable>();
        for (int i = 0; i < tokens.length; i++) {
            String t = tokens[i];
            if (",".equals(t)) {
                continue;
            }
            if ("-".equals(t) && i + 1 < tokens.length && isNumberToken(tokens[i + 1])) {
                out.add(new FloatValueable(-Float.parseFloat(tokens[++i])));
            } else if ("+".equals(t) && i + 1 < tokens.length && isNumberToken(tokens[i + 1])) {
                out.add(new FloatValueable(Float.parseFloat(tokens[++i])));
            } else if (isNumberToken(t)) {
                out.add(new FloatValueable(Float.parseFloat(t)));
            } else {
                out.add(new StringValueable(t));
            }
        }
        if (out.isEmpty()) {
            out.add(IntValueable.Zero);
        }
        return out.toArray(new Valueable[out.size()]);
    }

    private static float[] parseNumberList(String value) {
        String[] tokens = tokenize(value.trim());
        List<Float> nums = new ArrayList<Float>();
        for (int i = 0; i < tokens.length; i++) {
            if (",".equals(tokens[i])) {
                continue;
            }
            if ("-".equals(tokens[i]) && i + 1 < tokens.length && isNumberToken(tokens[i + 1])) {
                nums.add(-Float.parseFloat(tokens[++i]));
            } else if (isNumberToken(tokens[i])) {
                nums.add(Float.parseFloat(tokens[i]));
            }
        }
        float[] arr = new float[nums.size()];
        for (int i = 0; i < nums.size(); i++) {
            arr[i] = nums.get(i);
        }
        return arr;
    }

    private static boolean isNumberToken(String t) {
        if (t == null || t.isEmpty()) {
            return false;
        }
        char c0 = t.charAt(0);
        return Character.isDigit(c0) || c0 == '.' || (t.length() > 1 && Character.isDigit(t.charAt(1)));
    }

    public static String[] tokenize(String exp) {
        List<String> list = new ArrayList<String>();
        int i = 0;
        int n = exp.length();
        while (i < n) {
            char c = exp.charAt(i);
            if (Character.isWhitespace(c)) {
                i++;
                continue;
            }
            if (c == ',' || c == '(' || c == ')' || c == '[' || c == ']') {
                list.add(String.valueOf(c));
                i++;
                continue;
            }
            if (c == '+' || c == '-') {
                list.add(String.valueOf(c));
                i++;
                continue;
            }
            if (Character.isLetter(c) || c == '_') {
                int start = i;
                i++;
                while (i < n) {
                    char d = exp.charAt(i);
                    if (Character.isLetterOrDigit(d) || d == '_' || d == '.') {
                        i++;
                    } else {
                        break;
                    }
                }
                list.add(exp.substring(start, i));
                continue;
            }
            if (Character.isDigit(c) || c == '.') {
                int start = i;
                i++;
                while (i < n) {
                    char d = exp.charAt(i);
                    if (Character.isDigit(d) || d == '.') {
                        i++;
                    } else {
                        break;
                    }
                }
                list.add(exp.substring(start, i));
                continue;
            }
            i++;
        }
        return list.toArray(new String[list.size()]);
    }
}
