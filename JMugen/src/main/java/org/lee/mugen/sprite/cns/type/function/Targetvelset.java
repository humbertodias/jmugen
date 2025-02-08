package org.lee.mugen.sprite.cns.type.function;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.cns.eval.function.StateCtrlFunction;
import org.lee.mugen.sprite.parser.Parser;

public class Targetvelset extends StateCtrlFunction {

    public Targetvelset() {
        super("targetvelset", new String[] {"x", "y", "id"});
    }

    
    @Override
    public Object getValue(String spriteId, Valueable... params) {
		Sprite thisSprite = GameFight.getInstance().getSpriteInstance(spriteId);
		int idIndex = getParamIndex("id");
		int idSpriteToChange = -1;
		if (valueableParams[idIndex] != null) {
			Valueable id = valueableParams[idIndex][0];
			
			if (id == null || Parser.getIntValue(id.getValue(spriteId)) <= 0) {
				
			} else {
				idSpriteToChange = Parser.getIntValue(id.getValue(spriteId));
			}
		}
	
		Sprite targetSpr = GameFight.getInstance().getFightEngine().getTarget(thisSprite, idSpriteToChange);
		int xIndex = getParamIndex("x");
		int yIndex = getParamIndex("y");
		
		Valueable x = null;
		Valueable y = null;
		
		
		if (valueableParams[xIndex] != null) {
			x = valueableParams[xIndex][0];
			float xValue = Parser.getFloatValue(x.getValue(spriteId));
			targetSpr.getInfo().getVelset().addX(xValue);
			
		}
		
		if (valueableParams[yIndex] != null) {
			y = valueableParams[yIndex][0];
			float yValue = Parser.getFloatValue(y.getValue(spriteId));
			targetSpr.getInfo().getVelset().addY(yValue);
			
		}
		return null;
	}
    
    
}
