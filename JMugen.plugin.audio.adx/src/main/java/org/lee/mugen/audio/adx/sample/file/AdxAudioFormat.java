package org.lee.mugen.audio.adx.sample.file;

import java.util.Map;

import javax.sound.sampled.AudioFormat;

import org.tritonus.share.sampled.TAudioFormat;

public class AdxAudioFormat extends TAudioFormat {
	public AdxAudioFormat(AudioFormat.Encoding encoding, float nFrequency,
			int SampleSizeInBits, int nChannels, int FrameSize,
			float FrameRate, boolean isBigEndian, Map properties) {
		super(encoding, nFrequency, SampleSizeInBits, nChannels, FrameSize,
				FrameRate, isBigEndian, properties);
	}
	
	public Map properties()
	{
		return super.properties();	
	}	
}
