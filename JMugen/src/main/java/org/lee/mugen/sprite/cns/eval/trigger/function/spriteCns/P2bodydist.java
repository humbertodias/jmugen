package org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns;

import java.awt.Rectangle;
import java.util.List;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.StringValueable;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.character.SpriteHelper;
import org.lee.mugen.sprite.cns.eval.function.SpriteCnsTriggerFunction;
/**
 * 
 * @author Dr Wong
 * @category Trigger : Complete
 */
public class P2bodydist extends SpriteCnsTriggerFunction {

	public P2bodydist() {
		super("p2bodydist", new String[] {"x", "y"});
	}
	public void addParam(String name, Valueable[] param) {
		
	}
	
	public static float getXDiff(String spriteId, Valueable... params) {
		Sprite sprOne = GameFight.getInstance().getSpriteInstance(spriteId);
		Sprite sprTwo = null;
		for (Sprite spr : GameFight.getInstance().getSprites()) {
			if (!spr.equals(sprOne) && !GameFight.getInstance().getRoot(sprOne).equals(spr)
					&& !GameFight.getInstance().getRoot(sprOne).equals(GameFight.getInstance().getRoot(spr))) {
				if (spr instanceof SpriteHelper)
					continue;
				sprTwo = spr;
			}
		}
//		Area shapeOne = new Area();
//		for (Rectangle r: sprOne.getCns2())
//			shapeOne.add(new Area(r));
//		
//		Area shapeTwo = new Area();
//		for (Rectangle r: sprTwo.getCns2())
//			shapeTwo.add(new Area(r));
//		
//		Rectangle rOne = shapeOne.getBounds();
//		Rectangle rTwo = shapeTwo.getBounds();
//		
//		if (rOne.x < rTwo.x) {
//			float result = (float) (rTwo.getX() - (rOne.getX() + rOne.getWidth()));
//			return result < 0? 0: result;
//		} else {
//			float result = (float) (rOne.getX() - (rTwo.getX() + rTwo.getWidth()));
//			return result < 0? 0: result;
//		}
		Rectangle one = sprOne.getInfo().getSizeRect();
		Rectangle two = sprTwo.getInfo().getSizeRect();
		int xOne = sprOne.getInfo().isFlip()? one.x: one.x + one.width;
		int xTwo = sprTwo.getInfo().isFlip()? two.x: two.x + two.width;
		return Math.abs(xOne - xTwo);
		
	}
	private static float getYDiff(String spriteId, Valueable...params) {
		Sprite sprOne = GameFight.getInstance().getSpriteInstance(spriteId);
		Sprite sprTwo = null;
		for (Sprite spr : GameFight.getInstance().getSprites()) {
			if (!spr.equals(sprOne)) {
				sprTwo = spr;
			}
		}
		return sprTwo.getInfo().getYPos() - sprOne.getInfo().getYPos() ;
	}
	@Override
	public Object getValue(String spriteId, Valueable... params) {
		String p = params[0].getValue(spriteId).toString();

		if ("x".equals(p)) {
			return getXDiff(spriteId, params);
		} else if ("y".equals(p)) {
			return getYDiff(spriteId, params);		
		} 
		throw new IllegalArgumentException("arg must be x or y");
	}

	@Override
	public int parseValue(String[] tokens, int pos, List<Valueable> result) {
		pos++;
		if (!"x".equalsIgnoreCase(tokens[pos]) && !"y".equalsIgnoreCase(tokens[pos])) {
			throw new IllegalArgumentException("arg must be x or y");
		}
		final String component = tokens[pos];
		final Valueable valueable = new StringValueable(component);
		result.add(valueable);
		return pos;
	}
	
}
