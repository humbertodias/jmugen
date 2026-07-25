package org.lee.mugen.fight.section.elem;


public class PlayerName extends SimpleElement {
	Type name;

	public Type getName() {
		return name;
	}

	public void setName(Type name) {
		this.name = name;
	}
	
	@Override
	public void parse(Object root, String pname, String value) {
		super.parse(root, Type.getNext(pname), value);
		if (pname.startsWith("name")) {
			if (name == null) {
				name = new Type();
			}
			name.setType(Type.getNext(pname), name, value, root);
			name.parse(Type.getNext(pname), value);
		}
	}
	
	@Override
	public void init() {
		super.init();
		if (name != null)
			name.init();
	}
}
