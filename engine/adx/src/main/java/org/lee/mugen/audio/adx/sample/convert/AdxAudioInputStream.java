package org.lee.mugen.audio.adx.sample.convert;

import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;

public class AdxAudioInputStream extends AudioInputStream {

	public AdxAudioInputStream(InputStream stream, AudioFormat format,
			long length) {
		super(stream, format, length);
		// TODO Auto-generated constructor stub
	}
}
