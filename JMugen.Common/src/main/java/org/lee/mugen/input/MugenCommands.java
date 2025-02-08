package org.lee.mugen.input;

import java.io.Serializable;

import org.lee.mugen.input.AbstractCommand.Kind;
import org.lee.mugen.input.KeyLockCommand.State;
import org.lee.mugen.input.MugenSingleCmd.CommandType;
import org.lee.mugen.lang.WrapInt;


/**
 * MugenCommands describes the sequence in *.cmd
 * @author Dr Wong
 *
 */
public class MugenCommands implements Serializable {
	private MugenSingleCmd[] _cmds;
	private long _time = 1;
	private String _commandName;
	

	public MugenCommands(MugenSingleCmd[] cmds, String cmdName) {
		this(cmds, cmdName, 1, 1);
	}
	public MugenCommands(MugenSingleCmd[] cmds, String cmdName, int time, int bufferTime) {
		this._cmds = cmds;
		this._time = time;
		this._bufferTime = bufferTime;
		this._commandName = cmdName;
	}
	// This command will be valid for bufferTime tick
	private long _bufferTime = 100; 

	public boolean find(CmdProcDispatcher cpd, long gameTime, boolean isFlip) {
		
		_bufferTime = 1; 
		int posCmd = 0;
		int posSeq = 0;
		SingleCmdProcessor[] seq = cpd.getSequence(gameTime, _time);
		while (posCmd < _cmds.length && posSeq < seq.length) {
			WrapInt aPosCmd = new WrapInt(posCmd);
			WrapInt aPosSeq = new WrapInt(posSeq);
			if (isMatch(_cmds, aPosCmd, seq, aPosSeq, _time, isFlip, gameTime)) {
				posCmd = aPosCmd.getValue();
				posSeq = aPosSeq.getValue();
				if (posCmd > _cmds.length - 1) {
					return true;
				}
			} else {
				posSeq++;
			}
		}
		return false;
	}

	private int isKeyInverse(int key, boolean isFlip) {
		return key;
//		if (isFlip) {
//			Key k0 = Key.getKey(key);
//			if (k0 != null) {
//				Key k = CmdProcDispatcher.inverseMap.get(k0);
//				if (k != null) {
//					return k.bit;
//				}
//				
//			}
//			return key;
//		} else {
//			return key;
//		}
	}
	
	private int incerseKey(int key, boolean isFlip) {
//		return key;
		if (isFlip) {
			Key k0 = Key.getKey(key);
			if (k0 != null) {
				Key k = CmdProcDispatcher.inverseMap.get(k0);
				if (k != null) {
					return k.bit;
				}
				
			}
			return key;
		} else {
			return key;
		}
	}
	
	private static final int SIMULTANEOUS_ACCEPT_KEY_TIME = 5;
	private boolean isMatch(
			MugenSingleCmd[] cmds, 
			WrapInt aPosCmd, 
			SingleCmdProcessor[] seqFromBuffer, 
			WrapInt aPosSeqFromBuffer, 
			long maxTimeToexec, 
			boolean isFlip,
			long gameTime) {
		boolean result = false;
		boolean isIncSeq = true;
		int posCmd = aPosCmd.getValue();
		int posSeq = aPosSeqFromBuffer.getValue();
		int typesFromCmd = cmds[posCmd].getTypes();
		int keyFromCmd = cmds[posCmd].getKeys();
		KeyProc[] keysFromBuffer = seqFromBuffer[posSeq].getKeys();

		// F, >~F, >F
		// Press F, 
		// Release and no other key than F, 
		// Press and no other key than F
		if ((typesFromCmd & CommandType.NO_OTHER_KEY_BEFORE.bit) == CommandType.NO_OTHER_KEY_BEFORE.bit) {
			if (posCmd > 0 && posSeq > 0) {
				KeyProc[] kpMenosOne = seqFromBuffer[posSeq - 1].getKeys();
				int aKey = 0;
				for (KeyProc kp: kpMenosOne) {
   					aKey |= isKeyInverse(kp.getKey().getKey(), isFlip);
   				}
				if (aKey != incerseKey(cmds[posCmd - 1].getKeys(), isFlip)) {
					result = false;
					aPosSeqFromBuffer.setValue(seqFromBuffer.length);

					return false;
				}
				
			} 
			{
				int aKey = 0;
				for (KeyProc kp: keysFromBuffer) {
   					aKey |= isKeyInverse(kp.getKey().getKey(), isFlip);
   				}

				if (incerseKey(keyFromCmd, isFlip) != aKey){
					result = false;
					aPosSeqFromBuffer.setValue(seqFromBuffer.length);
					return false;
				}
				
			}
		}
		if ((typesFromCmd & CommandType.HOLD.bit) == CommandType.HOLD.bit) {
			// Hold and direction
			// this needn't to match exactly, it needs only that a direction is hold
			// the key to match is /~D and i hold DB then this match because of the direction
			// DB has a D direction
			if ((typesFromCmd & CommandType.DIRECTION.bit) == CommandType.DIRECTION.bit) {
				
				for (KeyProc kp: keysFromBuffer) {
					if ((incerseKey(keyFromCmd, isFlip) & isKeyInverse(kp.getKey().getKey(), isFlip)) == incerseKey(keyFromCmd, isFlip)
							) {
//							&& (StateMachine.getInstance().getGameState().getGameTime() - kp.getKey().getTick() >= cmds[posCmd].getTime())){
						result = result || true;
						break;
					}
				}

			} else {
				// hold only a key
				// this must match exactly
				// /D doesn't match an hold DB
				for (KeyProc kp: keysFromBuffer) {
					if (incerseKey(keyFromCmd, isFlip) == isKeyInverse(kp.getKey().getKey(), isFlip)) {
						result = result || true;
						break;
					}
				}
			}
		} else if ((typesFromCmd & CommandType.RELEASED.bit) == CommandType.RELEASED.bit) {
			if ((typesFromCmd & CommandType.DIRECTION.bit) == CommandType.DIRECTION.bit) {
				
				for (KeyProc kp: keysFromBuffer) {
					if ((incerseKey(keyFromCmd, isFlip) & isKeyInverse(kp.getKey().getKey(), isFlip)) == incerseKey(keyFromCmd, isFlip) && kp.getKey().getKind() == Kind.RELEASE) {
//							&& (StateMachine.getInstance().getGameState().getGameTime() - kp.getKey().getTick() >= cmds[posCmd].getTime())){
						ReleaseCommand rc = (ReleaseCommand) kp.getKey();
						if (rc.getHoldTick() >= cmds[posCmd].getTime()) {
							result = result || true;
							break;					
						}
					}
					if ((incerseKey(keyFromCmd, isFlip) & isKeyInverse(kp.getKey().getKey(), isFlip)) == incerseKey(keyFromCmd, isFlip) && kp.getState() == State.HIDDEN && kp.getKey().getKind() == Kind.PRESS) {

						result = result || true;
						break;
					}
				}

			} else {
				for (KeyProc kp: keysFromBuffer) {
					if (incerseKey(keyFromCmd, isFlip) == isKeyInverse(kp.getKey().getKey(), isFlip) && kp.getKey().getKind() == Kind.RELEASE) {

						result = result || true;
						break;
					}
					if ((incerseKey(keyFromCmd, isFlip) & isKeyInverse(kp.getKey().getKey(), isFlip)) == incerseKey(keyFromCmd, isFlip) && kp.getState() == State.HIDDEN && kp.getKey().getKind() == Kind.PRESS) {
						isIncSeq = false;
						result = result || true;
						break;
					}
				}
			}
			
		} else if ((typesFromCmd & CommandType.PRESS.bit) == CommandType.PRESS.bit) {
			if ((typesFromCmd & CommandType.DIRECTION.bit) == CommandType.DIRECTION.bit) {
				for (KeyProc kp: keysFromBuffer) {
					if ((incerseKey(keyFromCmd, isFlip) & isKeyInverse(kp.getKey().getKey(), isFlip)) == incerseKey(keyFromCmd, isFlip) && kp.getKey().getKind() == Kind.PRESS) {
						//if (Math.abs(kp.getKey().getTick() - gameTime) >= 3) {
						int timeDelta = (int) Math.abs(kp.getKey().getTick() - gameTime);
						if (timeDelta <= 3) {
							result = result || true;
							break;
						}
					}
				}
			} else {
				for (KeyProc kpFromBuffer: keysFromBuffer) {
					if (incerseKey(keyFromCmd, isFlip) == kpFromBuffer.getKey().getKey() 
							&& kpFromBuffer.getState() != State.HIDDEN
							&& kpFromBuffer.getKey().getKind() == Kind.PRESS 
							&& gameTime - kpFromBuffer.getKey().getTick() <= maxTimeToexec) {

						result = result || true;
						break;
					}
				}
			}
			
		} else if ((typesFromCmd & CommandType.SIMULTANEOUS.bit) == CommandType.SIMULTANEOUS.bit) {
			
			boolean isFirst= true;
			long lastTimeKeyProcBuffer = 0;
			boolean thisResult = false;
			
//			KeyProc[] keysFromBuffer 
//			for (int tempPosSeq = posSeq; tempPosSeq < seqFromBuffer.length; ++tempPosSeq)
			int bufferedWhenEqual = 0;
			

			for (int i = posSeq; i < seqFromBuffer.length && !thisResult; ++i) {
				for (KeyProc kpFromBuffer: seqFromBuffer[i].getKeys()) {
					if (isFirst 
							&& (keyFromCmd & kpFromBuffer.getKey().getKey()) == kpFromBuffer.getKey().getKey()
							&& kpFromBuffer.getKey().getKind() == Kind.PRESS 
							&& gameTime - kpFromBuffer.getKey().getTick() <= maxTimeToexec) {

						bufferedWhenEqual |= kpFromBuffer.getKey().getKey();
						isFirst = false;
						lastTimeKeyProcBuffer = kpFromBuffer.getKey().getTick();
						
						// here we have to check screen
					} else if (!isFirst 
							&& (bufferedWhenEqual & kpFromBuffer.getKey().getKey()) == 0
							&& kpFromBuffer.getKey().getTick() - lastTimeKeyProcBuffer < SIMULTANEOUS_ACCEPT_KEY_TIME 
							&& (keyFromCmd & kpFromBuffer.getKey().getKey()) == kpFromBuffer.getKey().getKey()
//							&& kpFromBuffer.getKey().getKind() == Kind.PRESS 
							&& gameTime - kpFromBuffer.getKey().getTick() <= maxTimeToexec + SIMULTANEOUS_ACCEPT_KEY_TIME) {

						bufferedWhenEqual |= kpFromBuffer.getKey().getKey();
						if ((bufferedWhenEqual & keyFromCmd) == bufferedWhenEqual) {
							thisResult = true; 
							break;
						}
						
					} else if (!isFirst && kpFromBuffer.getKey().getTick() - lastTimeKeyProcBuffer > SIMULTANEOUS_ACCEPT_KEY_TIME) {
						break;
					}
					
				}
			}
			result = result || thisResult;
		}
		if (result) {
			posCmd++;
			if (isIncSeq)
				posSeq++;
				
				
			
		}
		aPosCmd.setValue(posCmd);
		aPosSeqFromBuffer.setValue(posSeq);
		return result;
	}
	
	
//	private boolean isMatch(MugenSingleCmd[] cmds2, int posCmd, SingleCmdProcessor[] seq, int posSeq, long time2) {
//		// TODO Auto-generated method stub
//		return false;
//	}
	public boolean find(AbstractCommand last, AbstractCommand current, int pos, MugenSingleCmd[] cmds) {
		return false;
	}

	public long getBufferTime() {
		return _bufferTime;
	}

	public void setBufferTime(long bufferTime) {
		this._bufferTime = bufferTime;
	}

	public MugenSingleCmd[] getCmds() {
		return _cmds;
	}

	public void setCmds(MugenSingleCmd[] cmds) {
		this._cmds = cmds;
	}

	public long getTime() {
		return _time;
	}

	public void setTime(long time) {
		this._time = time;
	}
	public String getCommandName() {
		return _commandName;
	}
	public void setCommandName(String commandName) {
		this._commandName = commandName;
	}
	public void addBufferTime(int i) {
		_bufferTime += i;
	}


	
}
