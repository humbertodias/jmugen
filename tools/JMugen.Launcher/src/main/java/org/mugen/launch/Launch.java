package org.mugen.launch;

import org.lee.mugen.test.ExpressionTester;
import org.lee.mugen.test.TestGameFight;
import org.lee.mugen.test.TestMenu;
import org.lee.mugen.util.debugger.SpriteDebugerUI;
import org.lee.mugen.util.debugger.component.cnsFile.CnsPanel;


public class Launch {
	public static void main(String[] args) throws Exception {

		CnsPanel.main(args);
//		TestGameFight.main(args);
		SpriteDebugerUI debugerUI = new SpriteDebugerUI();
		debugerUI.setVisible(true);
		ExpressionTester.lanch();
		TestMenu.main(args);

	}
}
