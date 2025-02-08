package org.lee.mugen.sprite.entity;

import java.util.ArrayList;
import java.util.List;

import org.lee.mugen.sprite.base.AbstractSprite;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.others.MakedustSprite;

public class MakeDustSpriteManager extends AbstractSprite {
	private MakedustSprite[] sparkSprites;
	private int spacing;
	private int tick;
	private boolean remove;
	private int position = 0;
	private int priority = 0;
	private List<MakedustSprite> sparkSpritesToProcess = new ArrayList<MakedustSprite>();;
	
	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getSpacing() {
		return spacing;
	}

	public void setSpacing(int spacing) {
		this.spacing = spacing;
	}

	public MakedustSprite[] getSparkSprites() {
		return sparkSprites;
	}

	public void setSparkSprites(MakedustSprite[] sparkSprites) {
		this.sparkSprites = sparkSprites;
	}

	public List<MakedustSprite> getSparkSpritesToProcess() {
		return sparkSpritesToProcess;
	}

	public void setSparkSpritesToProcess(List<MakedustSprite> sparkSpritesToProcess) {
		this.sparkSpritesToProcess = sparkSpritesToProcess;
	}

	public int getTick() {
		return tick;
	}

	public void setTick(int tick) {
		this.tick = tick;
	}

	public MakeDustSpriteManager(Sprite sprite, int spacing, PointF...pts) {
		this.spacing = spacing;
//		this.sprite = sprite;
		
		sparkSprites = new MakedustSprite[pts.length];
		
		for (int i = 0; i < pts.length; i++) {
			PointF newPoint = new PointF(sprite.getRealXPos(), sprite.getRealYPos());
			newPoint.addX(sprite.isFlip()? -pts[i].getX(): pts[i].getX());
			newPoint.addY(pts[i].getY());
			
			sparkSprites[i] = new MakedustSprite(sprite.isFlip(), newPoint);
		}
		if (sparkSprites.length > position)
			sparkSpritesToProcess.add(sparkSprites[position++]);
		
	}

	@Override
	public float getRealXPos() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getRealYPos() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isFlip() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void process() {
		if (spacing > 0) {
			spacing--;
			return;
		}
		
		
		
		
		
		
		tick++;
		if (tick > spacing) {
			tick = 0;
			if (sparkSprites.length > position)
				sparkSpritesToProcess.add(sparkSprites[position++]);

		}
		remove = true;
		for (MakedustSprite sprite: sparkSprites) {
			sprite.process();
			boolean removeThis = sprite.remove();
			remove = remove && removeThis;
			if (removeThis)
				sparkSpritesToProcess.remove(sprite);
			
		}
	}
	public boolean isProcess() {
		return true;
	}


	public boolean remove() {
		return remove;
	}

}
