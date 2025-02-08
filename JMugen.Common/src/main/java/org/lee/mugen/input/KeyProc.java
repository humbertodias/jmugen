package org.lee.mugen.input;

import org.lee.mugen.input.KeyLockCommand.State;

/**
 * Key proc descripte a command key and his state NONE, LOCK, HIDDEN
 * @author Dr Wong
 *
 */
public class KeyProc {
	private AbstractCommand key;
	private State state;

	public KeyProc(AbstractCommand key) {
		this(key, State.NONE);
	}

	
	public KeyProc(AbstractCommand key, State state) {
		this.key = key;
		this.state = state;
	}
	
	public AbstractCommand getKey() {
		return key;
	}
	public void setKey(AbstractCommand key) {
		this.key = key;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}

	
	@Override
	public String toString() {
		return key + " " + state;
	}
	
}
