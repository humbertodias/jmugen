package org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns;

import java.util.List;

import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.cns.eval.function.SpriteCnsTriggerFunction;

public class Hitdefattr extends SpriteCnsTriggerFunction {

	// Format : HitDefAttr [oper] value1, value2
	// TODO : Hitdefattr
	public Hitdefattr() {
		super("hitdefattr", new String[] {});
	}

	@Override
	public int parseValue(String[] tokens, int pos, List<Valueable> result) {
		// TODO Auto-generated method stub
		return super.parseValue(tokens, pos, result);
	}
	
	@Override
	public Object getValue(String spriteId, Valueable... params) {
		// TODO Auto-generated method stub
		return super.getValue(spriteId, params);
	}
	
	private boolean isValidToken(String token) {
		return false;
	}
}
