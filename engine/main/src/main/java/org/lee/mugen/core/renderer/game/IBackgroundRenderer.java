package org.lee.mugen.core.renderer.game;

import org.lee.mugen.renderer.Renderable;

public interface IBackgroundRenderer extends Renderable {

	public abstract int getLayerDisplay();

	public abstract void setLayerDisplay(int layerDisplay);

}