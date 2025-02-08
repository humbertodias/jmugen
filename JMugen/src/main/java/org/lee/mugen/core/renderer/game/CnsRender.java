package org.lee.mugen.core.renderer.game;

import java.awt.Color;
import java.awt.Rectangle;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.renderer.GraphicsWrapper;
import org.lee.mugen.renderer.MugenDrawer;
import org.lee.mugen.renderer.Renderable;
import org.lee.mugen.sprite.base.AbstractSprite;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.stage.Stage;

public class CnsRender implements Renderable {
	
	private AbstractSprite sprite;
	private boolean isProcess = true;
	
	private boolean showCns = false;
	private boolean showAttackCns = false;
	
	public void setProcess(boolean isProcess) {
		this.isProcess = isProcess;
	}
	public CnsRender(AbstractSprite sprite) {
		this.sprite = sprite;
	}
	public int getPriority() {
		return 250;
	}

	public boolean isProcess() {
		return isProcess;
	}

	public boolean remove() {
		return false;
	}

	
	
	public void render() {
		MugenDrawer g = GraphicsWrapper.getInstance();
		boolean isFlip = sprite.isFlip();
		Stage stage = GameFight.getInstance().getStage();
		int _mvX = stage.getCamera().getX();
		int _mvY = stage.getCamera().getY();
	

		int x = 0;
		int y = 0;
		x = _mvX + stage.getCamera().getWidth()/2;
		y = stage.getStageinfo().getZoffset() + _mvY;
		
		if (showCns) {
			g.setColor(Color.MAGENTA);
			int deltaDraw = 5;
			
			g.drawLine(
					x - deltaDraw + (int)sprite.getRealXPos(), y + (int)sprite.getRealYPos(), 
					x + deltaDraw + (int)sprite.getRealXPos(), y +  (int)sprite.getRealYPos());
			g.drawLine(
					x + (int)sprite.getRealXPos(), y - deltaDraw + (int)sprite.getRealYPos(), 
					x + (int)sprite.getRealXPos(), y + deltaDraw + (int)sprite.getRealYPos());
			
			g.setColor(Color.GREEN);
			for (java.awt.Rectangle r: sprite.getCns2()) {
				r.setLocation(x + r.x, y + r.y);
	            g.draw(r);
				
			}
			
			// Draw Width, height
			if (sprite instanceof Sprite) {
				Sprite spr = (Sprite) sprite;
				g.setColor(Color.YELLOW);
				int topX = (int) (isFlip? spr.getInfo().getWidth().getFront(): spr.getInfo().getWidth().getBack());
				int bottomX = (int) (isFlip? spr.getInfo().getWidth().getBack(): spr.getInfo().getWidth().getFront());
				int topY = (int) (spr.getInfo().getSize().getHeight());
				int bottomY = 0;
				
				topX = (int) (sprite.getRealXPos() - topX);
				bottomX = (int) (sprite.getRealXPos() + bottomX);
				topY = (int) (sprite.getRealYPos() - topY);
				bottomY = (int) (sprite.getRealYPos());
				
				Rectangle r = new Rectangle(topX, topY, Math.abs(bottomX - topX), Math.abs(bottomY - topY));
				r.translate(_mvX + stage.getCamera().getWidth()/2, (stage.getStageinfo().getZoffset() + _mvY));
				g.draw(r);
				
			}
		}
		
		if (showAttackCns) {
			g.setColor(Color.RED);
			
			for (java.awt.Rectangle r: sprite.getCns1()) {
				r.setLocation(x + r.x, y + r.y);
	            g.draw(r);
				
			}
		}
	}
	public void setPriority(int p) {
		// TODO Auto-generated method stub
		
	}
	public boolean isShowCns() {
		return showCns;
	}
	public void setShowCns(boolean showCns) {
		this.showCns = showCns;
	}
	public boolean isShowAttackCns() {
		return showAttackCns;
	}
	public void setShowAttackCns(boolean showAttackCns) {
		this.showAttackCns = showAttackCns;
	}

}
