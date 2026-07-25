package org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns;

import java.util.List;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.StringValueable;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.character.SpriteCns.Type;
import org.lee.mugen.sprite.character.spiteCnsSubClass.HitDefSub;
import org.lee.mugen.sprite.cns.eval.function.SpriteCnsTriggerFunction;

public class Hitvel extends SpriteCnsTriggerFunction {

	public Hitvel() {
		super("hitvel", new String[] {"XY"});
	}
	@Override
	public Object getValue(String spriteId, Valueable... params) {
		String p = params[0].getValue(spriteId).toString();
		Sprite sprite = GameFight.getInstance().getSpriteInstance(spriteId);
		HitDefSub hitdefFrom = GameFight.getInstance().getSpriteInstance(spriteId).getInfo().getLastHitdef();
		if (hitdefFrom == null || hitdefFrom.getHittime() <= 0)
			return 0;
		if ("x".equals(p)) {
			if (hitdefFrom.getAttr().getType() == Type.A.getBit()) {
				return hitdefFrom.getGround().getVelocity().getX();
			} else {
				return hitdefFrom.getAir().getVelocity().getX();
			}
			
		} else if ("y".equals(p)) {
			if (hitdefFrom.getAttr().getType() == Type.A.getBit()) {
				return hitdefFrom.getGround().getVelocity().getY();
			} else {
				return hitdefFrom.getAir().getVelocity().getY();
			}
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
		final Valueable valueable = new StringValueable(component);

		result.add(valueable);
		return pos;
	}
}
