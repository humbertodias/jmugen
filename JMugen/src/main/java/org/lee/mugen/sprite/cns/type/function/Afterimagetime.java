package org.lee.mugen.sprite.cns.type.function;

import java.util.List;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.renderer.Renderable;
import org.lee.mugen.sprite.cns.eval.function.StateCtrlFunction;
import org.lee.mugen.sprite.parser.Parser;

/**
 * Seems ok
 * @author Dr Wong
 *
 */
public class Afterimagetime extends StateCtrlFunction {

	public Afterimagetime() {
		super("afterimagetime", new String[] {"time"});
	}
	@Override
	public Object getValue(String spriteId, Valueable... params) {
		List<Renderable> list = GameFight.getInstance().getRenderables();
		int timeIndex = getParamIndex("time");
		Valueable time = valueableParams[timeIndex][0];
		int itime = Parser.getIntValue(time.getValue(spriteId, params));
		
		GameFight.getInstance().setAfterImageTime(spriteId, itime);
		return null;
	}
}
