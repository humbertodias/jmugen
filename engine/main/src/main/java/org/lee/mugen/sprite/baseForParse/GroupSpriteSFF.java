package org.lee.mugen.sprite.baseForParse;

import java.io.Serializable;
import java.util.HashMap;

public class GroupSpriteSFF implements Serializable {
	
	private int _grpNum;

	public int getGrpNum() {
		return _grpNum;
	}

	private HashMap<Integer, ImageSpriteSFF> _imageMap;

	public HashMap<Integer, ImageSpriteSFF> ImgMap() {
		return _imageMap;
	}

	public GroupSpriteSFF(int grpNum) {
		_grpNum = grpNum;
		_imageMap = new HashMap<Integer, ImageSpriteSFF>();
	}

	public void add(int key, ImageSpriteSFF imgSpr) {
		if (_imageMap.containsKey(key))
			return;
		_imageMap.put(key, imgSpr);
	}

	public ImageSpriteSFF getImgSpr(int key) {
		return (ImageSpriteSFF) _imageMap.get(key);
	}
}