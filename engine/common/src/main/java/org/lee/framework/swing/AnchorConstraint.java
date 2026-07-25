package org.lee.framework.swing;


import java.awt.Dimension;
import java.awt.Point;

import org.lee.framework.lang.ClassUtils;


public final class AnchorConstraint {
	
	private Anchors _anchors;
	Point _oldPoint;
	Dimension _oldDim;

	Point _newPoint;
	Dimension _newDim;
	
    public AnchorConstraint(Anchors anchors) {
        _anchors = anchors;
    }
	public Anchors getAnchors() {
		return _anchors;
	}

	@Override
	public String toString() {
		return ClassUtils.toString(this);
	}
}
