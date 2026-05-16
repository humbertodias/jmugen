package org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns;

/** GWT: const names for {@link org.lee.mugen.sprite.parser.ExpressionFactory} tokenization only. */
public class Gethitvar {

    private static final String[] CONST = {
        "xveladd", "yveladd", "type", "animtype", "airtype", "groundtype", "damage", "hitcount"
    };

    public static String getConstRegex() {
        StringBuilder builder = new StringBuilder();
        for (int index = 0; index < CONST.length; index++) {
            builder.append("(\\b").append(CONST[index].replace(".", "\\.")).append("\\b)");
            if (index < CONST.length - 1) {
                builder.append("|");
            }
        }
        return builder.toString();
    }
}
