package org.lee.mugen.core.renderer.game.fight;

import java.awt.Point;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.core.GameState;
import org.lee.mugen.fight.section.elem.FontType;
import org.lee.mugen.fight.section.elem.Type;
import org.lee.mugen.renderer.GraphicsWrapper;
import org.lee.mugen.renderer.MugenDrawer;
import org.lee.mugen.renderer.PalFxSub;
import org.lee.mugen.renderer.RGB;
import org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns.Roundstate;
import org.lee.mugen.sprite.cns.type.function.Assertspecial.Flag;

public class TimeRender extends BaseRender {
	PalFxSub thisCustompalFx = new PalFxSub();
	{
		thisCustompalFx.setMul(new RGB(255f, 255f, 255f, 0f));
	}
	public void render() {
		if (GameFight.getInstance().getGlobalEvents().isAssertSpecial(Flag.nobardisplay))
			return;
		
		if (GameFight.getInstance().getGameState().getRoundState() != Roundstate.COMBAT) {
			thisCustompalFx.getMul().setA(0f);
			return;
		} else if (thisCustompalFx.getMul().getA() < 255) {
			thisCustompalFx.getMul().setA(thisCustompalFx.getMul().getA() + 1f);
		}
		float time = GameFight.getInstance().getGameState().getRoundTime();
		MugenDrawer md = GraphicsWrapper.getInstance();
		Type counter = GameFight.getInstance().getFightdef().getTime().getCounter();
		int framepersec = GameFight.getInstance().getFightdef().getTime().getFramespercount();
		String display = (int)(time/framepersec) + "";
		if (time == GameState.DEFAULT_TIME + 1) {
			display = "o";
		}
		if (counter.getLayerno() != layer)
			return;
		FontType font = (FontType) counter.getType();
		Point pos = GameFight.getInstance().getFightdef().getTime().getPos();
		Integer fontIdx = font.getFontno();
		
		Integer fontSens = font.getAlignmt().getCode();
		md.scale(counter.getScale().getX(), counter.getScale().getY());
		font.getFont().get(fontIdx).
				draw(font.getFontbank(), (int)(pos.x*1f/counter.getScale().getX()), (int)(pos.y*1f/counter.getScale().getY()),
						md, display, fontSens, counter.getAlpha());
		md.scale(1f/counter.getScale().getX(), 1f/counter.getScale().getY());
	}

}
