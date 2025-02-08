package org.lee.mugen.sprite.cns.type.function;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.character.SpriteCns;
import org.lee.mugen.sprite.character.spiteCnsSubClass.HitDefSub;
import org.lee.mugen.sprite.cns.eval.function.StateCtrlFunction;
import org.lee.mugen.sprite.entity.BindToSub;
import org.lee.mugen.sprite.entity.PointF;
import org.lee.mugen.sprite.parser.Parser;

public class Targetbind extends StateCtrlFunction {

    public Targetbind() {
        super("targetbind", new String[] {"pos", "x", "y", "time", "id"});
    }
    
    
	@Override
	public Object getValue(String spriteId, Valueable... params) {
		
		HitDefSub hitdef = null;
		for (Sprite s: GameFight.getInstance().getSprites()) {
			if (s.getSpriteId().equals(spriteId)) {
				continue;
			}
			if (s.getInfo().getLastHitdef() != null && s.getInfo().getLastHitdef().getSpriteId().equals(spriteId)) {
				hitdef = s.getInfo().getLastHitdef();
				break;
			}
		}

		final Sprite sprHitter = GameFight.getInstance().getSpriteInstance(spriteId);
		
		String targetId = hitdef.getTargetId();
		Sprite target = GameFight.getInstance().getSpriteInstance(targetId);
		SpriteCns sprInfo = target.getInfo();
		final PointF pointF = new PointF();
		
		
		int xIndex = getParamIndex("x");
		if (valueableParams[xIndex] != null) {
			Valueable x = valueableParams[xIndex][0];
			if (x != null) {
				float fx = Parser.getFloatValue(x.getValue(spriteId));
				if (sprHitter.getInfo().isFlip())
					fx = -fx;
				pointF.setX(fx);
			}
		}
		int yIndex = getParamIndex("y");
		if (valueableParams[yIndex] != null) {
			Valueable y = valueableParams[yIndex][0];
			if (y != null) {
				float fy = Parser.getFloatValue(y.getValue(spriteId));
				pointF.setY(fy);
			}		
		}
		
		int posIndex = getParamIndex("pos");
		if (valueableParams[posIndex] != null) {
			if (valueableParams[posIndex][0] != null) {
				Valueable pos = valueableParams[posIndex][0];
				if (pos != null) {
					float fpos = Parser.getFloatValue(pos.getValue(spriteId));
					if (sprHitter.getInfo().isFlip())
						fpos = -fpos;
					pointF.setX(fpos);
				}		
				
			}
			if (valueableParams[posIndex].length > 1 && valueableParams[posIndex][1] != null) {
				Valueable pos = valueableParams[posIndex][1];
				if (pos != null) {
					float fpos = Parser.getFloatValue(pos.getValue(spriteId));
					
					pointF.setY(fpos);
				}		
				
			}
		}
		BindToSub bindToSub = new BindToSub() {
			@Override
			public boolean isBindOriginal() {
				return true;
			}

			
			@Override
			public PointF getPos() {
				// TODO Auto-generated method stub
				return new PointF(pointF.getX() + sprHitter.getInfo().getXPos(), pointF.getY() + sprHitter.getInfo().getYPos());
			}};
			sprInfo.setBindTo(bindToSub);
		return null;
	}


}
