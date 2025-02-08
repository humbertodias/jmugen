package org.lee.mugen.sprite.cns.type.function;

import java.util.HashMap;
import java.util.Map;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.character.SpriteState;
import org.lee.mugen.sprite.cns.eval.function.StateCtrlFunction;
import org.lee.mugen.sprite.parser.Parser;
import org.lee.mugen.util.MugenRandom;
/**
 * 
 * @author Dr Wong
 * @category Cns Function : Complete
 */
public class Varrandom extends StateCtrlFunction {

	private static Map<String, String> _RENAME_FIELD = new HashMap<String, String>();
	static {
		_RENAME_FIELD.put("value", "range");
	}
	@Override
	public boolean containsParam(String param) {
		return getParamNames().contains(param) || _RENAME_FIELD.containsKey(param);
	}
	
	/**
	 * because of some compatibilties
	 */
	@Override
	public void addParam(String name, Valueable[] param) {
		if (_RENAME_FIELD.containsKey(name)) {
			name = _RENAME_FIELD.get(name);
		}
		super.addParam(name, param);
	}
	
	
	public Varrandom() {
		super("varrandom", new String[] {"v", "range"});
	}

	@Override
	public Object getValue(String spriteId, Valueable... ps) {
		Sprite sprite = GameFight.getInstance().getSpriteInstance(spriteId);
		
		final SpriteState spriteState = sprite.getSpriteState();
		
		int vIndex = getParamIndex("v");
		Valueable v = valueableParams[vIndex][0];
		Float fv = Parser.getFloatValue(v.getValue(spriteId));
		int iv = fv.intValue();
		
		int rangeIndex = getParamIndex("range");
		Valueable[] range = valueableParams[rangeIndex];
		Valueable randomValue = null;

		if (range == null) {
			final float res = MugenRandom.getRandomNumber(0, 1000);
			randomValue = new Valueable() {

				public Object getValue(String spriteId, Valueable... params) {
					return res;
				}};
			
		} else if (range.length == 1) {
			int end = Parser.getIntValue(range[0].getValue(spriteId));
			final float res = MugenRandom.getRandomNumber(0, end);
			randomValue = new Valueable() {

				public Object getValue(String spriteId, Valueable... params) {
					return res;
				}};
		} else if (range.length == 2) {
			int start = Parser.getIntValue(range[0].getValue(spriteId));
			int end = Parser.getIntValue(range[1].getValue(spriteId));
			final float res = MugenRandom.getRandomNumber(start, end);
			randomValue = new Valueable() {

				public Object getValue(String spriteId, Valueable... params) {
					return res;
				}};
		} 
		spriteState.getVars().setVar(iv + "", randomValue);
		return null;
	}
}
