package org.lee.mugen.audio.adx.sample.file;

import java.util.Map;

import javax.sound.sampled.AudioFormat;

import org.tritonus.share.sampled.file.TAudioFileFormat;

public class AdxAudioFileFormat extends TAudioFileFormat {

	public AdxAudioFileFormat(Type type, AudioFormat audioFormat,
			int lengthInFrames, int lengthInBytes,
			Map<String, Object> properties) {
		super(type, audioFormat, lengthInFrames, lengthInBytes, properties);
	}

}
