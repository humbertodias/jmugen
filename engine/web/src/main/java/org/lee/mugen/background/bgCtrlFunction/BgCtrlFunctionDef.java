package org.lee.mugen.background.bgCtrlFunction;

import java.util.HashMap;
import java.util.Map;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.cns.eval.function.StateCtrlFunction;

/** GWT: bg ctrl executors are no-ops; title backgrounds still parse triggers. */
public class BgCtrlFunctionDef {

    public static StateCtrlFunction getStateCtrlFunc(String name) {
        StateCtrlFunction scf = stateCtrlFunctionMap.get(name);
        return scf;
    }

    public static final Map<String, StateCtrlFunction> stateCtrlFunctionMap = buildStateCtrlFunction();

    private static Map<String, StateCtrlFunction> buildStateCtrlFunction() {
        Map<String, StateCtrlFunction> map = new HashMap<String, StateCtrlFunction>();
        map.put("posset", new NoOpBgCtrl("posset", new String[] {"x", "y"}));
        map.put("velset", new NoOpBgCtrl("velset", new String[] {"x", "y"}));
        map.put("enable", new NoOpBgCtrl("enable", new String[] {"value"}));
        map.put("visible", new NoOpBgCtrl("visible", new String[] {"value"}));
        map.put("siny", new NoOpBgCtrl("siny", new String[] {"value"}));
        map.put("sin.y", new NoOpBgCtrl("sin.y", new String[] {"value"}));
        return map;
    }

    private static final class NoOpBgCtrl extends StateCtrlFunction {
        NoOpBgCtrl(String functionName, String[] paramNames) {
            super(functionName, paramNames);
        }

        @Override
        public Object getValue(String spriteId, Valueable... params) {
            return null;
        }
    }
}
