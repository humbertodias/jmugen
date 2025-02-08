package org.lee.mugen.core.physics;

import java.awt.Rectangle;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.character.SpriteHelper;
import org.lee.mugen.sprite.character.SpriteCns.Physics;
import org.lee.mugen.sprite.cns.type.function.Assertspecial.Flag;
import org.lee.mugen.stage.Stage;

/**
 * Compute position of sprite TODO : with SpriteCns take in consideration the
 * ctrl WIDTH
 * 
 * @author Dr Wong
 * 
 */
public class PhysicsEngime {
	public static void processSpritePhysics(Sprite sprite) {
		if (sprite.isPause()) {
			return;
		}
		if (sprite.getInfo().getPosfreeze() > 0) {
			sprite.getInfo().addPosFreeze(-1);
			return;
		}
		

		Physics p = sprite.getInfo().getPhysics();
		processSpriteCommonPhysics(sprite);

		switch (p) {
			// Stand
			case S:
				processSpriteStandPhysics(sprite);
	
				break;
			// Air
			case A:
				processSpriteAirPhysics(sprite);
				break;
			// Crouch
			case C:
				processSpriteCrouchPhysics(sprite);
				break;
			// None
			case N:

			// Nothing to but : the common part is done in common process
			break;

		default:
			throw new IllegalStateException("This state musn't be reached : "
					+ PhysicsEngime.class.getName());
		}
		if (GameFight.getInstance().getGlobalEvents().isAssertSpecial(sprite.getSpriteId(), Flag.intro))
			return;
		if (sprite instanceof SpriteHelper) {
			
		} else {
			try {
				for (Sprite s : GameFight.getInstance().getSprites()) {
					if (!s.equals(sprite) && !(s instanceof SpriteHelper) && s != sprite) {
						processSpriteCollision(sprite, s);
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
			}

			
		}
		
	}

	private static Rectangle getGlobalClsn2ect(Sprite spr) {
		Rectangle rectangle = null;
		for (java.awt.Rectangle rec: spr.getCns2()) {
			if (rectangle == null) {
				rectangle = new Rectangle(rec);
				
			} else {
				rectangle.add(rec);
			}
		}
		if (rectangle == null)
			return null;
		if (rectangle.width < 0) {
			int width = rectangle.width;
			rectangle.x = rectangle.x + width;
			rectangle.width = -width;
		}
		if (rectangle.height < 0) {
			int height = rectangle.height;
			rectangle.y = rectangle.y + height;
			rectangle.height = -height;
		}
		int x = 0;
		int y = 0;
		Stage stage = GameFight.getInstance().getStage();
		int _mvX = stage.getCamera().getXNoShaKe();
		int _mvY = stage.getCamera().getYNoShake();
		x = _mvX + stage.getCamera().getWidth()/2;
		y = stage.getStageinfo().getZoffset() + _mvY;
		rectangle.setLocation(x + rectangle.x, y + rectangle.y);
		return rectangle;
	}
	
	private static Rectangle getGlobalWidthRect(Sprite spr) {
		if (spr.getInfo().getYPos() < 0 && getGlobalClsn2ect(spr) == null) {
			return getGlobalClsn2ect(spr);
		}

		return getRectEdge(spr);
	}
	
	private static void correctRectangle(Rectangle r) {
		if (r.width < 0) {
			r.x = r.x - r.width;
			r.width = -r.width;
		}
		if (r.height < 0) {
			r.y = r.y - r.height;
			r.height = -r.height;
		}
	}
	public static void processSpriteCollision(Sprite sprOne,
			Sprite sprTwo) {
		
		boolean isOnePlayerpush = sprOne.getInfo().getPlayerpush() >= 0;
		boolean isTwoPlayerpush = sprTwo.getInfo().getPlayerpush() >= 0;
		checkGoodPositionInScreen(sprOne);
		checkGoodPositionInScreen(sprTwo);
		

//		if (sprOne.getInfo().getType() == Type.L || sprTwo.getInfo().getType() == Type.L)
//			return;
		
		if (isOnePlayerpush || isTwoPlayerpush)
			return;
		if (sprOne.getInfo().getBindTo() != null && sprOne.getInfo().getBindTo().getTime() > -1)
			return;
		if (sprTwo.getInfo().getBindTo() != null && sprTwo.getInfo().getBindTo().getTime() > -1)
			return;
		

		if (sprOne.getInfo().getPhysics() == Physics.A && sprTwo.getInfo().getPhysics() == Physics.A) {
			Rectangle r1 = getGlobalClsn2ect(sprOne);
			Rectangle r2 = getGlobalClsn2ect(sprTwo);
			
			if (sprOne.getInfo().getPhysics() != Physics.A) {
				r1 = getGlobalWidthRect(sprOne);
				Rectangle tmp = getGlobalClsn2ect(sprOne);
				r1.y = tmp.y;
				r1.height = tmp.height;
			}
			if (sprTwo.getInfo().getPhysics() != Physics.A) {
				r2 = getGlobalWidthRect(sprTwo);
				Rectangle tmp = getGlobalClsn2ect(sprTwo);
				r2.y = tmp.y;
				r2.height = tmp.height;
			}
			
			
			boolean isIntersect = r1.intersects(r2);
			if (isIntersect) {
				int mul = 1;

				if (r1.x > r2.x && sprOne.getRealXPos() > sprTwo.getRealXPos())
					mul = -mul;
				sprTwo.getInfo().addXPos(r1.intersection(r2).width * mul);
//				sprTwo.getInfo().addXPos(r1.intersection(r2).width/2f * mul);
//				sprOne.getInfo().addXPos(r1.intersection(r2).width/2f * mul);
				checkGoodPositionInScreen(sprTwo);
			}
			r1 = getGlobalClsn2ect(sprOne);
			r2 = getGlobalClsn2ect(sprTwo);
			if (sprOne.getInfo().getPhysics() != Physics.A) {
				r1 = getGlobalWidthRect(sprOne);
				Rectangle tmp = getGlobalClsn2ect(sprOne);
				r1.y = tmp.y;
				r1.height = tmp.height;
			}
			if (sprTwo.getInfo().getPhysics() != Physics.A) {
				r2 = getGlobalWidthRect(sprTwo);
				Rectangle tmp = getGlobalClsn2ect(sprTwo);
				r2.y = tmp.y;
				r2.height = tmp.height;
			}
					
			isIntersect = r1.intersects(r2);
			if (isIntersect) { // P2 is collision with back
				sprOne.getInfo().moveXPos(-r1.intersection(r2).width);
			}
			checkGoodPositionInScreen(sprOne);
		} else {
			Rectangle r1 = getGlobalWidthRect(sprOne);
			Rectangle r2 = getGlobalWidthRect(sprTwo);
			
			if (sprOne.getInfo().getPhysics() == Physics.A) {
				r1 = getGlobalWidthRect(sprOne);
				Rectangle tmp = getGlobalClsn2ect(sprOne);
				r1.y = tmp.y;
				r1.height = tmp.height;
			}
			if (sprTwo.getInfo().getPhysics() == Physics.A) {
				r2 = getGlobalWidthRect(sprTwo);
				Rectangle tmp = getGlobalClsn2ect(sprTwo);
				r2.y = tmp.y;
				r2.height = tmp.height;
			}
			
			boolean isIntersect = r1.intersects(r2);
			if (isIntersect) {
				int mul = 1;

				if (sprOne.getRealXPos() > sprTwo.getRealXPos())
					mul = -mul;
				sprTwo.getInfo().addXPos(r1.intersection(r2).width * mul);
//				sprTwo.getInfo().addXPos(r1.intersection(r2).width/2f * mul);
//				sprOne.getInfo().addXPos(r1.intersection(r2).width/2f * mul);
				checkGoodPositionInScreen(sprTwo);
			}
			r1 = getGlobalWidthRect(sprOne);
			r2 = getGlobalWidthRect(sprTwo);
			if (sprOne.getInfo().getPhysics() == Physics.A) {
				r1 = getGlobalWidthRect(sprOne);
				Rectangle tmp = getGlobalClsn2ect(sprOne);
				r1.y = tmp.y;
				r1.height = tmp.height;
			}
			if (sprTwo.getInfo().getPhysics() == Physics.A) {
				r2 = getGlobalWidthRect(sprTwo);
				Rectangle tmp = getGlobalClsn2ect(sprTwo);
				r2.y = tmp.y;
				r2.height = tmp.height;
			}
			isIntersect = r1.intersects(r2);
			if (isIntersect) { // P2 is collision with back
				sprOne.getInfo().moveXPos(-r1.intersection(r2).width );
			}
			checkGoodPositionInScreen(sprOne);
			
		}


	}

	private static Rectangle getRectEdge(Sprite spr) {

		boolean isFlip = spr.isFlip();
		Stage stage = GameFight.getInstance().getStage();
		float _mvX = stage.getCamera().getXNoShaKe();
		float _mvY = stage.getCamera().getYNoShake();

		float x = 0;
		float y = 0;
		x = _mvX + stage.getCamera().getWidth() / 2;
		y = stage.getStageinfo().getZoffset() + _mvY;
		int topX = (int) (isFlip ? spr.getInfo().getWidth().getFront() : spr
				.getInfo().getWidth().getBack());
		int bottomX = (int) (isFlip ? spr.getInfo().getWidth().getBack() : spr
				.getInfo().getWidth().getFront());
		int topY = (int) (spr.getInfo().getSize().getHeight());
		int bottomY = 0;

		topX = (int) (spr.getRealXPos() - topX);
		bottomX = (int) (spr.getRealXPos() + bottomX);
		topY = (int) (spr.getRealYPos() - topY);
		bottomY = (int) (spr.getRealYPos());

		Rectangle r = new Rectangle(topX, topY, Math.abs(bottomX - topX), Math
				.abs(bottomY - topY));
		r.translate((int) (_mvX + stage.getCamera().getWidth() / 2),
				(int) (stage.getStageinfo().getZoffset() + _mvY));
		return r;
	}

	public static void checkGoodPositionInScreen(Sprite sprOne) {
		if (sprOne.getInfo().getScreenbound().isScreenbound()
			||
				sprOne.getCns2().size() == 0) {
			return;
		}
		Rectangle r1 = getRectEdge(sprOne);
		if (r1.x < 0) {
			sprOne.getInfo().addXPos(-r1.x);
		} else if (r1.x + r1.width > 320 || r1.x > 320) {
			sprOne.getInfo().addXPos(((320 - r1.x - r1.width)));
		}
	}

	public static boolean isOutOfScreeen(Sprite sprOne, float xVelToAdd) {
		if (sprOne.getInfo().getScreenbound().isScreenbound())
			return false;
		Rectangle r1 = getRectEdge(sprOne);
		if (r1 == null)
			return false;
		if (!(r1.x + xVelToAdd <= 0 || r1.x + r1.width + xVelToAdd >= 320)) {
		} else {
			return true;
		}
		if (r1.x + xVelToAdd < 0) {
			return true;
		} else if (r1.x + r1.width + xVelToAdd > 320) {
			return true;
		}
		return false;
	}

	private static void processSpriteCommonPhysics(Sprite sprite) {
		Sprite sprOne = sprite;
		if (sprOne instanceof SpriteHelper) {
			if (((SpriteHelper) sprOne).getHelperSub().getHelpertype().equals(
					"normal")) {

				float xVel = sprOne.getInfo().getVelset().getX();
				float yVel = sprOne.getInfo().getVelset().getY();
				sprOne.getInfo().moveXPos(xVel);
				sprOne.getInfo().addYPos(yVel);
			}
		} else {
			float xVel = sprOne.getInfo().getVelset().getX();
			float yVel = sprOne.getInfo().getVelset().getY();

			sprOne.getInfo().addYPos(yVel);
			sprOne.getInfo().moveXPos(xVel);
		}
	}

	private static void processSpriteStandPhysics(Sprite sprite) {
		float friction = sprite.getInfo().getMovement().getStand().getFriction();
		sprite.getInfo().getVelset().mulX(friction);
	}

	private static void processSpriteCrouchPhysics(Sprite sprite) {
		float friction = sprite.getInfo().getMovement().getCrouch()
				.getFriction();
		sprite.getInfo().getVelset().mulX(friction);

	}

	private static void processSpriteAirPhysics(Sprite sprite) {
		float friction = sprite.getInfo().getMovement().getYaccel();

		sprite.getInfo().getVelset().addY(friction);

		if (sprite.getInfo().getYPos() >= 0) {
			sprite.getInfo().getVelset().setY(0);
			sprite.getInfo().setYPos(0);
			sprite.getSpriteState().changeStateDef(52);
		}

	}

	public static boolean isSpriteOutOfScreeen(int x) {
		for (Sprite s : GameFight.getInstance().getSprites()) {
			if (!(s instanceof SpriteHelper)) {
				if (isOutOfScreeen(s, x))
					return true;
			}
		}
		return false;
	}
}
