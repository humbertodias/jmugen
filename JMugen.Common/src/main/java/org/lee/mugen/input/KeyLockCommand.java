package org.lee.mugen.input;

import java.util.concurrent.atomic.AtomicReference;


/**
 * When a key is press, this key is lock until the release command for this key is fire
 * @author Dr Wong
 *
 */
public class KeyLockCommand {
	/**
	 * NONE : KeyLockCommand haven't state
	 * LOCK : The Key is lock until it is release
	 * HIDDEN : Key is hidden by another it is only applicable for direction B <=> BD : B is hidden
	 *        Why do this :
	 *            if you have a command like Guile's in street fighter "sonic boom" attack, you want to know how long 
	 *            B is hold before release and then F and PUNCH, and this independly of command BD or BU, that why we have to 
	 *            hold B hidden with time
	 *
	 */
	public static enum State {
		NONE, LOCK, HIDDEN;
	}
	private Key key;
	private long pressTick;
	private long releasedTick;
	private boolean isPress;
	private AtomicReference<State> token = new AtomicReference<State>(State.NONE);

	private AbstractCommand cmd;

	public boolean isHold(long ticks) {
		return isPress && pressTick < ticks;

	}
	public KeyLockCommand(Key key) {
		this.key = key;
	}

	
	public State getTokenTaken() {
		return token.get();
	}
	
	public void setTokenTaken(State state) {
		token.set(state);
	}
	
	public long getPressTick() {
		return pressTick;
	}
	public void unHidden() {
		if (token.get() != State.HIDDEN) return;
//			throw new IllegalStateException("KeyLock can't be unhidden if there are no hidden before");
		token.set(State.LOCK);
	}

	public void setHidden() {
		if (token.get() != State.LOCK) return;
//			throw new IllegalStateException("KeyLock can't be hidden if there are no lock before");
		token.set(State.HIDDEN);
	}
	public void setPressTick(long pressTick) {
		setTokenTaken(State.LOCK);
		this.pressTick = pressTick;
		setPress();
		cmd = new PressCommand(key.bit, pressTick);
	}

	public long getReleasedTick() {
		return releasedTick;
	}

	public void setReleasedTick(long releasedTick) {
		setTokenTaken(State.NONE);
		setRelease();
		
		this.releasedTick = releasedTick;
		long pressTick = cmd.getTick();
		long hold = releasedTick - pressTick;
		cmd = new ReleaseCommand(key.bit, releasedTick, hold);
	}

	public Key getKey() {
		return key;
	}
	public boolean isPress() {
		return isPress;
	}
	private void setPress() {
		this.isPress = true;
	}
	private void setRelease() {
		this.isPress = false;
	}
	public AbstractCommand getCmd() {
		return cmd;
	}
	public boolean isLock() {
		return token.get() == State.LOCK;
	}
	public boolean isHidden() {
		return token.get() == State.HIDDEN;
	}
	public String toString() {
		if (token.get() == State.NONE)
			return "";
		return key.desc + " is " + (!isPress? "Released at " + releasedTick: "Pressed at " + pressTick);
	}
	
}
