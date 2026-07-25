package org.lee.mugen.core.renderer.game;

import java.util.Stack;

import org.lee.mugen.renderer.DrawProperties;
import org.lee.mugen.renderer.GraphicsWrapper;
import org.lee.mugen.renderer.ImageContainer;
import org.lee.mugen.renderer.RGB;
import org.lee.mugen.renderer.DrawProperties.ImageProperties;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.entity.AfterImageSprite;
import org.lee.mugen.sprite.entity.AfterImageSprite.InfoAfterImage;

public class AfterimageRender implements IAfterimageRender {

	private AfterImageSprite _afterImageSprite;
	
	public AfterimageRender(AfterImageSprite spr) {
		_afterImageSprite = spr;
	}
	
	public AfterImageSprite getAfterImageSprite() {
		return _afterImageSprite;
	}

	public int getPriority() {
		return _afterImageSprite.getPriority();
	}

	public boolean isProcess() {
		return true;
//		return !_afterImageSprite.isRemove();
	}

	public boolean remove() {
		return _afterImageSprite.remove();
	}

	public void render() {
		try {
			Sprite sprite = _afterImageSprite.getSprite();
			float xScale = sprite.getXScale();
			float yScale = sprite.getYScale();

			
			Stack<AfterImageSprite.InfoAfterImage> stack = _afterImageSprite.getAnimElementStack();
			int pos = 1;
			for (InfoAfterImage info: stack) {
				ImageContainer img = _afterImageSprite.getImageContainerForInfo(info);
				if (img == null)
					return;
				final float f = (float)(info.getTime()) / (info.getTotalTime());
				
				DrawProperties dp = new DrawProperties(
						info.getXToDraw(), 
						info.getYToDraw(), 
						info.isFlip() ^ info.getAnimElement().isMirrorH(),
						info.getAnimElement().isMirrorV(),
						img);
				
				ImageProperties ip = new ImageProperties();
				

				ip.setPalbright(_afterImageSprite.getPalbright().mul(1));
				ip.setPalcontrast(_afterImageSprite.getPalcontrast().mul(1));
				ip.setPalpostbright(_afterImageSprite.getPalpostbright().mul(1));
				
				ip.setPalcolor(_afterImageSprite.getPalcolor());
				ip.setPalinvertall(_afterImageSprite.getPalinvertall());
				ip.setTrans(_afterImageSprite.getTrans());
				
//				if (pos == 0) {
//					ip.setPalmul(new RGB(1,1,1,1));
//					ip.setPaladd(new RGB(0,0,0,0));
//				} else {
				RGB mul = _afterImageSprite.getPalmul().pow(pos);
//				mul.setA(f * 255);
				ip.setPalmul(mul);
				ip.setPaladd(_afterImageSprite.getPaladd().mul(pos));
					
//				}
				pos++;
				dp.setImageProperties(ip);
				
				dp.setXScaleFactor(xScale);
				dp.setYScaleFactor(yScale);
				
				GraphicsWrapper.getInstance().draw(dp);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void setPriority(int p) {
	}

}
