package org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns;

import java.util.Collection;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.cns.eval.function.SpriteCnsTriggerFunction;

public class Numenemy extends SpriteCnsTriggerFunction {

	public Numenemy() {
		super("numenemy", new String[] {});
	}

	@Override
	public Object getValue(String spriteId, Valueable... params) {
		Sprite sprite = GameFight.getInstance().getSpriteInstance(spriteId);
		Collection<Sprite> sprites = GameFight.getInstance().getEnnmies(sprite);
		return GameFight.countNormalPlayer(sprites);
	
	}
}
