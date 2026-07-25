package org.lee.mugen.fight.select;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.lee.mugen.core.JMugenConstant;
import org.lee.mugen.fight.section.Section;
import org.lee.mugen.sprite.parser.Parser;
import org.lee.mugen.sprite.parser.Parser.GroupText;

public class ExtraStages implements Section {
	LinkedList<String> stages = new LinkedList<String>();
	@Override
	public void parse(Object root, String name, String value) throws Exception {
		stages.add(name);
		
	}
	public LinkedList<String> getStages() {
		return stages;
	}

	Map<String, String> pathRealNameMap = new HashMap<String, String>();
	public String getRealName(String path) {
		if (pathRealNameMap.containsKey(path))
			return pathRealNameMap.get(path);
		try {
			List<GroupText> groups = Parser.getGroupTextMap(new InputStreamReader(new FileInputStream(JMugenConstant.RESOURCE + path), "utf-8"), true);
			for (GroupText grp: groups) {
				if (grp.getSection().equals("info")) {
					String stageName = grp.getKeyValues().get("name");
					if (stageName.startsWith("\"") && stageName.endsWith("\""))
						stageName = stageName.substring(1, stageName.length() - 1);
					pathRealNameMap.put(path, stageName);
				}
			}
			return pathRealNameMap.get(path);
			
		} catch (Exception e) {
			e.printStackTrace();
			return path;
		}
	}
	
}
