package org.lee.mugen.audio.adx.sample.file;

import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.tritonus.share.sampled.file.TAudioFileReader;

public class AdxAudioFileReader extends TAudioFileReader {
	private final AudioFormat.Encoding[]	sm_aEncodings =
	{
		AdxEncoding.ADXV3, AdxEncoding.ADXV4
	};
	protected AdxAudioFileReader(int markLimit, boolean rereading) {
		super(markLimit, rereading);
	}

	protected AdxAudioFileReader(int markLimit) {
		super(markLimit);
	}

	@Override
	protected AudioFileFormat getAudioFileFormat(InputStream inputStream,
			long fileLengthInBytes) throws UnsupportedAudioFileException,
			IOException {
		// TODO Auto-generated method stub
		return null;
	}

}
