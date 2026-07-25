package org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns;

import org.lee.mugen.core.GameState;
import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.cns.eval.function.SpriteCnsTriggerFunction;

public class Matchover extends SpriteCnsTriggerFunction {

	public Matchover() {
		super("matchover", new String[] {});
	}

	@Override
	public Object getValue(String spriteId, Valueable... params) {
		GameState gs = GameFight.getInstance().getGameState();
		int winRoundCount = GameFight.getInstance().getFightdef().getRound().getMatch().getWins();
		boolean result = gs.getTeamOneWinRound() >= winRoundCount || gs.getTeamTwoWinRound() > winRoundCount;
		return result? 1: 0;
	}
}
