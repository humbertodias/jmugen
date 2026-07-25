package org.lee.mugen.sprite.cns.eval.function;

/** GWT: title/background expressions use {@code isProcessSprite=false}; sprite triggers are not compiled in. */
public class SpriteCnsFunctionDef {

    public static SpriteCnsTriggerFunction getSpriteCnsFunc(String name) {
        throw new UnsupportedOperationException("sprite triggers are not available on web: " + name);
    }

    public static String getCnsTriggerFunctionRegex() {
        return "(?!x)x";
    }
}
