package org.lee.mugen.sprite.cns.type.function;

import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.cns.eval.function.StateCtrlFunction;

public class Clearclipboard extends StateCtrlFunction {

    // TODO : clearclipboard
    public Clearclipboard() {
        super("clearclipboard", new String[] {});
    }
    @Override
    public Valueable[] parse(String name, String value) {
        return null;
    }
    public void addParam(String name, Valueable[] param) {

    }
}
