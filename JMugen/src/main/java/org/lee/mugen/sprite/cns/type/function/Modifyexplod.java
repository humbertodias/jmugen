package org.lee.mugen.sprite.cns.type.function;

import java.util.Collection;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.cns.eval.function.StateCtrlFunction;
import org.lee.mugen.sprite.entity.ExplodSprite;
import org.lee.mugen.sprite.parser.ExpressionFactory;
import org.lee.mugen.sprite.parser.Parser;

public class Modifyexplod extends StateCtrlFunction {
    public Modifyexplod() {
        super("explod", new String[] {
        		"anim","id"
        		,"pos","postype"
        		,"facing","vfacing"
        		,"bindtime","vel"
        		,"accel","random"
        		,"removetime","supermove"
        		,"supermovetime","pausemovetime"
        		,"scale","sprpriority","ontop"
        		,"shadow","ownpal","removeongethit"});
    }
    @Override
    public Valueable[] parseValue(String name, String value) {
		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}

    @Override
    public Object getValue(String spriteId, Valueable... params) {
		int idIndex = getParamIndex("id");
		Valueable[] vId = valueableParams[idIndex];

		// Id must exist
    	int id = Parser.getIntValue(vId[0].getValue(spriteId));
    	
    	Collection<ExplodSprite> explodSprites = GameFight.getInstance().getExplodeSprites(id);
    	
    	for (ExplodSprite explodSpr: explodSprites) {
    		if (explodSpr.getExplod().getSprite().getSpriteId().equals(spriteId))
    			fillBean(spriteId, explodSpr.getExplod());
        	// TODO MAKE REMOVE SPRITE SHADOW RENDERER
    	}
    	return null;
    }

}
