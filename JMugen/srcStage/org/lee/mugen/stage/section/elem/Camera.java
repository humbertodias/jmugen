package org.lee.mugen.stage.section.elem;


import org.lee.mugen.geom.MugenRect;
import java.util.Random;
import java.util.regex.Matcher;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.core.physics.PhysicsEngime;
import org.lee.mugen.fight.section.Section;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.character.SpriteHelper;
import org.lee.mugen.sprite.entity.Shake;
import org.lee.mugen.sprite.parser.ExpressionFactory;
import org.lee.mugen.stage.Stage;


public class Camera implements Section {
	public boolean isForcePos() {
		return forcePos;
	}



	public void setForcePos(boolean forcePos) {
		this.forcePos = forcePos;
	}

	private boolean forcePos = false;
	private int width = 320;
	private int height = 240;
	private Stage parent = null;
	public Camera(Stage stage) {
		parent = stage;
	}

	private int x;
	private int y;
	
	private int startx = 0;
	private int starty = 0;

	 private int boundleft = -95;
	 private int boundright = 95;

	 private int boundhigh = -25;
	 private int boundlow = 0;

	 private float verticalfollow = .2f;

	 private int floortension = 0;

	 private int tension = 50;

	 
	@Override
	public void parse(Object root, String name, String value) throws Exception {
		if (name.equals("startx")) {
			startx = Integer.parseInt(value);
		} else if (name.equals("starty")) {
			starty = Integer.parseInt(value);
		} else if (name.equals("boundleft")) {
			boundleft = Integer.parseInt(value);
		} else if (name.equals("boundright")) {
			boundright = Integer.parseInt(value);
		} else if (name.equals("boundhigh")) {
			boundhigh = Integer.parseInt(value);
		} else if (name.equals("boundlow")) {
			boundlow = Integer.parseInt(value);
		} else if (name.equals("verticalfollow")) {
			Matcher m = ExpressionFactory.P_FLOAT_REGEX.matcher(value);
			m.find();
			verticalfollow = Float.parseFloat(m.group(1));
		} else if (name.equals("floortension")) {
			floortension = Integer.parseInt(value);
		} else if (name.equals("tension")) {
			tension = Integer.parseInt(value);
		}		
	}
	 
	 
	 
	public int getBoundhigh() {
		return boundhigh;
	}

	public void setBoundhigh(int boundhigh) {
		this.boundhigh = boundhigh;
	}

	public int getBoundleft() {
		return boundleft;
	}

	public void setBoundleft(int boundleft) {
		this.boundleft = boundleft;
	}

	public int getBoundlow() {
		return boundlow;
	}

	public void setBoundlow(int boundlow) {
		this.boundlow = boundlow;
	}

	public int getBoundright() {
		return boundright;
	}

	public void setBoundright(int boundright) {
		this.boundright = boundright;
	}

	public int getFloortension() {
		return floortension;
	}

	public void setFloortension(int floortension) {
		this.floortension = floortension;
	}

	public int getStartx() {
		return startx;
	}

	public void setStartx(int startx) {
		this.startx = startx;
	}

	public int getStarty() {
		return starty;
	}

	public void setStarty(int starty) {
		this.starty = starty;
	}

	public int getTension() {
		return tension;
	}

	public void setTension(int tension) {
		this.tension = tension;
	}

	public float getVerticalfollow() {
		return verticalfollow;
	}

	public void setVerticalfollow(float verticalfollow) {
		this.verticalfollow = verticalfollow;
	}

	private Random r = new Random();
	private Shake envShake = new Shake();
	public MugenRect getCameraView() {
		return new MugenRect(x, y, width, height);
	}
	
	public int getX() {
		return getXNoShaKe() + (envShake.getTime() > 0? r.nextInt(envShake.getAmpl() <= 0? 1: 2): 0);
	}
	
	public int getXNoShaKe() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y + (envShake.getTime() > 0? r.nextInt(envShake.getAmpl() <= 0? 5: 5): 0);
	}
	
	public int getYNoShake() {
		return y;
	}

	
	public void setY(int y) {
		this.y = y;
	
	}

	public void addX(int i) {
		x += i;
	}
	 
	public void addY(int i) {
		y += i;
	}

	public Shake getEnvShake() {
		return envShake;
	}

	public void setEnvShake(Shake envShake) {
		this.envShake = envShake;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void init() {
		x = getStartx();
		y = getStarty();
		
	}



	public void process() {
		if (forcePos) {
			return;
		}
		boolean canMoveX = true;
		boolean canMoveY = true;
		for (Sprite s: GameFight.getInstance().getSprites()) {
			if (s instanceof SpriteHelper)
				continue;
			canMoveX &= s.getInfo().getScreenbound().isCamCanMoveX();
			canMoveY &= s.getInfo().getScreenbound().isCamCanMoveY();
		}
//		if (StateMachine.getInstance().getGlobalEvents().isSuperPause()) {
//			canMoveX = true;
//			canMoveY = true;
//		}
		int xCam = getXNoShaKe();
		int xSpr = 0;

		int[] minMaxX = getMinMaxForXSprite();
		int minX = minMaxX[0];
		int maxX = minMaxX[1];
		xSpr = minX + (maxX - minX)/2;
		
//		if (getEnvShake().getTime() > 0) {
//			
//		} else {
			int left = parent.getBound().getScreenleft();
			int right = parent.getBound().getScreenright();

			int leftLimit = left + getBoundleft();

			int rightLimit = -right + getBoundright();
			
			int diff = xCam + xSpr;
			Sprite sprLeft = GameFight.getInstance().getSpriteInstance("1");
			Sprite sprRight = GameFight.getInstance().getSpriteInstance("1");
			
			
			
			for (Sprite s: GameFight.getInstance().getSprites()) {
				if (s instanceof SpriteHelper)// && (((SpriteHelper)s).getHelperSub().getHelpertype().equals("normal")))
					continue;
				if (sprLeft.getInfo().getXPos() > s.getInfo().getXPos())
					sprLeft = s;
				if (sprRight.getInfo().getXPos() < s.getInfo().getXPos())
					sprRight = s;
				
			}
			
			if (diff < -5 && !PhysicsEngime.isOutOfScreeen(sprRight, 1) && canMoveX)
				addX(1);
			
			if (diff > 5 && !PhysicsEngime.isOutOfScreeen(sprLeft, -1) && canMoveX)
				addX(-1);
			
			
			
			
			////// Y 
			int yCam = getYNoShake();
			int ySpr = 0;
			Sprite highestSpr = null;
			for (Sprite spr: GameFight.getInstance().getSprites()) {
				if (spr instanceof SpriteHelper)
					continue;
				if (highestSpr == null)
					highestSpr = spr;
				if (highestSpr.getInfo().getYPos() > spr.getInfo().getYPos())
					highestSpr = spr;
				ySpr = (int) Math.min(ySpr, spr.getInfo().getYPos());
			}
			int yDiff = yCam - ySpr;
			float yAdd = 0;
			if (highestSpr != null) {
				yAdd = highestSpr.getInfo().getVelset().getY()/2;
				if (ySpr == 0 && highestSpr.getInfo().getVelset().getY() == 0 && yCam != 0)
					yAdd = highestSpr.getInfo().getVelset().getY()/2 + 1;
			}
			
			
			
			
			if (yDiff < 0 && canMoveY)
				setY((int) (yCam + yAdd));
			
			if (yDiff >= 0 && canMoveY)
				setY((int) (yCam - yAdd));
			
			if (-getXNoShaKe() > -leftLimit)
				setX(leftLimit);
			if (-getXNoShaKe() < -rightLimit)
				setX(rightLimit);
			if (getYNoShake() < getBoundlow())
				setY(getBoundlow());
			if (getYNoShake() > -getBoundhigh())
				setY(-getBoundhigh());
		
			
//		}
		getEnvShake().addTime(-1);
		
	}

	private int[] getMinMaxForXSprite() {
		Integer[] minMax = new Integer[2];
		for (Sprite s: GameFight.getInstance().getSprites()) {
			if (s instanceof SpriteHelper && (((SpriteHelper)s).getHelperSub().getHelpertype().equals("normal")))
				continue;
			if (minMax[0] == null)
				minMax[0] = (int) s.getInfo().getXPos();
			if (minMax[1] == null)
				minMax[1] = (int) s.getInfo().getXPos();
			
			minMax[0] = (int) Math.min(s.getInfo().getXPos(), minMax[0]);
			minMax[1] = (int) Math.max(s.getInfo().getXPos(), minMax[1]);
		}
		return new int[] {minMax[0]==null?0:minMax[0], minMax[1]==null?0:minMax[1]};}
	
	private int[] getMinMaxForYSprite() {
		Integer[] minMax = new Integer[2];
		for (Sprite s: GameFight.getInstance().getSprites()) {
			if (s instanceof SpriteHelper && (((SpriteHelper)s).getHelperSub().getHelpertype().equals("normal")))
				continue;
			if (minMax[0] == null)
				minMax[0] = (int) s.getInfo().getYPos();
			if (minMax[1] == null)
				minMax[1] = (int) s.getInfo().getYPos();
			
			minMax[0] = (int) Math.min(s.getInfo().getYPos(), minMax[0]);
			minMax[1] = (int) Math.max(s.getInfo().getYPos(), minMax[1]);
		}
		return new int[] {minMax[0], minMax[1]};
		
	}


}
