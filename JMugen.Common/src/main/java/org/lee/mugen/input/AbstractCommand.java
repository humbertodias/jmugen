package org.lee.mugen.input;

/**
 * Base of a single command events
 * 
 * @author Dr Wong
 *
 */
public abstract class AbstractCommand {
	/**
	 * Basic Key can only be PRESS or RELEASE
	 *
	 */
	public static enum Kind {
		PRESS, RELEASE;
	}
	
	protected Kind kind;
	private int key;
	private long tick;
	
	
	public AbstractCommand(int key, long tick, Kind kind) {
		this.key = key;
		this.tick = tick;
		this.kind = kind;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getKey() {
		return key;
	}
	/**
	 * If Kind = Press => Time when button is pressed then if Time press == System Time then key is just press
	 * If Kind = Release => Time when button is released
	 * @return
	 */
	public long getTick() {
		return tick;
	}

	public Kind getKind() {
		return kind;
	}
	@Override
	public String toString() {
		return kind + " " + key + " @t " + tick;
	}

}
