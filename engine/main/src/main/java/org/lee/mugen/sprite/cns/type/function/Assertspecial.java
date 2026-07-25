package org.lee.mugen.sprite.cns.type.function;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.parser.type.ObjectValueable;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.sprite.character.spiteCnsSubClass.AssertSpecialSub;
import org.lee.mugen.sprite.cns.eval.function.StateCtrlFunction;
import org.lee.mugen.sprite.entity.AssertSpecialEval;

/**
 * TODO : not all
 * @author Dr Wong
 *
 */
public class Assertspecial extends StateCtrlFunction {

	public static enum Flag {
		intro("intro"), invisible("invisible"), roundnotover("roundnotover"),
		nobardisplay("nobardisplay"), nobg("nobg"), nofg("nofg"), nostandguard("nostandguard"),
		nocrouchguard("nocrouchguard"), noairguard("noairguard"),
		noautoturn("noautoturn"), nojugglecheck("nojugglecheck"), 
		nokosnd("nokosnd"), nokoslow("nokoslow"), noshadow("noshadow"),
		globalnoshadow("globalnoshadow"), nomusic("nomusic"), 
		nowalk("nowalk"), timerfreeze("timerfreeze"), unguardable("unguardable"), none("none");
	
		private String desc;
		Flag(String desc) {
			this.desc = desc;
		}
		public String getDesc() {
			return desc;
		}
		
	}
	
	public static String getFlagValuesRegex() {
		StringBuilder builder = new StringBuilder();
		Flag[] flags = Flag.values();
		for (int i = 0; i < flags.length; ++i) {
			builder.append("([\\W]*" + flags[i].getDesc() + "[\\W]*)" + (i < flags.length - 1? "|": ""));
		}
		return builder.toString();
	}
	
	/*
  The flag name can be one of the following:
    - intro
      Tells MUGEN that the character is currently performing his intro
      pose. Must be asserted on every tick while the intro pose is 
      being performed.
    - invisible
      Turns the character invisible while asserted. Does not affect 
      display of afterimages.
    - roundnotover
      Tells MUGEN that the character is currently performing his win
      pose. Must be asserted on every tick while the win pose is being
      performed.
    - nobardisplay
      Disables display of life, super bars, etc. while asserted.
    - noBG
      Turns off the background. The screen is cleared to black.
    - noFG
      Disables display of layer 1 of the stage (the foreground).
    - nostandguard
      While asserted, disables standing guard for the character.
    - nocrouchguard
      While asserted, disables crouching guard for the character.
    - noairguard
      While asserted, disables air guard for the character.
    - noautoturn
      While asserted, keeps the character from automatically turning
      to face the opponent.
    - nojugglecheck
      While asserted, disables juggle checking. P2 can be juggled 
      regardless of juggle points.
    - nokosnd
      Suppresses playback of sound 11, 0 (the KO sound) for players
      who are knocked out. For players whose KO sound echoes, nokosnd
      must be asserted for 50 or more ticks after the player is KOed
      in order to suppress all echoes.
    - nokoslow
      While asserted, keeps MUGEN from showing the end of the round in
      slow motion.
    - noshadow
      While asserted, disables display of this player's shadows.
    - globalnoshadow
      Disables display of all player, helper and explod shadows.
    - nomusic
      While asserted, pauses playback of background music.
    - nowalk
      While asserted, the player cannot enter his walk states, even if
      he has control. Use to prevent run states from canceling into
      walking.
    - timerfreeze
      While asserted, keeps the round timer from counting down. Useful 
      to keep the round from timing over in the middle of a splash 
      screen.
    - unguardable
      While asserted, all the asserting player's HitDefs become 
      unblockable, i.e., their guardflags are ignored.

	 */
	
	public Assertspecial() {
		super("assertspecial", new String[] {"flag", "flag2", "flag3"});
	}
	
	@Override
	public Object getValue(String spriteId, Valueable... p) {
	
		AssertSpecialSub assertSpecial = new AssertSpecialSub();
		
		int flagIndex = getParamIndex("flag");
		if (valueableParams[flagIndex] != null) {
			Valueable flag = valueableParams[flagIndex][0];
			if (flag != null) {
				Flag eflag = (Flag) flag.getValue(spriteId);
				assertSpecial.setFlag(eflag);
			}
		}		
		int flag2Index = getParamIndex("flag2");
		if (valueableParams[flag2Index] != null) {
			Valueable flag2 = valueableParams[flag2Index][0];
			if (flag2 != null) {
				Flag eflag2 = (Flag) flag2.getValue(spriteId);
				assertSpecial.setFlag2(eflag2);
			}
		}

		int flag3Index = getParamIndex("flag3");
		if (valueableParams[flag3Index] != null) {
			Valueable flag3 = valueableParams[flag3Index][0];
			if (flag3 != null) {
				Flag eflag3 = (Flag) flag3.getValue(spriteId);
				assertSpecial.setFlag3(eflag3);
			}
			
		}
		GameFight.getInstance().getGlobalEvents().addAssertSpecial(new AssertSpecialEval(assertSpecial, spriteId));

		return null;
	}

	@Override
	public Valueable[] parse(String name, String value) {
		final Flag flag = Flag.valueOf(value);
		return new Valueable[] {new ObjectValueable(flag)};
	}
	

}
