package org.lee.mugen.sprite.parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.lee.mugen.background.BgExpression;
import org.lee.mugen.background.bgCtrlFunction.BgFunctionDef;
import org.lee.mugen.parser.type.FloatValueable;
import org.lee.mugen.parser.type.Functionable;
import org.lee.mugen.parser.type.IntValueable;
import org.lee.mugen.parser.type.StringValueable;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.cns.eval.function.MathFunction;
import org.lee.mugen.sprite.cns.eval.function.MathsFunctionDef;
import org.lee.mugen.sprite.cns.eval.function.SpriteCnsTriggerFunction;
import org.lee.mugen.sprite.cns.eval.operator.CnsOperatorsDef;
import org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns.Gethitvar;

/**
 * GWT: expression evaluation for title backgrounds ({@code isProcessSprite=false}).
 */
public class ExpressionFactory {

    private static final String _OPEN_BRACET_GRP_REGEX = "\\(";
    private static final String _CLOSE_BRACET_GRP_REGEX = "\\)";
    private static final String _OPERATOR_REGEX = CnsOperatorsDef.getOperatorRegex();
    private static final String _TRIGGER_MATHS_FUNCTION_REGEX = MathsFunctionDef.getMathsFunctionDefRegex();
    private static final String _CONST_SPRITE_REGEX = "(\\bconst\\b *\\([a-zA-Z0-9\\.]*\\))";
    private static final String _TRIGGER_FUNCTION_SPRITE_REGEX = "(?!x)x";
    private static final String _TRIGGER_FUNCTION_BG_REGEX = BgFunctionDef.getCnsTriggerFunctionRegex();
    private static final String _FLOAT_REGEX = "((?:\\+|-)?(?:(?:\\.\\d+)|(?:\\d+(?:\\.\\d*)?)))";
    private static final String _STRING_REX_EXP = "(\"[^\"\\\\]*(\\\\.[^\"\\\\]*)*\")" + "|" + Gethitvar.getConstRegex() + "|" + "([a-zA-Z][a-zA-Z0-9._]*)";
    private static final String _CONST_STRING_REG_EXP = Gethitvar.getConstRegex();
    private static final String _SPECIAL_OPERATOR_REGEX = "(?:(=|!=) *((?:\\[|\\()) *"
            + _FLOAT_REGEX + " *, *" + _FLOAT_REGEX + " *((?:\\]|\\))))";
    private static final String _CONST_STRING = "(\"[^\"\\\\]*(\\\\.[^\"\\\\]*)*\")";

    public static final Pattern _TOKENIZE_CNS_REGEX = Pattern.compile(
            _CONST_STRING + "|" + _CONST_SPRITE_REGEX + "|" + _CONST_STRING_REG_EXP + "|"
                    + _TRIGGER_MATHS_FUNCTION_REGEX + "|" + _TRIGGER_FUNCTION_SPRITE_REGEX + "|"
                    + _TRIGGER_FUNCTION_BG_REGEX + "|" + _SPECIAL_OPERATOR_REGEX + "|" + _OPERATOR_REGEX + "|"
                    + _FLOAT_REGEX + "|" + _OPEN_BRACET_GRP_REGEX + "|" + _CLOSE_BRACET_GRP_REGEX + "|"
                    + _STRING_REX_EXP);

    private static boolean isThisTokenIsOp(String[] tokens, int pos) {
        if (pos == 0) {
            return false;
        }
        return !org.lee.mugen.util.MugenPatterns.matchesInsensitive(
                "\\(|\\[|,|=|<|>|-|\\+|<=|>=|&&|\\|\\||&|\\||!=|\\*", tokens[pos - 1]);
    }

    private static String[] expressionSpecialOp2Tokens(String exp, Pattern regex) {
        Matcher matcher = regex.matcher(exp);
        List<String> list = new ArrayList<String>();
        matcher.find();
        list.add(matcher.group(1));
        list.add(matcher.group(2));
        list.add(matcher.group(3));
        list.add(matcher.group(4));
        list.add(matcher.group(5));
        return list.toArray(new String[0]);
    }

    public static String[] getCloseInTokens(String[] strs, int start, String openStr, String closeStr) {
        if (!openStr.equals(strs[start])) {
            throw new IllegalArgumentException("Token must begin by '" + openStr + "'");
        }
        int open = 0;
        int i = start;
        List<String> list = new ArrayList<String>();
        do {
            if (openStr.equals(strs[i])) {
                open++;
            } else if (closeStr.equals(strs[i])) {
                open--;
            }
            if (open == 0) {
                break;
            }
            if (i != start && i != strs.length - 1) {
                list.add(strs[i]);
            }
            i++;
        } while (i < strs.length && open != 0);
        return list.toArray(new String[list.size()]);
    }

    public static String[] expression2Tokens(String exp) {
        return expression2Tokens(exp, _TOKENIZE_CNS_REGEX);
    }

    /** GWT/JS: hand tokenizer — avoids {@code Matcher#start}/{@code #end} and zero-width regex loops. */
    public static String[] expression2Tokens(String exp, Pattern regex) {
        if (exp == null || exp.trim().isEmpty()) {
            return new String[0];
        }
        return BgExpression.tokenize(exp.trim());
    }

    public static Valueable[] evalExpression(String str) {
        return evalExpression(expression2Tokens(str));
    }

    public static final Pattern P_OPEN_BRACET_GRP_REGEX = Pattern.compile(_OPEN_BRACET_GRP_REGEX);
    public static final Pattern P_FLOAT_REGEX = Pattern.compile(_FLOAT_REGEX);
    private static final Pattern P_SPECIAL_OPERATOR_REGEX = Pattern.compile(_SPECIAL_OPERATOR_REGEX);
    private static final Pattern P_OPERATOR_REGEX = Pattern.compile(_OPERATOR_REGEX);
    private static boolean isMatch(Pattern reg, String input) {
        return reg.matcher(input).matches();
    }

    private static boolean isMatchInsensitive(String regex, String input) {
        return org.lee.mugen.util.MugenPatterns.matchesInsensitive(regex, input);
    }

    public static Valueable[] evalExpression(String[] tokens) {
        return evalExpression(tokens, false, true);
    }

    public static Valueable[] evalExpression(String[] tokens, boolean isProcessSprite, boolean isProcessBg) {
        final LinkedList<Valueable> values = new LinkedList<Valueable>();
        final LinkedList<MathFunction> ops = new LinkedList<MathFunction>();
        int i = 0;
        ArrayList<Valueable> valuableList = new ArrayList<Valueable>();
        while (i < tokens.length) {
            if (isMatch(P_OPEN_BRACET_GRP_REGEX, tokens[i])) {
                String[] subTokens = getCloseInTokens(tokens, i, "(", ")");
                Valueable valueable = evalExpression(subTokens)[0];
                values.add(valueable);
                i += subTokens.length + 1;
                i++;
            } else if (isMatch(P_FLOAT_REGEX, tokens[i])) {
                values.add(new FloatValueable(Float.parseFloat(tokens[i])));
                i++;
            } else if (isMatch(P_SPECIAL_OPERATOR_REGEX, tokens[i])) {
                final String[] subTokens = expressionSpecialOp2Tokens(tokens[i], P_SPECIAL_OPERATOR_REGEX);
                String equalityOp = subTokens[0];
                String openBracet = subTokens[1];
                final float first = Float.parseFloat(subTokens[2]);
                final float last = Float.parseFloat(subTokens[3]);
                String closeBracet = subTokens[4];
                Valueable[] valueables = new Valueable[3];
                valueables[0] = values.getLast();
                valueables[1] = new FloatValueable(first);
                valueables[2] = new FloatValueable(last);
                MathFunction newOp = CnsOperatorsDef.getSpecialOp(equalityOp + openBracet + closeBracet);
                values.add(newOp);
                values.add(valueables[1]);
                values.add(valueables[2]);
                ops.add(newOp);
                i++;
            } else if (isMatch(P_OPERATOR_REGEX, tokens[i])) {
                if (tokens[i].equals("-") || tokens[i].equals("+")) {
                    final int mul = tokens[i].equals("-") ? -1 : 1;
                    if (!isThisTokenIsOp(tokens, i)) {
                        values.add(new IntValueable(mul));
                        MathFunction mulOp = CnsOperatorsDef.getOperator("*");
                        ops.add(mulOp);
                        values.add(mulOp);
                        i++;
                        continue;
                    }
                }
                MathFunction newOp = CnsOperatorsDef.getOperator(tokens[i]);
                if (",".equals(tokens[i])) {
                    giveValue(values, ops, valuableList);
                    i++;
                    if (i > tokens.length - 1) {
                        values.add(IntValueable.Zero);
                    }
                    continue;
                }
                values.add(newOp);
                ops.add(newOp);
                i++;
            } else if (isMatchInsensitive(_TRIGGER_MATHS_FUNCTION_REGEX, tokens[i])) {
                final Functionable f = MathsFunctionDef.getFunction(tokens[i]);
                i++;
                String[] subTokens = {};
                if (i < tokens.length && tokens[i].equals("(")) {
                    subTokens = getCloseInTokens(tokens, i, "(", ")");
                }
                final Valueable[] valueables;
                if (subTokens.length != 0) {
                    i += subTokens.length + 1;
                    valueables = evalExpression(subTokens);
                } else {
                    valueables = new Valueable[0];
                    i--;
                }
                values.add(new Valueable() {
                    public Object getValue(String spriteId, Valueable... params) {
                        return f.getValue(spriteId, valueables);
                    }
                });
                i++;
            } else if (isProcessBg && isMatchInsensitive(_TRIGGER_FUNCTION_BG_REGEX, tokens[i])) {
                final SpriteCnsTriggerFunction f = BgFunctionDef.getSpriteCnsFunc(tokens[i]);
                List<Valueable> result = new ArrayList<Valueable>();
                int newPos = f.parseValue(tokens, i, result);
                i = newPos;
                final Valueable[] valueables = result.toArray(new Valueable[result.size()]);
                values.add(new Valueable() {
                    public Object getValue(String bgId, Valueable... params) {
                        return f.getValue(bgId, valueables);
                    }
                });
                i++;
            } else if (isMatchInsensitive(_STRING_REX_EXP, tokens[i])) {
                String goodString = tokens[i];
                if (goodString.startsWith("\"") && goodString.endsWith("\"")) {
                    goodString = goodString.substring(1, goodString.length() - 1).replaceAll("\\\\\"", "\"");
                }
                values.add(new StringValueable(goodString));
                i++;
            } else {
                throw new IllegalStateException("unrecognized token: " + tokens[i]);
            }
        }
        giveValue(values, ops, valuableList);
        return valuableList.toArray(new Valueable[0]);
    }

    private static final Comparator<MathFunction> _MATHFUNCTION_SORTER = new Comparator<MathFunction>() {
        public int compare(MathFunction o1, MathFunction o2) {
            return o2.getPriority() - o1.getPriority();
        }
    };

    private static void giveValue(final LinkedList<Valueable> values,
            LinkedList<MathFunction> ops,
            ArrayList<Valueable> valuableList) {
        Collections.sort(ops, _MATHFUNCTION_SORTER);
        for (final MathFunction op : ops) {
            int paramCount = op.getParamCount();
            int index = values.indexOf(op);
            if (index == -1) {
                continue;
            }
            if (paramCount == 3) {
                final Valueable one = values.get(index - 1);
                final Valueable two = values.get(index + 1);
                final Valueable tree = values.get(index + 2);
                values.remove(op);
                values.remove(one);
                values.remove(two);
                values.remove(tree);
                values.add(index - 1, new Valueable() {
                    public Object getValue(String spriteId, Valueable... params) {
                        return op.getValue(spriteId, one, two, tree);
                    }
                });
            } else if (paramCount == 2) {
                final Valueable one = values.get(index - 1);
                final Valueable two = values.get(index + 1);
                values.remove(op);
                values.remove(one);
                values.remove(two);
                values.add(index - 1, new Valueable() {
                    public Object getValue(String spriteId, Valueable... params) {
                        return op.getValue(spriteId, one, two);
                    }
                });
            } else if (paramCount == 1 && index + 1 < values.size()) {
                final Valueable one = values.get(index + 1);
                values.remove(op);
                values.remove(one);
                values.add(index, new Valueable() {
                    public Object getValue(String spriteId, Valueable... params) {
                        return op.getValue(spriteId, one);
                    }
                });
            } else {
                values.remove(op);
            }
        }
        if (values.size() > 1) {
            final List<Valueable> result = new ArrayList<Valueable>(values);
            values.clear();
            values.add(new Valueable() {
                public Object getValue(String spriteId, Valueable... params) {
                    StringBuilder buffer = new StringBuilder();
                    for (Valueable val : result) {
                        buffer.append(val.getValue(spriteId, params));
                    }
                    return buffer.toString();
                }
            });
        }
        valuableList.addAll(values);
        values.clear();
        ops.clear();
    }
}
