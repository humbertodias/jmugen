package org.lee.mugen.sprite.character.spiteCnsSubClass;

import java.io.Serializable;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.sprite.character.Sprite;


public class VelSetSub implements Cloneable, Serializable {
	private int precision = 1000;
	private float x;
	private float y;
	private float z;
	
	private String spriteId;

	public VelSetSub(String spriteId) {
		this.spriteId = spriteId;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	public void addX(float x) {

		this.x += x;
	}
	public void addY(float y) {
		this.y += y;
	}
	public void addZ(float z) {
		this.z += z;
	}
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	public float getZ() {
		return z;
	}
	public void mulX(float x) {
		this.x *= x;
		if ((int)(this.x * precision) == 0)
			this.x = 0;
	}
	public void mulY(float y) {
		this.y *= y;
		if ((int)(this.y * precision) == 0)
			this.y = 0;
	}
	public void mulZ(float z) {
		this.z *= z;
		if ((int)(this.z * precision) == 0)
			this.z = 0;
	}
	public void setX(float x) {
		this.x = x;

	}
	public void setY(float y) {
		this.y = y;
	}
	public void setZ(float z) {
		this.z = z;
	}

	public String getSpriteId() {
		return spriteId;
	}

	public void setSpriteId(String spriteId) {
		this.spriteId = spriteId;
	}

	public float getXReal() {

		Sprite spr = GameFight.getInstance().getSpriteInstance(spriteId);
		return spr.isFlip()? -x: x;
	}

}
