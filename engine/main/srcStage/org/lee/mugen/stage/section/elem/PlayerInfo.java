package org.lee.mugen.stage.section.elem;

import org.lee.mugen.fight.section.Section;
import org.lee.mugen.stage.Stage;

public class PlayerInfo implements Section {
	private Stage parent = null;
	public PlayerInfo(Stage stage) {
		parent = stage;
	}
	//--- Player 1 ---
	//Player 1 starting coordinates.
	//p1startx is typically -70 and p2startx is 70.
	//p1starty and p1startz should be 0.
	private int p1startx = -70;          //Starting x coordinates
	private int p1starty = 0;            //Starting y coordinates
	private int p1startz = 0;            //Starting z coordinates
	private int p1facing = 1;            //Direction player faces: 1=right, -1=left

	//--- Player 2 ---
	private int p2startx = 70;
	private int p2starty = 0;
	private int p2startz = 0;
	private int p2facing = -1;

	//--- Common ---
	//Don't change these values.
	private int leftbound  = -1000; //Left bound (x-movement)
	private int rightbound =  1000; //Right bound
	private int topbound  =  0;     //Top bound (z-movement)
	private int botbound  =  0;     //Bottom bound
	
	
	@Override
	public void parse(Object root, String name, String value) throws Exception {
		if (name.equals("p1startx")) {
			p1startx = Integer.parseInt(value);
		} else if (name.equals("p1starty")) {
			p1starty = Integer.parseInt(value);
		} else if (name.equals("p1startz")) {
			p1startz = Integer.parseInt(value);
		} else if (name.equals("p1facing")) {
			p1facing = Integer.parseInt(value);
		} else if (name.equals("p2startx")) {
			p2startx = Integer.parseInt(value);
		} else if (name.equals("p2starty")) {
			p2starty = Integer.parseInt(value);
		} else if (name.equals("p2startz")) {
			p2startz = Integer.parseInt(value);
		} else if (name.equals("p2facing")) {
			p2facing = Integer.parseInt(value);
		} else if (name.equals("leftbound")) {
			leftbound = Integer.parseInt(value);
		} else if (name.equals("rightbound")) {
			rightbound = Integer.parseInt(value);
		} else if (name.equals("topbound")) {
			topbound = Integer.parseInt(value);
		} else if (name.equals("botbound")) {
			botbound = Integer.parseInt(value);
		} 
		
	}
	
	
	/////////////////////////////////////////////////////
	public int getBotbound() {
		return botbound;
	}
	public void setBotbound(int botbound) {
		this.botbound = botbound;
	}
	public int getLeftbound() {
		return leftbound;
	}
	public void setLeftbound(int leftbound) {
		this.leftbound = leftbound;
	}
	public int getP1facing() {
		return p1facing;
	}
	public void setP1facing(int p1facing) {
		this.p1facing = p1facing;
	}
	public int getP1startx() {
		return p1startx;
	}
	public void setP1startx(int p1startx) {
		this.p1startx = p1startx;
	}
	public int getP1starty() {
		return p1starty;
	}
	public void setP1starty(int p1starty) {
		this.p1starty = p1starty;
	}
	public int getP1startz() {
		return p1startz;
	}
	public void setP1startz(int p1startz) {
		this.p1startz = p1startz;
	}
	public int getP2facing() {
		return p2facing;
	}
	public void setP2facing(int p2facing) {
		this.p2facing = p2facing;
	}
	public int getP2startx() {
		return p2startx;
	}
	public void setP2startx(int p2startx) {
		this.p2startx = p2startx;
	}
	public int getP2starty() {
		return p2starty;
	}
	public void setP2starty(int p2starty) {
		this.p2starty = p2starty;
	}
	public int getP2startz() {
		return p2startz;
	}
	public void setP2startz(int p2startz) {
		this.p2startz = p2startz;
	}
	public int getRightbound() {
		return rightbound;
	}
	public void setRightbound(int rightbound) {
		this.rightbound = rightbound;
	}
	public int getTopbound() {
		return topbound;
	}
	public void setTopbound(int topbound) {
		this.topbound = topbound;
	}

	
}
