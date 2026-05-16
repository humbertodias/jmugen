package org.lee.mugen.fight.system.elem;

import org.lee.mugen.geom.MugenPoint;
import org.lee.mugen.geom.MugenRect;

import org.lee.mugen.fight.section.Section;
import org.lee.mugen.sprite.entity.PointF;
import org.lee.mugen.util.BeanTools;

public class StageDisplay implements Section {

	
	boolean enable;
	MugenPoint pos;
	MugenRect rectangle;
	PointF scale;
	MugenPoint camera;
	
	
	public boolean isEnable() {
		return enable;
	}


	public void setEnable(boolean enable) {
		this.enable = enable;
	}


	public MugenPoint getPos() {
		return pos;
	}


	public void setPos(MugenPoint pos) {
		this.pos = pos;
	}


	public MugenRect getRectangle() {
		return rectangle;
	}


	public void setRectangle(MugenRect rectangle) {
		this.rectangle = rectangle;
	}


	public PointF getScale() {
		return scale;
	}


	public void setScale(PointF scale) {
		this.scale = scale;
	}


	public MugenPoint getCamera() {
		return camera;
	}


	public void setCamera(MugenPoint camera) {
		this.camera = camera;
	}


	@Override
	public void parse(Object root, String name, String value) throws Exception {
		if (name.equals("enable")) {
			enable = Integer.parseInt(value) != 0;
		} else if (name.equals("pos")) {
			pos = (MugenPoint) BeanTools.getConvertersMap().get(MugenPoint.class).convert(value);
		} else if (name.equals("rectangle")) {
			rectangle = (MugenRect) BeanTools.getConvertersMap().get(MugenRect.class).convert(value);
		} else if (name.equals("scale")) {
			scale = (PointF) BeanTools.getConvertersMap().get(PointF.class).convert(value);
		} else if (name.equals("camera")) {
			camera = (MugenPoint) BeanTools.getConvertersMap().get(MugenPoint.class).convert(value);
		}
	}



}
