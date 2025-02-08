package org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns;

import java.text.MessageFormat;

import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.cns.eval.function.SpriteCnsTriggerFunction;

/**
 * 
 * @author Dr Wong
 * Test if the player is alive
 * @category Trigger : Complete
 */
public class Format extends SpriteCnsTriggerFunction {

	public Format() {
		super("format", new String[0]);
	}
	@Override
	public Object getValue(String spriteId, Valueable... params) {
		if (params.length == 0)
			return "";
		Object[] ps = new Object[params.length - 1];
		for (int i = 1; i < params.length; ++i) {
			ps[i - 1] = params[i].getValue(spriteId);
		}
		return MessageFormat.format(params[0].getValue(spriteId).toString(), ps);
	}
	
}
