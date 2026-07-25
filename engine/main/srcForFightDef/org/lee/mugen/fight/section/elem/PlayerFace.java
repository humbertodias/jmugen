package org.lee.mugen.fight.section.elem;



public class PlayerFace extends SimpleElement {
	Type elem;

	public Type getElem() {
		return elem;
	}

	public void setElem(Type elem) {
		this.elem = elem;
	}
	
	@Override
	public void parse(Object root, String name, String value) {
		super.parse(root, name, value);
		if (name.startsWith("elem")) {
			if (elem == null) {
				elem = new Type();
			}
			elem.setType(Type.getNext(name), elem, value, root);
			elem.parse(Type.getNext(name), value);
		} else 	if (name.startsWith("face.")) {
			if (elem == null) {
				elem = new Type();
			}
			elem.setType(Type.getNext(name), elem, value, root);
			elem.parse(Type.getNext(name), value);
		}
	}
}
