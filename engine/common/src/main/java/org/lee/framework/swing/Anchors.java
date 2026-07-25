package org.lee.framework.swing;

public final class Anchors {

	/////////////////////////////////////////////
	/*
	 * Constants
	 */
	////////////////////////////////////////////
	
	private static final int TOP = 1;
	private static final int BOTTOM = 2;
	private static final int LEFT = 4;
	private static final int RIGHT = 8;
	
	
	///////////////////////////////////////////
	/*
	 * Fields
	 */
	//////////////////////////////////////////
	
	/**
	 * 
	 */
	private int _anchors;
	
	/////////////////////////////////////////
	/*
	 * private Methods
	 */
	/////////////////////////////////////////
	
	private Anchors addAnchor(int v) {
		if (v != TOP && v != BOTTOM && v != LEFT && v != RIGHT)
			throw new IllegalArgumentException("not TOP, BOTTOM, LEFT, RIGHT value");
		_anchors |= v;
		return this;
	}
	private Anchors removeAnchor(int v) {
		if (v != TOP && v != BOTTOM && v != LEFT && v != RIGHT)
			throw new IllegalArgumentException("not TOP, BOTTOM, LEFT, RIGHT value");
		_anchors &= ~v;
		return this;
	}
	
	////////////////////////////////////////
	/*
	 * Public Methods
	 */
	////////////////////////////////////////
	
	public Anchors addTop() {
		return addAnchor(TOP);
	}
	public Anchors addBottom() {
		return addAnchor(BOTTOM);
	}
	public Anchors addLeft() {
		return addAnchor(LEFT);
	}
	public Anchors addRight() {
		return addAnchor(RIGHT);
	}
	
	public Anchors removeTop() {
		return removeAnchor(TOP);
	}
	public Anchors removeBottom() {
		return removeAnchor(BOTTOM);
	}
	public Anchors removeLeft() {
		return removeAnchor(LEFT);
	}
	public Anchors removeRight() {
		return removeAnchor(RIGHT);
	}
	
	
	public boolean isTop() {
		return (TOP & _anchors) == TOP;
	}
	public boolean isBottom() {
		return (BOTTOM & _anchors) == BOTTOM;
	}
	public boolean isLeft() {
		return (LEFT & _anchors) == LEFT;
	}
	public boolean isRight() {
		return (RIGHT & _anchors) == RIGHT;
	}
}
