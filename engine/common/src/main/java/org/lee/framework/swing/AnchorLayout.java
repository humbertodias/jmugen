package org.lee.framework.swing;


import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager2;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class AnchorLayout  implements LayoutManager2 {

	private Collection<Component> _components;
	private Map<Component, AnchorConstraint> _map;
	private Dimension _oldParentDim;
	public AnchorLayout() {

		_components = new HashSet<Component>();
		_map = new HashMap<Component, AnchorConstraint>();
		
	}
	
	public void addLayoutComponent(Component comp, Object constraints) {
		if (!(constraints instanceof AnchorConstraint))
			throw new IllegalArgumentException("constraints must be CustomLayoutConstraint");
		synchronized (comp.getTreeLock()) {
			_components.add(comp);
			_map.put(comp, (AnchorConstraint) constraints);
		}
	}
	
	private void anchorsNotBorder(
			Component comp, 
			AnchorConstraint clc, 
			float oldWidth, 
			float oldHeight,
			float newWidth,
			float newHeight) {

		

		int deltaX = Math.abs((int) (oldWidth - newWidth));
		int deltaY = Math.abs((int) (oldHeight - newHeight));
		if (clc._oldDim == null || clc._newDim == null ||
				clc._newDim.height != comp.getHeight() || clc._newDim.width != comp.getWidth()) {
			clc._oldDim = comp.getPreferredSize();
		}
		if (clc._newDim == null || 
				clc._newDim.height != comp.getHeight() || clc._newDim.width != comp.getWidth()) {
			clc._newDim = new Dimension();
			clc._newDim.height = comp.getHeight();
			clc._newDim.width = comp.getWidth();
		}
		if (clc._oldPoint == null || clc._newPoint == null ||
				clc._newPoint.x != comp.getLocation().x || clc._newPoint.y != comp.getLocation().y) {
			clc._oldPoint = comp.getLocation();
		}
		if (clc._newPoint == null ||
				clc._newPoint.x != comp.getLocation().x || clc._newPoint.y != comp.getLocation().y) {
			clc._newPoint = comp.getLocation();
		}
		int x = clc._oldPoint.x;
		int y = clc._oldPoint.y;
		int w = clc._oldDim.width;
		int h = clc._oldDim.height;
		if (oldWidth > newWidth) {

			if (!clc.getAnchors().isLeft()) {
				x -= deltaX;
				w += deltaX;
			}
			if (clc.getAnchors().isRight()) {
				w -= deltaX;
			}
				
		} else {
			if (!clc.getAnchors().isLeft()) {
				x += deltaX;
				w -= deltaX;
			}
			if (clc.getAnchors().isRight()) {
				w += deltaX;
			}
		}
		if (oldHeight > newHeight) {
			if (!clc.getAnchors().isTop()) {
				y -= deltaY;
				h += deltaY;
			}
			if (clc.getAnchors().isBottom()) {
				h -= deltaY;
			}
		} else {
			if (!clc.getAnchors().isTop()) {
				y += deltaY;
				h -= deltaY;
			}
			if (clc.getAnchors().isBottom()) {
				h += deltaY;
			}
		}

		
		if (w < comp.getMinimumSize().width) {
			w = comp.getMinimumSize().width;
		}
		if (h < comp.getMinimumSize().height) {
			h = comp.getMinimumSize().height;
		}
		if (w > comp.getMaximumSize().width && comp.getMaximumSize().width > 0) {
			w = comp.getMaximumSize().width;
		}
		if (h > comp.getMaximumSize().height && comp.getMaximumSize().height > 0) {
			h = comp.getMaximumSize().height;
		}
		
		comp.setBounds(x, y, w, h);
		clc._newDim.width = w;
		clc._newDim.height = h;
		clc._newPoint.x = x;
		clc._newPoint.y = y;
	}

	
	public void layoutContainer(Container parent) {
		synchronized (parent.getTreeLock()) {
			if (_oldParentDim == null)
				_oldParentDim = parent.getSize();
			float oldWidth = _oldParentDim.width;
			float oldHeight = _oldParentDim.height;
		//	_oldParentDim = parent.getSize();
			float newWidth = parent.getSize().width;
			float newHeight = parent.getSize().height;
			for (Component comp : _components) {
				AnchorConstraint clc = _map.get(comp);
				anchorsNotBorder(comp, clc, oldWidth, oldHeight, newWidth, newHeight);
			}
		}
	}
	public static void main(String[] args) {
		JFrame frm = new JFrame();
		frm.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		JPanel contentPane = (JPanel) frm.getContentPane();
		contentPane.setPreferredSize(new Dimension(600, 600));
		AnchorLayout cl = new AnchorLayout();
		contentPane.setLayout(cl);
		JButton btn = new JButton("HELLO");
	//	btn.setBounds(20, 20, 380, 380);
		btn.setLocation(30, 30);
		
		btn.setMinimumSize(new Dimension(125, 50));
		btn.setMaximumSize(new Dimension(500, 400));
		btn.setPreferredSize(new Dimension(125, 100));

		Anchors a = new Anchors().addTop().addBottom().addRight().addLeft();

		AnchorConstraint clc = new AnchorConstraint(a);
		contentPane.add(btn, clc);

		frm.pack();
		//cl.init(contentPane);
		frm.setVisible(true);
		
	}

	public void init(Component c) {
		_oldParentDim = c.getSize();
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

	public void invalidateLayout(Container target) {		
	}

	public void addLayoutComponent(String name, Component comp) {
		
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

}
