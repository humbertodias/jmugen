package org.lee.mugen.audio.adx.sample.file;

import javax.sound.sampled.AudioFileFormat;

public class AdxFileFormatType extends	AudioFileFormat.Type {

	public static final AudioFileFormat.Type	ADX = new AdxFileFormatType("ADX", "adx");
	
	public AdxFileFormatType(String strName, String strExtension) {
		super(strName, strExtension);
	}

}
