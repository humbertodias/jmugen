package org.lee.mugen.sprite.cns.type.function;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.character.spiteCnsSubClass.NotHitBySub;
import org.lee.mugen.sprite.character.spiteCnsSubClass.HitDefSub.AttrClass;
import org.lee.mugen.sprite.cns.eval.function.StateCtrlFunction;
import org.lee.mugen.util.BeanTools;

public class Nothitby extends StateCtrlFunction {
	// TODO do it in SpriteCns Or figthEngine
	public Nothitby() {
		super("nothitby", new String[] {"value", "value2", "time"});
	}
	
	@Override
	public Object getValue(String spriteId, Valueable... p) {
		Sprite sprite = GameFight.getInstance().getSpriteInstance(spriteId);

		NotHitBySub notHitBySub = new NotHitBySub();
		notHitBySub.setTime(1); 
		fillBean(spriteId, notHitBySub);
		
		// + 1 because in this time binding time is decrease of 1
		notHitBySub.addTime(1);
		
		sprite.getInfo().setNothitby(notHitBySub);
		return null;
	}
	// TODO NOTHITBY
	public static Valueable[] parseForValue(String name, String value) {
		final Object v = BeanTools.getConvertersMap().get(AttrClass.class).convert(value);
		Valueable[] vals = new Valueable[1];
		vals = new Valueable[1];
		vals[0] = new Valueable() {
			public Object getValue(String spriteId, Valueable... params) {
				return v;
			}
		};
		return vals;
	}
	public static Valueable[] parseForValue2(String name, String value) {
		final Object v = BeanTools.getConvertersMap().get(AttrClass.class).convert(value);
		Valueable[] vals = new Valueable[1];
		vals = new Valueable[1];
		vals[0] = new Valueable() {
			public Object getValue(String spriteId, Valueable... params) {
				return v;
			}
		};
		return vals;
	}
}