package org.lee.mugen.sprite.entity;

import java.awt.Point;
import java.io.Serializable;

/**
 * @author Dr Wong
 *
 */
public class PointF implements Cloneable, Serializable {
	private float x;
	private float y;
	private float z;

	private boolean xSetted;
	private boolean ySetted;
	private boolean zSetted;

	
	public boolean isXSetted() {
		return xSetted;
	}
	public boolean isYSetted() {
		return ySetted;
	}
	public boolean isZSetted() {
		return zSetted;
	}
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	public PointF() {
		
	}

	public PointF(float x, float y) {
		this.x = x;
		this.y = y;
	}
	public PointF(PointF pt) {
		x = pt.x;
		y = pt.y;
		
		xSetted = true;
		ySetted = true;
		
		
	}
	public PointF(Point pt) {
		x = pt.x;
		y = pt.y;
		
		xSetted = true;
		ySetted = true;
	}
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
		xSetted = true;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
		ySetted = true;
	}
	
	public PointF getLocation() {
		return this;
	}
	public void setLocation(float x, float y) {
		this.x = x;
		this.y = y;
		
		xSetted = true;
		ySetted = true;
	}
	public float getZ() {
		return z;
	}
	public void setZ(float z) {
		this.z = z;
		
		zSetted = true;
	}
	public void addX(float x2) {
		x += x2;
		xSetted = true;
	}
	public void addY(float y2) {
		y += y2;
		ySetted = true;
	}
	public void addZ(float z2) {
		z += z2;
		zSetted = true;
	}
}
