package org.lee.mugen.sprite.cns.eval.redirect;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Functionable;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.character.SpriteHelper;
import org.lee.mugen.sprite.cns.eval.function.MathFunction;
import org.lee.mugen.sprite.parser.Parser;

public class SpriteRedirect {
	
	public static final String ROOT_GROUP_REGEX = "root";
	public static final String PARENT_GROUP_REGEX = "parent";
	public static final String ENEMY_GROUP_REGEX = "enemy";
	public static final String PARTNER_GROUP_REGEX = "partner";
	public static final String HELPER_WITH_ID_GROUP_REGEX = "helper";
	public static final String PLAYER_WITH_ID_GROUP_REGEX = "playerid";
	public static final String TARGET_WITH_ID_GROUP_REGEX = "target";
	public static final String ENEMYNEAR_WITH_ID_GROUP_REGEX = "enemynear";
	
	
	
	public static final String SPRITE_REDIRECT_REG = "\\b(" + ROOT_GROUP_REGEX + ")\\b" + "|" +
	"\\b(" + PARENT_GROUP_REGEX + ")\\b" + "|" +
	"\\b(" + PARTNER_GROUP_REGEX + ")\\b" + "|" +
	"\\b(" + ENEMYNEAR_WITH_ID_GROUP_REGEX + ")\\b" + "|" +
	"\\b(" + HELPER_WITH_ID_GROUP_REGEX + ")\\b" + "|" +
	"\\b(" + PLAYER_WITH_ID_GROUP_REGEX + ")\\b" + "|" +
	"\\b(" + TARGET_WITH_ID_GROUP_REGEX + ")\\b" + "|" +
	"\\b(" + ENEMY_GROUP_REGEX + ")\\b";
	
	public static Pattern P_SPRITE_REDIRECT_REG = Pattern.compile(SPRITE_REDIRECT_REG, Pattern.CASE_INSENSITIVE);
	
	private static Map<String, MathFunction> functionInfoMap = buildFunctionInfo();

	public static MathFunction getFunction(String func)  {
		for (String reg: functionInfoMap.keySet()) {
			if (Pattern.matches(reg, func)) {
				MathFunction mf = functionInfoMap.get(reg);
				try {
					return (MathFunction) mf.clone();
				} catch (CloneNotSupportedException e) {
					assert false;
				}
			}
		}
		return null;
	}

	private static Map<String, MathFunction> buildFunctionInfo() {
		Map<String, MathFunction> map = new HashMap<String, MathFunction>();
		map.put(PARENT_GROUP_REGEX, new MathFunction("parent", 1000, 1, new Parent()));
		map.put(ROOT_GROUP_REGEX, new MathFunction("root", 1000, 1, new Root()));
//		map.put(ENEMY_GROUP_REGEX, new MathFunction("enemy", 1000, 1, new Enemy()));
		map.put(HELPER_WITH_ID_GROUP_REGEX, new MathsFunctionSpeRedirect("helper", 1000, 1, new Helper())); 
		map.put(PLAYER_WITH_ID_GROUP_REGEX, new MathsFunctionSpeRedirect("playerid", 1000, 1, new Playerid())); 
		map.put(TARGET_WITH_ID_GROUP_REGEX, new MathsFunctionSpeRedirect("target", 1000, 1, new Target())); 
		map.put(ENEMYNEAR_WITH_ID_GROUP_REGEX, new MathsFunctionSpeRedirect("enemynear", 1000, 1, new Enemynear()));
		map.put(ENEMY_GROUP_REGEX, new MathsFunctionSpeRedirect("enemy", 1000, 1, new Enemy()));

		map.put(PARTNER_GROUP_REGEX, new MathsFunctionSpeRedirect("partner", 1000, 1, new Partner()));

//		map.put("playerid", new MathFunction("playerid", 1, 1, new Parent()));
		
		return map;
	}
	
	private static class Partner extends SpecialPatternRedirect {
		public Object getValue(String spriteId, Valueable... params) {
			Collection<Sprite> partners = GameFight.getInstance().getPartners(GameFight.getInstance().getSpriteInstance(spriteId));
			for (Sprite s: partners) {
				if (!(s instanceof SpriteHelper) 
						|| (s instanceof SpriteHelper && ((SpriteHelper)s).getHelperSub().getHelpertype().equals("normal"))) {
					return params[0].getValue(s.getSpriteId(), params);
				} 
			}
			return null;//params[0].getValue(spriteId, params);
		}
		public void reset() {}
	}

	
	private static class Parent implements Functionable {
		public Object getValue(String spriteId, Valueable... params) {
			spriteId = GameFight.getInstance().getParentId(spriteId);
			return params[0].getValue(spriteId, params);
		}
		public void reset() {}
	}
	private static class Root implements Functionable {
		public Object getValue(String spriteId, Valueable... params) {
			spriteId = GameFight.getInstance().getRootId(spriteId);
			return params[0].getValue(spriteId, params);
		}
		public void reset() {}
	}

//	private static class Enemy implements Functionable {
//		public Object getValue(String spriteId, Valueable... params) {
//			spriteId = StateMachine.getInstance().getFirstEnnmy(spriteId).getSpriteId();
//			return params[0].getValue(spriteId, params);
//		}
//		public void reset() {}
//	}
	
	
	
	
	
	
	public static class MathsFunctionSpeRedirect extends MathFunction {

		public MathsFunctionSpeRedirect(String op, int priority, int paramCount, SpecialPatternRedirect function) {
			super(op, priority, paramCount, function);
		}
		@Override
		public Object clone() throws CloneNotSupportedException {
			
			MathFunction clone = (MathFunction) super.clone();
			clone.setFunction((Functionable) ((SpecialPatternRedirect)function).clone());
			return clone;
		};		
	}
	
	
	
	
	
	
	
	public static abstract class SpecialPatternRedirect implements Functionable, Cloneable {

		public SpecialPatternRedirect() {
		}
		
		public void reset() {
			
		}
		@Override
		public Object clone() throws CloneNotSupportedException {
			return super.clone();
		}
		Valueable param;
		public Valueable getParam() {
			return param;
		}
		public void setParam(Valueable param) {
			this.param = param;
		}
		
	}
	
	
	
	private static class Helper extends SpecialPatternRedirect {
		public Helper() {
		}

		public Object getValue(String spriteId, Valueable... params) {
			Integer id = null;
			if (param != null) {
				id = Parser.getIntValue(param.getValue(spriteId));
			}
			if (id != null) {
				spriteId = GameFight.getInstance().getHelperIdWithID(spriteId, id);
				assert spriteId != null;
				
			} else {
				spriteId = GameFight.getInstance().getHelperId(spriteId);
			}
			return params[0].getValue(spriteId, params);
		}
	}
	private static class Target extends SpecialPatternRedirect {
		public Target() {
		}
		public Object getValue(String spriteId, Valueable... params) {
			Integer id = null;
			Sprite sprite = GameFight.getInstance().getSpriteInstance(spriteId);
			if (param != null) {
				id = Parser.getIntValue(param.getValue(spriteId));
			}
			if (id != null) {
				spriteId = GameFight.getInstance().getFightEngine().getTarget(sprite, id).getSpriteId();
				assert spriteId != null;
				
			} else {
				spriteId = GameFight.getInstance().getFightEngine().getTarget(sprite, -1).getSpriteId();
			}
			return params[0].getValue(spriteId, params);
		}

	}

	private static class Playerid extends SpecialPatternRedirect {
		public Playerid() {
		}
		public Object getValue(String spriteId, Valueable... params) {
			Integer id = Parser.getIntValue(param.getValue(spriteId));
			return params[0].getValue(id.toString(), params);
		}

	}

	private static class Enemynear extends SpecialPatternRedirect {
		public Enemynear() {
		}
		public Object getValue(String spriteId, Valueable... params) {
			Integer id = null;
			Sprite sprite = GameFight.getInstance().getSpriteInstance(spriteId);
			if (param != null) {
				id = Parser.getIntValue(param.getValue(spriteId));
			}
			if (id != null) {
				spriteId = GameFight.getInstance().getFightEngine().getEnemynear(sprite, id).getSpriteId();
				assert spriteId != null;
				
			} else {
				spriteId = GameFight.getInstance().getFightEngine().getEnemynear(sprite, -1).getSpriteId();
			}
			return params[0].getValue(spriteId, params);
		}

	}

	private static class Enemy extends SpecialPatternRedirect {
		public Object getValue(String spriteId, Valueable... params) {
			Collection<Sprite> ennemies = GameFight.getInstance().getEnnmies(GameFight.getInstance().getSpriteInstance(spriteId));
			Integer id = null;
			Sprite sprite = GameFight.getInstance().getSpriteInstance(spriteId);
			
			if (param != null) {
				id = Parser.getIntValue(param.getValue(spriteId));
			}
			int count = 0;
			Sprite last = null;
			for (Sprite s: ennemies) {
				// TODO Check why i comment this
//				if (!(s instanceof SpriteHelper) 
//						|| (s instanceof SpriteHelper && ((SpriteHelper)s).getHelperSub().getHelpertype().equals("normal"))) {
//					if (id != null && id == count)
//						return params[0].getValue(s.getSpriteId(), params);
//					count++;
//					last = s;
//				} 
				if (!(s instanceof SpriteHelper)) {
					if (id != null && id == count)
						return params[0].getValue(s.getSpriteId(), params);
					count++;
					last = s;
				} 
			}
			return params[0].getValue(last.getSpriteId(), params);
		}
		public void reset() {}
	}

}
