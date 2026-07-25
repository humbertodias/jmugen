package org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns;

import java.util.List;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.StringValueable;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.cns.eval.function.SpriteCnsTriggerFunction;
import org.lee.mugen.sprite.entity.PointF;
import org.lee.mugen.stage.Stage;

/**
 * 
 * @author Dr Wong
 * @category Trigger : Ok : do Fix Scale
 */
public class Screenpos extends SpriteCnsTriggerFunction {

	public Screenpos() {
		super("screenpos", new String[] {"x", "y"});
	}

	@Override
	public Object getValue(String spriteId, Valueable... params) {
		String p = params[0].getValue(spriteId).toString();
		Sprite sprite = GameFight.getInstance().getSpriteInstance(spriteId);
		final Stage stage = GameFight.getInstance().getStage();
		
		if ("x".equals(p)) {
			return getPosRelativeToScreen(stage, sprite.getRealXPos(), sprite.getRealYPos()).getX();
		} else if ("y".equals(p)) {
			return getPosRelativeToScreen(stage, sprite.getRealXPos(), sprite.getRealYPos()).getY();		
		}
		throw new IllegalArgumentException("arg must be x or y");
	}
	
	private static PointF getPosRelativeToScreen(Stage stage, float xRealPos, float yRealPos) {
		// TODO SCALE if 
		int _mvX = stage.getCamera().getXNoShaKe();
		int _mvY = stage.getCamera().getYNoShake();
		int x = _mvX + stage.getCamera().getWidth()/2;
		int y = stage.getStageinfo().getZoffset() + _mvY;
		
		PointF pos = new PointF(xRealPos + x, yRealPos + y);
		
		return pos;
	
	}
	@Override
	public int parseValue(String[] tokens, int pos, List<Valueable> result) {
		pos++;
		if (!"x".equals(tokens[pos]) && !"y".equals(tokens[pos])) {
			throw new IllegalArgumentException("arg must be x or y");
		}
		final String component = tokens[pos];
		final Valueable valueable = new StringValueable(component);
		result.add(valueable);
		return pos;
	}
	
}
