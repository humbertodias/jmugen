package org.lee.mugen.sprite.cns.type.function;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.cns.eval.function.StateCtrlFunction;

public class Playerpush extends StateCtrlFunction {

    public Playerpush() {
        super("playerpush", new String[] {"value"});
    }
	@Override
	public Object getValue(String spriteId, Valueable... params) {
		GameFight.getInstance().getSpriteInstance(spriteId).getInfo().setPlayerpush(1);
		return null;
	}
	
	
}
