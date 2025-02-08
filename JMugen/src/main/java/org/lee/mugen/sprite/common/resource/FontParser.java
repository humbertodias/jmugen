package org.lee.mugen.sprite.common.resource;

import java.awt.Dimension;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.StringTokenizer;

import org.lee.mugen.core.JMugenConstant;
import org.lee.mugen.imageIO.PCXPalette;
import org.lee.mugen.imageIO.RawPCXImage;
import org.lee.mugen.io.LittleEndianDataInputStream;
import org.lee.mugen.sprite.parser.Parser;
import org.lee.mugen.sprite.parser.Parser.GroupText;

public class FontParser {
	static FontProducer fontProducer;
	public static FontProducer getFontProducer() throws Exception {
		if (fontProducer == null) {
			FileInputStream fis = new FileInputStream(new File(JMugenConstant.RESOURCE + "font/name1.fnt"));
			fontProducer = parse(new LittleEndianDataInputStream(fis));
			
		}
		return fontProducer;
	}
	public static FontProducer getFontProducer(String filename) throws Exception {
		FileInputStream fis = new FileInputStream(new File(filename));
		FontProducer fontProducer = parse(new LittleEndianDataInputStream(fis));
		return fontProducer;
	}
	public static FontProducer parse(LittleEndianDataInputStream br) throws IOException {
		FntRaw fntRaw = new FntRaw();
		
		br.readChars(fntRaw.signature);
		fntRaw.verhi = br.readShort();
		fntRaw.verlo = br.readShort();
		fntRaw.offsetPcx = br.readInt();
		fntRaw.pcxLength = br.readInt();;
		fntRaw.offsetText = br.readInt();;
		fntRaw.textLength = br.readInt();;
//		br.readChars(fntRaw.comment);

		final int headerSize = 12 + 4 + 4+ 4 + 4+ 4;
		
		long skip = fntRaw.offsetPcx - headerSize;
		
		br.skip(skip);
		byte[] buffer = new byte[(int) fntRaw.pcxLength];
		final ByteArrayOutputStream pcx = new ByteArrayOutputStream();
		br.read(buffer);
		pcx.write(buffer);
		
//		FileOutputStream fos = new FileOutputStream("toto.pcx");
//		fos.write(buffer);
//		fos.close();
		
		skip = 0;
		br.skip(skip);
		buffer = new byte[(int) fntRaw.textLength];
		br.read(buffer);
		
		//
		
		FontProducer fontProducer = new FontProducer();
		String text = new String(buffer);
		List<GroupText> grpsText = Parser.getGroupTextMap(new StringReader(text), true);
		for (GroupText grp: grpsText) {
			if (grp.getSection().equalsIgnoreCase("def")) {
				String[] strsSize = grp.getKeyValues().get("size").replaceAll(" ", "").split(",");
				fontProducer.setSize(new Dimension(Integer.parseInt(strsSize[0]), Integer.parseInt(strsSize[1])));
				
				String[] strsSpacing = grp.getKeyValues().get("spacing").replaceAll(" ", "").split(",");
				fontProducer.setSpacing(new Dimension(Integer.parseInt(strsSpacing[0]), strsSpacing.length > 1?Integer.parseInt(strsSpacing[1]):0));
				
				String strColors = grp.getKeyValues().get("colors");
				//TODO
				fontProducer.setColors(Integer.parseInt(strColors));
				
				String[] strsOffset = grp.getKeyValues().get("offset").replaceAll(" ", "").split(",");
				fontProducer.setOffset(new Dimension(Integer.parseInt(strsOffset[0]), Integer.parseInt(strsOffset[1])));
				
				String strType = grp.getKeyValues().get("type");
				fontProducer.setType(strType);
				
			} else if (grp.getSection().equalsIgnoreCase("map")) {
				StringTokenizer strToken = new StringTokenizer(grp.getText().toString(), "\r\n");
				int count = 0;
				while (strToken.hasMoreTokens()) {
					String line = strToken.nextToken();
					String[] tokens = line.replaceAll("( )|(\t)|(  )", " ").split(" ");
					char c = 0;
					if (tokens[0].length() > 0 && tokens[0].length() > 1) {
						try {c = (char) Integer.decode(tokens[0]).intValue();}catch (Exception e) {
							c = tokens[0].toCharArray()[0];
							assert tokens[0].length() == 1;
						}
					} else if (tokens[0].length() > 0) {
						c = tokens[0].charAt(0);
					}
					if (fontProducer.getType().equalsIgnoreCase("Fixed")) {
						fontProducer.getMap().put(c, new Desc((count + (count > 0? fontProducer.getSpacing().width: 0)) * fontProducer.getSize().width, fontProducer.getSize().width));
					} else if (fontProducer.getType().equalsIgnoreCase("Variable")) {
						
						fontProducer.getMap().put(c, new Desc(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2])));
					} else
						throw new IllegalArgumentException("Unknow Type");
					count++;
					
				}
			}
		}
//		fntRaw.pcx = (BufferedImage)PCXLoader.loadImage(new ByteArrayInputStream(pcx.toByteArray()), new PCXPalette(), false, true);
		RawPCXImage raw = new RawPCXImage(pcx.toByteArray(), new PCXPalette());
		fontProducer.setImage(raw);

		
		return fontProducer;
	}
}
