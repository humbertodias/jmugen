/* JMugenLexer.java is a generated file.  You probably want to
 * edit JMugenLexer.lex to make changes.  Use JFlex to generate it.
 * To generate JMugenLexer.java
 * Install <a href="http://jflex.de/">JFlex</a> v1.3.2 or later.
 * Once JFlex is in your classpath run<br>
 * <code>java JFlex.Main JMugenLexer.lex</code><br>
 * You will then have a file called JMugenLexer.java
 */

/*
 * This file is part of a <a href="http://ostermiller.org/syntax/">syntax
 * highlighting</a> package.
 * Copyright (C) 1999-2002 Stephen Ostermiller
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

package com.Ostermiller.Syntax.Lexer;

import java.io.*;

/** 
 * JMugenLexer is a Mugen lexer.  Created with JFlex.  An example of how it is used:
 *  <CODE>
 *  <PRE>
 *  JMugenLexer shredder = new JMugenLexer(System.in);
 *  JMugenToken t;
 *  while ((t = shredder.getNextToken()) != null){
 *      System.out.println(t);
 *  }
 *  </PRE>
 *  </CODE>
 * The tokens returned should comply with the 
 * <A Href=http://java.sun.com/docs/books/jls/html/>Java 
 * Language Specification</A>.
 * @see JMugenToken
 */ 

%%

%public
%class JMugenLexer
%implements Lexer
%function getNextToken
%type Token 
%ignorecase

%{
    private int lastToken;
    private int nextState=YYINITIAL;
    
    /** 
     * next Token method that allows you to control if whitespace and comments are
     * returned as tokens.
     */
    public Token getNextToken(boolean returnComments, boolean returnWhiteSpace)throws IOException{
        Token t = getNextToken();
        while (t != null && ((!returnWhiteSpace && t.isWhiteSpace()) || (!returnComments && t.isComment()))){
            t = getNextToken();
        }
        return (t); 
    }
        
    /**
     * Prints out tokens from a file or System.in.
     * If no arguments are given, System.in will be used for input.
     * If more arguments are given, the first argument will be used as
     * the name of the file to use as input
     *
     * @param args program arguments, of which the first is a filename
     */
    public static void main(String[] args) {
        InputStream in;
        try {
            if (args.length > 0){
                File f = new File(args[0]);
                if (f.exists()){
                    if (f.canRead()){
                        in = new FileInputStream(f);
                    } else {
                        throw new IOException("Could not open " + args[0]);
                    }
                } else {
                    throw new IOException("Could not find " + args[0]);
                }
            } else {
                in = System.in;
            }
            JMugenLexer shredder = new JMugenLexer(in);
            Token t;
            while ((t = shredder.getNextToken()) != null) {
                if (t.getID() != JMugenToken.WHITE_SPACE){
                    System.out.println(t);
                }
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }  

    /**
     * Closes the current input stream, and resets the scanner to read from a new input stream.
	 * All internal variables are reset, the old input stream  cannot be reused
	 * (content of the internal buffer is discarded and lost).
	 * The lexical state is set to the initial state.
     * Subsequent tokens read from the lexer will start with the line, char, and column
     * values given here.
     *
     * @param reader The new input.
     * @param yyline The line number of the first token.
     * @param yychar The position (relative to the start of the stream) of the first token.
     * @param yycolumn The position (relative to the line) of the first token.
     * @throws IOException if an IOExecption occurs while switching readers.
     */
    public void reset(java.io.Reader reader, int yyline, int yychar, int yycolumn) throws IOException{
        yyreset(reader);
        this.yyline = yyline;
		this.yychar = yychar;
		this.yycolumn = yycolumn;
	}
%}

%line
%char
%full
%column

HexDigit=([0-9a-fA-F])
Digit=([0-9])
OctalDigit=([0-7])
TetraDigit=([0-3])
NonZeroDigit=([1-9])
Letter=([a-zA-Z_$])
NoLetter=([^a-zA-Z_$])
BLANK=([ ])
TAB=([\t])
FF=([\f])
EscChar=([\\])
CR=([\r])
LF=([\n])
EOL=({CR}|{LF}|{CR}{LF})
WhiteSpace=({BLANK}|{TAB}|{FF}|{EOL})
AnyNonSeparator=([^\t\f\r\n\ \(\)\{\}\[\]\;\,\.\=\>\<\!\~\?\:\+\-\*\/\&\|\^\%\"\'])

OctEscape1=({EscChar}{OctalDigit})
OctEscape2=({EscChar}{OctalDigit}{OctalDigit})
OctEscape3=({EscChar}{TetraDigit}{OctalDigit}{OctalDigit})
OctEscape=({OctEscape1}|{OctEscape2}|{OctEscape3})
UnicodeEscape=({EscChar}[u]{HexDigit}{HexDigit}{HexDigit}{HexDigit})
Escape=({EscChar}([r]|[n]|[b]|[f]|[t]|[\\]|[\']|[\"]))


AnyStrChr=([^\"\n\r\\])
UnclosedString=([\"]({Escape}|{OctEscape}|{UnicodeEscape}|{AnyStrChr})*)
String=({UnclosedString}[\"])
MalformedUnclosedString=([\"]({EscChar}|{AnyStrChr})*)
MalformedString=({MalformedUnclosedString}[\"])

COMMENT_REGEX = (;.*)


FloatDouble1=({Digit}+[\.]{Digit}*)
FloatDouble2=([\.]{Digit}+)
FloatDouble3=({Digit}+)
Float1=({FloatDouble1})
Float2=({FloatDouble2})
Float3=({FloatDouble3})
FLOAT_REGEX=({Float1}|{Float2}|{Float3})

LEFT_CONTAINS=[a-z0-9().]
LEFT_KEY = ({EOL}{WhiteSpace}*{LEFT_CONTAINS}+){WhiteSpace}*=



Text=([^\t\f\r\n ])

TRIGGER_MATHS_FUNCTION_REGEX=(min|ln|atan|max|{NoLetter}+e{NoLetter}+|asin|cos|ceil|log|exp|abs|floor|sin|ifelse|pi|tan|acos)
TRIGGER_FUNCTION_SPRITE_REGEX=(projhit|projhit|projhittime|projhittime|projcontact|projcontact|projcontacttime|projcontacttime|projguarded |projguarded|projguardedtime |projguardedtime|projcanceltime |projcanceltime|ctrl|pos|animtime|projguarded |projguarded|p2name|time|loseko|movetype|powermax|timemod|prevstateno|moveguarded|animexist|winko|projcontacttime |projcontacttime|teamside|uniqhitcount|movereversed|random|roundno|fvar|numhelper|frontedgedist|stateno|movecontact|animelemtime|hitvel|movehit|numproj|anim|physics|const|beaninfo|p4name|p2dist|p2stateno|gethitvar|p2bodydist|projcontact |projcontact|canrecover|life|projhittime |projhittime|matchno|command|hitfall|gametime|numtarget|screenpos|inguarddist|hitpausetime|hitcount|ishometeam|p3name|tickspersecond|vel|sysvar|matchover|numenemy|hitdefattr|alive|drawgame|hitshakeover|p2life|power|parentdist|sysfvar|projguardedtime |projguardedtime|projcanceltime |projcanceltime|animelemno|authorname|format|roundstate|playeridexist|ishelper|p2movetype|backedgedist|selfanimexist|p2statetype|isassertspecial|p1name|statetime|animelem|rootdist|commandcount|var|projhit |projhit|hitover|id|palno|numpartner|name|frontedgebodydist|teammode|numprojid|roundsexisted|win|backedgebodydist|lose|statetype|facing|numexplod)
TYPE_STATECTRL = (afterimage|targetdrop|forcefeedback|changeanim2|hitfallvel|poweradd|appendtoclipboard|sndpan|bindtoparent|makedust|ctrlset|assertspecial|hitadd|bgpalfx|defencemulset|pause|screenbound|movehitreset|hitvelset|varrandom|powerset|envcolor|turn|changeanim|bindtoroot|posadd|selfstate|width|targetvelset|angleadd|targetstate|lifeset|varset|trans|attackdist|spritebeanset|explodbindtime|destroyself|reversaldef|targetveladd|posset|stopsnd|gravity|targetpoweradd|anglemul|posfreeze|hitfalldamage|superpause|veladd|targetfacing|varrangeset|nothitby|parentvaradd|lifeadd|velmul|displaytoscreen|varadd|targetbind|targetlifeadd|clearclipboard|statetypeset|fallenvshake|bindtotarget|gamemakeanim|playerpush|playsnd|angleset|projectile|null|explod|removeexplod|helper|envshake|sprpriority|displaytoclipboard|hitby|changestate|modifyexplod|print|angledraw|allpalfx|palfx|hitoverride|parentvarset|attackmulset|afterimagetime|hitfallset|changepal|velset|hitdef)



SPRITE_REDIRECT_REG=(root|parent|partner|enemynear|helper|playerid|target|enemy)
CONST_SPRITE_REGEX = (const{WhiteSpace}*\([a-zA-Z0-9.]+\))
SECTION_STATEDEF =  ({WhiteSpace}*\[statedef{WhiteSpace}*[-+]*[0-9]+\])

STATECTRL_PARAM = ({Text}|[^\[\]]|{WhiteSpace})
SECTION_STATECTRL = ({WhiteSpace}*\[state{WhiteSpace}{STATECTRL_PARAM}.+\])
SECTION = ({WhiteSpace}*\[[^\[\]]+\])



%%

<YYINITIAL> "(" { 
    lastToken = JMugenToken.SEPARATOR_LPAREN;
    String text = yytext();
    JMugenToken t = (new JMugenToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
    return (t);
    }
<YYINITIAL> ")" {
    lastToken = JMugenToken.SEPARATOR_RPAREN;
    String text = yytext();
    JMugenToken t = (new JMugenToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
    return (t);
}

<YYINITIAL> "[" {
    lastToken = JMugenToken.SEPARATOR_LBRACKET;
    String text = yytext();
    JMugenToken t = (new JMugenToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
    return (t);
}
<YYINITIAL> "]" {
    lastToken = JMugenToken.SEPARATOR_RBRACKET;
    String text = yytext();
    JMugenToken t = (new JMugenToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
    return (t);
}

<YYINITIAL> "," {
    lastToken = JMugenToken.SEPARATOR_COMMA;
    String text = yytext();
    JMugenToken t = (new JMugenToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
    return (t);
}
<YYINITIAL> "." {
    lastToken = JMugenToken.SEPARATOR_PERIOD;
    String text = yytext();
    JMugenToken t = (new JMugenToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
    return (t);
}
<YYINITIAL> "=" {
    lastToken = JMugenToken.OPERATOR_REGEX;
    String text = yytext();
    JMugenToken t = (new JMugenToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
    return (t);
}
<YYINITIAL> ">" {
    lastToken = JMugenToken.OPERATOR_REGEX;
    String text = yytext();
    JMugenToken t = (new JMugenToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
    return (t);
}
<YYINITIAL> "<" {
    lastToken = JMugenToken.OPERATOR_REGEX;
    String text = yytext();
    JMugenToken t = (new JMugenToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
    return (t);
}
<YYINITIAL> "!" {
    lastToken = JMugenToken.OPERATOR_REGEX;
    String text = yytext();
    JMugenToken t = (new JMugenToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
    return (t);
}
<YYINITIAL> "~" {
    lastToken = JMugenToken.OPERATOR_REGEX;
    String text = yytext();
    JMugenToken t = (new JMugenToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
    return (t);
}
<YYINITIAL> "?" {
    lastToken = JMugenToken.OPERATOR_REGEX;
    String text = yytext();
    JMugenToken t = (new JMugenToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
    return (t);
}
<YYINITIAL> ":" {
    lastToken = JMugenToken.OPERATOR_REGEX;
    String text = yytext();
    JMugenToken t = (new JMugenToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
    return (t);
}
<YYINITIAL> "+" {
    lastToken = JMugenToken.OPERATOR_REGEX;
    String text = yytext();
    JMugenToken t = (new JMugenToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
    return (t);
}
<YYINITIAL> "-" {
    lastToken = JMugenToken.OPERATOR_REGEX;
    String text = yytext();
    JMugenToken t = (new JMugenToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
    return (t);
}
<YYINITIAL> "*" {
    lastToken = JMugenToken.OPERATOR_REGEX;
    String text = yytext();
    JMugenToken t = (new JMugenToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
    return (t);
}
<YYINITIAL> "/" {
    lastToken = JMugenToken.OPERATOR_REGEX;
    String text = yytext();
    JMugenToken t = (new JMugenToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
    return (t);
}
<YYINITIAL> "&" {
    lastToken = JMugenToken.OPERATOR_REGEX;
    String text = yytext();
    JMugenToken t = (new JMugenToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
    return (t);
}
<YYINITIAL> "|" {
    lastToken = JMugenToken.OPERATOR_REGEX;
    String text = yytext();
    JMugenToken t = (new JMugenToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
    return (t);
}
<YYINITIAL> "^" {
    lastToken = JMugenToken.OPERATOR_REGEX;
    String text = yytext();
    JMugenToken t = (new JMugenToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
    return (t);
}
<YYINITIAL> "%" {
    lastToken = JMugenToken.OPERATOR_REGEX;
    String text = yytext();
    JMugenToken t = (new JMugenToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
    return (t);
}

<YYINITIAL> "==" {
    lastToken = JMugenToken.OPERATOR_REGEX;
    String text = yytext();
    JMugenToken t = (new JMugenToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
    return (t);
}
<YYINITIAL> "<=" { 
    lastToken = JMugenToken.OPERATOR_REGEX;
    String text = yytext();
    JMugenToken t = (new JMugenToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
    return (t);
}
<YYINITIAL> ">=" { 
    lastToken = JMugenToken.OPERATOR_REGEX;
    String text = yytext();
    JMugenToken t = (new JMugenToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
    return (t);
}
<YYINITIAL> "!=" { 
    lastToken = JMugenToken.OPERATOR_REGEX;
    String text = yytext();
    JMugenToken t = (new JMugenToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
    return (t);
}
<YYINITIAL> "||" { 
    lastToken = JMugenToken.OPERATOR_REGEX;
    String text = yytext();
    JMugenToken t = (new JMugenToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
    return (t);
}
<YYINITIAL> "&&" { 
    lastToken = JMugenToken.OPERATOR_REGEX;
    String text = yytext();
    JMugenToken t = (new JMugenToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
    return (t);
}
<YYINITIAL> "++" { 
    lastToken = JMugenToken.OPERATOR_REGEX;
    String text = yytext();
    JMugenToken t = (new JMugenToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
    return (t);
}
<YYINITIAL> "--" { 
    lastToken = JMugenToken.OPERATOR_REGEX;
    String text = yytext();
    JMugenToken t = (new JMugenToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
    return (t);
}
<YYINITIAL> ">>" { 
    lastToken = JMugenToken.OPERATOR_REGEX;
    String text = yytext();
    JMugenToken t = (new JMugenToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
    return (t);
}
<YYINITIAL> "<<" { 
    lastToken = JMugenToken.OPERATOR_REGEX;
    String text = yytext();
    JMugenToken t = (new JMugenToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
    return (t);
}
<YYINITIAL> ">>>" { 
    lastToken = JMugenToken.OPERATOR_REGEX;
    String text = yytext();
    JMugenToken t = (new JMugenToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
    return (t);
}
<YYINITIAL> "+=" { 
    lastToken = JMugenToken.OPERATOR_REGEX;
    String text = yytext();
    JMugenToken t = (new JMugenToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
    return (t);
}
<YYINITIAL> "-=" { 
    lastToken = JMugenToken.OPERATOR_REGEX;
    String text = yytext();
    JMugenToken t = (new JMugenToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
    return (t);
}
<YYINITIAL> "*=" { 
    lastToken = JMugenToken.OPERATOR_REGEX;
    String text = yytext();
    JMugenToken t = (new JMugenToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
    return (t);
}
<YYINITIAL> "/=" { 
    lastToken = JMugenToken.OPERATOR_REGEX;
    String text = yytext();
    JMugenToken t = (new JMugenToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
    return (t);
}
<YYINITIAL> "&=" { 
    lastToken = JMugenToken.OPERATOR_REGEX;
    String text = yytext();
    JMugenToken t = (new JMugenToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
    return (t);
}
<YYINITIAL> "|=" { 
    lastToken = JMugenToken.OPERATOR_REGEX;
    String text = yytext();
    JMugenToken t = (new JMugenToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
    return (t);
}
<YYINITIAL> "^=" { 
    lastToken = JMugenToken.OPERATOR_REGEX;
    String text = yytext();
    JMugenToken t = (new JMugenToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
    return (t);
}
<YYINITIAL> "%=" { 
    lastToken = JMugenToken.OPERATOR_REGEX;
    String text = yytext();
    JMugenToken t = (new JMugenToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
    return (t);
}
<YYINITIAL> "<<=" { 
    lastToken = JMugenToken.OPERATOR_REGEX;
    String text = yytext();
    JMugenToken t = (new JMugenToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
    return (t);
}
<YYINITIAL> ">>=" { 
    lastToken = JMugenToken.OPERATOR_REGEX;
    String text = yytext();
    JMugenToken t = (new JMugenToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
    return (t);
}
<YYINITIAL> ">>>=" { 
    lastToken = JMugenToken.OPERATOR_REGEX;
    String text = yytext();
    JMugenToken t = (new JMugenToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
    return (t);
}
<YYINITIAL> {String} { 
    lastToken = JMugenToken.LITERAL_STRING;
    String text = yytext();
    JMugenToken t = (new JMugenToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
    return (t);
}

<YYINITIAL> ({WhiteSpace}+) { 
    lastToken = JMugenToken.WHITE_SPACE;
    String text = yytext();
    JMugenToken t = (new JMugenToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
    return (t);
}
<YYINITIAL> ({EOL}+) { 
    lastToken = JMugenToken.EOL;
    String text = yytext();
    JMugenToken t = (new JMugenToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
    return (t);
}

<YYINITIAL> {LEFT_KEY} { 
    lastToken = JMugenToken.LEFT_KEY;
    String text = yytext();
    JMugenToken t = (new JMugenToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
    return (t);
}

<YYINITIAL> {SPRITE_REDIRECT_REG} { 
    lastToken = JMugenToken.SPRITE_REDIRECT_REG;
    String text = yytext();
    JMugenToken t = (new JMugenToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
    return (t);
}
<YYINITIAL> {COMMENT_REGEX} { 
    lastToken = JMugenToken.COMMENT_REGEX;
    String text = yytext();
    JMugenToken t = (new JMugenToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
    return (t);
}
<YYINITIAL> {FLOAT_REGEX} { 
    lastToken = JMugenToken.FLOAT_REGEX;
    String text = yytext();
    JMugenToken t = (new JMugenToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
    return (t);
}
<YYINITIAL> {CONST_SPRITE_REGEX} { 
    lastToken = JMugenToken.CONST_SPRITE_REGEX;
    String text = yytext();
    JMugenToken t = (new JMugenToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
    return (t);
}

<YYINITIAL> {SECTION_STATEDEF} { 
    lastToken = JMugenToken.SECTION_STATEDEF;
    String text = yytext();
    JMugenToken t = (new JMugenToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
    return (t);
}
<YYINITIAL> {SECTION_STATECTRL} { 
    lastToken = JMugenToken.SECTION_STATECTRL;
    String text = yytext();
    JMugenToken t = (new JMugenToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
    return (t);
}
<YYINITIAL> {SECTION} { 
    lastToken = JMugenToken.SECTION;
    String text = yytext();
    JMugenToken t = (new JMugenToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
    return (t);
}

<YYINITIAL> {TRIGGER_MATHS_FUNCTION_REGEX} { 
    lastToken = JMugenToken.TRIGGER_MATHS_FUNCTION_REGEX;
    String text = yytext();
    JMugenToken t = (new JMugenToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
    return (t);
}
<YYINITIAL> {TRIGGER_FUNCTION_SPRITE_REGEX} { 
    lastToken = JMugenToken.TRIGGER_FUNCTION_SPRITE_REGEX;
    String text = yytext();
    JMugenToken t = (new JMugenToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
    return (t);
}
<YYINITIAL> {TYPE_STATECTRL} { 
    lastToken = JMugenToken.TYPE_STATECTRL;
    String text = yytext();
    JMugenToken t = (new JMugenToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
    return (t);
}



<YYINITIAL> ({Text}) { 
    lastToken = JMugenToken.TEXT;
    String text = yytext();
    JMugenToken t = (new JMugenToken(lastToken,text,yyline,yychar,yychar+text.length(),nextState));
    return (t);
}

