package org.lee.framework.swing;


import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager2;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;


public class EllipseLayout implements LayoutManager2 {
	/**
	 * begin on top
	 */
	public static final double TOP = -1f/2f;
	/**
	 * begin on bottom
	 */
	public static final double BOTTOM = 1f/2f;
	/**
	 * begin on left
	 */
	public static final double LEFT = 1;
	/**
	 * brgin on right
	 */
	public static final double RIGHT = 0;

	/**
	 * add component right to left
	 */
	public static final int TO_LEFT = -1;
	/**
	 * add component left to right
	 */
	public static final int TO_RIGHT = 1;

	private List<Component> _components;

	private Dimension _componentSize = new Dimension(120, 30);
	private Insets _insets = new Insets(0, 0, 0, 0);

	private int _orientation = TO_LEFT;
	private float _percent = 1f;
	private double _start = TOP;

	/**
	 * Constructor
	 * @param componentSize
	 */
	public EllipseLayout(Dimension componentSize) {
        _components = new ArrayList<Component>();
		_componentSize = componentSize;
	}
	
	/**
	 * Constructor with option
	 * @param componentSize
	 * @param percent
	 * @param start
	 * @param orientation
	 */
	public EllipseLayout(Dimension componentSize, float percent, double start, int orientation) {
        _components = new ArrayList<Component>();
		_componentSize = componentSize;
		_percent = percent;
		_start = start;
		_orientation = orientation;
	}

	/* (non-Javadoc)
	 * @see java.awt.LayoutManager2#addLayoutComponent(java.awt.Component, java.lang.Object)
	 */
	public void addLayoutComponent(Component comp, Object constraints) {
		addLayoutComponent("", comp);
		
	}

	/* (non-Javadoc)
	 * @see java.awt.LayoutManager#addLayoutComponent(java.lang.String, java.awt.Component)
	 */
	public void addLayoutComponent(String name, Component comp) {
		synchronized (comp.getTreeLock()) {
			_components.add(comp);
		}
		
	}


	/* (non-Javadoc)
	 * @see java.awt.LayoutManager2#getLayoutAlignmentX(java.awt.Container)
	 */
	public float getLayoutAlignmentX(Container target) {
		return 0;
	}

	/* (non-Javadoc)
	 * @see java.awt.LayoutManager2#getLayoutAlignmentY(java.awt.Container)
	 */
	public float getLayoutAlignmentY(Container target) {
		return 0;
	}
	
	/**
	 * get orientation
	 * @return
	 */
	public int getOrientation() {
		return _orientation;
	}
	
	private Point getPosition(int nbr, int count, Dimension dim, float percent, double start, int orientation) {
		int xR = (int) (dim.width / (2 * percent));
		int yR = (int) (dim.height / (2 * percent));
		float prop = (float)nbr/(float)(count);
		prop = (float) (prop * 2f * Math.PI + start * Math.PI);
		xR *= Math.cos(prop) * orientation;
		yR *= Math.sin(prop);
		return new Point(xR, yR);
	}

	/**
	 * get start position
	 * @return
	 */
	public double getStart() {
		return _start;
	}

	/* (non-Javadoc)
	 * @see java.awt.LayoutManager2#invalidateLayout(java.awt.Container)
	 */
	public void invalidateLayout(Container target) {
	}

	/* (non-Javadoc)
	 * @see java.awt.LayoutManager#layoutContainer(java.awt.Container)
	 */
	public void layoutContainer(Container parent) {
	      synchronized (parent.getTreeLock()) {
	    	//Insets _insets = parent.getInsets();
	    	int top = _insets.top + _componentSize.height/2;
	    	int bottom = parent.getHeight() - _insets.bottom - _componentSize.height/2;
	    	int left = _insets.left + _componentSize.width/2;
	    	int right = parent.getWidth() - _insets.right - _componentSize.width/2;
	    	
	    	int count = parent.getComponentCount();
	    	
	    	int index = 0;
	    	Dimension allDim = new Dimension(right - left, bottom - top);
	    	for (Component c: _components) {
            //for (Iterator iter = _components.iterator(); iter.hasNext();) {
                //Component c = (Component) iter.next();
	    		c.setSize(_componentSize);
	    		Dimension dim = c.getSize();

	    		Point pt = getPosition(index++, count, allDim, _percent, _start, _orientation);
	    		pt.x += left - dim.width/2 + allDim.width/2;
	    		pt.y += top - dim.height/2 + allDim.height/2;
	    		c.setBounds(pt.x, pt.y, c.getSize().width, c.getSize().height);
	    	}
	      }
	}

	/* (non-Javadoc)
	 * @see java.awt.LayoutManager2#maximumLayoutSize(java.awt.Container)
	 */
	public Dimension maximumLayoutSize(Container target) {
		return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
	}

	/* (non-Javadoc)
	 * @see java.awt.LayoutManager#minimumLayoutSize(java.awt.Container)
	 */
	public Dimension minimumLayoutSize(Container parent) {
	      synchronized (parent.getTreeLock()) {
	    	Dimension dim = new Dimension(0, 0);
	    	return dim;
	      }
	}

	/* (non-Javadoc)
	 * @see java.awt.LayoutManager#preferredLayoutSize(java.awt.Container)
	 */
	public Dimension preferredLayoutSize(Container parent) {
		return new Dimension(200, 200);
	}

	/* (non-Javadoc)
	 * @see java.awt.LayoutManager#removeLayoutComponent(java.awt.Component)
	 */
	public void removeLayoutComponent(Component comp) {
		_components.remove(comp);
	}

//	/**
//	 * get Insets
//	 * @return
//	 */
//	private Insets getInset() {
//		return (Insets) _insets.clone();
//	}
//	/**
//	 * set insets
//	 * @param insets
//	 */
//	private void setInset(Insets insets) {
//		_insets = (Insets) insets.clone();
//	}
	
	/**
	 * set orientation
	 * @param orientation
	 */
	public void setOrientation(int orientation) {
		_orientation = orientation;
	}
	/**
	 * set start
	 * @param start
	 */
	public void setStart(double start) {
		_start = start;
	}
}