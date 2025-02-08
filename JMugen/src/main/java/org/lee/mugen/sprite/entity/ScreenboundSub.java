package org.lee.mugen.sprite.entity;

import java.awt.Point;
import java.io.Serializable;

public class ScreenboundSub implements Serializable {
	private int time;
	private Point movecamera = new Point(1,1);
	private int value = 0;
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public Point getMovecamera() {
		return movecamera;
	}
	public void setMovecamera(Point movecamera) {
		this.movecamera = movecamera;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
	public void decrease() {
		if (time > 0)
			this.time--;
	}
	public void activate() {
		time = 2;
	}
	public boolean isActivate() {
		return time > 0;
	}
	public void deActivate() {
		time = 0;
	}
	public boolean isScreenbound() {
		return time > 0 &&  value == 0;
	}
	
	public boolean isCamCanMoveX() {
		return time <= 0 || (time > 0 &&  movecamera.x != 0);
	}
	public boolean isCamCanMoveY() {
		return time <= 0 || (time > 0 &&  movecamera.y != 0);
	}
}
