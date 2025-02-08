package org.lee.mugen.audio.adx.sample.convert;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;


public class AdxDecoder {
	
	public static void main(String[] args) throws IOException {
		Adx adx = new Adx(new File("E:/dev/workspace/JMugen.plugin.audio.adx/ADX_S060.BIN"));
		new AdxDecoder(adx);
	}
	public AdxDecoder(Adx adx) {
		this.adx = adx;
	}
	Adx adx;
	boolean loopingEnabled = true;
	public byte[] read(int samples_needed) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		adx.convert(bos, samples_needed);
		return bos.toByteArray();
		
	}
	public void close() throws IOException {
		adx.close();
	}

}
