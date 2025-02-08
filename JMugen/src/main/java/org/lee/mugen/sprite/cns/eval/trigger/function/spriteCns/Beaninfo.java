package org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns;

import org.apache.commons.beanutils.PropertyUtils;
import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.cns.eval.function.SpriteCnsTriggerFunction;

/**
 * 
 * @author Dr Wong
 * Test if the player is alive
 * @category Trigger : Complete
 */
public class Beaninfo extends SpriteCnsTriggerFunction {

	public Beaninfo() {
		super("beaninfo", new String[] {});
	}
	@Override
	public Object getValue(String spriteId,Valueable... params) {
		final Sprite sprite = GameFight.getInstance().getSpriteInstance(spriteId);
		try {
			Object o = PropertyUtils.getNestedProperty(sprite, params[0].getValue(spriteId).toString());
			return o;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
//		return new Valueable() {
//
//			public Object getValue(String spriteId, Valueable... params) {
//				try {
//					return PropertyUtils.getPropertyDescriptor(sprite, params[0].getValue(spriteId).toString()).getReadMethod().invoke(sprite).toString();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				return null;
//			}};
	}
}
