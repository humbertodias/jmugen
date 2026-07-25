package org.lee.mugen.core.renderer.game;

import org.lee.mugen.background.BG;
import org.lee.mugen.background.BGCtrlDef;
import org.lee.mugen.core.GameFight;
import org.lee.mugen.core.sound.SoundSystem;
import org.lee.mugen.object.Rectangle;
import org.lee.mugen.parser.air.AirData;
import org.lee.mugen.parser.air.AirData.TypeBlit;
import org.lee.mugen.renderer.DrawProperties;
import org.lee.mugen.renderer.GraphicsWrapper;
import org.lee.mugen.renderer.ImageContainer;
import org.lee.mugen.renderer.MugenDrawer;
import org.lee.mugen.renderer.PalFxSub;
import org.lee.mugen.renderer.RGB;
import org.lee.mugen.renderer.Trans;
import org.lee.mugen.sprite.base.AbstractAnimManager;
import org.lee.mugen.sprite.baseForParse.ImageSpriteSFF;
import org.lee.mugen.sprite.baseForParse.SpriteSFF;
import org.lee.mugen.sprite.entity.SuperpauseSub;
import org.lee.mugen.stage.Stage;

public class StageBackgroundRender implements IBackgroundRenderer {
	Stage stage;
	MugenDrawer md = null;
	MugenDrawer getMugenDrawer() {
		if (md == null) {
			md = GraphicsWrapper.getInstance();
		}
		return md;
	}
	public StageBackgroundRender(Stage stage, MugenDrawer md) {
		this.stage = stage;
		this.md = md;

	}
	public StageBackgroundRender(Stage stage) {
		this.stage = stage;

	}

	private void drawTileY(ImageContainer img, BG bg, float x,
			float y, float moveX, float moveY, int xStartForAll, 
			Trans trans, boolean isHFlip, boolean isVFlip) {
		Rectangle r = bg.getWindow();
		int yTile = (int) bg.getTile().getY();
		float startPosY = y ;
		float deltaY = bg.getDelta().getY();
		float deltaX = bg.getDelta().getX();
		
		if (yTile == 0) {
//			drawImage(trans, img, (startPosY + (bg.getTilespacing().getY())
//					* bg.getDelta().getX())
//					+ xStartForAll, (y + moveY * bg.getDelta().getY()), isHFlip, isVFlip);
		
		} else if (yTile == 1) {
			float tileSpacingY = bg.getTilespacing() == null? img.getHeight(): bg.getTilespacing().getY();
			startPosY = y + tileSpacingY;
			while (startPosY < 240/stage.getScaling().getYscale()) {
				drawImage(bg.getAlphaOne(), trans, img, x, startPosY, isHFlip, isVFlip);
				startPosY += tileSpacingY;
			}
			
			startPosY = y - tileSpacingY;
			while (startPosY + img.getHeight() > 0) {
				drawImage(bg.getAlphaOne(), trans, img, x, startPosY, isHFlip, isVFlip);
				startPosY -= tileSpacingY;
			}
			
			
		} else {
			float tilespacingY = bg.getTilespacing() == null? img.getHeight(): bg.getTilespacing().getY();
			
			startPosY = (y + moveY) % tilespacingY;
			yTile--;
			while (startPosY < 240 && yTile > 0) {
				drawImage(bg.getAlphaOne(), trans, img, x, (startPosY + (tilespacingY)
								* deltaY), isHFlip, isVFlip);
				startPosY += img.getHeight();
				yTile--;
			}
		}
	}
	
	private void drawTileXY(ImageContainer img, BG bg, float x,
			float y, float moveX, float moveY, int xStartForAll, 
			Trans trans, boolean isHFlip, boolean isVFlip) {
		if (!bg.isEnable())
			return;
		int xTile = (int) bg.getTile().getX();
		float startPosX = x;
		float tileSpacingX = (bg.getTilespacing() == null? img.getWidth() : bg.getTilespacing().getX() == 0? img.getWidth(): bg.getTilespacing().getX());
		float tileSpacingY = (bg.getTilespacing() == null? img.getHeight() : bg.getTilespacing().getY() == 0? img.getHeight():bg.getTilespacing().getY());
		float deltaY = bg.getDelta().getY();
		float deltaX = bg.getDelta().getX();
		if (xTile == 0) {
			startPosX = x + moveX * deltaX + xStartForAll;
			
			float yDraw = (y + moveY * deltaY);
			if (bg.getTile().getY() > 0)
				yDraw = (y + moveY * deltaY) % (tileSpacingY);
			drawImage(bg.getAlphaOne(), trans, img, startPosX,  yDraw, isHFlip, isVFlip);

			drawTileY(img, bg, startPosX, yDraw + img.getHeight(), moveX, moveY, xStartForAll, trans, isHFlip, isVFlip);
			startPosX += tileSpacingX;

		} else if (xTile > 1) {
			startPosX = (x + moveX * deltaX + xStartForAll) % img.getWidth();
			
			while (startPosX < 320/stage.getScaling().getXscale() && (xTile > 0 || bg.getTile().getX() == 1)) {
				
				float yDraw = (y + moveY * deltaY);
				if (bg.getTile().getY() > 0)
					yDraw = (y + moveY * deltaY) % (tileSpacingY);
				drawImage(bg.getAlphaOne(), trans, img, startPosX,  yDraw, isHFlip, isVFlip);

				drawTileY(img, bg, startPosX, yDraw, moveX, moveY, xStartForAll, trans, isHFlip, isVFlip);
				startPosX += tileSpacingX;

			}
		} else if (bg.getTile().getX() == 1) {
			
			startPosX =  (x + moveX * deltaX + xStartForAll) % (tileSpacingX);
				
			//			startPosX = 300;
			while (startPosX < (320f / stage.getScaling().getXscale())) {
				float yDraw = (y + moveY * deltaY);
				if (bg.getTile().getY() > 0)
					yDraw = (y + moveY * deltaY) % (tileSpacingY);
				drawImage(bg.getAlphaOne(), trans, img, startPosX,  yDraw, isHFlip, isVFlip);

				drawTileY(img, bg, startPosX, yDraw, moveX, moveY, xStartForAll, trans, isHFlip, isVFlip);
				startPosX += tileSpacingX;

			}
			
			startPosX =  (x + moveX * deltaX + xStartForAll) % (tileSpacingX);
			startPosX -= tileSpacingX;
			while (startPosX + img.getWidth() > 0) {
				
				float yDraw = (y + moveY * deltaY);
				if (bg.getTile().getY() > 0)
					yDraw = (y + moveY * bg.getDelta().getY()) % (tileSpacingY);
				drawImage(bg.getAlphaOne(), trans, img, startPosX,  yDraw, isHFlip, isVFlip);

				drawTileY(img, bg, startPosX, yDraw, moveX, moveY, xStartForAll, trans, isHFlip, isVFlip);
				startPosX -= tileSpacingX;
			}
		}

	}


	private void drawImage(float alpha, Trans trans, ImageContainer img, float x, float y, boolean isHFlip, boolean isVFlip) {

		PalFxSub palfx = GameFight.getInstance().getGlobalEvents().getBgpalfx();

		DrawProperties dp = 
			new DrawProperties(x, y, isHFlip, isVFlip, img);
		if (!palfx.isNoPalFx()) {
			palfx.setDrawProperties(dp);
		}
		if (trans == Trans.ADDALPHA) {
			dp.setAlpha(alpha);
		}
		dp.setTrans(trans);
		
		if (GameFight.getInstance().getGlobalEvents().isSuperPause()) {
			palfx = new PalFxSub();
			palfx.setMul(new RGB(100f, 100f, 100f, 100f ));
			palfx.setDrawProperties(dp);
		}
		getMugenDrawer().draw(dp);	
	}
	private void drawImage(Trans trans, ImageContainer img, float x, float y, boolean isHFlip, boolean isVFlip) {
		drawImage(1f, trans, img, x, y, isHFlip, isVFlip);

	}



	private int layerDisplay = 0;


	public void render() {
		int xStartForAll = (int) (stage.getCamera().getWidth()/2 * 1f/stage.getScaling().getXscale());
		
		float _mvX = stage.getCamera().getX();
		float _mvY = stage.getCamera().getY();
		SpriteSFF sffSprite = stage.getBackground().getBgdef().getSpr();

		getMugenDrawer().scale(stage.getScaling().getXscale(), stage.getScaling().getYscale());

		float moveX = _mvX;
		float moveY = _mvY;
		for (BG bg : stage.getBackground().getBgs()) {
			if (bg.getLayerno() != layerDisplay)
				continue;

			try {
				if (bg.getBgType() == BG.Type.NORMAL
						|| bg.getBgType() == BG.Type.NORM) {
					int grpno = 0;
					try {
						grpno = bg.getSpriteno().getSpritegrp();
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					int imgno = bg.getSpriteno().getSpriteno();
					ImageSpriteSFF imgSprSff = sffSprite.getGroupSpr(grpno).getImgSpr(imgno);
					ImageContainer img = (ImageContainer) imgSprSff.getImage();
					img = getImgProcessSuperpause(img);



					float x =  (bg.getPos().getX() - imgSprSff.getXAxis());
					float y =  bg.getPos().getY() - imgSprSff.getYAxis();
//
					drawTileXY(img, bg, x, y, moveX, moveY,
							xStartForAll, bg.getBgTrans(), false, false);

				} else if (bg.getBgType() == BG.Type.ANIM && bg.getId() == null) {

					AbstractAnimManager animMng = bg.getAnimManager();
					if (animMng.getCurrentImageSprite() == null)
						continue;
					AirData air = animMng.getCurrentImageSprite().getAirData();
					int grpno = air.getGrpNum();
					int imgno = air.getImgNum();
					if (sffSprite.getGroupSpr(grpno) == null)
						continue;
					ImageSpriteSFF imgSprSff = sffSprite.getGroupSpr(grpno)
							.getImgSpr(imgno);
					if (imgSprSff == null)
						continue;
					ImageContainer img = (ImageContainer) imgSprSff.getImage();
					img = getImgProcessSuperpause(img);

					boolean isVFlip = air.isMirrorV();
					boolean isHFlip = air.isMirrorH();

					float x =  (bg.getPos().getX() - imgSprSff.getXAxis() + air.getXOffSet());
					float y =  bg.getPos().getY() - imgSprSff.getYAxis() + air.getYOffSet();

					Trans trans = bg.getBgTrans();
					trans = air.type == TypeBlit.ASD ? Trans.ADD: trans;
					
					drawTileXY(img, bg, x, y, moveX, moveY,
							xStartForAll, trans, isHFlip, isVFlip);

				} else if (bg.getBgType() == BG.Type.ANIM && bg.getId() != null) {
					BGCtrlDef bgdef = stage.getBackground().getBgCtrlDefMap().get(bg.getId());
					if (bgdef == null) {
						System.err.println("This BGCtrlDef doen't exist : " + bg.getId());
						continue;
					}
					bg = bgdef.getBgCopys().get(bg.getOrder());
					if (!bg.isVisible())
						continue;
					AbstractAnimManager animMng = bg.getAnimManager();
					if (animMng.getCurrentImageSprite() == null)
						continue;
					AirData air = animMng.getCurrentImageSprite().getAirData();
					int grpno = air.getGrpNum();
					int imgno = air.getImgNum();
					if (sffSprite.getGroupSpr(grpno) == null || sffSprite.getGroupSpr(grpno).getImgSpr(imgno) == null)
						continue;
					
					ImageSpriteSFF imgSprSff = sffSprite.getGroupSpr(grpno)
							.getImgSpr(imgno);
					ImageContainer img = (ImageContainer) imgSprSff.getImage();
					img = getImgProcessSuperpause(img);

					
					float x =  (bg.getPos().getX() - imgSprSff.getXAxis() + air.getXOffSet());
					float y =  bg.getPos().getY() - imgSprSff.getYAxis() + air.getYOffSet();
					
					boolean isVFlip = air.isMirrorV();
					boolean isHFlip = air.isMirrorH();
					
					Trans trans = bg.getBgTrans();
					trans = air.type == TypeBlit.ASD ? Trans.ADD: trans;
					
					drawTileXY(img, bg, x, y, moveX, moveY,
							xStartForAll, trans, isHFlip, isVFlip);
					

				} else if (bg.getBgType() == BG.Type.PARALLAX) {
					int grpno = bg.getSpriteno().getSpritegrp();
					int imgno = bg.getSpriteno().getSpriteno();
					ImageSpriteSFF imgSprSff = sffSprite.getGroupSpr(grpno)
							.getImgSpr(imgno);
					ImageContainer img = (ImageContainer) imgSprSff.getImage();
					img = getImgProcessSuperpause(img);


					float x =  (bg.getPos().getX() - imgSprSff.getXAxis());
					float y =  bg.getPos().getY() - imgSprSff.getYAxis();


					for (int v = 0; v < img.getHeight(); ++v) {
						float deltaY = bg.getXscale().getX()
								+ (v * ((bg.getXscale().getY() - bg
										.getXscale().getX()) / img.getHeight()));
						deltaY = deltaY * bg.getDelta().getX();
						float x1 = (x + (deltaY * moveX)) + xStartForAll;
						float y2 = (y + moveY * bg.getDelta().getY()) + v;
						if (!bg.isEnable())
							continue;
						drawImage(bg.getTrans(), img, x1, y2, x1 + img.getWidth(), y2 + 1,
								0, v, img.getWidth(), v + 1);
					}
				}

			} catch (Exception e) {
				 e.printStackTrace();

				continue;
			}
		}
		getMugenDrawer().scale(1f/stage.getScaling().getXscale(), 1f/stage.getScaling().getYscale());

	}

	private void drawImage(Trans trans, ImageContainer img, float xl, float yt, float xr,
			float yb, int xlSrc, int yTopSrc, int xrSrc, int yBottomSrc) {
		PalFxSub palfx = GameFight.getInstance().getGlobalEvents().getBgpalfx();

		DrawProperties dp = 
			new DrawProperties(xl, xr, yt,
					yb, xlSrc, xrSrc, yTopSrc, yBottomSrc, false, false, img);
		dp.setTrans(trans);
		if (!palfx.isNoPalFx()) {
			palfx.setDrawProperties(dp);
		}
		getMugenDrawer().draw(dp);
		
	}

	private ImageContainer getImgProcessSuperpause(ImageContainer img) {
		if (GameFight.getInstance().getGlobalEvents().isSuperPause()) {
			SuperpauseSub superpause = GameFight.getInstance().getGlobalEvents().getSuperpause();
			for (int type: superpause.getDarken()) {
//				img = superpause.getFilter(type).filter(img, null);
			}
		}

		return img;
	}
	
	public int getPriority() {
		return 100;
	}

	public boolean isProcess() {
		return true;
	}

	public boolean remove() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.lee.mugen.core.renderer.game.java.IBackgroundRenderer#getLayerDisplay()
	 */
	public int getLayerDisplay() {
		return layerDisplay;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.lee.mugen.core.renderer.game.java.IBackgroundRenderer#setLayerDisplay
	 */
	public void setLayerDisplay(int layerDisplay) {
		this.layerDisplay = layerDisplay;
	}

	public void setPriority(int p) {
		// TODO Auto-generated method stub

	}

}
