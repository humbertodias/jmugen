package org.lee.mugen.sprite.parser;

import JFlex.sym;
import org.lee.mugen.parser.type.Valueable;
%%

%public
%class FlexParser
%implements sym

%unicode

%line
%column

%function nextToken
%type Valueable






%%

<YYINITIAL> {
{DecIntegerLiteral}            { return new Integer(yytext()); }
{OperatorPlus}                      { return Keys.OP.PLUS; }
{OperatorMoins}                      { return Keys.OP.MOINS; }

}