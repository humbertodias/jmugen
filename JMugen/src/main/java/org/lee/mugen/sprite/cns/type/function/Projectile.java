package org.lee.mugen.sprite.cns.type.function;

import java.util.HashMap;
import java.util.Map;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.core.renderer.game.ProjectileRender;
import org.lee.mugen.core.renderer.game.SpriteShadowRender;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.entity.ProjectileSprite;
import org.lee.mugen.sprite.entity.ProjectileSub;
import org.lee.mugen.util.BeanTools;

public class Projectile extends Hitdef {
//TODO
	// -1 other
	// n 
	private static Map<Integer, Integer> projectilesHitsMap = new HashMap<Integer, Integer>();
	
	public static Integer getLastProjHitTime(Integer num) {
		if (num == null) {
			return projectilesHitsMap.get(-1);
		} else {
			return projectilesHitsMap.get(num);
		}
	}
	public static void clearProjHitTime() {
		projectilesHitsMap.clear();
	}
	public static void setHitedFor(Integer projid) {
		if (projid == null)
			projid = -1;
		projectilesHitsMap.put(projid, GameFight.getInstance().getGameState().getGameTime());
	}
	@Override
	public void fillBean(String spriteId, Object hitDef) {
		for (String paramName : getParamNames()) {
			super.fillBeanChild(spriteId, paramName, hitDef);
		}
			
	}
	@Override
	protected void fillBeanChild(String spriteId, String name, Object hitDef) {
		try {
			int nameIndex = getParamIndex(name);
			Valueable[] values = valueableParams[nameIndex];

			Object[] objectValues = new Object[values == null ? 0 : values.length];
			Object[] defaultValues = getDefaultValues(name);
			if ((objectValues == null || objectValues.length == 0) && (defaultValues != null && defaultValues.length > 0)) {
				objectValues = new Object[defaultValues.length];
			}
			for (int i = 0; i < objectValues.length; ++i) {
				if (values != null && values[i] != null)
					objectValues[i] = values[i].getValue(spriteId);
				if (objectValues[i] == null) {
					if (defaultValues[i] != null && defaultValues[i] instanceof Valueable) {
						objectValues[i] = ((Valueable)defaultValues[i]).getValue(spriteId);
					} else {
						objectValues[i] = defaultValues[i];
						
					}
				}
			}
			if (objectValues.length == 1) {
				BeanTools.setObject(hitDef, name,
						objectValues[0]);
			} else if (objectValues.length > 0) {
				BeanTools.setObject(hitDef, name,
							objectValues);
			}
		} catch (Exception e) {
			System.err.println(getClass().getName() + " error in build : " + name);
			e.printStackTrace();
		}
		
	}

	
	
	@Override
	public Object getValue(String spriteId, Valueable... param) {
		Sprite sprOne = GameFight.getInstance().getSpriteInstance(spriteId);
		
		ProjectileSub projectile = new ProjectileSub(sprOne);
		projectile.setSpriteId(spriteId);
		fillBean(spriteId, projectile);
		final ProjectileSprite projectileSprite = new ProjectileSprite(projectile);
		projectile.setSpriteHitter(projectileSprite);
		GameFight.getInstance().getFightEngine().add(projectile);
		ProjectileRender projRender = new ProjectileRender(projectile);
		GameFight.getInstance().addRender(projRender);
		GameFight.getInstance().getOtherSprites().add(projectileSprite);
		GameFight.getInstance().addRender(new SpriteShadowRender(projectileSprite, false));
		
		// This help me to see CNS For Projectile
//		CnsRender cnsRender = new CnsRender(projectileSprite) {
//			@Override
//			public boolean remove() {
//				return projectileSprite.remove();
//			}
//		};
//		cnsRender.setShowAttackCns(true);
//		StateMachine.getInstance().addRender(cnsRender);
		return null;
	}

	
	
	
	private Map<String, Object[]> buildDefaultValuesMapForProjectile() {
		Map<String, Object[]> map = new HashMap<String, Object[]>();

		return map;
	}
	

	public Projectile() {
		super("projectile", new String[] { "attr", "hitflag", "guardflag",
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
				"numhits", "guard.cornerpush.veloff","palfx.time","palfx.mul","palfx.add","attack.width",
				"id", "sprpriority"
				
				
				/// Propre a projecrile
				,"projid","projanim"
				,"projhitanim","projremanim","projcancelanim","projscale","projremove"
				,"projremovetime","velocity","remvelocity","accel","velmul"
				,"projhits","projmisstime","projpriority","projsprpriority"
				,"projedgebound","projstagebound","projheightbound","offset"
				,"postype","projshadow","supermovetime","pausemovetime", "removeongethit"
				// check For id
		});
		_DEFAULT_VALUES_MAP.putAll(buildDefaultValuesMapForProjectile());
	}

	///////////////////////////////////////////////////////////////////
	// Projectile
	

	
}
