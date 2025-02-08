package org.lee.mugen.core;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import org.lee.mugen.core.renderer.game.SparkRender;
import org.lee.mugen.core.renderer.game.SparkhitRender;
import org.lee.mugen.core.sound.SoundSystem;
import org.lee.mugen.lang.Wrap;
import org.lee.mugen.lang.Wrapper;
import org.lee.mugen.snd.Snd;
import org.lee.mugen.sprite.base.AbstractSprite;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.character.SpriteCns;
import org.lee.mugen.sprite.character.SpriteHelper;
import org.lee.mugen.sprite.character.SpriteCns.MoveType;
import org.lee.mugen.sprite.character.SpriteCns.Type;
import org.lee.mugen.sprite.character.spiteCnsSubClass.HitBySub;
import org.lee.mugen.sprite.character.spiteCnsSubClass.HitDefSub;
import org.lee.mugen.sprite.character.spiteCnsSubClass.NotHitBySub;
import org.lee.mugen.sprite.character.spiteCnsSubClass.ReversaldefSub;
import org.lee.mugen.sprite.character.spiteCnsSubClass.HitDefSub.AffectTeam;
import org.lee.mugen.sprite.character.spiteCnsSubClass.HitDefSub.AttrClass;
import org.lee.mugen.sprite.character.spiteCnsSubClass.HitDefSub.AttrType;
import org.lee.mugen.sprite.cns.type.function.Assertspecial;
import org.lee.mugen.sprite.cns.type.function.Projectile;
import org.lee.mugen.sprite.entity.HitOverrideSub;
import org.lee.mugen.sprite.entity.Priority;
import org.lee.mugen.sprite.entity.ProjectileSprite;
import org.lee.mugen.sprite.entity.ProjectileSub;
import org.lee.mugen.sprite.entity.Priority.HitType;

/**
 * FightEngine groups hitdefs and decice what's rules for hit, protect, ...
 * @author Dr Wong
 *
 */
public class FightEngine {

	private List<HitDefSub> hitdefs = new LinkedList<HitDefSub>();
	
	private List<HitDefSub> hitdefsWhenClns1IsDefine = new LinkedList<HitDefSub>();

	public void add(HitDefSub hitdef) {
		hitdefs.add(hitdef);
	}

	public void process() {
		processPosition();
		Set<HitDefSub> removes = new HashSet<HitDefSub>();
		Set<HitDefSub> notProcessThisTime = new HashSet<HitDefSub>();
		for (HitDefSub hitdef : hitdefs.toArray(new HitDefSub[hitdefs.size()])) {
			Collection<Sprite> sprites = getSpriteAffected(hitdef.getAffectTeam(), hitdef.getSpriteHitter());
			for (Sprite sprite : sprites) {
				if (hitdef.getSpriteId().equals(sprite.getSpriteId())
						|| notProcessThisTime.contains(hitdef))
					continue;
				if (hitdef.getSpriteHitter() != null && hitdef.getSpriteHitter().isPause())
					continue;
				process(hitdef, sprite, removes, notProcessThisTime);
			}
			if (hitdef instanceof ProjectileSub) {
				ProjectileSub projHitter = (ProjectileSub) hitdef;
				for (HitDefSub hitdefHitted : hitdefs) {
					if (notProcessThisTime.contains(hitdef))
						continue;
					if (hitdefHitted instanceof ProjectileSub) {
						ProjectileSub projHitted = (ProjectileSub) hitdefHitted;
						if (hitdefHitted != projHitter) {
							process(projHitter, projHitted.getSpriteHitter(),
									removes, notProcessThisTime);
						}
					}
				}
			}
			if (!(hitdef instanceof ProjectileSub)) {
				Sprite spr = GameFight.getInstance().getSpriteInstance(
						hitdef.getSpriteId());
				if (spr == null || 
						(spr.getInfo().getMovetype() != MoveType.A 
							&& 	hitdef.getTimeCreated() != GameFight.getInstance().getGameState().getGameTime()
						)) 
					removes.add(hitdef);
			}
			if (hitdefsWhenClns1IsDefine.contains(hitdef) && !(hitdef instanceof ProjectileSub)) {
				if (!hasAttackRect(hitdef.getSpriteHitter()) && hitdef.getHittime() > 0) {
					removes.add(hitdef);
					hitdefsWhenClns1IsDefine.remove(hitdef);	
				}
				
			} else if (hasAttackRect(hitdef.getSpriteHitter())) {
				hitdefsWhenClns1IsDefine.add(hitdef);
			}
		}
		hitdefsWhenClns1IsDefine.removeAll(removes);
		hitdefs.removeAll(removes);
	}
	

	private Collection<Sprite> getSpriteAffected(AffectTeam affectTeam, AbstractSprite sprite) {
		if (affectTeam == AffectTeam.E) {
			return GameFight.getInstance().getEnnmies(sprite);
		} else if (affectTeam == AffectTeam.F) {
			return GameFight.getInstance().getPartners(sprite);
		} else if (affectTeam == AffectTeam.B) {
			Collection<Sprite> result = new LinkedList<Sprite>();
			result.addAll(GameFight.getInstance().getEnnmies(sprite));
			result.addAll(GameFight.getInstance().getPartners(sprite));
			return result;
		}
		return null;
	}

	private void process(HitDefSub hitdef, AbstractSprite sprite,
			Set<HitDefSub> removes, Set<HitDefSub> notProcess) {
		if (sprite instanceof Sprite) {
			attemptToProtect(hitdef, (Sprite) sprite);
		}
		if (hitdef instanceof ProjectileSub) {
			if (sprite instanceof ProjectileSprite) {
				processProjectileVsProjectile((ProjectileSub) hitdef,
						(ProjectileSprite) sprite, removes, notProcess);
			} else if (sprite instanceof Sprite) {
				processProjectileVsSprite((ProjectileSub) hitdef,
						(Sprite) sprite, removes, notProcess);
			}
		} else {
			if (sprite instanceof ProjectileSprite) {
				processHitdefVsProjectile(hitdef, (ProjectileSprite) sprite,
						removes, notProcess);

			} else if (!(sprite instanceof SpriteHelper) && (sprite instanceof Sprite)) {
				processHitdefVsSprite(hitdef, (Sprite) sprite, removes, notProcess);
			}
		}
	}

	//
	private void processHitdefVsSprite(HitDefSub hitdef, Sprite sprite,
			Set<HitDefSub> removes, Set<HitDefSub> notProcess) {
		if (sprite.getInfo().getNothitby().getTime() > 0)
			return; // TODO
		if (isBlockState(sprite)
				&& !canOneHitsTwo(hitdef.getHitFlag(), hitdef.getGuardFlag(), sprite) 
				&& canHitDefByPassHitByAndNotHitBy(hitdef.getAttr(), sprite)) {
			processHitdefVsSpriteProtectedMode(hitdef, sprite, removes,
					notProcess);
		} else if (canOneHitsTwo(hitdef.getHitFlag(), hitdef.getGuardFlag(), sprite)) {
			processHitdefVsSpriteHittedMode(hitdef, sprite, removes, notProcess);
		}
	}

	private boolean canHitDefByPassHitByAndNotHitBy(AttrClass attr,
			Sprite sprite) {
		SpriteCns info = sprite.getInfo();
		boolean isHitBy = false;
		boolean isNotHitBy = false;

		boolean isHitByActive = false;
		boolean isNotHitByActive = false;

		if (info.getHitby().getTime() > 0) {
			isHitByActive = true;
			HitBySub hitby = info.getHitby();
			AttrClass otherAttr = hitby.getValue();
			if (otherAttr == null)
				otherAttr = hitby.getValue2();
			isHitBy = otherAttr.containsType(attr.getType()) && attr.isAttrTypesAndLevels(otherAttr.getCouples());
			
		}
		if (info.getNothitby().getTime() > 0) {
			isNotHitByActive = true;
			NotHitBySub notHitby = info.getNothitby();
			AttrClass otherAttr = notHitby.getValue();
			if (otherAttr == null)
				otherAttr = notHitby.getValue2();
			isNotHitByActive = otherAttr.containsType(attr.getType()) && attr.isAttrTypesAndLevels(otherAttr.getCouples());
		}
		return (!isNotHitByActive || !isHitByActive) || ((isHitByActive && isHitBy) && (!isNotHitByActive || !isNotHitBy));
	}

	//
	private void processHitdefVsProjectile(HitDefSub hitdef,
			ProjectileSprite sprite, Set<HitDefSub> removes,
			Set<HitDefSub> notProcess) {
//		Wrapper<Rectangle> wrap = new Wrapper<Rectangle>();

		if (isHitdefHitAttackRec(hitdef, sprite)) {
			// TODO
		}
	}

	private void processHitdefVsSpriteProtectedMode(HitDefSub hitdef,
			Sprite sprite, Set<HitDefSub> removes, Set<HitDefSub> notProcess) {
//		Wrapper<Rectangle> wrap = new Wrapper<Rectangle>();

		if (isHitdefHitCollisionRec(hitdef, sprite)) {
			Sprite spriteHitter = GameFight.getInstance().getSpriteInstance(
					hitdef.getSpriteId());
			
			hitdef.setTargetId(sprite.getSpriteId());
			// TODO Make a class for shaking

			try {
				playSoundGuard(hitdef, sprite);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (spriteHitter != null) // It's a helper
				spriteHitter.setPause(hitdef.getGuard().getPausetime().getP1_pausetime());

			sprite.getInfo().setLastHitdef(hitdef);


			setSpriteBlockBySpriteState(hitdef, sprite);
			
			sprite.getInfo().getShake().setAmpl(2);
			sprite.getInfo().getShake().setTime(
					hitdef.getGuard().getPausetime().getP2_shaketime());
			sprite.setPause(hitdef.getGuard().getPausetime().getP2_shaketime());
			
			
			// envshake
    		GameFight.getInstance().getStage().getCamera().setEnvShake(hitdef.getEnvshake());
			
			

			removes.add(hitdef);
			drawSparkHit(hitdef, sprite);
//			drawSparkHit(hitdef, sprite, wrap.getValue());
		}
	}

	private void processHitdefVsSpriteHittedMode(HitDefSub hitdef,
			Sprite sprite, Set<HitDefSub> removes, Set<HitDefSub> notProcess) {
		if (hasAttackRect(sprite)) {
			processHitdefVsAttackRec(hitdef, sprite, removes, notProcess);
		} else {
			processHitdefVsCollisionRec(hitdef, sprite, removes, notProcess);
		}
	}

	private void processHitdefVsCollisionRec(final HitDefSub hitdef,
			final Sprite sprite, Set<HitDefSub> removes,
			Set<HitDefSub> notProcess) {
		processHitdefVsCollisionRec(hitdef, sprite, removes, notProcess, false);
	}

	private void processHitdefVsCollisionRec(final HitDefSub hitdef,
			final Sprite sprite, Set<HitDefSub> removes,
			Set<HitDefSub> notProcess, boolean force) {
		
		Wrapper<Rectangle> wrap = new Wrapper<Rectangle>();
		if (hitdef instanceof ReversaldefSub)
			return;
		if (isHitdefHitCollisionRec(hitdef, sprite, wrap) || force) {
			hitdef.setTargetId(sprite.getSpriteId());

			Sprite spriteHitter = GameFight.getInstance().getSpriteInstance(
					hitdef.getSpriteId());

			// TODO Make a class for shaking

			try {
				playSoundHit(hitdef, sprite);
				
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (spriteHitter != null) // It's a helper
				spriteHitter.setPause(hitdef.getPausetime().getP1_pausetime());
			
			sprite.getInfo().setLastHitdef(hitdef);
			sprite.getInfo().addLife(-hitdef.getDamage().getHit_damage());
			setSpriteHitSpriteState(hitdef, sprite, removes, notProcess);
			
			sprite.getInfo().getShake().setAmpl(2);
			sprite.getInfo().getShake().setTime(
					hitdef.getPausetime().getP2_shaketime());
			sprite.setPause(hitdef.getPausetime().getP2_shaketime());
//			sprite.getInfo().addLife(-10);

			// envshake
    		GameFight.getInstance().getStage().getCamera().setEnvShake(hitdef.getEnvshake());

			
			drawSparkHit(hitdef, sprite, wrap.getValue());

			removes.add(hitdef);
		}
	}

	private void processHitdefVsAttackRec(HitDefSub hitdef, Sprite sprite,
			Set<HitDefSub> removes, Set<HitDefSub> notProcess) {
//		Wrapper<Rectangle> wrap = new Wrapper<Rectangle>();

		boolean isHitdefHitAttackRec = isHitdefHitAttackRec(hitdef, sprite);
		if (hitdef instanceof ReversaldefSub 
				&& isHitdefHitAttackRec) {
//			&& 
//		}
//				((ReversaldefSub)hitdef).canBlockAttack(getFirstHitdefBySpriteHitter(sprite.getSpriteId()))) {

			hitdef.setTargetId(sprite.getSpriteId());

			Sprite spriteHitter = GameFight.getInstance().getSpriteInstance(
					hitdef.getSpriteId());

			// TODO Make a class for shaking

			try {
				playSoundHit(hitdef, sprite);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (spriteHitter != null) // It's a helper
				spriteHitter.setPause(hitdef.getPausetime().getP1_pausetime());
			
			sprite.getInfo().setLastHitdef(hitdef);
			sprite.getInfo().addLife(-hitdef.getDamage().getGuard_damage());

			setSpriteHitSpriteState(hitdef, sprite, removes, notProcess);
			
			sprite.getInfo().getShake().setAmpl(2);
			sprite.getInfo().getShake().setTime(
					hitdef.getPausetime().getP2_shaketime());
			sprite.setPause(hitdef.getPausetime().getP2_shaketime());


			// envshake
    		GameFight.getInstance().getStage().getCamera().setEnvShake(hitdef.getEnvshake());

			
			drawSparkHit(hitdef, sprite);

			removes.add(hitdef);
			for (HitDefSub h: hitdefs)
				if (h.getSpriteHitter() == sprite) {
					removes.add(h);
					notProcess.add(h);
				}
		
		} else if (isHitdefHitAttackRec) {
			int onePrio = hitdef.getPriority().getHit_prior();
			HitDefSub hitdefTwo = getFirstHitdefBySpriteHitter(sprite.getSpriteId());
			if (hitdefTwo == null)
				return; // and do nothing wait next time
			int twoPrio = hitdefTwo.getPriority().getHit_prior();
			notProcess.add(hitdefTwo);
			removes.add(hitdefTwo);

			// /////////////
			HitDefSub hitdefOne = hitdef;
			Sprite one = GameFight.getInstance().getSpriteInstance(
					hitdef.getSpriteId());
			Sprite two = sprite;


			// One is hitted
			Priority priorityOne = hitdefOne.getPriority();
			Priority priorityTwo = hitdefTwo.getPriority();
			if (priorityOne.getHit_type() == HitType.HIT 
					&& priorityTwo.getHit_type() == HitType.HIT
			) {
				if (onePrio <= twoPrio) {
					processHitdefVsCollisionRec(hitdefTwo, one, removes,
							notProcess, true);
				}
				if (onePrio >= twoPrio) {
					processHitdefVsCollisionRec(hitdefOne, two, removes,
							notProcess, true);
				}
				removes.add(hitdefTwo);
				notProcess.add(hitdefTwo);
				notProcess.add(hitdefTwo);
			} else if (priorityOne.getHit_type() == HitType.HIT
					&& priorityTwo.getHit_type() == HitType.MISS
			) {
				processHitdefVsCollisionRec(hitdefOne, two, removes,
						notProcess, true);
				removes.add(hitdefTwo);
				notProcess.add(hitdefTwo);
			} else {
				notProcess.add(hitdefTwo);
			}

		}
	}

	// //////////////////////////////////////////
	//
	private void processProjectileVsProjectile(ProjectileSub hitdef,
			ProjectileSprite sprite, Set<HitDefSub> removes,
			Set<HitDefSub> notProcess) {
//		Wrapper<Rectangle> wrap = new Wrapper<Rectangle>();

		if (isHitdefHitAttackRec(hitdef, sprite)) {
			// TODO Projectile Life
			if (hitdef.getProjremove() != 0) {
				removes.add(hitdef);
				hitdef.getSpriteHitter().setProjHitSprite();

			}
			hitdef.addProjhits(-1);
			if (hitdef.getProjhits() <= 0) {
				removes.add(hitdef);
			}
			
			for (HitDefSub hi : hitdefs)
				if (hi.getSpriteHitter() == sprite) {
					ProjectileSub h = (ProjectileSub) hi;
					if (h.getProjremove() != 0) {
						removes.add(h);
						sprite.setProjHitSprite();

					}
					h.addProjhits(-1);
					if (h.getProjhits() <= 0) {
						removes.add(h);
					}
					sprite.setProjHitSprite();
					notProcess.add(h);
				}

		
		}
	}

	private void processProjectileVsSprite(ProjectileSub hitdef, Sprite sprite,
			Set<HitDefSub> removes, Set<HitDefSub> notProcess) {
		if (isBlockState(sprite)) {
			processProjectileVsSpriteProtectedMode(hitdef, sprite, removes,
					notProcess);
		} else {
			processProjectileVsSpriteHittedMode(hitdef, sprite, removes,
					notProcess);
		}
	}

	//
	private void processProjectileVsSpriteProtectedMode(ProjectileSub hitdef,
			Sprite sprite, Set<HitDefSub> removes, Set<HitDefSub> notProcess) {
		
//		Wrapper<Rectangle> wrap = new Wrapper<Rectangle>();

		if (isHitdefHitCollisionRec(hitdef, sprite)) {

			
			hitdef.setTargetId(sprite.getSpriteId());

			Sprite spriteHitter = GameFight.getInstance().getSpriteInstance(
					hitdef.getSpriteId());

			// TODO Make a class for shaking

			try {
				playSoundGuard(hitdef, sprite);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (spriteHitter != null) // It's a helper
				spriteHitter.setPause(hitdef.getGuard().getPausetime().getP1_pausetime());

			long currentTime = GameFight.getInstance().getGameState().getGameTime();
			hitdef.setLastTimeBlockBySomething(currentTime);
			hitdef.setLastTimeHitSomething(currentTime);
			
			sprite.getInfo().setLastHitdef(hitdef);
			setProjectileBlockBySpriteState(hitdef, sprite);
			
			sprite.getInfo().getShake().setAmpl(2);
			sprite.getInfo().getShake().setTime(
					hitdef.getGuard().getPausetime().getP2_shaketime());
			sprite.setPause(hitdef.getGuard().getPausetime().getP2_shaketime());

			
			// TODO Projectile Life
			if (hitdef.getProjremove() != 0) {
				removes.add(hitdef);
				hitdef.getSpriteHitter().setProjHitSprite();

			}
			hitdef.addProjhits(-1);
			if (hitdef.getProjhits() <= 0) {
				removes.add(hitdef);
			}
//			drawSparkHit(hitdef, sprite, wrap.getValue());
			drawSparkHit(hitdef, sprite);
		}
	}

	//
	private void processProjectileVsSpriteHittedMode(ProjectileSub hitdef,
			Sprite sprite, Set<HitDefSub> removes, Set<HitDefSub> notProcess) {
		if (hasAttackRect(sprite)) {
			processProjectileVsAttackRec(hitdef, sprite, removes, notProcess);
		} else {
			processProjectileVsCollisionRec(hitdef, sprite, removes, notProcess);
		}
	}

	private void processProjectileVsAttackRec(ProjectileSub hitdef,
			Sprite sprite, Set<HitDefSub> removes, Set<HitDefSub> notProcess) {
		
		Wrapper<Rectangle> wrap = new Wrapper<Rectangle>();

		if (isHitdefHitAttackRec(hitdef, sprite, wrap)) {
			// TODO
		}
	}

	private void processProjectileVsCollisionRec(ProjectileSub hitdef,
			Sprite sprite, Set<HitDefSub> removes, Set<HitDefSub> notProcess) {

//		Wrapper<Rectangle> wrap = new Wrapper<Rectangle>();
		if (isHitdefHitCollisionRec(hitdef, sprite)) {
			long time = GameFight.getInstance().getGameState().getGameTime();
			hitdef.setLastTimeHitSomething(time);
			
			hitdef.setTargetId(sprite.getSpriteId());

			Sprite spriteHitter = GameFight.getInstance().getSpriteInstance(
					hitdef.getSpriteId());

			// TODO Make a class for shaking

			try {
				playSoundHit(hitdef, sprite);
				
			} catch (Exception e) {
				e.printStackTrace();
			}


			sprite.getInfo().addLife(-hitdef.getDamage().getHit_damage());
			sprite.getInfo().setLastHitdef(hitdef);
			
			setProjectileHitSpriteState(hitdef, sprite, removes, notProcess);
			// TODO Projectile Life
			if (hitdef.getProjremove() != 0) {
				removes.add(hitdef);
				hitdef.getSpriteHitter().setProjHitSprite();

			}
			Projectile.setHitedFor(hitdef.getProjid());
			hitdef.addProjhits(-1);
			if (hitdef.getProjhits() <= 0) {
				removes.add(hitdef);
			}
			
			if (spriteHitter != null) // It's a helper
				spriteHitter.setPause(hitdef.getPausetime().getP1_pausetime());

			sprite.getInfo().getShake().setAmpl(2);
			sprite.getInfo().getShake().setTime(
					hitdef.getPausetime().getP2_shaketime());
			sprite.setPause(hitdef.getPausetime().getP2_shaketime());
			
			drawSparkHit(hitdef, sprite);
		}
	}

	// //////////////////////////////////////
	// Set Sprites states
	// /////////////////////////////////////

	/**
	 * Set Sprite, Sprite state
	 */
	private void setSpriteHitSpriteState(HitDefSub hitdef, Sprite sprite, Set<HitDefSub> removes, Set<HitDefSub> notProcess) {

		if (hitdef instanceof ProjectileSub)
			throw new IllegalArgumentException("Only Hitdef");
		Sprite sprHitter = (Sprite) hitdef.getSpriteHitter();

		if (hitdef.getHitonce() == 1) {
			removes.add(hitdef);
			notProcess.add(hitdef);
		}
		
		if (hitdef.getSnap() != null) {
			int mul = sprite.isFlip()? -1: 1;
			sprite.getInfo().setXPos((float) (hitdef.getSpriteHitter().getRealXPos() - hitdef.getSnap().getX() * mul));
			sprite.getInfo().setYPos((float) (hitdef.getSpriteHitter().getRealYPos() + hitdef.getSnap().getY()));
		}
		if (hitdef.getForcestand() == 1) {
			sprite.getInfo().setType(Type.S);
		}
		if (hitdef.getP2stateno() != null) {
			if (hitdef.getP2getp1state() == 1) {
				sprite.getInfo().setCtrl(0);
				sprite.getSpriteState().targetState(sprHitter.getSpriteId(),
						hitdef.getP2stateno() + "");
			} else {
				sprite.getInfo().setCtrl(0);
				sprite.getSpriteState().changeStateDef(hitdef.getP2stateno());
				
			}

		} else if (sprite.getInfo().isHitOverride(hitdef.getAttr())) {
			HitOverrideSub ho = sprite.getInfo().getHitOverride(hitdef.getAttr());
			int stateno = ho.getStateno();
			if (stateno != -1) {
				if (ho.getForceair() != 0) {
					sprite.getInfo().setType(Type.A);
				}
				sprite.getInfo().setCtrl(0);
				sprite.getSpriteState().changeStateDef(stateno);
			}
			
			
		} else if (!hitdef.getAttr().isAttrType(AttrType.T) || hitdef.getP2stateno() == null) {
			if (sprite.getInfo().getType() == Type.S) {
				if (hitdef.getGround().getType() == org.lee.mugen.sprite.character.spiteCnsSubClass.HitDefSub.Type.TRIP) {
					sprite.getSpriteState().changeStateDef(5070);
					sprite.getInfo().setCtrl(0);
				} else {
					sprite.getSpriteState().changeStateDef(5000);
					sprite.getInfo().setCtrl(0);
				}
			} else if (sprite.getInfo().getType() == Type.C) {
				if (hitdef.getGround().getType() == org.lee.mugen.sprite.character.spiteCnsSubClass.HitDefSub.Type.TRIP) {
					sprite.getSpriteState().changeStateDef(5070);
					sprite.getInfo().setCtrl(0);
				} else {
					sprite.getSpriteState().changeStateDef(5010);	
					sprite.getInfo().setCtrl(0);
				}
			} else if (sprite.getInfo().getType().getBit() == Type.A.getBit()) {
				if (hitdef.getGround().getType() == org.lee.mugen.sprite.character.spiteCnsSubClass.HitDefSub.Type.TRIP) {
					sprite.getSpriteState().changeStateDef(5070);
					sprite.getInfo().setCtrl(0);
				} else {
					sprite.getSpriteState().changeStateDef(5020);	
					sprite.getInfo().setCtrl(0);
				}
			}
		}
		if (hitdef.getP1stateno() != null) {
			sprHitter.getSpriteState().changeStateDef(hitdef.getP1stateno());

		}
		if (hitdef.getPalfx().getTime() > 0) {
			sprite.getPalfx().init();
			sprite.setPalfx(hitdef.getPalfx());
		}
		if (hitdef.getP1getp2facing() != 0) {
			if (hitdef.getP1getp2facing() == 1)
				sprHitter.getInfo().setFlip(sprHitter.getInfo().isFlip());
			else if (hitdef.getP1getp2facing() == -1)
				sprHitter.getInfo().setFlip(!sprHitter.getInfo().isFlip());
				
		} else {
			if (hitdef.getP1facing() == -1) {
				sprHitter.getInfo().setFlip(!sprHitter.getInfo().isFlip());
			} 
			if (hitdef.getP2facing() == -1) {
				sprite.getInfo().setFlip(sprHitter.isFlip());
			}
			if (hitdef.getP2facing() == 1) {
				sprite.getInfo().setFlip(!sprHitter.isFlip());
			}
			if (hitdef.getP2facing() == -1) {
				sprite.getInfo().setFlip(sprHitter.isFlip());
			}
			
		}

	}

	/**
	 * Set Sprite, Sprite state
	 */
	private void setSpriteBlockBySpriteState(HitDefSub hitdef, Sprite sprite) {
		if (hitdef instanceof ProjectileSub)
			throw new IllegalArgumentException("Only Hitdef");
		Sprite sprHitted = sprite;

		hitdef.setLastTimeBlockBySomething(GameFight.getInstance().getGameState().getGameTime());
		if (sprHitted.getInfo().getType() == Type.C) {
			sprHitted.getSpriteState().changeStateDef(152);

		} else if (sprHitted.getInfo().getType() == Type.S) {
			sprHitted.getSpriteState().changeStateDef(150);
		}

	}

	/**
	 * Set Sprite, Sprite state
	 */
	private void setProjectileBlockBySpriteState(ProjectileSub hitdef,
			Sprite sprite) {
		Sprite sprHitted = sprite;

		if (sprHitted.getInfo().getType() == Type.C) {
			sprHitted.getSpriteState().changeStateDef(152);

		} else if (sprHitted.getInfo().getType() == Type.S) {
			sprHitted.getSpriteState().changeStateDef(150);
		}

	}

	/**
	 * Set Projectile, Sprite
	 * 
	 * @param hitdef
	 * @param sprite
	 */
	private void setProjectileHitSpriteState(ProjectileSub hitdef, Sprite sprite, Set<HitDefSub> removes, Set<HitDefSub> notProcess) {

		if (hitdef.getClass() != ProjectileSub.class)
			throw new IllegalArgumentException("Only Hitdef");
		Sprite sprHitter = (Sprite) hitdef.getSpriteParent();

		if (hitdef.getHitonce() == 1) {
			removes.add(hitdef);
			notProcess.add(hitdef);
		}
		
		if (hitdef.getSnap() != null) {
			sprite.getInfo().setXPos((float) (hitdef.getSpriteHitter().getRealXPos() + hitdef.getSnap().getX()));
			sprite.getInfo().setYPos((float) (hitdef.getSpriteHitter().getRealYPos() + hitdef.getSnap().getY()));
		}
		if (hitdef.getForcestand() == 1) {
			sprite.getInfo().setType(Type.S);
		}
		if (hitdef.getP2stateno() != null) {
			if (hitdef.getP2getp1state() == 1) {
				sprite.getInfo().setCtrl(0);
				sprite.getSpriteState().targetState(sprHitter.getSpriteId(),
						hitdef.getP2stateno() + "");
			} else {
				sprite.getInfo().setCtrl(0);
				sprite.getSpriteState().changeStateDef(hitdef.getP2stateno());
				
			}

		} else if (sprite.getInfo().isHitOverride(hitdef.getAttr())) {
			HitOverrideSub ho = sprite.getInfo().getHitOverride(hitdef.getAttr());
			int stateno = ho.getStateno();
			if (stateno != -1) {
				if (ho.getForceair() != 0) {
					sprite.getInfo().setType(Type.A);
				}
				sprite.getInfo().setCtrl(0);
				sprite.getSpriteState().changeStateDef(stateno);
			}
			
			
		} else if (!hitdef.getAttr().isAttrType(AttrType.T) || hitdef.getP2stateno() == null) {
			if (sprite.getInfo().getType() == Type.S) {
				if (hitdef.getGround().getType() == org.lee.mugen.sprite.character.spiteCnsSubClass.HitDefSub.Type.TRIP) {
					sprite.getSpriteState().changeStateDef(5070);
					sprite.getInfo().setCtrl(0);
				} else {
					sprite.getSpriteState().changeStateDef(5000);
					sprite.getInfo().setCtrl(0);
				}
			} else if (sprite.getInfo().getType() == Type.C) {
				if (hitdef.getGround().getType() == org.lee.mugen.sprite.character.spiteCnsSubClass.HitDefSub.Type.TRIP) {
					sprite.getSpriteState().changeStateDef(5070);
					sprite.getInfo().setCtrl(0);
				} else {
					sprite.getSpriteState().changeStateDef(5010);	
					sprite.getInfo().setCtrl(0);
				}
			} else if (sprite.getInfo().getType().getBit() == Type.A.getBit()) {
				if (hitdef.getGround().getType() == org.lee.mugen.sprite.character.spiteCnsSubClass.HitDefSub.Type.TRIP) {
					sprite.getSpriteState().changeStateDef(5070);
					sprite.getInfo().setCtrl(0);
				} else {
					sprite.getSpriteState().changeStateDef(5020);	
					sprite.getInfo().setCtrl(0);
				}
			}
		}
		if (hitdef.getP1stateno() != null) {
			sprHitter.getSpriteState().changeStateDef(hitdef.getP1stateno());

		}
		if (hitdef.getPalfx().getTime() > 0) {
			sprite.getPalfx().init();
			sprite.setPalfx(hitdef.getPalfx());
		}
		if (hitdef.getP1getp2facing() != 0) {
			if (hitdef.getP1getp2facing() == 1)
				sprHitter.getInfo().setFlip(sprHitter.getInfo().isFlip());
			else if (hitdef.getP1getp2facing() == -1)
				sprHitter.getInfo().setFlip(!sprHitter.getInfo().isFlip());
				
		} else {
			if (hitdef.getP1facing() == -1) {
				sprHitter.getInfo().setFlip(!sprHitter.getInfo().isFlip());
			} 
			if (hitdef.getP2facing() == -1) {
				sprite.getInfo().setFlip(sprHitter.isFlip());
			}
			if (hitdef.getP2facing() == 1) {
				sprite.getInfo().setFlip(!sprHitter.isFlip());
			}
			if (hitdef.getP2facing() == -1) {
				sprite.getInfo().setFlip(sprHitter.isFlip());
			}
			
		}

	}

	/**
	 * Set Projectile, Projectile
	 * 
	 * @param hitdef
	 * @param sprite
	 */
	private void setProjectileHitProjectileState(ProjectileSub hitdef,
			ProjectileSprite sprite) {
		// TODO
	}

	// ///////////////////////////////////
	// Sound
	// ////////////////////////////////////
	private static void playSoundHit(HitDefSub hitdef, AbstractSprite sprite) {
		if (hitdef.getHitsound().getSnd_grp() < 0)
			return;
		if (hitdef.getHitsound().isPlaySpriteSnd()) {
			AbstractSprite aspr = hitdef.getSpriteHitter();
			Sprite spriteHitter = null;
			if (aspr instanceof ProjectileSprite) {
				ProjectileSprite pspr = (ProjectileSprite) aspr;
				spriteHitter = pspr.getProjectileSub().getSpriteParent();
			} else {
				spriteHitter = (Sprite) aspr;
			}
			try {
				SoundSystem.Sfx.playSnd(spriteHitter.getSpriteSnd().getGroup(
						hitdef.getHitsound().getSnd_grp()).getSound(
						hitdef.getHitsound().getSnd_item()),
						new AtomicBoolean(true));
			} catch (Exception e) {
				System.err.println("Sound Error " 
						+ hitdef.getHitsound().isPlaySpriteSnd() + " "
						+ hitdef.getHitsound().getSnd_grp() + " "
						+ hitdef.getHitsound().getSnd_item());
			}


		} else {
			Snd commonSnd = GameFight.getInstance().getFightdef().getFiles().getCommon().getSnd();
			try {
				SoundSystem.Sfx.playSnd(commonSnd.getGroup(
						hitdef.getHitsound().getSnd_grp()).getSound(
						hitdef.getHitsound().getSnd_item()), new AtomicBoolean(
						true));
			} catch (Exception e) {
				System.err.println("Sound Error " 
						+ hitdef.getHitsound().isPlaySpriteSnd() + " "
						+ hitdef.getHitsound().getSnd_grp() + " "
						+ hitdef.getHitsound().getSnd_item());
			}

		}
	}

	private static void playSoundGuard(HitDefSub hitdef, AbstractSprite sprite) {
		if (hitdef.getGuardsound().getSnd_grp() < 0)
			return;
		if (hitdef.getHitsound().isPlaySpriteSnd()) {
			Sprite spriteHitter = GameFight.getInstance().getSpriteInstance(
					hitdef.getSpriteId());
			SoundSystem.Sfx.playSnd(spriteHitter.getSpriteSnd().getGroup(
					hitdef.getGuardsound().getSnd_grp()).getSound(
					hitdef.getGuardsound().getSnd_item()), new AtomicBoolean(
					true));
		} else {
			Snd commonSnd = GameFight.getInstance().getFightdef().getFiles().getCommon().getSnd();
			
			try {
				SoundSystem.Sfx.playSnd(commonSnd.getGroup(
						hitdef.getGuardsound().getSnd_grp()).getSound(
						hitdef.getGuardsound().getSnd_item()),
						new AtomicBoolean(true));
			} catch (Exception e) {
				System.err.println("Sound Error " 
						+ hitdef.getHitsound().isPlaySpriteSnd() + " "
						+ hitdef.getHitsound().getSnd_grp() + " "
						+ hitdef.getHitsound().getSnd_item());
			}
		}
	}
	private void drawSparkHit(final HitDefSub hitdef, Sprite sprite) {
		drawSparkHit(hitdef, sprite, null);
	}
	private void drawSparkHit(final HitDefSub hitdef, Sprite sprite, Rectangle union) {
		if (hitdef.getAttr().isAttrType(AttrType.T))
			return;
		if (hitdef.getSparkno() == null)// && hitdef.getSparkno().getAction() < 0)
			return;
		AbstractSprite sprToRender = null;
		try {
			sprToRender = SparkRender.SparkRenderFactory.getInstanceOfSparkSpriteFor(hitdef, sprite, union);
			
		} catch (Exception e) {
			return;
			// TODO: handle exception
		}
		
		
		GameFight.getInstance().addRender(new SparkhitRender(sprToRender));
		GameFight.getInstance().getOtherSprites().add(sprToRender);

	}

	// /////////////////////////////////////
	// process position
	// ///////////////////////////////////

	public static Sprite getNearestEnnemies(Sprite spr) {
		Collection<Sprite> sprites = GameFight.getInstance().getEnnmies(spr);
		Sprite theNearest = null;
		float dist = 0;
		for (Sprite s: sprites) {
			if (s instanceof SpriteHelper)
				continue;
			if (theNearest == null) {
				theNearest = s;
				dist = Math.abs(spr.getInfo().getXPos() - theNearest.getInfo().getXPos());
			}
			
			float distToCmp = Math.abs(spr.getInfo().getXPos() - s.getInfo().getXPos());
			if (distToCmp < dist)
				theNearest = s;
			
		}
		assert theNearest != null;
		return theNearest;
	}
	
	private static void processPosition(Sprite one, Sprite two) {
		boolean isTempFlip = one.getInfo().getXPos() > two.getInfo().getXPos();
		if (!GameFight.getInstance().getGlobalEvents().isAssertSpecial(one.getSpriteId(), Assertspecial.Flag.noautoturn))
		if (
				one.getInfo().getCtrl() == 1
				&& 
				one.getInfo().getMovetype() == MoveType.I
				&& (one.getInfo().getPhysics() == SpriteCns.Physics.S || one
						.getInfo().getPhysics() == SpriteCns.Physics.C)) {
			if (one.getSprAnimMng().getAction() == 0 && one.getInfo().isFlip() != isTempFlip
					&& (one.getSprAnimMng().getAction() != 5 && one
							.getSprAnimMng().getAction() != 6)) {
				if (one.getInfo().getPhysics() == SpriteCns.Physics.S) {
					if (one.getInfo().getMovetype() == MoveType.I)
						one.getInfo().setFlip(isTempFlip);
					one.getSprAnimMng().setAction(5);
				} else if (one.getInfo().getPhysics() == SpriteCns.Physics.C) {
					if (one.getInfo().getMovetype() == MoveType.I)
						one.getInfo().setFlip(isTempFlip);
					one.getSprAnimMng().setAction(6);
				}

			} else {
				if (isTempFlip != one.getInfo().isFlip())
					one.getInfo().setFlip(isTempFlip);
			}



		}
		if (!GameFight.getInstance().getGlobalEvents().isAssertSpecial(two.getSpriteId(), Assertspecial.Flag.noautoturn))
		if (
				two.getInfo().getCtrl() == 1
				&& 
				two.getInfo().getMovetype() == MoveType.I
				&& (two.getInfo().getPhysics() == SpriteCns.Physics.S || two
						.getInfo().getPhysics() == SpriteCns.Physics.C)) {
			if (two.getSprAnimMng().getAction() == 0 && two.getInfo().isFlip() != !isTempFlip
					&& (two.getSprAnimMng().getAction() != 5 && two
							.getSprAnimMng().getAction() != 6)) {
				if (two.getInfo().getPhysics() == SpriteCns.Physics.S) {
					two.getSprAnimMng().setAction(5); // 5 stand crounch
				} else if (two.getInfo().getPhysics() == SpriteCns.Physics.C) {
					two.getSprAnimMng().setAction(6); // 6 crounch
				}
			}
			if (two.getInfo().getMovetype() == MoveType.I) {
				if (!isTempFlip != two.getInfo().isFlip())
					two.getInfo().setFlip(!isTempFlip);
			}
		}
	}
		
	
	public void processPosition() {
		Sprite one = null;
		Sprite two = null;
		for (Sprite s : GameFight.getInstance().getSprites()) {
			if (s instanceof SpriteHelper)
				continue;
			if (one == null) {
				one = s;
				two = getNearestEnnemies(one);
				processPosition(one, two);
			}
		}

		
	}

	// /////////////////////////////////////////////
	// Others utils Function
	// ////////////////////////////////////////////
	public static float getXDistatnce(AbstractSprite sprOne,
			AbstractSprite sprTwo) {
		return (float) Point.distance(sprOne.getRealXPos(), 0, sprTwo
				.getRealXPos(), 0);

	}

	public static float getDistatnce(AbstractSprite sprOne,
			AbstractSprite sprTwo) {
		return (float) Point.distance(sprOne.getRealXPos(), sprOne.getRealYPos(), sprTwo
				.getRealXPos(), sprTwo.getRealYPos());

	}
	private static void attemptToProtect(HitDefSub hitdef, Sprite sprTwo) {
		int stateTwo = sprTwo.getSpriteState().getCurrentState().getIntId();
		if (stateTwo >= 120 && stateTwo <= 155 && stateTwo == 140)
			return;
		if (sprTwo.getInfo().getMovetype() != MoveType.H) {
//			Wrapper<Rectangle> wrap = new Wrapper<Rectangle>();

			if (isHitdefHitCollisionRec(hitdef, sprTwo) //{
				 || getXDistatnce(hitdef.getSpriteHitter(), sprTwo) <= 160)
				// {//sprTwo.getInfo().getSize().getProj().getAttack().getDist())
				 {

				if (hasAttackRect(hitdef.getSpriteHitter())) {
					if (sprTwo.getInfo().getCommand("\"holdback\"") == 1 
							&& sprTwo.getInfo().getCtrl() == 1
							&& (sprTwo.getInfo().getLastHitdef() == null 
									|| sprTwo.getInfo().getLastHitdef().getHittime() <= 0)
							&& (stateTwo < 120 || stateTwo > 155)
							&& sprTwo.getInfo().getMovetype() != MoveType.A) 
//							&& sprTwo.getInfo().getMovetype() != MoveType.H
//							&& sprTwo.getSpriteState().getTimeInState() > 2)
						sprTwo.getSpriteState().changeStateDef(120);
				}
			} else if (!hasAttackRect(hitdef.getSpriteHitter())
					&& (stateTwo >= 120 && stateTwo <= 155 && stateTwo != 140)) {
				sprTwo.getSpriteState().changeStateDef(140);

			}

		}
	}

	public static boolean isBlockState(Sprite spr) {
		return isBlockState(spr.getSpriteState().getCurrentState().getIntId());
	}

	public static boolean isBlockState(int state) {
		return state >= 120 && state <= 155;
	}

	public static boolean isHitdefHitCollisionRec(HitDefSub hitdef,
			AbstractSprite sprite) {
		return isHitdefHitCollisionRec(hitdef, sprite, null);
	}

	public static boolean isHitdefHitCollisionRec(HitDefSub hitdef,
			AbstractSprite sprite, Wrap<Rectangle> outUnion) {
		boolean isTouch = false;
		List<Rectangle> rectangles = hitdef.getSpriteHitter().getCns1();
		for (Rectangle rAttack : rectangles) {
			for (Rectangle rCln2 : sprite.getCns2()) {
				if (rAttack.intersects(rCln2)) {
					if (outUnion != null) {
						outUnion.setValue(rAttack.intersection(rCln2));
					}
					isTouch = true;
					break;
				}
			}
		}
		return isTouch;
	}
	public static boolean isHitdefHitAttackRec(HitDefSub hitdef,
			AbstractSprite sprite) {
		return isHitdefHitAttackRec(hitdef, sprite, null);
	}

	public static boolean isHitdefHitAttackRec(HitDefSub hitdef,
			AbstractSprite sprite, Wrap<Rectangle> outUnion) {
		boolean isTouch = false;
		for (Rectangle rAttack : hitdef.getSpriteHitter().getCns1()) {
			for (Rectangle rCln1 : sprite.getCns1()) {
				if (rAttack.intersects(rCln1)) {
					if (outUnion != null) {
						outUnion.setValue(rAttack.intersection(rCln1));
					}

					isTouch = true;
					break;
				}
			}
		}
		return isTouch;
	}

	public static boolean hasAttackRect(AbstractSprite sprite) {
		if (sprite.getSprAnimMng().getCurrentImageSprite() == null)
			return false;
		org.lee.mugen.object.Rectangle[] r = sprite.getSprAnimMng()
				.getCurrentImageSprite().getAtacksRec();
		return r != null && r.length > 0;
	}

	private static boolean canOneHitsTwo(int hittedType, int hittedGuard,
			Sprite hitted) {

		if (isMatch(HitDefSub.HitFlag.M.bit, hittedType)
				&& (hitted.getInfo().getType() == Type.S || hitted.getInfo().getType() == Type.C)) {
			return canOneHitsTwo(hittedGuard, hitted);
		} else if (isMatch(HitDefSub.HitFlag.H.bit, hittedType)
				&& hitted.getInfo().getType() == Type.S) {
			return canOneHitsTwo(hittedGuard, hitted);
		} else if (isMatch(HitDefSub.HitFlag.L.bit, hittedType)
				&& hitted.getInfo().getType() == Type.C) {
			return canOneHitsTwo(hittedGuard, hitted);
		} else if (isMatch(HitDefSub.HitFlag.A.bit, hittedType)
				&& hitted.getInfo().getType() == Type.A) {
			return canOneHitsTwo(hittedGuard, hitted);
		} else if (isMatch(HitDefSub.HitFlag.F.bit, hittedType)
				&& hitted.getInfo().getType() == Type.A
				&& hitted.getInfo().getMovetype() == MoveType.H) {
			return canOneHitsTwo(hittedGuard, hitted);
		} else if (isMatch(HitDefSub.HitFlag.D.bit, hittedType)
				&& hitted.getInfo().getType() == Type.L) {
			return canOneHitsTwo(hittedGuard, hitted);
		}

		return false;
	}

	private static boolean canOneHitsTwo(int hittedGuard, Sprite hitted) {
		boolean isBlock = isBlockState(hitted.getSpriteState()
				.getCurrentState().getIntId());

		if (isMatch(HitDefSub.GuardFlag.M.bit, hittedGuard)
				&& (hitted.getInfo().getType() == Type.S || hitted.getInfo()
						.getType() == Type.C)) {
			return !isBlock;
		} else if (isMatch(HitDefSub.GuardFlag.H.bit, hittedGuard)
				&& hitted.getInfo().getType() == Type.S) {
			return !isBlock;
		} else if (isMatch(HitDefSub.GuardFlag.L.bit, hittedGuard)
				&& hitted.getInfo().getType() == Type.C) {
			return !isBlock;
		} else if (isMatch(HitDefSub.GuardFlag.A.bit, hittedGuard)
				&& hitted.getInfo().getType() == Type.A) {
			return !isBlock;
		}
		return true;
	}

	private static boolean isMatch(int constBit, int bit) {
		return (constBit & bit) == constBit;
	}

	public void remove(HitDefSub hitdef) {
		hitdefs.remove(hitdef);
	}

	public HitDefSub getFirstHitdefBySpriteHitter(String spriteHitterId) {
		for (HitDefSub hitdef : hitdefs)
			if (hitdef.getSpriteId().equals(spriteHitterId))
				return hitdef;
		return null;
	}

	public LinkedList<HitDefSub> getHitdefBySpriteHitter(String spriteHitterId) {
		LinkedList<HitDefSub> result = new LinkedList<HitDefSub>();
		for (HitDefSub hitdef : hitdefs)
			if (hitdef.getSpriteId().equals(spriteHitterId))
				result.add(hitdef);
		return result;
	}

	public Sprite getTarget(Sprite sprite, int id) {
		String sid = String.valueOf(id);
		if (id >= 0) {
			HitDefSub hit = null;
			for (HitDefSub hitdef: hitdefs) {
				if (hitdef instanceof ProjectileSub) {
					ProjectileSub p = (ProjectileSub) hitdef;
					Sprite s = p.getSpriteHitter().getProjectileSub().getSpriteParent();
					if (s.getSpriteId().equals(sprite.getSpriteId()) && hitdef.getId().equals(sid)) {
						hit = hitdef;
						break;
					}
				} else {
					if (hitdef.getSpriteId().equals(sprite.getSpriteId()) && hitdef.getId().equals(sid)) {
						hit = hitdef;
						break;
					}
					
				}
			}
			if (hit != null) {
				for (Sprite s: GameFight.getInstance().getSprites()) {
					if (s.getInfo().getLastHitdef() != null && s.getInfo().getLastHitdef().equals(hit))
						return s;
				}
			}
		} else {
			HitDefSub hit = null;
			for (HitDefSub hitdef: hitdefs) {
				if (hitdef instanceof ProjectileSub) {
					ProjectileSub p = (ProjectileSub) hitdef;
					Sprite s = p.getSpriteHitter().getProjectileSub().getSpriteParent();
					if (s.getSpriteId().equals(sprite.getSpriteId())) {
						hit = hitdef;
						break;
					}
				} else {
					if (hitdef.getSpriteId().equals(sprite.getSpriteId())) {
						hit = hitdef;
						break;
					}
					
				}
			}
			if (hit != null) {
				for (Sprite s: GameFight.getInstance().getSprites()) {
					if (s.getInfo().getLastHitdef() != null && s.getInfo().getLastHitdef().equals(hit))
						return s;
				}
			} else {
				for (Sprite s: GameFight.getInstance().getEnnmies(sprite)) {
					if (s.getInfo().getLastHitdef() != null && s.getInfo().getLastHitdef().getSpriteHitter().equals(sprite))
						return s;
				}
			}
		}
		for (Sprite s: GameFight.getInstance().getSprites()) {
			if (s.getInfo().getLastHitdef() == null)
				continue;
			AbstractSprite sprToCompare = s.getInfo().getLastHitdef().getSpriteHitter();
			if (sprToCompare instanceof ProjectileSprite) {
				ProjectileSprite p = (ProjectileSprite) sprToCompare;
				sprToCompare = p.getProjectileSub().getSpriteParent();
			}
			if (sprToCompare instanceof SpriteHelper)
				sprToCompare = GameFight.getInstance().getRoot((Sprite) sprToCompare);
				
			if (s.getInfo().getLastHitdef() != null && !s.equals(sprite) && sprToCompare.equals(sprite))
				return s;
		}
		return null;
	}

	public int getTargetsCount(Sprite sprite, int id) {
		String sid = String.valueOf(id);
		int count = 0;
		if (id >= 0) {
			HitDefSub hit = null;
			for (HitDefSub hitdef: hitdefs) {
				if (hitdef instanceof ProjectileSub) {
					ProjectileSub p = (ProjectileSub) hitdef;
					Sprite s = p.getSpriteHitter().getProjectileSub().getSpriteParent();
					if (s.getSpriteId().equals(sprite.getSpriteId()) && hitdef.getId().equals(sid)) {
						hit = hitdef;
						break;
					}
				} else {
					if (hitdef.getId() != null && hitdef.getSpriteId().equals(sprite.getSpriteId()) && hitdef.getId().equals(sid)) {
						hit = hitdef;
						break;
					}
					
				}
			}
			if (hit != null) {
				for (Sprite s: GameFight.getInstance().getSprites()) {
					if (s.getInfo().getLastHitdef() != null && s.getInfo().getLastHitdef().equals(hit))
						count++;
				}
			}
		} else {
			HitDefSub hit = null;
			for (HitDefSub hitdef: hitdefs) {
				if (hitdef instanceof ProjectileSub) {
					ProjectileSub p = (ProjectileSub) hitdef;
					Sprite s = p.getSpriteHitter().getProjectileSub().getSpriteParent();
					if (s.getSpriteId().equals(sprite.getSpriteId())) {
						hit = hitdef;
						break;
					}
				} else {
					if (hitdef.getSpriteId().equals(sprite.getSpriteId())) {
						hit = hitdef;
						break;
					}
					
				}
			}
			if (hit != null) {
				for (Sprite s: GameFight.getInstance().getSprites()) {
					if (s.getInfo().getLastHitdef() != null && s.getInfo().getLastHitdef().equals(hit))
						count++;
				}
			} else {
				for (Sprite s: GameFight.getInstance().getEnnmies(sprite)) {
					if (s.getInfo().getLastHitdef() != null && s.getInfo().getLastHitdef().getSpriteHitter().equals(sprite))
						count++;
				}
			}
		}
		return count;
	}

	public Sprite getEnemynear(Sprite sprite, Integer id) {
		float dist = 0;
		Sprite enemy = null;
		for (Sprite s: GameFight.getInstance().getEnnmies(sprite)) {
			if (s instanceof SpriteHelper &&((SpriteHelper)s).getHelperSub().getHelpertype().equals("normal"))
				continue;
			float tempDist = getDistatnce(sprite, s);
			if (enemy == null) {
				dist = tempDist;
				enemy = s;
			}
			if (tempDist < dist) {
				enemy = s;
			}
		}

		return enemy;
	}

	public List<ProjectileSub> getProjectiles() {
		return getProjectiles(null);
	}
	public List<ProjectileSub> getProjectiles(Integer id) {
		List<ProjectileSub> projectiles = new ArrayList<ProjectileSub>();
		if (id != null && id > 0) {
			for (HitDefSub hitdef: hitdefs) {
				if (hitdef instanceof ProjectileSub) {
					ProjectileSub sub = (ProjectileSub) hitdef;
					if (sub.getProjid() != null && sub.getProjid() == id.intValue()) {
						projectiles.add((ProjectileSub) hitdef);
					}
				}
			}
		} else {
			for (HitDefSub hitdef: hitdefs) {
				if (hitdef instanceof ProjectileSub)
					projectiles.add((ProjectileSub) hitdef);
			}
		}
		for (Sprite s: GameFight.getInstance().getSprites()) {
			if (s.getClass() == Sprite.class) {
				if (s.getInfo().getLastHitdef() != null && s.getInfo().getLastHitdef() instanceof ProjectileSub) {
					if (!projectiles.contains(s.getInfo().getLastHitdef())) {
						ProjectileSub sub = (ProjectileSub) s.getInfo().getLastHitdef();
						if (id != null && id > 0) {
							if (sub.getProjid() != null && sub.getProjid() == id.intValue()) {
								projectiles.add((ProjectileSub) sub);
							}
						} else {
							projectiles.add((ProjectileSub) sub);
						}
					}
				}
			}
		}
		return projectiles;
	}
	
}
