package org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns;

import java.util.List;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.StringValueable;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.character.SpriteCns;
import org.lee.mugen.sprite.cns.eval.function.SpriteCnsTriggerFunction;

/**
 * 
 * @author Dr Wong
 * @category Trigger : Complete
 */
public class Command extends SpriteCnsTriggerFunction {
	public Command() {
		super("command", new String[] {"name"});
	}
	@Override
	public Object getValue(String spriteId, Valueable... params) {
		Sprite sprite = GameFight.getInstance().getSpriteInstance(spriteId);
		SpriteCns info = sprite.getInfo();
		boolean b = ((Boolean)params[1].getValue(spriteId)).booleanValue();

		if (b) {
			return info.getCommand(params[0].getValue(spriteId).toString());
		} else {
			return info.getCommand(params[0].getValue(spriteId).toString()) > 0? 0 : 1;
		}
	}

	@Override
	public int parseValue(String[] tokens, int pos, List<Valueable> result) {
		pos++;
		final boolean isEqual = "=".equals(tokens[pos]);

		pos++;
		final String str = tokens[pos];
		result.add(new StringValueable(str));

		result.add(new Valueable() {

			public Object getValue(String spriteId, Valueable... params) {
				return isEqual;
			}});
		return pos;
	}

	
}
