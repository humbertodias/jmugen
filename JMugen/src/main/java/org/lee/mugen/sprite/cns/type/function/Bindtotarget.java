package org.lee.mugen.sprite.cns.type.function;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.cns.eval.function.StateCtrlFunction;
import org.lee.mugen.sprite.entity.BindToSub;
import org.lee.mugen.sprite.entity.BindToTargetSub;
import org.lee.mugen.sprite.entity.PointF;

/**
 * 
 * @author Dr Wong
 * @category Cns Function : Complete
 */
public class Bindtotarget extends StateCtrlFunction {
    public Bindtotarget() {
        super("bindtotarget", new String[] {"time", "id", "pos"});
    }

	@Override
	public Object getValue(String spriteId, Valueable... params) {
		final Sprite sprite = GameFight.getInstance().getSpriteInstance(spriteId);

		
		final BindToTargetSub bindToTarget = new BindToTargetSub();
		fillBean(spriteId, bindToTarget);
		
		final Sprite cible = GameFight.getInstance().getFightEngine().getTarget(sprite, bindToTarget.getId());
		BindToSub bindToSub = new BindToSub() {
			@Override
			public boolean isBindOriginal() {
				return true;
			}
			@Override
			public PointF getPos() {
				float x = (sprite.isFlip()? 1:-1) * bindToTarget.getPos().getX() + cible.getInfo().getXPos();
				float y = bindToTarget.getPos().getY() + cible.getInfo().getYPos();
				
				PointF pos = new PointF();
				
				switch (bindToTarget.getPos().getType()) {
					case FOOT:
//						x += sprite.isFlip()? pos.x: -pos.x;
//						y += pos.y;
						break;
					case MID:
						pos = cible.getInfo().getSize().getMid().getPos();
						x += sprite.isFlip()? pos.getX(): -pos.getX();
						y += pos.getY();
						break;
					case HEAD:
						pos = cible.getInfo().getSize().getHead().getPos();
						x += sprite.isFlip()? pos.getX(): -pos.getX();
						y += pos.getY();
						break;

					default:
						throw new IllegalStateException();
				}
				return new PointF(x, y);
			}
			
		};

		bindToSub.setCaller(GameFight.getInstance().getSpriteInstance(spriteId));
		bindToSub.setTime(bindToTarget.getTime());
		
		sprite.getInfo().setBindTo(bindToSub);
		
		
		return null;
		
	}
    
}
