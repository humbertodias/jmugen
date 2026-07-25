package org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.cns.eval.function.SpriteCnsTriggerFunction;

public class Roundstate extends SpriteCnsTriggerFunction {

	public static final int PRE_INTRO = 0; // : Pré-intro - l'écran apparaît en fade-in (fondu)
	public static final int INTRO = 1; // : Intro
	public static final int COMBAT = 2; // : Combat - Les joueurs s'affrontent
	public static final int PRE_END = 3; // : Pré-fin - Un round vient juste d'être gagné ou perdu.
	public static final int VICTORY = 4; // : Fini - Poses de victoire
	
	public Roundstate() {
		super("roundstate", new String[] {});
	}
	
	@Override
	public Object getValue(String spriteId, Valueable... p) {
		
		return GameFight.getInstance().getGameState().getRoundState();
	}
}
