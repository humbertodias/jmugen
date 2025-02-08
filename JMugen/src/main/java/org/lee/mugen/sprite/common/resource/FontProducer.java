package org.lee.mugen.sprite.common.resource;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

import org.lee.mugen.imageIO.RawPCXImage;
import org.lee.mugen.renderer.DrawProperties;
import org.lee.mugen.renderer.GraphicsWrapper;
import org.lee.mugen.renderer.ImageContainer;
import org.lee.mugen.renderer.MugenDrawer;

public class FontProducer {

	private String name;
	
	private Dimension size;
	private Dimension spacing;
	private int colors;
	private Dimension offset;
	private String type;

	private Map<Integer, ImageContainer> fontBankMap = new HashMap<Integer, ImageContainer>();
	private RawPCXImage image;
	
	private ImageContainer getImageByBankno(int bankno) {
		ImageContainer ic = fontBankMap.get(bankno);
		if (ic == null) {
			ic = GraphicsWrapper.getInstance().getImageContainer(image, colors * bankno - (colors * bankno > 0? 0: 0));
		
			fontBankMap.put(bankno, ic);
		}
		
		return ic;
	}
	
	public RawPCXImage getImage() {
		return image;
	}

	public void setImage(RawPCXImage image) {
		this.image = image;
	}

	private Map<Character, Desc> map = new HashMap<Character, Desc>();
	private int computeString(String line) {
		int len = 0;
		for (char c: line.toCharArray()) {
			Desc desc = map.get(c);
			if (desc != null) {
				len += desc.width;
			} else {
				len += size.width;
			}
			len += spacing.width;
		}
		return len;
	}
		
	private int getMaxWidth(String[] lines) {
		int max = 0;
		for (String line: lines)
			if (max < line.length())
				max = computeString(line);
		return max;
	}
	public void draw(int bankno, int xpos, int ypos, MugenDrawer md, String s) {
		drawLeftToRight(xpos, ypos, md, s, bankno, 1f);
	}
	public void draw(int bankno, int xpos, int ypos, MugenDrawer md, String s, int sens, float alpha) {
		if (sens > 0) {
			drawLeftToRight(xpos, ypos, md, s, bankno, alpha);
		} else if (sens < 0) {
			drawRightToLeft(xpos, ypos, md, s, bankno, alpha);
		} else {
			drawCenter(xpos, ypos, md, s, bankno, alpha);
		}
	}
	
//	public void draw(int xpos, int ypos, MugenDrawer md, String s) {
//		drawLeftToRight(xpos, ypos, md, s, 0);
//	}
//	public void draw(int xpos, int ypos, MugenDrawer md, String s, int sens) {
//		if (sens > 0) {
//			drawLeftToRight(xpos, ypos, md, s, 0);
//		} else if (sens < 0) {
//			drawRightToLeft(xpos, ypos, md, s, 0);
//		} else {
//			drawCenter(xpos, ypos, md, s, 0);
//		}
//	}
	


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Dimension getSize() {
		return size;
	}

	public void setSize(Dimension size) {
		this.size = size;
	}

	public Dimension getSpacing() {
		return spacing;
	}

	public void setSpacing(Dimension spacing) {
		this.spacing = spacing;
	}

	public int getColors() {
		return colors;
	}

	public void setColors(int colors) {
		this.colors = colors;
	}

	public Dimension getOffset() {
		return offset;
	}

	public void setOffset(Dimension offset) {
		this.offset = offset;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Map<Character, Desc> getMap() {
		return map;
	}

	public void setMap(Map<Character, Desc> map) {
		this.map = map;
	}

	public void drawLeftToRight(int x, int y, MugenDrawer md, String s, int bankno, float alpha) {
		String[] lines = s.split("\n");
		int width = getMaxWidth(lines);
		int height = lines.length * (size.height + (lines.length > 1? spacing.height: 0));
		
		for (String line: lines) {
			char[] str = line.toCharArray();
			int xposTemp = x;
			for (char c: str) {
				Desc pt = map.get(c);
				if (pt == null) {
					xposTemp += size.width + spacing.width;
					continue;
				}
				DrawProperties dp = new DrawProperties(
						xposTemp, xposTemp + pt.width, y, y - size.height, 
						pt.x , pt.x + pt.width, size.height, 0, 
						false, false,
						getImageByBankno(bankno));
				dp.setAlpha(alpha);
				md.draw(dp);
				xposTemp += (pt.width + spacing.width);
			}
			y += size.height + spacing.height;
		}
		
	}
	
	
	public void drawRightToLeft(int x, int y, MugenDrawer md, String s, int bankno, float alpha) {
		String[] lines = s.split("\n");
		int width = getMaxWidth(lines);
		int height = lines.length * (size.height + (lines.length > 1? spacing.height: 0));
		x -= width;
		for (String line: lines) {
			char[] str = line.toCharArray();
			int xposTemp = x;
			for (char c: str) {
				Desc pt = map.get(c);
				if (pt == null) {
					xposTemp += size.width + spacing.width;
					continue;
				}
				DrawProperties dp = new DrawProperties(
						xposTemp, xposTemp + pt.width, y, y - size.height, 
						pt.x , pt.x + pt.width, size.height, 0, 
						false, false,
						getImageByBankno(bankno));
				dp.setAlpha(alpha);
				md.draw(dp);
				xposTemp += (pt.width + spacing.width);
			}
			y += size.height + spacing.height;
		}
		
	}
	
	public void drawCenter(int x, int y, MugenDrawer md, String s, int bankno, float alpha) {
		
		if (s == null)
			s = "";
		String[] lines = s.split("\n");
		int width = getMaxWidth(lines);
		int height = lines.length * (size.height + (lines.length > 1? spacing.height: 0));
		x -= width/2;
		for (String line: lines) {
			char[] str = line.toCharArray();
			int xposTemp = x;
			for (char c: str) {
				Desc pt = map.get(c);
				if (pt == null) {
					xposTemp += size.width + spacing.width;
					continue;
				}
				DrawProperties dp = new DrawProperties(
						xposTemp, xposTemp + pt.width, y, y - size.height, 
						pt.x , pt.x + pt.width, size.height, 0, 
						false, false,
						getImageByBankno(bankno));
				dp.setAlpha(alpha);
				md.draw(dp);
				xposTemp += (pt.width + spacing.width);
			}
			y += size.height + spacing.height;
		}
		
	}
}
