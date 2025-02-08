package org.lee.mugen.core.renderer.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.renderer.GraphicsWrapper;
import org.lee.mugen.renderer.Renderable;
import org.lee.mugen.renderer.GameWindow.MouseCtrl;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.character.SpriteCns;
import org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns.Backedgebodydist;
import org.lee.mugen.sprite.common.resource.FontParser;
import org.lee.mugen.sprite.common.resource.FontProducer;
import org.lee.mugen.stage.Stage;


public class DebugRender implements Renderable {
	public static final DebugRender debugRender = new DebugRender();
	
	
	
	public int getPriority() {
		return Integer.MAX_VALUE;
	}

	public boolean isProcess() {
		return true;
	}

	public boolean remove() {
		return false;
	}

	
	private Sprite current;
	
	public void nextSprite() {
		
		ArrayList<Sprite> sprites = new ArrayList<Sprite>();
		for (Sprite spr: GameFight.getInstance().getSprites()) {
//			if (!(spr instanceof SpriteHelper)) {
				sprites.add(spr);
//			}
		}
		Collections.sort(sprites, new Comparator<Sprite>() {

			public int compare(Sprite o1, Sprite o2) {
				return o1.getSpriteId().compareTo(o2.getSpriteId());
			}});
		if (current == null) {
			current = sprites.get(0);
		} else {
			int index = sprites.indexOf(current);
			if (index == sprites.size() - 1)
				current = null;
			else if (index == -1)
				current = sprites.get(0);
			else
				current = sprites.get(index + 1);
			
		}
	}
	
	public void render() {
		try {
			displayHelp();
			if (!GameFight.getInstance().getSprites().contains(current)) {
				current = null;
			}
			if (current == null)
				return;

			Sprite sprite = current;
			SpriteCns sprInfo = sprite.getInfo();
			Stage stage = GameFight.getInstance().getStage();
			int _mvX = stage.getCamera().getX();
			int _mvY = stage.getCamera().getY();
			int x = 10;
			int y = 150;
			int left = stage.getBound().getScreenleft();
			int right = stage.getBound().getScreenright();

			int leftLimit = left + stage.getCamera().getBoundleft()
					- stage.getCamera().getWidth() / 2
					+ stage.getCamera().getTension();

			int rightLimit = -right + stage.getCamera().getBoundright()
					+ stage.getCamera().getWidth() / 2
					- stage.getCamera().getTension();
			
			String[] strSpriteInfos = {
				"Author : " + sprite.getDefinition().getInfo().getAuthor() + " - " + "backedgebodydist : " + Backedgebodydist.compute(sprite)
				,"Name : " + sprite.getDefinition().getInfo().getName()
				,"SpriteID : " + sprite.getSpriteId() + " - IsFlip : " + sprite.isFlip() + " " + "Debug " + sprite.isDebugRender()
				,"Game Fps = " + GameFight.getInstance().getWindow().getTimer().getFps() 
				+ " - Camera : " + (-stage.getCamera().getXNoShaKe()) + ", " + stage.getCamera().getYNoShake()
			};
			String[] strSpriteInfos2 = {
				"Life = " + sprInfo.getLife()
				,"Power = " + sprInfo.getPower()
				,"Physics = " + sprInfo.getPhysics().getDescription()
				,"MoveType = " + sprInfo.getMovetype().getDescription()
				,"StateType = " + sprInfo.getType().getDescription()
				,"Ctrl = " + sprInfo.getCtrl()
				,"sprPriority : " + sprite.getPriority()
				,"Pos = " + ((int)sprInfo.getXPos()) + "," + ((int)sprInfo.getYPos())
				,"Vel = " + sprInfo.getVelset().getX() + ", " + sprInfo.getVelset().getY()
			};
			String[] strSpriteInfos3 = {
				"Action = " + sprite.getSprAnimMng().getAction()
				,"AnimElem = " + (sprite.getSprAnimMng().getCurrentGroupSprite() != null ? sprite.getSprAnimMng().getAnimElemNo() + "/" + sprite.getSprAnimMng().getCurrentGroupSprite().getImgSprites().length: "Err")
				,"imgCount = " + sprite.getSprAnimMng().getImgCount()
				,"AnimTime = " + (sprite.getSprAnimMng().getCurrentGroupSprite() != null ?sprite.getSprAnimMng().getAnimTime() + "/" + sprite.getSprAnimMng().getAnimTimeCount(): "Err")
//				,"AnimTimeReal = " + (sprite.getSprAnimMng().getCurrentGroupSprite() != null ?sprite.getSprAnimMng().getAnimTimeReal() + "/" + sprite.getSprAnimMng().getAnimTimeCount(): "Err")
				,"AnimElemTime = " + (sprite.getSprAnimMng().getCurrentGroupSprite() != null ?sprite.getSprAnimMng().getAnimElemTime() + "/" + sprite.getSprAnimMng().getAnimTimeCount(): "Err")
			};
			String[] strSpriteInfos4 = {
				"state = " + (sprite.getSpriteState().getCurrentState() != null? sprite.getSpriteState().getCurrentState().getId():"")
				,"prevstateno = " + sprite.getSpriteState().getPrevstateno()
				,"Time = " + GameFight.getInstance().getGameState().getGameTime()
				,"StateTime = " + sprite.getSpriteState().getTimeInState()
				,"Porjectile Sprite Count : " + GameFight.getInstance().getOtherSprites().size()
				,"Helper Count : " + GameFight.getInstance().countHelper(sprite.getSpriteId())
				
			};
			FontProducer fp = FontParser.getFontProducer();
			int addX = 0;
			MouseCtrl mouse = GraphicsWrapper.getInstance().getInstanceOfGameWindow().getMouseStatus();
			for (String s: strSpriteInfos) {
				if (mouse.getY() >= y && mouse.getY() < y+fp.getSize().height
						&& 
						mouse.getX() >= x && mouse.getX() < x+fp.getSize().width * s.length() 
						&& mouse.isLeftPress() && s.startsWith("SpriteID")
				) {
					sprite.setDebugRender(!sprite.isDebugRender());
				}
				fp.draw(0, x, y+=fp.getSize().height, GraphicsWrapper.getInstance(), s);
				addX = Math.max(addX, s.length());
				
			}
			addX = 0;
			y += fp.getSize().height;
			int yMem = y;
			for (String s: strSpriteInfos2) {
				fp.draw(0, x, y+=fp.getSize().height, GraphicsWrapper.getInstance(), s);
				addX = Math.max(addX, s.length());
			}
			
			x += 95;
			addX = 0;
			y = yMem+= fp.getSize().height*4;
			for (String s: strSpriteInfos3) {
				fp.draw(0, x, y+=fp.getSize().height, GraphicsWrapper.getInstance(), s);
				addX = Math.max(addX, s.length());
			}
			
			x += 100;
			addX = 0;
			y = yMem;
			for (String s: strSpriteInfos4) {
				fp.draw(0, x, y+=fp.getSize().height, GraphicsWrapper.getInstance(), s);
				addX = Math.max(addX, s.length());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Info Err");
		}

	
	}

	private boolean displayHelp = false;
	public boolean isDisplayHelp() {
		return displayHelp;
	}

	public void setDisplayHelp(boolean displayHelp) {
		this.displayHelp = displayHelp;
	}

	private void displayHelp() throws Exception {
		if (displayHelp) {
			FontProducer fp = FontParser.getFontProducer();
			int x = 10;
			int y = 50;
			fp.draw(0, x, y+=fp.getSize().height*2,GraphicsWrapper.getInstance(),"F1         : Display Help");
			fp.draw(0, x, y+=fp.getSize().height, GraphicsWrapper.getInstance(), "CTRL '+/-' : +/- FPS");
			fp.draw(0, x, y+=fp.getSize().height, GraphicsWrapper.getInstance(), "CTRL '*'   : Reset FPS");
			fp.draw(0, x, y+=fp.getSize().height, GraphicsWrapper.getInstance(), "Space      : Init");
			fp.draw(0, x, y+=fp.getSize().height, GraphicsWrapper.getInstance(), "CTRL-D     : Switch Sprite Informations");
			fp.draw(0, x, y+=fp.getSize().height, GraphicsWrapper.getInstance(), "CTRL-C     : Show Cns Box");
			fp.draw(0, x, y+=fp.getSize().height, GraphicsWrapper.getInstance(), "CTRL-X     : Show Cns Attack Box");

			fp.draw(0, x, y+=fp.getSize().height, GraphicsWrapper.getInstance(), "CTRL-P     : Debug Pause");
			fp.draw(0, x, y+=fp.getSize().height, GraphicsWrapper.getInstance(), "CTRL-A     : If Debug Pause Advance Frame By Frame");
			
		}

	}
	
	public void setPriority(int p) {
	}

}
