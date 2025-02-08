package org.lee.mugen.sprite.cns.type.function;

import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.cns.eval.function.StateCtrlFunction;

public class Displaytoclipboard extends StateCtrlFunction {

    // TODO : displaytoclipboard
    public Displaytoclipboard() {
        super("displaytoclipboard", new String[] {"text", "params"});
    }
    
    @Override
    public Valueable[] parse(String name, String value) {
        return null;
    }
    public void addParam(String name, Valueable[] param) {

    }
}
