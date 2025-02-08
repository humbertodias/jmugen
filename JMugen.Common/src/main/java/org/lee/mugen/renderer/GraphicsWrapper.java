package org.lee.mugen.renderer;

import java.util.ResourceBundle;


public class GraphicsWrapper {
	
	private static MugenDrawer instanceOfMugenDrawer;
	public static void init() throws Exception {
		String className = ResourceBundle.getBundle("render").getString("renderClass");
		instanceOfMugenDrawer = (MugenDrawer) Class.forName(className).newInstance();
	}
	
	public static MugenDrawer getInstance() {
		if (instanceOfMugenDrawer == null)
			throw new RuntimeException("Not initialize");
		return instanceOfMugenDrawer;
	}
	
	

}
