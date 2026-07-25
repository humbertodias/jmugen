package org.lee.mugen.fight.system.elem;

import org.lee.mugen.fight.section.Section;
import org.lee.mugen.fight.section.elem.PlayerFace;
import org.lee.mugen.fight.section.elem.PlayerName;
import org.lee.mugen.fight.section.elem.Type;

public class PlayerSelectInfo implements Section {
	PlayerCursor cursor;
	Type random$move;
	
	Type face;
	Type name;
	
	public PlayerCursor getCursor() {
		return cursor;
	}

	public void setCursor(PlayerCursor cursor) {
		this.cursor = cursor;
	}

	public Type getRandom$move() {
		return random$move;
	}

	public void setRandom$move(Type random$move) {
		this.random$move = random$move;
	}

	public Type getFace() {
		return face;
	}

	public void setFace(Type face) {
		this.face = face;
	}

	public Type getName() {
		return name;
	}

	public void setName(Type name) {
		this.name = name;
	}

	@Override
	public void parse(Object root, String name, String value) throws Exception {

		if (name.startsWith("cursor.")) {
			if (cursor == null)
				cursor = new PlayerCursor();
			cursor.parse(root, Type.getNext(name), value);
		} else if (name.startsWith("random.move.")) {
			if (random$move == null)
				random$move = new Type();
			random$move.setType(Type.getNext(name), random$move, value, root);
			random$move.parse(Type.getNext(name), value);
			
		} else if (name.startsWith("face.")) {
			if (face == null)
				face = new Type();
			face.setType(Type.getNext(name), face, value, root);
			face.parse(Type.getNext(name), value);
		} else if (name.startsWith("name.")) {
			if (this.name == null)
				this.name = new Type();
			this.name.setType(Type.getNext(name), this.name, value, root);
			this.name.parse(Type.getNext(name), value);
		}
		
	}
	
	
}
