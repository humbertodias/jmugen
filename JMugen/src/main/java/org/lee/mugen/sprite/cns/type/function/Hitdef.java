package org.lee.mugen.sprite.cns.type.function;

import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Map;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.base.AbstractSprite;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.character.SpriteCns.Type;
import org.lee.mugen.sprite.character.spiteCnsSubClass.HitDefSub;
import org.lee.mugen.sprite.character.spiteCnsSubClass.HitDefSub.AffectTeam;
import org.lee.mugen.sprite.character.spiteCnsSubClass.HitDefSub.AnimType;
import org.lee.mugen.sprite.character.spiteCnsSubClass.HitDefSub.AttrClass;
import org.lee.mugen.sprite.character.spiteCnsSubClass.HitDefSub.AttrType;
import org.lee.mugen.sprite.character.spiteCnsSubClass.HitDefSub.GuardFlag;
import org.lee.mugen.sprite.character.spiteCnsSubClass.HitDefSub.HitFlag;
import org.lee.mugen.sprite.cns.eval.function.StateCtrlFunction;
import org.lee.mugen.sprite.entity.Priority;
import org.lee.mugen.sprite.parser.ExpressionFactory;
import org.lee.mugen.util.BeanTools;

/*
 * 
 * HitDef
 * 
 * Defines a single hit of the player's attack. If the player's Clsn1 box
 * (red) comes in contact with his opponent's Clsn2 box (blue), and the
 * HitDef was define on or before that particular point in time, then the
 * specified effect will be applied. This is one of the more complex, but
 * most commonly-used controllers. A single HitDef is valid only for a
 * single hit. To make a move hit several times, you must trigger more than
 * one HitDef during the attack.
 */ 
public class Hitdef extends StateCtrlFunction {

	public static boolean isPlayerHitOnes(AbstractSprite one, AbstractSprite two) {
		boolean isTouch = false;
		for (Rectangle rAttack: one.getCns1()) {
			for (Rectangle rCln2: two.getCns2()) {
				if (rAttack.intersects(rCln2)) {
					isTouch = true;
					break;
				}
			}
		}
		return isTouch;
	}
	
	
	
	private static Map<String, String> _RENAME_FIELD = new HashMap<String, String>();
	static {
		_RENAME_FIELD.put("fall.recover.time", "fall.recovertime");
		_RENAME_FIELD.put("down.velocities", "down.velocity");
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
		return super.parseValue(name, value);
	};
	@Override
	public Object getValue(String spriteId, Valueable... params) {
		Sprite sprOne = GameFight.getInstance().getSpriteInstance(spriteId);
		HitDefSub hitDef = new HitDefSub();
		hitDef.setSpriteHitter(sprOne);
		hitDef.setSpriteId(spriteId);
		fillBean(spriteId, hitDef);
		hitDef.setTimeCreated(GameFight.getInstance().getGameState().getGameTime());
		
		GameFight.getInstance().getFightEngine().add(hitDef);
//		GameFight.getInstance().getFightEngine().process();
		return null;
	}

	
	/*
	 * Execute in general
	 */
	protected Map<String, Object[]> _DEFAULT_VALUES_MAP = buildDefaultValuesMap();

	protected Map<String, Object[]> buildDefaultValuesMap() {
		final Map<String, Object[]> map = new HashMap<String, Object[]>();
		

		map.put("hitflag", new Object[] {HitFlag.M.bit | HitFlag.A.bit | HitFlag.F.getBit()});
		map.put("affectteam", new Object[] {AffectTeam.E});
		map.put("animtype", new Object[] {HitDefSub.AnimType.LIGHT});
		map.put("air.animtype", new Object[] {new Valueable() {

			public Object getValue(String spriteId, Valueable... params) {
				final String name = "animtype";
				Object[] objectValues = getValueFromName(spriteId, name);
				if (objectValues.length == 1) {
					return objectValues[0];
				} else 
					throw new IllegalArgumentException();
				
			}}});

		
//fall.animtype = anim_type (chaîne de caractères)
//    Similaire au paramètre "animtype", ceci est le type d'animation dans laquelle P2 est placé 
//		si P2 est touché alors qu'il tombe. La valeur défaut est Up si le air.animtype est Up, Back dans les autres cas.
		map.put("fall.animtype", new Object[] {new Valueable() {

			public Object getValue(String spriteId, Valueable... params) {
				final String name = "air.animtype";
				Object[] objectValues = getValueFromName(spriteId, name);
				if (objectValues.length == 1) {
					int value = ((AnimType) objectValues[0]).value;
					if (value == AnimType.UP.value) {
						return AnimType.UP;
					} else {
						return AnimType.BACK;
					}
				} else 
					throw new IllegalArgumentException();
				
			}}});

		
//priority = hit_prior (entier), hit_type (chaîne de caractères)
//    Indique la priorité pour ce coup. Les coups avec les priorités  les plus 
//		hautes prennent l'ascendant sur les coups ayant une priorité plus faible. 
//		Les valeurs valides pour hit_prior sont de 1 à 7. Défaut : 4.
//    hit_type, si précisé, donne la classe de priorité du coup. 
//		Les classes de priorité sont Dodge (esquive), Hit (coup), et Miss (raté). 
//		La classe de priorité détermine le comportement qui l'emporte quand P1 et P2 
//		se touchent simultanément avec des priorités égales. Le comportement est le suivant :
//    * Hit vs. Hit : P1 et P2 sont tous les deux touchés
//    * Hit vs. Miss: Hit touche, Miss rate
//    * Hit vs. Dodge: Pas de coup
//    * Dodge vs. Dodge: Pas de coup
//    * Dodge vs. Miss: Pas de coup
//    * Miss vs. Miss: Pas de coup
//Dans le cas où les classes interviennent et qu'il en résulte "Pas de coup", les HitsDefs respectifs restent actifs.
//
		map.put("priority", new Object[] {
				new Valueable() {
					public Object getValue(String spriteId, Valueable... params) {
						return 4;
					}}, 
				new Valueable() {
					public Object getValue(String spriteId, Valueable... params) {
						return Priority.HitType.HIT;
					}}, 
		});

//damage = hit_damage, guard_damage (entier)
//    hit_damage indique les dégâts subis par P2 s'il est touché par P1. 
//		Le paramètre optionnel guard_damage indique les dégâts subis par 
//		P2 si le coup est bloqué. Les deux sont par défaut à zéro si omis.
		map.put("damage", new Object[] {0, 0});

		
//pausetime = p1_pausetime, p2_shaketime (entier)
//    C'est le temps durant lequel chaque joueur va être en pause sur le coup. p1_pausetime est le temps où P1 va être bloqué, en ticks. p2_pausetime est le temps où P2 va être choqué avant de s'écarter de l'impact. Valeurs défaut si omises : 0,0
		map.put("pausetime", new Object[] {0,0});

//guard.pausetime = p1_pausetime, p2_shaketime (entier)
//    Ressemblant au paramètre "pausetime", ce sont les temps de pause de chaque joueur si le coup est bloqué. Par défaut, ce sont les mêmes valeurs que celles du paramètre pausetime, si omis.
		map.put("guard.pausetime", new Object[] {
				new Valueable() {
					public Object getValue(String spriteId, Valueable... params) {
						int nameIndex = getParamIndex("pausetime");
						Valueable[] values = valueableParams[nameIndex];
						if (values == null) {
							
							nameIndex = getParamIndex("pausetime");
							if (valueableParams[nameIndex] == null)
								return new Valueable() {

								public Object getValue(String spriteId, Valueable... params) {
									return 0;
								}};
						}
						if (valueableParams[nameIndex].length > 0)
							return valueableParams[nameIndex][0];
						else
							return new Valueable() {

								public Object getValue(String spriteId, Valueable... params) {
									return 0;
								}};
					}}, 
				new Valueable() {
					public Object getValue(String spriteId, Valueable... params) {						
						int nameIndex = getParamIndex("pausetime");
						Valueable[] values = valueableParams[nameIndex];
						if (values == null) {
							nameIndex = getParamIndex("pausetime");
							if (valueableParams[nameIndex] == null)
								return new Valueable() {
								public Object getValue(String spriteId, Valueable... params) {
									return 0;
								}};
						}
						if (valueableParams[nameIndex].length > 1)
							return valueableParams[nameIndex][1];
						else
							return new Valueable() {
								public Object getValue(String spriteId, Valueable... params) {
									return 0;
								}};
				}}, 
		});

//sparkno = action_no (entier)
//    C'est le numéro d'action du spark à afficher si le coup touche. Pour jouer un spark depuis le fichier AIR du joueur, mettez un S devant le numéro d'action. Par défaut, c'est la valeur mise dans les variables du joueur si omis.
		map.put("sparkno", new Object[] {
				new Valueable() {
					public Object getValue(String spriteId, Valueable... params) {
						return GameFight.getInstance().getSpriteInstance(spriteId).getInfo().getData().getSparkno();
					}
				}
		});

//guard.sparkno = action_no (entier)
//    C'est le numéro daction du spark à afficher si le coup est bloqué. Pour jouer un spark depuis le fichier AIR du joueur, mettez un S devant le numéro d'action. Par défaut, c'est la valeur mise dans les variables du joueur si omis.
		map.put("guard.sparkno", new Object[] {
				new Valueable() {
					public Object getValue(String spriteId, Valueable... params) {
						return GameFight.getInstance().getSpriteInstance(spriteId).getInfo().getData().getGuard().getSparkno();
					}
				}
		});

//sparkxy = spark_x, spark_y (entier)
//    Indique où le hit/guard spark doit être affiché. spark_x est une coordonnée relative au devant de P2. Une valeur négative place le spark plus loin dans P2. spark_y est relatif à P1. Une valeur négative place le spark plus haut. Vous pouvez utiliser un outil comme AirView pour déterminer cette valeur en positionnant le curseur au point d'attaque et en reportant la valeur de la position y. 0,0 par défaut si omis.
		map.put("sparkxy", new Object[] {0, 0});

//hitsound = snd_grp, snd_item (entier)
//    C'est le son à jouer lors du coup (depuis le common.snd). 
//		Le fight.snd inclus vous permet de choisir de 5,0 (son de coup faible) 
//		jusqu'à 5,4 (coup puissant douloureux). 
//		Pour jouer un son depuis le fichier SND propre du joueur, faites précéder 
//		le premier chiffre d'un "S". Par exemple, "hitsound = S1,0". Par défaut, 
//		c'est la valeur mise dans les variables du joueur, si omis.
		map.put("hitsound", new Object[] {0, 0}); // TODO

//guardsound = snd_grp, snd_item (entier)
//    C'est le son à jouer lors du bloc (depuis le common.snd). 
//		Seul le 6,0 est disponible pour le moment. 
//		Pour jouer un son depuis le fichier SND propre du joueur, 
//		faites précer le premier chiffre d'un "S". 
//		Il n'y a aucun moyen de jouer un son depuis le fichier SND de l'adversaire. 
//		Par défaut, c'est la valeur mise dans les variables du joueur, si omis.
		map.put("guardsound", new Object[] {0, 0}); // TODO


//ground.type = attack_type (chaîne de caractères)
//    C'est le type d'attaque si P2 est sol. Choisissez "High" pour les attaques où la tête de P2 part vers l'arrière, "Low" pour les attaques telles que des coups dans le ventre, "Trip" pour les attaque qui décollent P2 du sol, ou "none" pour ne rien faire à  P2. Les attaques "High" et "Low" sont identique si l'AnimType est "Back". "High" par défaut si omis.
		map.put("ground.type", new Object[] {HitDefSub.Type.HIGH});

//air.type = attack_type (chaîne de caractères)
//    C'est le type d'attaque si P2 est en l'air. Par défaut, même valeur que "ground.type" si omis.
		map.put("air.type", new Object[] {new Valueable() {

			public Object getValue(String spriteId, Valueable... params) {
				final String name = "ground.type";
				Object[] objectValues = getValueFromName(spriteId, name);
				if (objectValues.length == 1) {
					return objectValues[0];
				} else 
					throw new IllegalArgumentException();
				
			}}});

//ground.slidetime = slide_time (entier)
//    C'est le temps en ticks durant lequel P2 va glisser vers l'arrière 
//		après avoir été touché (ce temps n'inclut pas le pausetime de P2). 
//		Applicable seulement sur les coups qui laissent P2 au sol. 
//		0 par défaut si omis.
		map.put("ground.slidetime", new Object[] {0});

//guard.slidetime = slide_time (entier)
//    Idem que "ground.slidetime", sauf que c'est la valeur si P2 bloque le coup. 
//		Par défaut, même valeur que "guard.hittime".
		map.put("guard.slidetime", new Object[] {new Valueable() {
			
			public Object getValue(String spriteId, Valueable... params) {

				final String name = "guard.hittime";
				Object[] objectValues = getValueFromName(spriteId, name);
				if (objectValues.length > 0) {
					return objectValues[0];
				} else 
					throw new IllegalArgumentException();
			}}});
		
		
//ground.hittime = hit_time (entier)
//    Temps durant lequel P2 reste dans un state gethit après avoir été touché. 
//		Augmentez la valeur pour garder P2 dans un état gethit pendant plus 
//		longtemps, peut-être pour faciliter un enchaînement. 
//		Applicable uniquement aux coups qui laissent P2 au sol. 
//		0 par  défaut si omis.
		map.put("ground.hittime", new Object[] {0});

//guard.hittime = hit_time (entier)
//    Idem que "ground.hittime", sauf que c'est la valeur si P2 bloque le coup. 
//		Par défaut, même valeur que "ground.hittme".
		map.put("guard.hittime", new Object[] {new Valueable() {
			
			public Object getValue(String spriteId, Valueable... params) {

				final String name = "ground.hittime";
				Object[] objectValues = getValueFromName(spriteId, name);
				if (objectValues.length > 0) {
					return objectValues[0];
				} else 
					throw new IllegalArgumentException();
			}}});

//air.hittime = hit_time (entier)
//    Temps durant lequel P2 reste en gethit après avoir été touché dans les airs, 
//		avant d'accéder au state de "air recover" (rétablissement aérien). 
//		Ce paramètre est sans effet si le paramètre fall est à 1. 20 
//		par défaut si omis.
		map.put("air.hittime", new Object[] {20f});

		
//guard.ctrltime = ctrl_time (entier)
//    C'est le délai avant que P2 ne retrouve le contrôle en state de garde au sol.
//	  Par défaut, même valeur que "guard.slidetime" si omis.
		map.put("guard.ctrltime", new Object[] {new Valueable() {
			
			public Object getValue(String spriteId, Valueable... params) {

				final String name = "guard.slidetime";
				Object[] objectValues = getValueFromName(spriteId, name);
				if (objectValues.length > 0) {
					return objectValues[0];
				} else 
					throw new IllegalArgumentException();
			}}});

//guard.dist = x_dist (entier)
//    C'est la distance x depuis P1 à partir de laquelle P2 se mettra en garde 
//		si P2 met une direction opposée à P1. Par défaut, 
//		c'est la valeur entrée dans les variables du joueur si omis. 
//		Vous n'avez normalement pas besoin d'utiliser ce paramètre.
		map.put("guard.dist", new Object[] {new Valueable() {
			public Object getValue(String spriteId, Valueable... params) {
				return GameFight.getInstance().getSpriteInstance(spriteId).getInfo().getSize().getAttack().getDist();
			}}});
		
//yaccel = accel (flottant)
//    Indique l'accélération y à imprimer à P2 si le coup touche. 0 par défaut.
		map.put("yaccel", new Object[] {new Valueable() {
			public Object getValue(String spriteId, Valueable... params) {
				return 0.35f;
			}}});

//ground.velocity = x_velocity, y_velocity (flottant)
//    Vitesse initiale à donner à P2 après le coup, si P2 est au sol. 
//		Si y_velocity n'est pas zéro, P2 sera envoyé dans les airs. 
//		Les deux valeurs sont à 0 par défaut, si omises. 
//		Vous pouvez omettre le y_velocity si vous souhaitez que P2 reste au sol.
		map.put("ground.velocity", new Object[] {0, 0});

//guard.velocity = x_velocity (flottant)
//    Vitesse à donner à P2 si P2 bloque le coup au sol. Par défaut, 
//		c'est la valeur x_velocity du paramètre "ground.velocity", si omis.
		map.put("guard.velocity", new Object[] {new Valueable() {
			
			public Object getValue(String spriteId, Valueable... params) {
				final String name = "ground.velocity";
				Object[] objectValues = getValueFromName(spriteId, name);
				if (objectValues.length > 0) {
					return objectValues[0];
				} else 
					throw new IllegalArgumentException();
			}}});
//
//air.velocity = x_velocity, y_velocity (flottant)
//    Vitesse initiale à donner à P2 si P2 est touché en l'air. 
//		Valeur défaut : 0,0 si omis.
		map.put("air.velocity", new Object[] {0, 0});
//
//airguard.velocity = x_velocity, y_velocity (flottant)
//    Vitesse à donner à P2 si P2 bloque le coup en l'air. 
//		Par défaut, c'est x_velocity*1.5, y_velocity/2, 
//		où x_velocity et y_velocity sont les valeurs du paramètre "air.velocity".
		map.put("airguard.velocity", new Object[] {
				new Valueable() {
					
					public Object getValue(String spriteId, Valueable... params) {

						final String name = "air.velocity";
						Object[] objectValues = getValueFromName(spriteId, name);
						if (objectValues.length > 0) {
							return ((Number)objectValues[0]).floatValue() * 1.5f;
						} else 
							throw new IllegalArgumentException();
					}}
				,new Valueable() {
					public Object getValue(String spriteId, Valueable... params) {
						final String name = "air.velocity";
						Object[] objectValues = getValueFromName(spriteId, name);
						if (objectValues.length > 1) {
							return ((Number)objectValues[1]).floatValue() /2f;
						} else 
							return 0;
//							throw new IllegalArgumentException();
					}}
		});

		
		
//ground.cornerpush.veloff = x_velocity (flottant)
//    Détermine la vitesse additionnelle (ajout de vitesse) à donner au joueur 
//		s'il donne un coup au sol dans un coin. 
//		Mettre une grande valeur fera que le joueur sera repoussé plus loin du coin. 
//		Si omis, la valeur défaut dépendra du paramètre attr. 
//		Si arg1 est "A", la valeur défaut est 0. 
//		Dans les autres cas, la valeur défaut est de 1.3*guard.velocity.
		map.put("ground.cornerpush.veloff", new Object[] {
				new Valueable() {
					public Object getValue(String spriteId, Valueable... params) {
						int nameIndex = getParamIndex("attr");
						if (valueableParams[nameIndex] != null) {
							if (valueableParams[nameIndex].length > 0) {
								AttrClass attrClass = (AttrClass) valueableParams[nameIndex][0].getValue(spriteId, params);
								if (attrClass.getType() == Type.A.getBit()) {
									return 0;
								} else {
									final String name = "guard.velocity";
									Object[] objectValues = getValueFromName(spriteId, name);
									if (objectValues.length > 0) {
										return ((Number)objectValues[0]).floatValue() * 1.3f;
									}
								}
							}
						} else {
							final String name = "guard.velocity";
							Object[] objectValues = getValueFromName(spriteId, name);
							if (objectValues.length > 0) {
								return ((Number)objectValues[0]).floatValue() * 1.3f;
							}

						}
						throw new IllegalArgumentException("ground.cornerpush.veloff arg error ");
					}}
		});
//
//air.cornerpush.veloff = x_velocity (flottant)
//    Détermine la vitesse additionnelle (ajout de vitesse) à donner au joueur 
//		s'il donne un coup à un adversaire qui se trouve dans un coin, en l'air. 
//		Mettre une valeur élevée fera que le joueur sera repoussé plus loin du coin. 
//		Valeur défaut si omis : ground.cornerpush.veloff.
		map.put("air.cornerpush.veloff", new Object[] {new Valueable() {
			public Object getValue(String spriteId, Valueable... params) {
				final String name = "ground.cornerpush.veloff";
				Object[] objectValues = getValueFromName(spriteId, name);
				if (objectValues.length > 0) {
					return objectValues[0];
				} else 
					throw new IllegalArgumentException();
			}}});
//down.cornerpush.veloff = x_velocity (flottant)
//    Détermine la vitesse additionnelle (ajout de vitesse) à donner au joueur 
//      s'il donne un coup à un adversaire accroupi dans un coin. 
//      Mettre une valeur élevé fera que le joueur sera repoussé plus loin du coin. 
//      La valeur défaut si omis : ground.cornerpush.veloff.
		map.put("down.cornerpush.veloff", new Object[] {new Valueable() {
			public Object getValue(String spriteId, Valueable... params) {
				final String name = "ground.cornerpush.veloff";
				Object[] objectValues = getValueFromName(spriteId, name);
				if (objectValues.length > 0) {
					return objectValues[0];
				} else 
					throw new IllegalArgumentException();
			}}});
		
//guard.cornerpush.veloff = x_velocity (flottant)
//    Détermine la vitesse additionnelle (ajout de vitesse) à donner au joueur 
//      si son coup a été bloqué dans un coin. Mettre une valeur élevée fera que 
//      le joueur sera repoussé plus loin du coin. 
//      Valeur défaut si omis : ground.cornerpush.veloff.
		map.put("guard.cornerpush.veloff", new Object[] {new Valueable() {
			public Object getValue(String spriteId, Valueable... params) {
				final String name = "ground.cornerpush.veloff";
				Object[] objectValues = getValueFromName(spriteId, name);
				if (objectValues.length > 0) {
					return objectValues[0];
				} else 
					throw new IllegalArgumentException();
			}}});
		
//airguard.cornerpush.veloff = x_velocity (flottant)
//    Détermine la vitesse additionnelle (ajout de vitesse) à donner au joueur 
//      si le coup a été bloqué en l'air dans un coin. Mettre une valeur élevée fera 
//      que le joueur sera repoussé plus loin du coin.
//      Valeur défaut si omis : guard.cornerpush.veloff.

		map.put("airguard.cornerpush.veloff", new Object[] {new Valueable() {
			public Object getValue(String spriteId, Valueable... params) {
				final String name = "guard.cornerpush.veloff";
				Object[] objectValues = getValueFromName(spriteId, name);
				if (objectValues.length > 0) {
					return objectValues[0];
				} else 
					throw new IllegalArgumentException();
			}}});
//airguard.ctrltime = ctrl_time (entier)
//    C'est le délai avant que P2 ne reprenne le contrôle dans le state 
//      de défense aérien. Valeur défaut : guard.ctrltime si omis.
		map.put("airguard.ctrltime", new Object[] {new Valueable() {
			public Object getValue(String spriteId, Valueable... params) {
				final String name = "guard.ctrltime";
				Object[] objectValues = getValueFromName(spriteId, name);
				if (objectValues.length > 0) {
					return objectValues[0];
				} else 
					throw new IllegalArgumentException();
			}}});
		
//air.juggle = juggle_points (entier)
//    Le nombre de points additionnels de juggle que le coup requière. 
//      Ne pas confondre avec le paramètre "juggle" du StateDef.
//      Vous n'avez normalement pas besoin de ce paramètre, 
//      sauf pour les HitDefs des projectiles. Valeur défaut : 0 si omis.
		map.put("air.juggle", new Object[] {0});
      
//
//mindist = x_pos, y_pos (entier)
//maxdist = x_pos, y_pos (entier)
//    Ceci vous laisse contrôler les distance minimum et maximum entre P2 et P1, 
//      après que P2 ait été touché. Ces paramètres ne sont pas utilisés, normalement. 
//      Par défaut, si omis, ne change pas la position de P2.

      
//
//snap = x_pos, y_pos (entier)
//    Place P2 à la position spécifiée, relativement à P1, si le coup touche. 
//      Ce paramètre n'est normalement pas utilisé. Si vous voulez placez P2 
//      à une position particulière pour une projection, il est recommandé 
//      d'utiliser le controller "TargetBind" dans un state de projection 
//      de P1, à la place. Par défaut, ne change pas la position de P2 si omis.
      
//
//sprpriority = drawing_priority (entier)
//    C'est la priorité d'affichage du sprite de P1 si le coup touche ou est 
//      bloqué par P2. Ca détermine si P1 est affiché au-dessus ou au-dessous de P2.
//      La priorité d'affichage de P2 est toujours mise à 0. 
//      Mettez -1 si vous voulez que le P1 apparaisse derrière P2. 
//      La valeur défaut est de 1.
		map.put("sprpriority", new Object[] {1});
//
//p1facing = facing (entier)
//    Mettez -1 pour que P1 se tourne si le coup touche. 
//      Habituellement, n'est utilisé que pour les projections. 
//      La valeur défaut est que le côté auquel P1 fait face n'est pas changé.
		map.put("p1facing", new Object[] {0});
		
//p1getp2facing = facing (entier)
//    Mettez 1 pour que P1 regarde dans la même direction que P2 
//      après que le coup ait touché, et -1 pour que P1 regarde dans la  
//      direction opposée à celle de P2. Par défaut, c'est 0 (pas de changement). 
//      Si ce paramètre est différent de 0, il est prioritaire sur P1facing.
		map.put("p1getp2facing", new Object[] {0});
//
//p2facing = facing (entier)
//    Mettez 1 pour que P2 regarde dans la même direction que P1 si le coup touche, 
//      et -1 pour qu'il regarde dans la direction opposée. 
//      La valeur par défaut est pas de changement 
//      dans la direction à laquelle P2 fait face.
		map.put("p2facing", new Object[] {0});
//
//p1stateno = state_no (entier)
//    C'est le numéro du state dans lequel P1 doit être placé si le coup touche. 
//      Utilisé principalement pour les projections.

		
		
		//
//p2stateno = state_no (entier)
//    C'est le numéro du state dans lequel P2 doit être placé si le coup touche. 
//      P2 utilisera les states et animations de P1. 
//      Utilisé principalement pour les projections.
      
//
//p2getp1state = value (entier)
//    Mettez 0 pour éviter que P2 n'utilise les states et animations de P1, 
//      si vous ne souhaitez pas de l'attitude par défaut du paramètre "p2stateno". 
//      Par défaut : 1, si le paramètre "p2stateno" est utilisé. Ignoré dans les autres cas.
		map.put("p2getp1state", new Object[] {1});
//
//forcestand = value (entier)
//    Mettez 1 pour forcer P2 à se mettre dans un state de type stand si le 
//      coup touche et que P2 est dans un state accroupi. La valeur défaut est 
//      normalement de 0, mais si la valeur y_velocity 
//      du paramètre "ground.velocity" est différente de 0, la valeur défaut est de 1.
		map.put("forcestand", new Object[] {new Valueable() {
			public Object getValue(String spriteId, Valueable... params) {
				final String name = "ground.velocity";
				Object[] objectValues = getValueFromName(spriteId, name);
				if (objectValues.length > 0) {
					float value = ((Number)objectValues[0]).floatValue();
					if (value != 0) {
						return 1;
					}
				}
				return 0;
			}}});
//
//fall = value (entier)
//    Mettez 1 si vous voulez que P2 aille dans un state de "fall"  
//      (chute - où P2 touche le sol sans retrouver le contrôle en l'air). 
//      Utilisez-le pour un mouvement qui fait tomber P2.
		map.put("fall", new Object[] {0});
//
//fall.xvelocity = x_velocity (flottant)
//    C'est la vitesse en x que prend P2 lorsqu'il rebondit du sol 
//      dans un state de "fall". Par défaut : pas de changement de vitesse.

//
//fall.yvelocity = y_velocity (flottant)
//    C'est la vitesse en y que prend P2 lorsqu'il rebondit du sol dans un 
//      state de "fall". Valeur défaut : -4.5 si omis.
		map.put("fall.yvelocity", new Object[] {-4.5f});
//
//fall.recover = value (entier)
//    Mettez 0 si vous ne voulez pas que P2 puisse se rétablir 
//      alors qu'il est dans un state de "fall". Valeur défaut : 1 (peut se rétablir).

		map.put("fall.recover", new Object[] {1});

//
//fall.recovertime = recover_time (entier)
//    C'est le délai avant que P2 ne puisse se rétablir d'un state de "fall". 
//      N'inclut pas le temps durant lequel P2 est en pause, 
//      alors qu'il vibre suite au coup. Valeur défaut si omis : 4.
		map.put("fall.recovertime", new Object[] {4});

//
//fall.damage = damage_amt (entier)
//    Indique les dommages à enlever quand P2 touche le sol après un state de fall. 
//      Valeur défaut : 0.
		map.put("fall.damage", new Object[] {0});
		
//
//air.fall = value (entier)
//    Mettez 1 si vous souhaitez que P2 aille dans un state de "fall" (chute - où 
//      P2 touche le sol sans retrouver le contrôle en l'air) s'il est touché en l'air. 
//      Par défaut, même valeur que fall.
		map.put("air.fall", new Object[] {new Valueable() {
			public Object getValue(String spriteId, Valueable... params) {
				final String name = "fall";
				Object[] objectValues = getValueFromName(spriteId, name);
				if (objectValues.length > 0) {
					return objectValues[0];
				} else 
					throw new IllegalArgumentException();
			}}
		});
		
//
//down.velocity = x_velocity, y_velocity (flottant)
//    C'est la vitesse à donner à P2 si P2 est touché alors qu'il est allongé au sol. 
//      Si y_velocity n'est pas 0, P2 sera touché dans les airs. S'il vaut 0, 
//      P2 glissera vers l'arrière au sol. Par défaut, même value que le paramètre "air.velocity".
		map.put("down.velocity", new Object[] {new Valueable() {
			public Object getValue(String spriteId, Valueable... params) {
				final String name = "air.velocity";
				Object[] objectValues = getValueFromName(spriteId, name);
				if (objectValues.length == 2) {
					return objectValues;
				} else 
					return new Object[] {objectValues[0], new Float(0)};
//					throw new IllegalArgumentException("objectValues.length must == 2");
			}}
		});
//
//down.hittime = hit_time (entier)
//    C'est le temps durant lequel P2 va glisser vers l'arrière si P2 est touché 
//      alors qu'il est alongé au sol. Ce paramètre est ignoré si y_velocity 
//      est différent de 0 pour le paramètre "down.velocity".
		
//
//down.bounce = value (entier)
//    Mettez 1 pour que P2 rebondisse au sol une fois 
//      (en utilisant les valeur de fall.xvelocity et fall.yvelocity) 
//      après avoir touché le sol après le coup. Ce paramètre est ignoré 
//      si y_velocity vaut 0 dans le paramètre "ground.velocity". 
//      Valeur défaut : 0 (P2 touche le sol et y reste).
		map.put("down.bounce", new Object[] {0});
//
//id = id_number (entier)
//    Utilisé surtout pour les enchaînements. Vous pouvez utiliser 
//      ce nombre plus tard pour détecter si le joueur a été touché 
//      en dernier par ce HitDef en particulier. 
//      Les valeurs acceptées sont toutes les valeurs >= 1. Si omis, 
//      la valeur défaut est de 0 (pas d'ID).
		map.put("id", new Object[] {0});
//
//chainID = id_number (entier)
//    Utilisé surtout pour les enchaînements. Si P2 a été touché en dernier 
//        par un coup avec cet ID, alors seulement il peut être touché par 
//        le HitDef avec ce chainID. A utiliser dans les parties suivantes
//        d'un enchaînement. Notez que les enchaînements restent possible 
//        même sans utiliser les "id" et "chainID. Les valeurs acceptées 
//        sont toutes les valeurs >= 1. Si omis, valeur défaut : -1 (s'enchaîne avec n'importe quel coup).
		map.put("chainID", new Object[] {-1});

//
//nochainID = nochain_1, nochain_2 (entier)
//    Spécifie jusqu'à 2 numéros ID de coups qui ne peuvent pas s'enchaîner avec ce coup. 
//    S'ils sont à -1 (valeur défaut), alors l'enchaînement n'est désactivé pour aucun coup, 
//    quel que soit son ID. nochain_2 peut être omis. 
//    Sauf pour -1, les valeurs spécifiées ne doivent pas correspondre avec la valeur de chainID. 

		
//hitonce = hitonce_flag (boolean)
//    En mettant 1, le HitDef n'affectera qu'un seul adversaire. 
//        Si le coup réussit, toutes les autres cibles seront abandonnées. 
//        0 par défaut la plupart du temps. La seule exception est si 
//        le paramètre attr est de type projection, ce qui met la valeur défaut à 1.
		map.put("hitonce", new Object[] {
				new Valueable() {
					public Object getValue(String spriteId, Valueable... params) {
						final String name = "attr";
						Object[] objectValues = getValueFromName(spriteId, name);
						if (objectValues.length > 0) {
							AttrClass attrClass = ((AttrClass)objectValues[0]);
							if (attrClass.isAttrType(AttrType.P)) {
								return 1;
							}
						}
						return 0;
					}}
		});
		
//
//kill = kill_flag (entier)
//    Mettez 0 pour que ce coup ne puisse pas mettre KO l'adversaire si le coup réussit. 
//        1 par défaut.
		map.put("kill", new Object[] {1});
//
//guard.kill = gkill_flag (entier)
//    Mettez 0 pour que ce coup ne puisse pas mettre KO l'adversaire si le coup est bloqué. 
//        1 par défaut.
		map.put("guard.kill", new Object[] {1});
		
//fall.kill = fkill_flag (entier)
//    Mettez 0 pour que ce coup ne puisse par mettre KO l'adversaire 
//        lorsqu'il tombe au sol (voir fall.damage). 
//        1 par défaut.
		map.put("fall.kill", new Object[] {1});
		
//
//numhits = hit_count (entier)
//    Indique combien de coups ce HitDef doit ajouter au compteur de combo. 
//        1 par défaut.
		map.put("numhits", new Object[] {1});
		
//
//getpower = p1power, p1gpower (entier)
//    p1power spécifie combien de power doit être donné à P1 si le coup réussit. 
//    p1gpower indique combien de power doit être donné à P1 si le coup est bloqué. 
//    Si omis, p1power vaut hit_damage (du paramètre "damage") multiplié par la 
//    valeur du paramètre Default.Attack.LifeToPowerMul spécifié dans data/mugen.cfg. 
//    Si p1gpower est omis, sa valeur défaut est de celle spécifiée pour p1power divisé par 2.
    // TODO
//
//givepower = p2power, p2gpower (entier)
//    p2power spécifie combien de power doit être donné à P2 si le coup réussit. 
//    p2gpower indique combien de power doit être donné à P2 si le coup est bloqué. 
//    Si omis, p2power vaut hit_damage (du paramètre "damage") multiplié par la 
//    valeur du paramètre Default.GetHit.LifeToPowerMul spécifié dans data/mugen.cfg. 
//    Si p2gpower est omis, sa valeur défaut est de celle spécifiée pour p2power divisé par 2.
		// TODO
//
//palfx.time = palfx_time (entier)
//palfx.mul = r1, g1, b1 (entier)
//palfx.add = r2, g2, b2 (entier)
//    Si inclus, ceci vous permet des effets de palette sur P2 si le coup réussit. 
//        palfx_time est la durée en ticks pendant laquelle l'effet de palette est effectif sur P2. 
//        Les autres paramètres sont les mêmes que ceux du controller PalFX.
		// TODO
		
//envshake.time = envshake_time (entier)
//envshake.freq = envshake_freq (flottant)
//envshake.ampl = envshake_ampl (entier)
//envshake.phase = envshake_phase (flottant)
//    Si inclus, fait trembler l'écran si le coup réussit. 
//    envshake_time est la durée en ticks pendant laquelle l'écran doit trembler. 
//    Les autres paramètres sont les mêmes que ceux du controller EnvShake.


//fall.envshake.time = envshake_time (entier)
//fall.envshake.freq = envshake_freq (flottant)
//fall.envshake.ampl = envshake_ampl (entier)
//fall.envshake.phase = envshake_phase (flottant)
//    Identiques aux paramètres envshake.*, sauf que les effets  s'appliquent seulement quand P2 touche le sol.

//attack.width = z1, z2 (entier)
//    Non utilisé pour l'instant.

		return map;
	}

	@Override
	protected Object[] getDefaultValues(String name) {
		return _DEFAULT_VALUES_MAP.get(name);
	}
	public Hitdef() {
		this("hitdef", new String[] { "attr", "hitflag", "guardflag",
				"affectteam", "damage", "animtype", "priority", "pausetime",
				"sparkno", "sparkxy", "hitsound", "guardsound", "ground.type", 
				"ground.slidetime", "ground.hittime", 
				"ground.velocity", "ground.cornerpush.veloff",
				"yaccel","mindist","maxdist","snap","p1sprpriority","p2sprpriority","p1facing","p1getp2facing",
				"p2facing","p1stateno","p2stateno","p2getp1state","forcestand","fall","chainid","nochainid",
				"hitonce","kill","getpower","givepower",
				"air.velocity","air.cornerpush.veloff","air.animtype","air.hittime",
				"air.type","air.fall","air.juggle","air.guard.velocity",
				"air.guard.cornerpush.veloff","air.guard.ctrltime","ownpal","palfx.color","palfx.interval",
				
				"airguard.velocity", "yaccel",
				"airguard.cornerpush.veloff","airguard.ctrltime",
				
				"down.cornerpush.veloff","down.velocity",
				"down.hittime","down.bounce","envshake.time","envshake.freq",
				"envshake.ampl","envshake.phase","fall.envshake.time","fall.envshake.freq",
				"fall.envshake.ampl","fall.envshake.phase","fall.xvelocity","fall.yvelocity",
				"fall.recover","fall.recovertime","fall.damage","fall.animtype",
				"fall.kill","guard.kill","guard.slidetime","guard.hittime","guard.pausetime",
				"guard.sparkno","guard.ctrltime","guard.dist","guard.velocity",
				"numhits", "guard.cornerpush.veloff","palfx.time","palfx.mul","palfx.add","palfx.sinadd", "attack.width",
				"id", "sprpriority", 
				
				//
				
				"sparkhitauto", "sparkguardauto"
				// check For id
		});
	}
	public Hitdef(String name, String...params) {
		super(name, params);
	}
	/*
	 * attr = hit_attribute (string) This is the attribute of the attack. It is
	 * used to determine if the attack can hit P2. It has the format: attr =
	 * arg1, arg2 Where: - arg1 is either "S", "C" or "A". Similar to
	 * "statetype" for the StateDef, this says whether the attack is a standing,
	 * crouching, or aerial attack. - arg2 is a 2-character string. The first
	 * character is either "N" for "normal", "S" for "special", or "H" for
	 * "hyper" (or "super", as it is commonly known). The second character must
	 * be either "A" for "attack" (a normal hit attack), "T" for "throw", or "P"
	 * for projectile.
	 * 
	 */


	public static Valueable[] parseForAttr(String name, final String value) {
		final Object v = BeanTools.getConvertersMap().get(AttrClass.class).convert(value);
		Valueable[] vals = new Valueable[1];
		vals = new Valueable[1];
		vals[0] = new Valueable() {
			public Object getValue(String spriteId, Valueable... params) {
				return v;
			}
		};
		return vals;
	}

	/*
	 * hitflag = hit_flags (string) This determines what type of state P2 must
	 * be in for P1 to hit. hit_flags is a string containing a combination of
	 * the following characters: "H" for "high", "L" for "low" or "A" for air.
	 * "M" (mid) is equivalent to saying "HL". "F" is for fall, and if included
	 * will allow P1 to juggle falling opponents in the air. "D" is for "lying
	 * Down", and if included allows P1 to hit opponents lying down on the
	 * ground. Two optional characters are "+" and "-". If "+" is added, then
	 * the hit only affects people in a gethit state. This may be useful for
	 * chain-moves that should not affect opponents who were not hit by the
	 * first move in the chain attack. If "-" is added, then the hit only
	 * affects players that are NOT in a gethit state. You should use "-" for
	 * throws and other moves you do not want P1 to be able to combo into. "+"
	 * and "-" are mutually exclusive, ie. cannot be used at the same time. If
	 * omitted, this defaults to "MAF".
	 */

	public static Valueable[] parseForHitflag(String name, String value) {

		Valueable[] vals = new Valueable[1];
		int tempInt = 0;
		for (char c : value.toUpperCase().toCharArray()) {
			String str = c + "";
			str = str.equals("+") ? "PLUS": str.equals("-")? "MOINS": str;
			if (",".equals(str)) {
				continue;
			}
			try {
				tempInt |= HitFlag.valueOf(str).getBit();
			} catch (Exception e) {
				// TODO: remove try catch
			}
		}
		final int iarg = tempInt;
		tempInt = 0;
		vals[0] = new Valueable() {
			public Object getValue(String spriteId, Valueable... params) {
				return iarg;
			}
		};
		return vals;
	}

	/*
	 * guardflag = hit_flags (string) This determines how P2 may guard the
	 * attack. hit_flags is a string containing a combination of the following
	 * characters: "H" for "high", "L" for "low" or "A" for air. "M" (mid) is
	 * equivalent to saying "HL".
	 */

	public static Valueable[] parseForGuardflag(String name, String value) {


		Valueable[] vals = new Valueable[1];
		int tempInt = 0;
		for (char c : value.toUpperCase().toCharArray()) {
			String vl = String.valueOf(c);
			switch (c) {
				case '+':
					vl = "PLUS";
					break;
				case '-':
					vl = "MOINS";
					break;
	
				default:
					break;
			}
			if (GuardFlag.isGuardFlag(vl))
				tempInt |= GuardFlag.valueOf(vl).getBit();
		}
		final int iarg = tempInt;
		tempInt = 0;
		vals[0] = new Valueable() {
			public Object getValue(String spriteId, Valueable... params) {
				return iarg;
			}
		};
		return vals;
	}

	/*
	 * affectteam = team_type (string) team_type specifies which team's players
	 * can be hit by this HitDef. Use B for both teams (all players), E for
	 * enemy team (opponents), or F for friendly team (your own team). The
	 * default is E.
	 * 
	 */

	public static Valueable[] parseForAffectteam(String name, String value) {


		Valueable[] vals = new Valueable[1];
		final AffectTeam affectTeam = AffectTeam.valueOf(value.toUpperCase());
		vals[0] = new Valueable() {
			public Object getValue(String spriteId, Valueable... params) {
				return affectTeam;
			}
		};
		return vals;
	}

	/*
	 * damage = hit_damage, guard_damage (int) hit_damage is the damage that P2
	 * takes when hit by P2. The optional guard_damage parameter is the damage
	 * taken by P2 if the hit is guarded. Both default to zero if omitted.
	 */

	public static Valueable[] parseForDamage(String name, String value) {
		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}

	/*
	 * animtype = anim_type (string) This refers to the type of animation that
	 * P2 will go into when hit by the attack. Choose from "light", "medium",
	 * "hard", "back", "up", or "diagup". The first three should be
	 * self-explanatory. "Back" is the animation where P2 is knocked off her
	 * feet. "Up" should be used when the character is knocked straight up in
	 * the air (for instance, by an uppercut), and "DiagUp" should be used when
	 * the character is knocked up and backwards in the air, eventually landing
	 * on his head. The default is "Light".
	 * 
	 */

	public static Valueable[] parseForAnimtype(String name, String value) {


		final AnimType animType = AnimType.getValueFromStr(value.toUpperCase());
		Valueable[] vals = new Valueable[] { new Valueable() {
			public Object getValue(String spriteId, Valueable... params) {
				return animType;
			}
		}

		};
		return vals;
	}

	/*
	 * priority = hit_prior (int), hit_type (string) Specifies the priority for
	 * this hit. Hits with higher priorities take precedence over hits with
	 * lower priorities. Valid values for hit_prior are 1-7. Defaults to 4.
	 * hit_type, if specified, gives the priority class of the hit. Valid
	 * priority classes are Dodge, Hit, and Miss. The priority class determines
	 * the tiebreaking behavior when P1 and P2 hit each other simultaneously
	 * with equal priorities. The behavior is as follows: Hit vs. Hit: both P1
	 * and P2 are hit Hit vs. Miss: Hit hits, Miss misses Hit vs. Dodge: No hits
	 * Dodge vs. Dodge: No hits Dodge vs. Miss: No hits Miss vs. Miss: No hits
	 * In the case of a no-hit tie, the respective HitDefs stay enabled.
	 * 
	 */

	public static Valueable[] parseForPriority(String name, String value) {
		final Valueable[] vals = ExpressionFactory.evalExpression(value);
		if (vals.length == 1) {
			Valueable[] result = new Valueable[] { 
					new Valueable() {
						public Object getValue(String spriteId, Valueable... params) {
							return vals[0].getValue(spriteId, params);
						}
					},
					new Valueable() {

						public Object getValue(String spriteId, Valueable... params) {
							return Priority.HitType.HIT;
						}}
			};
			return result;
		} else if (vals.length == 2) {
			Valueable[] result = new Valueable[] { 
					new Valueable() {
						public Object getValue(String spriteId, Valueable... params) {
							return vals[0].getValue(spriteId, params);
						}
					},
					new Valueable() {

						public Object getValue(String spriteId, Valueable... params) {
							return Priority.HitType.valueOf(vals[1].getValue(spriteId, params).toString().toUpperCase());
						}}
			};
			return result;
		} else {
			throw new IllegalArgumentException();
		}
		
	}

	/*
	 * pausetime = p1_pausetime, p2_shaketime (int) This is the time that each
	 * player will pause on the hit. p1_pausetime is the time to freeze P1,
	 * measured in game-ticks. p2_pausetime is the time to make P2 shake before
	 * recoiling from the hit. Defaults to 0,0 if omitted.
	 * 
	 */
	public static Valueable[] parseForPausetime(String name, String value) {
		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}

	/*
	 * sparkno = action_no (int) This is the action number of the spark to
	 * display if the hit is successful. To play a spark out of the player's
	 * .AIR file, precede the action number with an S. Defaults to the value set
	 * in the player variables if omitted.
	 */
	public static Valueable[] parseForSparkno(String name, String value) {
		if (value.equals("-1")) {
			return new Valueable[] {				
					new Valueable() {
						public Object getValue(String spriteId, Valueable... params) {
							return GameFight.getInstance().getSpriteInstance(spriteId).getInfo().getData().getSparkno();
					}
				}
			};
		} else if (value.startsWith("s")) {
			value = value.substring(1);
			String[] tokens = ExpressionFactory.expression2Tokens(value);
			final Valueable[] vals = ExpressionFactory.evalExpression(tokens);
			return new Valueable[] {				
					new Valueable() {
						public Object getValue(String spriteId, Valueable... params) {
							return "s" + vals[0].getValue(spriteId, params);
					}
				}
			};
		} else {
			String[] tokens = ExpressionFactory.expression2Tokens(value);
			Valueable[] vals = ExpressionFactory.evalExpression(tokens);
			return vals;
		}


	}

	/*
	 * sparkxy = spark_x, spark_y (int) This is where to make the hit/guard
	 * spark. spark_x is a coordinate relative to the front of P2. A negative
	 * value makes the spark deeper inside P2. "Front" refers to the x- position
	 * at P2's axis offset towards P1 by the corresponding width value in the
	 * [Size] group in P2's player variables. spark_y is relative to P1. A
	 * negative value makes a spark higher up. You can use a tool like AirView
	 * to determine this value by positioning the cursor at the "attack spot"
	 * and reading off the value of the y-position. Defaults to 0,0 if omitted.
	 */

	public static Valueable[] parseForSparkxy(String name, String value) {


		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}

	/*
	 * hitsound = snd_grp, snd_item (int) This is the sound to play on hit (from
	 * common.snd). The included fight.snd lets you choose from 5,0 (light hit
	 * sound) through to 5,4 (painful whack). To play a sound from the player's
	 * own SND file, precede the first number with an "S". For example,
	 * "hitsound = S1,0". Defaults to the value set in the player variables if
	 * omitted.
	 */
	public static Valueable[] parseForGuardsound(String name, String value) {


		final boolean isPlaySpriteSnd;
		if (value.indexOf("s") != -1) {
			isPlaySpriteSnd = true;
			value = value.replaceAll("s", "");
		} else {
			isPlaySpriteSnd = true;
		}
		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		Valueable[] result = vals;
		if (isPlaySpriteSnd) {
			result = new Valueable[vals.length + 1];
			System.arraycopy(vals, 0, result, 0, vals.length);
			result[vals.length] = new Valueable() {
				public Object getValue(String spriteId, Valueable... params) {
					return isPlaySpriteSnd;
				}
			};
		}
		return result;
	}

	/*
	 * guardsound = snd_grp, snd_item (int) This is the sound to play on guard
	 * (from common.snd). Only 6,0 is available at this time. To play a sound
	 * from the player's own SND file, precede the first number with an "S".
	 * There is no facility to play a sound from the opponent's SND file.
	 * Defaults to the value set in the player variables if omitted.
	 */

	public static Valueable[] parseForHitsound(String name, String value) {


		final boolean isPlaySpriteSnd;
		if (value.indexOf("s") != -1) {
			isPlaySpriteSnd = true;
			int index = value.indexOf("s");
			assert index == 0;
			value = value.substring(1);
		} else {
			isPlaySpriteSnd = false;
		}
		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		Valueable[] result = vals;
		if (isPlaySpriteSnd) {
			result = new Valueable[vals.length + 1];
			System.arraycopy(vals, 0, result, 0, vals.length);
			result[vals.length] = new Valueable() {
				public Object getValue(String spriteId, Valueable... params) {
					return isPlaySpriteSnd;
				}
			};
		}
		return result;
	}

	/*
	 * ground.type = attack_type (string) This is the kind of attack if P2 is on
	 * the ground. Choose from "High" for attacks that make P2's head snap
	 * backwards, "Low" for attacks that look like that hit in the stomach,
	 * "Trip" for low sweep attacks, or "None" to not do anything to P2. "High"
	 * and "Low" attacks have no effect on P2 if the AnimType is "Back". If P2
	 * is hit from behind, "High" will be displayed as "Low" and vice-versa.
	 * Defaults to "High" if omitted.
	 * 
	 */
	public static Valueable[] parseForGround$type(String name, String value) {
		if (value.toUpperCase().startsWith("H"))
			value = "HIGH";
		Valueable[] vals = new Valueable[1];
		final HitDefSub.Type type = HitDefSub.Type.valueOf(value.toUpperCase());
		vals[0] = new Valueable() {
			public Object getValue(String spriteId, Valueable... params) {
				return type;
			}
		};
		return vals;
	}

	/*
	 * ground.slidetime = slide_time (int) This is the time in game-ticks that
	 * P2 will slide back for after being hit (this time does not include the
	 * pausetime for P2). Applicable only to hits that keep P2 on the ground.
	 * Defaults to 0 if omitted.
	 */
	public static Valueable[] parseForGround$slidetime(String name,
			String value) {
		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}

	/*
	 * ground.hittime = hit_time (int) Time that P2 stays in the hit state after
	 * being hit. Increase this value to keep P2 in the hit state for a longer
	 * time, perhaps to make it easier to combo. Applicable only to hits that
	 * keep P2 on the ground. Defaults to 0 if omitted.
	 */
	public static Valueable[] parseForGround$hittime(String name, String value) {


		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}

	/*
	 * ground.velocity = x_velocity, y_velocity (float) Initial velocity to give
	 * P2 after being hit, if P2 is on the ground. If y_velocity is not zero, P2
	 * will be knocked into the air. Both values default to 0 if omitted. You
	 * can leave out the y_velocity if you want P2 to remain on the ground.
	 */
	public static Valueable[] parseForGround$velocity(String name,
			String value) {
		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}

	/*
	 * ground.cornerpush.veloff = x_velocity (float) Determines the additional
	 * velocity (velocity offset) to impart to the player if he lands a ground
	 * hit in the corner. Setting this to a higher value will cause the player
	 * to be "pushed back" farther out of the corner. If omitted, default value
	 * depends on the attr parameter. If arg1 of attr is "A", default value is
	 * 0. Otherwise, defaults to 1.3*guard.velocity.
	 */

	public static Valueable[] parseForGround$cornerpush$veloff(String name,
			String value) {
		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}

	/*
	 * 
	 * yaccel = accel (float) Specifies the y acceleration to impart to P2 if
	 * the hit connects. Defaults to 0.
	 */

	public static Valueable[] parseForYaccel(String name, String value) {


		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}

	/*
	 * mindist = x_pos, y_pos (int)
	 */
	public static Valueable[] parseForMindist(String name, String value) {


		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}

	/*
	 * maxdist = x_pos, y_pos (int) These let you control the minimum and
	 * maximum distance of P2 relative to P1, after P2 has been hit. These
	 * parameters are not commonly used. Defaults to no change in P2's position
	 * if omitted.
	 */
	public static Valueable[] parseForMaxdist(String name, String value) {


		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}

	/*
	 * snap = x_pos, y_pos (int) This moves P2 to the specified position
	 * relative to P1 if hit. This parameter is not normally used. If you want
	 * to snap P2 to a particular position for a throw, it is recommended you
	 * use a "TargetBind" controller in P1's throwing state instead. Defaults to
	 * no change in P2's position if omitted.
	 */
	public static Valueable[] parseForSnap(String name, String value) {


		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}

	/*
	 * p1sprpriority = drawing_priority (int) This is the drawing priority of
	 * P1's sprite if the move hits or is guarded by P2. Together with the
	 * p2sprpriority parameter, it controls whether or not P1 is drawn in front
	 * of or behind P2. The default value is 1.
	 */
	public static Valueable[] parseForP1sprpriority(String name, String value) {


		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}

	/*
	 * p2sprpriority = drawing_priority (int) This is the drawing priority of
	 * P2's sprite if the move hits or is guarded by P2. The default value is 0.
	 */
	public static Valueable[] parseForP2sprpriority(String name, String value) {


		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}

	/*
	 * p1facing = facing (int) Set to -1 to make P1 turn around if the hit is
	 * successful. Usually useful only for throws. The default value is no
	 * change in where P1 is facing.
	 */
	public static Valueable[] parseForP1facing(String name, String value) {


		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}

	/*
	 * p1getp2facing = facing (int) Set to 1 to have P1 face in the same
	 * direction as P2 is facing after the hit connects, and -1 to have P1 face
	 * the opposite direction from P2. Defaults to 0 (no change). If nonzero,
	 * this parameter takes precedence over p1facing.
	 */
	public static Valueable[] parseForP1getp2facing(String name, String value) {


		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}

	/*
	 * p2facing = facing (int) Set to 1 to make P2 face the same direction as P1
	 * if the hit is successful, -1 to make P2 face away. The default value is
	 * no change in where P2 is facing.
	 */
	public static Valueable[] parseForP2facing(String name, String value) {


		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}

	/*
	 * p1stateno = state_no (int) This is the number of the state to set P1 to
	 * if the hit is successful. Used mainly for throws.
	 */
	public static Valueable[] parseForP1stateno(String name, String value) {


		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}

	/*
	 * p2stateno = state_no (int) This is the number of the state to set P2 to
	 * if the hit is successful. P2 will get P1's states and animation data.
	 * Used mainly for throws.
	 */
	public static Valueable[] parseForP2stateno(String name, String value) {


		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}

	/*
	 * p2getp1state = value (int) Set to 0 to prevent P2 from getting P1's state
	 * and animation data, in case you do not want that default behaviour of the
	 * "p2stateno" parameter. Defaults to 1 if the "p2stateno" parameter is
	 * used. Ignored otherwise.
	 */
	public static Valueable[] parseForP2getp1state(String name, String value) {


		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}

	/*
	 * forcestand = value (int) Set to 1 to force P2 to a standing state-type if
	 * the hit is successful, and P2 is in a crouching state. Normally defaults
	 * to 0, but if the y_velocity of the "ground.velocity" parameter is
	 * non-zero, it defaults to 1.
	 */
	public static Valueable[] parseForForcestand(String name, String value) {


		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}

	/*
	 * fall = value (int) Set to 1 if you want P2 to go into a "fall" state
	 * (where P2 hits the ground without recovering control in the air). Use if
	 * you want a move to "knock down" P2. id = id_number (int) Main use of this
	 * is for chain moves. You can use this number to later detect if a player
	 * was last hit by this particular HitDef. This number is called the
	 * targetID. It is used in controllers such as TargetBind, or in the
	 * target(ID) redirection keyword. Valid values are all values >= 1. If
	 * omitted, defaults to 0 (no ID). Do not confuse targetID with PlayerID.
	 */
	public static Valueable[] parseForFall(String name, String value) {


		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}

	/*
	 * chainID = id_number (int) Main use of this is for chain moves. If P2 was
	 * last hit by a move by P1 with this ID, only then can he be hit by the
	 * HitDef with this chainID. You can use this in the following parts of a
	 * chain move. Note that chain moves are still possible even without the use
	 * of the "id" and "chainid" parameters. Valid values are all values >= 1.
	 * If omitted, defaults to -1 (chain from any hit).
	 */
	public static Valueable[] parseForChainid(String name, String value) {


		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}

	/*
	 * nochainID = nochain_1, nochain_2 (int) nochainID specifies up to 2 ID
	 * numbers of hits which cannot be chained into this hit. If these are -1
	 * (the default), then chaining is not explicitly disabled for any hit ID
	 * numbers. nochain_2 can be omitted. Except for -1, the values specified
	 * must not coincide with the value for chainID. This parameter has no
	 * effect if P2 is hit by a third party between P1's previous HitDef and the
	 * current HitDef.
	 */
	public static Valueable[] parseForNochainid(String name, String value) {


		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}

	/*
	 * hitonce = hitonce_flag (boolean) If set to 1, the HitDef only affects one
	 * opponent. If the hit is successful, all other targets will be dropped.
	 * Defaults to 0 most of the time. Only exception is if the "attr" parameter
	 * is a throw type, which makes it default to 1.
	 */
	public static Valueable[] parseForHitonce(String name, String value) {


		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}

	/*
	 * kill = kill_flag (int) Set to 0 if this hit should not be able to KO the
	 * opponent when the hit is successful. Defaults to 1. numhits = hit_count
	 * (int) hit_count indicates how many hits this hitdef should add to the
	 * combo counter. Defaults to 1.
	 */
	public static Valueable[] parseForKill(String name, String value) {


		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}

	/*
	 * getpower = p1power, p1gpower (int) p1power specifies the amount of power
	 * to give P1 if this HitDef connects successfully. p1gpower specifies the
	 * amount of power to give P1 if this HitDef is guarded. If omitted, p1power
	 * defaults to hit_damage (from "damage" parameter) multiplied by the value
	 * of Default.Attack.LifeToPowerMul specified in data/mugen.cfg. If p1gpower
	 * is omitted, it defaults to the value specified for p1power divided by 2.
	 */
	public static Valueable[] parseForGetpower(String name, String value) {


		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}

	/*
	 * givepower = p2power, p2gpower (int) p2power specifies the amount of power
	 * to give P2 if this HitDef connects successfully. p2gpower specifies the
	 * amount of power to give P2 if this HitDef is guarded. If omitted, p1power
	 * defaults to hit_damage (from "damage" parameter) multiplied by the value
	 * of Default.GetHit.LifeToPowerMul specified in data/mugen.cfg. If p1gpower
	 * is omitted, it defaults to the value specified for p1power divided by 2.
	 */

	public static Valueable[] parseForGivepower(String name, String value) {


		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}



	
	/* Optional parameters:
	 */
	 
	 /* air.velocity = x_velocity, y_velocity (float)
	 * Initial velocity to give P2 if P2 is hit in the air. Defaults to 0,0 if
	 * omitted.
	 */

	public static Valueable[] parseForAir$velocity(String name, String value) {


		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}
	
	 /* air.recover = 'int)
	 */

	public static Valueable[] parseForAir$recover(String name, String value) {


		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}

	/*
	 * air.cornerpush.veloff = x_velocity (float) Determines the additional
	 * velocity (velocity offset) to impart to the player if he lands a hit to
	 * an aerial opponent in the corner. Setting this to a higher value will
	 * cause the player to be "pushed back" farther out of the corner. Defaults
	 * to ground.cornerpush.veloff if omitted.
	 */

	public static Valueable[] parseForAir$cornerpush$veloff(String name,
			String value) {
		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}

	/*
	 * air.animtype = anim_type (string) Similar to the "animtype" parameter,
	 * this is the animtype to set P2 to if P2 is in the air, instead of on the
	 * ground. Defaults to the same value as the "animtype" parameter if
	 * omitted.
	 * 
	 */

	public static Valueable[] parseForAir$animtype(String name, String value) {



		final HitDefSub.AnimType animType = AnimType.valueOf(value
				.toUpperCase());

		return new Valueable[] { new Valueable() {
			public Object getValue(String spriteId, Valueable... params) {
				return animType;
			}
		} };
	}

	/*
	 * air.hittime = hit_time (int) Time that p2 stays in the hit state after
	 * being hit in or into the air, before being able to guard again. This
	 * parameter has no effect if the "fall" parameter is set to 1. Defaults to
	 * 20 if omitted.
	 */

	public static Valueable[] parseForAir$hittime(String name, String value) {


		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}

	/*
	 * 
	 * air.type = attack_type (string) This is the kind of attack if P2 is in
	 * the air. Defaults to the same value as "ground.type" if omitted.
	 */

	public static Valueable[] parseForAir$type(String name, String value) {


		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}

	/*
	 * air.fall = value (int) Set to 1 if you want P2 to go into a "fall" state
	 * (where P2 hits the ground without recovering control in the air) if hit
	 * in the air. Defaults to the same value as fall.
	 */

	public static Valueable[] parseForAir$fall(String name, String value) {


		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}

	/*
	 * air.juggle = juggle_points (int) The amount of additional juggle points
	 * the hit requires. Do not confuse this with the "juggle" parameter in the
	 * StateDef. You typically do not need this parameter, except for HitDefs of
	 * projectiles. Defaults to 0 if omitted.
	 */

	public static Valueable[] parseForAir$juggle(String name, String value) {


		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}

	/*
	 * 
	 * airguard.velocity = x_velocity, y_velocity (float) Velocity to give P2 if
	 * P2 guards the hit in the air. Defaults to x_velocity*1.5, y_velocity/2,
	 * where x_velocity and y_velocity are values of the "air.velocity"
	 * parameter.
	 */

	public static Valueable[] parseForAirguard$velocity(String name,
			String value) {
		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}

	/*
	 * 
	 * airguard.cornerpush.veloff = x_velocity (float) Determines the additional
	 * velocity (velocity offset) to impart to the player if his hit is guarded
	 * in the corner. Setting this to a higher value will cause the player to be
	 * "pushed back" farther out of the corner. Defaults to
	 * guard.cornerpush.veloff if omitted.
	 */

	public static Valueable[] parseForAirguard$cornerpush$veloff(String name,
			String value) {
		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}

	/*
	 * airguard.ctrltime = ctrl_time (int) This is the time before p2 regains
	 * control in the air guard state. Defaults to the same value as
	 * "guard.ctrltime" if omitted.
	 */

	public static Valueable[] parseForAirguard$ctrltime(String name,
			String value) {
		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}
/////////////////////////////////
	/*
	 * 
	 * air.guard.velocity = x_velocity, y_velocity (float) Velocity to give P2 if
	 * P2 guards the hit in the air. Defaults to x_velocity*1.5, y_velocity/2,
	 * where x_velocity and y_velocity are values of the "air.velocity"
	 * parameter.
	 */

	public static Valueable[] parseForAir$guard$velocity(String name,
			String value) {
		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}

	/*
	 * 
	 * air.guard.cornerpush.veloff = x_velocity (float) Determines the additional
	 * velocity (velocity offset) to impart to the player if his hit is guarded
	 * in the corner. Setting this to a higher value will cause the player to be
	 * "pushed back" farther out of the corner. Defaults to
	 * guard.cornerpush.veloff if omitted.
	 */

	public static Valueable[] parseForAir$guard$cornerpush$veloff(String name,
			String value) {
		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}

	/*
	 * air.guard.ctrltime = ctrl_time (int) This is the time before p2 regains
	 * control in the air guard state. Defaults to the same value as
	 * "guard.ctrltime" if omitted.
	 */

	public static Valueable[] parseForAir$guard$ctrltime(String name,
			String value) {
		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}

	/*
	 * down.cornerpush.veloff = x_velocity (float) Determines the additional
	 * velocity (velocity offset) to impart to the player if he lands a hit on a
	 * downed opponent in the corner. Setting this to a higher value will cause
	 * the player to be "pushed back" farther out of the corner. Defaults to
	 * ground.cornerpush.veloff if omitted.
	 */

	public static Valueable[] parseForDown$cornerpush$veloff(String name,
			String value) {
		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}

	/*
	 * 
	 * down.velocity = x_velocity, y_velocity (float) This is the velocity to
	 * assign P2 if P2 is hit while lying down. If the y_velocity is non-zero,
	 * P2 will be hit into the air. If it is zero, then P2 will slide back on
	 * the ground. Defaults to the same values as the "air.velocity" parameter
	 * if omitted.
	 */

	public static Valueable[] parseForDown$velocity(String name, String value) {


		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}

	/*
	 * down.hittime = hit_time (int) This is the time that P2 will slide back
	 * for if P2 is hit while lying down. This parameter is ignored if the
	 * y_velocity is non- zero for the "down.velocity" parameter.
	 */

	public static Valueable[] parseForDown$hittime(String name, String value) {


		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}

	/*
	 * down.bounce = value (int) Set to 1 if you want P2 to bounce off the
	 * ground one time (using the fall.xvelocity and fall.yvelocity values)
	 * after hitting the ground from the hit. This parameter is ignored if the
	 * y_velocity is zero for the "down.velocity" parameter. Defaults to 0 if
	 * omitted (P2 hits the ground and stays there).
	 */

	public static Valueable[] parseForDown$bounce(String name, String value) {


		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}

	/*
	 * envshake.time = envshake_time (int)
	 */

	public static Valueable[] parseForEnvshake$time(String name, String value) {


		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}

	/*
	 * envshake.freq = envshake_freq (float)
	 */

	public static Valueable[] parseForEnvshake$freq(String name, String value) {


		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}

	/*
	 * envshake.ampl = envshake_ampl (int)
	 */

	public static Valueable[] parseForEnvshake$ampl(String name, String value) {


		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}

	/*
	 * envshake.phase = envshake_phase (float)
	 * 
	 * If included, this shakes the screen if the hit is successful.
	 * envshake_time is the time in game-ticks to shake the screen. The rest of
	 * the parameters are the same as in the EnvShake controller.
	 */

	public static Valueable[] parseForEnvshake$phase(String name, String value) {


		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}

	/*
	 * fall.envshake.time = envshake_time (int)
	 */

	public static Valueable[] parseForFall$envshake$time(String name,
			String value) {
		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}

	/*
	 * fall.envshake.freq = envshake_freq (float)
	 */

	public static Valueable[] parseForFall$envshake$freq(String name,
			String value) {
		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}

	/*
	 * fall.envshake.ampl = envshake_ampl (int)
	 */

	public static Valueable[] parseForFall$envshake$ampl(String name,
			String value) {
		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}

	/*
	 * fall.envshake.phase = envshake_phase (float)
	 * 
	 * Similar to the envshake.* parameters, except the effects are applied only
	 * when P2 hits the ground.
	 * 
	 */

	public static Valueable[] parseForFall$envshake$phase(String name,
			String value) {
		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}

	/*
	 * 
	 * fall.xvelocity = x_velocity (float) This is the x-velocity that P2 gets
	 * when bouncing off the ground in the "fall" state. Defaults to no change
	 * if omitted.
	 */

	public static Valueable[] parseForFall$xvelocity(String name, String value) {


		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}

	/*
	 * fall.yvelocity = y_velocity (float) This is the y-velocity that P2 gets
	 * when bouncing off the ground in the "fall" state. Defaults to -4.5 if
	 * omitted.
	 */

	public static Valueable[] parseForFall$yvelocity(String name, String value) {


		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}
	public static Valueable[] parseForFall$velocity(String name, String value) {


		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}

	/*
	 * fall.recover = value (int) Set to 0 if you do not want P2 to be able to
	 * recover from the "fall" state. Defaults to 1 if omitted (can recover).
	 */

	public static Valueable[] parseForFall$recover(String name, String value) {


		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}

	/*
	 * fall.recovertime = recover_time (int) This is the time that must pass
	 * before P2 is able to recover from the "fall" state. Does not include the
	 * time that P2 is paused for while shaking from the hit. Defaults to 4 if
	 * omitted.
	 */

	public static Valueable[] parseForFall$recovertime(String name,
			String value) {
		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}

	/*
	 * fall.damage = damage_amt (int) Indicates the amount of damage to deal
	 * when P2 hits the ground out of a falling state. Defaults to 0 if omitted.
	 * 
	 */

	public static Valueable[] parseForFall$damage(String name, String value) {
		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}

	/*
	 * fall.animtype = anim_type (string) Similar to the "animtype" parameter,
	 * this is the animtype to set P2 to if P2 is hit while falling. Defaults to
	 * Up if air.animtype is Up, or Back otherwise.
	 */

	public static Valueable[] parseForFall$animtype(String name, String value) {
		final HitDefSub.AnimType animType = AnimType.valueOf(value
				.toUpperCase());

		return new Valueable[] { new Valueable() {
			public Object getValue(String spriteId, Valueable... params) {
				return animType;
			}
		} };
	}

	/*
	 * fall.kill = fkill_flag (int) Set to 0 if this attack should not be able
	 * to KO the opponent when he falls on the ground (see fall.damage).
	 * Defaults to 1.
	 */

	public static Valueable[] parseForFall$kill(String name, String value) {
		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}

	/*
	 * 
	 * guard.kill = gkill_flag (int) Set to 0 if this hit should not be able to
	 * KO the opponent when he guards. Defaults to 1.
	 */

	public static Valueable[] parseForGuard$kill(String name, String value) {


		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}

	/*
	 * guard.slidetime = slide_time (int) Same as "ground.slidetime", but this
	 * is the value if P2 guards the hit. Defaults to same value as
	 * "guard.hittime".
	 */

	public static Valueable[] parseForGuard$slidetime(String name,
			String value) {
		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}

	/*
	 * guard.hittime = hit_time (int) Same as "ground.hittime", but this is the
	 * value if P2 guards the hit. Defaults to same value as "ground.hittime".
	 */

	public static Valueable[] parseForGuard$hittime(String name, String value) {


		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}

	/*
	 * guard.pausetime = p1_pausetime, p2_shaketime (int) Similar to the
	 * "pausetime" parameter, these are the times to pause each player if the
	 * hit was guarded. Defaults to the same values as the "pausetime" parameter
	 * if omitted.
	 */

	public static Valueable[] parseForGuard$pausetime(String name,
			String value) {
		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}

	/*
	 * guard.sparkno = action_no (int) This is the action number of the spark to
	 * display if the hit was guarded. To play a spark out of the player's .AIR
	 * file, precede the action number with an S. Defaults to the value set in
	 * the player variables if omitted.
	 * 
	 */

	public static Valueable[] parseForGuard$sparkno(String name, String value) {


		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}

	/*
	 * guard.ctrltime = ctrl_time (int) This is the time before p2 regains
	 * control in the ground guard state. Defaults to the same value as
	 * "guard.slidetime" if omitted.
	 */

	public static Valueable[] parseForGuard$ctrltime(String name, String value) {


		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}

	/*
	 * guard.dist = x_dist (int) This is the x-distance from P1 in which P2 will
	 * go into a guard state if P2 is holding the direction away from P1.
	 * Defaults to the value in the player variables if omitted. You normally do
	 * not need to use this parameter.
	 */

	public static Valueable[] parseForGuard$dist(String name, String value) {


		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}

	/*
	 * guard.velocity = x_velocity (float) Velocity to give P2 if P2 guards the
	 * hit on the ground. Defaults to the x_velocity value of the
	 * "ground.velocity" parameter if omitted.
	 */

	public static Valueable[] parseForGuard$velocity(String name, String value) {


		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}

	/*
	 * guard.cornerpush.veloff = x_velocity (float) Determines the additional
	 * velocity (velocity offset) to impart to the player if his hit is guarded
	 * in the corner. Setting this to a higher value will cause the player to be
	 * "pushed back" farther out of the corner. Defaults to
	 * ground.cornerpush.veloff if omitted.
	 */

	public static Valueable[] parseForGuard$cornerpush$veloff(String name,
			String value) {
		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}

	/*
	 * palfx.sinadd
	 */
	public static Valueable[] parseForPalfx$sinadd(String name, String value) {


		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}	
	/*
	 * palfx.time = palfx_time (int)
	 */

	public static Valueable[] parseForPalfx$time(String name, String value) {


		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}

	/*
	 * palfx.mul = r1, g1, b1 (int)
	 */

	public static Valueable[] parseForPalfx$mul(String name, String value) {


		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}

	/*
	 * palfx.add = r2, g2, b2 (int) If included, this allows for palette effects
	 * on P2 if the hit is successful. palfx_time is the time in game-ticks to
	 * apply palette effects on P2. palfx_time is 0 by default (no effect). The
	 * rest of the parameters are the same as in the PalFX controller.
	 */

	public static Valueable[] parseForPalfx$add(String name, String value) {


		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}

	/*
	 * attack.width = z1, z2 (int) Not currently used.
	 */

	public static Valueable[] parseForAttack$width(String name, String value) {


		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}
	/*
	 * numhits int
	 */
	public static Valueable[] parseForNumhits(String name, String value) {


		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}
	
	/*
	 * id int
	 */
	public static Valueable[] parseForId(String name, String value) {
		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}

	/*
	 * id int
	 */
	public static Valueable[] parseForSparkhitauto(String name, String value) {
		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}
	/*
	 * id int
	 */
	public static Valueable[] parseForSparkguardauto(String name, String value) {
		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}

	
	/*
	 * sprpriority int
	 */
	public static Valueable[] parseForSprpriority(String name, String value) {
		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}
	
}
