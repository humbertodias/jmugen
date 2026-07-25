package org.lee.mugen.sprite.cns;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import org.lee.mugen.parser.type.Functionable;
import org.lee.mugen.parser.type.Valueable;

/** GWT copy of {@link org.lee.mugen.sprite.cns.AbstractCnsFunction} using {@link GWT#create(Class)}. */
public abstract class AbstractCnsFunction implements Functionable, Serializable {

    protected Valueable[][] valueableParams;
    protected String[] paramNames;
    protected String functionName;
    protected HashMap<String, Integer> paramNameIndexMap;

    public AbstractCnsFunction(String functionName, String[] paramNames) {
        valueableParams = new Valueable[paramNames.length][];
        this.functionName = functionName;
        this.paramNames = paramNames;
        paramNameIndexMap = new HashMap<String, Integer>();
        int i = 0;
        for (String s : paramNames) {
            paramNameIndexMap.put(s, i++);
        }
    }

    public AbstractCnsFunction copy() {
        return this;
    }

    public void addParam(String name, Valueable[] param) {
        int index = getParamIndex(name);
        if (index == -1) {
            return;
        }
        valueableParams[index] = param;
    }

    public Object getValue(String spriteId, Valueable... params) {
        return null;
    }

    public Collection<String> getParamNames() {
        return Collections.unmodifiableCollection(paramNameIndexMap.keySet());
    }

    public final String getFunctionName() {
        return functionName;
    }

    public final void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public final int getParamIndex(String name) {
        Integer result = paramNameIndexMap.get(name);
        return result == null ? -1 : result.intValue();
    }

    public static Valueable[] getFloatValuable(String value) {
        final Float f = Float.parseFloat(value);
        return new Valueable[] {
            new Valueable() {
                public Object getValue(String spriteId, Valueable... params) {
                    return f;
                }
            }
        };
    }

    public static Valueable[] getIntValuable(String value) {
        final Integer f = Integer.parseInt(value);
        return new Valueable[] {
            new Valueable() {
                public Object getValue(String spriteId, Valueable... params) {
                    return f;
                }
            }
        };
    }

    public static Valueable[] getStringValuable(String value) {
        final String f = value;
        return new Valueable[] {
            new Valueable() {
                public Object getValue(String spriteId, Valueable... params) {
                    return f;
                }
            }
        };
    }

    public boolean control() {
        return true;
    }

    protected boolean isSetterMandatory(String name) {
        return false;
    }

    public abstract Valueable[] parseValue(String name, String value);
}
