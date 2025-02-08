package org.lee.mugen.sprite.cns.type.function;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.character.SpriteCns;
import org.lee.mugen.sprite.cns.eval.function.StateCtrlFunction;
import org.lee.mugen.sprite.parser.Parser;

/**
 * 
 * @author Dr Wong
 * @category Cns Function : do edge, player
 */
public class Width extends StateCtrlFunction {

	// TODO : width
	public Width() {
		super("width", new String[] { "edge", "player", "value" });
	}

	@Override
	public Object getValue(String spriteId, Valueable... params) {
		Sprite sprite = GameFight.getInstance().getSpriteInstance(spriteId);
		SpriteCns sprInfo = sprite.getInfo();
		{
			int valueIndex = getParamIndex("value");
			if (valueableParams[valueIndex] != null) {
				Valueable front = valueableParams[valueIndex][0];
				if (valueableParams[valueIndex].length == 2) {
					Valueable back = valueableParams[valueIndex][1];
					int iBack = Parser.getIntValue(back.getValue(spriteId));

					int iFront = Parser.getIntValue(front.getValue(spriteId));
					sprInfo.setWidth(iFront, iBack);
					sprInfo.setEdge(iFront, iBack);
				} else {
					int iFront = Parser.getIntValue(front.getValue(spriteId));
					sprInfo.setWidth(iFront);
					sprInfo.setEdge(iFront);
				}
			}
		}
		{
			int valueIndex = getParamIndex("edge");
			if (valueableParams[valueIndex] != null) {
				Valueable front = valueableParams[valueIndex][0];
				if (valueableParams[valueIndex].length == 2) {
					Valueable back = valueableParams[valueIndex][1];
					int iBack = Parser.getIntValue(back.getValue(spriteId));

					int iFront = Parser.getIntValue(front.getValue(spriteId));
					sprInfo.setEdge(iFront, iBack);

				} else {
					int iFront = Parser.getIntValue(front.getValue(spriteId));
					sprInfo.setEdge(iFront);

				}
			}
		}
		{
			int valueIndex = getParamIndex("player");
			if (valueableParams[valueIndex] != null) {
				Valueable front = valueableParams[valueIndex][0];
				if (valueableParams[valueIndex].length == 2) {
					Valueable back = valueableParams[valueIndex][1];
					int iBack = Parser.getIntValue(back.getValue(spriteId));

					int iFront = Parser.getIntValue(front.getValue(spriteId));
					sprInfo.setWidth(iFront, iBack);

				} else {
					int iFront = Parser.getIntValue(front.getValue(spriteId));
					sprInfo.setWidth(iFront);

				}
			}
		}
		return null;
	}

}
