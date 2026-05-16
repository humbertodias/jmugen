package org.lee.mugen.sprite.cns.eval.function;

import org.lee.mugen.parser.type.Functionable;
import org.lee.mugen.parser.type.Valueable;

/** GWT copy of {@link org.lee.mugen.sprite.cns.eval.function.MathFunction} without {@code Object#clone()}. */
public class MathFunction implements Functionable {

    protected String op;
    protected int priority;
    protected int paramCount;
    protected Functionable function;

    public MathFunction(String op, int priority, int paramCount, Functionable function) {
        this.op = op;
        this.priority = priority;
        this.paramCount = paramCount;
        this.function = function;
    }

    public Functionable getFunction() {
        return function;
    }

    public void setFunction(Functionable function) {
        this.function = function;
    }

    public String getOp() {
        return op;
    }

    public int getParamCount() {
        return paramCount;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public Object getValue(String spriteId, Valueable... params) {
        return function.getValue(spriteId, params);
    }

    public MathFunction copy() {
        return new MathFunction(op, priority, paramCount, function);
    }

    public void reset() {
    }
}
