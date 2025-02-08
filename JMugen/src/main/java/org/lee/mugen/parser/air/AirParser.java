package org.lee.mugen.parser.air;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.lee.mugen.lang.Wrapper;
import org.lee.mugen.object.Rectangle;
import org.lee.mugen.parser.air.AirData.TypeBlit;
import org.lee.mugen.sprite.parser.Parser;
import org.lee.mugen.sprite.parser.Parser.GroupText;


public class AirParser {
    private HashMap<Integer, AirGroup> airGroupMap = new HashMap<Integer, AirGroup>();

    public static final String _COMMENT_OR_EMPTY_REGEX = "^;.*$|^ *$";
    private static final String _FLOAT_REGEX = "((?:\\+|-)?(?:(?:\\.\\d+)|(?:\\d+(?:\\.\\d*)?)))";//"[+-]*(?:\\d+\\.\\d+)|(?:\\d+)";

	//
	private static final String _END = "(?:(?: *;.*$)|(?: *$))";
    
	private static final String _FOUR_NUMBER_COMMA_REGEX = _FLOAT_REGEX + " *, *" + _FLOAT_REGEX + " *, *" + _FLOAT_REGEX + " *, *" + _FLOAT_REGEX;
    public static final String _GRP_ACTION_REGEX = " *\\[ *begin action *(\\d*) *\\]" + _END;
    private static final String _CLSN1DEFAULT_REGEX = " *clsn1default *: *(\\d+)" + _END;
    private static final String _CLSN2DEFAULT_REGEX = " *clsn2default *: *(\\d+)" + _END;
    private static final String _CLSN1_REGEX = " *clsn1 *: *(\\d+)" + _END;
    private static final String _CLSN2_REGEX = " *clsn2 *: *(\\d+)" + _END;
    
//    private static final String _CLSN1DEFAULT_REGEX = " *clsn1default *: *(\\d+)" + _END;
//    private static final String _CLSN2DEFAULT_REGEX = " *clsn2default *: *(\\d+)" + _END;
//    private static final String _CLSN1_REGEX = " *clsn1 *: *(\\d+)" + _END;
//    private static final String _CLSN2_REGEX = " *clsn2 *: *(\\d+)" + _END;
    
//    private static final String _CLSN1_RECT_REGEX = " *clsn1 *\\[ *(\\d+) *\\] *= *" + _FOUR_NUMBER_COMMA_REGEX  + _END;
//    private static final String _CLSN2_RECT_REGEX = " *clsn2 *\\[ *(\\d+) *\\] *= *" + _FOUR_NUMBER_COMMA_REGEX  + _END;

    // Error accept and recovery
    private static final String _CLSN1_RECT_REGEX = " *clsn[0-9].* *\\[ *(\\d+) *\\] *= *" + _FOUR_NUMBER_COMMA_REGEX  + ".*" + _END;
    private static final String _CLSN2_RECT_REGEX = " *clsn[0-9].* *\\[ *(\\d+) *\\] *= *" + _FOUR_NUMBER_COMMA_REGEX  + ".*" + _END;
    
//    private static final String _CLSN1_RECT_REGEX = " *clsn[1-2] *\\[ *(\\d+) *\\] *= *" + _FOUR_NUMBER_COMMA_REGEX  + _END;
//    private static final String _CLSN2_RECT_REGEX = " *clsn[1-2] *\\[ *(\\d+) *\\] *= *" + _FOUR_NUMBER_COMMA_REGEX  + _END;

    public static final String _LOOP_START_REGEX = " *(loopstart)" + _END;
    private static final String _AIR_DATA_REGEX = 
    	_FLOAT_REGEX + " *, *" +  _FOUR_NUMBER_COMMA_REGEX + "(?: *, *((?:h?v?)|(?:v?h?)))?(?: *, *(\\w*)?)?" + ",*" + ".*";//_END;
    
    
    public static final Pattern _AIR_DATA_PATTERN = Pattern.compile(_AIR_DATA_REGEX, Pattern.CASE_INSENSITIVE);


    
	private static final Pattern grpActionPattern = Pattern.compile(_GRP_ACTION_REGEX, Pattern.CASE_INSENSITIVE);

	
	private static final Pattern P_COMMENT_OR_EMPTY_REGEX = Pattern.compile(_COMMENT_OR_EMPTY_REGEX, Pattern.CASE_INSENSITIVE);
	private static final Pattern P_CLSN1DEFAULT_REGEX = Pattern.compile(_CLSN1DEFAULT_REGEX, Pattern.CASE_INSENSITIVE);
	private static final Pattern P_CLSN2DEFAULT_REGEX = Pattern.compile(_CLSN2DEFAULT_REGEX, Pattern.CASE_INSENSITIVE);
	private static final Pattern P_CLSN1_REGEX = Pattern.compile(_CLSN1_REGEX, Pattern.CASE_INSENSITIVE);
	private static final Pattern P_CLSN2_REGEX = Pattern.compile(_CLSN2_REGEX, Pattern.CASE_INSENSITIVE);
	private static final Pattern P_CLSN1_RECT_REGEX = Pattern.compile(_CLSN1_RECT_REGEX, Pattern.CASE_INSENSITIVE);
	private static final Pattern P_CLSN2_RECT_REGEX = Pattern.compile(_CLSN2_RECT_REGEX, Pattern.CASE_INSENSITIVE);
	private static final Pattern P_LOOP_START_REGEX = Pattern.compile(_LOOP_START_REGEX, Pattern.CASE_INSENSITIVE);

	/*


	 */
	
	private static boolean isMatch(Pattern reg, String input) {
		return reg.matcher(input).matches();
	}
	
    private String getLine(StringTokenizer br) throws IOException {
        String line = null;
        if (!br.hasMoreTokens())
        	return null;
        line = br.nextToken();
		line = line == null? null: line.toLowerCase();
		return line;
    }
    public AirParser() {
	}
    public AirParser(String sFilename) throws IOException {
    	this(Parser.getGroupText(Parser.getText(new FileInputStream(sFilename))));
    }
    public AirParser(String[] groups) throws IOException {
    	
        for (String grp: groups) {
        	parseGroup(grp);
        }
    }
    
    public boolean parseGroup(GroupText grp) throws IOException {
    	return parseGroup("[" + grp.getSection() + "]" + "\n" + grp.getText().toString());
    }
    
	public boolean parseGroup(String grp) throws IOException {
    	StringTokenizer strToken = new StringTokenizer(grp, "\r\n");
    	
    	String line = strToken.nextToken();

    	AirGroup airGrp = new AirGroup();
    	Matcher matcher = grpActionPattern.matcher(line);
    	if (!matcher.find()) {
    		return false;
    	}

    	String actionId = matcher.group(1);
    	airGrp.action = Integer.parseInt(actionId);
    	if (airGroupMap.containsKey(airGrp.action))
    		return false;
    	airGroupMap.put(airGrp.action, airGrp);
    	//
    	Wrapper<Rectangle[]> clsn1defaultInOut = new Wrapper<Rectangle[]>();
    	clsn1defaultInOut.setValue(new Rectangle[0]);
        Wrapper<Rectangle[]> clsn2defaultInOut = new Wrapper<Rectangle[]>();
        clsn2defaultInOut.setValue(new Rectangle[0]);
        

        Wrapper<Rectangle[]> clsn1InOut = new Wrapper<Rectangle[]>();
        
        Wrapper<Rectangle[]> clsn2InOut = new Wrapper<Rectangle[]>();
        
    	
    	do {
    		line = getLine(strToken);
    		if (line == null)
    			break;
    		
            if (isMatch(P_COMMENT_OR_EMPTY_REGEX, line)) {
            	
            } else if (isMatch(P_CLSN1DEFAULT_REGEX, line)) {
            	clsn1InOut.setValue(new Rectangle[0]);
                parseClsn(line, strToken, clsn1defaultInOut, P_CLSN1DEFAULT_REGEX, P_CLSN1_RECT_REGEX);
            } else if (isMatch(P_CLSN2DEFAULT_REGEX, line)) {
            	clsn2InOut.setValue(new Rectangle[0]);
                parseClsn(line, strToken, clsn2defaultInOut, P_CLSN2DEFAULT_REGEX, P_CLSN2_RECT_REGEX);
            } else if (isMatch(P_CLSN1_REGEX, line)) {
            	clsn1InOut.setValue(new Rectangle[0]);
                parseClsn(line, strToken, clsn1InOut, P_CLSN1_REGEX, P_CLSN1_RECT_REGEX);
            } else if (isMatch(P_CLSN2_REGEX, line)) {
            	clsn2InOut.setValue(new Rectangle[0]);
                parseClsn(line, strToken, clsn2InOut, P_CLSN2_REGEX, P_CLSN2_RECT_REGEX);
            } else if (isMatch(P_CLSN1_RECT_REGEX, line)) {
                parseClsn(line, strToken, clsn1InOut, P_CLSN1_REGEX, P_CLSN1_RECT_REGEX);
            } else if (isMatch(P_CLSN2_RECT_REGEX, line)) {
                parseClsn(line, strToken, clsn2InOut, P_CLSN2_REGEX, P_CLSN2_RECT_REGEX);
            } else if (isMatch(P_LOOP_START_REGEX, line)) {
            	parseLoopStart(line, airGrp);
    		} else if (_AIR_DATA_PATTERN.matcher(line).find()) {
    			parseAirData(
    						line, 
    						airGrp, 
    						isArrayEmpty(clsn1InOut.getValue())? clsn1defaultInOut: clsn1InOut, 
    						isArrayEmpty(clsn2InOut.getValue())? clsn2defaultInOut: clsn2InOut);
    			clsn1InOut.setValue(new Rectangle[0]);
    			clsn2InOut.setValue(new Rectangle[0]);
    		} else {
            	break;
            }
            
        } while (strToken.hasMoreElements());
        
    	return true;
		
	}

	private boolean isArrayEmpty(Object[] array) {
		return array == null || array.length == 0;
	}
    private void parseClsn(
    		String line, 
    		StringTokenizer strToken, 
    		Wrapper<Rectangle[]> clsnWrap, 
    		Pattern clsnHeadPattern,
    		Pattern clsnRectPattern) throws IOException {

        line = line.toLowerCase().trim();
    	Matcher matcher = clsnHeadPattern.matcher(line);
    	if (line == null || !matcher.find()) {
    		return;
    	}
    	
    	int rectCount = Integer.parseInt(matcher.group(1));
    	Rectangle[] clsn = new Rectangle[rectCount];
        
        for (int i = 0; i < clsn.length; i++) {
        	line = getLine(strToken);
        	line = line.toLowerCase().trim();
        	matcher = clsnRectPattern.matcher(line);
            if (matcher.find()) {
            	
            } else {
            	
            }
            try {
            	int index = Integer.parseInt(matcher.group(1));
	
			} catch (Exception e) {
				e.printStackTrace();
			}
            
            int x1 = Integer.parseInt(matcher.group(2));
            int y1 = Integer.parseInt(matcher.group(3));
            int x2 = Integer.parseInt(matcher.group(4));
            int y2 = Integer.parseInt(matcher.group(5));
            
            int xTopLeft = Math.min(x1, x2);
            int yTopLeft = Math.min(y1, y2);
            int xTopRight = Math.max(x1, x2);
            int yTopRight = Math.max(y1, y2);
            int width = Math.abs(xTopLeft) + Math.abs(xTopRight);
            int height = Math.abs(yTopLeft - yTopRight);
			
            java.awt.Rectangle jr = new java.awt.Rectangle(xTopLeft, yTopLeft, width, height);
            clsn[i/*index*/] = new Rectangle(x1, y1, x2, y2);
//            clsn[i/*index*/] = jr;
        }
        clsnWrap.setValue(clsn);
    }
    public static boolean parseLoopStart(String line, AirGroup aGrp) {
    	line = getFormatedLine(line);
        if (!isMatch(P_LOOP_START_REGEX, line))
            return false;
        aGrp.loopStart = aGrp.airDataList.size();
        return true;
    }
	
	
    
    private static final Pattern P_A = Pattern.compile(".*a$", Pattern.CASE_INSENSITIVE);
    private static final Pattern P_AS09d09 = Pattern.compile(".*as([0-9]+)d([0-9]+).*", Pattern.CASE_INSENSITIVE);
    private static final Pattern P_A09 = Pattern.compile(".*a([0-9]+).*", Pattern.CASE_INSENSITIVE);
    private static final Pattern P_S09 = Pattern.compile(".*s([0-9]+).*", Pattern.CASE_INSENSITIVE);
    private static final Pattern P_S = Pattern.compile(".*s$", Pattern.CASE_INSENSITIVE);
    
    public static boolean parseAirData(String line, AirGroup aGrp, Wrapper<Rectangle[]> clsn1, Wrapper<Rectangle[]> clsn2) {
        line = getFormatedLine(line).replace(" ", "");
        if (line == null || !_AIR_DATA_PATTERN.matcher(line).find())
            return false;
        Matcher matcher = _AIR_DATA_PATTERN.matcher(line);
        matcher.find();
        

        AirData airData = new AirData();
        airData.grpNum = Integer.parseInt(matcher.group(1));
        airData.imgNum = Integer.parseInt(matcher.group(2));
        airData.xOffSet = Integer.parseInt(matcher.group(3));
        airData.yOffSet = Integer.parseInt(matcher.group(4));
        airData.delayTick = Integer.parseInt(matcher.group(5));
        
        String mirror;
        if (matcher.group(6) != null) {
            mirror = matcher.group(6);
            airData.isMirrorH = mirror.indexOf("h") != -1;
            airData.isMirrorV = mirror.indexOf("v") != -1;
        } 
//        if (matcher.group(7) != null)
//            airData.alpha = Integer.parseInt(matcher.group(7)); // TODO ALPHA IN AIR PARSER strToken.nextToken();
        if (clsn1 != null)
        	airData.clsn1 = (Rectangle[])clsn1.getValue().clone();
        if (clsn2 != null)
        	airData.clsn2 = (Rectangle[])clsn2.getValue().clone();
        aGrp.airDataList.add(airData);
        
        Matcher adPattern = P_A.matcher(line);
        Matcher asdPattern = P_AS09d09.matcher(line);
        Matcher aPattern = P_A09.matcher(line);
        Matcher sPattern = P_S09.matcher(line);
        Matcher sdPattern = P_S.matcher(line);
        
//        sPattern.find();
        if (adPattern.find() || sdPattern.find()) {
        	airData.type = TypeBlit.ASD;
        	airData.addcolor = 256;
        	airData.subcolor = 256;
        	airData.destcolor = 256;
        } else if (asdPattern.find()) {
        	airData.type = TypeBlit.ASD;
        	airData.addcolor = Integer.parseInt(asdPattern.group(1));
        	airData.subcolor = Integer.parseInt(asdPattern.group(1));
        	airData.destcolor = Integer.parseInt(asdPattern.group(2));
        	
        } else if (aPattern.find()) {
        	airData.type = TypeBlit.A;
        	airData.addcolor = Integer.parseInt(aPattern.group(1)) * 256;
        	airData.subcolor = Integer.parseInt(aPattern.group(1)) * 256;
        	airData.destcolor = 128;
        } else if (sPattern.find()) {
        	airData.type = TypeBlit.S;
        	airData.subcolor = Integer.parseInt(sPattern.group(1));
        }
        return true;
    }

	private static String getFormatedLine(String line) {
		return line == null? null: line.toLowerCase();
	}
	public Map<Integer, AirGroup> getAirGroupMap() {
		return airGroupMap;
	}


}