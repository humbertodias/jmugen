package org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.cns.eval.function.SpriteCnsTriggerFunction;

/**
 * 
 * @author Dr Wong
 * Test if the player is alive
 * @category Trigger : Complete
 */
public class Losetime extends SpriteCnsTriggerFunction {

	public Losetime() {
		super("losetime", new String[] {});
	}
	@Override
	public Object getValue(String spriteId, Valueable... params) {
		return getResult(spriteId)? 1: 0;
		
	}
	public static boolean getResult(String spriteId) {
		GameFight stateMachine = GameFight.getInstance();
		Sprite sprite = stateMachine.getSpriteInstance(spriteId);
		int lifeOne = 0;
		int lifeTwo = 0;
		
		for (Sprite s: stateMachine.getPartners(sprite)) {
			if (s.getClass() == Sprite.class)
				lifeOne += s.getInfo().getLife();
		}
		for (Sprite s: stateMachine.getEnnmies(sprite)) {
			if (s.getClass() == Sprite.class)
				lifeTwo += s.getInfo().getLife();
		}
		return lifeOne < lifeTwo
				&& stateMachine.getGameState().getRoundTime() <= 0;
		
	}
}
