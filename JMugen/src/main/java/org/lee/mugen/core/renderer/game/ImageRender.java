package org.lee.mugen.core.renderer.game;

import java.awt.Point;

import org.lee.mugen.renderer.GraphicsWrapper;
import org.lee.mugen.renderer.Renderable;
import org.lee.mugen.sprite.common.resource.FontProducer;

public class ImageRender implements Renderable {
	private String str;
	private FontProducer fp;
	private int time;
	private Point point;
	private int sens;
	
	public int getSens() {
		return sens;
	}
	public void setSens(int sens) {
		this.sens = sens;
	}
	public ImageRender(String str, FontProducer fp, Point point, int time) {
		this.str = str;
		this.fp = fp;
		this.time = time;
		this.point = point;
	}
	public ImageRender(String str, FontProducer fp, Point point, int time, int sens) {
		this.str = str;
		this.fp = fp;
		this.time = time;
		this.point = point;
		this.sens = sens;
	}
	public int getPriority() {
		return 1000;
	}

	public boolean isProcess() {
		return true;
	}

	public boolean remove() {
		return time < 0;
	}

	public void render() {
		time--;
		fp.draw(0, point.x, point.y, GraphicsWrapper.getInstance(), str, sens, 1f);
	}

	public void setPriority(int p) {

	}

}
