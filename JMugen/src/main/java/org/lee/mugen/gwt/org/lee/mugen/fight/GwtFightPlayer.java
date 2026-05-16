package org.lee.mugen.fight;

import java.util.List;
import java.util.Map;
import org.lee.mugen.input.MugenCommands;
import org.lee.mugen.renderer.DrawProperties;
import org.lee.mugen.renderer.ImageContainer;
import org.lee.mugen.renderer.MugenDrawer;

/**
 * GWT fighter: real {@code *.cmd} command detection + state numbers from cmd state -1 entries.
 * Physics/collision remain simplified until full {@code GameFight} is on the GWT classpath.
 */
public final class GwtFightPlayer {

    private static final float WALK_SPEED = 2.2f;
    private static final float GRAVITY = 0.45f;
    private static final float JUMP_VY = -7.5f;
    private static final int GROUND_Y = 192;
    private static final int STAGE_LEFT = -120;
    private static final int STAGE_RIGHT = 120;

    private static final int ACT_STAND = 0;
    private static final int ACT_WALK_FWD = 20;
    private static final int ACT_WALK_BACK = 21;
    private static final int ACT_JUMP = 40;

    final String cmdId;
    final int facing;
    final GwtFightFighterAnim anim;
    private final List<MugenCommands> commands;
    private final Map<String, Integer> commandToState;

    float x;
    float y;
    float vy;
    public int life;
    int currentAction = ACT_STAND;
    int stateTimer;
    boolean onGround = true;
    boolean inAttack;
    boolean attackHit;
    int groundY;
    private boolean holdForward;
    private boolean holdBack;

    public GwtFightPlayer(
            String cmdId,
            int facing,
            int startX,
            int startY,
            GwtFightFighterAnim anim,
            int life,
            GwtCmdLoader.FightCmdData cmdData) {
        this.cmdId = cmdId;
        this.facing = facing;
        this.x = startX;
        this.y = startY;
        this.groundY = startY;
        this.anim = anim;
        this.life = life;
        this.commands = cmdData != null ? cmdData.commands : null;
        this.commandToState = cmdData != null ? cmdData.commandToState : null;
    }

    List<MugenCommands> getCommands() {
        return commands;
    }

    public void onCommand(String commandName) {
        if (commandName == null || inAttack) {
            return;
        }
        if ("holdfwd".equals(commandName)) {
            holdForward = true;
            return;
        }
        if ("holdback".equals(commandName)) {
            holdBack = true;
            return;
        }
        if ("holdup".equals(commandName) && onGround) {
            startJump();
            return;
        }
        Integer state = commandToState != null ? commandToState.get(commandName) : null;
        if (state != null && onGround && state >= 200) {
            enterAttackState(state);
        }
    }

    public void update(GwtFightPlayer opponent) {
        boolean fwd = holdForward;
        boolean back = holdBack;
        holdForward = false;
        holdBack = false;

        anim.process();
        if (inAttack) {
            stateTimer--;
            tryHit(opponent);
            if (stateTimer <= 0) {
                inAttack = false;
                currentAction = onGround ? ACT_STAND : ACT_JUMP;
            }
            applyPhysics();
            anim.setAction(currentAction);
            return;
        }

        applyMovementFromHold(fwd, back);
        tryHit(opponent);
        applyPhysics();
        clampStage();
    }

    private void applyMovementFromHold(boolean fwd, boolean back) {
        if (!onGround || inAttack) {
            return;
        }
        if (fwd) {
            currentAction = ACT_WALK_FWD;
            x += facing * WALK_SPEED;
        } else if (back) {
            currentAction = ACT_WALK_BACK;
            x -= facing * WALK_SPEED;
        } else if (currentAction != ACT_JUMP) {
            currentAction = ACT_STAND;
        }
    }

    private void startJump() {
        vy = JUMP_VY;
        onGround = false;
        currentAction = commandToState.containsKey("holdup") ? ACT_JUMP : ACT_JUMP;
        Integer jumpState = commandToState != null ? commandToState.get("holdup") : null;
        if (jumpState != null) {
            currentAction = jumpState;
        } else {
            currentAction = ACT_JUMP;
        }
    }

    private void enterAttackState(int state) {
        inAttack = true;
        attackHit = false;
        currentAction = state;
        anim.setAction(state);
        stateTimer = 28;
    }

    private void tryHit(GwtFightPlayer opponent) {
        if (!inAttack || attackHit || opponent == null || stateTimer > 14) {
            return;
        }
        int reach = 55;
        float dist = Math.abs((160 + x) - (160 + opponent.x));
        if (dist < reach && currentAction >= 200) {
            opponent.life = Math.max(0, opponent.life - 50);
            attackHit = true;
        }
    }

    private void applyPhysics() {
        if (!onGround) {
            vy += GRAVITY;
            y += vy;
            if (y >= groundY) {
                y = groundY;
                vy = 0;
                onGround = true;
                if (!inAttack && currentAction == ACT_JUMP) {
                    currentAction = ACT_STAND;
                }
            }
        }
        if (!inAttack) {
            anim.setAction(currentAction);
        }
    }

    private void clampStage() {
        if (x < STAGE_LEFT) {
            x = STAGE_LEFT;
        }
        if (x > STAGE_RIGHT) {
            x = STAGE_RIGHT;
        }
    }

    public void draw(MugenDrawer md) {
        ImageContainer sprite = anim.getCurrentImage();
        if (sprite == null) {
            return;
        }
        boolean flipH = facing == -1;
        int drawX = 160 + (int) x - sprite.getWidth() / 2;
        int drawY = GROUND_Y - sprite.getHeight() + (int) y;
        md.draw(new DrawProperties(drawX, drawY, flipH, false, sprite));
    }
}
