package org.lee.mugen.core.renderer.game.system;

import org.lee.mugen.geom.MugenPoint;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.core.GameMenu;
import org.lee.mugen.core.renderer.game.fight.BaseRender;
import org.lee.mugen.fight.section.elem.FontType;
import org.lee.mugen.fight.system.MugenSystem;
import org.lee.mugen.fight.system.TitleInfo;
import org.lee.mugen.fight.system.elem.ItemName;
import org.lee.mugen.fight.system.elem.Menu;
import org.lee.mugen.object.Rectangle;
import org.lee.mugen.renderer.GraphicsWrapper;
import org.lee.mugen.renderer.MugenDrawer;

public class TitleInfoRender extends BaseRender {
	BackgroundRender br;
	GameMenu gameMenu;
	@Override
	public int getPriority() {
		return 100;
	}
	public TitleInfoRender(GameMenu gameMenu) {
		br = new BackgroundRender(MugenSystem.getInstance().getTitleBackground());
		this.gameMenu = gameMenu;
	}
	@Override
	public void render() {
		
		MugenDrawer md = GraphicsWrapper.getInstance();
		MugenSystem ms = MugenSystem.getInstance();
		TitleInfo titleInfo = ms.getTitleInfo();
		Menu menu = titleInfo.getMenu();
		if (titleInfo.getPhase() == TitleInfo.ENTER) {
			float alpha = (float)(ms.getTitleInfo().getFadein().getTime()) / ms.getTitleInfo().getFadein().getOriginalTime();
			md.setAlpha(1f-alpha);
			
		} else if (titleInfo.getPhase() == TitleInfo.LEAVE) {
			float alpha = (float)(ms.getTitleInfo().getFadeout().getTime()) / ms.getTitleInfo().getFadeout().getOriginalTime();
			md.setAlpha(alpha);
			
		} else if (titleInfo.getPhase() == TitleInfo.CURRENT) {
			md.setAlpha(1f);
		} else if (titleInfo.getPhase() == TitleInfo.NOTHING) {
			md.setAlpha(1f);
		} else if (titleInfo.getPhase() == TitleInfo.END) {
			md.setAlpha(0);
		}
		
		MugenPoint pos = (MugenPoint) menu.getPos().clone();
			
		ItemName itemName = menu.getItemname();
		String[] strs = itemName.getList();
		
		br.render();
		int count = menu.getWindow$visibleitems();
		int base = gameMenu.getLastStartIndex();//menu.getItemname().startIndex();
		int add = gameMenu.getaddPixel();
		Rectangle box = (Rectangle) menu.getBoxcursor$coords().clone();
		GraphicsWrapper.getInstance().setClip(
				new Rectangle(
						pos.x + box.getX1(), 
						pos.y + box.getY1(), 
						pos.x + box.getX2(), 
						pos.y + (-box.getY1() + box.getY2()) * count));
		pos.y += add;
		pos = new MugenPoint();
		pos.y += menu.getWindow$margins$y().x;
		pos.x = -box.getX1();
		pos.y += add;
		
		for (int i = base; i < strs.length; i++) {
			
			if (i == itemName.getCurrentIndex()) {
				if (menu.getBoxcursor$visible() != 0) {
					float coef = (float) Math.abs(Math.sin(gameMenu.getTime()/10f));
					md.setColor(0.4f, 0.4f, 0.4f, coef - 0.2f);
					md.fillRect(pos.x + box.getX1(), pos.y + box.getY1(), 
							-box.getX1() + box.getX2(), -box.getY1() + box.getY2());
					md.setColor(1f, 1f, 1f, 1f);
				}
				FontType fontType = (FontType) menu.getItem().getActive().getType();
				fontType.setText(strs[i]);
				render(md, pos, menu.getItem().getActive());
				pos.x += menu.getItem().getSpacing().x;
				pos.y += menu.getItem().getSpacing().y;
				
			} else {
				FontType fontType = (FontType) menu.getItem().getType();
				fontType.setText(strs[i]);
				render(md, pos, menu.getItem());
				pos.x += menu.getItem().getSpacing().x;
				pos.y += menu.getItem().getSpacing().y;
				
			}
		}
//		GraphicsWrapper.getInstance().setClip(null);
		
//		md.setClip(new Rectangle(
//				159,
//				158,
//				200,
//				200
//		));
		md.setClip(null);
//		md.setAlpha(1f);
	}
	
}
