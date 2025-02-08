package org.lee.mugen.sprite.cns.type.function;

import java.awt.Point;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.core.renderer.game.ImageRender;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.cns.eval.function.StateCtrlFunction;
import org.lee.mugen.sprite.common.resource.FontParser;

public class DisplayToScreen extends StateCtrlFunction {
	public static class DisplayToScreenSub {
		String value;
		Point pos = new Point();
		int time = 1;
		public Point getPos() {
			return pos;
		}
		public void setPos(Point pos) {
			this.pos = pos;
		}
		public int getTime() {
			return time;
		}
		public void setTime(int time) {
			this.time = time;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		
	}
	// TODO DisplayToScreen
	public DisplayToScreen() {
		super("displaytoscreen", new String[] {"value", "pos", "time"});
	}

	@Override
	public Object getValue(String spriteId, Valueable... params) {
		DisplayToScreenSub sub = new DisplayToScreenSub();
		fillBean(spriteId, sub);
		try {
			ImageRender ir = new ImageRender(sub.getValue(), FontParser.getFontProducer(), sub.getPos(), sub.time);
			GameFight.getInstance().addRender(ir);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}
}
