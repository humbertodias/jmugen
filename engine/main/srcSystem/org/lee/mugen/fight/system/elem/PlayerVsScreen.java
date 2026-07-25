package org.lee.mugen.fight.system.elem;

import org.lee.mugen.geom.MugenPoint;

import org.lee.mugen.fight.section.elem.PlayerFace;
import org.lee.mugen.fight.section.elem.PlayerName;
import org.lee.mugen.fight.section.elem.Type;
import org.lee.mugen.sprite.entity.PointF;
import org.lee.mugen.util.BeanTools;

public class PlayerVsScreen extends PlayerFace {
	Type name;
	MugenPoint pos = new MugenPoint();
	int facing;
	PointF scale = new PointF(1,1);
	public MugenPoint getPos() {
		return pos;
	}

	public void setPos(MugenPoint pos) {
		this.pos = pos;
	}

	public int getFacing() {
		return facing;
	}

	public void setFacing(int facing) {
		this.facing = facing;
	}

	public PointF getScale() {
		return scale;
	}

	public void setScale(PointF scale) {
		this.scale = scale;
	}

	public Type getName() {
		return name;
	}

	public void setName(Type name) {
		this.name = name;
	}
	@Override
	public void parse(Object root, String name, String value) {
		super.parse(root, name, value);
		if (name.startsWith("name.")) {
			if (this.name == null) {
				this.name = new Type();
			}
			this.name.setType(Type.getNext(name), this.name, value, root);
			this.name.parse(Type.getNext(name), value);
		} else if (name.startsWith("pos")) {
			pos = (MugenPoint) BeanTools.getConvertersMap().get(MugenPoint.class).convert(value);
		}  else if (name.startsWith("facing")) {
			facing = Integer.parseInt(value);
		} else if (name.startsWith("scale")) {
			scale = (PointF) BeanTools.getConvertersMap().get(PointF.class).convert(value);
		}
	}
}
