package org.lee.mugen.core.renderer.game;

import java.awt.Rectangle;

import org.lee.mugen.core.FightEngine;
import org.lee.mugen.core.GameFight;
import org.lee.mugen.fight.section.Fightfx;
import org.lee.mugen.sprite.base.AbstractSprite;
import org.lee.mugen.sprite.baseForParse.SpriteSFF;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.character.SpriteAnimManager;
import org.lee.mugen.sprite.character.spiteCnsSubClass.HitDefSub;
import org.lee.mugen.sprite.entity.ProjectileSprite;
import org.lee.mugen.sprite.entity.SuperpauseSub;

public class SparkRender {

	public static class SparkRenderFactory {
		// TODO FIXME
		private static SparkRenderFactory instance = new SparkRenderFactory();
		
		
		public static AbstractSprite getInstanceOfSparkSpriteFor(HitDefSub hitdef, Sprite spriteHitted) {
			return getInstanceOfSparkSpriteFor(hitdef, spriteHitted, null);
		}
		private static Fightfx getFightfx() {
			return GameFight.getInstance().getFightdef().getFiles().getFightfx();
		}
		public static AbstractSprite getInstanceOfSparkSpriteFor(HitDefSub hitdef, Sprite spriteHitted, final Rectangle union) {
			if (instance == null)
				throw new IllegalStateException("You have to init this before");

			final boolean isBlocked = FightEngine.isBlockState(spriteHitted);
			Sprite spriteLaunchHit = GameFight.getInstance().getSpriteInstance(hitdef.getSpriteId());
			
			final boolean isFlip = spriteLaunchHit.isFlip();
			
			final float xReal;
			final float yReal;
			
			final SpriteSFF sprSFF;
			
			
			final SpriteAnimManager spriteAnimManager = new SpriteAnimManager("");

			if (isBlocked) {
				if (hitdef.getGuard().getSparkno() != null && hitdef.getGuard().getSparkno().isSpriteUse()) {
					spriteAnimManager.setGroupSpriteMap(spriteLaunchHit.getSprAnimMng().getGroupSpriteMap());
					sprSFF = spriteLaunchHit.getSpriteSFF();
					
					spriteAnimManager.setAction(hitdef.getGuard().getSparkno().getAction());
				} else if (hitdef.getGuard() != null) {
					spriteAnimManager.setGroupSpriteMap(getFightfx().getAir().getGroupSpriteMap());
					sprSFF = getFightfx().getSff();
					
					spriteAnimManager.setAction(hitdef.getGuard().getSparkno().getAction());
					
				} else {
					sprSFF = null;
					System.err.println("pas de hit spark block");
				}
				
			} else {
				if (hitdef.getSparkno() != null && hitdef.getSparkno().isSpriteUse()) {
					spriteAnimManager.setGroupSpriteMap(spriteLaunchHit.getSprAnimMng().getGroupSpriteMap());
					sprSFF = spriteLaunchHit.getSpriteSFF();
					
					spriteAnimManager.setAction(hitdef.getSparkno().getAction());
				} else if (hitdef.getSparkno() != null) {
					spriteAnimManager.setGroupSpriteMap(getFightfx().getAir().getGroupSpriteMap());
					sprSFF = getFightfx().getSff();
					
					spriteAnimManager.setAction(hitdef.getSparkno().getAction());
					
				} else {
					sprSFF = null;
					System.err.println("pas de hit spark");
				}
			}
			
			xReal = spriteHitted.getRealXPos() + ((isFlip ^ spriteLaunchHit.getSprAnimMng().getCurrentImageSprite().isMirrorH()? -1:1) * hitdef.getSparkxy().getSpark_x());
		
			
			if (hitdef.getSpriteHitter() instanceof ProjectileSprite) {
				yReal = hitdef.getSpriteHitter().getRealYPos() + hitdef.getSparkxy().getSpark_y();
//				yReal = spriteHitted.getRealYPos() + hitdef.getSparkxy().getSpark_y();
			} else {
				yReal = spriteHitted.getRealYPos() + hitdef.getSparkxy().getSpark_y();
			}
						
			AbstractSprite sprToRender = new AbstractSprite() {
				float _x = union != null && !isBlocked? union.x + union.width/2: xReal;
				float _y = union != null && !isBlocked? union.y + union.height/2: yReal;
				boolean _isFlip = isFlip;
				{
					this.sprAnimMng = spriteAnimManager;
					this.spriteSFF = sprSFF;
				}
				@Override
				public float getRealXPos() {
					return _x;
				}
				
				@Override
				public float getRealYPos() {
					return _y;
				}

				@Override
				public boolean isFlip() {
					return _isFlip;
				}
				
				boolean error;
				@Override
				public void process() {
					try {
						this.sprAnimMng.process();
						
					} catch (Exception e) {
						error = true;
						// TODO: handle exception
					}
				}
				
				@Override
				public boolean remove() {
					try {
						return error || this.sprAnimMng.getAnimTime() == 0;
						
					} catch (Exception e) {
//						e.printStackTrace();
					}
					return true;
				}
				
			};
			
			return sprToRender;
		}

		public static AbstractSprite getInstanceOfSparkSuperpause(SuperpauseSub superpause) {
			if (instance == null)
				throw new IllegalStateException("You have to init this before");

			Sprite sprite = superpause.getSprite();
			final boolean isFlip = sprite.isFlip();
			
			final float xReal;
			final float yReal;
			
			final SpriteSFF sprSFF;
			
			
			final SpriteAnimManager spriteAnimManager = new SpriteAnimManager("");

			if (superpause.getAnim() != null && superpause.getAnim().isSpriteUse()) {
				spriteAnimManager.setGroupSpriteMap(sprite.getSprAnimMng().getGroupSpriteMap());
				sprSFF = sprite.getSpriteSFF();
				
				spriteAnimManager.setAction(superpause.getAnim().getAction());
			} else if (superpause.getAnim() != null) {
				spriteAnimManager.setGroupSpriteMap(getFightfx().getAir().getGroupSpriteMap());
				sprSFF = getFightfx().getSff();
				
				spriteAnimManager.setAction(superpause.getAnim().getAction());
				
			} else {
				sprSFF = null;
				System.err.println("pas de hit spark");
			}
			
			xReal = sprite.getRealXPos() + ((isFlip ^ sprite.getSprAnimMng().getCurrentImageSprite().isMirrorH()? -1:1) * superpause.getPos().x);
			yReal = sprite.getRealYPos() + superpause.getPos().y;
						
			AbstractSprite sprToRender = new AbstractSprite() {
				float _x = xReal;
				float _y = yReal;
				boolean _isFlip = isFlip;
				{
					this.sprAnimMng = spriteAnimManager;
					this.spriteSFF = sprSFF;
				}
				@Override
				public float getRealXPos() {
					return _x;
				}
				
				@Override
				public float getRealYPos() {
					return _y;
				}

				@Override
				public boolean isFlip() {
					return _isFlip;
				}
				
				boolean error;
				@Override
				public void process() {
					try {
						this.sprAnimMng.process();
						
					} catch (Exception e) {
						error = true;
						// TODO: handle exception
					}
				}
				
				@Override
				public boolean remove() {
					if (this.sprAnimMng.getAction() == -1)
						return true;
					if (this.sprAnimMng.getCurrentGroupSprite() == null)
						return true;
					return error || this.sprAnimMng.getAnimTime() == 0;
				}
				
			};
			sprToRender.setSprAnimMng(spriteAnimManager);
			sprToRender.setSprAnimMng(spriteAnimManager);
			
			return sprToRender;
		}

		private SparkRenderFactory() {

		}

		public static SparkRenderFactory getInstanceOfFightFx() {
			return instance;
		}


		
	}
}
