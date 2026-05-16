package org.lee.mugen.sprite.character;

import org.lee.mugen.sprite.cns.StateDef;

/** GWT compile stub. */
public class SpriteState {

    private StateDef currentState;
    private int prevstateno;
    private int timeInState;

    public StateDef getCurrentState() {
        return currentState;
    }

    public void changeStateDef(int stateno) {
        prevstateno = currentState == null ? -1 : currentState.getIntId();
        currentState = new StateDef(String.valueOf(stateno));
        timeInState = 0;
    }

    public int getPrevstateno() {
        return prevstateno;
    }

    public int getTimeInState() {
        return timeInState;
    }
}
