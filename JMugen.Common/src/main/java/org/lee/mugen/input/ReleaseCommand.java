package org.lee.mugen.input;


/**
 * ReleaseCommand
 * INFO : May be here there are some replication in conception
 * because this class is a PressCommand and it's kind is RELEASE
 * We have the information twice :/
 * 
 * Never mind it's work :p
 * @author Dr Wong
 *
 */
public class ReleaseCommand extends AbstractCommand {

	private long holdTick;
	
	public ReleaseCommand(int key, long tick, long holdTick) {
		super(key, tick, Kind.RELEASE);
		this.holdTick = holdTick;
	}

	/**
	 * get the number of tick that say how long this key is hold before it release
	 * @return
	 */
	public long getHoldTick() {
		return holdTick;
	}
	@Override
	public String toString() {
		return super.toString() + " hold : " + holdTick;
	}

}
