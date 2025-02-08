package org.lee.mugen.renderer;

public class AngleDrawProperties {
	private float angleset;
	private float xAnchor;
	private float yAnchor;
	private float xScale = 1;
	private float yScale = 1;
	
	/**
	 * angle en degree sens contraire aiguille montre
	 * @return
	 */
	public float getAngleset() {
		return angleset;
	}

	public void setAngleset(float angleset) {
		this.angleset = angleset;
	}

	public float getXAnchor() {
		return xAnchor;
	}

	public void setXAnchor(float anchor) {
		xAnchor = anchor;
	}

	public float getYAnchor() {
		return yAnchor;
	}

	public void setYAnchor(float anchor) {
		yAnchor = anchor;
	}

	public float getXScale() {
		return xScale;
	}

	public void setXScale(float scale) {
		xScale = scale;
	}

	public float getYScale() {
		return yScale;
	}

	public void setYScale(float scale) {
		yScale = scale;
	}
}
