package org.lee.mugen.fight.select;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.io.IOUtils;
import org.lee.mugen.fight.section.Section;
import org.lee.mugen.sprite.parser.Parser;
import org.lee.mugen.sprite.parser.Parser.GroupText;

public class Select {
	private Characters characters; 
	private ExtraStages extraStages;
	private Options options;
	
	public Characters getCharacters() {
		return characters;
	}

	public void setCharacters(Characters characters) {
		this.characters = characters;
	}

	public ExtraStages getExtraStages() {
		return extraStages;
	}

	public void setExtraStages(ExtraStages extraStages) {
		this.extraStages = extraStages;
	}

	public Options getOptions() {
		return options;
	}

	public void setOptions(Options options) {
		this.options = options;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	private String filename;
	public Select(String filename) throws Exception {
		this.filename = filename;
		parse();
	}

	private void parse() throws Exception {
		BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "utf-8"));
		List<GroupText> groups = Parser.getGroupTextMap(r, true, true);

		for (GroupText grp: groups) {
			if (grp.getSection().equals("characters")) {
				characters = new Characters();
				parse(characters, grp);
			} else if (grp.getSection().equals("extrastages")) {
				extraStages = new ExtraStages();
				parse(extraStages, grp);				
			} else if (grp.getSection().equals("options")) {
				options = new Options();
				parse(options, grp);
			} 
			
		}
	}

	private void parse(Section section, GroupText grp) throws Exception {
		StringTokenizer token = new StringTokenizer(grp.getText().toString(), "\r\n");
		while (token.hasMoreTokens()) {
			section.parse(this, token.nextToken(), null);
			
		}
	}
}
