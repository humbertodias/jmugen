package org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.base.AbstractSprite;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.cns.eval.function.SpriteCnsTriggerFunction;
import org.lee.mugen.stage.Stage;

public class Frontedgebodydist extends SpriteCnsTriggerFunction {

	public Frontedgebodydist() {
		super("frontedgebodydist", new String[] {});
	}

	public static float compute(AbstractSprite sprite) {
		Stage stage = GameFight.getInstance().getStage();
		int _mvX = stage.getCamera().getX();
		if (sprite.isFlip()) {
			float x = _mvX + stage.getCamera().getWidth()/2f + sprite.getRealXPos();
//			if (sprite instanceof Sprite) {
//				Sprite s = (Sprite) sprite;
//				x += s.getInfo().getWidth().getFront();
//			}
			return x;
		} else {
			float x = -_mvX + stage.getCamera().getWidth()/2f - sprite.getRealXPos();
//			if (sprite instanceof Sprite) {
//				Sprite s = (Sprite) sprite;
//				x += s.getInfo().getWidth().getFront();
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
