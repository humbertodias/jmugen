package org.lee.mugen.fight.section.elem;

import org.lee.mugen.geom.MugenPoint;

import org.lee.mugen.util.BeanTools;

public class Bar extends SimpleElement {
	public static class Range {
		MugenPoint x = new MugenPoint();

		public MugenPoint getX() {
			return x;
		}

		public void setX(MugenPoint x) {
			this.x = x;
		}
		
	}
	
	Range range = new Range();

	public Range getRange() {
		return range;
	}

	public void setRange(Range range) {
		this.range = range;
	}
	
	@Override
	public void parse(Object root, String name, String value) {
		super.parse(root, name, value);
		if (name.equalsIgnoreCase("range.x")) {
			MugenPoint rangeX = (MugenPoint) BeanTools.getConvertersMap().get(MugenPoint.class).convert(value);
			getRange().setX(rangeX);
		}
		
	}


}
