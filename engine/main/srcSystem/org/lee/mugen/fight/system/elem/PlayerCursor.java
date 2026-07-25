package org.lee.mugen.fight.system.elem;

import org.lee.mugen.geom.MugenPoint;

import org.lee.mugen.fight.section.Section;
import org.lee.mugen.fight.section.elem.Type;
import org.lee.mugen.util.BeanTools;

public class PlayerCursor implements Section {
	private MugenPoint startcell;
	private Type active;
	private Type done;
	private Type move;
	


	public Type getMove() {
		return move;
	}

	public void setMove(Type move) {
		this.move = move;
	}

	@Override
	public void parse(Object root, String name, String value) throws Exception {
		if (name.equals("startcell")) {
			startcell = (MugenPoint) BeanTools.getConvertersMap().get(MugenPoint.class).convert(value);
		} else if (name.startsWith("active")) {
			if (active == null)
				active = new Type();
			active.setType(Type.getNext(name), active, value, root);
			active.parse(Type.getNext(name), value);
		} else if (name.startsWith("done")) {
			if (done == null)
				done = new Type();
			done.setType(Type.getNext(name), done, value, root);
			done.parse(Type.getNext(name), value);
		} else if (name.startsWith("move")) {
			if (move == null)
				move = new Type();
			move.setType(Type.getNext(name), move, value, root);
			move.parse(Type.getNext(name), value);
		}
		
	}
	
	public MugenPoint getStartcell() {
		return startcell;
	}
	public void setStartcell(MugenPoint startcell) {
		this.startcell = startcell;
	}
	public Type getActive() {
		return active;
	}
	public void setActive(Type active) {
		this.active = active;
	}
	public Type getDone() {
		return done;
	}
	public void setDone(Type done) {
		this.done = done;
	}

	
}
