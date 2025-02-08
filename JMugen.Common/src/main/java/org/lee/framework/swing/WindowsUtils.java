package org.lee.framework.swing;


import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;

public class WindowsUtils {

	public static void centerScreen(Window w) {
		Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screenDim.width - w.getWidth()) / 2;
		int y = (screenDim.height - w.getHeight()) / 2;
		w.setLocation(x, y);
	}

}
