package org.lee.mugen.stage.section.elem;

import org.lee.mugen.geom.MugenPoint;

import org.lee.mugen.fight.section.Section;
import org.lee.mugen.renderer.RGB;
import org.lee.mugen.stage.Stage;
import org.lee.mugen.util.BeanTools;

public class Shadow implements Section {
	private Stage parent = null;

	public Shadow(Stage stage) {
		parent = stage;
	}

	// This is the shadow darkening intensity. Valid values range from
	// 0 (lightest) to 256 (darkest). Defaults to 128 if omitted.
	private int intensity = 96;

	// This is the shadow color given in r,g,b. Valid values for each
	// range from 0 (lightest) to 255 (darkest).
	// Defaults to 0,0,0 if omitted.
	// intensity and color's effects add up to give the final shadow
	// result.
	private RGB color = new RGB(0, 0, 0);

	// This is the scale factor of the shadow. Use a big scale factor
	// to make the shadow longer. You can use a NEGATIVE scale factor
	// to make the shadow fall INTO the screen.
	// Defaults to 0.4 if omitted.
	private float yscale = .4f;

	// This parameter lets you set the range over which the shadow is
	// visible. The first value is the high level, and the second is
	// the middle level. Both represent y-coordinates of the player.
	// A shadow is invisible if the player is above the high level,
	// and fully visible if below the middle level. The shadow is
	// faded in between the two levels. This gives an effect of the
	// shadow fading away as the player gets farther away from the
	// ground. If omitted, defaults to no level effects (shadow is
	// always fully visible).
	private MugenPoint fade$range = new MugenPoint();

	private int reflect = 0;

	
	@Override
	public void parse(Object root, String name, String value) throws Exception {
		if (name.equals("intensity")) {
			intensity = Integer.parseInt(value);
		} else if (name.equals("color")) {
			color = (RGB) BeanTools.getConvertersMap().get(RGB.class).convert(value);
		} else if (name.equals("yscale")) {
			yscale = Float.parseFloat(value);
		} else if (name.equals("fade.range")) {
			fade$range = (MugenPoint) BeanTools.getConvertersMap().get(MugenPoint.class).convert(value);
		} else if (name.equals("reflect")) {
			reflect = Integer.parseInt(value);
		}
	}
	

	// /////////////////////////////////
	
	public int getReflect() {
		return reflect;
	}

	public void setReflect(int reflect) {
		this.reflect = reflect;
	}

	
	public void setColor(RGB rgb) {
		color = rgb;
	}

	public int getIntensity() {
		return intensity;
	}

	public void setIntensity(int intensity) {
		this.intensity = intensity;
	}

	public float getYscale() {
		return yscale;
	}

	public void setYscale(float yscale) {
		this.yscale = yscale;
	}

	public Stage getParent() {
		return parent;
	}

	public void setParent(Stage parent) {
		this.parent = parent;
	}

	public MugenPoint getFade$range() {
		return fade$range;
	}

	public void setFade$range(MugenPoint fade$range) {
		this.fade$range = fade$range;
	}

	public RGB getColor() {
		return color;
	}


}
