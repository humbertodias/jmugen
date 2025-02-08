package org.lee.mugen.snd;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Snd implements Serializable {
	private Map<Integer, GroupSnd> _grpSoundMap = new HashMap<Integer, GroupSnd>();

	public GroupSnd getGroup(int key) {
		return _grpSoundMap.get(key);
	}
	public Collection<GroupSnd> getGroups() {
		return _grpSoundMap.values();
	}

	public void addGroup(int key, GroupSnd grp) {
		_grpSoundMap.put(key, grp);
	}
	private int count = 0;
	public void addSound(int grp, int sample, byte[] data) {
		GroupSnd groupSnd = getGroup(grp);
		if (groupSnd == null) {
			groupSnd = new GroupSnd(grp);
			addGroup(grp, groupSnd);
		}
		groupSnd.addSound(sample, data);
		count++;
	}

	public int getCount() {
		return count;
	}


}
