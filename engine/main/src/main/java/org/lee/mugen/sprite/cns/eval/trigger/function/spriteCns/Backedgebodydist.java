package org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.base.AbstractSprite;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.cns.eval.function.SpriteCnsTriggerFunction;
import org.lee.mugen.stage.Stage;

public class Backedgebodydist extends SpriteCnsTriggerFunction {

	public Backedgebodydist() {
		super("backedgebodydist", new String[] {});
	}
	public static float compute(AbstractSprite sprite) {
		Stage stage = GameFight.getInstance().getStage();
		int _mvX = stage.getCamera().getXNoShaKe();
		if (sprite.isFlip()) {
			float x = stage.getCamera().getWidth()/2 -_mvX - sprite.getRealXPos();
//			if (sprite instanceof Sprite) {
//				Sprite s = (Sprite) sprite;
//				x -= s.getInfo().getWidth().getBack();
//			}
			return x;
			
		} else {
			float x = stage.getCamera().getWidth()/2f + _mvX + sprite.getRealXPos();
//			if (sprite instanceof Sprite) {
//				Sprite s = (Sprite) sprite;
//				x -= s.getInfo().getWidth().getBack();
//			}
			return x;
			
		}
	}
	@Override
	public Object getValue(String spriteId, Valueable... params) {
		Sprite sprite = GameFight.getInstance().getSpriteInstance(spriteId);
		return compute(sprite);
	}


}
