package org.lee.mugen.sprite.common.resource;

import java.awt.image.BufferedImage;

class FntRaw {
//  00-11  "ElecbyteFnt\0" signature               [12]
	char[] signature = new char[12];
//  12-15  2 verhi, 2 verlo                        [04]
	short verhi;
	short verlo;
//  16-20  File offset where PCX data is located.  [04]
	long offsetPcx;
//  20-23  Length of PCX data in bytes.            [04]
	long pcxLength;
//  24-27  File offset where TEXT data is located. [04]
	long offsetText;
	
//  28-31  Length of TEXT data in bytes.           [04]
	long textLength;

//  32-63  Blank; can be used for comments.        [40]
	char[] comment = new char[40];

	BufferedImage pcx;
}
