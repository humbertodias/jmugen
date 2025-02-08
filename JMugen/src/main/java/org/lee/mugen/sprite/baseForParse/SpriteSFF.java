package org.lee.mugen.sprite.baseForParse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.lee.mugen.imageIO.PCXPalette;
import org.lee.mugen.imageIO.RawPCXImage;
import org.lee.mugen.imageIO.PCXLoader.PCXHeader;
import org.lee.mugen.renderer.DeferedImageLoader;
import org.lee.mugen.renderer.ImageContainer;
import org.lee.mugen.sff.SffReader;
import org.lee.mugen.util.Logger;

public class SpriteSFF {//implements Serializable {

	private HashMap<Integer, GroupSpriteSFF> _groupMap;

	public HashMap<Integer, GroupSpriteSFF> GroupMap() {
		return _groupMap;
	}

	public SpriteSFF(final SffReader sffReader, final boolean isUseIndexColor) throws IOException {
		this(sffReader, isUseIndexColor, false);
	}
	public SpriteSFF(final SffReader sffReader, final boolean isUseIndexColor, boolean useBufferedImage) throws IOException {

		_groupMap = new HashMap<Integer, GroupSpriteSFF>();
		PCXPalette prevPalette = new PCXPalette();
		
		ArrayList<RawPCXImage> imgSprList = new ArrayList<RawPCXImage>();
		long timeToLoadPcx = 0;
		long timeToLoadTExture = 0;
		int countImage = 0;
		
		Logger.log("Enter to process SFF File");
		
		for (SffReader.SubFileHeader subFile : sffReader.SubFileList) {
			countImage++;
			GroupSpriteSFF grpSpr = getGroupSpr(subFile.grpNumber);
			if (null == grpSpr) {
				grpSpr = new GroupSpriteSFF(subFile.grpNumber);
				_groupMap.put(subFile.grpNumber, grpSpr);
			}
			ImageSpriteSFF imgSpr;
			RawPCXImage bitmap;

			if (subFile.subFileLen > 0) {
				ByteArrayOutputStream memStream = subFile.pcxFile.pcxStream;
				long time = System.currentTimeMillis();
				RawPCXImage rawPCXImage = new RawPCXImage(memStream.toByteArray(), prevPalette);
				bitmap = rawPCXImage;
				time = System.currentTimeMillis() - time;
				timeToLoadPcx += time;
				
			} else {
				bitmap = imgSprList.get(subFile.indexPreviousCopySprite);
			}
			imgSprList.add(bitmap);

			long time = System.currentTimeMillis();
			if (useBufferedImage) {
				ByteArrayOutputStream memStream = subFile.pcxFile.pcxStream;
//			BufferedImage loadImage = (BufferedImage) PCXLoader.loadImageColorIndexed(new ByteArrayInputStream(memStream.toByteArray()), prevPalette, false, true);
//				ImageContainer imgContainer = new ImageContainer(loadImage, loadImage.getWidth(), loadImage.getHeight());

				PCXHeader header = new PCXHeader(memStream.toByteArray());
				int width = header.getWidth();
				int height = header.getHeight();
				ImageContainer imgContainer = new DeferedImageLoader(bitmap, width, height);
				imgSpr = new ImageSpriteSFF(subFile.grpNumber, subFile.imgNumber, imgContainer, subFile.xAxis, subFile.yAxis);
			} else {
				imgSpr = new ImageSpriteSFF(subFile.grpNumber, subFile.imgNumber, bitmap, subFile.xAxis, subFile.yAxis);
				
			}
			time = System.currentTimeMillis() - time;
			timeToLoadTExture += time;
			grpSpr.add(subFile.imgNumber, imgSpr);
		}
		Logger.log("End process SFF File");
		Logger.log("Number of image = " + countImage);
		Logger.log("Time to load PCX = " + timeToLoadPcx);
		Logger.log("Time to load PCX = " + timeToLoadTExture);
	}

	protected void addGroup(int key, GroupSpriteSFF grpSpr) {
		_groupMap.put(key, grpSpr);
	}

	public GroupSpriteSFF getGroupSpr(int key) {
		return _groupMap.get(key);
	}
	public Set<Integer> keys() {
		return _groupMap.keySet();
	}
	public Collection<GroupSpriteSFF> getGroupSprs() {
		List<GroupSpriteSFF> l = new ArrayList<GroupSpriteSFF>();
		l.addAll(_groupMap.values());
		return l;
	}

	public void free() {
		int i = 0;
		for (GroupSpriteSFF grp: _groupMap.values()) {
			for (ImageSpriteSFF img: grp.ImgMap().values()) {
				img.getImage().free();
				i++;
			}
		}
		Logger.log("Total Image Free " + i);
	}

	public void reload(final SpriteSFF spriteSFF) {
		new Thread() {
			@Override
			public void run() {
				int i = 0;
				for (GroupSpriteSFF grp: _groupMap.values()) {
					for (ImageSpriteSFF img: grp.ImgMap().values()) {
						if (spriteSFF.getGroupSpr(grp.getGrpNum()) == null || spriteSFF.getGroupSpr(grp.getGrpNum()).getImgSpr(img.getImgNum()) == null)
							throw new IllegalArgumentException("reload Sprite must be the same groupe");
						ImageSpriteSFF imgSFF = spriteSFF.getGroupSpr(grp.getGrpNum()).getImgSpr(img.getImgNum());
						img.getImage().reload(imgSFF.getImage());
						i++;
					}
				}
				Logger.log("Total Image reloaded " + i);
			}
		}.run();
		
	}

}