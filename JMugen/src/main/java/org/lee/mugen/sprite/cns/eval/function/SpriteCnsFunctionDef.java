package org.lee.mugen.sprite.cns.eval.function;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns.*;


public class SpriteCnsFunctionDef {
	public static SpriteCnsTriggerFunction getSpriteCnsFunc(String name) {
		SpriteCnsTriggerFunction scf = (SpriteCnsTriggerFunction) spriteCnsFunctionMap.get(name);


		if (Projhit.PROJ_HIT_SPEC_REG.matcher(name).find()) {
			return spriteCnsFunctionMap.get("projhit");
		} else if (Projhittime.PROJ_HIT_TIME_SPEC_REG.matcher(name).find()) {
			return spriteCnsFunctionMap.get("projhittime");
		} else if (Projcontact.PROJ_CONTACT_SPEC_REG.matcher(name).find()) {
			return spriteCnsFunctionMap.get("projcontact");
		} else if (Projcontacttime.PROJ_CONTACT_TIME_SPEC_REG.matcher(name).find()) {
			return spriteCnsFunctionMap.get("projcontacttime");
		} else if (Projguarded.PROJ_GUARDED_SPEC_REG.matcher(name).find()) {
			return spriteCnsFunctionMap.get("projguarded");
		} else if (Projguardedtime.PROJ_GUARDED_TIME_SPEC_REG.matcher(name).find()) {
			return spriteCnsFunctionMap.get("projguardedtime");
		} else if (Projcanceltime.PROJ_CANCEL_TIME_SPEC_REG.matcher(name).find()) {
			return spriteCnsFunctionMap.get("projcanceltime");
		}

		if (scf == null) {
			throw new IllegalStateException();
		}

		return scf;
	}

	private static final Map<String, SpriteCnsTriggerFunction> spriteCnsFunctionMap = buildSpriteCnsFunction();
	private static Map<String, SpriteCnsTriggerFunction> buildSpriteCnsFunction() {
		Map<String, SpriteCnsTriggerFunction> spriteCnsFunctionMap = new HashMap<String, SpriteCnsTriggerFunction>();

		// 
		spriteCnsFunctionMap.put("animtime", new Animtime());
		spriteCnsFunctionMap.put("animelemtime", new Animelemtime());
		
		spriteCnsFunctionMap.put("anim", new Anim());
		spriteCnsFunctionMap.put("alive", new Alive());
		spriteCnsFunctionMap.put("animexist", new Animexist());
		spriteCnsFunctionMap.put("animelem", new Animelem());
		spriteCnsFunctionMap.put("command", new Command());
		spriteCnsFunctionMap.put("selfanimexist", new Selfanimexist());
		spriteCnsFunctionMap.put("gametime", new Gametime());
		spriteCnsFunctionMap.put("gethitvar", new Gethitvar());
		
        spriteCnsFunctionMap.put("pos", new Pos());
		spriteCnsFunctionMap.put("vel", new Vel());
		spriteCnsFunctionMap.put("p2bodydist", new P2bodydist());
		
		
		spriteCnsFunctionMap.put("ctrl", new Ctrl());
		spriteCnsFunctionMap.put("life", new Life());
		
//		spriteCnsFunctionMap.put("enemy", new Enemy());
		spriteCnsFunctionMap.put("animelemno", new Animelemno());
		spriteCnsFunctionMap.put("authorname", new Authorname());
		spriteCnsFunctionMap.put("backedgebodydist", new Backedgebodydist());
		spriteCnsFunctionMap.put("backedgedist", new Backedgedist());
		spriteCnsFunctionMap.put("canrecover", new Canrecover());
		spriteCnsFunctionMap.put("drawgame", new Drawgame());
		spriteCnsFunctionMap.put("facing", new Facing());
		spriteCnsFunctionMap.put("frontedgebodydist", new Frontedgebodydist());
		spriteCnsFunctionMap.put("frontedgedist", new Frontedgedist());
		spriteCnsFunctionMap.put("hitcount", new Hitcount());
		spriteCnsFunctionMap.put("hitdefattr", new Hitdefattr());
		spriteCnsFunctionMap.put("hitfall", new Hitfall());
		spriteCnsFunctionMap.put("hitover", new Hitover());
		spriteCnsFunctionMap.put("hitpausetime", new Hitpausetime());
		spriteCnsFunctionMap.put("hitshakeover", new Hitshakeover());
		spriteCnsFunctionMap.put("hitvel", new Hitvel());
		spriteCnsFunctionMap.put("id", new Id());
		spriteCnsFunctionMap.put("inguarddist", new Inguarddist());
		spriteCnsFunctionMap.put("ishelper", new Ishelper());
		spriteCnsFunctionMap.put("ishometeam", new Ishometeam());
		spriteCnsFunctionMap.put("lose", new Lose());
		spriteCnsFunctionMap.put("loseko", new Loseko());
		spriteCnsFunctionMap.put("matchno", new Matchno());
		spriteCnsFunctionMap.put("matchover", new Matchover());
		spriteCnsFunctionMap.put("movecontact", new Movecontact());
		spriteCnsFunctionMap.put("moveguarded", new Moveguarded());
		spriteCnsFunctionMap.put("movehit", new Movehit());
		spriteCnsFunctionMap.put("movetype", new Movetype());
		spriteCnsFunctionMap.put("physics", new Physics());
		spriteCnsFunctionMap.put("movereversed", new Movereversed());
		spriteCnsFunctionMap.put("name", new Name());
		spriteCnsFunctionMap.put("numenemy", new Numenemy());
		spriteCnsFunctionMap.put("numexplod", new Numexplod());
		spriteCnsFunctionMap.put("numhelper", new Numhelper());
		spriteCnsFunctionMap.put("numpartner", new Numpartner());
		spriteCnsFunctionMap.put("numproj", new Numproj());
		spriteCnsFunctionMap.put("numprojid", new Numprojid());
		spriteCnsFunctionMap.put("numtarget", new Numtarget());
		spriteCnsFunctionMap.put("p1name", new P1name());
		spriteCnsFunctionMap.put("random", new Random());
		
		
		
//		spriteCnsFunctionMap.put("p2bodydistx", new P2bodydist_x());
		spriteCnsFunctionMap.put("p2dist", new P2dist());
		spriteCnsFunctionMap.put("p2life", new P2life());
		spriteCnsFunctionMap.put("p2movetype", new P2movetype());
		spriteCnsFunctionMap.put("p2name", new P2name());
		spriteCnsFunctionMap.put("p2stateno", new P2stateno());
		spriteCnsFunctionMap.put("p2statetype", new P2statetype());
		spriteCnsFunctionMap.put("p3name", new P3name());
		spriteCnsFunctionMap.put("p4name", new P4name());
		spriteCnsFunctionMap.put("palno", new Palno());
		spriteCnsFunctionMap.put("parentdist", new Parentdist());
		spriteCnsFunctionMap.put("power", new Power());
		spriteCnsFunctionMap.put("powermax", new Powermax());
		spriteCnsFunctionMap.put("playeridexist", new Playeridexist());
		spriteCnsFunctionMap.put("prevstateno", new Prevstateno());
		spriteCnsFunctionMap.put("projcanceltime", new Projcanceltime());
		spriteCnsFunctionMap.put("projcontact", new Projcontact());
		spriteCnsFunctionMap.put("projcontacttime", new Projcontacttime());
		spriteCnsFunctionMap.put("projguarded", new Projguarded());
		spriteCnsFunctionMap.put("projguardedtime", new Projguardedtime());
		spriteCnsFunctionMap.put("projhit", new Projhit());
		spriteCnsFunctionMap.put("projhittime", new Projhittime());
		spriteCnsFunctionMap.put("rootdist", new Rootdist());
		spriteCnsFunctionMap.put("roundno", new Roundno());
		spriteCnsFunctionMap.put("roundsexisted", new Roundsexisted());
		spriteCnsFunctionMap.put("roundstate", new Roundstate());
		spriteCnsFunctionMap.put("screenpos", new Screenpos());
		spriteCnsFunctionMap.put("stateno", new Stateno());
		spriteCnsFunctionMap.put("statetype", new Statetype());
		spriteCnsFunctionMap.put("teammode", new Teammode());
		spriteCnsFunctionMap.put("teamside", new Teamside());
		spriteCnsFunctionMap.put("tickspersecond", new Tickspersecond());
		spriteCnsFunctionMap.put("winko", new Winko());
		
		spriteCnsFunctionMap.put("timemod", new Timemod());
		spriteCnsFunctionMap.put("uniqhitcount", new Uniqhitcount());
		spriteCnsFunctionMap.put("win", new Win());
		
		spriteCnsFunctionMap.put("sysvar", new Sysvar());
		spriteCnsFunctionMap.put("sysfvar", new Sysfvar());
		spriteCnsFunctionMap.put("var", new Var());
		spriteCnsFunctionMap.put("fvar", new Fvar());
		
//		spriteCnsFunctionMap.put("parent", new Parent());
//		spriteCnsFunctionMap.put("root", new Root());
//		spriteCnsFunctionMap.put("helper", new Helper());
//		spriteCnsFunctionMap.put("target", new Target());
//		spriteCnsFunctionMap.put("partner", new Partner());
//		spriteCnsFunctionMap.put("enemy", new Enemy());
//		spriteCnsFunctionMap.put("enemynear", new Enemynear());
//		spriteCnsFunctionMap.put("playerid", new Playerid());
		
		spriteCnsFunctionMap.put("const", new Const());
		
		spriteCnsFunctionMap.put("time", new Time());
		spriteCnsFunctionMap.put("statetime", new Statetime());

		
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
		
		strBuilder.append(Projhit.PROJ_HIT_SPEC_REG.pattern() + "|");
		strBuilder.append(Projhittime.PROJ_HIT_TIME_SPEC_REG.pattern() + "|");
		
		strBuilder.append(Projcontact.PROJ_CONTACT_SPEC_REG.pattern() + "|");
		strBuilder.append(Projcontacttime.PROJ_CONTACT_TIME_SPEC_REG.pattern() + "|");
		
		strBuilder.append(Projguarded.PROJ_GUARDED_SPEC_REG.pattern() + "|");
		strBuilder.append(Projguardedtime.PROJ_GUARDED_TIME_SPEC_REG.pattern() + "|");

		strBuilder.append(Projcanceltime.PROJ_CANCEL_TIME_SPEC_REG.pattern() + "|");

		for (Iterator<SpriteCnsTriggerFunction> iter = SpriteCnsFunctionDef.spriteCnsFunctionMap.values().iterator(); iter.hasNext();) {
			SpriteCnsTriggerFunction value = iter.next();
			strBuilder.append(value.getRegex() + (iter.hasNext()? "|": ""));
		}

		
		return strBuilder.toString();
	}
}
