package org.lee.mugen.sprite.common.resource;



import java.io.InputStream;

import java.io.StringReader;

import java.util.Arrays;

import java.util.List;

import java.util.StringTokenizer;



import org.lee.mugen.core.JMugenConstant;

import org.lee.mugen.geom.MugenPoint;

import org.lee.mugen.imageIO.PCXPalette;

import org.lee.mugen.imageIO.RawPCXImage;

import org.lee.mugen.io.IOUtils;

import org.lee.mugen.io.MugenDataStreams;

import java.io.IOException;



import org.lee.mugen.sprite.parser.Parser;

import org.lee.mugen.sprite.parser.Parser.GroupText;



public class FontParser {

	static FontProducer fontProducer;



	private static final byte[] SIGNATURE = new byte[] {
			'E', 'l', 'e', 'c', 'b', 'y', 't', 'e', 'F', 'n', 't', 0
	};



	public static FontProducer getFontProducer() throws Exception {

		if (fontProducer == null) {

			InputStream in = MugenDataStreams.openBinary(JMugenConstant.RESOURCE + "font/name1.fnt");

			try {

				fontProducer = parse(IOUtils.toByteArray(in));

			} finally {

				in.close();

			}

		}

		return fontProducer;

	}



	public static FontProducer getFontProducer(String filename) throws Exception {

		InputStream in = MugenDataStreams.openBinary(filename);

		try {

			return parse(IOUtils.toByteArray(in));

		} finally {

			in.close();

		}

	}



	public static FontProducer parse(byte[] fileData) throws IOException {

		final int fileLength = fileData.length;

		if (fileLength < 32) {

			throw new IOException("Font file too small (" + fileLength + " bytes)");

		}

		for (int i = 0; i < SIGNATURE.length; i++) {
			if (fileData[i] != SIGNATURE[i]) {
				throw new IOException("Not a Mugen font (missing ElecbyteFnt signature)");
			}
		}



		final int offsetPcx = readIntLe(fileData, 16);

		final int pcxLength = readIntLe(fileData, 20);

		final int offsetText = readIntLe(fileData, 24);

		final int textLength = readIntLe(fileData, 28);



		IOUtils.checkRange(offsetPcx, pcxLength, fileLength, "font PCX");

		IOUtils.checkRange(offsetText, textLength, fileLength, "font text");



		byte[] pcxBytes = Arrays.copyOfRange(fileData, offsetPcx, offsetPcx + pcxLength);

		byte[] textBytes = Arrays.copyOfRange(fileData, offsetText, offsetText + textLength);



		FontProducer fontProducer = new FontProducer();

		String text = new String(textBytes);

		List<GroupText> grpsText = Parser.getGroupTextMap(new StringReader(text), true);

		for (GroupText grp : grpsText) {

			if (grp.getSection().equalsIgnoreCase("def")) {

				String[] strsSize = grp.getKeyValues().get("size").replaceAll(" ", "").split(",");

				fontProducer.setSize(new MugenPoint(Integer.parseInt(strsSize[0]), Integer.parseInt(strsSize[1])));



				String[] strsSpacing = grp.getKeyValues().get("spacing").replaceAll(" ", "").split(",");

				fontProducer.setSpacing(new MugenPoint(Integer.parseInt(strsSpacing[0]),

						strsSpacing.length > 1 ? Integer.parseInt(strsSpacing[1]) : 0));



				String strColors = grp.getKeyValues().get("colors");

				fontProducer.setColors(Integer.parseInt(strColors));



				String[] strsOffset = grp.getKeyValues().get("offset").replaceAll(" ", "").split(",");

				fontProducer.setOffset(new MugenPoint(Integer.parseInt(strsOffset[0]), Integer.parseInt(strsOffset[1])));



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

						try {

							c = (char) Integer.decode(tokens[0]).intValue();

						} catch (Exception e) {

							c = tokens[0].toCharArray()[0];

						}

					} else if (tokens[0].length() > 0) {

						c = tokens[0].charAt(0);

					}

					if (fontProducer.getType().equalsIgnoreCase("Fixed")) {

						fontProducer.getMap().put(c, new Desc(

								(count + (count > 0 ? fontProducer.getSpacing().x : 0)) * fontProducer.getSize().x,

								fontProducer.getSize().x));

					} else if (fontProducer.getType().equalsIgnoreCase("Variable")) {

						fontProducer.getMap().put(c, new Desc(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2])));

					} else {

						throw new IllegalArgumentException("Unknow Type");

					}

					count++;

				}

			}

		}

		RawPCXImage pcxImage = new RawPCXImage(pcxBytes, new PCXPalette());

		fontProducer.setImage(pcxImage);

		return fontProducer;

	}



	private static int readIntLe(byte[] data, int offset) {

		return (data[offset] & 0xff) | ((data[offset + 1] & 0xff) << 8) | ((data[offset + 2] & 0xff) << 16)

				| ((data[offset + 3] & 0xff) << 24);

	}

}

