package org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns;

import java.util.List;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.character.SpriteCns;
import org.lee.mugen.sprite.cns.eval.function.SpriteCnsTriggerFunction;
/**
 * 
 * @author Dr Wong
 * @category Trigger : Complete
 */
public class Vel extends SpriteCnsTriggerFunction {

	public Vel() {
		super("vel", new String[] {"XY"});
	}
	@Override
	public Object getValue(String spriteId, Valueable... params) {
		String p = params[0].getValue(spriteId).toString();
		Sprite sprite = GameFight.getInstance().getSpriteInstance(spriteId);
		SpriteCns spriteInfo = sprite.getInfo();

		if ("x".equals(p)) {
			return spriteInfo.getVelset().getX();
		} else if ("y".equals(p)) {
			return spriteInfo.getVelset().getY();			
		} else if ("z".equals(p)) {
			return spriteInfo.getVelset().getZ();			
		}
		throw new IllegalArgumentException("arg must be x or y");
	}
	@Override
	public int parseValue(String[] tokens, int pos, List<Valueable> result) {
		pos++;
		if (!"x".equals(tokens[pos]) && !"y".equals(tokens[pos])) {
			throw new IllegalArgumentException("arg must be x or y");
		}
		final String component = tokens[pos];
		final Valueable valueable = new Valueable() {

			public Object getValue(String spriteId, Valueable... params) {
				return component;
			}
			
		};
		result.add(valueable);
		return pos;
	}
}
