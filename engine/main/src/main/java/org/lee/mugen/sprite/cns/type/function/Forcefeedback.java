package org.lee.mugen.sprite.cns.type.function;

import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.cns.eval.function.StateCtrlFunction;

public class Forcefeedback extends StateCtrlFunction {

	// TODO : Forcefeedback
	public Forcefeedback() {
		super("forcefeedback", new String[] {"waveform", "time", "freq", "ampl", "self"});
	}
	
	@Override
	public Valueable[] parse(String name, String value) {
		return null;
	}
	public void addParam(String name, Valueable[] param) {

	}
}
