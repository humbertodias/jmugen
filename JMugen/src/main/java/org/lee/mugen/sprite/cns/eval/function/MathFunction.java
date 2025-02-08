package org.lee.mugen.sprite.cns.eval.function;

import org.lee.mugen.parser.type.Functionable;
import org.lee.mugen.parser.type.ObjectValueable;
import org.lee.mugen.parser.type.Valueable;

public class MathFunction implements Functionable, Cloneable {
	protected String op;

	protected int priority;

	protected int paramCount;

	protected Functionable function;

	public MathFunction(String op, int priority, int paramCount,
			Functionable function) {
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

	public void setOp(String op) {
		this.op = op;
	}

	public int getParamCount() {
		return paramCount;
	}

	public void setParamCount(int paramCount) {
		this.paramCount = paramCount;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public Object getValue(String spriteId, Valueable... params) {
		try {
			return function.getValue(spriteId, params);
			
		} catch (Exception e) {
			return 0;
		}
	}
	
	public Object getFunctionResult(String spriteId, final Object... params) {
		Valueable[] vals = new Valueable[params.length];
		for (int i = 0; i < vals.length; ++i) {
			final Object r = params[i];
			Valueable v = new ObjectValueable(r);
			vals[i] = v;
		}
		return function.getValue(spriteId, vals);
	}

	public void reset() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

}
