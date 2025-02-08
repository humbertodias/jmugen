package org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.cns.eval.function.SpriteCnsTriggerFunction;

public class Inguarddist extends SpriteCnsTriggerFunction {

	// TODO : Inguarddist
	public Inguarddist() {
		super("inguarddist", new String[] {});
	}
	public float getXDiff(String spriteId, Valueable... params) {
		Sprite sprOne = GameFight.getInstance().getSpriteInstance(spriteId);
		Sprite sprTwo = null;
		for (Sprite spr : GameFight.getInstance().getSprites()) {
			if (!spr.equals(sprOne)) {
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
		if (sprOne.getInfo().getXPos() < sprTwo.getInfo().getXPos()) {
			return Math.abs(sprOne.getInfo().getXPos() + sprOne.getInfo().getSize().getGround().getFront() 
			- (sprTwo.getInfo().getXPos() - sprTwo.getInfo().getSize().getGround().getBack()));
			
		} else {
			return Math.abs(sprTwo.getInfo().getXPos() + sprTwo.getInfo().getSize().getGround().getFront() 
			- (sprOne.getInfo().getXPos() - sprOne.getInfo().getSize().getGround().getBack()));
		}
	}
	@Override
	public Object getValue(String spriteId, Valueable... params) {
		float dist = getXDiff(spriteId) <= GameFight.getInstance().getSpriteInstance(spriteId).getInfo().getSize().getAttack().getDist()? 1: 0;
		return dist;
	}



	
}
