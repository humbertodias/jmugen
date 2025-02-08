package org.lee.mugen.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.character.SpriteHelper;
import org.lee.mugen.sprite.character.SpriteCns.Type;
import org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns.Roundstate;
import org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns.Win;
import org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns.Winko;
import org.lee.mugen.sprite.cns.type.function.Assertspecial.Flag;
import org.lee.mugen.util.MugenRandom;


/**
 * Game state holds game time, round number, number of round, ...
 * Everything about game state but in fight not transition like menu, ...
 * @author Dr Wong
 *
 */
public class GameState {
	GameState() {
	}
	
	public static int DEFAULT_TIME = 99 * 60;
	
	
	public static int getDefaultTime() {
		return DEFAULT_TIME;
	}
	public static void setDefaultTime(int defaultTime) {
		DEFAULT_TIME = defaultTime;
	}

	private int gameTime;
	
	private int roundTime = DEFAULT_TIME;
	private int roundno;
	private int roundState = 0; // PRE_INTRO
	private int roundsExisted = 0; 
	
	
	
	private Map<String, Integer> spriteIdStateMap = new HashMap<String, Integer>();
	private int drawCountMap = 0;

	
	public int getRoundsExisted() {
		return roundsExisted;
	}
	public void setRoundsExisted(int roundsExisted) {
		this.roundsExisted = roundsExisted;
	}
	public int getRoundState() {
		return roundState;
	}
	public void setRoundState(int roundState) {
		this.roundState = roundState;
	}
	private int teamOneWinRound;
	private int teamTwoWinRound;
	public enum WinType {
		KO, DKO, TO
	}
	private WinType lastWin;

	private int drawRound;

	public int getDrawRound() {
		return drawRound;
	}

	public void setDrawRound(int drawRound) {
		this.drawRound = drawRound;
	}

	public int getTeamOneWinRound() {
		return teamOneWinRound;
	}

	public void setTeamOneWinRound(int teamOneWinRound) {
		this.teamOneWinRound = teamOneWinRound;
	}

	public int getTeamTwoWinRound() {
		return teamTwoWinRound;
	}

	public void setTeamTwoWinRound(int teamTwoWinRound) {
		this.teamTwoWinRound = teamTwoWinRound;
	}

	public int getRoundno() {
		return roundno;
	}

	public void setRoundno(int roundno) {
		this.roundno = roundno;
	}

	public int getGameTime() {
		return gameTime;
	}

	public void setGameTime(int gameTime) {
		this.gameTime = gameTime;
	}
	
	public WinType getLastWin() {
		return lastWin;
	}
	public void leave(GameFight sm) {
		gameTime++;
		if (getRoundTime() > 0 && getRoundState() != Roundstate.PRE_END)
			roundTime--;
		
		if (roundState == Roundstate.COMBAT) {
			boolean isOneWin = false;
			Sprite winner = null;
			for (String spriteId: spriteIdStateMap.keySet()) {
				if (Win.isWin(spriteId)) {
					isOneWin = true;
					winner = GameFight.getInstance().getSpriteInstance(spriteId);

				}
			}
			if (isOneWin) {
				roundState = Roundstate.PRE_END;
				for (String spriteId: spriteIdStateMap.keySet()) {
					spriteIdStateMap.put(spriteId, Roundstate.PRE_END);
					GameFight.getInstance().getSpriteInstance(spriteId).getInfo().setCtrl(0);
				}
			}
			if (getRoundTime() == 0) {
				roundState = Roundstate.PRE_END;
				for (String spriteId: spriteIdStateMap.keySet()) {
					spriteIdStateMap.put(spriteId, Roundstate.PRE_END);
					GameFight.getInstance().getSpriteInstance(spriteId).getInfo().setCtrl(0);
				}
			}
			
		} else if (roundState == Roundstate.PRE_END) {
			boolean isAllSpriteInactive = true;
			for (String spriteId: spriteIdStateMap.keySet()) {
				Sprite s = GameFight.getInstance().getSpriteInstance(spriteId);
				if (s.getInfo().getType() == Type.L && s.getInfo().getLife() <= 0) {
					continue;
				}
				isAllSpriteInactive = isAllSpriteInactive && s.getSpriteState().getCurrentState().getIntId() == 0 && s.getInfo().getType() == Type.S;
			}
			if (isAllSpriteInactive) {
				roundState = Roundstate.VICTORY;
			}
		} else if (roundState == Roundstate.VICTORY) {
			
			Sprite winner = null;
			for (String spriteId: spriteIdStateMap.keySet()) {
				if (Win.isWin(spriteId)) {
					winner = GameFight.getInstance().getSpriteInstance(spriteId);
				}
			}
			if (lastWin == null) {
				if (winner != null) {
					Collection<Sprite> winners = new LinkedList<Sprite>();
					Collection<Sprite> loser = new LinkedList<Sprite>();
					if (Winko.isWinKo(winner.getSpriteId()))
						lastWin = WinType.KO;
					if (Win.isWin(winner.getSpriteId()) && !Winko.isWinKo(winner.getSpriteId()))
						lastWin = WinType.TO;
					
					if (GameFight.getInstance().getTeamOne().containsKey(winner.getSpriteId())) {
						winners.addAll(GameFight.getInstance().getTeamOne().values());
						loser.addAll(GameFight.getInstance().getTeamTwo().values());
						teamOneWinRound++;

					} else {
						winners.addAll(GameFight.getInstance().getTeamTwo().values());
						loser.addAll(GameFight.getInstance().getTeamOne().values());
						teamTwoWinRound++;
					}
					for (Sprite s: winners) {
						if (s instanceof SpriteHelper)
							continue;
						if (s.getInfo().getType() == Type.L)
							continue;
						if (spriteIdStateMap.get(s.getSpriteId()) != Roundstate.VICTORY) {
							List<Integer> validNumber = new ArrayList<Integer>();
							
							for (int i = 180; i < 190; i++) {
								if (s.getSpriteState().isStateExist(i)) {
									validNumber.add(i);
								}
							}
							int size = validNumber.size();
							if (size > 0) {
								int state = MugenRandom.getRandomNumber(0, size - 1);
								s.getSpriteState().changeStateDef(validNumber.get(state));
								spriteIdStateMap.put(s.getSpriteId(), Roundstate.VICTORY);
							}
						}
					}
					for (Sprite s: loser) {
						if (s instanceof SpriteHelper)
							continue;
						if (s.getInfo().getType() == Type.L)
							continue;
						if (spriteIdStateMap.get(s.getSpriteId()) != Roundstate.VICTORY) {
							List<Integer> validNumber = new ArrayList<Integer>();
							
							if (s.getSpriteState().isStateExist(170)) {
								validNumber.add(170);
							}
							int size = validNumber.size();
							if (size > 0) {
								int state = MugenRandom.getRandomNumber(0, size - 1);
								s.getSpriteState().changeStateDef(validNumber.get(state));
								spriteIdStateMap.put(s.getSpriteId(), Roundstate.VICTORY);
							}
						}
					}
					
				} else {
					int lifeTeamOne = 0;
					int lifeTeamTwo = 0;
					for (Sprite s: GameFight.getInstance().getTeamOne().values()) {
						if (s.getClass() == Sprite.class) {
							lifeTeamOne += s.getInfo().getLife();
						}
					}
					for (Sprite s: GameFight.getInstance().getTeamTwo().values()) {
						if (s.getClass() == Sprite.class) {
							lifeTeamTwo += s.getInfo().getLife();
						}
					}
					if (lifeTeamOne == 0)
						lastWin = WinType.DKO;
					else
						lastWin = WinType.TO;
						
					drawCountMap++;
					for (Sprite s: GameFight.getInstance().getSprites()) {
						if (s instanceof SpriteHelper)
							continue;
						if (s.getInfo().getType() == Type.L)
							continue;
						if (spriteIdStateMap.get(s.getSpriteId()) != Roundstate.VICTORY) {
							s.getSpriteState().changeStateDef(175);
							spriteIdStateMap.put(s.getSpriteId(), Roundstate.VICTORY);
						}
					}
				}
			} else {
				timeBeforeStartNewRound--;
				if (timeBeforeStartNewRound <= 0) {
					sm.initRound(roundno + 1);
				}
			}

			
		}
		
	}
	int timeBeforeStartNewRound = DEFAULT_TIME_BEFORE_START_NEWROUND;
	static final int DEFAULT_TIME_BEFORE_START_NEWROUND = 60 * 5;
	
	public void enter(GameFight sm) {
		for (String spriteId: spriteIdStateMap.keySet()) {
			if (sm.getGlobalEvents().isAssertSpecial(spriteId, Flag.intro)) {
				spriteIdStateMap.put(spriteId, Roundstate.INTRO);
			}
			int stateSpr = spriteIdStateMap.get(spriteId);
			if (stateSpr == Roundstate.INTRO && !sm.getGlobalEvents().isAssertSpecial(spriteId, Flag.intro)) {
				spriteIdStateMap.put(spriteId, Roundstate.COMBAT);
			}
		}

		boolean isAllSameState = true;
		String lastId = null;
		int state = 0;
		for (String spriteId: spriteIdStateMap.keySet()) {
			if (lastId == null)
				lastId = spriteId;
			state = spriteIdStateMap.get(spriteId);
			isAllSameState = isAllSameState && (state == spriteIdStateMap.get(lastId));
			lastId = spriteId;
		}
		
		if (isAllSameState && getRoundState() <= Roundstate.INTRO) {
			setRoundState(state);
			if (getRoundState() == Roundstate.COMBAT 
					&& getRoundsExisted() == 0) {
				
				for (String spriteId: spriteIdStateMap.keySet()) {
					sm.getSpriteInstance(spriteId).getInfo().setCtrl(1);
				}
				setRoundsExisted(1);
				roundTime = DEFAULT_TIME;
			}
		}
	}

	public int getRoundTime() {
		return roundTime;
	}

	public void setRoundTime(int roundTime) {
		this.roundTime = roundTime;
	}
	public void init(GameFight sm, int round) {
		setRoundState(0);
		setRoundsExisted(0);
		roundno = round;
		roundTime = DEFAULT_TIME;
		spriteIdStateMap.clear();
		lastWin = null;
		timeBeforeStartNewRound = DEFAULT_TIME_BEFORE_START_NEWROUND;
		for (Sprite s: sm.getSprites()) {
			if (s instanceof SpriteHelper)
				continue;
			spriteIdStateMap.put(s.getSpriteId(), Roundstate.PRE_INTRO);
		}
	}
}
