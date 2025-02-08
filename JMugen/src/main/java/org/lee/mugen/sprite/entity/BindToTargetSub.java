package org.lee.mugen.sprite.entity;

import java.io.Serializable;


public class BindToTargetSub implements Serializable {
	public static enum BindToTargetType {
		FOOT, HEAD, MID;
	}
	public static class Pos implements Serializable {
		BindToTargetType type = BindToTargetType.MID; //TODO check if default
		int x;
		int y;
		public BindToTargetType getType() {
			return type;
		}
		public void setType(BindToTargetType type) {
			this.type = type;
		}
		public int getX() {
			return x;
		}
		public void setX(int x) {
			this.x = x;
		}
		public int getY() {
			return y;
		}
		public void setY(int y) {
			this.y = y;
		}
		
	}
	private int id = -1;
	private Pos pos = new Pos();
	
	private int time = 1;
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	public int getId() {
		return id;
	}
	public Pos getPos() {
		return pos;
	}
	public int getTime() {
		return time;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setPos(Pos pos) {
		this.pos = pos;
	}
	public void setTime(int time) {
		this.time = time;
	}
	


}
