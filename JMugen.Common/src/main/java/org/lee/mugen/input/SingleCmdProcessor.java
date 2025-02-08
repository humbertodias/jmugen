package org.lee.mugen.input;



/**
 * SingleCmdProcessor is request when Mugen core want to know what are the command in buffer
 * it describe a group of keys that is press, release, hidden, ... at a time
 * @author Dr Wong
 *
 */
public class SingleCmdProcessor {
	private KeyProc[] keys;
	private long tick;


	/* (non-Javadoc)
	 * @see org.lee.mugen.core.command.ISingleCmdProcessor#getTick()
	 */
	public long getTick() {
		return tick;
	}

	public SingleCmdProcessor(long tick, KeyProc...keys) {
		this.keys = keys;
		this.tick = tick;
	}

	/* (non-Javadoc)
	 * @see org.lee.mugen.core.command.ISingleCmdProcessor#getKeys()
	 */
	public KeyProc[] getKeys() {
		return keys;
	}

}
