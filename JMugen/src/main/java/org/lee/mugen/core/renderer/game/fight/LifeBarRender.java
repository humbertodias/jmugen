package org.lee.mugen.core.renderer.game.fight;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.fight.section.Lifebar;
import org.lee.mugen.fight.section.elem.PlayerLifebar;
import org.lee.mugen.fight.section.elem.Type;
import org.lee.mugen.renderer.DrawProperties;
import org.lee.mugen.renderer.GraphicsWrapper;
import org.lee.mugen.renderer.MugenDrawer;
import org.lee.mugen.renderer.PalFxSub;
import org.lee.mugen.renderer.RGB;
import org.lee.mugen.sprite.base.AbstractAnimManager.SpriteDrawProperties;
import org.lee.mugen.sprite.baseForParse.ImageSpriteSFF;
import org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns.Roundstate;
import org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns.Teammode.TeamMode;
import org.lee.mugen.sprite.cns.type.function.Assertspecial.Flag;

public class LifeBarRender extends BaseRender {





	PalFxSub thisCustompalFx = new PalFxSub();
	{
		thisCustompalFx.setMul(new RGB(255f, 255f, 255f, 0f));
	}
	protected void draw(MugenDrawer md, DrawProperties drawProperties) {
		drawProperties.setPalfx(thisCustompalFx);
		super.draw(md, drawProperties);
	}
	protected void render(MugenDrawer md, Point pos, Type type, int x, int x2) {
		if (type.getLayerno() != layer)
			return;
		ImageSpriteSFF sff = getImageSFF(type);
		if (sff == null)
			return;
		
		SpriteDrawProperties dp = getSpriteDrawProperties(type);
		DrawProperties drawProperties = null;
		if (type.getFacing() == -1) {
			drawProperties = new DrawProperties(
					pos.x + sff.getWidth() - sff.getXAxis() + type.getOffset().x, 
					pos.y - sff.getYAxis() + type.getOffset().y, 
					true, 
					false, sff.getImage());
		} else if (type.getFacing() == 1) {
			drawProperties = new DrawProperties(
					pos.x - sff.getXAxis() + type.getOffset().x, 
					pos.y - sff.getYAxis() + type.getOffset().y, 
					false, 
					false, sff.getImage());
		}
		if (x < x2) {
			int delta = Math.abs(x-x2);
			int originDelta = (int) ((drawProperties.getXRightDst() - drawProperties.getXLeftDst()) * type.getScale().getX());
			delta = Math.min(delta, originDelta);
			drawProperties.setXRightDst(drawProperties.getXLeftDst() + delta/type.getScale().getX());
			drawProperties.setXRightSrc(drawProperties.getXLeftSrc() + delta/type.getScale().getX());
			drawProperties.setXScaleFactor(type.getScale().getX());
			drawProperties.setYScaleFactor(type.getScale().getY());
		} else if (x >= x2) {
			int delta = Math.abs(x2-x);
			int originDelta = (int) ((drawProperties.getXRightDst() - drawProperties.getXLeftDst()) * type.getScale().getX());
			delta = Math.min(delta, originDelta);
			drawProperties.setXRightDst(drawProperties.getXLeftDst() + originDelta);
			drawProperties.setXLeftDst(drawProperties.getXRightDst() - delta);
			
			drawProperties.setXLeftSrc(drawProperties.getXRightSrc() - delta/type.getScale().getX());
			drawProperties.setYScaleFactor(type.getScale().getY());
		}
		draw(md, drawProperties);
	
	}
	
	@Override
	public void render() {
		if (GameFight.getInstance().getGlobalEvents().isAssertSpecial(Flag.nobardisplay))
			return;
		
		if (GameFight.getInstance().getGameState().getRoundState() != Roundstate.COMBAT) {
			thisCustompalFx.getMul().setA(0f);
			return;
		} else if (thisCustompalFx.getMul().getA() < 255) {
			thisCustompalFx.getMul().setA(thisCustompalFx.getMul().getA() + 1f);
		}
		
		
		
		MugenDrawer md = GraphicsWrapper.getInstance();
		if (GameFight.getInstance().getTeamOneMode() == TeamMode.SINGLE) {
			Lifebar lifebar = GameFight.getInstance().getFightdef().getLifebar();
			PlayerLifebar plb = lifebar.getP1();
			
			List<Integer> order = new ArrayList<Integer>();
			order.addAll(plb.getBg().keySet());
			Collections.sort(order);
			Map<Integer, Type> map = plb.getBg();
			
			Point pos = plb.getPos();
			for (Integer key: order) {
				Type bg = map.get(key);
				render(md, pos, bg);
			}

			float zero = plb.getRange().getX().x;
			float full = plb.getRange().getX().x;
			
			render(md, pos, plb.getMid(), plb.getRange().getX().x, plb.getRangeMid().getX().y);
			render(md, pos, plb.getFront(), plb.getRange().getX().x, plb.getRangeFront().getX().y);
			
		}
		
		if (GameFight.getInstance().getTeamTwoMode() == TeamMode.SINGLE) {
			Lifebar lifebar = GameFight.getInstance().getFightdef().getLifebar();
			PlayerLifebar plb = lifebar.getP2();
			
			List<Integer> order = new ArrayList<Integer>();
			order.addAll(plb.getBg().keySet());
			Collections.sort(order);
			Map<Integer, Type> map = plb.getBg();
			
			Point pos = plb.getPos();
			for (Integer key: order) {
				Type bg = map.get(key);
				render(md, pos, bg);
			}

			render(md, pos, plb.getMid(), plb.getRange().getX().x, plb.getRangeMid().getX().y);
			render(md, pos, plb.getFront(), plb.getRange().getX().x, plb.getRangeFront().getX().y);
			
		}
		
	}

	@Override
	public void setPriority(int p) {
	}
	
}
