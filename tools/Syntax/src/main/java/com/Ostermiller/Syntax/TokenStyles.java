/*
 * This file is part of the programmer editor demo
 * Copyright (C) 2005 Stephen Ostermiller
 * http://ostermiller.org/contact.pl?regarding=Syntax+Highlighting
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * See COPYING.TXT for details.
 */
package com.Ostermiller.Syntax;

import java.awt.Color;
import java.util.HashMap;

import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

class TokenStyles {
	private TokenStyles() { } // disable constructor
	
	/**
	 * A hash table containing the text styles. Simple attribute sets are hashed
	 * by name (String)
	 */
	private static HashMap styles = new HashMap();

	/**
	 * Create the styles and place them in the hash table.
	 */
	static {
		Color maroon = new Color(0xB03060);
		Color darkBlue = new Color(0x000080);
		Color darkGreen = Color.GREEN.darker();
		Color darkPurple = new Color(0xA020F0).darker();

		addStyle("body", Color.WHITE, Color.BLACK, false, false);
		addStyle("tag", Color.WHITE, Color.BLUE, true, false);
		addStyle("endtag", Color.WHITE, Color.BLUE, false, false);
		addStyle("reference", Color.WHITE, Color.BLACK, false, false);
		addStyle("name", Color.WHITE, maroon, true, false);
		addStyle("value", Color.WHITE, maroon, false, true);
		addStyle("text", Color.WHITE, Color.BLACK, true, false);
		addStyle("reservedWord", Color.WHITE, Color.BLUE, false, false);
		addStyle("identifier", Color.WHITE, Color.BLACK, false, false);
		addStyle("literal", Color.WHITE, maroon, false, false);
		addStyle("separator", Color.WHITE, darkBlue, false, false);
		addStyle("operator", Color.WHITE, Color.BLACK, true, false);
		addStyle("comment", Color.WHITE, darkGreen, false, false);
		addStyle("preprocessor", Color.WHITE, darkPurple, false, false);
		addStyle("whitespace", Color.WHITE, Color.BLACK, false, false);
		addStyle("error", Color.WHITE, Color.RED, false, false);
		addStyle("unknown", Color.WHITE, Color.ORANGE, false, false);
		addStyle("grayedOut", Color.WHITE, Color.GRAY, false, false);
		
		
		
		addStyle("typeStateCtrl", Color.WHITE, new Color(155,25,100), true, false);
		addStyle("sectionStatedef", Color.WHITE, new Color(25,25,100), true, false);
		addStyle("sectionStatectrl", Color.WHITE, new Color(25,25,150), true, true);
		addStyle("section", Color.WHITE, new Color(100,25,100), true, true);
		addStyle("redirect", Color.WHITE, new Color(150,25,25), false, true);
		addStyle("leftKey", Color.WHITE, new Color(150,200,255), true, false);
		addStyle("const", Color.WHITE, Color.GRAY, false, true);
		addStyle("triggerMath", Color.WHITE, new Color(25,25,150), true, false);
		addStyle("triggerFunction", Color.WHITE, new Color(25,25,100), true, false);
		addStyle("bgTrigger", Color.WHITE, new Color(25,25,75), true, false);
		addStyle("operator", Color.WHITE, Color.RED, false, false);
		addStyle("string", Color.WHITE, Color.GREEN.darker().darker(), false, true);
		addStyle("separator", Color.WHITE, Color.GRAY, true, false);
		addStyle("whitespace", Color.WHITE, Color.WHITE, false, false);
		addStyle("error", Color.WHITE, Color.RED, false, true);
		addStyle("float", Color.WHITE, Color.PINK, false, false);
		addStyle("comment", Color.WHITE, new Color(200,200,255), false, true);
		addStyle("unknow", Color.WHITE, Color.GRAY, false, false);
	}

	private static void addStyle(String name, Color bg, Color fg, boolean bold,
			boolean italic) {
		SimpleAttributeSet style = new SimpleAttributeSet();
		StyleConstants.setFontFamily(style, "Monospaced");
		StyleConstants.setFontSize(style, 12);
		StyleConstants.setBackground(style, bg);
		StyleConstants.setForeground(style, fg);
		StyleConstants.setBold(style, bold);
		StyleConstants.setItalic(style, italic);
		styles.put(name, style);
	}

	/**
	 * Retrieve the style for the given type of token.
	 * 
	 * @param styleName
	 *            the label for the type of text ("tag" for example) or null if
	 *            the styleName is not known.
	 * @return the style
	 */
	public static AttributeSet getStyle(String styleName) {
		return (AttributeSet) styles.get(styleName);
	}
}
