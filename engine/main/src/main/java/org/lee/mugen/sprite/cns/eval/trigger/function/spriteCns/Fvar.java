package org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.character.SpriteState;
import org.lee.mugen.sprite.cns.eval.function.SpriteCnsTriggerFunction;
import org.lee.mugen.sprite.parser.Parser;

public class Fvar extends SpriteCnsTriggerFunction {

	public Fvar() {
		super("fvar", new String[0]);
	}
	@Override
	public Object getValue(String spriteId, Valueable... params) {
		Sprite sprite = GameFight.getInstance().getSpriteInstance(spriteId);
		SpriteState spriteState = sprite.getSpriteState();
		
		int index = Parser.getIntValue(params[0].getValue(spriteId));
		
		return spriteState.getVars().getFVar(String.valueOf(index));
	}
}
