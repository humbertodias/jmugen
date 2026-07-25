package org.lee.mugen.input;

import java.io.Serializable;


/**
 * MugenSingleCmd describes a single command of the sequence in *.cmd
 * @author Dr Wong
 *
 */
public class MugenSingleCmd implements Serializable {
	public static enum CommandType {
		PRESS(1, ""), HOLD(2, "/"), RELEASED(4, "~"), DIRECTION(8, "$"), SIMULTANEOUS(16, "+"), 
		NO_OTHER_KEY_BEFORE(32, ">");

		public final String desc;
		CommandType(int bit, String desc) {
			this.bit = bit;
			this.desc = desc;
		}
		public final int bit;
	}
	private int  types;
	private int keys;
	// time is only for release type
	private int time;
	
	
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}

	private int computeTypes(CommandType...types) {
		int res = 0;
		for (CommandType t: types)
			res |= t.bit;
		return res;
	}
	private int computeKeys(Key...keys) {
		int res = 0;
		for (Key t: keys)
			res |= t.bit;
		return res;
	}
	
	public MugenSingleCmd(Key[] keys, CommandType...types) {
		this(0, keys, types);
	}
	public MugenSingleCmd(int time, Key[] keys, CommandType...types) {
		this.time = time;
		this.keys = computeKeys(keys);
		this.types = computeTypes(types);
	}
	public int getTypes() {
		return types;
	}
	public int getKeys() {
		return keys;
	}
}
