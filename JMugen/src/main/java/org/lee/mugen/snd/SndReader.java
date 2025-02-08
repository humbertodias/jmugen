package org.lee.mugen.snd;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.lee.mugen.core.JMugenConstant;
import org.lee.mugen.core.sound.SoundSystem;
import org.lee.mugen.io.IOUtils;
import org.lee.mugen.io.LittleEndianDataInputStream;

public class SndReader {
	
	private static void seek(LittleEndianDataInputStream fs, long skip) throws IOException {
    	fs.reset();
    	fs.skip(skip);
    }
	
	public static Snd parse(String filename) throws IOException {
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filename));
		return parse(bis);
	}

	
	public static Snd parse(InputStream in) throws IOException {

    	ByteArrayOutputStream baos = new ByteArrayOutputStream();
    	IOUtils.copy(in, baos);
    	ByteArrayInputStream fs = new ByteArrayInputStream(baos.toByteArray());
    	LittleEndianDataInputStream dis = new LittleEndianDataInputStream(fs);
    	baos.close();
    	baos = null;
    	
    	Snd snd = new Snd();
    	long size = dis.available();
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
    		seek(dis, offset);
    		offset = dis.readInt();
    		if (offset == -1)
    			break;
    		final int length = dis.readInt();
    		final int grpNumber = dis.readInt();
    		final int sampleNumber = dis.readInt();

    		byte[] data = new byte[length];
    		dis.read(data);
    		snd.addSound(grpNumber, sampleNumber, data);
    	}
    	
//    	byte[] data = snd.getGroup(0).getSound(0);

    	return snd;
	}
	
	public static void main(String[] args) throws IOException {
		SoundSystem.SoundBackGround.playMusic(JMugenConstant.RESOURCE + "sound/LeeKyungwon_WayOfRebirth.mp3");
		Snd snd = SndReader.parse(JMugenConstant.RESOURCE + "chars/xiangfei/files/xiangfei.snd");
		for (GroupSnd grp: snd.getGroups())
			for (byte[] bytes: grp.getSounds())
				SoundSystem.Sfx.playSnd(bytes, false);
	}
}
