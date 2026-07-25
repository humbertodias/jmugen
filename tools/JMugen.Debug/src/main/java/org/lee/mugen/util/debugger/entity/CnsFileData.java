package org.lee.mugen.util.debugger.entity;

import java.io.File;
import java.util.List;

public class CnsFileData {
	private File file;
	private List<StatedefData> statedefs;
	
	public CnsFileData(File file) {
		this.file = file;
	}
	
}
