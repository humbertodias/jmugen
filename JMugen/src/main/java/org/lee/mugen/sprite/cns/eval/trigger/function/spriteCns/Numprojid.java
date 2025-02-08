package org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns;

import java.util.Collection;

import org.lee.mugen.core.FightEngine;
import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.base.AbstractSprite;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.character.spiteCnsSubClass.HitDefSub;
import org.lee.mugen.sprite.cns.eval.function.SpriteCnsTriggerFunction;
import org.lee.mugen.sprite.entity.ProjectileSprite;
import org.lee.mugen.sprite.entity.ProjectileSub;
import org.lee.mugen.sprite.parser.Parser;

public class Numprojid extends SpriteCnsTriggerFunction {

	public Numprojid() {
		super("numprojid", new String[] {});
	}

	@Override
	public Object getValue(String spriteId, Valueable... params) {
		if (params.length == 0)
			throw new IllegalArgumentException("no parameter for numprojid");
		int id = Parser.getIntValue(params[0].getValue(spriteId));
		int count = 0;

		FightEngine engine = GameFight.getInstance().getFightEngine();
		Collection<HitDefSub> hitdefs = engine.getHitdefBySpriteHitter(spriteId);
		Sprite sprite = GameFight.getInstance().getSpriteInstance(spriteId);
		for (HitDefSub hitdef : hitdefs) {
			if (hitdef instanceof ProjectileSub 
					&& GameFight.getInstance().getRoot((Sprite) hitdef.getSpriteHitter()) == sprite) {
				ProjectileSub proj = (ProjectileSub) hitdef;
				if (proj.getProjid().intValue() == id)
					count++;
			}
		}
		for (AbstractSprite spr: GameFight.getInstance().getOtherSprites()) {
			if (spr instanceof ProjectileSprite) {
				ProjectileSprite proj = (ProjectileSprite) spr;
				if (proj.getProjectileSub().getProjid().intValue() == id
						&& GameFight.getInstance().getRoot(proj.getProjectileSub().getSpriteParent()) == sprite)
					count++;
			}
		}
		return count;
	}
}
