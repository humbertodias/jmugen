package org.lee.mugen.fight;

import java.util.List;
import java.util.Map;
import org.lee.mugen.input.CmdProcDispatcher;
import org.lee.mugen.input.MugenCommands;
import org.lee.mugen.renderer.DrawProperties;
import org.lee.mugen.renderer.ImageContainer;
import org.lee.mugen.renderer.MugenDrawer;
import org.lee.mugen.renderer.PalFxSub;
import org.lee.mugen.renderer.RGB;

/**
 * GWT fighter: crouch (10→11), jump (40→41/42/43), crouch kicks and hit reactions.
 */
public final class GwtFightPlayer {

    private static final float WALK_SPEED = 2.4f;
    private static final float RUN_SPEED = 5.2f;
    private static final float JUMP_X = 2.8f;
    private static final float GRAVITY = 0.45f;
    private static final float JUMP_VY = -8.2f;
    private static final int GROUND_Y = 192;
    private static final int FF_WINDOW = 18;
    private static final int SCREEN_MARGIN = 28;
    /** Min world gap before auto-turn (avoids flip thrash when overlapping). */
    private static final float TURN_GAP = 28f;
    private static final int TURN_COOLDOWN = 24;

    private static final int ACT_STAND = 0;
    private static final int ACT_TURN = 5;
    private static final int ACT_CROUCH_START = 10;
    private static final int ACT_CROUCH = 11;
    private static final int ACT_WALK_FWD = 20;
    private static final int ACT_WALK_BACK = 21;
    private static final int ACT_JUMP_START = 40;
    private static final int ACT_JUMP_UP = 41;
    private static final int ACT_JUMP_FWD = 42;
    private static final int ACT_JUMP_BACK = 43;
    private static final int ACT_LAND = 47;
    private static final int ACT_RUN = 100;
    private static final int ACT_RUN_BACK = 105;

    private static final int ANIM_HIT_STAND_L = 5000;
    private static final int ANIM_HIT_STAND_M = 5001;
    private static final int ANIM_HIT_STAND_H = 5002;
    private static final int ANIM_HIT_SLIDE_L = 5005;
    private static final int ANIM_HIT_CROUCH_L = 5010;
    private static final int ANIM_HIT_AIR_SHAKE = 5020;
    private static final int ANIM_HIT_AIR = 5030;
    private static final int ANIM_HIT_FALL = 5050;
    private static final int ANIM_HIT_BOUNCE = 5100;
    private static final int ANIM_HIT_LIE = 5110;
    private static final int ANIM_HIT_GETUP = 5120;
    private static final int ANIM_GUARD_STAND = 130;
    private static final int ANIM_GUARD_HIT = 150;

    private enum HitPhase {
        NONE,
        SHAKE,
        SLIDE,
        AIR,
        FALL,
        BOUNCE,
        LIE,
        GETUP,
        GUARD
    }

    private enum JumpPhase {
        NONE,
        START,
        AIR,
        LAND
    }

    final String cmdId;
    int facing;
    final GwtFightFighterAnim anim;
    private final List<MugenCommands> commands;
    private final Map<String, Integer> commandToState;
    private final GwtCnsData cns;
    private final GwtCnsRuntime cnsRuntime;
    private GwtFightAudio audio;
    private final int stageLeft;
    private final int stageRight;

    float x;
    float y;
    float vy;
    float vx;
    public int life;
    public int power;
    int currentAction = ACT_STAND;
    boolean onGround = true;
    boolean inAttack;
    boolean attackHit;
    private int attackHitsDone;
    private int lastHitAnimElem;
    boolean blocking;
    int groundY;
    private boolean cmdHoldForward;
    private boolean cmdHoldBack;
    private boolean cmdHoldDown;
    private boolean cmdHoldUp;
    private String pendingSpecial;
    private boolean ctrlEnabled = true;
    private boolean crouching;
    private boolean running;
    private boolean turning;
    private HitPhase hitPhase = HitPhase.NONE;
    private int hitTimer;
    private float hitSlideVx;
    private int hitSlideTime;
    private int hitPause;
    /** Anim to play after shake (common1 5005+animtype). */
    private int hitSlideAnim = ANIM_HIT_SLIDE_L;
    /** Remaining ground hittime after shake (common1 5001). */
    private int hitGroundTime;
    /** Screen floor line from stage zoffset (sprite draw / hitboxes). */
    private int zoffset = GROUND_Y;
    /** Stage {@code [Shadow]} — intensity 0 disables. */
    private int shadowIntensity = 128;
    private float shadowYScale = 0.25f;
    private JumpPhase jumpPhase = JumpPhase.NONE;
    private int jumpDir;
    private boolean prevPunch;
    private boolean prevKick;
    private boolean prevStrongPunch;
    private boolean prevStrongKick;
    private boolean prevUp;
    private boolean prevToward;
    private int towardTapTimer;
    private boolean towardTapArmed;
    private boolean towardSawRelease;
    private int turnCooldown;

    public GwtFightPlayer(
            String cmdId,
            int facing,
            int startX,
            int startY,
            GwtFightFighterAnim anim,
            int life,
            GwtCmdLoader.FightCmdData cmdData,
            int stageLeft,
            int stageRight) {
        this(cmdId, facing, startX, startY, anim, life, cmdData, null, stageLeft, stageRight);
    }

    public GwtFightPlayer(
            String cmdId,
            int facing,
            int startX,
            int startY,
            GwtFightFighterAnim anim,
            int life,
            GwtCmdLoader.FightCmdData cmdData,
            GwtCnsData cnsData,
            int stageLeft,
            int stageRight) {
        this.cmdId = cmdId;
        this.facing = facing;
        this.x = startX;
        this.y = startY;
        this.groundY = startY;
        this.anim = anim;
        this.cns = cnsData != null ? cnsData : new GwtCnsData();
        this.cnsRuntime = new GwtCnsRuntime(this.cns);
        this.life = life > 0 ? life : this.cns.life;
        this.power = 0;
        this.commands = cmdData != null ? cmdData.commands : null;
        this.commandToState = cmdData != null ? cmdData.commandToState : null;
        this.stageLeft = stageLeft;
        this.stageRight = stageRight;
    }

    private float walkSpeed() {
        return cns.walkFwd > 0 ? cns.walkFwd : WALK_SPEED;
    }

    private float walkBackSpeed() {
        return cns.walkBack != 0 ? Math.abs(cns.walkBack) : WALK_SPEED;
    }

    private float runSpeed() {
        return cns.runFwdX > 0 ? cns.runFwdX : RUN_SPEED;
    }

    private float jumpXSpeed() {
        return cns.jumpFwdX > 0 ? cns.jumpFwdX : JUMP_X;
    }

    private float jumpVy() {
        return cns.jumpNeuY != 0 ? cns.jumpNeuY : JUMP_VY;
    }

    private float gravity() {
        return cns.yAccel > 0 ? cns.yAccel : GRAVITY;
    }

    public void setAudio(GwtFightAudio audio) {
        this.audio = audio;
    }

    public void setZOffset(int zoffset) {
        this.zoffset = zoffset > 0 ? zoffset : GROUND_Y;
    }

    public void setShadow(int intensity, float yScale) {
        this.shadowIntensity = intensity;
        // Match desktop SpriteShadowRender behind mode (~0.25) when stage omits / uses tiny values.
        float abs = Math.abs(yScale);
        this.shadowYScale = abs > 0.01f ? abs : 0.25f;
    }

    /** Resolve Clsn1 vs Clsn2 after body push (desktop: physics then FightEngine). */
    public void tryHitOpponent(GwtFightPlayer opponent) {
        if (inAttack && hitPause <= 0) {
            tryHit(opponent);
        }
    }

    public GwtCnsData getCns() {
        return cns;
    }

    List<MugenCommands> getCommands() {
        return commands;
    }

    public void setCtrlEnabled(boolean ctrlEnabled) {
        this.ctrlEnabled = ctrlEnabled;
    }

    public boolean isInHitReaction() {
        return hitPhase != HitPhase.NONE;
    }

    public void onCommand(String commandName) {
        if (commandName == null) {
            return;
        }
        if ("holdfwd".equals(commandName)) {
            cmdHoldForward = true;
            return;
        }
        if ("holdback".equals(commandName)) {
            cmdHoldBack = true;
            return;
        }
        if ("holddown".equals(commandName)) {
            cmdHoldDown = true;
            return;
        }
        if ("holdup".equals(commandName)) {
            cmdHoldUp = true;
            return;
        }
        if (!ctrlEnabled || inAttack || isInHitReaction() || jumpPhase == JumpPhase.START || turning) {
            return;
        }
        if ("FF".equals(commandName)) {
            pendingSpecial = "FF";
            return;
        }
        if ("BB".equals(commandName)) {
            pendingSpecial = "BB";
            return;
        }
        if (isSingleButton(commandName)) {
            return;
        }
        pendingSpecial = commandName;
    }

    private static boolean isSingleButton(String name) {
        return "a".equals(name)
                || "b".equals(name)
                || "c".equals(name)
                || "x".equals(name)
                || "y".equals(name)
                || "z".equals(name)
                || "start".equals(name)
                || "recovery".equals(name)
                || "down_a".equals(name)
                || "down_b".equals(name);
    }

    public void update(CmdProcDispatcher cmd, boolean[] keysDown, GwtFightPlayer opponent) {
        boolean down = cmdHoldDown;
        boolean up = cmdHoldUp;
        cmdHoldForward = false;
        cmdHoldBack = false;
        cmdHoldDown = false;
        cmdHoldUp = false;

        // Screen-absolute left/right (A/D or ←/→): direction does not invert when facing flips.
        boolean moveLeft = false;
        boolean moveRight = false;
        boolean punch = false;
        boolean kick = false;
        boolean strongPunch = false;
        boolean strongKick = false;
        if (cmd != null && keysDown != null) {
            // Standard maps: back=left key, forward=right key
            moveLeft = key(keysDown, cmd.getBack());
            moveRight = key(keysDown, cmd.getForward());
            up |= key(keysDown, cmd.getUp());
            down |= key(keysDown, cmd.getDown());
            punch = key(keysDown, cmd.getX());
            strongPunch = key(keysDown, cmd.getY());
            kick = key(keysDown, cmd.getA());
            strongKick = key(keysDown, cmd.getB());
        }

        // Facing-relative (Mugen holdfwd / holdback) after facing is set
        boolean toward = (facing == 1 && moveRight) || (facing == -1 && moveLeft);
        boolean away = (facing == 1 && moveLeft) || (facing == -1 && moveRight);

        boolean punchPress = punch && !prevPunch;
        boolean kickPress = kick && !prevKick;
        boolean strongPunchPress = strongPunch && !prevStrongPunch;
        boolean strongKickPress = strongKick && !prevStrongKick;
        boolean upPress = up && !prevUp;
        boolean towardPress = toward && !prevToward;
        prevPunch = punch;
        prevKick = kick;
        prevStrongPunch = strongPunch;
        prevStrongKick = strongKick;
        prevUp = up;
        prevToward = toward;

        if (towardTapTimer > 0) {
            towardTapTimer--;
        }
        if (turnCooldown > 0) {
            turnCooldown--;
        }

        if (down) {
            up = false;
            upPress = false;
        }

        crouching = down && onGround && jumpPhase == JumpPhase.NONE && !running && !turning;
        blocking = away && onGround && !isInHitReaction() && !inAttack && !running;

        // True hitstop: freeze movement (desktop pausetime). Still tick CNS
        // controllers marked ignorehitpause (e.g. ChangeAnim on movecontact).
        if (hitPause > 0) {
            hitPause--;
            if (inAttack) {
                cnsRuntime.tick(cnsHost);
            }
            clampStage();
            return;
        }

        if (isInHitReaction()) {
            running = false;
            turning = false;
            // SHAKE: freeze anim like desktop pausetime; other phases advance.
            if (hitPhase != HitPhase.SHAKE) {
                anim.process();
            }
            updateHitReaction();
            applyPhysics();
            clampStage();
            return;
        }

        anim.process();

        if (turning) {
            if (anim.isActionFinished()) {
                facing = -facing;
                turning = false;
                turnCooldown = TURN_COOLDOWN;
                currentAction = ACT_STAND;
                anim.setAction(ACT_STAND);
                // Recompute toward after flip so held keys keep screen meaning
                toward = (facing == 1 && moveRight) || (facing == -1 && moveLeft);
                away = (facing == 1 && moveLeft) || (facing == -1 && moveRight);
                prevToward = toward;
            }
            applyPhysics();
            clampStage();
            return;
        }

        if (jumpPhase == JumpPhase.START) {
            updateJumpStart(moveLeft, moveRight);
            applyPhysics();
            clampStage();
            return;
        }

        if (jumpPhase == JumpPhase.LAND) {
            if (anim.isActionFinished()) {
                jumpPhase = JumpPhase.NONE;
                currentAction = ACT_STAND;
                anim.setAction(ACT_STAND);
            }
            applyPhysics();
            clampStage();
            return;
        }

        if (inAttack) {
            running = false;
            cnsRuntime.tick(cnsHost);
            if (!inAttack) {
                applyPhysics();
                clampStage();
                return;
            }
            // Safety: exit if anim ended and CNS ChangeState didn't fire.
            if (anim.isActionFinished()) {
                inAttack = false;
                if (!onGround) {
                    currentAction = jumpAirAnim();
                    anim.setAction(currentAction);
                } else if (down) {
                    enterCrouchIdle();
                } else {
                    currentAction = ACT_STAND;
                    anim.setAction(ACT_STAND);
                }
            } else {
                // Hits resolved after body push in GwtGameFight (desktop order).
            }
            applyPhysics();
            clampStage();
            return;
        }

        if (ctrlEnabled) {
            if (pendingSpecial != null) {
                String cmdName = pendingSpecial;
                pendingSpecial = null;
                if ("FF".equals(cmdName) && onGround) {
                    startRun();
                } else if ("BB".equals(cmdName) && onGround) {
                    startRunBack();
                } else {
                    tryEnterFromCommand(cmdName, down);
                }
            }
            // Double-tap toward opponent → run
            if (!toward && towardTapArmed) {
                towardSawRelease = true;
            }
            if (onGround && !running && towardPress) {
                if (towardTapArmed && towardSawRelease && towardTapTimer > 0) {
                    startRun();
                    clearTowardTap();
                } else {
                    towardTapArmed = true;
                    towardSawRelease = false;
                    towardTapTimer = FF_WINDOW;
                }
            }
            if (towardTapTimer <= 0 && towardTapArmed) {
                clearTowardTap();
            }

            // Auto-turn only when idle horizontally (prevents flip loop while walking past)
            if (!inAttack && !running && !moveLeft && !moveRight) {
                maybeAutoTurn(opponent);
            }

            if (!inAttack && !turning) {
                boolean attackPressed =
                        punchPress || strongPunchPress || kickPress || strongKickPress;
                if (running) {
                    updateRun(
                            toward,
                            away,
                            moveLeft,
                            moveRight,
                            upPress,
                            attackPressed,
                            punchPress,
                            strongPunchPress,
                            kickPress,
                            strongKickPress,
                            down);
                } else if (upPress && onGround && jumpPhase == JumpPhase.NONE) {
                    beginJump(moveLeft, moveRight);
                } else if (attackPressed && onGround) {
                    enterBasicAttack(
                            punchPress, strongPunchPress, kickPress, strongKickPress, down);
                } else if (attackPressed && !onGround) {
                    enterBasicAttack(
                            punchPress, strongPunchPress, kickPress, strongKickPress, false);
                } else if (onGround) {
                    applyGroundMovement(moveLeft, moveRight, toward, away, down);
                } else {
                    updateAirAnim(moveLeft, moveRight);
                }
            }
        } else if (onGround && !inAttack) {
            if (currentAction == ACT_CROUCH_START && anim.isActionFinished()) {
                enterCrouchIdle();
            }
        }

        applyPhysics();
        clampStage();
    }

    private static boolean key(boolean[] keysDown, int code) {
        return code >= 0 && code < keysDown.length && keysDown[code];
    }

    private void maybeAutoTurn(GwtFightPlayer opponent) {
        if (opponent == null || !onGround || running || turning || inAttack) {
            return;
        }
        if (jumpPhase != JumpPhase.NONE || turnCooldown > 0) {
            return;
        }
        if (currentAction == ACT_CROUCH || currentAction == ACT_CROUCH_START) {
            return;
        }
        int want = desiredFacing(opponent);
        if (want != 0 && want != facing) {
            startTurn();
        }
    }

    private int desiredFacing(GwtFightPlayer opponent) {
        float dx = opponent.x - x;
        if (dx > TURN_GAP) {
            return 1;
        }
        if (dx < -TURN_GAP) {
            return -1;
        }
        return 0;
    }

    private void startTurn() {
        turning = true;
        running = false;
        currentAction = ACT_TURN;
        if (anim.hasAction(ACT_TURN)) {
            anim.setAction(ACT_TURN);
        } else {
            facing = -facing;
            turning = false;
            turnCooldown = TURN_COOLDOWN;
            currentAction = ACT_STAND;
            anim.setAction(ACT_STAND);
        }
    }

    private void clearTowardTap() {
        towardTapArmed = false;
        towardSawRelease = false;
        towardTapTimer = 0;
    }

    private void startRun() {
        if (!onGround || turning) {
            return;
        }
        running = true;
        inAttack = false;
        clearTowardTap();
        currentAction = ACT_RUN;
        anim.setAction(anim.hasAction(ACT_RUN) ? ACT_RUN : ACT_WALK_FWD);
    }

    private void startRunBack() {
        if (!onGround || turning) {
            return;
        }
        running = false;
        inAttack = false;
        onGround = false;
        jumpPhase = JumpPhase.AIR;
        jumpDir = -1;
        vy = -4f;
        vx = -facing * runSpeed() * 0.85f;
        currentAction = ACT_RUN_BACK;
        anim.setAction(anim.hasAction(ACT_RUN_BACK) ? ACT_RUN_BACK : ACT_JUMP_BACK);
    }

    private void updateRun(
            boolean toward,
            boolean away,
            boolean moveLeft,
            boolean moveRight,
            boolean upPress,
            boolean attackPressed,
            boolean punchPress,
            boolean strongPunchPress,
            boolean kickPress,
            boolean strongKickPress,
            boolean down) {
        if (!toward || away || down) {
            running = false;
            currentAction = ACT_STAND;
            anim.setAction(ACT_STAND);
            return;
        }
        if (upPress) {
            running = false;
            beginJump(moveLeft, moveRight);
            return;
        }
        if (attackPressed) {
            running = false;
            enterBasicAttack(punchPress, strongPunchPress, kickPress, strongKickPress, false);
            return;
        }
        if (currentAction != ACT_RUN) {
            currentAction = ACT_RUN;
            anim.setAction(anim.hasAction(ACT_RUN) ? ACT_RUN : ACT_WALK_FWD);
        }
        // Run toward facing (screen: +x if facing right)
        x += facing * runSpeed();
    }

    private void applyGroundMovement(
            boolean moveLeft, boolean moveRight, boolean toward, boolean away, boolean down) {
        running = false;
        if (down) {
            if (currentAction != ACT_CROUCH && currentAction != ACT_CROUCH_START) {
                currentAction = ACT_CROUCH_START;
                anim.setAction(ACT_CROUCH_START);
            } else if (currentAction == ACT_CROUCH_START && anim.isActionFinished()) {
                enterCrouchIdle();
            }
            return;
        }
        if (currentAction == ACT_CROUCH || currentAction == ACT_CROUCH_START) {
            currentAction = ACT_STAND;
            anim.setAction(ACT_STAND);
        }
        // Screen-absolute movement: Left always -x, Right always +x
        if (moveRight && !moveLeft) {
            x += walkSpeed();
            if (toward) {
                if (currentAction != ACT_WALK_FWD) {
                    currentAction = ACT_WALK_FWD;
                    anim.setAction(ACT_WALK_FWD);
                }
            } else if (away) {
                if (currentAction != ACT_WALK_BACK) {
                    currentAction = ACT_WALK_BACK;
                    anim.setAction(ACT_WALK_BACK);
                }
            } else if (currentAction != ACT_WALK_FWD) {
                currentAction = ACT_WALK_FWD;
                anim.setAction(ACT_WALK_FWD);
            }
        } else if (moveLeft && !moveRight) {
            x -= walkSpeed();
            if (toward) {
                if (currentAction != ACT_WALK_FWD) {
                    currentAction = ACT_WALK_FWD;
                    anim.setAction(ACT_WALK_FWD);
                }
            } else if (away) {
                if (currentAction != ACT_WALK_BACK) {
                    currentAction = ACT_WALK_BACK;
                    anim.setAction(ACT_WALK_BACK);
                }
            } else if (currentAction != ACT_WALK_BACK) {
                currentAction = ACT_WALK_BACK;
                anim.setAction(ACT_WALK_BACK);
            }
        } else if (currentAction != ACT_STAND && currentAction != ACT_TURN) {
            currentAction = ACT_STAND;
            anim.setAction(ACT_STAND);
        }
    }

    private void enterCrouchIdle() {
        currentAction = ACT_CROUCH;
        anim.setAction(ACT_CROUCH);
    }

    private void beginJump(boolean moveLeft, boolean moveRight) {
        jumpPhase = JumpPhase.START;
        jumpDir = 0;
        if (moveRight && !moveLeft) {
            jumpDir = 1; // screen right
        } else if (moveLeft && !moveRight) {
            jumpDir = -1; // screen left
        }
        inAttack = false;
        onGround = true;
        currentAction = ACT_JUMP_START;
        anim.setAction(ACT_JUMP_START);
    }

    private void updateJumpStart(boolean moveLeft, boolean moveRight) {
        if (moveRight && !moveLeft) {
            jumpDir = 1;
        } else if (moveLeft && !moveRight) {
            jumpDir = -1;
        }
        if (anim.isActionFinished()) {
            jumpPhase = JumpPhase.AIR;
            onGround = false;
            vy = jumpVy();
            // jumpDir is screen direction; vx is world
            vx = jumpDir * jumpXSpeed();
            currentAction = jumpAirAnim();
            anim.setAction(currentAction);
        }
    }

    private int jumpAirAnim() {
        // Anim forward/back relative to facing
        int relative = jumpDir * facing;
        if (relative > 0) {
            return anim.hasAction(ACT_JUMP_FWD) ? ACT_JUMP_FWD : ACT_JUMP_UP;
        }
        if (relative < 0) {
            return anim.hasAction(ACT_JUMP_BACK) ? ACT_JUMP_BACK : ACT_JUMP_UP;
        }
        return ACT_JUMP_UP;
    }

    private void updateAirAnim(boolean moveLeft, boolean moveRight) {
        if (jumpPhase != JumpPhase.AIR) {
            return;
        }
        if (moveRight && !moveLeft) {
            x += 0.6f;
        } else if (moveLeft && !moveRight) {
            x -= 0.6f;
        }
        int want = jumpAirAnim();
        if (currentAction != want && currentAction < 200) {
            currentAction = want;
            anim.setAction(want);
        }
    }

    private void tryEnterFromCommand(String commandName, boolean down) {
        if ("FF".equals(commandName)) {
            startRun();
            return;
        }
        if ("BB".equals(commandName)) {
            startRunBack();
            return;
        }
        Integer state = commandToState != null ? commandToState.get(commandName) : null;
        if (state == null) {
            return;
        }
        if (state == 100 && onGround) {
            startRun();
            return;
        }
        if (state == 105 && onGround) {
            startRunBack();
            return;
        }
        if (state >= 600 && !onGround) {
            enterAttackState(state);
        } else if (state >= 400 && state < 600 && onGround) {
            enterAttackState(state);
        } else if (state >= 200 && state < 400 && onGround && !down) {
            enterAttackState(state);
        }
    }

    private void enterBasicAttack(
            boolean punch, boolean strongPunch, boolean kick, boolean strongKick, boolean down) {
        if (!punch && !strongPunch && !kick && !strongKick) {
            return;
        }
        if (down && onGround) {
            if (strongKick) {
                enterAttackState(resolveAttack("b", 440));
            } else if (kick) {
                enterAttackState(resolveAttack("a", 430));
            } else if (strongPunch) {
                enterAttackState(resolveAttack("y", 410));
            } else {
                enterAttackState(resolveAttack("x", 400));
            }
            return;
        }
        if (!onGround) {
            if (strongKick) {
                enterAttackState(640);
            } else if (kick) {
                enterAttackState(630);
            } else if (strongPunch) {
                enterAttackState(610);
            } else {
                enterAttackState(600);
            }
            return;
        }
        if (strongKick) {
            enterAttackState(resolveAttack("b", 240));
        } else if (kick) {
            enterAttackState(resolveAttack("a", 230));
        } else if (strongPunch) {
            enterAttackState(resolveAttack("y", 210));
        } else {
            enterAttackState(resolveAttack("x", 200));
        }
    }

    /** Prefer hardcoded crouch/stand state; .cmd map for "a" is often standing-only. */
    private int resolveAttack(String cmdName, int fallback) {
        if (fallback >= 400) {
            return fallback;
        }
        Integer mapped = commandToState != null ? commandToState.get(cmdName) : null;
        if (mapped != null && mapped >= 200 && mapped < 400) {
            return mapped;
        }
        return fallback;
    }

    private void enterAttackState(int state) {
        inAttack = true;
        attackHit = false;
        attackHitsDone = 0;
        lastHitAnimElem = 0;
        running = false;
        crouching = state >= 400 && state < 600;
        currentAction = state;
        // Restart anim even if same state is re-used.
        anim.restartAction(state);
        cnsRuntime.changeState(state, cnsHost);
    }

    private final GwtCnsRuntime.Host cnsHost =
            new GwtCnsRuntime.Host() {
                public int getStateNo() {
                    return cnsRuntime.getStateNo();
                }

                public int getStateTime() {
                    return cnsRuntime.getStateTime();
                }

                public int getAnimElem() {
                    return anim.getAnimElemNo();
                }

                public int getAnimTime() {
                    return anim.getAnimTime();
                }

                public boolean isMoveContact() {
                    return attackHitsDone > 0 || attackHit;
                }

                public boolean isHitPause() {
                    return hitPause > 0;
                }

                public int getFacing() {
                    return facing;
                }

                public void setAnim(int a) {
                    anim.restartAction(a);
                    currentAction = a;
                }

                public void setAnimElem(int elem1Based) {
                    anim.setAnimElem(elem1Based);
                }

                public void setVel(float worldVx, float worldVy) {
                    vx = worldVx;
                    vy = worldVy;
                }

                public void setCtrl(boolean ctrl) {
                    ctrlEnabled = ctrl;
                }

                public void applyChangeState(int stateNo, int ctrl) {
                    if (ctrl >= 0) {
                        ctrlEnabled = ctrl != 0;
                    }
                    if (stateNo == 0) {
                        inAttack = false;
                        running = false;
                        crouching = false;
                        currentAction = ACT_STAND;
                        anim.restartAction(ACT_STAND);
                        return;
                    }
                    if (stateNo == 11 || stateNo == 10) {
                        inAttack = false;
                        running = false;
                        crouching = true;
                        enterCrouchIdle();
                        return;
                    }
                    if (stateNo >= 200) {
                        inAttack = true;
                        currentAction = stateNo;
                        crouching = stateNo >= 400 && stateNo < 600;
                        return;
                    }
                    inAttack = false;
                    currentAction = stateNo;
                    anim.restartAction(stateNo);
                }
            };

    private void tryHit(GwtFightPlayer opponent) {
        if (!inAttack || opponent == null) {
            return;
        }
        // Don't land hits during mutual hitstop / opponent shake frames.
        if (opponent.hitPause > 0) {
            return;
        }
        HitParams hp = hitParamsFor(currentAction, attackHitsDone);
        int elem = anim.getAnimElemNo();
        if (attackHitsDone >= hp.maxHits) {
            return;
        }
        // Multi-hit: next hit only on a later AnimElem (e.g. crouch strong punch).
        if (attackHitsDone > 0 && elem <= lastHitAnimElem) {
            return;
        }
        // CNS HitDef activates at AnimElem = N and stays until hit / state end.
        if (hp.requiredAnimElem > 0 && elem < hp.requiredAnimElem) {
            return;
        }
        // Precise active frames: only when AIR has clsn1 (no distance fallback).
        if (!anim.hasActiveHitbox()
                || opponent.anim.getHurtBoxes() == null
                || opponent.anim.getHurtBoxes().length == 0) {
            return;
        }
        float ax = x;
        float ay = y;
        float dx = opponent.x;
        float dy = opponent.y;
        boolean hit =
                GwtFightHitbox.attackHits(
                        anim, ax, ay, facing, opponent.anim, dx, dy, opponent.facing);
        if (!hit) {
            return;
        }
        boolean blocked = opponent.blocking;
        int damage = hp.damage;
        if (blocked) {
            damage = Math.max(1, damage / 4);
        }
        playHitSound(blocked);
        opponent.life = Math.max(0, opponent.life - damage);
        power = Math.min(GwtFightRules.MAX_POWER, power + Math.max(1, damage / 2));
        attackHitsDone++;
        lastHitAnimElem = elem;
        attackHit = attackHitsDone >= hp.maxHits;
        // Attacker pausetime; defender shake is HitPhase.SHAKE (not hitPause double-freeze).
        hitPause = hp.pauseP1;
        if (hp.moveContactElem > 0) {
            anim.setAnimElem(hp.moveContactElem);
        }
        opponent.receiveHit(damage, facing, currentAction, blocked, !onGround, hp);
    }

    private void playHitSound(boolean blocked) {
        if (audio == null) {
            return;
        }
        GwtCnsData.HitDef hd = cns != null ? cns.getHitDef(currentAction, attackHitsDone) : null;
        if (blocked) {
            int grp = hd != null ? hd.guardsoundGrp : 6;
            int num = hd != null ? hd.guardsoundNum : 0;
            if (hd == null || !hd.guardsoundFromSprite) {
                audio.playCommon(grp, num);
            }
        } else {
            int grp = hd != null ? hd.hitsoundGrp : 5;
            int num = hd != null ? hd.hitsoundNum : 0;
            if (hd == null || !hd.hitsoundFromSprite) {
                audio.playCommon(grp, num);
            }
        }
    }

    /** Per-attack constants aligned with KFM {@code HitDef} (damage, pause, knockback, hittime). */
    private static final class HitParams {
        final int damage;
        final int pauseP1;
        final int pauseP2;
        final float groundVx;
        final float airVx;
        final float airVy;
        final int slideTime;
        final int hitTime;
        final int maxHits;
        final int requiredAnimElem;
        /** 1-based AnimElem to skip to on contact (0 = none). */
        final int moveContactElem;
        final boolean trip;
        /** 0=Light, 1=Medium, 2=Hard — common1 gethit anim index. */
        final int animType;

        HitParams(
                int damage,
                int pauseP1,
                int pauseP2,
                float groundVx,
                float airVx,
                float airVy,
                int slideTime,
                int hitTime,
                int maxHits,
                int moveContactElem,
                boolean trip) {
            this(
                    damage,
                    pauseP1,
                    pauseP2,
                    groundVx,
                    airVx,
                    airVy,
                    slideTime,
                    hitTime,
                    maxHits,
                    0,
                    moveContactElem,
                    trip,
                    0);
        }

        HitParams(
                int damage,
                int pauseP1,
                int pauseP2,
                float groundVx,
                float airVx,
                float airVy,
                int slideTime,
                int hitTime,
                int maxHits,
                int requiredAnimElem,
                int moveContactElem,
                boolean trip) {
            this(
                    damage,
                    pauseP1,
                    pauseP2,
                    groundVx,
                    airVx,
                    airVy,
                    slideTime,
                    hitTime,
                    maxHits,
                    requiredAnimElem,
                    moveContactElem,
                    trip,
                    0);
        }

        HitParams(
                int damage,
                int pauseP1,
                int pauseP2,
                float groundVx,
                float airVx,
                float airVy,
                int slideTime,
                int hitTime,
                int maxHits,
                int requiredAnimElem,
                int moveContactElem,
                boolean trip,
                int animType) {
            this.damage = damage;
            this.pauseP1 = pauseP1;
            this.pauseP2 = pauseP2;
            this.groundVx = groundVx;
            this.airVx = airVx;
            this.airVy = airVy;
            this.slideTime = slideTime;
            this.hitTime = hitTime;
            this.maxHits = maxHits;
            this.requiredAnimElem = requiredAnimElem;
            this.moveContactElem = moveContactElem;
            this.trip = trip;
            this.animType = animType < 0 ? 0 : (animType > 2 ? 2 : animType);
        }
    }

    private HitParams hitParamsFor(int state, int hitIndex) {
        GwtCnsData.HitDef hd = cns != null ? cns.getHitDef(state, hitIndex) : null;
        if (hd != null) {
            int maxHits = Math.max(1, cns.getHitDefCount(state));
            int contactElem = state == 210 ? 6 : 0;
            return new HitParams(
                    hd.damage,
                    hd.pauseP1,
                    hd.pauseP2,
                    hd.groundVelocityX,
                    hd.airVelocityX,
                    hd.airVelocityY,
                    hd.groundSlideTime,
                    hd.groundHitTime,
                    maxHits,
                    hd.animElem,
                    contactElem,
                    hd.trip || hd.fall,
                    hd.animType);
        }
        return hitParamsFallback(state, hitIndex);
    }

    private static HitParams hitParamsFallback(int state, int hitIndex) {
        // Fallback when CNS HitDef wasn't parsed for this state.
        switch (state) {
            case 200:
                return new HitParams(23, 8, 8, 4f, 1.4f, -3f, 5, 10, 1, 3, 0, false, 0);
            case 210:
                return new HitParams(57, 12, 12, 5.5f, 2.5f, -4f, 12, 13, 1, 3, 6, false, 2);
            case 230:
                return new HitParams(26, 12, 12, 5f, 2.5f, -3.5f, 10, 11, 1, 0, 0, false, 0);
            case 240:
                return new HitParams(63, 12, 12, 6f, 2.5f, -4f, 12, 13, 1, 0, 0, false, 2);
            case 400:
                return new HitParams(23, 10, 11, 4f, 1.4f, -3f, 5, 10, 1, 0, 0, false, 0);
            case 410:
                if (hitIndex == 0) {
                    return new HitParams(37, 12, 12, 4f, 3f, -4f, 12, 15, 2, 3, 0, false, 1);
                }
                return new HitParams(36, 12, 12, 7f, 3f, -4f, 12, 15, 2, 4, 0, false, 1);
            case 430:
                return new HitParams(28, 12, 12, 5f, 2f, -3f, 6, 10, 1, 0, 0, false, 0);
            case 440:
                return new HitParams(72, 12, 12, 1.5f, 1.2f, -3f, 10, 15, 1, 0, 0, true, 2);
            case 600:
                return new HitParams(20, 7, 8, 4f, 1.3f, -3f, 5, 5, 1, 0, 0, false, 0);
            case 610:
                return new HitParams(72, 12, 12, 5f, 3f, -4f, 17, 17, 1, 0, 0, false, 2);
            case 630:
                return new HitParams(26, 8, 8, 4f, 2f, -3f, 6, 6, 1, 0, 0, false, 0);
            case 640:
                return new HitParams(70, 12, 12, 5f, 3f, -4f, 15, 15, 1, 0, 0, false, 2);
            default:
                break;
        }
        if (state >= 1000) {
            return new HitParams(180, 12, 12, 6f, 4f, -6f, 12, 20, 1, 0, 0, false);
        }
        if (state >= 400) {
            return new HitParams(40, 10, 10, 4f, 2f, -3f, 8, 12, 1, 0, 0, false);
        }
        if (state >= 200) {
            return new HitParams(40, 8, 8, 4f, 2f, -3f, 8, 10, 1, 0, 0, false);
        }
        return new HitParams(30, 8, 8, 3f, 1.5f, -3f, 6, 10, 1, 0, 0, false);
    }

    private HitParams hitParamsFor(int state) {
        return hitParamsFor(state, 0);
    }

    private void updateHitReaction() {
        hitTimer--;
        if (hitPhase == HitPhase.SHAKE) {
            if (hitTimer <= 0) {
                if (life <= 0) {
                    startFall();
                } else {
                    // common1 5000 → 5001: HitVelSet + slide anim 5005+animtype
                    hitPhase = HitPhase.SLIDE;
                    hitTimer = Math.max(4, hitGroundTime);
                    vx = hitSlideVx;
                    setHitAnim(hitSlideAnim, ANIM_HIT_STAND_L);
                }
            }
            return;
        }
        if (hitPhase == HitPhase.SLIDE || hitPhase == HitPhase.GUARD) {
            x += vx;
            // After slidetime, decay; stay locked until hittime (hitTimer) ends.
            if (hitSlideTime > 0) {
                hitSlideTime--;
            } else {
                vx *= cns.standFriction > 0 ? cns.standFriction : 0.85f;
            }
            if (hitTimer <= 0) {
                if (life <= 0) {
                    startFall();
                } else {
                    clearHit();
                }
            }
            return;
        }
        if (hitPhase == HitPhase.AIR || hitPhase == HitPhase.FALL) {
            x += vx;
            if (onGround && vy >= 0 && y >= groundY) {
                hitPhase = HitPhase.BOUNCE;
                hitTimer = 18;
                vx *= 0.5f;
                vy = -3f;
                onGround = false;
                setHitAnim(ANIM_HIT_BOUNCE, ANIM_HIT_FALL);
            } else if (hitTimer <= 0 && anim.isActionFinished() && hitPhase == HitPhase.AIR) {
                hitPhase = HitPhase.FALL;
                setHitAnim(ANIM_HIT_FALL, ANIM_HIT_AIR);
                hitTimer = 60;
            }
            return;
        }
        if (hitPhase == HitPhase.BOUNCE) {
            x += vx;
            if (onGround && y >= groundY) {
                hitPhase = HitPhase.LIE;
                hitTimer = life <= 0 ? 90 : 30;
                vx = 0;
                setHitAnim(ANIM_HIT_LIE, ANIM_HIT_FALL);
            }
            return;
        }
        if (hitPhase == HitPhase.LIE) {
            if (hitTimer <= 0) {
                if (life <= 0) {
                    hitTimer = 1;
                } else {
                    hitPhase = HitPhase.GETUP;
                    hitTimer = 30;
                    setHitAnim(ANIM_HIT_GETUP, ACT_STAND);
                }
            }
            return;
        }
        if (hitPhase == HitPhase.GETUP) {
            if (hitTimer <= 0 || anim.isActionFinished()) {
                clearHit();
            }
        }
    }

    private void clearHit() {
        hitPhase = HitPhase.NONE;
        hitTimer = 0;
        hitSlideVx = 0;
        hitSlideTime = 0;
        vx = 0;
        currentAction = ACT_STAND;
        anim.setAction(ACT_STAND);
        inAttack = false;
        jumpPhase = JumpPhase.NONE;
    }

    private void startFall() {
        hitPhase = HitPhase.FALL;
        hitTimer = 90;
        onGround = false;
        vy = -4f;
        setHitAnim(ANIM_HIT_FALL, ANIM_HIT_AIR);
    }

    private void setHitAnim(int preferred, int fallback) {
        currentAction = preferred;
        anim.setActionIfExists(preferred, fallback);
    }

    public void receiveHit(int damage, int attackerFacing, int attackState, boolean wasBlocking) {
        receiveHit(damage, attackerFacing, attackState, wasBlocking, false, hitParamsFor(attackState));
    }

    public void receiveHit(
            int damage,
            int attackerFacing,
            int attackState,
            boolean wasBlocking,
            boolean attackerInAir) {
        receiveHit(
                damage, attackerFacing, attackState, wasBlocking, attackerInAir, hitParamsFor(attackState));
    }

    public void receiveHit(
            int damage,
            int attackerFacing,
            int attackState,
            boolean wasBlocking,
            boolean attackerInAir,
            HitParams hp) {
        inAttack = false;
        attackHit = false;
        attackHitsDone = 0;
        lastHitAnimElem = 0;
        pendingSpecial = null;
        running = false;
        turning = false;

        if (wasBlocking) {
            jumpPhase = JumpPhase.NONE;
            hitPhase = HitPhase.GUARD;
            hitTimer = Math.max(8, hp.hitTime / 2);
            hitSlideTime = 4;
            hitSlideVx = attackerFacing * 1.2f;
            vx = hitSlideVx;
            setHitAnim(ANIM_GUARD_HIT, ANIM_GUARD_STAND);
            return;
        }

        boolean hardKnockdown = damage >= 100 || attackState >= 1000 || life <= 0 || hp.trip;
        if (hardKnockdown) {
            jumpPhase = JumpPhase.NONE;
            hitPhase = HitPhase.AIR;
            hitTimer = Math.max(16, hp.hitTime);
            onGround = false;
            vx = attackerFacing * Math.max(hp.groundVx, 1.5f);
            vy = hp.trip ? -2f : -6f;
            setHitAnim(ANIM_HIT_AIR, ANIM_HIT_STAND_H);
            return;
        }

        if (!onGround) {
            jumpPhase = JumpPhase.NONE;
            hitPhase = HitPhase.AIR;
            hitTimer = Math.max(10, hp.hitTime);
            onGround = false;
            vx = attackerFacing * hp.airVx;
            vy = hp.airVy;
            int airAnim = ANIM_HIT_AIR_SHAKE + hp.animType;
            setHitAnim(airAnim, ANIM_HIT_AIR);
            return;
        }

        // Ground hit: common1 5000 (shake) → 5001 (slide). Shake lasts p2 pausetime.
        jumpPhase = JumpPhase.NONE;
        hitPhase = HitPhase.SHAKE;
        hitTimer = Math.max(1, hp.pauseP2);
        hitGroundTime = Math.max(6, hp.hitTime);
        hitSlideTime = Math.max(1, hp.slideTime);
        hitSlideVx = attackerFacing * hp.groundVx;
        vx = 0;
        int animType = hp.animType;
        if (crouching) {
            setHitAnim(ANIM_HIT_CROUCH_L + animType, ANIM_HIT_CROUCH_L);
            hitSlideAnim = ANIM_HIT_CROUCH_L + animType;
        } else {
            setHitAnim(ANIM_HIT_STAND_L + animType, ANIM_HIT_STAND_L);
            hitSlideAnim = ANIM_HIT_SLIDE_L + animType;
        }
    }

    private void applyPhysics() {
        if (jumpPhase == JumpPhase.START) {
            return;
        }
        if (!onGround || jumpPhase == JumpPhase.AIR) {
            if (jumpPhase == JumpPhase.AIR) {
                x += vx;
            }
            vy += gravity();
            y += vy;
            if (y >= groundY) {
                y = groundY;
                if (hitPhase == HitPhase.AIR
                        || hitPhase == HitPhase.FALL
                        || hitPhase == HitPhase.BOUNCE) {
                    onGround = true;
                } else if (jumpPhase == JumpPhase.AIR) {
                    onGround = true;
                    vy = 0;
                    vx = 0;
                    // Desktop: jump attack keeps physics=A until anim ends — don't cancel mid-kick.
                    if (inAttack && currentAction >= 600) {
                        jumpPhase = JumpPhase.NONE;
                    } else {
                        jumpPhase = JumpPhase.LAND;
                        inAttack = false;
                        if (anim.hasAction(ACT_LAND)) {
                            currentAction = ACT_LAND;
                            anim.setAction(ACT_LAND);
                        } else {
                            jumpPhase = JumpPhase.NONE;
                            currentAction = ACT_STAND;
                            anim.setAction(ACT_STAND);
                        }
                    }
                } else {
                    vy = 0;
                    onGround = true;
                    if (!inAttack && !isInHitReaction() && currentAction >= 600) {
                        currentAction = ACT_STAND;
                        anim.setAction(ACT_STAND);
                    }
                }
            }
        }
    }

    private void clampStage() {
        if (x < stageLeft) {
            x = stageLeft;
        }
        if (x > stageRight) {
            x = stageRight;
        }
    }

    /**
     * Like desktop {@code PhysicsEngime.checkGoodPositionInScreen}: keep fighter in the 320px view.
     */
    public void clampToScreen(float cameraX) {
        float screenX = 160f + x + cameraX;
        if (screenX < SCREEN_MARGIN) {
            x += SCREEN_MARGIN - screenX;
        } else if (screenX > 320 - SCREEN_MARGIN) {
            x -= screenX - (320 - SCREEN_MARGIN);
        }
        clampStage();
    }

    public void draw(MugenDrawer md, float cameraX) {
        ImageContainer sprite = anim.getCurrentImage();
        if (sprite == null) {
            return;
        }
        float[] pos = GwtFightHitbox.spriteDrawPos(anim, x, y, facing, cameraX, zoffset);
        boolean flipH = pos[2] > 0.5f;
        int shakeX = 0;
        if (hitPhase == HitPhase.SHAKE && hitTimer > 0) {
            shakeX = (hitTimer & 1) == 0 ? 1 : -1;
        }
        md.draw(new DrawProperties(pos[0] + shakeX, pos[1], flipH, false, sprite));
    }

    /**
     * Dark flattened shadow under the fighter (desktop {@code SpriteShadowRender} behind mode).
     * Drawn before the player sprite.
     */
    public void drawShadow(MugenDrawer md, float cameraX) {
        if (shadowIntensity <= 0) {
            return;
        }
        ImageContainer sprite = anim.getCurrentImage();
        if (sprite == null) {
            return;
        }
        float[] pos = GwtFightHitbox.spriteDrawPos(anim, x, y, facing, cameraX, zoffset);
        boolean flipH = pos[2] > 0.5f;
        // Behind-mode: flip vertically onto the floor line.
        boolean flipV = anim.isMirrorV() ^ true;
        float shadowY = zoffset - (y * 0.1f);
        DrawProperties dp = new DrawProperties(pos[0], shadowY, flipH, flipV, sprite);
        dp.setXScaleFactor(1f);
        dp.setYScaleFactor(shadowYScale);
        dp.setAlpha(1f);
        PalFxSub fx = new PalFxSub();
        // Darken like desktop SpriteShadowRender; scale mul alpha by stage intensity.
        float mulA = 40f * (shadowIntensity / 128f);
        if (mulA > 80f) {
            mulA = 80f;
        }
        fx.setMul(new RGB(0, 0, 0, mulA));
        fx.setAdd(new RGB(255, 255, 255, 255f));
        fx.setColor(0);
        dp.setPalfx(fx);
        if (dp.getYTopDst() < zoffset) {
            return;
        }
        md.draw(dp);
    }

    /** Draw clsn2 (green) / origin cross (magenta) like desktop {@code CnsRender}. */
    public void drawDebugCns(MugenDrawer md, float cameraX) {
        float[] origin = GwtFightHitbox.axisOrigin(anim, x, y, facing);
        int ox = 160 + (int) origin[0] + (int) cameraX;
        int oy = zoffset + (int) origin[1];
        md.setColor(1f, 0f, 1f, 1f);
        md.drawLine(ox - 5, oy, ox + 5, oy);
        md.drawLine(ox, oy - 5, ox, oy + 5);
        md.setColor(0f, 1f, 0f, 1f);
        drawAxisBoxes(md, anim.getHurtBoxes(), origin[0], origin[1], facing, cameraX);
    }

    /** Draw clsn1 attack boxes (red). */
    public void drawDebugAttackCns(MugenDrawer md, float cameraX) {
        float[] origin = GwtFightHitbox.axisOrigin(anim, x, y, facing);
        md.setColor(1f, 0f, 0f, 1f);
        drawAxisBoxes(md, anim.getAttackBoxes(), origin[0], origin[1], facing, cameraX);
    }

    private void drawAxisBoxes(
            MugenDrawer md,
            org.lee.mugen.object.Rectangle[] boxes,
            float axisX,
            float axisY,
            int face,
            float cameraX) {
        if (boxes == null) {
            return;
        }
        for (org.lee.mugen.object.Rectangle r : boxes) {
            if (r == null) {
                continue;
            }
            float[] b = GwtFightHitbox.toAxisBox(r, axisX, axisY, face);
            int x1 = 160 + (int) b[0] + (int) cameraX;
            int y1 = zoffset + (int) b[1];
            int x2 = 160 + (int) b[2] + (int) cameraX;
            int y2 = zoffset + (int) b[3];
            md.drawRect(x1, y1, x2 - x1, y2 - y1);
        }
    }

    public float getWorldX() {
        return x;
    }

    public void addWorldX(float dx) {
        x += dx;
        clampStage();
    }

    public String debugSummary() {
        return "P"
                + cmdId
                + " life="
                + life
                + " pow="
                + power
                + " act="
                + currentAction
                + " pos="
                + (int) x
                + ","
                + (int) y
                + " face="
                + facing
                + " ctrl="
                + ctrlEnabled;
    }

    /** Body push volume: clsn2 union, or default width (Mugen-like). */
    GwtFightPush.PushBox getPushBox() {
        return GwtFightPush.buildPushBox(
                x, y, facing, onGround, anim.getHurtBoxes(), anim.getXOffset(), anim.getYOffset());
    }

    public float getScreenX(float cameraX) {
        return 160 + x + cameraX;
    }

    public void resetForRound(int startX, int startY, int life) {
        this.x = startX;
        this.y = startY;
        this.groundY = startY;
        this.life = life;
        this.power = 0;
        this.vy = 0;
        this.vx = 0;
        this.onGround = true;
        this.inAttack = false;
        this.attackHit = false;
        this.attackHitsDone = 0;
        this.lastHitAnimElem = 0;
        this.pendingSpecial = null;
        this.hitPhase = HitPhase.NONE;
        this.hitTimer = 0;
        this.hitSlideVx = 0;
        this.hitSlideTime = 0;
        this.hitPause = 0;
        this.jumpPhase = JumpPhase.NONE;
        this.jumpDir = 0;
        this.running = false;
        this.turning = false;
        this.prevPunch = false;
        this.prevKick = false;
        this.prevStrongPunch = false;
        this.prevStrongKick = false;
        this.prevUp = false;
        this.prevToward = false;
        this.towardTapTimer = 0;
        this.towardTapArmed = false;
        this.towardSawRelease = false;
        this.turnCooldown = 0;
        this.currentAction = ACT_STAND;
        anim.setAction(ACT_STAND);
    }
}
