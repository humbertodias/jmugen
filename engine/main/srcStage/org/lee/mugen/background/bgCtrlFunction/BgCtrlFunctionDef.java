package org.lee.mugen.background.bgCtrlFunction;

import java.util.HashMap;
import java.util.Map;

import org.lee.mugen.sprite.cns.eval.function.StateCtrlFunction;


public class BgCtrlFunctionDef {

	public static StateCtrlFunction getStateCtrlFunc(String name) {
		StateCtrlFunction scf = (StateCtrlFunction) stateCtrlFunctionMap.get(name);
		if (scf != null)
			scf = (StateCtrlFunction) scf.copy();
		return scf;
	}

	public static final Map<String, StateCtrlFunction> stateCtrlFunctionMap = buildStateCtrlFunction();
	private static Map<String, StateCtrlFunction> buildStateCtrlFunction() {
		Map<String, StateCtrlFunction> stateCtrlFunctionMap = new HashMap<String, StateCtrlFunction>();
		
		

		// 
		stateCtrlFunctionMap.put("posset", new Posset());
		stateCtrlFunctionMap.put("velset", new Velset());
		stateCtrlFunctionMap.put("enable", new Enable());
		stateCtrlFunctionMap.put("visible", new Visible());
		stateCtrlFunctionMap.put("siny", new Siny());
		stateCtrlFunctionMap.put("sin.y", new Siny());

//		stateCtrlFunctionMap.put("assertspecial", new Assertspecial());
//		
//
//		stateCtrlFunctionMap.put("null", new Null());
//		stateCtrlFunctionMap.put("print", new Print());
//		
//
////		stateCtrlFunctionMap.put("sndpan", new Sndpan());
////		stateCtrlFunctionMap.put("stopsnd", new Stopsnd());
//
//		stateCtrlFunctionMap.put("displaytoclipboard", new Displaytoclipboard());
//		stateCtrlFunctionMap.put("envcolor", new Envcolor());
////		stateCtrlFunctionMap.put("explod", new Explod());
////		stateCtrlFunctionMap.put("explodbindtime", new Explodbindtime());	
//		 
//		stateCtrlFunctionMap.put("envshake", new Envshake());
//
//		
//
//		// A moi
//		
//		stateCtrlFunctionMap.put("displaytoscreen", new DisplayToScreen());
		

		
		return stateCtrlFunctionMap;
	}




}
