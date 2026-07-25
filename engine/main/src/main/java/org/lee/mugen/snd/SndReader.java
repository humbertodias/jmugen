package org.lee.mugen.snd;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import org.lee.mugen.io.MugenDataStreams;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicBoolean;

import org.lee.mugen.core.JMugenConstant;
import org.lee.mugen.core.sound.SoundSystem;
import org.lee.mugen.io.IOUtils;
import org.lee.mugen.io.MugenDataStreams;
import org.lee.mugen.io.LittleEndianDataInputStream;

public class SndReader {
	
	private static void seek(ByteArrayInputStream fs, long skip) throws IOException {
    	fs.reset();
    	fs.skip(skip);
    }
	
	public static Snd parse(String filename) throws IOException {
		BufferedInputStream bis = new BufferedInputStream(MugenDataStreams.openBinary(filename));
		return parse(bis);
	}

	
	public static Snd parse(InputStream in) throws IOException {

    	byte[] fileData = IOUtils.toByteArray(in);
    	if (fileData.length < 512) {
    		throw new IOException("SND file too small: " + fileData.length);
    	}
    	byte[] sndSig = new byte[] {'E', 'l', 'e', 'c', 'b', 'y', 't', 'e', 'S', 'n', 'd', 0};
    	for (int i = 0; i < sndSig.length; i++) {
    		if (fileData[i] != sndSig[i]) {
    			throw new IOException("Not a Mugen SND (bad signature, " + fileData.length + " bytes)");
    		}
    	}
    	ByteArrayInputStream fs = new ByteArrayInputStream(fileData);
    	LittleEndianDataInputStream dis = new LittleEndianDataInputStream(fs);
    	
    	Snd snd = new Snd();
    	final int fileLength = fileData.length;
    	// read header
    	
    	// Electbyte signature 12
    	byte[] temp = new byte[12];
    	dis.read(temp);
    	
    	// Veri hi lo 4
    	dis.read(temp = new byte[4]);
    	
    	// number of sound
    	int soundCount = dis.readInt();
    	
    	// first subfile 4
    	long offset = dis.readInt();
    	
    	// Comment 488
    	dis.read(temp = new byte[488]);
    	temp = null;
    	
    	// do subfiles
    	int count = 0;
    	while (offset > 0) {
    		count++;
    		if (count > soundCount)
    			break;
    		seek(fs, offset);
    		offset = dis.readInt();
    		if (offset == -1)
    			break;
    		final int length = dis.readInt();
    		final int grpNumber = dis.readInt();
    		final int sampleNumber = dis.readInt();
    		IOUtils.checkChunkSize(length, fileLength, "SND sample");

    		byte[] data = new byte[length];
    		dis.read(data);
    		snd.addSound(grpNumber, sampleNumber, data);
    	}
    	
//    	byte[] data = snd.getGroup(0).getSound(0);

    	return snd;
	}
	
	public static void main(String[] args) throws IOException {
		SoundSystem.SoundBackGround.playMusic(JMugenConstant.RESOURCE + "sound/intro.mp3");
		Snd snd = SndReader.parse(JMugenConstant.RESOURCE + "chars/kfm/kfm.snd");
		for (GroupSnd grp: snd.getGroups())
			for (byte[] bytes: grp.getSounds())
				SoundSystem.Sfx.playSnd(bytes, new AtomicBoolean(false));
	}
}
