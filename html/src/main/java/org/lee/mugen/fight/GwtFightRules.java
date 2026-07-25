package org.lee.mugen.fight;

/**
 * Round / timer flow aligned with default {@code fight.def} [Round] timing (60 ticks/sec),
 * matching desktop {@code Round.process()} / {@code RoundRender}:
 *
 * <ul>
 *   <li>{@code start.waittime = 30} — blank before Round text
 *   <li>{@code round.default.displaytime = 60} — "Round N"
 *   <li>{@code ctrl.time = 30} — FIGHT banner, then control
 * </ul>
 */
public final class GwtFightRules {

    public static final int TICKS_PER_SECOND = 60;
    public static final int ROUND_TIME_TICKS = 99 * TICKS_PER_SECOND;
    /** fight.def {@code start.waittime} */
    public static final int START_WAIT_TICKS = 30;
    /** fight.def {@code round.default.displaytime} */
    public static final int ROUND_DISPLAY_TICKS = 60;
    /** fight.def {@code ctrl.time} — FIGHT visible, then players get control */
    public static final int CTRL_DELAY_TICKS = 30;
    public static final int KO_DISPLAY_TICKS = 90;
    public static final int WIN_WAIT_TICKS = 120;
    public static final int MAX_ROUNDS = 3;
    public static final int MAX_POWER = 3000;

    public enum Phase {
        START_WAIT,
        ROUND_SHOW,
        CTRL_DELAY,
        FIGHTING,
        KO_PAUSE,
        ROUND_END,
        MATCH_END
    }

    public Phase phase = Phase.START_WAIT;
    public int round = 1;
    public int p1Wins;
    public int p2Wins;
    public int phaseTimer = START_WAIT_TICKS;
    public int roundTimer;
    public int koTimer;
    public String roundBanner;
    public String statusBanner;
    public boolean ctrlActive;
    private GwtFightAudio audio;

    public void setAudio(GwtFightAudio audio) {
        this.audio = audio;
    }

    public void startMatch() {
        round = 1;
        p1Wins = 0;
        p2Wins = 0;
        beginRound();
    }

    public void beginRound() {
        phase = Phase.START_WAIT;
        phaseTimer = START_WAIT_TICKS;
        roundTimer = ROUND_TIME_TICKS;
        ctrlActive = false;
        statusBanner = null;
        roundBanner = null;
    }

    public void tick() {
        switch (phase) {
            case START_WAIT:
                phaseTimer--;
                if (phaseTimer <= 0) {
                    phase = Phase.ROUND_SHOW;
                    phaseTimer = ROUND_DISPLAY_TICKS;
                    roundBanner = "Round " + round;
                    if (audio != null) {
                        audio.playRound(round);
                    }
                }
                break;
            case ROUND_SHOW:
                phaseTimer--;
                if (phaseTimer <= 0) {
                    phase = Phase.CTRL_DELAY;
                    phaseTimer = CTRL_DELAY_TICKS;
                    roundBanner = null;
                    statusBanner = "FIGHT!";
                    if (audio != null) {
                        audio.playFightCall();
                    }
                }
                break;
            case CTRL_DELAY:
                phaseTimer--;
                if (phaseTimer <= 0) {
                    phase = Phase.FIGHTING;
                    ctrlActive = true;
                    statusBanner = null;
                }
                break;
            case FIGHTING:
                if (roundTimer > 0) {
                    roundTimer--;
                }
                break;
            case KO_PAUSE:
                koTimer--;
                if (koTimer <= 0) {
                    phase = Phase.ROUND_END;
                    phaseTimer = WIN_WAIT_TICKS;
                }
                break;
            case ROUND_END:
                phaseTimer--;
                if (phaseTimer <= 0) {
                    if (p1Wins >= 2 || p2Wins >= 2 || round >= MAX_ROUNDS) {
                        phase = Phase.MATCH_END;
                        statusBanner = p1Wins > p2Wins ? "P1 WINS" : p2Wins > p1Wins ? "P2 WINS" : "DRAW";
                    } else {
                        round++;
                        beginRound();
                    }
                }
                break;
            default:
                break;
        }
    }

    public void onKo(int winner) {
        if (phase != Phase.FIGHTING && phase != Phase.KO_PAUSE) {
            return;
        }
        ctrlActive = false;
        if (winner == 1) {
            p1Wins++;
            statusBanner = "K.O.";
            if (audio != null) {
                audio.playKo();
            }
        } else if (winner == 2) {
            p2Wins++;
            statusBanner = "K.O.";
            if (audio != null) {
                audio.playKo();
            }
        } else {
            statusBanner = "D.K.O.";
            if (audio != null) {
                audio.playDko();
            }
        }
        phase = Phase.KO_PAUSE;
        koTimer = KO_DISPLAY_TICKS;
    }

    public void onTimeOver(int winner) {
        ctrlActive = false;
        if (audio != null) {
            audio.playTimeOver();
        }
        if (winner == 1) {
            p1Wins++;
            statusBanner = "TIME OVER - P1";
        } else if (winner == 2) {
            p2Wins++;
            statusBanner = "TIME OVER - P2";
        } else {
            statusBanner = "TIME OVER - DRAW";
        }
        phase = Phase.ROUND_END;
        phaseTimer = WIN_WAIT_TICKS;
    }

    public boolean isMatchOver() {
        return phase == Phase.MATCH_END;
    }
}
