package org.lee.mugen.core.renderer.game;

import org.lee.mugen.renderer.Renderable;

public class StartFightRender implements Renderable {
	private boolean remove = false;
	public int getPriority() {
		return 2000;
	}

	public boolean isProcess() {
		return true;
	}

	public boolean remove() {
		return remove;
	}

	public void render() {
		// TODO Auto-generated method stub
		
	}

	public void setPriority(int p) {
		// TODO Auto-generated method stub
		
	}

}
