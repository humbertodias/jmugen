package org.lee.mugen.core.renderer.game.vsScreen;

import java.awt.Point;

import org.apache.commons.beanutils.PropertyUtils;
import org.lee.mugen.core.GameVsScreen;
import org.lee.mugen.core.renderer.game.fight.BaseRender;
import org.lee.mugen.core.renderer.game.system.BackgroundRender;
import org.lee.mugen.fight.section.elem.CommonType;
import org.lee.mugen.fight.section.elem.FontType;
import org.lee.mugen.fight.section.elem.PlayerName;
import org.lee.mugen.fight.section.elem.Type;
import org.lee.mugen.fight.select.Characters;
import org.lee.mugen.fight.system.MugenSystem;
import org.lee.mugen.fight.system.TitleInfo;
import org.lee.mugen.fight.system.VsScreen;
import org.lee.mugen.fight.system.elem.Menu;
import org.lee.mugen.renderer.DrawProperties;
import org.lee.mugen.renderer.GraphicsWrapper;
import org.lee.mugen.renderer.ImageContainer;
import org.lee.mugen.renderer.MugenDrawer;
import org.lee.mugen.sprite.entity.PointF;

public class VsScreeenRender extends BaseRender {
	BackgroundRender br;
	GameVsScreen gameVsScreen;
	
	public VsScreeenRender(	GameVsScreen gameVsScreen) {
		br = new BackgroundRender(MugenSystem.getInstance().getVersusBackground());
		this.gameVsScreen = gameVsScreen;
	}
	
	@Override
	public void render() {
		MugenDrawer md = GraphicsWrapper.getInstance();
		MugenSystem ms = MugenSystem.getInstance();
		Characters characters = ms.getFiles().getSelect().getCharacters();
		
		VsScreen vsScreen = ms.getVsScreen();
		if (vsScreen.getPhase() == TitleInfo.ENTER) {
			float alpha = (float)(vsScreen.getFadein().getTime()) / vsScreen.getFadein().getOriginalTime();
			md.setAlpha(1f-alpha);
			
		} else if (vsScreen.getPhase() == TitleInfo.LEAVE) {
			float alpha = (float)(vsScreen.getFadeout().getTime()) / vsScreen.getFadeout().getOriginalTime();
			md.setAlpha(alpha);
			
		} else if (vsScreen.getPhase() == TitleInfo.CURRENT) {
			md.setAlpha(1f);
		} else if (vsScreen.getPhase() == TitleInfo.NOTHING) {
			md.setAlpha(1f);
		} else if (vsScreen.getPhase() == TitleInfo.END) {
			md.setAlpha(0);
		}
		
		br.render();
		
		Point pos = vsScreen.getP1().getPos();
		
		pos = (Point) pos.clone();
		int ifacing = (Integer) getProperty(vsScreen, 
				"p" + 1 + ".facing");
		boolean facing = ifacing == -1;
		float fy = 140f/characters.getBigPortrait(gameVsScreen.getName(0)).getHeight();
		float fx = 120f/characters.getBigPortrait(gameVsScreen.getName(0)).getWidth();
		PointF aScale = new PointF(fx,fy);
		
		PointF scale = (PointF) getProperty(vsScreen, 
				"p" + 1 + ".scale");
		scale = new PointF(scale.getX() * aScale.getX(), scale.getY() * aScale.getY());
		if (facing) {
			pos.x -= characters.getBigPortrait(gameVsScreen.getName(0)).getWidth() * scale.getX();
		}
		render(md, pos, characters.getBigPortrait(gameVsScreen.getName(0)), facing, false, scale);
		
		
		////
		
		pos = vsScreen.getP2().getPos();
			
		pos = (Point) pos.clone();
		ifacing = (Integer) getProperty(vsScreen, 
				"p" + 2 + ".facing");
		facing = ifacing == -1;
		scale = (PointF) getProperty(vsScreen, 
				"p" + 2 + ".scale");
		fy = 140f/characters.getBigPortrait(gameVsScreen.getName(1)).getHeight();
		fx = 120f/characters.getBigPortrait(gameVsScreen.getName(1)).getWidth();
		aScale = new PointF(fx,fy);
		
		scale = new PointF(scale.getX() * aScale.getX(), scale.getY() * aScale.getY());
		if (facing) {
			pos.x -= characters.getBigPortrait(gameVsScreen.getName(1)).getWidth() * scale.getX();
		}
		render(md, pos, characters.getBigPortrait(gameVsScreen.getName(1)), facing, false, scale);
		
		
		///////////// NAME
		Type type = (Type) getProperty(vsScreen, 
				"p" + 1 + ".name");
		type = (Type) type.clone();
		FontType font = (FontType) type.getType();
		font.setText(characters.getSpriteName(gameVsScreen.getName(0)) == null? gameVsScreen.getName(0): characters.getSpriteName(gameVsScreen.getName(0)));
		
		render(md, vsScreen.getP1().getName().getPos(), type);
		
		
		
		
		type = (Type) getProperty(vsScreen, 
				"p" + 2 + ".name");
		type = (Type) type.clone();
		font = (FontType) type.getType();
		font.setText(characters.getSpriteName(gameVsScreen.getName(1)) == null? gameVsScreen.getName(1): characters.getSpriteName(gameVsScreen.getName(1)));
		
		render(md, vsScreen.getP2().getName().getPos(), type);
		
	}
	private void render(MugenDrawer md, Point p, ImageContainer ic, boolean isFlipH, boolean isFlipV, PointF scale) {
		DrawProperties dp = new DrawProperties(p.x, p.y, isFlipH, isFlipV, ic);
		dp.setXScaleFactor(scale.getX());
		dp.setYScaleFactor(scale.getY());
		
		md.draw(dp);
		
	}
	private Object getProperty(Object o, String path) {
		try {
			return PropertyUtils.getNestedProperty(o, path);
		} catch (Exception e) {
			throw new IllegalArgumentException("Unknow path " + path + " for " + o.getClass());
		} 
	}
}
