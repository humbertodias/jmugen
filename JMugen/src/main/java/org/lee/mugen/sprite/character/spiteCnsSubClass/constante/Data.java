package org.lee.mugen.sprite.character.spiteCnsSubClass.constante;

import java.io.Serializable;

import org.lee.mugen.sprite.entity.Sparkno;

public class Data implements Cloneable, Serializable {
	public static class Fall implements Cloneable, Serializable {
		// Percentage to increase defense everytime player is knocked down
		private float defence_up = 50f;

		public float getDefence_up() {
			return defence_up;
		}

		public void setDefence_up(float defence_up) {
			this.defence_up = defence_up;
		}
		@Override
		protected Object clone() throws CloneNotSupportedException {
			return super.clone();
		}
	}
	
	public static class Liedown implements Cloneable, Serializable {
//		 Time which player lies down for, before getting up
		private int time = 60;

		public int getTime() {
			return time;
		}

		public void setTime(int time) {
			this.time = time;
		}
		@Override
		protected Object clone() throws CloneNotSupportedException {
			return super.clone();
		}
	}
	
	public static class Guard implements Cloneable, Serializable {
//		 Default guard spark number
		private Sparkno sparkno = new Sparkno();


		public Sparkno getSparkno() {
			return sparkno;
		}


		public void setSparkno(Sparkno sparkno) {
			this.sparkno = sparkno;
		}


		@Override
		protected Object clone() throws CloneNotSupportedException {
			return super.clone();
		}
	}
	
	public static class KOClass implements Cloneable, Serializable {
		// 1 to enable echo on KO
		private int echo = 0;

		public int getEcho() {
			return echo;
		}

		public void setEcho(int echo) {
			this.echo = echo;
		}
		@Override
		protected Object clone() throws CloneNotSupportedException {
			return super.clone();
		}
	}
	// Amount of life to start with
	private int life = 1000;
	// attack power (more is stronger)
	private int attack = 100;
	
	private int power = 3000;
	// defensive power (more is stronger)
	private int defence = 100;
	private Fall fall = new Fall();
	private Liedown liedown = new Liedown();
	// Number of points for juggling
	private int airjuggle = 15;
	// Default hit spark number for HitDefs
	private Sparkno sparkno = new Sparkno();
	private Guard guard = new Guard();
	private KOClass ko = new KOClass();
	// Volume offset (negative for softer)
	private int volume = 0;
	// Variables with this index and above will not have their values
	// reset to 0 between rounds or matches. There are 60 int variables,
	// indexed from 0 to 59, and 40 float variables, indexed from 0 to 39.
	// If omitted, then it defaults to 60 and 40 for integer and float
	// variables repectively, meaning that none are persistent, i.e. all
	// are reset. If you want your variables to persist between matches,
	// you need to override state 5900 from common1.cns.
	private int[] intpersistindex = new int[2];
	private float floatpersistindex = 40f;
	

	@Override
	public Object clone() throws CloneNotSupportedException {
		Data data = (Data) super.clone();
		data.fall = (Fall) fall.clone();
		data.liedown = (Liedown) liedown.clone();
		data.guard = (Guard) guard.clone();
		data.ko = (KOClass) ko.clone();
		return data;
	}
	/////////////
	
	public int getAirjuggle() {
		return airjuggle;
	}
	public void setAirjuggle(int airjuggle) {
		this.airjuggle = airjuggle;
	}
	public int getAttack() {
		return attack;
	}
	public void setAttack(int attack) {
		this.attack = attack;
	}
	public int getDefence() {
		return defence;
	}
	public void setDefence(int defence) {
		this.defence = defence;
	}
	public float getFloatpersistindex() {
		return floatpersistindex;
	}
	public void setFloatpersistindex(float floatPersistIndex) {
		this.floatpersistindex = floatPersistIndex;
	}
	public int[] getIntpersistindex() {
		return intpersistindex;
	}
	public void setIntpersistindex(int[] intPersistIndex) {
		this.intpersistindex = intPersistIndex;
	}
	public int getLife() {
		return life;
	}
	public void setLife(int life) {
		this.life = life;
	}
	
	public Sparkno getSparkno() {
		return sparkno;
	}

	public void setSparkno(Sparkno sparkno) {
		this.sparkno = sparkno;
	}

	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}
	public Fall getFall() {
		return fall;
	}
	public Guard getGuard() {
		return guard;
	}
	public KOClass getKo() {
		return ko;
	}
	public Liedown getLiedown() {
		return liedown;
	}
	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power = power;
	}



	

}
