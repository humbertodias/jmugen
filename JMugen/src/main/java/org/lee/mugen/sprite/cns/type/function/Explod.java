package org.lee.mugen.sprite.cns.type.function;

import java.util.HashMap;
import java.util.Map;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.core.renderer.game.ExplodRender;
import org.lee.mugen.core.renderer.game.SpriteShadowRender;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.cns.eval.function.StateCtrlFunction;
import org.lee.mugen.sprite.entity.ExplodSprite;
import org.lee.mugen.sprite.entity.ExplodSub;
import org.lee.mugen.sprite.parser.ExpressionFactory;

public class Explod extends StateCtrlFunction {
	
	
//		Paramètres requis :
//	anim = [F]anim_no (entier)
//    anim_no indique le numéro de l'animation à jouer. Le préfixe 'F' est optionnel : s'il est inclus, alors l'animation est jouée depuis le fichier fightfx.def.
//
//Paramètres optionnels :
//ID = id_no (entier)
//    id_no indique un numéro ID pour cet explod. Utile surtout avec le trigger NumExplod et le controller RemoveExplod.
//
//pos = x_pos, y_pos (entier)
//    x_pos et y_pos spécifient les offsets auxquels l'explod est créé. Le comportement exact dépend du postype. Si ces paramètres sont omis, leur valeur défaut est de 0.
//
//postype = string (chaîne de caractères)
//    String indique le postype -- comment interpréter les paramètres pos. Dans tous les cas, un offset y positif indique un déplacement vers
//le bas. Les valeurs valides pour postype sont les suivantes :
//- p1 : Interprète le pos relativement à l'axe de P1. Un offset x positif est devant P1. C'est la valeur par défaut de postype.
//- p2 : Interprète le pos relativement à l'axe de P2. Un offset x positif est devant P2.
//- front : Interprète le pos x relativement au bord de l'écran auquel P1 fait face, et le pos y relativement au haut de l'écran. Un offset x  positif s'éloigne du centre de l'écran, alors qu'un offset x négatif se rapproche du centre.
//- back : Interprète le pos x relativement au bord de l'écran auquel P1 est de dos, et le pos y relativement au haut de l'écran. Un offset x
//positif va vers le centre de l'écran, alors qu'un offset x négatif s'éloigne du centre.
//- left : Interprète les pos x et y relativement au coin haut gauche de l'écran. Un offset x positif va vers la droite de l'écran.
//- right : Interprète les pos x et y relativement au coin haut droit de l'écran. Un offset x positif va vers la gauche de l'écran.
//
//facing = facing (entier)
//    Mettez facing à 1 pour que l'explod soit dans la même direction que le offset x positif (tel que déterminé par postype), et -1 pour que l'explod soit dans la direction opposée. 1 par défaut.
//
//vfacing = vfacing (entier)
//    Mettez vfacing à -1 pour que l'affichage de l'explod soit inversé verticalement ou 1 pour que l'affichage de l'explod ne soit pas inveré. 1 par défaut si non précisé.
//
//bindtime = temps_de_lien (entier)
//    Indique le nombre de ticks durant lesquels l'explod est lié au point spécifié par postype. Par exemple, si postype = 1, pos = 30, -40, et bindtime = 5, alors l'explod sera dessiné à la position 30, -40 relativement à l'axe de P1 pendant 5 secondes, peu importe comment P1 bouge durant ce temps. Une fois le bindtime expiré, l'explod ne sera plus lié au point de liaison et gardera sa position (à moins d'être affecté par les paramètres vel ou accel). Si bindtime = -1, alors l'explod sera tout le temps liée.
//
//vel = vitesse_x, vitese_y (flottant)
//    Spécifie les composant X et Y de la vitesses initiale de l'explod. Ils sont interprétés relativement à la direction du "facing" de l'explod. Leur valeur défaut est de 0 si non précisée.
//
//accel = x_accel, y_accel (flottant)
//    Indique les composants X et Y d'accélération pour l'explod. Valeur défaut : 0.
//
//random = rand_x, rand_y (entier)
//    Déplace le point de liaison de l'explod par un nombre aléatoire lors de sa création. rand_x indique l'amplitude de déplacement dans la direction x et rand_y indique l'amplitude de déplacement dans la direction y. Par exemple, si pos = 0,0 et random = 40,80, alors la position x de l'explod peut avoir une valeur aléatoire comprise entre -20 et 19, et sa position y peut avoir une valeur aléatoire comprise entre -40 et 39. Les deux arguments ont une valeur défaut de 0 si non précisés.
//
//removetime = rem_time (entier)
//    Si rem_time est positif, l'explod sera effacé après avoir été affiché pendant ce nombre de ticks. Si rem_time est de -1, l'explod sera affiché indéfiniment. Si rem_time est de -2, l'explod sera effacé lorsque son animtime atteindra 0. La valeur par défaut est de -2.
//
//supermove = bvalue (boolean)
//    (déconseillé -- utilisez le paramètre supermovetime à la place)
//    Mettez supermove = 1 pour que l'explod dure jusqu'à la fin d'une superpause, sans tenir compte de la valeur du removetime. Valeur défaut : 0.
//
//supermovetime = move_time (entier)
//    Détermine le nombre de ticks où l'explod doit être "débloqué" pendant une SuperPause. Souvent utile si vous voulez que l'explod soit animé pendant une SuperPause, de même que pour des super sparks personnalisés. Valeur par défaut : 0.
//
//pausemovetime = move_time (entier)
//    Détermine le nombre de ticks où l'explod doit être "débloqué" durant une Pause. Valeur défaut : 0.
//
//scale = x_scale [,y_scale] (flottant)
//    x_scale et y_scale indiquent les facteurs de scale à appliquer à l'explod dans les direction horizontales et verticales. Les deux ont une valeur défaut de 1 (pas de scale) si non précisés.
//
//sprpriority = pr (entier)
//    pr indique la priorité de dessin de l'explod. Les animations avec une plus grande priorité sont dessinées par dessus les animations avec une priorité plus faible. Par exemple, mettre sprpriority = -3 fera que l'expod sera dessinés sous la plupart des personnages et des autres explods, qui ont habituellement un sprpriority >= -2. Valeur par défaut : 0.
//
//ontop = bvalue (boolean)
//    Mettez ontop = 1 pour que l'explod soit affichée par dessus tous les autres sprites et plans de décor. Ce paramètre est prioritaire sur sprpriority. Valeur défaut : 0.
//
//shadow = shad_r, shad_v, shad_b (entier)
//    Indique les composants R, V et B de l'ombre de l'explod. Les  valeurs valides pour chaque composant sont de 0 à 255. Plus un composant est grand, moins cette couleur sera présente dans l'ombre. Pour utiliser la couleur d'ombre du décor, mettez shad_r à -1. Valeurs défaut : 0,0,0 (pas d'ombre).
//
//ownpal = bvalue (boolean)
//    Mettez ownpal = 1 pour donner à l'explod sa propre copie de sa palette. Ceci est préférable si vous voulez éviter que des  changements temporaires sur la palette du joueur, tel qu'un rétablissement en chute, ou lors de l'utilisation du controller PalFX, n'affectent pas la couleur de l'explod. Valeur défaut : 1 si non précisé.
//
//removeongethit = bvalue (boolean)
//    En mettant ce paramètre à 1, l'explod sera effacé si le joueur est touché. Valeur défaut : 0.

	
	
    // TODO : explod
    public Explod() {
        super("explod", new String[] {
        		"anim","id"
        		,"pos","postype"
        		,"facing","vfacing"
        		,"bindtime","vel"
        		,"accel","random"
        		,"removetime","supermove"
        		,"supermovetime","pausemovetime"
        		,"scale","sprpriority","ontop"
        		,"shadow","ownpal","removeongethit"});
    }

	
	private static Map<String, String> _RENAME_FIELD = new HashMap<String, String>();
	static {
		_RENAME_FIELD.put("velocity", "vel");
	}
	@Override
	public boolean containsParam(String param) {
		return getParamNames().contains(param) || _RENAME_FIELD.containsKey(param);
	}
	
	/**
	 * because of some compatibilties
	 */
	@Override
	public void addParam(String name, Valueable[] param) {
		if (_RENAME_FIELD.containsKey(name)) {
			name = _RENAME_FIELD.get(name);
		}
		super.addParam(name, param);
	}
	@Override
	public Valueable[] parseValue(String name, String value) {
		if (_RENAME_FIELD.containsKey(name)) {
			name = _RENAME_FIELD.get(name);
		}
		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	};
    
	

    
    @Override
    public Object getValue(String spriteId, Valueable... params) {
    	ExplodSub explodSub = new ExplodSub();
    	fillBean(spriteId, explodSub);
    	explodSub.setSprite(GameFight.getInstance().getSpriteInstance(spriteId));
    	
    	
    	ExplodSprite explodSprite = new ExplodSprite(explodSub);

    	ExplodRender render = new ExplodRender(explodSprite);

    	if (explodSub.getShadow() != null) {
    		SpriteShadowRender shadowRender = new SpriteShadowRender(explodSprite, false);
        	GameFight.getInstance().addRender(shadowRender);
    	}

    	GameFight.getInstance().getOtherSprites().add(explodSprite);
    	explodSprite.remove();
    	explodSprite.isProcess();
    	GameFight.getInstance().addRender(render);
    	return null;
    }
}
