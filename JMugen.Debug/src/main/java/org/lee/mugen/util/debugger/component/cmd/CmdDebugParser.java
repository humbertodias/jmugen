package org.lee.mugen.util.debugger.component.cmd;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.lee.mugen.core.JMugenConstant;
import org.lee.mugen.sprite.parser.Parser.GroupText;


public class CmdDebugParser {

	enum FileType {
		CMD, CNS
	}
	static class GrpTxtCategory {
		private String category;
		private List<GroupText> list = new ArrayList<GroupText>();
		public GrpTxtCategory(String category) {
			this.category = category;
		}
		void add(GroupText grp) {
			list.add(grp);
		}
		public String getCategory() {
			return category;
		}
		public List<GroupText> getList() {
			return list;
		}
		@Override
		public String toString() {
			return category;
		}
	}
	private static final Pattern grpPattern = Pattern.compile("^\\s*;-\\|\\s*(.*)\\s*\\|-+.*");
	public static final String S_COMMENT_OR_EMPTY_REGEX = "^\\s*;.*$|^\\s*$";
	public static final String S_END = "(?:(?:\\s*;.*$)|(?:\\s*$))";
	public static final String S_FLOAT_REGEX = "((?:\\+|-)?(?:(?:\\.\\d+)|(?:\\d+(?:\\.\\d*)?)))";//"[+-]*(?:\\d+\\.\\d+)|(?:\\d+)";
	public static final String S_STATE_DEF_TITLE_REGEX = "\\s*\\[ *statedef *" + S_FLOAT_REGEX + " *\\]" + S_END;
	public static final Pattern P_COMMENT_OR_EMPTY_REGEX = Pattern.compile(S_COMMENT_OR_EMPTY_REGEX);
	public static final String S_STATEDEF = " *statedef +([a-zA-Z0-9\\ \\-\\+\\_\\(\\)\\{\\}\\,]*,.*) *";
	public static final Pattern P_END = Pattern.compile(S_END);
	public static final Pattern P_SECTION_REGEX = Pattern.compile("^\\s*\\[(.*)\\]" + S_END);
	
	private static final Pattern P_STATE_DEF_TITLE_REGEX = Pattern.compile(S_STATE_DEF_TITLE_REGEX, Pattern.CASE_INSENSITIVE);
	public static final Pattern P_STATEDEF = Pattern.compile(S_STATEDEF, Pattern.CASE_INSENSITIVE);

	private List<GrpTxtCategory> gtCategories;
	
	private static String[] getSeparateKeyValue(String line, boolean caseSensitive) {
		String[] keyValue = new String[2];
		if (P_COMMENT_OR_EMPTY_REGEX.matcher(line).find())
			return null;
		line = caseSensitive? line :line.trim().toLowerCase();
		int indexEnd = line.indexOf(";"); // search comment
		indexEnd = indexEnd == -1 ? line.length() - 1 : indexEnd - 1;
		int indexEqual = line.indexOf("=");
		keyValue[0] = "";
		keyValue[1] = "";
		if (-1 == indexEqual)
			return null;
		keyValue[0] = line.substring(0, indexEqual).trim();
		keyValue[1] = line.substring(indexEqual + 1,
				indexEqual + 1 + indexEnd - indexEqual).trim();
		return keyValue;
	}
	
	

	public CmdDebugParser(FileType fileType, String...files) throws Exception {
		switch (fileType) {
		case CNS:
			buildCns(files);
			break;
		case CMD:
			buildCmd(files);
			break;

		default:
			throw new IllegalArgumentException("Accept CNS or CMD type only");
		}
	}
	void buildCmd(String...files) throws IOException {
		List<InputStream> ins = new ArrayList<InputStream>();
		for (String file: files) 
			ins.add(new FileInputStream(new File(file)));
		ins.add(new ByteArrayInputStream(";-| Mandatory State for basic Move |-\n".getBytes()));
		ins.add(new FileInputStream(JMugenConstant.RESOURCE + "data/common.cmd"));
		Enumeration<InputStream> eIns = Collections.enumeration(ins);
		SequenceInputStream in = new SequenceInputStream(eIns);
		
		BufferedReader r = new BufferedReader(new InputStreamReader(in, "utf-8"));
		gtCategories = parseCmdCategories(r, true, false);
		
	}
	
	
	void buildCns(String...files) throws IOException {
		List<InputStream> ins = new ArrayList<InputStream>();
		for (String file: files) 
			ins.add(new FileInputStream(new File(file)));
		ins.add(new ByteArrayInputStream(";-| Mandatory State for basic Move |-\n".getBytes()));
		ins.add(new FileInputStream(JMugenConstant.RESOURCE + "data/common.cmd"));
		Enumeration<InputStream> eIns = Collections.enumeration(ins);
		SequenceInputStream in = new SequenceInputStream(eIns);
		
		BufferedReader r = new BufferedReader(new InputStreamReader(in, "utf-8"));
		gtCategories = parseCnsCategories(r, true, false);
		
	}
	public List<GrpTxtCategory> getCategories() {
		return gtCategories;
	}
	
	public List<GrpTxtCategory> parseCmdCategories(BufferedReader r, boolean caseSensitive, boolean keyCaseSensistive) throws IOException {
		List<GrpTxtCategory> result = new ArrayList<GrpTxtCategory>();
		GrpTxtCategory cng = new GrpTxtCategory("not category");
		result.add(cng);
		
		String line;
		GroupText grp = null;
		boolean disableCategory = false;
		while ((line = r.readLine()) != null) {

			Matcher m = grpPattern.matcher(line);
			if (m.find()) {
				String category = m.group(1);
				if (!disableCategory || category.toLowerCase().contains("Mandatory State for basic Move".toLowerCase())) {
					cng = new GrpTxtCategory(category);
					result.add(cng);
				}
			}

			m = P_STATE_DEF_TITLE_REGEX.matcher(line);
			if (m.find()) {
				String category = "Cmd State Controller";
				cng = new GrpTxtCategory(category);
				result.add(cng);
				disableCategory = true;
			}
			
			if (P_COMMENT_OR_EMPTY_REGEX.matcher(line).find()) {
				continue;
			}
			line = line.replaceAll(S_END, "");
			m = P_SECTION_REGEX.matcher(line);
			if (m.find()) {
				if (!caseSensitive)
					line = line.toLowerCase();
				grp = new GroupText();
				grp.setSection(m.group(1));
				grp.setSectionRaw(line);
				cng.add(grp);
			} else {
				String[] keyValue = getSeparateKeyValue(line, true);
				grp.appendText(line);
				grp.getOriginalKeysOrdered().add(keyValue[0]);
				keyValue[0] = keyCaseSensistive? keyValue[0]: 
					caseSensitive? keyValue[0]: keyValue[0].toLowerCase();
				keyValue[1] = caseSensitive? keyValue[1]: keyValue[1].toLowerCase();
				grp.getKeysOrdered().add(keyValue[0]);
				grp.getKeyValues().put(keyValue[0], keyValue[1]);
				
			}
		}
		for (Iterator<GrpTxtCategory> iter = result.iterator(); iter.hasNext();) {
			GrpTxtCategory c = iter.next();
			if (c.getList().isEmpty()) {
				iter.remove();
			}
			
		}
        return result;
    }
	private List<GrpTxtCategory> getInitCategoryForCnsCategories() {
		List<GrpTxtCategory> cmdData = new ArrayList<GrpTxtCategory>();
		GrpTxtCategory constMoves = new GrpTxtCategory("CONST");
		GrpTxtCategory basicMoves = new GrpTxtCategory("Basic Moves");
		GrpTxtCategory jumpMoves = new GrpTxtCategory("Jump Moves");
		GrpTxtCategory runMoves = new GrpTxtCategory("Run Moves");
		GrpTxtCategory guardMoves = new GrpTxtCategory("Guard Moves");
		GrpTxtCategory hitGuardMoves = new GrpTxtCategory("Hit Guard Moves");
		GrpTxtCategory lostStates = new GrpTxtCategory("Lost States");
		GrpTxtCategory introStates = new GrpTxtCategory("Intro States");
		GrpTxtCategory hitStates = new GrpTxtCategory("Hit states");
		GrpTxtCategory otherStates = new GrpTxtCategory("Other States");
		GrpTxtCategory customStates = new GrpTxtCategory("Custom States");

		cmdData.add(constMoves);
		cmdData.add(basicMoves);
		cmdData.add(jumpMoves);
		cmdData.add(runMoves);
		cmdData.add(guardMoves);
		cmdData.add(hitGuardMoves);
		cmdData.add(lostStates);
		cmdData.add(introStates);
		cmdData.add(hitStates);
		cmdData.add(otherStates);
		cmdData.add(customStates);
		
		return cmdData;
	}
	
	private GrpTxtCategory getGrpTxtByPreDefineCategoriesForStatedef(List<GrpTxtCategory> cmdData, int statedefNum) {
		Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(0, "Basic Moves");
		map.put(10, "Basic Moves");
		map.put(11, "Basic Moves");
		map.put(12, "Basic Moves");
		map.put(20, "Basic Moves");

		map.put(40, "Jump Moves");
		map.put(45, "Jump Moves");
		map.put(50, "Jump Moves");
		map.put(51, "Jump Moves");
		map.put(52, "Jump Moves");

		map.put(100, "Run Moves");
		map.put(105, "Run Moves");
		map.put(106, "Run Moves");
		map.put(110, "Run Moves");
		map.put(115, "Run Moves");

		map.put(120, "Guard Moves");
		map.put(130, "Guard Moves");
		map.put(131, "Guard Moves");
		map.put(132, "Guard Moves");
		map.put(140, "Guard Moves");

		map.put(150, "Hit Guard Moves");
		map.put(151, "Hit Guard Moves");
		map.put(152, "Hit Guard Moves");
		map.put(153, "Hit Guard Moves");
		map.put(154, "Hit Guard Moves");
		map.put(155, "Hit Guard Moves");

		map.put(170, "Lost States");
		map.put(175, "Lost States");

		map.put(190, "Intro States");
		map.put(191, "Intro States");
		map.put(192, "Intro States");
		map.put(193, "Intro States");
		map.put(194, "Intro States");
		map.put(195, "Intro States");
		map.put(196, "Intro States");
		map.put(197, "Intro States");
		map.put(198, "Intro States");
		map.put(199, "Intro States");

		map.put(5000, "Hit states");
		map.put(5001, "Hit states");
		map.put(5010, "Hit states");
		map.put(5011, "Hit states");
		map.put(5020, "Hit states");
		map.put(5030, "Hit states");
		map.put(5035, "Hit states");
		map.put(5040, "Hit states");
		map.put(5050, "Hit states");
		map.put(5070, "Hit states");
		map.put(5071, "Hit states");
		map.put(5080, "Hit states");
		map.put(5081, "Hit states");
		map.put(5100, "Hit states");
		map.put(5101, "Hit states");
		map.put(5110, "Hit states");
		map.put(5120, "Hit states");
		map.put(5150, "Hit states");
		map.put(5200, "Hit states");
		map.put(5201, "Hit states");
		map.put(5210, "Hit states");

		map.put(5500, "Other States");
		map.put(5900, "Other States");

		String value = map.get(statedefNum);

		GrpTxtCategory custom = null;
		for (GrpTxtCategory c: cmdData) {
			if (c.category.equalsIgnoreCase(value))
				return c;
			if (c.category.equalsIgnoreCase("Custom States"))
				custom = c;
		}
		return custom;
	}
	
	public List<GrpTxtCategory> parseCnsCategories(BufferedReader r, boolean caseSensitive, boolean keyCaseSensistive) throws IOException {
		List<GrpTxtCategory> result = getInitCategoryForCnsCategories();
		GrpTxtCategory cng = new GrpTxtCategory("not category");
		result.add(cng);
		
		String line;
		GroupText grp = null;
		boolean disableCategory = false;
		while ((line = r.readLine()) != null) {

//			Matcher m = grpPattern.matcher(line);
//			if (m.find()) {
//				String category = m.group(1);
//				if (!disableCategory || category.toLowerCase().contains("Mandatory State for basic Move".toLowerCase())) {
//					cng = new gtCategory(category);
//					result.add(cng);
//				}
//			}

			Matcher m = P_STATE_DEF_TITLE_REGEX.matcher(line);
			if (m.find()) {
				String stateDefNum = m.group(1);
				cng = getGrpTxtByPreDefineCategoriesForStatedef(result, Integer.parseInt(stateDefNum));
			}
			
			if (P_COMMENT_OR_EMPTY_REGEX.matcher(line).find()) {
				continue;
			}
			line = line.replaceAll(S_END, "");
			m = P_SECTION_REGEX.matcher(line);
			if (m.find()) {
				if (!caseSensitive)
					line = line.toLowerCase();
				grp = new GroupText();
				grp.setSection(m.group(1));
				grp.setSectionRaw(line);
				if (grp.getSection().equalsIgnoreCase("data")
						|| grp.getSection().equalsIgnoreCase("size")
						|| grp.getSection().equalsIgnoreCase("velocity")
						|| grp.getSection().equalsIgnoreCase("movement"))
					cng = getConstgtCategory(result);
				cng.add(grp);
					
			} else {
				String[] keyValue = getSeparateKeyValue(line, true);
				grp.appendText(line);
				grp.getOriginalKeysOrdered().add(keyValue[0]);
				keyValue[0] = keyCaseSensistive? keyValue[0]: 
					caseSensitive? keyValue[0]: keyValue[0].toLowerCase();
				keyValue[1] = caseSensitive? keyValue[1]: keyValue[1].toLowerCase();
				grp.getKeysOrdered().add(keyValue[0]);
				grp.getKeyValues().put(keyValue[0], keyValue[1]);
				
			}
		}
		for (Iterator<GrpTxtCategory> iter = result.iterator(); iter.hasNext();) {
			GrpTxtCategory c = iter.next();
			if (c.getList().isEmpty()) {
				iter.remove();
			}
			
		}
        return result;
    }

	private GrpTxtCategory getConstgtCategory(List<GrpTxtCategory> cmdData) {
		for (GrpTxtCategory c: cmdData) {
			if (c.category.equalsIgnoreCase("CONST"))
				return c;
		}
		return null;
	}

}
