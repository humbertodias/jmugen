package org.lee.mugen.sprite.cns.type.function;

import java.util.concurrent.atomic.AtomicBoolean;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.core.sound.SoundSystem;
import org.lee.mugen.parser.type.Valueable;
import org.lee.mugen.snd.GroupSnd;
import org.lee.mugen.snd.Snd;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.cns.eval.function.StateCtrlFunction;
import org.lee.mugen.sprite.parser.ExpressionFactory;
import org.lee.mugen.sprite.parser.Parser;

public class Playsnd extends StateCtrlFunction {
	
public Playsnd() {
		super("playsnd", new String[] {
				"value","volume"
				,"channel","lowpriority"
				,"freqmul","loop"
				,"pan","abspan", "ignorehitpause"
		});
	}

	AtomicBoolean isPlaying = new AtomicBoolean(false);
	@Override
	public Object getValue(String spriteId, Valueable... params) {
		if (isPlaying.get())
			return null;
		isPlaying.set(true);
		String errorMsg = null;
		try {

			Sprite sprite = GameFight.getInstance().getRoot(spriteId);
			spriteId = sprite.getSpriteId();
//			spriteId = sprite.getSpriteId();
			
			int valueIndex = getParamIndex("value");
			Valueable[] value = valueableParams[valueIndex];
//			{
//			int grp = Parser.getIntValue(value[0].getValue(spriteId));
//			
//			int id = Parser.getIntValue(value[1].getValue(spriteId));
//			SoundSystem.Sfx.playSnd(sprite.getSpriteSnd().getGroup(grp).getSound(id), isPlaying);
//			}
//			if (true)
//				return null;
			String strGrp = value[0].getValue(spriteId).toString();
			if (strGrp.startsWith("f")) {
				Snd commonSnd = GameFight.getInstance().getFightdef().getFiles().getCommon().getSnd();
				
				strGrp = strGrp.substring(1);
				int grp = Integer.parseInt(strGrp);
				int id = Parser.getIntValue(value[1].getValue(spriteId));
				GroupSnd grpSnd = commonSnd.getGroup(grp);
				if (grpSnd == null) {
					errorMsg = "This sound " + grp + ", " + id + " for FigthFx do not exist.";
					return null;
					//					throw new NullPointerException();
				}
				byte[] dataSnd = grpSnd.getSound(id);//, isPlaying);
				if (dataSnd == null) {
					errorMsg = "This sound " + grp + ", " + id + " for FigthFx do not exist.";
					return null;

//					throw new NullPointerException();
				}
				SoundSystem.Sfx.playSnd(dataSnd, isPlaying);

			} else {
				int grp = 0;
				if (strGrp.startsWith("s")) {
					grp = Integer.parseInt(strGrp.substring(1));
				} else {
					grp = Parser.getIntValue(value[0].getValue(spriteId));
						
				}
				int id = Parser.getIntValue(value[1].getValue(spriteId));
				GroupSnd grpSnd = sprite.getSpriteSnd().getGroup(grp);
				if (grpSnd == null) {
					errorMsg = "This sound " + grp + ", " + id + " for sprite " + spriteId + " do not exist.";
					return null;

//					throw new NullPointerException();
				}
				byte[] dataSnd = grpSnd.getSound(id);//, isPlaying);
				if (dataSnd == null) {
					errorMsg = "This sound " + grp + ", " + id + " for sprite " + spriteId + " do not exist.";
					return null;

//					throw new NullPointerException();
				}
				SoundSystem.Sfx.playSnd(dataSnd, isPlaying);
			}
			
			
		} catch (Exception e) {
//			e.printStackTrace();
			System.err.println(errorMsg);
			isPlaying.set(false);
		}
		return null;
		
	}
	/*
------------------------------------------------------------
PlaySnd
------------------------------------------------------------
 
Plays back a sound
 
Required parameters:
  value = group_no, sound_no
    group_no and sound_no correspond to the identifying pair
    that you assigned each sound in the player's snd file.
    To play back a sound from "common.snd", precede group_no
    with an "F".
*/
	public static Valueable[] parseForValue(String name, String value) {
		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}
/*
Optional parameters:
  volume = volume_level
    volume_level (int) is 0 for normal volume, positive for
    louder, and negative for softer.
*/
	public static Valueable[] parseForVolume(String name, String value) {
		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}
/*
  channel = channel_no
    channel_no (int) specifies which of the player's sound channels
    the sound should play on. Only one voice may play on a particular
    channel at a time. For example, if you play a sound on channel 2,
    then play any sound on the same channel before the first sound is
    done, then by default the first sound is stopped as the second one
    plays. 0 is a special channel reserved for player voices. Channel
    0 voices are stopped when the player is hit. It's recommended you
    play your character's voice sounds on channel 0.
    If omitted, channel_no defaults to -1, meaning the sound will play
    on any free channel.
 */
	public static Valueable[] parseForChannel(String name, String value) {
		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}
/*
  lowpriority = pr (int)
    This is only valid if the channel is not -1. If pr is nonzero,
    then a sound currently playing on this sound's channel (from a
    previous PlaySnd call) cannot be interrupted by this sound.
 */
	public static Valueable[] parseForLowpriority(String name, String value) {
		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}
/*
  freqmul = f (float)
    The sound frequency will be multiplied by f. For example. f = 1.1
    will result in a higher-pitched sound. Defaults to 1.0 (no change
    in frequency).
 */
	public static Valueable[] parseForFreqmul(String name, String value) {
		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}
/*
  loop = loop_flag (int)
    Set loop_flag to a nonzero value to have the sound sample loop
    over and over. Defaults to 0.
 */
	public static Valueable[] parseForLoop(String name, String value) {
		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}
/*
    (mutually exclusive with abspan)
    This is the positional offset of the sound, measured in pixels.
    If p > 0, then the sound is offset to the front of the player.
    If p < 0, then sound is offset to the back.
    Defaults to 0.
 */
	public static Valueable[] parseForPan(String name, String value) {
		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}
/*
  abspan = p (int)
    (mutually exclusive with pan)
    Like pan, except the sound is panned from the center of the
    screen, not from the player's position.
 
*/
	public static Valueable[] parseForAbspan(String name, String value) {
		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}
/*
 * ignorehitpause
 */
	public static Valueable[] parseForIgnorehitpause(String name, String value) {
		String[] tokens = ExpressionFactory.expression2Tokens(value);
		Valueable[] vals = ExpressionFactory.evalExpression(tokens);
		return vals;
	}
}
