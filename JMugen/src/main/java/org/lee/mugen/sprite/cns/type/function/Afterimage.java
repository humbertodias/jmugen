package org.lee.mugen.sprite.cns.type.function;

import java.util.HashMap;
import java.util.Map;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.ObjectValueable;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.renderer.RGB;
import org.lee.mugen.renderer.Trans;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.cns.eval.function.StateCtrlFunction;
import org.lee.mugen.sprite.entity.AfterImageSprite;
/**
 * Parse : OK
 * Renderer not because Opengl issue
 * @author Dr Wong
 *
 */
public class Afterimage extends StateCtrlFunction {
	// "none" for normal drawing
	// "add" for colour addition (like a spotlight effect)
	// "add1" for colour addition with background dimmed to 50% brightness
	// "addalpha" for colour addition with control over alpha values (you need
	// an "alpha" parameter if you use this)
	//"sub" for colour subtraction (like a shadow effect)
	
	public static Valueable[] parseForTrans(String name, String value) {

		value = value.toUpperCase();
		final org.lee.mugen.renderer.Trans trans = org.lee.mugen.renderer.Trans.valueOf(value);
		final Valueable v = new ObjectValueable(trans);
		return new Valueable[] {v};
	}
	
//	@Override
//	public Valueable[] parse(String name, String value) {
//		if ("trans".equals(name)) {
//			return parseForTrans(name, value);
//		} else {
//			return super.parse(name, value);
//		}
//	}

	public Afterimage() {
		super("afterimage", new String[] {
				"time","length","palcolor","palinvertall"
				,"palbright","palcontrast","palpostbright","paladd"
				,"palmul","timegap","framegap","trans"
		});
	}

	private AfterImageSprite afterimageSprite = null;
	@Override
	public Object getValue(final String spriteId, Valueable... params) {
		GameFight stateMachine = GameFight.getInstance();
		Sprite sprite = GameFight.getInstance().getSpriteInstance(spriteId);
		
		stateMachine.removeAfterImageSprite(afterimageSprite);

		afterimageSprite = null;
		if (afterimageSprite == null) // Only One After Image for One Sprite and statectrl
			afterimageSprite = new AfterImageSprite(sprite);
		fillBean(spriteId, afterimageSprite);
		stateMachine.addAfterImageSprite(afterimageSprite);
		
		return null;
	}
	
	
	protected Map<String, Object[]> _DEFAULT_VALUES_MAP = buildDefaultValuesMap();

	protected Map<String, Object[]> buildDefaultValuesMap() {
		final Map<String, Object[]> map = new HashMap<String, Object[]>();
		map.put("palcolor", new Object[] {256f});
		map.put("palinvertall", new Object[] {0f});
		map.put("palbright", new Object[] {new RGB(30, 30, 30, 0)});
		map.put("palcontrast", new Object[] {new RGB(120,120,220, 255)});
		map.put("palpostbright", new Object[] {new RGB(0, 0, 0, 0)});
		map.put("paladd", new Object[] {new RGB(10f, 10f, 25f, 255f)});
		map.put("palmul", new Object[] {new RGB(.65f,.65f,.75f, 1f)});
		map.put("timegap", new Object[] {1});
		map.put("framegap", new Object[] {4});
		map.put("trans", new Object[] {Trans.ADD});
		return map;
		
	}
	@Override
	protected Object[] getDefaultValues(String name) {
		return _DEFAULT_VALUES_MAP.get(name);
	}
}
