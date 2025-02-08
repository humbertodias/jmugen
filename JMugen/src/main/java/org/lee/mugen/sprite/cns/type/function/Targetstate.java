package org.lee.mugen.sprite.cns.type.function;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.character.SpriteCns;
import org.lee.mugen.sprite.character.SpriteState;
import org.lee.mugen.sprite.cns.eval.function.StateCtrlFunction;
import org.lee.mugen.sprite.parser.Parser;
/**
 * 
 * @author Dr Wong
 * @category Cns Function : Complete
 */
public class Targetstate extends StateCtrlFunction {

    public Targetstate() {
        super("targetstate", new String[] {"value", "ctrl", "anim", "id"});
//        setInterrupt(true);
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
	
		Sprite sprite = GameFight.getInstance().getFightEngine().getTarget(thisSprite, idSpriteToChange);
			


		SpriteState spriteState = sprite.getSpriteState();
		SpriteCns spriteInfo = sprite.getInfo();
		int valueIndex = getParamIndex("value");
		int ctrlIndex = getParamIndex("ctrl");
		int animIndex = getParamIndex("anim");
		
		Valueable value = valueableParams[valueIndex][0];
		Valueable ctrl = null;
		Valueable anim = null;
		
		if (valueableParams[ctrlIndex] != null)
			ctrl = valueableParams[ctrlIndex][0];
		
		if (valueableParams[animIndex] != null)
			anim = valueableParams[animIndex][0];
		int ivalue = Parser.getIntValue(value.getValue(spriteId));
		
		if (ctrl != null) {
			int ictrl = Parser.getIntValue(ctrl.getValue(spriteId));
			spriteInfo.setCtrl(ictrl);
		}
		if (anim != null) {
			int ianim = Parser.getIntValue(anim.getValue(spriteId));
			sprite.getSprAnimMng().setAction(ianim);
		}
		spriteState.targetState(GameFight.getInstance().getRootId(spriteId), ivalue);
		
		return null;
	}

}
