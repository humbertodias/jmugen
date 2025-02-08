package org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns;

import java.util.HashMap;
import java.util.Map;

import org.lee.mugen.core.FightEngine;
import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.character.SpriteCns.Type;
import org.lee.mugen.sprite.character.spiteCnsSubClass.HitDefSub;
import org.lee.mugen.sprite.cns.eval.function.SpriteCnsTriggerFunction;

public class Gethitvar extends SpriteCnsTriggerFunction {

	public Gethitvar() {
		super("gethitvar", new String[] {});
	}
	public void addParam(String name, Valueable[] param) {
//		int index = getParamIndex(name);
//		valueableParams[index] = param;

	}

	@Override
	public Object getValue(String spriteId, Valueable...params) {
		String param = params[0].getValue(spriteId).toString();
		Valueable v = valuableMap.get(param);
		if (v == null)
			return 1; // TODO Check this 
		return v.getValue(spriteId);
	};
	
	private static final String[] CONST = {
		"xveladd", "yveladd", "type", "animtype"
		,"airtype", "groundtype", "damage", "hitcount"
		,"fallcount", "hitshaketime", "hittime", "slidetime"
		,"ctrltime", "recovertime", "xoff", "yoff"
		,"zoff", "xvel", "yvel", "yaccel"
		,"chainid", "guarded", "isbound"
		,"fall.damage", "fall.xvel", "fall.yvel", "fall.recover"
		,"fall.recovertime", "fall.kill", "fall.envshake.time", "fall.envshake.freq"
		,"fall.envshake.ampl", "fall.envshake.phase", "fall"
	};
	
	
	
	private Map<String, Valueable> valuableMap = buildMap();

	
	private Map<String, Valueable> buildMap() {
		Map<String, Valueable> map = new HashMap<String, Valueable>();
		
//		xveladd : Retourne la vitesse x additionnelle ajoutée à celle du joueur quand il est mis KO (flottant).
		map.put("xveladd", new Valueable() {
			public Object getValue(String spriteId, Valueable... params) {
				Sprite sprOne = GameFight.getInstance().getSpriteInstance(spriteId);
				HitDefSub hitdefFrom = sprOne.getInfo().getLastHitdef();
				if (hitdefFrom == null)
					return null;
				
				
				if (sprOne.getInfo().getType() == Type.A) {
					return -hitdefFrom.getAir().getVelocity().getX();
					
				} else {
					return -hitdefFrom.getGround().getVelocity().getX();
				}
			}});
		
//		yveladd : Retourne la vitesse y additionnelle ajoutée à celle du joueur quand il est mis KO (flottant).
		map.put("yveladd", new Valueable() {
			public Object getValue(String spriteId, Valueable... params) {
				Sprite sprOne = GameFight.getInstance().getSpriteInstance(spriteId);
				HitDefSub hitdefFrom = sprOne.getInfo().getLastHitdef();
				if (hitdefFrom == null)
					return null;
				
				
				if (sprOne.getInfo().getType() == Type.A) {
					return hitdefFrom.getAir().getVelocity().getY();
					
				} else {
					return hitdefFrom.getGround().getVelocity().getY();
				}
			}});
		
//		type : Retourne le type du coup : 0 pour aucun, 1 pour high, 2 pour low, 3 pour trip (au sol uniquement). 
		map.put("type", new Valueable() {
			public Object getValue(String spriteId, Valueable... params) {
				Sprite sprOne = GameFight.getInstance().getSpriteInstance(spriteId);
				HitDefSub hitdefFrom = sprOne.getInfo().getLastHitdef();
				if (hitdefFrom == null)
					return null;
				
				
				return hitdefFrom.getAttr().getType();
			}});
		
//		animtype : Retourne le type d'animation du coup (0 pour light, 1 pour medium, 2 pour hard, 3 pour back, 4 pour up, 5 pour diag-up).
		map.put("animtype", new Valueable() {
			public Object getValue(String spriteId, Valueable... params) {
				Sprite sprOne = GameFight.getInstance().getSpriteInstance(spriteId);
				HitDefSub hitdefFrom = sprOne.getInfo().getLastHitdef();
				if (hitdefFrom == null)
					return null;
				return hitdefFrom.getAnimtype().value;
			}});
		
//		airtype : Retourne le type spécifié dans le HitDef pour un coup aérien.
		map.put("airtype", new Valueable() {
			public Object getValue(String spriteId, Valueable... params) {
				Sprite sprOne = GameFight.getInstance().getSpriteInstance(spriteId);
				HitDefSub hitdefFrom = sprOne.getInfo().getLastHitdef();
				if (hitdefFrom == null)
					return null;
				return hitdefFrom.getAir().getType().ordinal();
			}});
		
//		groundtype : Retourne le type spécifié dans le HitDef pour un coup au sol.
		map.put("groundtype", new Valueable() {
			public Object getValue(String spriteId, Valueable... params) {
				Sprite sprOne = GameFight.getInstance().getSpriteInstance(spriteId);
				HitDefSub hitdefFrom = sprOne.getInfo().getLastHitdef();
				if (hitdefFrom == null)
					return null;
				return hitdefFrom.getGround().getType().ordinal();
			}});

		
//		damage : Retourne les dommages infligés par le coup (entier).
		map.put("damage", new Valueable() {
			public Object getValue(String spriteId, Valueable... params) {
				Sprite sprOne = GameFight.getInstance().getSpriteInstance(spriteId);
				HitDefSub hitdefFrom = sprOne.getInfo().getLastHitdef();
				if (hitdefFrom == null)
					return null;
				if (FightEngine.isBlockState(sprOne.getSpriteState().getCurrentState().getIntId())) {
					return hitdefFrom.getDamage().getGuard_damage();
				}
				return hitdefFrom.getDamage().getHit_damage();
			}});

		
//		hitcount : Retourne le nombre de coups subi par le joueur dans le combo courant (entier).
		map.put("hitcount", new Valueable() {
			public Object getValue(String spriteId, Valueable... params) {
				Sprite sprOne = GameFight.getInstance().getSpriteInstance(spriteId);
				HitDefSub hitdefFrom = sprOne.getInfo().getLastHitdef();
				if (hitdefFrom == null)
					return null;
				return hitdefFrom.getNumhits();
			}});
		
//		fallcount : Retourne le nombre de fois que le joueur a été touché au sol dans le combo courant (entier).
		map.put("fallcount", new Valueable() {
			public Object getValue(String spriteId, Valueable... params) {
				Sprite sprOne = GameFight.getInstance().getSpriteInstance(spriteId);
				HitDefSub hitdefFrom = sprOne.getInfo().getLastHitdef();
				if (hitdefFrom == null)
					return null;
				return hitdefFrom.getDown().getBounce();
			}});
		
//		hitshaketime : Retourne le délai où le joueur est "gelé" pendant le coup. Ce nombre diminue de 1 à chaque tick, et s'arrête à 0 (entier).
		map.put("hitshaketime", new Valueable() {
			public Object getValue(String spriteId, Valueable... params) {
				Sprite sprOne = GameFight.getInstance().getSpriteInstance(spriteId);
				HitDefSub hitdefFrom = sprOne.getInfo().getLastHitdef();
				if (hitdefFrom == null)
					return null;
				return hitdefFrom.getPausetime().getP2_shaketime();
			}});

//		hittime : Retourne le délai avant que le joueur puisse ravoir le contrôle après le coup (entier).
		map.put("hittime", new Valueable() {
			public Object getValue(String spriteId, Valueable... params) {
				Sprite sprOne = GameFight.getInstance().getSpriteInstance(spriteId);
				HitDefSub hitdefFrom = sprOne.getInfo().getLastHitdef();
				if (hitdefFrom == null)
					return null;
				if (FightEngine.isBlockState(sprOne.getSpriteState().getCurrentState().getIntId())) {
					return hitdefFrom.getGuard().getHittime();
				} else {
					Type type = Type.getType(hitdefFrom.getAttr().getType());
					switch (type) {
					case A:
						return hitdefFrom.getAir().getHittime();
					case I:
						return hitdefFrom.getAir().getHittime();
					case S:
						return hitdefFrom.getGround().getHittime();
					case C:
						return hitdefFrom.getGround().getHittime();
					case L:
					default:
						throw new IllegalArgumentException("no attr arg1");
					}
				}
			}});
		
//		slidetime : Retourne le temps durant lequel le joueur glisse vers l'arrière (au sol) après le coup (entier).
		map.put("slidetime", new Valueable() {
			public Object getValue(String spriteId, Valueable... params) {
				Sprite sprOne = GameFight.getInstance().getSpriteInstance(spriteId);
				HitDefSub hitdefFrom = sprOne.getInfo().getLastHitdef();
				if (hitdefFrom == null)
					return null;
				
				
				if (FightEngine.isBlockState(sprOne.getSpriteState().getCurrentState().getIntId())) {
					return hitdefFrom.getGuard().getSlidetime();
				}
				return hitdefFrom.getGround().getSlidetime();
			}});
		
//		ctrltime : Retourne le délai avant que le joueur récupère le contrôle 
//		après avoir paré le coup.
		map.put("ctrltime", new Valueable() {
			public Object getValue(String spriteId, Valueable... params) {
				Sprite sprOne = GameFight.getInstance().getSpriteInstance(spriteId);
				HitDefSub hitdefFrom = sprOne.getInfo().getLastHitdef();
				if (hitdefFrom == null)
					return null;
				if (FightEngine.isBlockState(sprOne.getSpriteState().getCurrentState().getIntId())) {
					Type type = Type.getType(hitdefFrom.getAttr().getType());
					switch (type) {
					case A:
						return hitdefFrom.getAirguard().getCtrltime();
					case I:
						return hitdefFrom.getAirguard().getCtrltime();
					case S:
						return hitdefFrom.getGuard().getCtrltime();
					case C:
						return hitdefFrom.getGuard().getCtrltime();
					case L:
					default:
						throw new IllegalArgumentException("ctrltime");
					}
					
				}
				return 0;
			}});
		
//		recovertime : Retourne le délai avant que le joueur se relève d'un state liedown. 
		map.put("recovertime", new Valueable() {
			public Object getValue(String spriteId, Valueable... params) {
				Sprite sprOne = GameFight.getInstance().getSpriteInstance(spriteId);
				HitDefSub hitdefFrom = sprOne.getInfo().getLastHitdef();
				if (hitdefFrom == null)
					return null;
				if (sprOne.getInfo().getType() == Type.L) {
					hitdefFrom.getFall().getRecovertime();
				}
				return 0;
			}});
		
//		Ce nombre va vers 0 à chaque tick, et diminuera plus vite si des boutons sont pressés (entier).
		map.put("ctrltime", new Valueable() {
			public Object getValue(String spriteId, Valueable... params) {
				Sprite sprOne = GameFight.getInstance().getSpriteInstance(spriteId);
				HitDefSub hitdefFrom = sprOne.getInfo().getLastHitdef();
				if (hitdefFrom == null)
					return null;
				if (FightEngine.isBlockState(sprOne.getSpriteState().getCurrentState().getIntId())) {
					Type type = Type.getType(hitdefFrom.getAttr().getType());
					switch (type) {
					case A:
						return hitdefFrom.getAirguard().getCtrltime();
					case I:
						return hitdefFrom.getAirguard().getCtrltime();
					case S:
						return hitdefFrom.getGuard().getCtrltime();
					case C:
						return hitdefFrom.getGuard().getCtrltime();
					case L:
					default:
						throw new IllegalArgumentException("ctrltime");
					}
					
				}
				throw new IllegalArgumentException("ctrltime");
			}});
		
//		xoff : valeur x du snap au moment du coup (déconseillé).
		
//		yoff : valeur y du snap au moment du coup (déconseillé).
		
//		zoff : Relève la position z au moment du coup (déconseillé).
		
//		xvel : Fixe la vitesse en x donnée par un coup (flottant).
		map.put("xvel", new Valueable() {
			public Object getValue(String spriteId, Valueable... params) {
				Sprite sprOne = GameFight.getInstance().getSpriteInstance(spriteId);
				HitDefSub hitdefFrom = sprOne.getInfo().getLastHitdef();
				if (hitdefFrom == null)
					return null;
				
				
				if (sprOne.getInfo().getType() == Type.A) {
					return hitdefFrom.getAir().getVelocity().getX();
					
				} else {
					return hitdefFrom.getGround().getVelocity().getX();
				}
			}});
		
//		yvel : Fixe la vitesse en y donnée par un coup (flottant).
		map.put("yvel", new Valueable() {
			public Object getValue(String spriteId, Valueable... params) {
				Sprite sprOne = GameFight.getInstance().getSpriteInstance(spriteId);
				HitDefSub hitdefFrom = sprOne.getInfo().getLastHitdef();
				if (hitdefFrom == null)
					return null;
				if (hitdefFrom.getSprHittedTypeWhenHit() == Type.A || hitdefFrom.getSprHittedTypeWhenHit() == Type.I) {
					return hitdefFrom.getAir().getVelocity().getY();
				} else {
					return hitdefFrom.getGround().getVelocity().getY();
				}
			}});
		
//		yaccel : accélération y donnée par le coup (flottant).
		map.put("yaccel", new Valueable() {
			public Object getValue(String spriteId, Valueable... params) {
				Sprite sprOne = GameFight.getInstance().getSpriteInstance(spriteId);
				HitDefSub hitdefFrom = sprOne.getInfo().getLastHitdef();
				if (hitdefFrom == null)
					return sprOne.getInfo().getMovement().getYaccel();

				boolean hitover = ((Integer) new Hitover().getValue(spriteId, params)).intValue() == 1;
				if (hitover)
					return sprOne.getInfo().getMovement().getYaccel();
				return hitdefFrom.getYaccel();
			}});
		
//		chainid : chainID assignée au joueur pour le dernier coup encaissé (entier).
		map.put("chainid", new Valueable() {
			public Object getValue(String spriteId, Valueable... params) {
				Sprite sprOne = GameFight.getInstance().getSpriteInstance(spriteId);
				HitDefSub hitdefFrom = sprOne.getInfo().getLastHitdef();
				if (hitdefFrom == null)
					return null;
				return hitdefFrom.getChainid();
			}});
//		guarded : Vrai si le dernier coup a été bloqué, faux dans les autres cas.
		map.put("guarded", new Valueable() {
			public Object getValue(String spriteId, Valueable... params) {
				Sprite sprOne = GameFight.getInstance().getSpriteInstance(spriteId);
				HitDefSub hitdefFrom = sprOne.getInfo().getLastHitdef();
				if (hitdefFrom == null)
					return null;
				return hitdefFrom.isBlocked();
			}});
		
//		fall : Vrai s'il y a chute, faux sinon (entier).
		map.put("fall", new Valueable() {
			public Object getValue(String spriteId, Valueable... params) {
				Sprite sprOne = GameFight.getInstance().getSpriteInstance(spriteId);
				
//				for (HitDefSub hitdefFrom: sprOne.getInfo().getHitdefs()) {
				HitDefSub hitdefFrom = sprOne.getInfo().getLastHitdef();
					if (hitdefFrom == null)
						return 0;
					if (hitdefFrom.getHittime() > 0) {
						if ((sprOne.getInfo().getType() == Type.I 
								|| sprOne.getInfo().getType() == Type.A) 
								&& hitdefFrom.getAir().getFall() != 0)
							return 1;
						else if (hitdefFrom.fall() != 0)
							return 1;
					}
//				}
				return 0;
					
			}});	
		
//		fall.damage : Dommages subis sur une chute (entier).
		map.put("fall.damage", new Valueable() {
			public Object getValue(String spriteId, Valueable... params) {
				Sprite sprOne = GameFight.getInstance().getSpriteInstance(spriteId);
				HitDefSub hitdefFrom = sprOne.getInfo().getLastHitdef();
				if (hitdefFrom == null)
					return null;
				return hitdefFrom.getFall().getDamage();
			}});
		
//		fall.xvel : vitesse en x après un rebond au sol (flottant).
		map.put("fall.xvel", new Valueable() {
			public Object getValue(String spriteId, Valueable... params) {
				Sprite sprOne = GameFight.getInstance().getSpriteInstance(spriteId);
				HitDefSub hitdefFrom = sprOne.getInfo().getLastHitdef();
				if (hitdefFrom == null)
					return null;
					
				return hitdefFrom.getFall().getXvelocity();
			}});	
		
//		fall.yvel : vitesse en y après un rebond au sol (flottant).
		map.put("fall.yvel", new Valueable() {
			public Object getValue(String spriteId, Valueable... params) {
				Sprite sprOne = GameFight.getInstance().getSpriteInstance(spriteId);
				HitDefSub hitdefFrom = sprOne.getInfo().getLastHitdef();
				if (hitdefFrom == null)
					return null;
					
				return hitdefFrom.getFall().getYvelocity();
			}});	
		
//		fall.recover : Vrai si le joueur peut se rétablir, faux sinon.
		map.put("fall.recover", new Valueable() {
			public Object getValue(String spriteId, Valueable... params) {
				Sprite sprOne = GameFight.getInstance().getSpriteInstance(spriteId);
				HitDefSub hitdefFrom = sprOne.getInfo().getLastHitdef();
				if (hitdefFrom == null)
					return null;
				return hitdefFrom.fall();
			}});	
		
//		fall.recovertime : Délai avant que le joueur puisse se rétablir (entier).
		map.put("fall.recovertime", new Valueable() {
			public Object getValue(String spriteId, Valueable... params) {
				Sprite sprOne = GameFight.getInstance().getSpriteInstance(spriteId);
				HitDefSub hitdefFrom = sprOne.getInfo().getLastHitdef();
				if (hitdefFrom == null)
					return null;
				return hitdefFrom.getFall().getRecovertime();
			}});	
		
//		fall.kill : Valeur du paramètre fall.kill dans le hitdef de l'attaquant (entier).
		map.put("fall.kill", new Valueable() {
			public Object getValue(String spriteId, Valueable... params) {
				Sprite sprOne = GameFight.getInstance().getSpriteInstance(spriteId);
				HitDefSub hitdefFrom = sprOne.getInfo().getLastHitdef();
				if (hitdefFrom == null)
					return null;
				return hitdefFrom.getFall().getKill();
			}});	
		
//		fall.envshake.time : Voir plus bas (entier).
		map.put("fall.envshake.time", new Valueable() {
			public Object getValue(String spriteId, Valueable... params) {
				Sprite sprOne = GameFight.getInstance().getSpriteInstance(spriteId);
				HitDefSub hitdefFrom = sprOne.getInfo().getLastHitdef();
				if (hitdefFrom == null)
					return null;
				return hitdefFrom.getFall().getEnvshake().getTime();
			}});	
		
//		fall.envshake.freq : Voir plus bas (flottant).
		map.put("fall.envshake.freq", new Valueable() {
			public Object getValue(String spriteId, Valueable... params) {
				Sprite sprOne = GameFight.getInstance().getSpriteInstance(spriteId);
				HitDefSub hitdefFrom = sprOne.getInfo().getLastHitdef();
				if (hitdefFrom == null)
					return null;
				return hitdefFrom.getFall().getEnvshake().getFreq();
			}});	
		
//		fall.envshake.ampl : Voir plus bas (entier).
		map.put("fall.envshake.ampl", new Valueable() {
			public Object getValue(String spriteId, Valueable... params) {
				Sprite sprOne = GameFight.getInstance().getSpriteInstance(spriteId);
				HitDefSub hitdefFrom = sprOne.getInfo().getLastHitdef();
				if (hitdefFrom == null)
					return null;
				return hitdefFrom.getFall().getEnvshake().getAmpl();
			}});	
		
//		fall.envshake.phase : Retourne la valeur donnée par le paramètre fall.envshake.* dans le hitdef de l'attaquant (flottant).
		map.put("fall.envshake.phase", new Valueable() {
			public Object getValue(String spriteId, Valueable... params) {
				Sprite sprOne = GameFight.getInstance().getSpriteInstance(spriteId);
				HitDefSub hitdefFrom = sprOne.getInfo().getLastHitdef();
				if (hitdefFrom == null)
					return null;
				return hitdefFrom.getFall().getEnvshake().getPhase();
			}});	

		// xoff
		
		// yoff
		
		//zoff
		
		// isbound
		
		
		return map;
	}
	
	@Override
	public String getRegex() {
		return super.getRegex();
//		StringBuilder builder = new StringBuilder();
//		builder.append("(?:" + super.getRegex() + "\\((?:");
//		int index = 0;
//		for (String s : PARAMS) {
//			builder.append("\\b" + s + "\\b");
//			if (index < PARAMS.length - 1)
//				builder.append("|");
//			index++;
//		}
//		builder.append(")\\))");
//		return builder.toString();
	}
	public static String getConstRegex() {
		StringBuilder builder = new StringBuilder();
		int index = 0;
		for (String s : CONST) {
			builder.append("(\\b" + s.replaceAll("\\.", "\\\\.") + "\\b)");
			if (index < CONST.length - 1)
				builder.append("|");
			index++;
		}
		return builder.toString();
	}
}
