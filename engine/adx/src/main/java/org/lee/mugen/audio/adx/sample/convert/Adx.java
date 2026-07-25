package org.lee.mugen.audio.adx.sample.convert;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;

public class Adx {
	public AdxHeader getHeader() {
		return header;
	}

	/*
	 * Address Size Contains 
	 * 0x00 2 0x8000 
	 * 0x02 2 data offset 
	 * 0x04 1 format (3 for ADX) 
	 * 0x05 1 block size (typically 18) 
	 * 0x06 1 bits per samplWe? (4)
	 * 0x07 1 channel count 
	 * 0x08 4 sample rate 
	 * 0x0c 4 sample count 
	 * 0x10 2 high-pass cutoff 
	 * 0x12 1 loop data style ("type": 03, 04, or 05) 
	 * 0x13 1 encryption flag (other flags?)
	 */
	public static abstract class AdxHeader {
		protected int head0x8000; // ushort
		protected int dataOffset; // ushort
		protected int format; // ubyte
		protected int blockSize; // ubyte
		protected int bitsPerSample; // ubyte
		protected int channelCount; // ubyte
		protected int sampleRate; // int
		protected int sampleCount; // int
		protected int highPassCutOff; // short
		protected int version; // byte
		protected int flag; // byte

		protected AdxHeader(RandomAccessFile file) throws IOException {
			file.seek(0);
			head0x8000 = file.readShort();
			dataOffset = file.readShort() - 2;
			format = file.readByte();
			blockSize = file.readByte();
			bitsPerSample = file.readByte();
			channelCount = file.readByte();
			sampleRate = file.readInt();
			sampleCount = file.readInt();
			highPassCutOff = file.readShort();
			version = file.readByte();
			flag = file.readByte();
		}

		// ////////////////

		protected byte[] unknown;
		protected int loopFlag;
		protected int loopStartSample;
		protected int loopStartByte;
		protected int loopEndSample;
		protected int loopEndByte;

		// ///////////////////////

		public int getHead0x8000() {
			return head0x8000;
		}

		public void setHead0x8000(int head0x8000) {
			this.head0x8000 = head0x8000;
		}

		public int getDataOffset() {
			return dataOffset;
		}

		public void setDataOffset(int dataOffset) {
			this.dataOffset = dataOffset;
		}

		public int getFormat() {
			return format;
		}

		public void setFormat(int format) {
			this.format = format;
		}

		public int getBlockSize() {
			return blockSize;
		}

		public void setBlockSize(int blockSize) {
			this.blockSize = blockSize;
		}

		public int getBitsPerSample() {
			return bitsPerSample;
		}

		public void setBitsPerSample(int bitsPerSample) {
			this.bitsPerSample = bitsPerSample;
		}

		public int getChannelCount() {
			return channelCount;
		}

		public void setChannelCount(int channelCount) {
			this.channelCount = channelCount;
		}

		public int getSampleRate() {
			return sampleRate;
		}

		public void setSampleRate(int sampleRate) {
			this.sampleRate = sampleRate;
		}

		public int getSampleCount() {
			return sampleCount;
		}

		public void setSampleCount(int sampleCount) {
			this.sampleCount = sampleCount;
		}

		public int getHighPassCutOff() {
			return highPassCutOff;
		}

		public void setHighPassCutOff(int highPassCutOff) {
			this.highPassCutOff = highPassCutOff;
		}

		public int getVersion() {
			return version;
		}

		public void setVersion(int version) {
			this.version = version;
		}

		public int getFlag() {
			return flag;
		}

		public void setFlag(int flag) {
			this.flag = flag;
		}

		public byte[] getUnknown() {
			return unknown;
		}

		public void setUnknown(byte[] unknown) {
			this.unknown = unknown;
		}

		public int getLoopFlag() {
			return loopFlag;
		}

		public void setLoopFlag(int loopFlag) {
			this.loopFlag = loopFlag;
		}

		public int getLoopStartSample() {
			return loopStartSample;
		}

		public void setLoopStartSample(int loopStartSample) {
			this.loopStartSample = loopStartSample;
		}

		public int getLoopStartByte() {
			return loopStartByte;
		}

		public void setLoopStartByte(int loopStartByte) {
			this.loopStartByte = loopStartByte;
		}

		public int getLoopEndSample() {
			return loopEndSample;
		}

		public void setLoopEndSample(int loopEndSample) {
			this.loopEndSample = loopEndSample;
		}

		public int getLoopEndByte() {
			return loopEndByte;
		}

		public void setLoopEndByte(int loopEndByte) {
			this.loopEndByte = loopEndByte;
		}

		// ////////////////

	}

	/*
	 * type 03 loop data Address Size Contains 
	 * 0x14 4 unknown 
	 * 0x18 4 loop flag
	 * 0x1c 4 loop start sample 
	 * 0x20 4 loop start byte 
	 * 0x24 4 loop end sample
	 * 0x28 4 loop end byte type 04 loop data
	 */
	public static class AdxHeader3 extends AdxHeader {

		protected AdxHeader3(RandomAccessFile file) throws IOException {
			super(file);
			unknown = new byte[4];
			file.read(unknown);
			loopFlag = file.readInt();
			loopStartSample = file.readInt();
			loopStartByte = file.readInt();
			loopEndSample = file.readInt();
			loopEndByte = file.readInt();
		}
	}

	/*
	 * Address Size Contains 
	 * 0x14 16 unknown 
	 * 0x24 4 loop flag 
	 * 0x28 4 loop start sample 
	 * 0x2c 4 loop start byte 
	 * 0x30 4 loop end sample 
	 * 0x34 4 loop end byte
	 */
	public static class AdxHeader4 extends AdxHeader {

		protected AdxHeader4(RandomAccessFile file) throws IOException {
			super(file);
			unknown = new byte[16];
			file.read(unknown);
			loopFlag = file.readInt();
			loopStartSample = file.readInt();
			loopStartByte = file.readInt();
			loopEndSample = file.readInt();
			loopEndByte = file.readInt();
		}

	}

	public static AdxHeader getHeader(RandomAccessFile file) throws IOException {
		file.seek(4);
		int version = file.readByte();
		AdxHeader header = null;
		switch (version) {
		case 0x03:
			header = new AdxHeader3(file);
			break;
		case 0x04:
			header = new AdxHeader4(file);
			break;

		default:
			throw new IllegalArgumentException("Unknow version");
		}
		return header;
	}

	private AdxHeader header;
	private RandomAccessFile file;

	public Adx(File pFile) throws IOException {
		file = new RandomAccessFile(pFile, "r");
		header = getHeader(file);
	}



	public void close() throws IOException {
		file.close();
	}
	final int BASEVOL = 0x3000; //0x4000
//	final int BASEVOL = 0x4000;
	
	int read_long(byte[] p, int pos) throws IOException
	{
		byte[] bytes = new byte[p.length - pos];
		System.arraycopy(p, pos, bytes, 0, bytes.length);
		ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
		DataInputStream in = new DataInputStream(bis);
        return in.readInt();
	}

	int read_word(byte[] p, int pos) throws IOException
	{
		byte[] bytes = new byte[p.length - pos];
		System.arraycopy(p, pos, bytes, 0, bytes.length);
		ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
		DataInputStream in = new DataInputStream(bis);
		return in.readShort();
	}

	class PreviousData {
		int channel1,channel2;
	}
	int step = 16;
	void convert(short[] out, int outPos, byte[] in, int inPos, PreviousData prev)
	{
		int scale = ((unsignedByteToInt(in[0])<<8)|(unsignedByteToInt(in[1])));
		int i;
		int s0,s1,s2,d;
		inPos += 2;
		s1 = prev.channel1;
		s2 = prev.channel2;
		for(i=0;i<step;i++) {
			d = unsignedByteToInt(in[inPos+i])>>4;
			if ((d&8) != 0) 
				d-=16;
			s0 = (BASEVOL*d*scale + 0x7298*s1 - 0x3350*s2)>>14;
			if (s0>32767) 
				s0=32767;
			else if (s0<-32768) 
				s0=-32768;
			int r = s0;

			out[outPos++] = (short) r;

			s2 = s1;
			s1 = s0;

			d = unsignedByteToInt(in[inPos+i])&15;
			if ((d&8) != 0) d-=16;
			s0 = (BASEVOL*d*scale + 0x7298*s1 - 0x3350*s2)>>14;
			if (s0>32767) 
				s0=32767;
			else if (s0<-32768) 
				s0=-32768;
			
			r = s0;
			out[outPos++] = (short) r;
			s2 = s1;
			s1 = s0;
		}
		prev.channel1 = s1;
		prev.channel2 = s2;
	}
	
	PreviousData[] prev = new PreviousData[] {new PreviousData(), new PreviousData()};

	int samplePosition;
	int filePosition;
	int convert(OutputStream outfile, int sampleNeed) throws IOException
	{
		RandomAccessFile in = getFile();
		in.seek(0);
		OrderDataOutputStream out;
		byte[] buf = new byte[18*2];
		short[] outbuf = new short[32*2];
		int offset;
		int size,wsize;
		
		int rest = header.getSampleCount() - samplePosition;
		size = Math.min(sampleNeed, rest);
		offset = header.getDataOffset();
		
		if (samplePosition == 0) {
			in.seek(offset);
			in.read(buf, 1, 6);
		} else {
			in.seek(filePosition);
		}

		out = new OrderDataOutputStream(new BufferedOutputStream(outfile));
		
		if (header.getChannelCount()==1)
			while(size > 0) {
				in.read(buf, 0, 18);
				convert(outbuf,0,buf,0,prev[0]);
				if (size>32) wsize=step*2; else wsize = size;
				size-=wsize;
				for (int pos = 0; pos < outbuf.length && pos < wsize*2/2; pos++) {
					out.writeShort(outbuf[pos]);
				}
			}
		else if (header.getChannelCount()==2)
			while(size > 0) {
				int i;
				short[] tmpbuf = new short[32*2];
				in.read(buf, 0, (step + 2)*2);
				convert(tmpbuf,0,buf,0,prev[0]);
				convert(tmpbuf,step*2,buf,step+2,prev[1]);

				for(i=0;i<32;i++) {
					outbuf[i*2]   = tmpbuf[i];
					outbuf[i*2+1] = tmpbuf[i+step*2];
				}
				wsize = size>step*2? step*2: size;
				size-=wsize;
				samplePosition+=wsize;
				byte[] buffer = new byte[wsize*2*2];
				int posBuffer = 0;
				filePosition = (int) getFile().getFilePointer();
				for (int pos = 0; pos < wsize*2*2/2; pos++) {
			        buffer[posBuffer++] = (byte) ((outbuf[pos]>>0) & 0xFF);
			        buffer[posBuffer++] = (byte) ((outbuf[pos]>>8) & 0xFF);
			        
			        if (header.getLoopFlag() != 0) {
						if (filePosition + posBuffer >= header.getLoopEndByte()) {
							getFile().seek(header.getLoopStartByte());
							samplePosition = header.getLoopStartSample();
							break;
						}
					}
				}
				out.write(buffer, 0, posBuffer);
				
			}
		out.close();
		filePosition = (int) getFile().getFilePointer();

		return 0;
	}
	  public static int unsignedByteToInt(byte b) {
		return (int) b & 0xFF;
	}

	public RandomAccessFile getFile() {
		return file;
	}
}
