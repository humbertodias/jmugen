package org.lee.mugen.parser.air;

import org.lee.mugen.object.Rectangle;



public class AirData extends BasicActionData {

	public static enum TypeBlit {
		ASD // Add source
		,A // ADD Color
		,S // Substrac
	}
	public TypeBlit type;
	
    public Integer addcolor;
    public Integer subcolor;

    public Integer destcolor;

    public boolean isMirrorH;
    public boolean isMirrorV;
    
    public Rectangle[] clsn1 = new Rectangle[0];
    public Rectangle[] clsn2 = new Rectangle[0];

	public Integer getAddcolor() {
		return addcolor;
	}
	public void setAddcolor(Integer addcolor) {
		this.addcolor = addcolor;
	}
	public Integer getDestcolor() {
		return destcolor;
	}
	public void setDestcolor(Integer destcolor) {
		this.destcolor = destcolor;
	}
	public Integer getSubcolor() {
		return subcolor;
	}
	public void setSubcolor(Integer subcolor) {
		this.subcolor = subcolor;
	}
	public Rectangle[] getClsn1() {
		return clsn1;
	}
	public void setClsn1(Rectangle[] clsn1) {
		this.clsn1 = clsn1;
	}
	public Rectangle[] getClsn2() {
		return clsn2;
	}
	public void setClsn2(Rectangle[] clsn2) {
		this.clsn2 = clsn2;
	}
	public boolean isMirrorH() {
		return isMirrorH;
	}
	public void setMirrorH(boolean isMirrorH) {
		this.isMirrorH = isMirrorH;
	}
	public boolean isMirrorV() {
		return isMirrorV;
	}
	public void setMirrorV(boolean isMirrorV) {
		this.isMirrorV = isMirrorV;
	}
    
}