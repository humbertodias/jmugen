package org.lee.mugen.audio.adx.sample.file;

import javax.sound.sampled.AudioFormat;

public class AdxEncoding extends AudioFormat.Encoding {

	public static final AudioFormat.Encoding	ADXV3 = new AdxEncoding("ADXV3");
	public static final AudioFormat.Encoding	ADXV4 = new AdxEncoding("ADXV4");

	public AdxEncoding(String name) {
		super(name);
	}

}
