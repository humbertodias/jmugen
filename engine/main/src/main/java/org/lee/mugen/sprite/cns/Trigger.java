package org.lee.mugen.sprite.cns;

import java.io.Serializable;

import org.lee.mugen.parser.type.Valueable;

public class Trigger implements Valueable, Cloneable, Serializable {
	private int prio = 1;
	private Valueable value;
	
	public Valueable getValueable() {
		return value;
	}
	public void setValueable(Valueable valueable) {
		value = valueable;
	}
	public Trigger(Valueable v) {
		value = v;
	}
	public Object getValue(String spriteId, Valueable... params) {
		return value.getValue(spriteId);
	}
	public int getPrio() {
		return prio;
	}
	public void setPrio(int prio) {
		this.prio = prio;
	}

}
