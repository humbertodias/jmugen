package org.lee.mugen.sprite.cns.type.function;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.cns.eval.function.StateCtrlFunction;
import org.lee.mugen.sprite.entity.SuperpauseSub;
import org.lee.mugen.sprite.parser.ExpressionFactory;

public class Superpause extends StateCtrlFunction {

    public Superpause() {
        super("superpause", new String[] {"time","anim", "useSpriteAnim","sound","pos","movetime","darken","p2defmul","poweradd","unhittable"});
    }
	protected Object[] getDefaultValues(String name) {
		
		if ("time".equals(name)) {
			return new Object[] {30};
		}
		if ("anim".equals(name)) {
			return new Object[] {30};
		}
		if ("sound".equals(name)) {
			return new Object[] {-1};
		}
		if ("pos".equals(name)) {
			return new Object[] {0, 0};
		}
		if ("movetime".equals(name)) {
			return new Object[] {0};
		}
		if ("darken".equals(name)) {
			return new Object[] {1};
		}
		if ("p2defmul".equals(name)) {
			return new Object[] {0};
		}
		if ("poweradd".equals(name)) {
			return new Object[] {0};
		}
		if ("unhittable".equals(name)) {
			return new Object[] {1};
		}

		return null;
	}
    
	@Override
	public Object getValue(String spriteId, Valueable... params) {
		SuperpauseSub superpauseSub = new SuperpauseSub();
		fillBean(spriteId, superpauseSub);
		superpauseSub.setSprite(GameFight.getInstance().getSpriteInstance(spriteId));
		GameFight.getInstance().getGlobalEvents().setSuperPause(superpauseSub);
		return null;
	}
	public static Valueable[] parseForAnim(String name, String value) {
		final boolean isUseSprite;
		if (value.indexOf("s") != -1) {
			isUseSprite = true;
			value = value.replaceAll("s", "");
		} else {
			isUseSprite = false;
		}
		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		Valueable[] result = vals;

		result = new Valueable[vals.length + 1];
		System.arraycopy(vals, 0, result, 0, vals.length);
		result[vals.length] = new Valueable() {
			public Object getValue(String spriteId, Valueable... params) {
				return isUseSprite;
			}
		};
		return result;
	}

	public static Valueable[] parseForSound(String name, String value) {
		final boolean isUseSprite;
		if (value.indexOf("s") != -1) {
			isUseSprite = true;
			value = value.replaceAll("s", "");
		} else {
			isUseSprite = false;
		}
		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		Valueable[] result = vals;
		result = new Valueable[vals.length + 1];
		System.arraycopy(vals, 0, result, 0, vals.length);
		result[vals.length] = new Valueable() {
			public Object getValue(String spriteId, Valueable... params) {
				return isUseSprite;
			}
		};
			
		return result;
	}

}
