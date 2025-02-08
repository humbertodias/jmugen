package org.lee.mugen.sprite.cns.type.function;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.cns.eval.function.StateCtrlFunction;
import org.lee.mugen.util.BeanTools;
/**
 * 
 * @author Dr Wong
 * @category Cns Function : Complete
 */
public class Spritebeanset extends StateCtrlFunction {

	public Spritebeanset() {
		super("Spritebeanset", new String[] {"value", "bean"});
	}
	
	@Override
	public Object getValue(String spriteId, Valueable... p) {
		Sprite sprite = GameFight.getInstance().getSpriteInstance(spriteId);
		
		int valueIndex = getParamIndex("value");
		Valueable value = valueableParams[valueIndex][0];

		int beanNameIndex = getParamIndex("bean");
		Valueable beanName = valueableParams[beanNameIndex][0];

		try {
			BeanTools.setObject(sprite, beanName.getValue(spriteId).toString(), value.getValue(spriteId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
