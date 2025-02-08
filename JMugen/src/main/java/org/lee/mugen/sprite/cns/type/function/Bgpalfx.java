package org.lee.mugen.sprite.cns.type.function;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.renderer.PalFxSub;
import org.lee.mugen.sprite.cns.eval.function.StateCtrlFunction;

public class Bgpalfx extends StateCtrlFunction {

	public Bgpalfx() {
		super("bgpalfx", new String[] { "time", "add", "mul", "sinadd",
				"invertall", "color" });
	}

	@Override
	public Object getValue(String spriteId, Valueable... params) {
		PalFxSub fx = GameFight.getInstance().getGlobalEvents().getBgpalfx();
		fx.init();
		return getValue(spriteId, GameFight.getInstance().getGlobalEvents(), getFunctionName(), params);
	}
}
