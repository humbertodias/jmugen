package org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.character.SpriteHelper;
import org.lee.mugen.sprite.cns.eval.function.SpriteCnsTriggerFunction;

public class Win extends SpriteCnsTriggerFunction {
	public Win() {
		super("win", new String[] {});
	}

	@Override
	public Object getValue(String spriteId, Valueable... params) {
		return isWin(spriteId)? 1: 0;
	}
	
	public static boolean isWin(String spriteId) {
		boolean allLoose = true;
		Sprite sprite = GameFight.getInstance().getSpriteInstance(spriteId);
		for (Sprite s: GameFight.getInstance().getEnnmies(sprite)) {
			if (s instanceof SpriteHelper)
				continue;
			if (s.getSpriteId().equals(spriteId))
				continue;
			allLoose = allLoose && Lose.isLose(s.getSpriteId());
		}
		
		
		return allLoose;
	}

}
