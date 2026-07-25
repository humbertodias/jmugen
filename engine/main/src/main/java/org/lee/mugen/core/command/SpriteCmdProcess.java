package org.lee.mugen.core.command;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.input.CmdProcDispatcher;
import org.lee.mugen.input.MugenCommands;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.character.SpriteCns;

/**
 * This class process command recognition
 * TODO : remove the interface KeyAdapter beause it's not genereic
 * In the java caller if the caller use AWT events, it has to use wrapper
 * @author Dr Wong
 *
 */
public class SpriteCmdProcess implements org.lee.mugen.input.ISpriteCmdProcess {

	private List<String> spriteIds = new ArrayList<String>();
	private CmdProcDispatcher dispatcher;
	private List<MugenCommands> cmds;
	private GameFight stateMachine = null;
	
	/* (non-Javadoc)
	 * @see org.lee.mugen.core.command.ISpriteCmdProcess#addSprite(java.lang.String)
	 */
	public void addSprite(String spriteId) {
		spriteIds.add(spriteId);
	}
	/* (non-Javadoc)
	 * @see org.lee.mugen.core.command.ISpriteCmdProcess#remove(java.lang.String)
	 */
	public void remove(String spriteId) {
		spriteIds.remove(spriteId);
	}
	public SpriteCmdProcess(CmdProcDispatcher dispatcher) {
		this.dispatcher = dispatcher;
		stateMachine = GameFight.getInstance();
	}
	
	
	/* (non-Javadoc)
	 * @see org.lee.mugen.core.command.ISpriteCmdProcess#process()
	 */
	public void process() {
		for (String spriteId: spriteIds) {
			Sprite sprite = GameFight.getInstance().getSpriteInstance(spriteId);
			
			boolean find = false;
			SpriteCns sprInfo = sprite.getInfo();
			List<MugenCommands> cmds = sprite.getCmds();

			for (MugenCommands mc : cmds) {
				if (dispatcher == null)
					continue;
				if (mc.find(dispatcher, stateMachine.getGameState().getGameTime(), sprInfo.isFlip())) {
					sprInfo.addCommand(mc.getCommandName(), (int) mc.getBufferTime());
					find = true;
				}
			}
		}

	}
	public void process(String spriteId) {
		Sprite sprite = GameFight.getInstance().getSpriteInstance(spriteId);
		
		boolean find = false;
		SpriteCns sprInfo = sprite.getInfo();
		List<MugenCommands> cmds = sprite.getCmds();

		for (MugenCommands mc : cmds) {
			if (dispatcher == null)
				continue;
			if (mc.find(dispatcher, stateMachine.getGameState().getGameTime(), sprInfo.isFlip())) {
				sprInfo.addCommand(mc.getCommandName(), (int) mc.getBufferTime());
				find = true;
			}
		}

	}

	public void keyPressed(KeyEvent e) {
		for (String sid: spriteIds) {
			Sprite sprite = GameFight.getInstance().getSpriteInstance(sid);
			dispatcher.press(e.getKeyCode(), stateMachine.getGameState().getGameTime(), sprite.isFlip());
		}
		
	}


	public void keyReleased(KeyEvent e) {
		for (String sid: spriteIds) {
			Sprite sprite = GameFight.getInstance().getSpriteInstance(sid);
			dispatcher.release(e.getKeyCode(), stateMachine.getGameState().getGameTime(), sprite.isFlip());
		}
		
	}

	/* (non-Javadoc)
	 * @see org.lee.mugen.core.command.ISpriteCmdProcess#keyPressed(int)
	 */
	public void keyPressed(int keycode) {
		for (String sid: spriteIds) {

			Sprite sprite = GameFight.getInstance().getSpriteInstance(sid);
			if (dispatcher == null)
				continue;
				
			dispatcher.press(keycode, stateMachine.getGameState().getGameTime(), sprite.isFlip());
		}
		
	}


	/* (non-Javadoc)
	 * @see org.lee.mugen.core.command.ISpriteCmdProcess#keyReleased(int)
	 */
	public void keyReleased(int keycode) {
		for (String sid: spriteIds) {
			Sprite sprite = GameFight.getInstance().getSpriteInstance(sid);
			if (dispatcher == null)
				continue;
			dispatcher.release(keycode, stateMachine.getGameState().getGameTime(), sprite.isFlip());
		}
		
	}


	/* (non-Javadoc)
	 * @see org.lee.mugen.core.command.ISpriteCmdProcess#getDispatcher()
	 */
	public CmdProcDispatcher getDispatcher() {
		return dispatcher;
	}
	public int[] getKeys() {
		return dispatcher.getKeys();
	}

}
