package org.lee.mugen.fight.section.elem;

public abstract class CommonType {
	public abstract void parse(String name, String value);
	public static CommonType buildType(String type, Object root) {
		CommonType result = null;
		if (type.equalsIgnoreCase("anim")) {
			result = new AnimType(root);
		} else if (type.equalsIgnoreCase("spr")) {
			result = new SprType(root);
		} else if (type.equalsIgnoreCase("font")) {
			result = new FontType(root);
		}
		return result;
	}
	public void process() {
		// TODO Auto-generated method stub
		
	}
	public CommonType copy() {
		return this;
	}
}
