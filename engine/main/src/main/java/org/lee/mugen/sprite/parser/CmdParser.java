package org.lee.mugen.sprite.parser;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;
import org.lee.mugen.core.JMugenConstant;
import org.lee.mugen.input.Key;
import org.lee.mugen.input.MugenCommands;
import org.lee.mugen.input.MugenSingleCmd;
import org.lee.mugen.input.MugenSingleCmd.CommandType;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.parser.Parser.GroupText;

public class CmdParser {
	/*
	 * ; name = some_name ; command = the_command ; time = time (optional) ;
	 * buffer.time = time (optional)
	 */

	
	public static void parse(InputStream in, Sprite sprite) throws Exception {
		boolean caseSensitive = true;
		
		List<InputStream> inList = new ArrayList<InputStream>();
		inList.add(in);
		inList.add(new ByteArrayInputStream("\n".getBytes()));
		inList.add(new FileInputStream(JMugenConstant.RESOURCE + "data/common.cmd"));
		
		Enumeration<InputStream> e = Collections.enumeration(inList);
		
		SequenceInputStream sis = new SequenceInputStream(e);
		Reader r = new InputStreamReader(sis, "utf-8");
		
		List<GroupText> grps = Parser.getGroupTextMap(r, caseSensitive);
		
		CnsParse.buildSpriteInfo(grps, sprite, sprite.getInfo(), sprite.getSpriteState());
//		
//		StateDef last = null;
//		for (GroupText grp : grps) {
//			if ("command".equalsIgnoreCase(grp.getSection())) {
//				MugenCommands mugenCommands = interpretCmd(grp);
//				sprite.getCmds().add(mugenCommands);
//			} else if (Pattern.matches(CnsParse._STATE_DEF_TITLE_REGEX, grp.getSectionRaw())) {
//				Matcher m = Pattern.compile(CnsParse._STATE_DEF_TITLE_REGEX).matcher(grp.getSectionRaw());
//				m.find();
//				String stateDefId = m.group(1);
//
//				last = CnsParse.parseStateDef(stateDefId, grp, "");
//				sprite.getSpriteState().addStateDef(last);
//			} else if (Pattern.matches(CnsParse._STATE_CTRL_TITLE_REGEX, grp.getSectionRaw())) {
//				Matcher m = Pattern.compile(CnsParse._STATE_CTRL_TITLE_REGEX).matcher(grp.getSectionRaw());
//				m.find();
//				String stateDefId = last.getId();//;m.group(1);
//				String stateCtrlId = m.group(1);
//				SpriteDebugger.addStatedef(sprite.getSpriteId(), stateDefId, grp.getSectionRaw() + "\n"+ grp.getText().toString());
//				CnsParse.parseStateCtrl(last, last.getId(), stateCtrlId, grp);
//			}
//		}

	}




	public static MugenCommands interpretCmd(GroupText grp) {
		if (!"command".equals(grp.getSection())) {
			throw new IllegalArgumentException("this section : "
					+ grp.getSection() + " is not [command] section");
		}

		MugenCommands commands = null;
		String cmds = grp.getKeyValues().get("command");
		String time = grp.getKeyValues().get("time");
		String bufferTime = grp.getKeyValues().get("buffer.time");
		String name = grp.getKeyValues().get("name");

		int iTime = time == null ? 15 : Integer.parseInt(time);
		int iBufferTime = bufferTime == null ? 1 : Integer.parseInt(bufferTime);

		commands = new MugenCommands(getMugenSingleCmds(cmds), name, iTime,
				iBufferTime);
		return commands;
	}

	private static MugenSingleCmd[] getMugenSingleCmds(String cmds) {
		List<MugenSingleCmd> mugenSingleCmds = new ArrayList<MugenSingleCmd>();
		String[] singleCmds = cmds.split(",");

		MugenSingleCmd previous = null;
		for (String scm : singleCmds) {
			boolean repeat = false;
			final MugenSingleCmd decodeScm = getMugenSingleCmd(scm);

			if (null != previous) {
				if ((previous.getTypes() == CommandType.PRESS.bit)
						&& decodeScm.getTypes() == CommandType.PRESS.bit
						&& previous.getKeys() == decodeScm.getKeys()) {
					for (Key k : Key.values()) {
						if (k.bit == previous.getKeys()
								&& k.bit == decodeScm.getKeys()) {
							// F, >~F, >F
							repeat = true;
							MugenSingleCmd mscAdd = new MugenSingleCmd(
									new Key[] { k }, new CommandType[] {
											CommandType.NO_OTHER_KEY_BEFORE,
											CommandType.RELEASED });
							
					//		mscAdd.setTime(decodeScm.getTime());
							mugenSingleCmds.add(mscAdd);

							mscAdd = new MugenSingleCmd(new Key[] { k },
									new CommandType[] {
											CommandType.NO_OTHER_KEY_BEFORE,
											CommandType.PRESS });

						//	mscAdd.setTime(decodeScm.getTime());
							mugenSingleCmds.add(mscAdd);
							break;
						}
					}

				}
			}
			if (!repeat)
				mugenSingleCmds.add(decodeScm);
			previous = decodeScm;
		}

		return mugenSingleCmds.toArray(new MugenSingleCmd[mugenSingleCmds
				.size()]);
	}

	// - command
	// list of buttons or directions, separated by commas. Each of these
	// buttons or directions is referred to as a "symbol".
	// Directions and buttons can be preceded by special characters:
	// slash (/) - means the key must be held down
	// egs. command = /D //hold the down direction
	// command = /DB, a //hold down-back while you press a
	// tilde (~) - to detect key releases
	// egs. command = ~a //release the a button
	// command = ~D, F, a //release down, press fwd, then a
	// If you want to detect "charge moves", you can specify
	// the time the key must be held down for (in game-ticks)
	// egs. command = ~30a //hold a for at least 30 ticks, then release
	// dollar ($) - Direction-only: detect as 4-way
	// egs. command = $D //will detect if D, DB or DF is held
	// command = $B //will detect if B, DB or UB is held
	// plus (+) - Buttons only: simultaneous press
	// egs. command = a+b //press a and b at the same time
	// command = x+y+z //press x, y and z at the same time
	// greater-than (>) - means there must be no other keys pressed or released
	// between the previous and the current symbol.
	// egs. command = a, >~a //press a and release it without having hit
	// //or released any other keys in between
	// You can combine the symbols:
	// eg. command = ~30$D, a+b //hold D, DB or DF for 30 ticks, release,
	// //then press a and b together
	//
	// Note: Successive direction symbols are always expanded in a manner
	// similar
	// to this example:
	// command = F, F
	// is expanded when MUGEN reads it, to become equivalent to:
	// command = F, >~F, >F
	//
	// It is recommended that for most "motion" commads, eg. quarter-circle-fwd,
	// you start off with a "release direction". This makes the command easier
	// to do.

	private static MugenSingleCmd getMugenSingleCmd(String scm) {
		CommandType[] cmdTypes = MugenSingleCmd.CommandType.values();

		boolean isPress = true;
		int timeIfReleased = 1;
		List<CommandType> modifiers = new ArrayList<CommandType>();
		for (CommandType cmdType : cmdTypes) {
			if (cmdType.desc.length() > 0
					&& scm.indexOf(CommandType.NO_OTHER_KEY_BEFORE.desc) != -1
					&& CommandType.NO_OTHER_KEY_BEFORE == cmdType) {
				modifiers.add(cmdType);
			} else if (cmdType.desc.length() > 0
					&& scm.indexOf(CommandType.DIRECTION.desc) != -1
					&& CommandType.DIRECTION == cmdType) {

				modifiers.add(cmdType);
			
			} else if (cmdType.desc.length() > 0
					&& scm.indexOf(cmdType.desc) != -1) {
				isPress = false;
				modifiers.add(cmdType);
				
//					timeIfReleased
				Matcher m = Pattern.compile("([0-9].)").matcher(scm);
				if (m.find()) {
					String time = m.group(1);
					timeIfReleased = Integer.parseInt(time);
				}
			}
		}
		Key[] keysDDir = new Key[] { Key.DB, Key.DF, Key.UF, Key.UB };
		Key selectedKey = null;
		for (Key dd : keysDDir) {
			if (scm.indexOf(dd.toString()) != -1)
				selectedKey = dd;
		}

		List<Key> keys = new ArrayList<Key>();
		if (selectedKey == null) {
			for (Key k : Key.values()) {
				if (scm.indexOf(k.toString()) != -1)
					keys.add(k);
			}
		} else {
			keys.add(selectedKey);
		}
		if (isPress) {
			modifiers.add(CommandType.PRESS);
		}
		MugenSingleCmd cmd = new MugenSingleCmd(keys.toArray(new Key[keys
				.size()]), modifiers.toArray(new CommandType[modifiers.size()]));
		cmd.setTime(timeIfReleased); // all is One 
		return cmd;
	}
}
