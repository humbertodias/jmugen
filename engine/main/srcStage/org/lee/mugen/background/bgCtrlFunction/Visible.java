package org.lee.mugen.background.bgCtrlFunction;

import java.util.ArrayList;

import org.lee.mugen.background.BG;
import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.cns.eval.function.StateCtrlFunction;
import org.lee.mugen.sprite.parser.Parser;
import org.lee.mugen.stage.Stage;

public class Visible extends StateCtrlFunction {

	public Visible() {
		super("visible", new String[] { "value" });
	}

	@Override
	public Object getValue(String bgId, Valueable... params) {
		Stage stage = GameFight.getInstance().getStage();
		ArrayList<BG> bgs = stage.getBackground().getBgCtrlDefMap().get(new Integer(bgId))
				.getBgCopys();

		for (BG bg : bgs) {
			int xValue = getParamIndex("value");
			if (valueableParams[xValue] != null) {
				Valueable value = valueableParams[xValue][0];
				if (value != null) {
					int ix = Parser.getIntValue(value.getValue(bgId));
					bg.setVisible(ix != 0);
				}
			}
		}
		return null;
	}
	
}