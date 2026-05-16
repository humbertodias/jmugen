package org.lee.mugen.renderer;

import org.lee.mugen.ResourceBundleHelper;


public class GraphicsWrapper {
	
	private static MugenDrawer instanceOfMugenDrawer;

	/**
	 * Hosts that construct the drawer explicitly (LibGDX Web/GWT cannot use {@link Class#forName} the same way).
	 */
	public static void installDrawer(MugenDrawer drawer) {
		if (drawer == null) {
			throw new IllegalArgumentException("drawer");
		}
		instanceOfMugenDrawer = drawer;
	}

	public static void init() throws Exception {
		if (instanceOfMugenDrawer != null) {
			return;
		}
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
