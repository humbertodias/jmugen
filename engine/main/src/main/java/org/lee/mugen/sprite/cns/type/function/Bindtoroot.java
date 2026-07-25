package org.lee.mugen.sprite.cns.type.function;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.character.SpriteHelper;
import org.lee.mugen.sprite.cns.eval.function.StateCtrlFunction;
import org.lee.mugen.sprite.entity.BindToParentSub;
import org.lee.mugen.sprite.entity.BindToSub;
import org.lee.mugen.sprite.entity.PointF;

public class Bindtoroot extends StateCtrlFunction {

    // TODO : Bindtoroot
    public Bindtoroot() {
        super("bindtoroot", new String[] {"time", "facing", "pos"});
    }
    @Override
    public Object getValue(String spriteId, Valueable... params) {
    	Sprite sprite = GameFight.getInstance().getSpriteInstance(spriteId);
    	if (sprite instanceof SpriteHelper) {
    		final Sprite parent = GameFight.getInstance().getRoot(sprite);
    		final BindToParentSub parentSub = new BindToParentSub();
    		
    		fillBean(spriteId, parentSub);
    		BindToSub sub = new BindToSub() {

				@Override
				public PointF getPos() {
					return new PointF(parent.getInfo().getXPos() + 
			    	    	(parent.isFlip()? -parentSub.getPos().getX(): parentSub.getPos().getX()),
			    	    	parent.getInfo().getYPos() + parentSub.getPos().getY()
			    	    	);
				}
    			
    		};
    		sub.setCaller(GameFight.getInstance().getSpriteInstance(spriteId));
    	    
    	    
    	    
    	    sprite.getInfo().setBindTo(sub);
    	}
    	return null;
    }
}
