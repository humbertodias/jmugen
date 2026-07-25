package org.lee.mugen.background.bgCtrlFunction;

import java.util.ArrayList;

import org.lee.mugen.background.BG;
import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.cns.eval.function.StateCtrlFunction;
import org.lee.mugen.sprite.parser.Parser;
import org.lee.mugen.stage.Stage;

/**
 * 
 * @author Dr Wong
 * Test if the player is alive
 * @category Trigger : Complete
 */
public class Velset extends StateCtrlFunction {

	public Velset() {
		super("velset", new String[] {"x", "y"});
	}
	@Override
	public Object getValue(String bgId, Valueable... params) {
		Stage stage = GameFight.getInstance().getStage();
		ArrayList<BG> bgs = stage.getBackground().getBgCtrlDefMap().get(new Integer(bgId)).getBgCopys();

		for (BG bg: bgs) {
			int xIndex = getParamIndex("x");
			if (valueableParams[xIndex] != null) {
				Valueable x = valueableParams[xIndex][0];
				if (x != null) {
					float fx = Parser.getFloatValue(x.getValue(bgId));
					bg.getVelocity().setX(fx);
				}
			}
			int yIndex = getParamIndex("y");
				if (valueableParams[yIndex] != null) {
				Valueable y = valueableParams[yIndex][0];
				if (y != null) {
					float fy = Parser.getFloatValue(y.getValue(bgId));
					bg.getVelocity().setY(fy);
				}		
			}
		}
		
		return null;
	}
}
