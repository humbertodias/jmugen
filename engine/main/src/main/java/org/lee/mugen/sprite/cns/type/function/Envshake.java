package org.lee.mugen.sprite.cns.type.function;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.cns.eval.function.StateCtrlFunction;
import org.lee.mugen.sprite.entity.Shake;

public class Envshake extends StateCtrlFunction {

    public Envshake() {
        super("envshake", new String[] {"time", "freq", "ampl", "phase"});
    }

	@Override
	public Object getValue(String spriteId, Valueable... params) {
		Shake envShake = new Shake();
		getValue(spriteId, envShake, "", params);
		
		GameFight.getInstance().getStage().getCamera().setEnvShake(envShake);

		return null;
	}
    
}
