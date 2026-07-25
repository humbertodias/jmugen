package org.lee.mugen.sff;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import org.lee.mugen.io.MugenDataStreams;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.lee.mugen.imageIO.PCXConstants;
import org.lee.mugen.imageIO.PCXPalette;
import org.lee.mugen.io.IOUtils;
import org.lee.mugen.io.LittleEndianDataInputStream;

/**
 * Read SFF file
 * @author Dr Wong
 *
 */
public class SffReader {
    public static class PcxFile {
        public ByteArrayOutputStream pcxStream = new ByteArrayOutputStream();
    };
    public static class SffHeader {
        public char[] signature = new char[12];
        public int verhi; // 1
        public int verlo; // 1
        public int verlo2; // 1
        public int verlo3; // 1
        public int nbrGrp; // 4
        public int nbrImg; // 4
        public int firstSubFileOffset; // 4
        public int subHeaderSize; // 4 // in bytes
        public int paletteType; // 1 // (1=SPRPALTYPE_SHARED or 0=SPRPALTYPE_INDIV)
        public char[] blank = new char[3];
        public char[] comment = new char[476];

        public SffHeader(LittleEndianDataInputStream br) throws IOException {
            br.readChars(signature);
            verhi = br.read();
            verlo = br.read();
            verlo2 = br.read();
            verlo3 = br.read();
            nbrGrp = br.readInt();
            nbrImg = br.readInt();
            firstSubFileOffset = br.readInt();
            subHeaderSize = br.readInt();
            paletteType = br.read();
            br.readChars(blank);
            br.readChars(comment);
        }
    }
    public static class SubFileHeader {
        public int nextPosition;
        public int subFileLen; //4 //not including SubHeader
        public int xAxis; // 2
        public int yAxis; // 2
        public int grpNumber; // 2
        public int imgNumber; // 2 // int the group
        public int indexPreviousCopySprite; // 2 // linked sprites only
        public boolean isSamePalAsPrev; // 1 //True if palette is same as previous image	
        public char[] comment = new char[13]; //comment
        public PcxFile pcxFile = new PcxFile();

        public SubFileHeader(LittleEndianDataInputStream br) throws IOException {
            nextPosition = br.readInt();
            subFileLen = br.readInt();
            xAxis = br.readShort();
            yAxis = br.readShort();
            grpNumber = br.readShort();
            imgNumber = br.readShort();
            indexPreviousCopySprite = br.readShort();
            isSamePalAsPrev = br.readBoolean();
            br.readChars(comment);
        }
    };
    

    public ArrayList<SffReader.SubFileHeader> SubFileList = new ArrayList<SffReader.SubFileHeader>();
    protected SffHeader sffHeader;
    
    private static void seek(InputStream fs, long skip) throws IOException {
    	fs.reset();
    	fs.skip(skip);
    }
    private static int streamOffset(long len, ByteArrayInputStream fs) {
    	return (int) (len - fs.available());
    }

    private static int sliceLength(int nextPosition, long len, ByteArrayInputStream fs) throws IOException {
    	int read = streamOffset(len, fs);
    	int iRead = nextPosition - read;
    	IOUtils.checkChunkSize(iRead, (int) len, "SFF PCX slice");
    	if (iRead < 0) {
    		throw new IOException("Invalid SFF slice at offset " + read + ": next=" + nextPosition + " len=" + len);
    	}
    	return iRead;
    }

    private static void expectSignature(byte[] fileData) throws IOException {
    	if (fileData.length < 12) {
    		throw new IOException("SFF file too small");
    	}
    	byte[] sig = new byte[] {'E', 'l', 'e', 'c', 'b', 'y', 't', 'e', 'S', 'p', 'r', 0};
    	for (int i = 0; i < sig.length; i++) {
    		if (fileData[i] != sig[i]) {
    			throw new IOException("Not a Mugen SFF (bad signature, " + fileData.length + " bytes)");
    		}
    	}
    }

    private static void copyPaletteTail(byte[] bytes, byte[] prevPalette, boolean isForceUSeDefPal) {
    	if (isForceUSeDefPal && bytes.length >= PCXPalette.PALETTE_SIZE) {
    		System.arraycopy(prevPalette, 0, bytes, bytes.length - PCXPalette.PALETTE_SIZE, PCXPalette.PALETTE_SIZE);
    	}
    }

    private static void readPaletteTail(byte[] bytes, byte[] prevPalette) {
    	if (bytes.length >= PCXPalette.PALETTE_SIZE) {
    		System.arraycopy(bytes, bytes.length - PCXPalette.PALETTE_SIZE, prevPalette, 0, PCXPalette.PALETTE_SIZE);
    	}
    }
    
	public SffReader(String filename, byte[] useThisPal) throws FileNotFoundException, IOException {
		this(MugenDataStreams.openBinary(filename), useThisPal);
	}
    public SffReader(InputStream in, byte[] useThisPal) throws IOException {

    	boolean isForceUSeDefPal = useThisPal != null && useThisPal.length == 768;
    	byte[] fileData = IOUtils.toByteArray(in);
    	expectSignature(fileData);
    	ByteArrayInputStream fs = new ByteArrayInputStream(fileData);
    	LittleEndianDataInputStream br = new LittleEndianDataInputStream(fs);
    	final long len = fileData.length;
    	if (len < 512) {
    		throw new IOException("SFF file too small: " + len);
    	}
    	
        sffHeader = new SffHeader(br);
        sffHeader.subHeaderSize = 512;

        seek(fs, 512);
        br = new LittleEndianDataInputStream(fs);

        SubFileHeader subFileHead = new SubFileHeader(br);

        byte[] prevPalette = isForceUSeDefPal? useThisPal: new byte[PCXPalette.PALETTE_SIZE];

        byte[] bytes = new byte[PCXConstants.HEADER_SIZE];
        if (subFileHead.subFileLen > 0) {
        	br.read(bytes);
            subFileHead.pcxFile.pcxStream.write(bytes);
            int iRead = sliceLength(subFileHead.nextPosition, len, fs);
            bytes = new byte[iRead];
            br.read(bytes);
            copyPaletteTail(bytes, prevPalette, isForceUSeDefPal);
            subFileHead.pcxFile.pcxStream.write(bytes);

            bytes = subFileHead.pcxFile.pcxStream.toByteArray();
            readPaletteTail(bytes, prevPalette);
        }
        if (useThisPal == null)
        	useThisPal = java.util.Arrays.copyOf(prevPalette, prevPalette.length);

        int next = subFileHead.nextPosition;
        boolean enter = false;

        while (next != 0 && next < len) {
        	enter = true;
            SubFileList.add(subFileHead);
            seek(fs, next);
            br = new LittleEndianDataInputStream(fs);
            subFileHead = new SubFileHeader(br);
            if (subFileHead.subFileLen > 0) {
                int iRead = sliceLength(subFileHead.nextPosition, len, fs);
                bytes = new byte[iRead];
                br.read(bytes);

                if (subFileHead.isSamePalAsPrev) {
                    subFileHead.pcxFile.pcxStream.write(bytes);
                    subFileHead.pcxFile.pcxStream.write(prevPalette);

                } else {
                	copyPaletteTail(bytes, prevPalette, isForceUSeDefPal);
                	if (!isForceUSeDefPal && subFileHead.grpNumber != 9000) {
                		readPaletteTail(bytes, prevPalette);
                	}
                    subFileHead.pcxFile.pcxStream.write(bytes);
//                    if (!isForceUSeDefPal) {
//                        bytes = subFileHead.pcxFile.pcxStream.toByteArray();
//                        System.arraycopy(bytes, (int) (bytes.length - PCXPalette.PALETTE_SIZE), prevPalette, 0, PCXPalette.PALETTE_SIZE);
//                    	
//                    }
                }
            } else if (subFileHead.subFileLen == 0) {
                subFileHead.pcxFile = null;
            }
            next = subFileHead.nextPosition;
        }
        if (enter)
        	SubFileList.add(subFileHead);
        in.close();
    }

    public static byte[] getImage(InputStream in, int grp, int num, byte[] useThisPal) throws IOException {

    	boolean isForceUSeDefPal = useThisPal != null && useThisPal.length == 768;
    	byte[] fileData = IOUtils.toByteArray(in);
    	ByteArrayInputStream fs = new ByteArrayInputStream(fileData);
    	LittleEndianDataInputStream br = new LittleEndianDataInputStream(fs);
    	final long len = fileData.length;
        ArrayList<SffReader.SubFileHeader> SubFileList = new ArrayList<SffReader.SubFileHeader>();
        SffHeader sffHeader;
        sffHeader = new SffHeader(br);
        sffHeader.subHeaderSize = 512;

        seek(fs, 512);
        
        SubFileHeader subFileHead = new SubFileHeader(br);

        byte[] prevPalette = isForceUSeDefPal? useThisPal: new byte[PCXPalette.PALETTE_SIZE];

        byte[] bytes = new byte[PCXConstants.HEADER_SIZE];
        if (subFileHead.subFileLen > 0) {
        	br.read(bytes);

            subFileHead.pcxFile.pcxStream.write(bytes);
            int iRead = sliceLength(subFileHead.nextPosition, len, fs);
            bytes = new byte[iRead];
            br.read(bytes);
            if (isForceUSeDefPal) {
            	System.arraycopy(prevPalette, 0, bytes, (int) (bytes.length - PCXPalette.PALETTE_SIZE), PCXPalette.PALETTE_SIZE);
            	
            }
            subFileHead.pcxFile.pcxStream.write(bytes);
        	if (grp == subFileHead.grpNumber && num == subFileHead.imgNumber) {
        		return subFileHead.pcxFile.pcxStream.toByteArray();
        	}
            bytes = subFileHead.pcxFile.pcxStream.toByteArray();
           	System.arraycopy(bytes, (int) (bytes.length - PCXPalette.PALETTE_SIZE), prevPalette, 0, PCXPalette.PALETTE_SIZE);
        }
        if (useThisPal == null)
        	useThisPal = java.util.Arrays.copyOf(prevPalette, prevPalette.length);

        int next = subFileHead.nextPosition;
        boolean enter = false;

        while (next != 0 && next < len) {
        	enter = true;
            SubFileList.add(subFileHead);
            seek(fs, next);
            subFileHead = new SubFileHeader(br);
            if (subFileHead.subFileLen > 0) {
                int iRead = sliceLength(subFileHead.nextPosition, len, fs);
                bytes = new byte[iRead];
                br.read(bytes);

                if (subFileHead.isSamePalAsPrev) {
                    subFileHead.pcxFile.pcxStream.write(bytes);
                    subFileHead.pcxFile.pcxStream.write(prevPalette);
                	if (grp == subFileHead.grpNumber && num == subFileHead.imgNumber) {
                		return subFileHead.pcxFile.pcxStream.toByteArray();
                	}

                } else {
                	if (isForceUSeDefPal)
                		System.arraycopy(prevPalette, 0, bytes, (int) (bytes.length - PCXPalette.PALETTE_SIZE), PCXPalette.PALETTE_SIZE);
                	else if (subFileHead.grpNumber != 9000)
                		System.arraycopy(bytes, (int) (bytes.length - PCXPalette.PALETTE_SIZE), prevPalette, 0, PCXPalette.PALETTE_SIZE);
                    subFileHead.pcxFile.pcxStream.write(bytes);
                	if (grp == subFileHead.grpNumber && num == subFileHead.imgNumber) {
                		return subFileHead.pcxFile.pcxStream.toByteArray();
                	}
//                    if (!isForceUSeDefPal) {
//                        bytes = subFileHead.pcxFile.pcxStream.toByteArray();
//                        System.arraycopy(bytes, (int) (bytes.length - PCXPalette.PALETTE_SIZE), prevPalette, 0, PCXPalette.PALETTE_SIZE);
//                    	
//                    }
                }
            } else if (subFileHead.subFileLen == 0) {
                subFileHead.pcxFile = null;
            }
            next = subFileHead.nextPosition;
        }
        if (enter)
        	SubFileList.add(subFileHead);
        in.close();
        return null;
    }

}