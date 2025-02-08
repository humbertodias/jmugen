package com.Ostermiller.Syntax.Lexer;

import java.util.ArrayList;
import java.util.List;

public class JMugenToken extends Token {
	public final static int SPRITE_REDIRECT_REG = 0x101;
	public final static int CONST_SPRITE_REGEX = 0x201;
	public final static int CONST_STRING_REG_EXP = 0x202;
	public final static int STRING_REX_EXP = 0x203;
	public final static int TRIGGER_MATHS_FUNCTION_REGEX = 0x301;
	public final static int TRIGGER_FUNCTION_SPRITE_REGEX = 0x302;
	public final static int TRIGGER_FUNCTION_BG_REGEX = 0x401;
	public final static int OPERATOR_REGEX = 0x501;
	public final static int FLOAT_REGEX = 0x601;

	public final static int COMMENT_REGEX = 0x701;

	public final static int LITERAL_STRING = 0x801;

	public final static int SEPARATOR_LPAREN = 0x900;
	public final static int SEPARATOR_RPAREN = 0x901;
	public final static int SEPARATOR_LBRACE = 0x910;
	public final static int SEPARATOR_RBRACE = 0x911;
	public final static int SEPARATOR_LBRACKET = 0x920;
	public final static int SEPARATOR_RBRACKET = 0x921;
	public final static int SEPARATOR_SEMICOLON = 0x930;
	public final static int SEPARATOR_COMMA = 0x940;
	public final static int SEPARATOR_PERIOD = 0x950;

	public final static int TEXT = 0xA01;

	public final static int LEFT_KEY = 0xB01;
	
	
	public final static int WHITE_SPACE = 0xE00;

	public final static int ERROR_UNCLOSED_STRING = 0xF10;
	public final static int ERROR_MALFORMED_STRING = 0xF11;
	public final static int ERROR_MALFORMED_UNCLOSED_STRING = 0xF12;
	
	

	public boolean isSpriteRedirect() {
		return ((ID >> 8) == 0x1);
	}
	public boolean isConstant() {
		return ((ID >> 8) == 0x2);
	}
	public boolean isTriggerMath() {
		return (ID == 0x301);
	}
	public boolean isTriggerFunction() {
		return (ID == 0x302);
	}
	public boolean isBGTriggerFunction() {
		return ((ID >> 8) == 0x4);
	}
	public boolean isOperator() {
		return ((ID >> 8) == 0x5);
	}
	public boolean isFloat() {
		return ((ID >> 8) == 0x6);
	}
	@Override
	public boolean isComment() {
		return ((ID >> 8) == 0x7);
	}
	public boolean isLiteral() {
		return ((ID >> 8) == 0x8);
	}
	public boolean isSeparator() {
		return ((ID >> 8) == 0x9);
	}
	public boolean isWhiteSpace() {
		return ((ID >> 8) == 0xE);
	}

	public boolean isError() {
		return ((ID >> 8) == 0xF);
	}

	
	public boolean isLeftKey() {
		return (ID == 0xB01);
	}
	public static final int SECTION_STATEDEF = 0xB02;
	
	public boolean isStatedefSection() {
		return (ID == 0xB02);
	}
	public static final int SECTION_STATECTRL = 0xB03;
	
	public boolean isStatectrlSection() {
		return (ID == 0xB03);
	}
	public static final int SECTION = 0xB04;
	
	public boolean isSection() {
		return (ID == 0xB04);
	}
	
	public static final int TYPE_STATECTRL = 0xB05;
	
	public boolean isStateCtrlType() {
		return (ID == 0xB05);
	}
	
	public static final int EOL = 0xB06;
	
	public boolean isEOL() {
		return (ID == 0xB06);
	}

	public String getDescription() {
		if (isEOL()) {
			return "EOL";
		} else if (isStateCtrlType()) {
			return "typeStateCtrl";
		} else if (isStatectrlSection()) {
			return "sectionStatectrl";
		} else if (isStatedefSection()) {
			return "sectionStatedef";
		} else if (isSpriteRedirect()) {
			return "redirect";
		} else if (isConstant()) {
			return "const";
		} else if (isTriggerMath()) {
			return "triggerMath";
		} else if (isTriggerFunction()) {
			return "triggerFunction";
		} else if (isBGTriggerFunction()) {
			return "bgTrigger";
		} else if (isOperator()) {
			return "operator";
		} else if (isLiteral()) {
			return "string";
		} else if (isSeparator()) {
			return "separator";
		} else if (isWhiteSpace()) {
			return "whitespace";
		} else if (isError()) {
			return "error";
		} else if (isFloat()) {
			return "float";
		} else if (isComment()) {
			return "comment";
		} else if (isLeftKey()) {
			return "leftKey";
		} else if (isSection()) {
			return "section";
		} else {
			return "unknow";
		}
	}

	
	
	private int ID;
	private String contents;
	private int lineNumber;
	private int charBegin;
	private int charEnd;
	private int state;

	/**
	 * Create a new token. The constructor is typically called by the lexer
	 * 
	 * @param ID
	 *            the id number of the token
	 * @param contents
	 *            A string representing the text of the token
	 * @param lineNumber
	 *            the line number of the input on which this token started
	 * @param charBegin
	 *            the offset into the input in characters at which this token
	 *            started
	 * @param charEnd
	 *            the offset into the input in characters at which this token
	 *            ended
	 */
	public JMugenToken(int ID, String contents, int lineNumber, int charBegin,
			int charEnd) {
		this(ID, contents, lineNumber, charBegin, charEnd,
				Token.UNDEFINED_STATE);
	}

	/**
	 * Create a new token. The constructor is typically called by the lexer
	 * 
	 * @param ID
	 *            the id number of the token
	 * @param contents
	 *            A string representing the text of the token
	 * @param lineNumber
	 *            the line number of the input on which this token started
	 * @param charBegin
	 *            the offset into the input in characters at which this token
	 *            started
	 * @param charEnd
	 *            the offset into the input in characters at which this token
	 *            ended
	 * @param state
	 *            the state the tokenizer is in after returning this token.
	 */
	public JMugenToken(int ID, String contents, int lineNumber, int charBegin,
			int charEnd, int state) {
		this.ID = ID;
		this.contents = new String(contents);
		this.lineNumber = lineNumber;
		this.charBegin = charBegin;
		this.charEnd = charEnd;
		this.state = state;
	}

	/**
	 * Get an integer representing the state the tokenizer is in after returning
	 * this token. Those who are interested in incremental tokenizing for
	 * performance reasons will want to use this method to figure out where the
	 * tokenizer may be restarted. The tokenizer starts in Token.INITIAL_STATE,
	 * so any time that it reports that it has returned to this state, the
	 * tokenizer may be restarted from there.
	 */
	public int getState() {
		return state;
	}

	/**
	 * get the ID number of this token
	 * 
	 * @return the id number of the token
	 */
	public int getID() {
		return ID;
	}

	/**
	 * get the contents of this token
	 * 
	 * @return A string representing the text of the token
	 */
	public String getContents() {
		return (new String(contents));
	}

	/**
	 * get the line number of the input on which this token started
	 * 
	 * @return the line number of the input on which this token started
	 */
	public int getLineNumber() {
		return lineNumber;
	}

	/**
	 * get the offset into the input in characters at which this token started
	 * 
	 * @return the offset into the input in characters at which this token
	 *         started
	 */
	public int getCharBegin() {
		return charBegin;
	}

	/**
	 * get the offset into the input in characters at which this token ended
	 * 
	 * @return the offset into the input in characters at which this token ended
	 */
	public int getCharEnd() {
		return charEnd;
	}


	/**
	 * get a String that explains the error, if this token is an error.
	 * 
	 * @return a String that explains the error, if this token is an error, null
	 *         otherwise.
	 */
	public String errorString() {
		String s;
		if (isError()) {
			s = "Error on line " + lineNumber + ": ";
			switch (ID) {
			case ERROR_UNCLOSED_STRING:
				s += "'\"' expected after " + contents;
				break;
			case ERROR_MALFORMED_STRING:
			case ERROR_MALFORMED_UNCLOSED_STRING:
				s += "Illegal character in " + contents;
				break;
			}

		} else {
			s = null;
		}
		return (s);
	}

	/**
	 * get a representation of this token as a human readable string. The format
	 * of this string is subject to change and should only be used for debugging
	 * purposes.
	 * 
	 * @return a string representation of this token
	 */
	public String toString() {
		return ("Token #" + Integer.toHexString(ID) + ": " + getDescription()
				+ " Line " + lineNumber + " from " + charBegin + " to "
				+ charEnd + " : " + contents);
	}


}
