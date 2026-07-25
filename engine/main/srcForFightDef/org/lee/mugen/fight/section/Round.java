package org.lee.mugen.fight.section;

import org.lee.mugen.geom.MugenPoint;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.core.GameState.WinType;
import org.lee.mugen.core.sound.SoundSystem;
import org.lee.mugen.core.sound.SoundSystem.SoundBackGround;
import org.lee.mugen.fight.section.elem.SimpleElement;
import org.lee.mugen.fight.section.elem.SndType;
import org.lee.mugen.fight.section.elem.Start;
import org.lee.mugen.fight.section.elem.Type;
import org.lee.mugen.fight.system.MugenSystem;
import org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns.Roundstate;
import org.lee.mugen.util.MugenRandom;

public class Round extends SimpleElement implements Section, Cloneable {
	
	public Map<Integer, Type> getRounds() {
		return rounds;
	}



	public static class Match implements Cloneable {
		int wins;
		int maxdrawgames;
		public int getWins() {
			return wins;
		}
		public void setWins(int wins) {
			this.wins = wins;
		}
		public int getMaxdrawgames() {
			return maxdrawgames;
		}
		public void setMaxdrawgames(int maxdrawgames) {
			this.maxdrawgames = maxdrawgames;
		}
		public void init() {
			// TODO Auto-generated method stub
			
		}
	}
	public static class Sub implements Section, Cloneable {
		int time;
		int originalTime;
		Type _default;
		List<Type> round = new LinkedList<Type>();
		int sndtime;
		int originalSndtime;
		
		public void init() {
			time = originalTime;
			sndtime = originalSndtime;
			if (_default != null)
				_default.init();
			for (Type t: round)
				t.init();
		}
		
		public int getOriginalSndtime() {
			return originalSndtime;
		}

		public int getOriginalTime() {
			return originalTime;
		}
		
		public int getTime() {
			return time;
		}
		public void setTime(int time) {
			this.time = time;
		}
		public Type getDefault() {
			return _default;
		}
		public void setDefault(Type default1) {
			_default = default1;
		}
		public List<Type> getRound() {
			return round;
		}
		public void setRound(List<Type> round) {
			this.round = round;
		}
		public int getSndtime() {
			return sndtime;
		}
		public void setSndtime(int sndtime) {
			this.sndtime = sndtime;
		}
		@Override
		public void parse(Object root, String name, String value) {
			if (name.startsWith("default.")) {
				if (_default == null) {
					_default = new Type();
				}
				_default.setType(Type.getNext(name), _default, value, root);
				_default.parse(Type.getNext(name), value);
			} else if (name.equals("time")) {
				time = Integer.parseInt(value);
				originalTime = time;
			} else if (name.equals("sndtime")) {
				sndtime = Integer.parseInt(value);
				originalSndtime = sndtime;
			} 
			
		}
	}
	public static class TimeTest {
		int time;
		int originalTime;

		public int getOriginalTime() {
			return originalTime;
		}
		public void init() {
			time = originalTime;
		}
		public int getTime() {
			return time;
		}

		public void setTime(int time) {
			this.time = time;
		}
	}
	
	public static class OverTime extends TimeTest {
		int waittime;
		int hittime;
		int wintime;
		
		int originalWaittime;
		int originalHittime;
		int originalWintime;
		
		public int getWaittime() {
			return waittime;
		}
		public void setWaittime(int waittime) {
			this.waittime = waittime;
		}
		public int getHittime() {
			return hittime;
		}
		public void setHittime(int hittime) {
			this.hittime = hittime;
		}
		public int getWintime() {
			return wintime;
		}
		public void setWintime(int wintime) {
			this.wintime = wintime;
		}
		@Override
		public void init() {
			super.init();
			waittime = originalWaittime;
			hittime = originalHittime;
			wintime = originalWintime;
		}
		public void parse(String name, String value) {
			if (name.equals("waittime")) {
				waittime = Integer.parseInt(value);
				originalWaittime = waittime;
			} else if (name.equals("hittime")) {
				hittime = Integer.parseInt(value);
				originalHittime = hittime;
			} else if (name.equals("wintime")) {
				wintime = Integer.parseInt(value);
				originalWintime = wintime;
			} else if (name.equals("time")) {
				time = Integer.parseInt(value);
				originalTime = time;
			}
			
		}

	}
	
	
	Match match = new Match();
	Start start = new Start();
	MugenPoint pos = new MugenPoint();
	Sub round = new Sub();
	Map<Integer, Type> rounds = new HashMap<Integer, Type>();
	Type fight;
	TimeTest ctrl = new TimeTest();
	Type KO;
	Type DKO;
	Type TO;
	TimeTest slow = new TimeTest();
	OverTime over = new OverTime();
	Type win;
	Type win2;
	Type draw;
	

	@Override
	public void init() {
		super.init();
		match.init();
		start.init();
		round.init();
		for (Type t: rounds.values())
			t.init();
		fight.init();
		ctrl.init();
		KO.init();
		DKO.init();
		TO.init();
		slow.init();
		over.init();
		win.init();
		win2.init();
		draw.init();
		
	}
	
	public void parse(Object root, String name, String value) {
		super.parse(root, name, value);
		if (name.equals("match.wins")) {
			match.setWins(Integer.parseInt(value));
		} else if (name.equals("match.maxdrawgames")) {
			match.setMaxdrawgames(Integer.parseInt(value));
		} else if (name.equals("start.waittime")) {
			start.setTime(Integer.parseInt(value));
			start.setOriginalTime(Integer.parseInt(value));
		} else if (name.startsWith("round.")) {
			round.parse(root, Type.getNext(name), value);
		} else if (Pattern.matches("round[0-9]+\\..*", name)) {
			String sNum = name.substring(5, name.indexOf("."));
			int num = 0;
			if (sNum.length() > 0) {
				num = Integer.parseInt(sNum);
			}
			Type elem = rounds.get(num);
			if (elem == null) {
				elem = new Type();
				rounds.put(num, elem);
			}
			elem.setType(Type.getNext(name), elem, value, root);
			elem.parse(Type.getNext(name), value);
			
		} else if (name.equals("ctrl.time")) {
			ctrl.setTime(Integer.parseInt(value));
		} else if (name.equals("slow.time")) {
			slow.setTime(Integer.parseInt(value));
		} else if (name.startsWith("over.")) {
			over.parse(Type.getNext(name), value);
		} else if (name.startsWith("fight.")) {
			if (fight == null) {
				fight = new Type();
			}
			fight.setType(Type.getNext(name), fight, value, root);
			fight.parse(Type.getNext(name), value);
		} else if (name.startsWith("ko.")) {
			if (KO == null) {
				KO = new Type();
			}
			KO.setType(Type.getNext(name), KO, value, root);
			KO.parse(Type.getNext(name), value);
		} else if (name.startsWith("dko.")) {
			if (DKO == null) {
				DKO = new Type();
			}
			DKO.setType(Type.getNext(name), DKO, value, root);
			DKO.parse(Type.getNext(name), value);
		} else if (name.startsWith("to.")) {
			if (TO == null) {
				TO = new Type();
			}
			TO.setType(Type.getNext(name), TO, value, root);
			TO.parse(Type.getNext(name), value);
		} else if (name.startsWith("win.")) {
			if (win == null) {
				win = new Type();
			}
			win.setType(Type.getNext(name), win, value, root);
			win.parse(Type.getNext(name), value);
		} else if (name.startsWith("win2.")) {
			if (win2 == null) {
				win2 = new Type();
			}
			win2.setType(Type.getNext(name), win2, value, root);
			win2.parse(Type.getNext(name), value);
		} else if (name.startsWith("draw.")) {
			if (draw == null) {
				draw = new Type();
			}
			draw.setType(Type.getNext(name), draw, value, root);
			draw.parse(Type.getNext(name), value);
		}
	}
	
	
	
	public Match getMatch() {
		return match;
	}
	public void setMatch(Match match) {
		this.match = match;
	}
	public Start getStart() {
		return start;
	}
	public void setStart(Start start) {
		this.start = start;
	}
	public MugenPoint getPos() {
		return pos;
	}
	public void setPos(MugenPoint pos) {
		this.pos = pos;
	}
	public Sub getRound() {
		return round;
	}
	public void setRound(Sub round) {
		this.round = round;
	}
	public Type getFight() {
		return fight;
	}
	public void setFight(Type fight) {
		this.fight = fight;
	}
	public TimeTest getCtrl() {
		return ctrl;
	}
	public void setCtrl(TimeTest ctrl) {
		this.ctrl = ctrl;
	}
	public Type getKO() {
		return KO;
	}
	public void setKO(Type ko) {
		KO = ko;
	}
	public Type getDKO() {
		return DKO;
	}
	public void setDKO(Type dko) {
		DKO = dko;
	}
	public Type getTO() {
		return TO;
	}
	public void setTO(Type to) {
		TO = to;
	}
	public TimeTest getSlow() {
		return slow;
	}
	public void setSlow(TimeTest slow) {
		this.slow = slow;
	}
	public OverTime getOver() {
		return over;
	}
	public void setOver(OverTime over) {
		this.over = over;
	}
	public Type getWin() {
		return win;
	}
	public void setWin(Type win) {
		this.win = win;
	}
	public Type getWin2() {
		return win2;
	}
	public void setWin2(Type win2) {
		this.win2 = win2;
	}
	public Type getDraw() {
		return draw;
	}
	public void setDraw(Type draw) {
		this.draw = draw;
	}



	public void process() {
		if (GameFight.getInstance().getGameState().getRoundState() <= Roundstate.COMBAT) {
			getStart().decrease();
			if (getRound().getDefault() != null) {
				if (getRound().getDefault().getDisplaytime() == getRound().getDefault().getOriginalDisplaytime()) {
					SndType snd = MugenSystem.getInstance().getFiles().getFight().getRound().getRounds().get(1).getSnd();
					byte[] data = MugenSystem.getInstance().getFiles().getFight().getFiles().getSnd().getGroup(snd.getGrp()).getSound(snd.getNum());
					SoundSystem.Sfx.playSnd(data);
				}
				getRound().getDefault().decreaseDisplayTime();
				if (getRound().getDefault().getDisplaytime() == 0) {
					SndType snd = MugenSystem.getInstance().getFiles().getFight().getRound().getFight().getSnd();
					byte[] data = MugenSystem.getInstance().getFiles().getFight().getFiles().getSnd().getGroup(snd.getGrp()).getSound(snd.getNum());
					SoundSystem.Sfx.playSnd(data);
				}
			}
		}
		
		if (GameFight.getInstance().getGameState().getRoundState() == Roundstate.VICTORY) {
			if (GameFight.getInstance().getGameState().getLastWin() == WinType.KO)
				getKO().process();
			if (GameFight.getInstance().getGameState().getLastWin() == WinType.DKO)
				getDKO().process();
			if (GameFight.getInstance().getGameState().getLastWin() == WinType.TO)
				getTO().process();
			
		}
	}



	
}
