package org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns;

import java.util.List;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.StringValueable;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.character.SpriteCns;
import org.lee.mugen.sprite.character.SpriteHelper;
import org.lee.mugen.sprite.cns.eval.function.SpriteCnsTriggerFunction;

public class Rootdist extends SpriteCnsTriggerFunction {

	public Rootdist() {
		super("rootdist", new String[] {});
	}
	
	public float getXDiff(SpriteHelper sprOne, Sprite sprTwo) {
		return sprOne.getInfo().getXPos() - sprTwo.getInfo().getXPos();
	}
	private float getYDiff(SpriteHelper sprOne, Sprite sprTwo) {
		return sprOne.getInfo().getYPos() - sprTwo.getInfo().getYPos();
	}
	@Override
	public Object getValue(String spriteId, Valueable... params) {
		String p = params[0].getValue(spriteId).toString();
		SpriteHelper helper = (SpriteHelper) GameFight.getInstance().getSpriteInstance(spriteId);
		SpriteCns helperInfo = helper.getInfo();

		Sprite root = GameFight.getInstance().getRoot(helper);
		
		if ("x".equals(p)) {
			return helperInfo.isFlip()? getXDiff(helper, root): -getXDiff(helper, root);
		} else if ("y".equals(p)) {
			return -getYDiff(helper, root);		
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
