package org.lee.mugen.renderer;

/**
 * Renderer interface
 * @author Dr Wong
 *
 */
public interface Renderable {
	void render();
	int getPriority();
	void setPriority(int p);
	boolean isProcess();
	boolean remove();
}
