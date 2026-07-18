package org.lee.mugen.background.bgCtrlFunction;

import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.cns.eval.function.SpriteCnsTriggerFunction;

/** GWT: background trigger names for expression parsing (implementations are no-ops). */
public class BgFunctionDef {

    public static SpriteCnsTriggerFunction getSpriteCnsFunc(String name) {
        return new NoOpTrigger(name);
    }

    public static String getCnsTriggerFunctionRegex() {
        return "\\b(?:drawgame|roundno|roundsexisted|roundstate|screenpos|teammode|tickspersecond|winko|time|beaninfo|commandcount|format|isassertspecial)\\b";
    }

    private static final class NoOpTrigger extends SpriteCnsTriggerFunction {
        NoOpTrigger(String functionName) {
            super(functionName, new String[0]);
        }

        @Override
        public Object getValue(String spriteId, Valueable... params) {
            return 0;
        }
    }
}
