package org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns;

import java.util.List;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.character.SpriteCns.Type;
import org.lee.mugen.sprite.cns.eval.function.SpriteCnsTriggerFunction;

/**
 * 
 * @author Dr Wong
 * @category Trigger : Complete
 */
public class Statetype extends SpriteCnsTriggerFunction {

	public Statetype() {
		super("statetype", new String[] {});
	}
	public void addParam(String name, Valueable[] param) {
		
	}

	
	@Override
	public Object getValue(String spriteId, Valueable... params) {
		return params[0].getValue(spriteId);
	}
	public Valueable[] parseValue(String name, final String value, final String spriteId) {
		return null;
	}
	
	@Override
	public int parseValue(String[] tokens, int pos, List<Valueable> result) {
		// statetype = char
		//   ^
		
		pos++;
		
		if ("!=".equals(tokens[pos])) {
			pos++;
			final Type type = Type.valueOf(tokens[pos].toUpperCase());
			Valueable v = new Valueable() {

				public Object getValue(String spriteId, Valueable... params) {
					final Sprite spr = GameFight.getInstance().getSpriteInstance(spriteId);
					return spr.getInfo().getType().getBit() != type.getBit() ? 1: 0;
				}
				
			};
			result.add(v);

			
		} else if ("=".equals(tokens[pos])) {
			pos++;
			final Type type = Type.valueOf(tokens[pos].toUpperCase());
			Valueable v = new Valueable() {

				public Object getValue(String spriteId, Valueable... params) {
					final Sprite spr = GameFight.getInstance().getSpriteInstance(spriteId);
					return spr.getInfo().getType().getBit() == type.getBit()? 1: 0;
				}
				
			};
			result.add(v);

			
		} else {
			throw new IllegalStateException();
		}
		return pos;
	}

}
