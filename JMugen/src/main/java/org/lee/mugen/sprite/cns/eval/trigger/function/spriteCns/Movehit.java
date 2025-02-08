package org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns;

import java.util.LinkedList;

import org.lee.mugen.core.FightEngine;
import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.character.SpriteCns.MoveType;
import org.lee.mugen.sprite.character.spiteCnsSubClass.HitDefSub;
import org.lee.mugen.sprite.cns.eval.function.SpriteCnsTriggerFunction;
import org.lee.mugen.sprite.entity.ProjectileSub;

public class Movehit extends SpriteCnsTriggerFunction {

	public Movehit() {
		super("movehit", new String[] {});
	}

	@Override
	public Object getValue(String spriteId, Valueable... params) {
		
		Sprite one = GameFight.getInstance().getSpriteInstance(spriteId);
		if (one.getInfo().getMovetype() != MoveType.A)
			return 0;
		LinkedList<HitDefSub> hitdefs = GameFight.getInstance().getFightEngine().getHitdefBySpriteHitter(spriteId);

		HitDefSub lastHitdef = null;
		// I want the last not reversal
		for (HitDefSub h: hitdefs) {
			if (h.getSpriteId().equals(spriteId) && !(h instanceof ProjectileSub))
				lastHitdef = h;
		}
		HitDefSub lastHitdefOrigin = lastHitdef;
		for (Sprite s: GameFight.getInstance().getSprites()) {
			if (s.getInfo().getLastHitdef() == null)
				continue;
			if (FightEngine.isBlockState(s) || !s.getInfo().getLastHitdef().getSpriteId().equals(spriteId)) {
				continue;
			}
			
			Sprite spriteHitted = s;
			if (spriteHitted.getInfo().getLastHitdef() != null 
					&& spriteHitted.getInfo().getLastHitdef().getHittime() > 0
					&& !(spriteHitted.getInfo().getLastHitdef() instanceof ProjectileSub)
			)
				lastHitdef = spriteHitted.getInfo().getLastHitdef();
				
			if (lastHitdef != null && !FightEngine.isBlockState(spriteHitted)
					&& lastHitdef.getLastTimeHitSomething() != -1
					&& lastHitdef.getLastTimeBlockBySomething() == -1
					&& lastHitdef.getTimeCreated() + 1 < GameFight.getInstance().getGameState().getGameTime()) {
						if (lastHitdef.getHittime() <= 0)
							return 0;
						return 1;
			} else if (lastHitdefOrigin != null && !FightEngine.isBlockState(spriteHitted)
					&& lastHitdefOrigin.getLastTimeHitSomething() != -1
					&& lastHitdefOrigin.getLastTimeBlockBySomething() == -1
					&& lastHitdefOrigin.getTimeCreated() + 1 < GameFight.getInstance().getGameState().getGameTime()) {
						if (lastHitdefOrigin.getHittime() <= 0)
							return 0;
						return 1;
			}
		}
		
		return 0;
	}

	

}
