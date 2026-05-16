package org.lee.mugen.renderer;

import org.lee.mugen.ResourceBundleHelper;


public class GraphicsWrapper {
	
	private static MugenDrawer instanceOfMugenDrawer;
	public static void init() throws Exception {
		String className = System.getProperty("jmugen.renderClass");
		if (className == null || className.isEmpty()) {
			className = ResourceBundleHelper.getBundle("render").getString("renderClass");
		}
		instanceOfMugenDrawer = (MugenDrawer) Class.forName(className).newInstance();
	}
	
	public static MugenDrawer getInstance() {
		if (instanceOfMugenDrawer == null)
			throw new RuntimeException("Not initialize");
		return instanceOfMugenDrawer;
	}
	
	

}
