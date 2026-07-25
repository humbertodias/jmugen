package org.lee.mugen.fight.section.elem;

public class Start {

	int x;
	int y;
	int time;
	int originalTime;
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public int getOriginalTime() {
		return originalTime;
	}
	public void setOriginalTime(int originalTime) {
		this.originalTime = originalTime;
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
	public void decrease() {
		if (time > 0)
			time--;
		
	}
	public void init() {
		time = originalTime;
	}

}
