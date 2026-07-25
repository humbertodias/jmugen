package org.lee.mugen.sprite.cns.type.function;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.cns.eval.function.StateCtrlFunction;
import org.lee.mugen.sprite.entity.ScreenboundSub;

public class Screenbound extends StateCtrlFunction {

    public Screenbound() {
        super("screenbound", new String[] {"value", "movecamera", "time"});
    }

    
    @Override
    public Object getValue(String spriteId, Valueable... params) {
    	ScreenboundSub screenboundSub = new ScreenboundSub();
    	fillBean(spriteId, screenboundSub);
    	if (!screenboundSub.isActivate())
    		screenboundSub.activate();
    	GameFight.getInstance().getSpriteInstance(spriteId).getInfo().setScreenbound(screenboundSub);
    	return null;
    }
}
