package org.lee.mugen.util.debugger.component.cns;

import javax.swing.JMenu;
import javax.swing.JSeparator;

public class CnsMenu extends JMenu {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public CnsMenu() {
		super("cns");
		
		add(new SaveAction());
		add(new SaveAsAction());
		add(new JSeparator());
		
		add(new CnsAlphaOrderAction());
		add(new CnsFileOrderAction());
		
	}
}
