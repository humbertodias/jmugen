package org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns;

import java.util.LinkedList;

import org.lee.mugen.core.FightEngine;
import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.character.SpriteHelper;
import org.lee.mugen.sprite.character.SpriteCns.MoveType;
import org.lee.mugen.sprite.character.spiteCnsSubClass.HitDefSub;
import org.lee.mugen.sprite.cns.eval.function.SpriteCnsTriggerFunction;
import org.lee.mugen.sprite.entity.ProjectileSub;

public class Moveguarded extends SpriteCnsTriggerFunction {

	// TODO : Moveguarded
	public Moveguarded() {
		super("moveguarded", new String[] {});
	}

	@Override
	public Object getValue(String spriteId, Valueable... params) {
		Sprite one = GameFight.getInstance().getSpriteInstance(spriteId);
		if (one.getInfo().getMovetype() == MoveType.A) {
			LinkedList<HitDefSub> hitdefs = GameFight.getInstance().getFightEngine().getHitdefBySpriteHitter(spriteId);
			HitDefSub strictHitdef = null;
			for (HitDefSub h: hitdefs)
				if (h.getClass() != ProjectileSub.class && spriteId.equals(h.getSpriteId()))
					strictHitdef = h;
			if (hitdefs.size() > 0 && (strictHitdef != null && strictHitdef.getTargetId() == null))
				return 0;
			for (Sprite s: GameFight.getInstance().getSprites()) {
				if (s == one || (s instanceof SpriteHelper))
					continue;
				if (s.getInfo().getLastHitdef() == null || !spriteId.equals(s.getInfo().getLastHitdef().getSpriteId())) {
					continue;
					
				} else if (FightEngine.isBlockState(s)) {
					continue;
				} else if (FightEngine.isBlockState(s) 
						&& spriteId.equals(s.getInfo().getLastHitdef().getSpriteId()) 
//						&& s.getInfo().getLastHitdef().getHittime() > 0
						&& s.getInfo().getLastHitdef().getLastTimeHitSomething() == s.getInfo().getLastHitdef().getLastTimeBlockBySomething()
						&& s.getInfo().getLastHitdef().getLastTimeBlockBySomething() + 1 < GameFight.getInstance().getGameState().getGameTime()
				) {
					return 1;
				}
			}
		}
		return 0;
	}
}
