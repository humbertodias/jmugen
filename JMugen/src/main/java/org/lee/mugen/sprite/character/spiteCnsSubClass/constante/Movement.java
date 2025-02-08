package org.lee.mugen.sprite.character.spiteCnsSubClass.constante;

import java.io.Serializable;

public class Movement implements Cloneable, Serializable {

	public static class Friction implements Cloneable, Serializable {
		private float friction;

		public float getFriction() {
			return friction;
		}

		public void setFriction(float friction) {
			this.friction = friction;
		}
		@Override
		protected Object clone() throws CloneNotSupportedException {
			return super.clone();
		}
	}
	public static class AirJump implements Cloneable, Serializable {
//		 Number of air jumps allowed (opt)
		private int num = 1;
		
//		 Minimum distance from ground before
		// you can air jump (opt)
		private float height = 35;
		
		@Override
		protected Object clone() throws CloneNotSupportedException {
			return super.clone();
		}
		public float getHeight() {
			return height;
		}
		public void setHeight(float height) {
			this.height = height;
		}
		public int getNum() {
			return num;
		}
		public void setNum(int num) {
			this.num = num;
		}

	}
	@Override
	public Object clone() throws CloneNotSupportedException {
		Movement m = (Movement) super.clone();
		m.airjump = (AirJump) airjump.clone();
		m.stand = (Friction) stand.clone();
		m.crouch = (Friction) crouch.clone();
		return m;
	}
	private AirJump airjump = new AirJump(); 
	private float yaccel = 0.44f; // Vertical acceleration
	private Friction stand = new Friction(); // Friction coefficient when
											// standing
	private Friction crouch = new Friction(); // Friction coefficient when
											// crouching
	public float getYaccel() {
		return yaccel;
	}
	public void setYaccel(float yaccel) {
		this.yaccel = yaccel;
	}
	public AirJump getAirjump() {
		return airjump;
	}
	public Friction getCrouch() {
		return crouch;
	}
	public Friction getStand() {
		return stand;
	}
	
	
}
