package org.lee.mugen.sprite.cns.type.function;

import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.cns.eval.function.StateCtrlFunction;
/**
 * 
 * @author Dr Wong
 * @category Cns Function : Complete
 */
public class Null extends StateCtrlFunction {

	public Null() {
		super("null", new String[0]);
	}
	@Override
	public Valueable[] parse(String name, String value) {
		return null;
	}
	public void addParam(String name, Valueable[] param) {

	}
	@Override
	public boolean containsParam(String param) {
		return true;
	}
}
