package org.lee.mugen.input;


/**
 * PressCommand
 * INFO : May be here there are some replication in conception
 * because this class is a PressCommand and it's kind is PRESS
 * We have the information twice :/
 * 
 * Never mind it's work :p
 * @author Dr Wong
 *
 */
public class PressCommand extends AbstractCommand {
	public PressCommand(int key, long tick) {
		super(key, tick, Kind.PRESS);
	}
	
}
