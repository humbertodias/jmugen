package org.lee.mugen.renderer;

import java.util.ResourceBundle;


public class GraphicsWrapper {
	
	private static MugenDrawer instanceOfMugenDrawer;
	public static void init() throws Exception {
		// TODO: Review
//		String className = ResourceBundle.getBundle("render").getString("renderClass");
		String className = System.getProperty("renderClass", "org.lee.mugen.renderer.lwjgl.LMugenDrawer");
		instanceOfMugenDrawer = (MugenDrawer) Class.forName(className).newInstance();
	}
	
	public static MugenDrawer getInstance() {
		if (instanceOfMugenDrawer == null)
			throw new RuntimeException("Not initialize");
		return instanceOfMugenDrawer;
	}
	
	

}
