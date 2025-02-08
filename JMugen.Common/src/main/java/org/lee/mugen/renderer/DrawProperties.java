package org.lee.mugen.renderer;



public class DrawProperties {
	private float alpha = 1f;
	
	public float getAlpha() {
		return alpha;
	}

	public void setAlpha(float alpha) {
		this.alpha = alpha;
	}

	public boolean isFlipH() {
		return isFlipH;
	}

	public boolean isFlipV() {
		return isFlipV;
	}

	public float getXLeftDst() {
		return xLeftDst;
	}

	public float getXLeftSrc() {
		if (isFlipH) {
			return ic.getWidth();
		}
		return xLeftSrc;
	}

	public float getXRightDst() {
		return xRightDst;
	}

	public float getXRightSrc() {
		if (isFlipH) {
			return 0;
			
		}
		return xRightSrc;
	}

	public float getYBottomDst() {
		return yBottomDst;
	}

	public float getYBottomSrc() {
		if (isFlipV) {
			return 0;
			
		}
		return yBottomSrc;
	}

	public float getYTopDst() {
		return yTopDst;
	}

	public float getYTopSrc() {
		if (isFlipV) {
			return ic.getHeight();
		}
		return yTopSrc;
	}

	public void setIc(ImageContainer ic) {
		this.ic = ic;
	}

	public void setFlipH(boolean isFlipH) {
		this.isFlipH = isFlipH;
	}

	public void setFlipV(boolean isFlipV) {
		this.isFlipV = isFlipV;
	}

	public void setXLeftDst(float leftDst) {
		xLeftDst = leftDst;
	}

	public void setXLeftSrc(float leftSrc) {
		xLeftSrc = leftSrc;
	}

	public void setXRightDst(float rightDst) {
		xRightDst = rightDst;
	}

	public void setXRightSrc(float rightSrc) {
		xRightSrc = rightSrc;
	}

	public void setYBottomDst(float bottomDst) {
		yBottomDst = bottomDst;
	}

	public void setYBottomSrc(float bottomSrc) {
		yBottomSrc = bottomSrc;
	}

	public void setYTopDst(float topDst) {
		yTopDst = topDst;
	}

	public void setYTopSrc(float topSrc) {
		yTopSrc = topSrc;
	}

	public DrawProperties(float x, float y, boolean isFlipH,
			boolean isFlipV, ImageContainer ic) {
		this.xLeftDst = x;
		this.yTopDst = y;
		this.xRightDst = x + ic.getWidth();
		this.yBottomDst = y + ic.getHeight();

		this.xLeftSrc = 0;
		this.xRightSrc = ic.getWidth();
		this.yTopSrc = 0;
		this.yBottomSrc = ic.getHeight();



		this.isFlipH = isFlipH;
		this.isFlipV = isFlipV;

		this.ic = ic;
	}
	private float xScaleFactor = 1f;
	private float yScaleFactor = 1f;
	private AngleDrawProperties angleDrawProperties;
	private ImageContainer ic;

	private boolean isFlipH;

	private boolean isFlipV;

	private float xLeftDst;

	private float xRightDst;

	private float yTopDst;

	private float yBottomDst;

	private float xLeftSrc;

	private float xRightSrc;

	private float yTopSrc;

	private float yBottomSrc;

	//////////////////////////
	//
	//////////////////////////
	private Trans trans = Trans.NONE;
	private PalFxSub palfx;
	private ImageProperties imageProperties; 
	public static class ImageProperties {
		
		// TODO : Afterimage's paladd palmul framegap trans
		private int palcolor = 256; //= col (int) 
		private int palinvertall; //= invertall (bool)
		private RGB palbright = new RGB(30, 30, 30); //= add_r, add_g, add_b (int)

		private RGB palcontrast = new RGB(120,120,220); //= mul_r,mul_g, mul_b (int)
		private RGB palpostbright = new RGB(); //= add2_r,add2_g, add2_b (int)


		private RGB paladd = new RGB(10, 10, 25); //= add_r, add_g, add_b (int)
		private RGB palmul = new RGB(0.65f, 0.65f, 0.75f); //= mul_r,mul_g, mul_b (float)

		private Trans trans = Trans.ADD; //= type (string)

		public int getPalcolor() {
			return palcolor;
		}

		public void setPalcolor(int palcolor) {
			this.palcolor = palcolor;
		}

		public int getPalinvertall() {
			return palinvertall;
		}

		public void setPalinvertall(int palinvertall) {
			this.palinvertall = palinvertall;
		}

		public RGB getPalbright() {
			return palbright;
		}

		public void setPalbright(RGB palbright) {
			this.palbright = palbright;
		}

		public RGB getPalcontrast() {
			return palcontrast;
		}

		public void setPalcontrast(RGB palcontrast) {
			this.palcontrast = palcontrast;
		}

		public RGB getPalpostbright() {
			return palpostbright;
		}

		public void setPalpostbright(RGB palpostbright) {
			this.palpostbright = palpostbright;
		}

		public RGB getPaladd() {
			return paladd;
		}

		public void setPaladd(RGB paladd) {
			this.paladd = paladd;
		}

		public RGB getPalmul() {
			return palmul;
		}

		public void setPalmul(RGB palmul) {
			this.palmul = palmul;
		}

		public Trans getTrans() {
			return trans;
		}

		public void setTrans(Trans trans) {
			this.trans = trans;
		}
		
	}
	///////////////////////////////
	//
	///////////////////////////////


	public DrawProperties(
			float xLeftDst, float xRightDst, float yTopDst, float yBottomDst, 
			float xLeftSrc, float xRightSrc, float yTopSrc, float yBottomSrc, 
			boolean isFlipH, boolean isFlipV,
			ImageContainer ic) {

		this.xLeftDst = xLeftDst;
		this.yTopDst = yTopDst;
		this.xRightDst = xRightDst;
		this.yBottomDst = yBottomDst;

		this.xLeftSrc = xLeftSrc;
		this.xRightSrc = xRightSrc;
		this.yTopSrc = yTopSrc;
		this.yBottomSrc = yBottomSrc;

		this.isFlipH = isFlipH;
		this.isFlipV = isFlipV;

		this.ic = ic;
	}

	public ImageContainer getIc() {
		return ic;
	}

	public AngleDrawProperties getAngleDrawProperties() {
		return angleDrawProperties;
	}

	public void setAngleDrawProperties(AngleDrawProperties angleDrawProperties) {
		this.angleDrawProperties = angleDrawProperties;
	}

	public float getXScaleFactor() {
		return xScaleFactor;
	}

	public void setXScaleFactor(float scaleFactor) {
		xScaleFactor = scaleFactor;
	}

	public float getYScaleFactor() {
		return yScaleFactor;
	}

	public void setYScaleFactor(float scaleFactor) {
		yScaleFactor = scaleFactor;
	}

	public PalFxSub getPalfx() {
		return palfx;
	}

	public void setPalfx(PalFxSub palfx) {
		this.palfx = palfx;
	}

	public ImageProperties getImageProperties() {
		return imageProperties;
	}

	public void setImageProperties(ImageProperties imageProperties) {
		this.imageProperties = imageProperties;
	}

	public Trans getTrans() {
		return trans;
	}

	public void setTrans(Trans trans) {
		this.trans = trans;
	}

}
