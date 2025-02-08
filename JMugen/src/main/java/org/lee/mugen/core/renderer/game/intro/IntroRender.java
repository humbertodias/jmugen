package org.lee.mugen.core.renderer.game.intro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.lee.mugen.core.JMugenConstant;
import org.lee.mugen.core.renderer.game.fight.BaseRender;
import org.lee.mugen.fight.intro.Intro;
import org.lee.mugen.fight.intro.sub.Scene;
import org.lee.mugen.fight.section.elem.Type;
import org.lee.mugen.renderer.GraphicsWrapper;
import org.lee.mugen.renderer.MugenDrawer;

public class IntroRender extends BaseRender {
	private Intro intro;
	
	public IntroRender() {
//		intro = new Intro("E:/dev/workspace/JMugen/resource/chars/kfm/intro.def");
//		intro = new Intro(JMugenConstant.RESOURCE + "chars/kfm/ending.def");
		intro = new Intro("I:/My Old Laptop/Mugen Works/GrandmasterMugenScreenpack/data/intro.def");
		try {
			intro.parse();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public int getPriority() {
		return 10;
	}

	@Override
	public boolean isProcess() {
		return !intro.isEnd();
	}

	@Override
	public boolean remove() {
		return intro.isEnd();
	}
	@Override
	public void setPriority(int p) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void render() {
//		intro.init();
		List<Integer> keys = new ArrayList<Integer>();
		keys.addAll(intro.getScenes().keySet());
		Collections.sort(keys);

		int current = intro.getScenedef().getCurrentScene();
		Scene scene = intro.getScenes().get(current);
		
		MugenDrawer md = GraphicsWrapper.getInstance();
		md.setColor(scene.getClearcolor().getR(),scene.getClearcolor().getG(), scene.getClearcolor().getB(),scene.getClearcolor().getA()); 
//		md.setColor(0, 0,0); 
		md.fillRect(0, 0, 640, 480);
		for (int layer = 0; layer < 10; layer++) {
			render(scene, layer);
		}
		intro.process();
	}

	private void render(Scene scene, int layer) {
		if (scene.getEnd$time() <= 0)
			return;
		float alpha = 1f;
		
		if ((scene.getFadein().getTime() > 0 && scene.getEnd$time() > 0)
				||
				(scene.getFadeout().getTime() > 0 && scene.getEnd$time() <= 0)) {
			alpha = ((float)scene.getFadein().getTime())/((float)scene.getFadein().getOriginalTime());
		}
		if (scene.getFadein().getTime() > 0 && scene.getEnd$time() > 0) {
			alpha = 1f - alpha;
		}
		Type type = scene.getLayers().get(layer);
		if (type == null)
			return;
		MugenDrawer md = GraphicsWrapper.getInstance();


		
		render(md, scene.getLayerall$pos(), type, alpha);

	}



}
