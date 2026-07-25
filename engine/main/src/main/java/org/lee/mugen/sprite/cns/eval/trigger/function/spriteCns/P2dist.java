package org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns;

import java.util.List;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.StringValueable;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.character.SpriteCns;
import org.lee.mugen.sprite.character.SpriteHelper;
import org.lee.mugen.sprite.cns.eval.function.SpriteCnsTriggerFunction;
/**
 * 
 * @author Dr Wong
 * @category Trigger : Complete
 */
public class P2dist extends SpriteCnsTriggerFunction {

	public P2dist() {
		super("p2dist", new String[] {});
	}
	
	public float getXDiff(String spriteId, Valueable... params) {
		Sprite sprOne = GameFight.getInstance().getSpriteInstance(spriteId);
		Sprite sprTwo = null;
		for (Sprite spr : GameFight.getInstance().getEnnmies(sprOne)) {
			if (spr instanceof SpriteHelper)
				continue;
			if (!spr.equals(sprOne)) {
				sprTwo = spr;
			}
		}
		return sprOne.getInfo().getXPos() - sprTwo.getInfo().getXPos();
	}
	private float getYDiff(String spriteId, Valueable[] params) {
		Sprite sprOne = GameFight.getInstance().getSpriteInstance(spriteId);
		Sprite sprTwo = null;
		for (Sprite spr : GameFight.getInstance().getEnnmies(sprOne)) {
			if (spr instanceof SpriteHelper)
				continue;
			if (!spr.equals(sprOne)) {
				sprTwo = spr;
			}
		}

		return sprOne.getInfo().getYPos() - sprTwo.getInfo().getYPos();
	}
	@Override
	public Object getValue(String spriteId, Valueable... params) {
		String p = params[0].getValue(spriteId).toString();
		Sprite sprite = GameFight.getInstance().getSpriteInstance(spriteId);
		SpriteCns spriteInfo = sprite.getInfo();

		if ("x".equals(p)) {
			return spriteInfo.isFlip()? getXDiff(spriteId, params): -getXDiff(spriteId, params);
		} else if ("y".equals(p)) {
			return -getYDiff(spriteId, params);		
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
