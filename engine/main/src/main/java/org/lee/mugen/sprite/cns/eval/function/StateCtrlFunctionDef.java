package org.lee.mugen.sprite.cns.eval.function;

import java.util.HashMap;
import java.util.Map;

import org.lee.mugen.sprite.cns.type.function.Afterimage;
import org.lee.mugen.sprite.cns.type.function.Afterimagetime;
import org.lee.mugen.sprite.cns.type.function.Allpalfx;
import org.lee.mugen.sprite.cns.type.function.Angleadd;
import org.lee.mugen.sprite.cns.type.function.Angledraw;
import org.lee.mugen.sprite.cns.type.function.Anglemul;
import org.lee.mugen.sprite.cns.type.function.Angleset;
import org.lee.mugen.sprite.cns.type.function.Appendtoclipboard;
import org.lee.mugen.sprite.cns.type.function.Assertspecial;
import org.lee.mugen.sprite.cns.type.function.Attackdist;
import org.lee.mugen.sprite.cns.type.function.Attackmulset;
import org.lee.mugen.sprite.cns.type.function.Bgpalfx;
import org.lee.mugen.sprite.cns.type.function.Bindtoparent;
import org.lee.mugen.sprite.cns.type.function.Bindtoroot;
import org.lee.mugen.sprite.cns.type.function.Bindtotarget;
import org.lee.mugen.sprite.cns.type.function.Changeanim;
import org.lee.mugen.sprite.cns.type.function.Changeanim2;
import org.lee.mugen.sprite.cns.type.function.Changepal;
import org.lee.mugen.sprite.cns.type.function.Changestate;
import org.lee.mugen.sprite.cns.type.function.Clearclipboard;
import org.lee.mugen.sprite.cns.type.function.Ctrlset;
import org.lee.mugen.sprite.cns.type.function.Defencemulset;
import org.lee.mugen.sprite.cns.type.function.Destroyself;
import org.lee.mugen.sprite.cns.type.function.DisplayToScreen;
import org.lee.mugen.sprite.cns.type.function.Displaytoclipboard;
import org.lee.mugen.sprite.cns.type.function.Envcolor;
import org.lee.mugen.sprite.cns.type.function.Envshake;
import org.lee.mugen.sprite.cns.type.function.Explod;
import org.lee.mugen.sprite.cns.type.function.Explodbindtime;
import org.lee.mugen.sprite.cns.type.function.Fallenvshake;
import org.lee.mugen.sprite.cns.type.function.Forcefeedback;
import org.lee.mugen.sprite.cns.type.function.Gamemakeanim;
import org.lee.mugen.sprite.cns.type.function.Gravity;
import org.lee.mugen.sprite.cns.type.function.Helper;
import org.lee.mugen.sprite.cns.type.function.Hitadd;
import org.lee.mugen.sprite.cns.type.function.Hitby;
import org.lee.mugen.sprite.cns.type.function.Hitdef;
import org.lee.mugen.sprite.cns.type.function.Hitfalldamage;
import org.lee.mugen.sprite.cns.type.function.Hitfallset;
import org.lee.mugen.sprite.cns.type.function.Hitfallvel;
import org.lee.mugen.sprite.cns.type.function.Hitoverride;
import org.lee.mugen.sprite.cns.type.function.Hitvelset;
import org.lee.mugen.sprite.cns.type.function.Lifeadd;
import org.lee.mugen.sprite.cns.type.function.Lifeset;
import org.lee.mugen.sprite.cns.type.function.Makedust;
import org.lee.mugen.sprite.cns.type.function.Modifyexplod;
import org.lee.mugen.sprite.cns.type.function.Movehitreset;
import org.lee.mugen.sprite.cns.type.function.Nothitby;
import org.lee.mugen.sprite.cns.type.function.Null;
import org.lee.mugen.sprite.cns.type.function.Palfx;
import org.lee.mugen.sprite.cns.type.function.Parentvaradd;
import org.lee.mugen.sprite.cns.type.function.Parentvarset;
import org.lee.mugen.sprite.cns.type.function.Pause;
import org.lee.mugen.sprite.cns.type.function.Playerpush;
import org.lee.mugen.sprite.cns.type.function.Playsnd;
import org.lee.mugen.sprite.cns.type.function.Posadd;
import org.lee.mugen.sprite.cns.type.function.Posfreeze;
import org.lee.mugen.sprite.cns.type.function.Posset;
import org.lee.mugen.sprite.cns.type.function.Poweradd;
import org.lee.mugen.sprite.cns.type.function.Powerset;
import org.lee.mugen.sprite.cns.type.function.Print;
import org.lee.mugen.sprite.cns.type.function.Projectile;
import org.lee.mugen.sprite.cns.type.function.Removeexplod;
import org.lee.mugen.sprite.cns.type.function.Reversaldef;
import org.lee.mugen.sprite.cns.type.function.Screenbound;
import org.lee.mugen.sprite.cns.type.function.Selfstate;
import org.lee.mugen.sprite.cns.type.function.Sndpan;
import org.lee.mugen.sprite.cns.type.function.Spritebeanset;
import org.lee.mugen.sprite.cns.type.function.Sprpriority;
import org.lee.mugen.sprite.cns.type.function.Statetypeset;
import org.lee.mugen.sprite.cns.type.function.Stopsnd;
import org.lee.mugen.sprite.cns.type.function.Superpause;
import org.lee.mugen.sprite.cns.type.function.Targetbind;
import org.lee.mugen.sprite.cns.type.function.Targetdrop;
import org.lee.mugen.sprite.cns.type.function.Targetfacing;
import org.lee.mugen.sprite.cns.type.function.Targetlifeadd;
import org.lee.mugen.sprite.cns.type.function.Targetpoweradd;
import org.lee.mugen.sprite.cns.type.function.Targetstate;
import org.lee.mugen.sprite.cns.type.function.Targetveladd;
import org.lee.mugen.sprite.cns.type.function.Targetvelset;
import org.lee.mugen.sprite.cns.type.function.Trans;
import org.lee.mugen.sprite.cns.type.function.Turn;
import org.lee.mugen.sprite.cns.type.function.Varadd;
import org.lee.mugen.sprite.cns.type.function.Varrandom;
import org.lee.mugen.sprite.cns.type.function.Varrangeset;
import org.lee.mugen.sprite.cns.type.function.Varset;
import org.lee.mugen.sprite.cns.type.function.Veladd;
import org.lee.mugen.sprite.cns.type.function.Velmul;
import org.lee.mugen.sprite.cns.type.function.Velset;
import org.lee.mugen.sprite.cns.type.function.Width;


public class StateCtrlFunctionDef {

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
		

		stateCtrlFunctionMap.put("afterimage", new Afterimage());
		stateCtrlFunctionMap.put("afterimagetime", new Afterimagetime());
		stateCtrlFunctionMap.put("forcefeedback", new Forcefeedback());
		stateCtrlFunctionMap.put("gamemakeanim", new Gamemakeanim());
		stateCtrlFunctionMap.put("hitby", new Hitby());
		stateCtrlFunctionMap.put("hitdef", new Hitdef());
		stateCtrlFunctionMap.put("hitfalldamage", new Hitfalldamage());
		stateCtrlFunctionMap.put("hitfallset", new Hitfallset());
		stateCtrlFunctionMap.put("hitfallvel", new Hitfallvel());
		stateCtrlFunctionMap.put("hitvelset", new Hitvelset());
		stateCtrlFunctionMap.put("makedust", new Makedust());
		stateCtrlFunctionMap.put("palfx", new Palfx());
		stateCtrlFunctionMap.put("nothitby", new Nothitby());
		stateCtrlFunctionMap.put("playsnd", new Playsnd());
		stateCtrlFunctionMap.put("posfreeze", new Posfreeze());
		stateCtrlFunctionMap.put("projectile", new Projectile());
		stateCtrlFunctionMap.put("selfstate", new Selfstate());
		stateCtrlFunctionMap.put("statetypeset", new Statetypeset());
		stateCtrlFunctionMap.put("turn", new Turn());
		stateCtrlFunctionMap.put("assertspecial", new Assertspecial());
		
		stateCtrlFunctionMap.put("varadd", new Varadd());
		stateCtrlFunctionMap.put("varrandom", new Varrandom());
		stateCtrlFunctionMap.put("varrangeset", new Varrangeset());
		stateCtrlFunctionMap.put("varset", new Varset());
		stateCtrlFunctionMap.put("veladd", new Veladd());
		stateCtrlFunctionMap.put("velmul", new Velmul());
		stateCtrlFunctionMap.put("velset", new Velset());
		stateCtrlFunctionMap.put("null", new Null());
		stateCtrlFunctionMap.put("print", new Print());
		
		stateCtrlFunctionMap.put("fallenvshake", new Fallenvshake());
		stateCtrlFunctionMap.put("gravity", new Gravity());
		stateCtrlFunctionMap.put("helper", new Helper());
		stateCtrlFunctionMap.put("hitadd", new Hitadd());
		stateCtrlFunctionMap.put("hitoverride", new Hitoverride());
		stateCtrlFunctionMap.put("modifyexplod", new Modifyexplod());
		stateCtrlFunctionMap.put("movehitreset", new Movehitreset());
		stateCtrlFunctionMap.put("parentvaradd", new Parentvaradd());
		stateCtrlFunctionMap.put("parentvarset", new Parentvarset());
		stateCtrlFunctionMap.put("pause", new Pause());
		stateCtrlFunctionMap.put("playerpush", new Playerpush());
		stateCtrlFunctionMap.put("removeexplod", new Removeexplod());
		stateCtrlFunctionMap.put("reversaldef", new Reversaldef());
		stateCtrlFunctionMap.put("screenbound", new Screenbound());
		stateCtrlFunctionMap.put("sndpan", new Sndpan());
		stateCtrlFunctionMap.put("stopsnd", new Stopsnd());
		stateCtrlFunctionMap.put("superpause", new Superpause());
		stateCtrlFunctionMap.put("targetbind", new Targetbind());
		stateCtrlFunctionMap.put("targetdrop", new Targetdrop());
		stateCtrlFunctionMap.put("targetfacing", new Targetfacing());
		stateCtrlFunctionMap.put("targetlifeadd", new Targetlifeadd());
		stateCtrlFunctionMap.put("targetpoweradd", new Targetpoweradd());
		stateCtrlFunctionMap.put("targetstate", new Targetstate());
		stateCtrlFunctionMap.put("targetveladd", new Targetveladd());
		stateCtrlFunctionMap.put("targetvelset", new Targetvelset());
		stateCtrlFunctionMap.put("trans", new Trans());
		stateCtrlFunctionMap.put("width", new Width());
		stateCtrlFunctionMap.put("allpalfx", new Allpalfx());
		stateCtrlFunctionMap.put("angleadd", new Angleadd());
		stateCtrlFunctionMap.put("angledraw", new Angledraw());
		stateCtrlFunctionMap.put("anglemul", new Anglemul());
		stateCtrlFunctionMap.put("angleset", new Angleset());
		stateCtrlFunctionMap.put("appendtoclipboard", new Appendtoclipboard());
		stateCtrlFunctionMap.put("attackdist", new Attackdist());
		stateCtrlFunctionMap.put("attackmulset", new Attackmulset());
		stateCtrlFunctionMap.put("bgpalfx", new Bgpalfx());
		stateCtrlFunctionMap.put("bindtoparent", new Bindtoparent());
		stateCtrlFunctionMap.put("bindtoroot", new Bindtoroot());
		stateCtrlFunctionMap.put("bindtotarget", new Bindtotarget());
		stateCtrlFunctionMap.put("changeanim2", new Changeanim2());
		stateCtrlFunctionMap.put("clearclipboard", new Clearclipboard());
		stateCtrlFunctionMap.put("destroyself", new Destroyself());
		stateCtrlFunctionMap.put("displaytoclipboard", new Displaytoclipboard());
		stateCtrlFunctionMap.put("envcolor", new Envcolor());
		stateCtrlFunctionMap.put("explod", new Explod());
		stateCtrlFunctionMap.put("explodbindtime", new Explodbindtime());	
		 
		stateCtrlFunctionMap.put("envshake", new Envshake());

		stateCtrlFunctionMap.put("poweradd", new Poweradd());
		stateCtrlFunctionMap.put("changeanim", new Changeanim());
		stateCtrlFunctionMap.put("changepal", new Changepal());
		stateCtrlFunctionMap.put("changestate", new Changestate());
		stateCtrlFunctionMap.put("ctrlset", new Ctrlset());
		stateCtrlFunctionMap.put("defencemulset", new Defencemulset());
		stateCtrlFunctionMap.put("lifeadd", new Lifeadd());
		stateCtrlFunctionMap.put("lifeset", new Lifeset());
		stateCtrlFunctionMap.put("posset", new Posset());
		stateCtrlFunctionMap.put("powerset", new Powerset());
		stateCtrlFunctionMap.put("sprpriority", new Sprpriority());
		stateCtrlFunctionMap.put("posadd", new Posadd());
		

		// A moi
		
		stateCtrlFunctionMap.put("displaytoscreen", new DisplayToScreen());
		stateCtrlFunctionMap.put("spritebeanset", new Spritebeanset());
		return stateCtrlFunctionMap;
	}

}
