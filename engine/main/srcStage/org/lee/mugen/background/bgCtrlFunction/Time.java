package org.lee.mugen.background.bgCtrlFunction;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.cns.eval.function.SpriteCnsTriggerFunction;
import org.lee.mugen.stage.Stage;

public class Time extends SpriteCnsTriggerFunction {

	public Time() {
		super("time", new String[0]);
	}
	@Override
	public Object getValue(String bgId, Valueable... params) {
		Stage stage = GameFight.getInstance().getStage();
		int time = stage.getBackground().getBgCtrlDefMap().get(new Integer(bgId)).getCounttime();
		
		return time;
	}
}