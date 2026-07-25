package org.lee.mugen.stage.section.elem;

import org.lee.mugen.fight.section.Section;



public class Info implements Section {
	public Info() {
	}
	private String name = "";
	private String author = "";
	
	public String getName() {
		if (name.startsWith("\"") && name.endsWith("\""))
			return name.substring(1, name.length() - 1);
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public void parse(Object root, String name, String value) throws Exception {
		if (name.equals("name")) {
			this.name = value;
		} else if (name.equals("author")) {
			author = value;
		}
		
	}

	 
}
