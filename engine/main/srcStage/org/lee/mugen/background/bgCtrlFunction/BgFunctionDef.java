package org.lee.mugen.background.bgCtrlFunction;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.lee.mugen.sprite.cns.eval.function.SpriteCnsTriggerFunction;
import org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns.Beaninfo;
import org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns.CommandCount;
import org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns.Drawgame;
import org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns.Format;
import org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns.IsAssertSpecial;
import org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns.Roundno;
import org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns.Roundsexisted;
import org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns.Roundstate;
import org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns.Screenpos;
import org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns.Teammode;
import org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns.Tickspersecond;
import org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns.Winko;


public class BgFunctionDef {

	public static SpriteCnsTriggerFunction getSpriteCnsFunc(String name) {
		SpriteCnsTriggerFunction scf = (SpriteCnsTriggerFunction) spriteCnsFunctionMap.get(name);

		return scf;
	}

	private static final Map<String, SpriteCnsTriggerFunction> spriteCnsFunctionMap = buildSpriteCnsFunction();
	private static Map<String, SpriteCnsTriggerFunction> buildSpriteCnsFunction() {
		Map<String, SpriteCnsTriggerFunction> spriteCnsFunctionMap = new HashMap<String, SpriteCnsTriggerFunction>();

		// 
		
		spriteCnsFunctionMap.put("drawgame", new Drawgame());
		spriteCnsFunctionMap.put("roundno", new Roundno());
		spriteCnsFunctionMap.put("roundsexisted", new Roundsexisted());
		spriteCnsFunctionMap.put("roundstate", new Roundstate());
		spriteCnsFunctionMap.put("screenpos", new Screenpos());
		spriteCnsFunctionMap.put("teammode", new Teammode());
		spriteCnsFunctionMap.put("tickspersecond", new Tickspersecond());
		spriteCnsFunctionMap.put("winko", new Winko());
		
		spriteCnsFunctionMap.put("time", new Time());

		
		//
		// a Moi
		spriteCnsFunctionMap.put("beaninfo", new Beaninfo());
		spriteCnsFunctionMap.put("commandcount", new CommandCount());
		spriteCnsFunctionMap.put("format", new Format());
		spriteCnsFunctionMap.put("isassertspecial", new IsAssertSpecial());
		
		
		return spriteCnsFunctionMap;
	}

	public static String getCnsTriggerFunctionRegex() {
		StringBuilder strBuilder = new StringBuilder();
		
		for (Iterator<SpriteCnsTriggerFunction> iter = spriteCnsFunctionMap.values().iterator(); iter.hasNext();) {
			SpriteCnsTriggerFunction value = iter.next();
			strBuilder.append(value.getRegex() + (iter.hasNext()? "|": ""));
		}

		
		return strBuilder.toString();
	}
}
