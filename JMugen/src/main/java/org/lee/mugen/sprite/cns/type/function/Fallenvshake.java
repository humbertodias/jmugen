package org.lee.mugen.sprite.cns.type.function;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.character.spiteCnsSubClass.HitDefSub;
import org.lee.mugen.sprite.cns.eval.function.StateCtrlFunction;

public class Fallenvshake extends StateCtrlFunction {
//	fall.EnvShake.time = 13
//	fall.EnvShake.ampl = 4
//	fall.EnvShake.freq = 70

    // TODO : fallenvshake
    public Fallenvshake() {
        super("fallenvshake", new String[] {});
    }

    @Override
    public Object getValue(String spriteId, Valueable... params) {
    	HitDefSub lastHitdef = GameFight.getInstance().getSpriteInstance(spriteId).getInfo().getLastHitdef();
    	if (lastHitdef != null && lastHitdef.getFall().getEnvshake().getTime() > 0) {
    		GameFight.getInstance().getStage().getCamera().setEnvShake(lastHitdef.getFall().getEnvshake());
    	}
    	
    	return null;
    }
}
