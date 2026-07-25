package org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.character.SpriteHelper;
import org.lee.mugen.sprite.cns.eval.function.SpriteCnsTriggerFunction;

public class Winko extends SpriteCnsTriggerFunction {
	public Winko() {
		super("winko", new String[] {});
	}

	@Override
	public Object getValue(String spriteId, Valueable... params) {
		return isWinKo(spriteId)? 1: 0;
	}
	
	public static boolean isWinKo(String spriteId) {
		boolean allLoose = true;
		for (Sprite s: GameFight.getInstance().getSprites()) {
			if (s instanceof SpriteHelper)
				continue;
			if (s.getSpriteId().equals(spriteId))
				continue;
			allLoose = allLoose && Loseko.isLoseKo(s.getSpriteId());
		}
		
		
		return allLoose;
	}

}
