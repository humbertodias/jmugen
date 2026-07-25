package org.lee.mugen.fight.system;

import org.lee.mugen.fight.section.Section;

public class Info implements Section {
	private String name;
	private String author;
	public String getName() {
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
			name = value;
		} else if (name.equals("author")) {
			author = value;
		}
	}
	
}
