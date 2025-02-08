package org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.character.SpriteCns;
import org.lee.mugen.sprite.cns.eval.function.SpriteCnsTriggerFunction;
/**
 * 
 * @author Dr Wong
 * @category Trigger : Complete
 */
public class Const extends SpriteCnsTriggerFunction {

//	- Les valeurs suivantes de param_name retournent les valeurs spécifiées dans le groupe [Data] des constantes du joueur :
//
//		data.life : Retourne la valeur du paramètre "life" (entier).
//		data.attack : Retourne la valeur du paramètre "attack" (entier).
//		data.defence : Retourne la valeur du paramètre "defense" (int).
//		data.fall.defence_mul : Retourne la valeur du multiplicateur défensif, calculé ainsi : 100/(f+100), où f est le paramètre "fall.defence_up" (flottant).
//		data.liedown.time : Retourne la valeur du paramètre "liedown.time" (entier).
//		data.airjuggle : Retourne la valeur du paramètre "airjuggle" (entier).
//		data.sparkno : Retourne la valeur du paramètre "sparkno" (entier).
//		data.guard.sparkno : Retourne la valeur du paramètre "guard.sparkno" (entier).
//		data.KO.echo : Retourne la valeur du paramètre "ko.echo" (entier).
//		data.IntPersistIndex : Retourne la valeur du paramètre "IntPersistInfex" (entier).
//		data.FloatPersistIndex : Retourne la valeur du paramètre "FloatPersistIndex" (entier).
//
//		- Les valeurs suivantes de param_name retournent les valeurs spécifiées dans le groupe [Size] des constantes du joueur :
//
//		size.xscale : Retourne la valeur du paramètre "xscale" (flottant).
//		size.yscale : Retourne la valeur du paramètre "yscale" (flottant).
//		size.ground.back : Retourne la valeur du paramètre "ground.back" (entier).
//		size.ground.front : Retourne la valeur du paramètre "ground.front" (entier).
//		size.air.back : Retourne la valeur du paramètre "air.back" (entier).
//		size.air.front : Retourne la valeur du paramètre "air.front" (entier).
//		size.height : Retourne la valeur du paramètre "height" (entier).
//		size.attack.dist : Retourne la valeur du paramètre "attack.dist" (entier).
//		size.proj.attack.dist : Retourne la valeur du paramètre "proj.attack.dist" (entier).
//		size.proj.doscale: Retourne la valeur du paramètre "proj.dotscale" (entier).
//		size.head.pos.x : Retourne la valeur x du paramètre "head.pos" (entier).
//		size.head.pos.y : Retourne la valeur y du paramètre "head.pos" (entier).
//		size.mid.pos.x : Retourne la valeur x du paramètre "mid.pos" (entier).
//		size.mid.pos.y : Retourne la valeur y du paramètre "mid.pos" (entier).
//		size.shadowoffset : Retourne la valeur du paramètre "shadowoffset" (entier).
//		size.draw.offset.x : Retourne la valeur x du paramètre "draw.offset" (entier).
//		size.draw.offset.y : Retourne la valeur y du paramètre "draw.offset" (entier).
//
//		- Les valeurs suivantes de param_name retournent les valeurs spécifiées dans le groupe [Velocity] des constantes du joueur.
//
//		velocity.walk.fwd.x : Retourne la valeur du paramètre "walk.fwd" (flottant).
//		velocity.walk.back.x : Retourne la valeur du paramètre "walk.back" (flottant).
//		velocity.run.fwd.x : Retourne la valeur x du paramètre "run.fwd" (flottant).
//		velocity.run.fwd.y : Retourne la valeur y du paramètre "run.fwd" (flottant).
//		velocity.run.back.x : Retourne la valeur x du paramètre "run.back" (flottant).
//		velocity.run.back.y : Retourne la valeur y du paramètre "run.back" (flottant).
//		velocity.jump.y : Retourne la valeur y du paramètre "jump.neu" (flottant).
//		    Note : ceci n'est PAS "velocity.jump.neu.y". Seuls les paramètres "neu" ont une valeur y.
//		velocity.jump.neu.x : Retourne la valeur x du paramètre "jump.neu" (flottant).
//		velocity.jump.back.x : Retourne la valeur du paramètre "jump.back" (flottant).
//		velocity.jump.fwd.x : Retourne la valeur du paramètre "jump.fwd" (flottant).
//		velocity.runjump.back.x : Retourne la valeur du paramètre "runjump.back" (flottant).
//		velocity.runjump.fwd.x : Retourne la valeur du paramètre "runjump.fwd" (flottant).
//		velocity.airjump.y : Retourne la valeur du paramètre "airjump.y" (flottant).
//		    Note: ceci n'est PAS "velocity.airjump.neu.y".
//		velocity.airjump.neu.x : Retourne la valeur du paramètre "airjump.neu" (flottant).
//		velocity.airjump.back.x : Retourne la valeur du paramètre "airjump.back" (flottant).
//		velocity.airjump.fwd.x : Retourne la valeur du paramètre "airjump.fwd" (flottant).
//
//		- Les valeurs suivantes de param_name retournent les valeurs spécifiées dans le groupe [Movement] des constantes du joueur.
//
//		movement.airjump.num : Retourne la valeur du paramètre "airjump.num" (entier).
//		movement.airjump.height : Retourne la valeur du paramètre "airjump.height" (entier).
//		movement.yaccel : Retourne la valeur du paramètre "yaccel" (flottant).
//		movement.stand.friction : Retourne la valeur du paramètre "stand.friction" (flottant).
//		movement.crouch.friction : Retourne la valeur du paramètre "crouch.friction" (flottant).
//

	static Map<String, String> specialCaseToGoodProperty = new HashMap<String, String>();
	static {
//		specialCaseToGoodProperty.put("velocity.jump.y", "velocity.jump.neu.y");
		specialCaseToGoodProperty.put("velocity.runjump.y", "velocity.jump.neu.y");
		specialCaseToGoodProperty.put("data.fall.defence_mul", "data.fall.defence_up");
	}
	
	public Const() {
		super("const", new String[0]);
	}
	@Override
	public Object getValue(String spriteId, Valueable... params) {
		Sprite sprite = GameFight.getInstance().getSpriteInstance(spriteId);
		SpriteCns spriteInfo = sprite.getInfo();
		Object res;
		try {
			String prop = params[0].getValue(spriteId).toString();
			if (specialCaseToGoodProperty.containsKey(prop)) {
				prop = specialCaseToGoodProperty.get(prop);
			}
			res = PropertyUtils.getProperty(spriteInfo, prop);
		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalArgumentException(params[0].getValue(spriteId) + " not exist");
		}
		if (res instanceof Double) {
			return res;
		} else if (res instanceof Float) {
			return res;
		} else if (res instanceof Float[]) {
			return res;
		} else if (res instanceof Integer) {
			return res;
		}
		
		throw new IllegalArgumentException("there coherent value for getter " + params[0].getValue(spriteId));
	}
	

}
