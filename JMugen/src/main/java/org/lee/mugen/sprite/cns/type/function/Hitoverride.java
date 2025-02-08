package org.lee.mugen.sprite.cns.type.function;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.cns.eval.function.StateCtrlFunction;
import org.lee.mugen.sprite.entity.HitOverrideSub;

public class Hitoverride extends StateCtrlFunction {
/*
Paramètres requis :
attr = attr_string
    La chaîne standard de l'attribut du coup spécifiant quels types peuvent être annulés. Voir la description du paramètre "attr" dans le HitDef.

Paramètres optionnels : 
slot = slot_no (entier)
    Indique un emplacement (de 0 à 7) dans lequel placé cette annulation. 0 par défaut si omis.

stateno = value (entier)
    Indique à quel state aller si le joueur est touché par un HitDef avec les attributs spécifiés. Par défaut : -1 (pas de changement) si omis.

time = effective_time (entier)
    Indique pendant combien de temps cette annulation de coup doit être active. Par défaut : 1 (un tick). Mettez -1 pour que l'annulation dure jusqu'à ce qu'elle soit remplacée par une autre.

forceair = value (boolean)
    A 1, les variables de gethit du joueur seront spécifiées comme s'il était dans un state aérien au moment du coup. Utile si vous voulez forcer le joueur à chuter sur n'importe quel coup. 0 par défaut.

 */
    // TODO : hitoverride
    public Hitoverride() {
        super("hitoverride", new String[] {"attr", "slot", "stateno", "time", "forceair"});
    }
    
    @Override
   	public Object getValue(String spriteId, Valueable... params) {
    	Sprite sprite = GameFight.getInstance().getSpriteInstance(spriteId);
    	HitOverrideSub hitOverrideSub = new HitOverrideSub();
    	fillBean(spriteId, hitOverrideSub);
    	
    	sprite.getInfo().setHitOverride(hitOverrideSub);
    	return null;
   	}
}
