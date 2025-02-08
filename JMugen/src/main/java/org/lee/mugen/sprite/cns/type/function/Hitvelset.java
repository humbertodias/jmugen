package org.lee.mugen.sprite.cns.type.function;

import org.lee.mugen.core.FightEngine;
import org.lee.mugen.core.GameFight;
import org.lee.mugen.core.physics.PhysicsEngime;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.base.AbstractSprite;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.character.SpriteCns.Physics;
import org.lee.mugen.sprite.character.SpriteCns.Type;
import org.lee.mugen.sprite.character.spiteCnsSubClass.HitDefSub;
import org.lee.mugen.sprite.cns.eval.function.StateCtrlFunction;
import org.lee.mugen.sprite.parser.Parser;

public class Hitvelset extends StateCtrlFunction {

	public Hitvelset() {
		super("hitvelset", new String[] {"x", "y"});
	}

	@Override
	public Object getValue(String spriteId, Valueable... params) {
		int xIndex = getParamIndex("x");
		int yIndex = getParamIndex("y");

		
		Sprite sprHitted = GameFight.getInstance().getSpriteInstance(spriteId);
		HitDefSub hitdefFrom = GameFight.getInstance().getSpriteInstance(spriteId).getInfo().getLastHitdef();
		
		AbstractSprite sprHitter = hitdefFrom.getSpriteHitter();
		
		if (valueableParams[xIndex] != null) {
			Valueable x = valueableParams[xIndex][0];
			float xVel = 0;
			if (x == null || Parser.getFloatValue(x.getValue(spriteId)) == 0) {
				
			} else if (Parser.getFloatValue(x.getValue(spriteId)) != 0) {
				if (hitdefFrom.getSprHittedTypeWhenHit() == Type.A || hitdefFrom.getSprHittedTypeWhenHit() == Type.I) {
					xVel = hitdefFrom.getAir().getVelocity().getX();
					
				} else {
					xVel = hitdefFrom.getGround().getVelocity().getX();
				}
				if (!sprHitter.isFlip() && sprHitted.isFlip()) {
				} else if (sprHitter.isFlip() && !sprHitted.isFlip()) {
				} else if ((!sprHitter.isFlip() && !sprHitted.isFlip()) || (sprHitter.isFlip() && sprHitted.isFlip())) {
					xVel *= -1;
				}
//				if (sprHitter.getClass() == Sprite.class)
//				if (sprHitter.getRealXPos() > sprHitted.getRealXPos() && (!sprHitter.isFlip() && sprHitted.isFlip()))  {
//					xVel *= -1;
//					
//				} else if (sprHitter.getRealXPos() < sprHitted.getRealXPos() && (sprHitter.isFlip() && !sprHitted.isFlip()))  {
//					
//				}
					
			}
			sprHitted.getInfo().getVelset().setX(xVel);
			if (PhysicsEngime.isOutOfScreeen(sprHitted, sprHitted.isFlip()? -xVel: xVel)) {
				if (sprHitter.getClass() == Sprite.class) {
					Sprite spr = (Sprite) sprHitter;
					boolean isHitterFlip = spr.isFlip();
					boolean isBlockState = FightEngine.isBlockState(sprHitted);
					float cornpush = 0;
					if (isBlockState) {
						if (spr.getInfo().getPhysics() == Physics.S || spr.getInfo().getPhysics() == Physics.C) {
							cornpush = hitdefFrom.getGuard().getCornerpush().getVeloff();
							
						} else if (spr.getInfo().getPhysics() == Physics.A) {
							cornpush = hitdefFrom.getAirguard().getCornerpush().getVeloff();
							
						}
						
					} else {
						if (spr.getInfo().getPhysics() == Physics.S) {
							cornpush = hitdefFrom.getGround().getCornerpush().getVeloff();
							
						} else if (spr.getInfo().getPhysics() == Physics.A) {
							cornpush = hitdefFrom.getAir().getCornerpush().getVeloff();
							
						} else if (spr.getInfo().getPhysics() == Physics.C) {
							cornpush = hitdefFrom.getDown().getCornerpush().getVeloff();
							
						}
						
					}
					spr.getInfo().getVelset().addX(cornpush);

				}
			} else {
				sprHitted.getInfo().getVelset().setX(xVel);
			}

		}

		if (valueableParams[yIndex] != null) {
			Valueable y = valueableParams[yIndex][0];
			float yVel = 0;
			if (y == null || Parser.getFloatValue(y.getValue(spriteId)) == 0) {
				
			} else if (Parser.getFloatValue(y.getValue(spriteId)) != 0) {
				if (hitdefFrom.getSprHittedTypeWhenHit() == Type.A || hitdefFrom.getSprHittedTypeWhenHit() == Type.I) {
					yVel = hitdefFrom.getAir().getVelocity().getY();
					
				} else {
					yVel = hitdefFrom.getGround().getVelocity().getY();
				}
			}
			sprHitted.getInfo().getVelset().setY(yVel);

		}
		return null;
	}
	
	
}
