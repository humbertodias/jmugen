package org.lee.mugen.fight;

import java.util.List;
import org.lee.mugen.input.CmdProcDispatcher;
import org.lee.mugen.input.ISpriteCmdProcess;
import org.lee.mugen.input.MugenCommands;

/** GWT: feeds {@link CmdProcDispatcher} and matches {@code *.cmd} commands for one fighter. */
public final class GwtFightCmdProcess implements ISpriteCmdProcess {

    private final CmdProcDispatcher dispatcher;
    private final GwtFightPlayer player;
    private long gameTime;

    public GwtFightCmdProcess(CmdProcDispatcher dispatcher, GwtFightPlayer player) {
        this.dispatcher = dispatcher;
        this.player = player;
    }

    public void setGameTime(long gameTime) {
        this.gameTime = gameTime;
    }

    @Override
    public void addSprite(String spriteId) {}

    @Override
    public void remove(String spriteId) {}

    @Override
    public void process() {
        process(player.cmdId);
    }

    @Override
    public void process(String spriteId) {
        if (dispatcher == null || player == null) {
            return;
        }
        List<MugenCommands> cmds = player.getCommands();
        if (cmds == null) {
            return;
        }
        boolean flip = player.facing == -1;
        for (MugenCommands mc : cmds) {
            if (mc.find(dispatcher, gameTime, flip)) {
                player.onCommand(mc.getCommandName());
            }
        }
    }

    @Override
    public void keyPressed(int keycode) {
        if (dispatcher != null && player != null) {
            dispatcher.press(keycode, gameTime, player.facing == -1);
        }
    }

    @Override
    public void keyReleased(int keycode) {
        if (dispatcher != null && player != null) {
            dispatcher.release(keycode, gameTime, player.facing == -1);
        }
    }

    @Override
    public int[] getKeys() {
        return dispatcher != null ? dispatcher.getKeys() : new int[0];
    }
}
