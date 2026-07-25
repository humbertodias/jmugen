package org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.cns.eval.function.SpriteCnsTriggerFunction;
/**
 * 
 * @author Dr Wong
 * @category Trigger : Complete
 */
public class Physics extends SpriteCnsTriggerFunction {

	public Physics() {
		super("physics", new String[] {});
	}

	@Override
	public Object getValue(String spriteId, Valueable... params) {
		return GameFight.getInstance().getSpriteInstance(spriteId).getInfo().getPhysics().toString().toLowerCase();
	}
	
	
}
