package org.lee.mugen.input;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.lee.mugen.input.KeyLockCommand.State;

/**
 * A Command dispatcher to sprite(s) that it hold
 * 
 * TODO : the static bloc is tempory and when configuration is done, 
 * insert keys controlers has to be done by a method
 * 
 * @author Dr Wong
 *
 */
public class CmdProcDispatcher {
	private static Map<String, CmdProcDispatcher> spriteDispatcherMap = new HashMap<String, CmdProcDispatcher>();
	

		

	
	public static final int bufferTime = 50;
	private EnumMap<Key, KeyLockCommand> keyLockFactory;
	
	private Map<Integer, Key> keyCodeToKeyMap;// = new HashMap<Integer, Key>();

	private List<SingleCmdProcessor> mugenKeyEvents = Collections.synchronizedList(new LinkedList<SingleCmdProcessor>());

	public void clear() {
		mugenKeyEvents.clear();
	}
	
//	private Sprite sprite;
	static final Map<Key, Key> inverseMap = new HashMap<Key, Key>();
	static { 
		inverseMap.put(Key.B, Key.F);
		inverseMap.put(Key.F, Key.B);
		
		inverseMap.put(Key.DB, Key.DF);
		inverseMap.put(Key.UB, Key.UF);
		inverseMap.put(Key.DF, Key.DB);
		inverseMap.put(Key.UF, Key.UB);
	}

	final int up;
	final int down; 
	final int back; 
	final int forward; 
	final int a;
	final int b;
	final int c;
	final int x;
	final int y; 
	final int z;
	final int abc; 
	final int xyz;
	
	
	public int getUp() {
		return up;
	}

	public int getDown() {
		return down;
	}

	public int getBack() {
		return back;
	}

	public int getA() {
		return a;
	}

	public int getB() {
		return b;
	}

	public int getC() {
		return c;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}

	public int getAbc() {
		return abc;
	}

	public int getXyz() {
		return xyz;
	}

	public CmdProcDispatcher(
			final int up, 
			final int down, 
			final int back, 
			final int forward, 
			int a, int b, int c, int x, int y, int z,
			int abc, int xyz) {
//		this.sprite = pSprite;

		this.up = up;
		this.down = down; 
		this.back = back; 
		this.forward = forward; 
		this.a = a;
		this.b = b;
		this.c = c;
		this.x = x;
		this.y = y; 
		this.z = z;
		this.abc = abc; 
		this.xyz = xyz;
		
		keyCodeToKeyMap = new HashMap<Integer, Key>();
		keyCodeToKeyMap.put(up, Key.U);
		keyCodeToKeyMap.put(down, Key.D);
		keyCodeToKeyMap.put(back, Key.B);
		keyCodeToKeyMap.put(forward, Key.F);
		

		
		keyLockFactory = 
			new EnumMap<Key, KeyLockCommand>(Key.class);

		keyCodeToKeyMap.put(a, Key.a);
		keyCodeToKeyMap.put(b, Key.b);
		keyCodeToKeyMap.put(c, Key.c);
		keyCodeToKeyMap.put(x, Key.x);
		keyCodeToKeyMap.put(y, Key.y);
		keyCodeToKeyMap.put(z, Key.z);
		

		keyLockFactory.put(Key.U, new KeyLockCommand(Key.U));
		keyLockFactory.put(Key.D, new KeyLockCommand(Key.D));
		keyLockFactory.put(Key.B, new KeyLockCommand(Key.B));
		keyLockFactory.put(Key.F, new KeyLockCommand(Key.F));
		
		keyLockFactory.put(Key.DB, new KeyLockCommand(Key.DB));
		keyLockFactory.put(Key.UB, new KeyLockCommand(Key.UB));
		keyLockFactory.put(Key.DF, new KeyLockCommand(Key.DF));
		keyLockFactory.put(Key.UF, new KeyLockCommand(Key.UF));

		
		keyLockFactory.put(Key.a, new KeyLockCommand(Key.a));
		keyLockFactory.put(Key.b, new KeyLockCommand(Key.b));
		keyLockFactory.put(Key.c, new KeyLockCommand(Key.c));
		keyLockFactory.put(Key.x, new KeyLockCommand(Key.x));
		keyLockFactory.put(Key.y, new KeyLockCommand(Key.y));
		keyLockFactory.put(Key.z, new KeyLockCommand(Key.z));

	}

	/* (non-Javadoc)
	 * @see org.lee.mugen.core.command.ICmdProcDispatcher#setKey(int, org.lee.mugen.core.command.Key)
	 */
	public void setKey(int keyCode, Key key) {
		keyCodeToKeyMap.put(keyCode, key);
	}

	public boolean diseable;
	
	
	
	private KeyLockCommand getDirKeyLocked() {
		KeyLockCommand result = null;
		if ((result = keyLockFactory.get(Key.B)).isLock()) {
			return result;
		}
		if ((result = keyLockFactory.get(Key.DB)).isLock()) {
			return result;
		}
		if ((result = keyLockFactory.get(Key.D)).isLock()) {
			return result;
		}
		if ((result = keyLockFactory.get(Key.DF)).isLock()) {
			return result;
		}
		if ((result = keyLockFactory.get(Key.F)).isLock()) {
			return result;
		}
		if ((result = keyLockFactory.get(Key.UF)).isLock()) {
			return result;
		}
		if ((result = keyLockFactory.get(Key.U)).isLock()) {
			return result;
		}
		if ((result = keyLockFactory.get(Key.UB)).isLock()) {
			return result;
		}
		return null;
	}
	
	private List<KeyLockCommand> getDirKeyHidden() {
		List<KeyLockCommand> list = new ArrayList<KeyLockCommand>();
		KeyLockCommand result = null;
		if ((result = keyLockFactory.get(Key.B)).isHidden()) {
			list.add(result);
		}
		if ((result = keyLockFactory.get(Key.DB)).isHidden()) {
			list.add(result);
		}
		if ((result = keyLockFactory.get(Key.D)).isHidden()) {
			list.add(result);
		}
		if ((result = keyLockFactory.get(Key.DF)).isHidden()) {
			list.add(result);
		}
		if ((result = keyLockFactory.get(Key.F)).isHidden()) {
			list.add(result);
		}
		if ((result = keyLockFactory.get(Key.UF)).isHidden()) {
			list.add(result);
		}
		if ((result = keyLockFactory.get(Key.U)).isHidden()) {
			list.add(result);
		}
		if ((result = keyLockFactory.get(Key.UB)).isHidden()) {
			list.add(result);
		}
		return list;
	}
	private boolean isCombinedDirKey(Key key) {
		return key != Key.U && key != Key.D && key != Key.B && key != Key.F;
	}
	
	
	private void releaseDir(int keyCode, long tick, boolean isFlip) {
		if (diseable)
			return;
		Key key = keyCodeToKeyMap.get(keyCode);
		releaseDir(key, tick);
	}
	
	private void releaseDir(Key key, long tick) {
		if (diseable)
			return;
		if (key == null)
			return;
		if ((key.bit & (Key.U.bit | Key.D.bit | Key.B.bit | Key.F.bit)) != key.bit)
			return;

		KeyLockCommand newKlc = keyLockFactory.get(key);
		if (!newKlc.isLock() && !newKlc.isHidden())
			return;
		KeyLockCommand klc = getDirKeyLocked();
		if (klc == null)
			return;
		if (isCombinedDirKey(klc.getKey())) {
			klc.setReleasedTick(tick);
			newKlc.unHidden();
			newKlc.setReleasedTick(tick);
			
			KeyProc keyProcNew = new KeyProc(newKlc.getCmd(), newKlc.getTokenTaken());
			KeyProc keyProcKlc = new KeyProc(klc.getCmd(), klc.getTokenTaken());
			
			//
			List<KeyProc> lst = new ArrayList<KeyProc>();
			lst.add(keyProcNew);
			lst.add(keyProcKlc);
			
			for (KeyLockCommand k : getDirKeyHidden()) {
				k.unHidden();
				lst.add(new KeyProc(k.getCmd(), k.getTokenTaken()));
			}
			
			
			SingleCmdProcessor scp = new SingleCmdProcessor(tick, lst.toArray(new KeyProc[lst.size()]));
			mugenKeyEvents.add(scp);
		} else {
			newKlc.setReleasedTick(tick);
			
			KeyProc keyProcNew = new KeyProc(newKlc.getCmd(), newKlc.getTokenTaken());
			SingleCmdProcessor scp = new SingleCmdProcessor(tick, keyProcNew);
			
			mugenKeyEvents.add(scp);
		}
	}
	
	private void pressDir(Key key, long tick) {
		if (diseable)
			return;
		if (key == null)
			return;
		if ((key.bit & (Key.U.bit | Key.D.bit | Key.B.bit | Key.F.bit)) != key.bit)
			return;

		if ((key.bit & Key.U.bit) == Key.U.bit) {
			releaseDir(Key.D, tick);
		}
		if ((key.bit & Key.D.bit) == Key.D.bit) {
			releaseDir(Key.U, tick);
		}
		if ((key.bit & Key.F.bit) == Key.F.bit) {
			releaseDir(Key.B, tick);
		}
		if ((key.bit & Key.B.bit) == Key.B.bit) {
			releaseDir(Key.F, tick);
		}

		KeyLockCommand newKlc = keyLockFactory.get(key);
		if (newKlc.isLock()) {
			return;
		}
		if (newKlc.isHidden()) {
			return;
		}
		KeyLockCommand klc = getDirKeyLocked();
		if (klc == null) {
			newKlc.setPressTick(tick);
			KeyProc keyProcNew = new KeyProc(newKlc.getCmd(), newKlc.getTokenTaken());
//				KeyProc keyProcKlc = new KeyProc(klc.getCmd(), klc.getTokenTaken());
			SingleCmdProcessor scp = new SingleCmdProcessor(tick, keyProcNew);

			mugenKeyEvents.add(scp);
			return;
		} else {
			if (isCombinedDirKey(key)) {

			} else {
				Key combinedKey = null;
				int moveDirection = klc.getKey().bit | newKlc.getKey().bit;
				if (moveDirection == Key.DB.bit)
					combinedKey = Key.DB;
				else if (moveDirection == Key.UB.bit)
					combinedKey = Key.UB;
				else if (moveDirection == Key.UF.bit)
					combinedKey = Key.UF;
				else if (moveDirection == Key.DF.bit)
					combinedKey = Key.DF;
				else {
					if (isCombinedDirKey(klc.getKey()))
						return;
					newKlc.setPressTick(tick);
					KeyProc keyProcNew = new KeyProc(newKlc.getCmd(), newKlc.getTokenTaken());
					SingleCmdProcessor scp = new SingleCmdProcessor(tick, keyProcNew);

					mugenKeyEvents.add(scp);
					return;
				}
				KeyLockCommand combinedKeyLock = keyLockFactory
						.get(combinedKey);
				if (combinedKeyLock.isLock())
					return;
				newKlc.setPressTick(tick);
				newKlc.setHidden();
//					klc.setPressTick(tick);
				klc.setHidden();
				combinedKeyLock.setPressTick(tick);

				KeyProc keyProcKlc = new KeyProc(klc.getCmd(), klc.getTokenTaken());
				KeyProc keyProcNew = new KeyProc(newKlc.getCmd(), newKlc.getTokenTaken());
				KeyProc keyProcCombinedKeyLock = new KeyProc(combinedKeyLock.getCmd(), combinedKeyLock.getTokenTaken());
				SingleCmdProcessor scp = new SingleCmdProcessor(tick, keyProcKlc, keyProcNew, keyProcCombinedKeyLock);

				mugenKeyEvents.add(scp);
			}
		}
	}

	private void pressDir(int keyCode, long tick, boolean isFlip) {
		if (diseable)
			return;
//		if (isFlip) {
//			if (keyCode == back)
//				keyCode = forward;
//			else if (keyCode == forward)
//				keyCode = back;
//		}
		Key key = keyCodeToKeyMap.get(keyCode);
		pressDir(key, tick);
	}

	/* (non-Javadoc)
	 * @see org.lee.mugen.core.command.ICmdProcDispatcher#press(int, long, boolean)
	 */
	public boolean press(int keyCode, long tick, boolean isFlip) {
		pressDir(keyCode, tick, isFlip);
		if (abc == keyCode) {
			press(this.a, tick, isFlip);
			press(this.b, tick, isFlip);
//			press(this.c, tick, isFlip);
			return true;
		}
		if (xyz == keyCode) {
			press(this.x, tick, isFlip);
			press(this.y, tick, isFlip);
//			press(this.z, tick, isFlip);
			return true;
		}
		Key key = keyCodeToKeyMap.get(keyCode);
		if (key == null)
			return true;


		if ((key.bit & (Key.U.bit | Key.D.bit | Key.B.bit | Key.F.bit)) != key.bit) {
			pressButton(key, tick);
		}
		return true;
	}

	
	private void pressButton(Key key, long tick) {
		if (diseable)
			return;
		
		if (key == null)
			return;
		if (!((key.bit & (Key.U.bit | Key.D.bit | Key.B.bit | Key.F.bit)) != key.bit))
			return;
		KeyLockCommand newKlc = keyLockFactory.get(key);
		if (newKlc.isLock()) {
			return;
		}
		if (newKlc.isHidden()) {
			return;
		}
		tick--;
		newKlc.setPressTick(tick);
		KeyProc keyProcNew = new KeyProc(newKlc.getCmd(), newKlc.getTokenTaken());
		SingleCmdProcessor scp = new SingleCmdProcessor(tick, keyProcNew);

		mugenKeyEvents.add(scp);
	}
	/* (non-Javadoc)
	 * @see org.lee.mugen.core.command.ICmdProcDispatcher#flushBufferCmd()
	 */
	public void flushBufferCmd() {
		mugenKeyEvents.clear();
	}
	
	/* (non-Javadoc)
	 * @see org.lee.mugen.core.command.ICmdProcDispatcher#release(int, long, boolean)
	 */
	public boolean release(int keyCode, long tick, boolean isFlip) {
		tick--;tick--; // du to a lack of latency ??? Java input ???
		releaseDir(keyCode, tick, isFlip);
		if (abc == keyCode) {
			release(this.a, tick, isFlip);
			release(this.b, tick, isFlip);
			release(this.c, tick, isFlip);
			return true;
		}
		if (xyz == keyCode) {
			release(this.x, tick, isFlip);
			release(this.y, tick, isFlip);
			release(this.z, tick, isFlip);
			return true;
		}
		Key key = keyCodeToKeyMap.get(keyCode);
		if (key == null)
			return true;;
		if ((key.bit & (Key.U.bit | Key.D.bit | Key.B.bit | Key.F.bit)) != key.bit) {
			releaseButton(key, tick);
		}
		return true;
	}

	private void releaseButton(Key key, long tick) {
		if (diseable)
			return;
		if (key == null)
			return;
		if (!((key.bit & (Key.U.bit | Key.D.bit | Key.B.bit | Key.F.bit)) != key.bit))
			return;

		KeyLockCommand newKlc = keyLockFactory.get(key);
		if (!newKlc.isLock() && !newKlc.isHidden())
			return;
		newKlc.setReleasedTick(tick);
		
		KeyProc keyProcNew = new KeyProc(newKlc.getCmd(), newKlc.getTokenTaken());
		SingleCmdProcessor scp = new SingleCmdProcessor(tick, keyProcNew);
		
		mugenKeyEvents.add(scp);
	}
	/* (non-Javadoc)
	 * @see org.lee.mugen.core.command.ICmdProcDispatcher#getSequence(long)
	 */
	public SingleCmdProcessor[] getSequence(long gameTime, long timeBufferToSearch) {
		long time = gameTime - timeBufferToSearch;
		List<SingleCmdProcessor> result = new LinkedList<SingleCmdProcessor>();
		List<KeyLockCommand> klcs = new LinkedList<KeyLockCommand>();

		for (KeyLockCommand klc : keyLockFactory.values()) {
			if (klc.isHold(time) && (klc.isLock() || klc.isHidden())) {
				klcs.add(klc);
			}
		}
		Collections.sort(klcs, new Comparator<KeyLockCommand>() {
			public int compare(KeyLockCommand o1, KeyLockCommand o2) {
				return (int) (o1.getPressTick() - o2.getPressTick());
			}
		});

		for (KeyLockCommand klc : klcs) {
			AbstractCommand sc = new PressCommand(klc.getKey().bit, klc
					.getPressTick());
			KeyProc kp = new KeyProc(sc, klc.getTokenTaken());
			result.add(new SingleCmdProcessor(klc
					.getPressTick(), kp));
		}

//		for (Iterator<SingleCmdProcessor> iter = mugenKeyEvents.iterator(); iter.hasNext();) {
		List<SingleCmdProcessor> remove = new ArrayList<SingleCmdProcessor>();
		for (SingleCmdProcessor sc: mugenKeyEvents.toArray(new SingleCmdProcessor[0])) {
//			SingleCmdProcessor sc = iter.next();
			if (sc.getTick() >= time) {
				result.add(sc);
			} 
			if (sc.getTick() < gameTime - bufferTime) {
//				iter.remove();
				remove.add(sc);
			}
		}
		mugenKeyEvents.removeAll(remove);
		return result.toArray(new SingleCmdProcessor[result.size()]);
	}
	/* (non-Javadoc)
	 * @see org.lee.mugen.core.command.ICmdProcDispatcher#invert()
	 */
	public void invert(long gameTime) {
		Map<Key, KeyLockCommand> keyToRevertMap = new HashMap<Key, KeyLockCommand>();
		long time = gameTime;
		
		for (Key key: keyLockFactory.keySet()) {
			KeyLockCommand klc = keyLockFactory.get(key);

			if ((klc.isHidden() || klc.isLock()) && 
					(((key.bit & Key.F.bit) == Key.F.bit) || ((key.bit & Key.B.bit) == Key.B.bit))) {
				Key inverse = inverseMap.get(key);
				
				KeyLockCommand newKlc = new KeyLockCommand(inverse);
				newKlc.setPressTick(time);
				if (klc.isHidden())
					newKlc.setHidden();
				keyToRevertMap.put(inverse, newKlc);
				klc.setTokenTaken(State.NONE);
			}
		}
		
		for (Key key: keyToRevertMap.keySet()) {
			KeyLockCommand klc = keyToRevertMap.get(key);
			keyLockFactory.put(key, klc);
			
			AbstractCommand sc = klc.getCmd();
			KeyProc kp = new KeyProc(sc, klc.getTokenTaken());
			mugenKeyEvents.add(new SingleCmdProcessor(time, kp));
		}
	}
	
	int[] keys = null;
	/* (non-Javadoc)
	 * @see org.lee.mugen.core.command.ICmdProcDispatcher#getKeys()
	 */
	public int[] getKeys() {
		if (keys == null) {
			keys = new int[] {
					this.up,
					this.down,
					this.back,
					this.forward,
					this.a,
					this.b,
					this.c,
					this.x,
					this.y,
					this.z,
					this.abc,
					this.xyz

			};
		}
		return keys; 
	}
	public int getForward() {
		return forward;
	}

	public static Map<String, CmdProcDispatcher> getSpriteDispatcherMap() {
		return spriteDispatcherMap;
	}


}
