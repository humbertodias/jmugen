package org.lee.mugen.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.lee.mugen.core.renderer.game.AfterimageRender;
import org.lee.mugen.core.renderer.game.EnvcolorRender;
import org.lee.mugen.core.renderer.game.ProjectileRender;
import org.lee.mugen.core.renderer.game.SpriteRender;
import org.lee.mugen.core.renderer.game.SpriteShadowRender;
import org.lee.mugen.core.renderer.game.SuperpauseRender;
import org.lee.mugen.core.renderer.game.SparkRender.SparkRenderFactory;
import org.lee.mugen.renderer.PalFxSub;
import org.lee.mugen.renderer.Renderable;
import org.lee.mugen.sprite.base.AbstractSprite;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.cns.type.function.Assertspecial;
import org.lee.mugen.sprite.entity.AssertSpecialEval;
import org.lee.mugen.sprite.entity.EnvcolorSub;
import org.lee.mugen.sprite.entity.PauseSub;
import org.lee.mugen.sprite.entity.SuperpauseSub;


/**
 * GameGlobalEvents class is designe to hold global events, it's to says gobal to the game and not for a sprite
 * like superpause, pause, some assert special, ...
 * @author Dr Wong
 *
 */
public class GameGlobalEvents {

	private PalFxSub bGPalFX = new PalFxSub();
	private SuperpauseSub superpauseToActivate;
	private SuperpauseSub superpause;
	private PauseSub pause = new PauseSub();
	private List<AssertSpecialEval> assertSpecials = new ArrayList<AssertSpecialEval>();
	private EnvcolorSub envcolor = new EnvcolorSub();
	private EnvcolorRender _envcolorRender = new EnvcolorRender();
	
	private boolean systemPause = false;
	private boolean forceUpdate = false;
	
	public boolean canUpdate() {
		return !systemPause || forceUpdate;
	}
	
	public boolean isSystemPause() {
		return systemPause;
	}
	public void setSystemPause(boolean systemPause) {
		this.systemPause = systemPause;
	}
	public boolean isForceUpdate() {
		return forceUpdate;
	}
	public void setForceUpdate(boolean forceUpdate) {
		this.forceUpdate = forceUpdate;
	}
	public PalFxSub getBgpalfx() {
		return bGPalFX;
	}
	public SuperpauseSub getSuperpause() {
		return superpause;
	}
	public PauseSub getPause() {
		return pause;
	}
	public void setPause(PauseSub pause) {
		this.pause = pause;
	}
	private Set<Class> renderNoDisplayForEnvcolor = new LinkedHashSet<Class>();
	{
		renderNoDisplayForEnvcolor.add(SpriteRender.class);
		renderNoDisplayForEnvcolor.add(AfterimageRender.class);
		renderNoDisplayForEnvcolor.add(ProjectileRender.class);
		renderNoDisplayForEnvcolor.add(SpriteShadowRender.class);
	}
	
	
	public EnvcolorSub getEnvcolor() {
		return envcolor;
	}
	
	public boolean isSuperPauseOrPause() {
		return isSuperPause() || isPause();
	}
	public boolean isSuperPauseMoveTimeOrPauseMoveTime(Sprite s) {
		return isSuperpauseMoveTime(s) || isPauseMoveTime(s);
	}
	public boolean isSuperPause() {
		return (superpause != null && superpause.getTime() > 0);
	}
	public boolean isPause() {
		return (pause != null && pause.getTime() > 0);
	}
	public boolean isSuperpauseMoveTime(Sprite s) {
		return superpause != null && superpause.isSuperpauseMoveTime()
		&& superpause.getSprite().equals(s);
	}
	public boolean isPauseMoveTime(Sprite s) {
		return pause != null && pause.isPauseMoveTime() && pause.getSpriteFrom().equals(s);
	}	
	
	public boolean canGameProcessWithPause(Sprite s) {
		s = GameFight.getInstance().getRoot(s);
		
		if (isSuperPause() && isSuperpauseMoveTime(s))
			return true;
		if (isPause() && isPauseMoveTime(s))
			return true;
		if (!isSuperPause() && !isPause())
			return true;
		return false;
	}
	
	public void setSuperPause(SuperpauseSub superpauseSub) {
		superpauseToActivate = superpauseSub;
		AbstractSprite sprToRender = SparkRenderFactory.getInstanceOfSparkSuperpause(superpauseSub);
		GameFight.getInstance().addRender(new SuperpauseRender(sprToRender));
		GameFight.getInstance().getOtherSprites().add(sprToRender);
	}

	public void addAssertSpecial(AssertSpecialEval assertSpecial) {
		assertSpecials.add(assertSpecial);
	}
	
	public boolean isAssertSpecial(Assertspecial.Flag flag) {
		return isAssertSpecial(null, flag);
	}
	
	public boolean isAssertSpecial(String spriteId, Assertspecial.Flag flag) {
		for (AssertSpecialEval as: assertSpecials) {
			if ((as.getSpriteId().equals(spriteId) || spriteId == null) 
					&& as.getAssertspecial().isValid()) {
				if (as.getAssertspecial().getFlag() == flag
						||
						as.getAssertspecial().getFlag2() == flag
						||
						as.getAssertspecial().getFlag3() == flag
						) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void enter() {
		envcolor.decreaseTime();
		for (Iterator<AssertSpecialEval> iter = assertSpecials.iterator(); iter.hasNext();) {
			AssertSpecialEval ase = iter.next();
			if (!ase.getAssertspecial().isValid()) {
				iter.remove();
			}
			ase.getAssertspecial().decrease();
		}
	}
	
	public void leave() {
		if (isSuperPause()) {
			if (superpause != null)
				superpause.decreaseTime();
		} else if (isPause()) {
			if (pause != null)
				pause.decreasePause();
		} else {
			getBgpalfx().decreaseTime();
		}
		if (superpauseToActivate != null) {
			superpause = superpauseToActivate;
			superpauseToActivate = null;
		}
	}
	public boolean canDisplayThisRender(Class<? extends Renderable> clazz) {
		return !renderNoDisplayForEnvcolor.contains(clazz);
	}
	public Renderable getEnvcolorRender() {
		return _envcolorRender;
	}
	public boolean canGameProcessWithSuperPause(Sprite s) {
		s = GameFight.getInstance().getRoot(s);
		if (isSuperPause() && isSuperpauseMoveTime(s))
			return true;
		if (!isSuperPause())
			return true;
		return false;
	}

	public void pauseUnpause() {
		systemPause = !systemPause;
		
	}
}
