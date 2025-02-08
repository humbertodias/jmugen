package org.lee.mugen.parser.air;

import java.io.Serializable;

/**
 * @author Lee
 *
 */
public class BasicActionData implements Serializable {
    public int grpNum;
    public int imgNum;
    public int xOffSet;
    public int yOffSet;
    public int delayTick;
	public int getDelayTick() {
		return delayTick;
	}
	public void setDelayTick(int delayTick) {
		this.delayTick = delayTick;
	}
	public int getGrpNum() {
		return grpNum;
	}
	public void setGrpNum(int grpNum) {
		this.grpNum = grpNum;
	}
	public int getImgNum() {
		return imgNum;
	}
	public void setImgNum(int imgNum) {
		this.imgNum = imgNum;
	}
	public int getXOffSet() {
		return xOffSet;
	}
	public void setXOffSet(int offSet) {
		xOffSet = offSet;
	}
	public int getYOffSet() {
		return yOffSet;
	}
	public void setYOffSet(int offSet) {
		yOffSet = offSet;
	}
    
    
}