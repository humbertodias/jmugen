package org.lee.mugen.sff;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.lee.mugen.imageIO.PCXLoader;
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
    private static long getPosition(long len, long available) {
    	return len - available;
    }
    
	public SffReader(String filename, byte[] useThisPal) throws FileNotFoundException, IOException {
		this(new FileInputStream(filename), useThisPal);
	}
    public SffReader(InputStream in, byte[] useThisPal) throws IOException {

    	boolean isForceUSeDefPal = useThisPal != null && useThisPal.length == 768;
    	ByteArrayOutputStream baos = new ByteArrayOutputStream();
    	IOUtils.copy(in, baos);
    	ByteArrayInputStream fs = new ByteArrayInputStream(baos.toByteArray());
    	
    	LittleEndianDataInputStream br = new LittleEndianDataInputStream(fs);
    	baos.close();
    	baos = null;
    	long len = fs.available();
    	
        sffHeader = new SffHeader(br);
        sffHeader.subHeaderSize = 512;

        seek(br, 512);
        
        SubFileHeader subFileHead = new SubFileHeader(br);

        byte[] prevPalette = isForceUSeDefPal? useThisPal: new byte[PCXPalette.PALETTE_SIZE];

        byte[] bytes = new byte[PCXLoader.HEADER_SIZE];
        if (subFileHead.subFileLen > 0) {
        	br.read(bytes);
            subFileHead.pcxFile.pcxStream.write(bytes);
            int iRead = (int)(subFileHead.nextPosition - (len - br.available()));
            bytes = new byte[iRead];
            br.read(bytes);
            if (isForceUSeDefPal) {
            	System.arraycopy(prevPalette, 0, bytes, (int) (bytes.length - PCXPalette.PALETTE_SIZE), PCXPalette.PALETTE_SIZE);
            	
            }
            subFileHead.pcxFile.pcxStream.write(bytes);

            bytes = subFileHead.pcxFile.pcxStream.toByteArray();
           	System.arraycopy(bytes, (int) (bytes.length - PCXPalette.PALETTE_SIZE), prevPalette, 0, PCXPalette.PALETTE_SIZE);
        }
        if (useThisPal == null)
        	useThisPal = prevPalette.clone();

        int next = subFileHead.nextPosition;
        boolean enter = false;

        while (next != 0 && next < len) {
        	enter = true;
            SubFileList.add(subFileHead);
            seek(br, next);
            subFileHead = new SubFileHeader(br);
            if (subFileHead.subFileLen > 0) {
                //int iRead = subFileHead.subFileLen - PcxReader.HEADER_SIZE - rewindPalette;
                int iRead = (int)(subFileHead.nextPosition - getPosition(len, br.available()));
                
                bytes = new byte[iRead];
                br.read(bytes);

                if (subFileHead.isSamePalAsPrev) {
                    subFileHead.pcxFile.pcxStream.write(bytes);
                    subFileHead.pcxFile.pcxStream.write(prevPalette);

                } else {
                	if (isForceUSeDefPal)
                		System.arraycopy(prevPalette, 0, bytes, (int) (bytes.length - PCXPalette.PALETTE_SIZE), PCXPalette.PALETTE_SIZE);
                	else if (subFileHead.grpNumber != 9000)
                		System.arraycopy(bytes, (int) (bytes.length - PCXPalette.PALETTE_SIZE), prevPalette, 0, PCXPalette.PALETTE_SIZE);
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
    	ByteArrayOutputStream baos = new ByteArrayOutputStream();
    	IOUtils.copy(in, baos);
    	ByteArrayInputStream fs = new ByteArrayInputStream(baos.toByteArray());
    	
    	LittleEndianDataInputStream br = new LittleEndianDataInputStream(fs);
    	baos.close();
    	baos = null;
    	long len = fs.available();
        ArrayList<SffReader.SubFileHeader> SubFileList = new ArrayList<SffReader.SubFileHeader>();
        SffHeader sffHeader;
        sffHeader = new SffHeader(br);
        sffHeader.subHeaderSize = 512;

        seek(br, 512);
        
        SubFileHeader subFileHead = new SubFileHeader(br);

        byte[] prevPalette = isForceUSeDefPal? useThisPal: new byte[PCXPalette.PALETTE_SIZE];

        byte[] bytes = new byte[PCXLoader.HEADER_SIZE];
        if (subFileHead.subFileLen > 0) {
        	br.read(bytes);

            subFileHead.pcxFile.pcxStream.write(bytes);
            int iRead = (int)(subFileHead.nextPosition - (len - br.available()));
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
        	useThisPal = prevPalette.clone();

        int next = subFileHead.nextPosition;
        boolean enter = false;

        while (next != 0 && next < len) {
        	enter = true;
            SubFileList.add(subFileHead);
            seek(br, next);
            subFileHead = new SubFileHeader(br);
            if (subFileHead.subFileLen > 0) {
                //int iRead = subFileHead.subFileLen - PcxReader.HEADER_SIZE - rewindPalette;
                int iRead = (int)(subFileHead.nextPosition - getPosition(len, br.available()));
                
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