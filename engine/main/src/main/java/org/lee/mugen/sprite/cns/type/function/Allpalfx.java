package org.lee.mugen.sprite.cns.type.function;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.renderer.PalFxSub;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.cns.eval.function.StateCtrlFunction;

/**
 * Render Problem
 * @author Dr Wong
 *
 */
public class Allpalfx extends StateCtrlFunction {
	
    public Allpalfx() {
        super("allpalfx", new String[] {"time", "add", "mul", "sinadd",
				"invertall", "color" });
	}

	@Override
	public Object getValue(String spriteId, Valueable... params) {
		PalFxSub fx = GameFight.getInstance().getGlobalEvents().getBgpalfx();
		fx.init();
		getValue(spriteId, GameFight.getInstance().getGlobalEvents(), "bgpalfx", params);
		
		for (Sprite sprite: GameFight.getInstance().getSprites()) {
			fx = sprite.getPalfx();
			fx.init();
			getValue(spriteId, sprite, "palfx", params);
		}
		
		return null;
	}
}
