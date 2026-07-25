package org.lee.mugen.snd;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class GroupSnd implements Serializable {
	private int grpNum;
	private Map<Integer, byte[]> _soundMap = new HashMap<Integer, byte[]>();

	public GroupSnd(int grp) {
		grpNum = grp;
	}
	public Collection<byte[]> getSounds() {
		return _soundMap.values();
	}

	public byte[] getSound(int key) {
		return _soundMap.get(key);
	}

	public void addSound(int key, byte[] soundData) {
		_soundMap.put(key, soundData);
	}

	public int getGrpNum() {
		return grpNum;
	}

	public void setGrpNum(int grpNum) {
		this.grpNum = grpNum;
	}

}
