package org.lee.mugen.core.renderer.java;

import java.io.Serializable;

import org.lee.mugen.imageIO.ImageUtils;
import org.lee.mugen.renderer.PalFxSub;

import com.jhlabs.image.PointFilter;

public class PalfxFilter extends PointFilter implements Serializable {
	private PalFxSub fxSub;
	public PalfxFilter(PalFxSub fxSub) {
		this.fxSub = fxSub;
	}
	public PalfxFilter() {
	}
	


	public PalFxSub getFxSub() {
		return fxSub;
	}



	public void setFxSub(PalFxSub fxSub) {
		this.fxSub = fxSub;
	}



	@Override
	public int filterRGB(int x, int y, int rgb) {
//		return rgb;
		int[] argb = ImageUtils.getRGBArray(rgb);
		if (fxSub.getSinadd().getPeriod() == 0) {
			int a = (int) (argb[0] + fxSub.getAdd().getA());
			int r = (int) (argb[1] + fxSub.getAdd().getR());
			int g = (int) (argb[2] + fxSub.getAdd().getG());
			int b = (int) (argb[3] + fxSub.getAdd().getB());
			
			
			a = (int)(a * fxSub.getMul().getA()/256f);
			r = (int)(r * fxSub.getMul().getR()/256f);
			g = (int)(g * fxSub.getMul().getG()/256f);
			b = (int)(b * fxSub.getMul().getB()/256f);

			return ImageUtils.getARGB(
					a > 255? 255: a < 0? 0: a,
					r > 255? 255: r < 0? 0: r,
					g > 255? 255: g < 0? 0: g, 
					b > 255? 255: b < 0? 0: b);

		} else {
			int a = (int) (argb[0] + fxSub.getAdd().getA());
			int r = (int) (argb[1] + fxSub.getAdd().getR());
			int g = (int) (argb[2] + fxSub.getAdd().getG());
			int b = (int) (argb[3] + fxSub.getAdd().getB());
			
			float alpha = (float) (Math.PI * fxSub.getTimeActivate() / fxSub.getSinadd().getPeriod());
			
			int rPlus = (int) (fxSub.getSinadd().getAmpl_r() * Math.sin(2 * alpha));
			int gPlus = (int) (fxSub.getSinadd().getAmpl_g() * Math.sin(2 * alpha));
			int bPlus = (int) (fxSub.getSinadd().getAmpl_b() * Math.sin(2 * alpha));
			
			r = r + rPlus;
			g = g + gPlus;
			b = b + bPlus;
			
			r = (int)(r * fxSub.getMul().getR()/256f);
			g = (int)(g * fxSub.getMul().getG()/256f);
			b = (int)(b * fxSub.getMul().getB()/256f);
			
			return ImageUtils.getARGB(
					255, 
					r > 255? 255: r < 0? 0: r,
					g > 255? 255: g < 0? 0: g, 
					b > 255? 255: b < 0? 0: b);
		}
	}

}
