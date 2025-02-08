package org.lee.mugen.sprite.cns.type.function;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.cns.eval.function.StateCtrlFunction;
import org.lee.mugen.sprite.entity.PauseSub;
import org.lee.mugen.sprite.parser.ExpressionFactory;

public class Pause extends StateCtrlFunction {

    public Pause() {
        super("pause", new String[] {"time", "movetime"});
    }

    @Override
    public Object getValue(String spriteId, Valueable... params) {
    	PauseSub pause = new PauseSub();
    	fillBean(spriteId, pause);
    	pause.setSpriteFrom(GameFight.getInstance().getSpriteInstance(spriteId));
    	GameFight.getInstance().getGlobalEvents().setPause(pause);
    	return null;
    }
    
    @Override
    public Valueable[] parseValue(String name, String value) {
		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}
}
