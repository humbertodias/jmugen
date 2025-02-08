package org.lee.mugen.sprite.cns.type.function;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.character.SpriteCns;
import org.lee.mugen.sprite.cns.eval.function.StateCtrlFunction;
import org.lee.mugen.sprite.parser.Parser;

public class Targetlifeadd extends StateCtrlFunction {

    // TODO : targetlifeadd
    public Targetlifeadd() {
        super("targetlifeadd", new String[] {"value", "id", "kill", "absolute"});
    }
	
	@Override
	public Object getValue(String spriteId, Valueable... p) {
		int idIndex = getParamIndex("id");
		String id = null;
		if (valueableParams[idIndex] != null) {
			Valueable vId = valueableParams[idIndex][0];
			if (vId != null) {
				id = vId.getValue(spriteId).toString();
			}
		}
		if (id == null) {
			id = "-1";
		}
		Sprite sprite = GameFight.getInstance().getSpriteInstance(spriteId);
		Sprite s = GameFight.getInstance().getFightEngine().getTarget(sprite, Integer.parseInt(id));
		if (s != null) {

			SpriteCns spriteInfo = s.getInfo();
			int valueIndex = getParamIndex("value");
			Valueable value = valueableParams[valueIndex][0];
			float fvalue = Parser.getFloatValue(value.getValue(spriteId));

			int ikill = 1;
			int iabsolute = 0;
			int killIndex = getParamIndex("kill");
			if (valueableParams[killIndex] != null) {
				Valueable kill = valueableParams[killIndex][0];
				if (kill != null) {
					ikill = Parser.getIntValue(kill.getValue(spriteId));
				}
			}
			int absoluteIndex = getParamIndex("absolute");
			
			if (valueableParams[absoluteIndex] != null) {
				Valueable absolute = valueableParams[absoluteIndex][0];
				if (absolute != null) {
					iabsolute = Parser.getIntValue(absolute.getValue(spriteId));
				}
			}
			spriteInfo.lifeAdd(fvalue, ikill, iabsolute);
		}
		
		
		return null;
	}
}
