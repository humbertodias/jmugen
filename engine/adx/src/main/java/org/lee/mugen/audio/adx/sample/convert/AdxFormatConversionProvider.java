package org.lee.mugen.audio.adx.sample.convert;

import java.util.Arrays;
import java.util.Collection;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;

import org.tritonus.share.TDebug;
import org.tritonus.share.sampled.Encodings;
import org.tritonus.share.sampled.convert.TEncodingFormatConversionProvider;

public class AdxFormatConversionProvider extends TEncodingFormatConversionProvider {
	private static final AudioFormat.Encoding	ADX = Encodings.getEncoding("ADX");
	private static final AudioFormat.Encoding	PCM_SIGNED = Encodings.getEncoding("PCM_SIGNED");

	private static final AudioFormat[]	INPUT_FORMATS =
	{
		// mono
		new AudioFormat(ADX, -1.0F, -1, 1, -1, -1.0F, false),
		new AudioFormat(ADX, -1.0F, -1, 1, -1, -1.0F, true),
		// stereo
		new AudioFormat(ADX, -1.0F, -1, 2, -1, -1.0F, false),
		new AudioFormat(ADX, -1.0F, -1, 2, -1, -1.0F, true),
	};


	private static final AudioFormat[]	OUTPUT_FORMATS =
	{
		// mono, 16 bit signed
		new AudioFormat(PCM_SIGNED, -1.0F, 16, 1, 2, -1.0F, false),
		new AudioFormat(PCM_SIGNED, -1.0F, 16, 1, 2, -1.0F, true),
		// stereo, 16 bit signed
		new AudioFormat(PCM_SIGNED, -1.0F, 16, 2, 4, -1.0F, false),
		new AudioFormat(PCM_SIGNED, -1.0F, 16, 2, 4, -1.0F, true),
	};
	protected AdxFormatConversionProvider() {
		super(Arrays.asList(INPUT_FORMATS), Arrays.asList(OUTPUT_FORMATS));
		if (TDebug.TraceAudioConverter) 
		{
			TDebug.out(">MpegFormatConversionProvider()");
		}
	}

	@Override
	public AudioInputStream getAudioInputStream(AudioFormat targetFormat,
			AudioInputStream sourceStream) {
		// TODO Auto-generated method stub
		return null;
	}

}
