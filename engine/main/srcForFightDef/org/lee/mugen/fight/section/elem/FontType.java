package org.lee.mugen.fight.section.elem;

import java.util.HashMap;
import java.util.Map;

import org.lee.mugen.sprite.base.AbstractAnimManager;
import org.lee.mugen.sprite.character.AnimGroup;
import org.lee.mugen.sprite.common.resource.FontProducer;
import org.lee.mugen.util.BeanTools;

public class FontType extends CommonType {
	public enum ALIGNMT {
		left(-1), center(0), right(1);
		
		int code;
		ALIGNMT(int code) {
			this.code = code;
		}
		public static ALIGNMT getValue(int alignmt) {
			switch (alignmt) {
			case -1:
				return left;
			case 0:
				return center;
			case 1:
				return right;
			default:
				throw new IllegalArgumentException();
			}
		}
		public Integer getCode() {
			return code;
		}

	}
	int fontno;
	int fontbank;
	ALIGNMT alignmt = ALIGNMT.left;
	
	String text;
	Object root;
	private Map<Integer, FontProducer> font;

	public FontType(Object root, int fontno, int fontbank, int alignmt) {
		this(root, fontno, fontbank, ALIGNMT.getValue(alignmt));
	}
	
	public FontType(Object root, int fontno, int fontbank, ALIGNMT alignmt) {
		this.fontno = fontno;
		this.fontbank = fontbank;
		this.alignmt = alignmt;
		this.root = root;
	}
	public Map<Integer, FontProducer> getFont() {
		if (font == null) {
			try {
				Object files = root.getClass().getMethod("getFiles").invoke(root);
				font = (Map<Integer, FontProducer>) files.getClass().getMethod("getFont").invoke(files);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return font;
	}
	public FontType(Object root) {
		this.root = root;
	}

	public int getFontno() {
		return fontno;
	}

	public void setFontno(int fontno) {
		this.fontno = fontno;
	}

	public int getFontbank() {
		return fontbank;
	}

	public void setFontbank(int fontbank) {
		this.fontbank = fontbank;
	}

	public ALIGNMT getAlignmt() {
		return alignmt;
	}

	public void setAlignmt(ALIGNMT alignmt) {
		this.alignmt = alignmt;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	public void parse(String name, String value) {
		if (name.equals("font")) {
			int[] res = (int[]) BeanTools.getConvertersMap().get(int[].class).convert(value);
			fontno = res[0];
			if (res.length > 1)
				fontbank = res[1];
			if (res.length > 2)
				alignmt = ALIGNMT.getValue(res[2]);
		} else if (name.equals("text")) {
			text = value;
		}
	}
}
