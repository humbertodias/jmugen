package org.lee.mugen.fight.section.elem;

public class PlayerTurnsFace extends PlayerFace {
	public static class Teammate {
		Type bg;
		Type ko;
		Type face;
		public Type getBg() {
			return bg;
		}
		public void setBg(Type bg) {
			this.bg = bg;
		}
		public Type getKo() {
			return ko;
		}
		public void setKo(Type ko) {
			this.ko = ko;
		}
		public Type getFace() {
			return face;
		}
		public void setFace(Type face) {
			this.face = face;
		}
		
	}
	Teammate teammate = new Teammate();
	public Teammate getTeammate() {
		return teammate;
	}
	public void setTeammate(Teammate teammate) {
		this.teammate = teammate;
	}
}
