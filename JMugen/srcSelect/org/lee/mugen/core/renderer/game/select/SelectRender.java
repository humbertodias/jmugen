package org.lee.mugen.core.renderer.game.select;

import java.awt.Dimension;
import java.awt.Point;
import java.util.Iterator;

import org.apache.commons.beanutils.PropertyUtils;
import org.lee.mugen.core.gameSelect.GameSelect;
import org.lee.mugen.core.gameSelect.SelectionController;
import org.lee.mugen.core.renderer.game.fight.BaseRender;
import org.lee.mugen.core.renderer.game.system.BackgroundRender;
import org.lee.mugen.fight.section.elem.CommonType;
import org.lee.mugen.fight.section.elem.FontType;
import org.lee.mugen.fight.section.elem.Type;
import org.lee.mugen.fight.select.Characters;
import org.lee.mugen.fight.system.MugenSystem;
import org.lee.mugen.fight.system.SelectInfo;
import org.lee.mugen.fight.system.elem.Cell;
import org.lee.mugen.fight.system.elem.StageDisplay;
import org.lee.mugen.object.Rectangle;
import org.lee.mugen.renderer.DrawProperties;
import org.lee.mugen.renderer.GraphicsWrapper;
import org.lee.mugen.renderer.ImageContainer;
import org.lee.mugen.renderer.MugenDrawer;
import org.lee.mugen.renderer.RGB;
import org.lee.mugen.sprite.entity.PointF;

public class SelectRender extends BaseRender {
	@Override
	public int getPriority() {
		return 100;
	}
	BackgroundRender br;
	GameSelect gs;
	public SelectRender(GameSelect gameSelect) {
		br = new BackgroundRender(MugenSystem.getInstance().getSelectBackground());
		gs = gameSelect;
	}
	
	
	@Override
	public void render() {
		
		MugenDrawer md = GraphicsWrapper.getInstance();
		MugenSystem ms = MugenSystem.getInstance();
		SelectInfo selectInfo = MugenSystem.getInstance().getSelectInfo();
		RGB rgb = new RGB(1,1,1);
		md.setColor(rgb.getR(), rgb.getG(), rgb.getB(), rgb.getA());
		md.fillRect(0, 0, 640, 480);
		br.render();
		md.setAlpha(1);
		
		
		////// Title
		Type title = (Type) selectInfo.getTitle().clone();
		FontType fontType = (FontType) title.getType();
		fontType.setText("Arcade");
		render(md, null, title);
		
		
		///// Cell
		Cell cell = selectInfo.getCell();
		Dimension cellSize = cell.getSize();
		int spacing = cell.getSpacing();
		int row = selectInfo.getRows();
		int col = selectInfo.getColumns();
		Point pos = selectInfo.getPos();

		for (int c = 0; c < col; c++) {
			for (int r = 0; r < row; r++) {
				render(md, new Point(pos.x + c * cellSize.width + c * spacing, pos.y + r * cellSize.height + r * spacing), cell.getBg());
			}
		}
		
		Characters characters = ms.getFiles().getSelect().getCharacters();
		Iterator<String> iterCharacter = characters.getCharactersOrder().iterator();
		String[] names = new String[gs.getSelectionControllers().length];
		
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				Point p = new Point(pos.x + c * cellSize.width + c * spacing, pos.y + r * cellSize.height + r * spacing);
				Point currentPos = new Point(r,c);
				
				if (iterCharacter.hasNext()) {
					String character = iterCharacter.next();
					render(md, p, characters.getPortrait(character));
					
					for (SelectionController sc: gs.getSelectionControllers()) {
						if (sc.getPosition().x == r && sc.getPosition().y == c) {
							names[Integer.parseInt(sc.getId()) - 1] = character;
						}
					}
				}
				long time = System.currentTimeMillis();
				long mod = time % gs.getSelectionControllers().length;
				int scPos = 0;
				for (SelectionController sc: gs.getSelectionControllers()) {
					if (!sc.getPosition().equals(currentPos))
						continue;
					if (gs.isOverlap(sc)) {
						if (sc.isCharSelected()) {
							Type type = (Type) getProperty(selectInfo, "p" + sc.getId() + ".cursor.done");
							render(md, p, type);
						} else {
							if (scPos == mod) {
								Type type = (Type) getProperty(selectInfo, "p" + sc.getId() + ".cursor.active");
								render(md, p, type);
							}
						}
					} else {
						Type type = null;
						if (sc.isCharSelected()) {
							type = (Type) getProperty(selectInfo, "p" + sc.getId() + ".cursor.done");
						} else {
							type = (Type) getProperty(selectInfo, "p" + sc.getId() + ".cursor.active");
						}
						render(md, p, type);
					}
					scPos++;
				}
				
			}
		}
		
		////////////////////
		// Big Portrait
		int posName = 1;
		for (String name: names) {
			if (name == null) {
			} else if (characters.getBigPortrait(name) != null) {

				pos = (Point) getProperty(selectInfo, 
						"p" + posName + ".face.offset");
				pos = (Point) pos.clone();
				int ifacing = (Integer) getProperty(selectInfo, 
						"p" + posName + ".face.facing");
				boolean facing = ifacing == -1;
				PointF scale = (PointF) getProperty(selectInfo, 
						"p" + posName + ".face.scale");
				if (facing) {
					pos.x -= characters.getBigPortrait(name).getWidth() * scale.getX();
				}
				CommonType type = (CommonType) getProperty(selectInfo, 
						"p" + posName + ".face.type");
				if (type == null) {
					render(md, pos, characters.getBigPortrait(name), facing, false, scale);
				}
			}
			posName++;
		}
		/////////////
		// Name
		posName = 1;
		for (String name: names) {
			if (name != null) {
				org.lee.mugen.fight.section.elem.Type type = (Type) getProperty(selectInfo, 
						"p" + posName + ".name");
				type = (Type) type.clone();
				FontType font = (FontType) type.getType();
				font.setText(characters.getSpriteName(name) == null? name: characters.getSpriteName(name));
				
				render(md, null, type);
				
			}
			posName++;
		}

		////////////
		// Stage
		StageDisplay stageDisplay = selectInfo.getStagedisplay();
		if (stageDisplay != null && gs.getStageBackgroundRender() != null &&  stageDisplay.isEnable()) {
			
			int width = stageDisplay.getRectangle().width;
			int height = stageDisplay.getRectangle().height;

			float xCoef = stageDisplay.getScale().getX();
			float yCoef = stageDisplay.getScale().getY();
			int x = stageDisplay.getPos().x;
			int y = stageDisplay.getPos().y;
			Rectangle r = new Rectangle(x, y, x + (int) (width * xCoef), y + (int) (height * yCoef));

			md.setClip(r);
			md.scale(xCoef, yCoef);
			gs.getStageBackgroundRender().render();
			md.scale(1f/xCoef, 1f/yCoef);
			md.setClip(null);

			
		}
		
		//////////////
		// Stage text
		

		Type done = (Type) selectInfo.getStage().getDone().clone();
		FontType font = (FontType) done.getType();
		String name = ms.getFiles().getSelect().getExtraStages().getRealName(gs.getCurrentStageNameFile());
		font.setText(name);
		render(md, 
				selectInfo.getStage().getPos(), 
				done);


	}

	private Object getProperty(Object o, String path) {
		try {
			return PropertyUtils.getNestedProperty(o, path);
		} catch (Exception e) {
			throw new IllegalArgumentException("Unknown path " + path + " for " + o.getClass());
		} 
	}

	private void render(MugenDrawer md, Point p, ImageContainer ic) {
		render(md, p, ic, false, false, new PointF(1,1));
		
	}
	private void render(MugenDrawer md, Point p, ImageContainer ic, boolean isFlipH, boolean isFlipV, PointF scale) {
		DrawProperties dp = new DrawProperties(p.x, p.y, isFlipH, isFlipV, ic);
		dp.setXScaleFactor(scale.getX());
		dp.setYScaleFactor(scale.getY());
		
		md.draw(dp);
		
	}
}
