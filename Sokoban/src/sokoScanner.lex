package Generated;

import java_cup.runtime.*;
import static Generated.SokoParserSym.*;
import java.io.IOException;
import source.TipoPiazzamento;
import source.TipoCondizione;

 
%%

%class Lexer 
%unicode
%line
%column

%public
 
%cupsym SokoParserSym  
//%standalone
%cup
//%cupdebug

   
%{
      private Symbol sym(int type)
        {
                return new Symbol(type, yyline, yycolumn); 
        }

        private Symbol sym(int type, Object value)
        {
                return new Symbol(type, yyline, yycolumn, value);
        }

        private void error()
        throws IOException
        {
                throw new IOException("illegal text at line = "+yyline+", column = "+yycolumn+", text = '"+yytext()+"'");
        }

%}

//spazi, a capo, separatori
	spazi	= \t| " "
	nl	=  \n|\r|\r\n
	vir	= ","
	pv	= ";"

//keyword
	var	= [Vv]ar
	height	= [hH]
	width	= [wW]
	for	= [fF]or
	if	= [Ii]f
	end	= [Ee]nd
	dim	= #[dD]imensioni
	Nmob	= #[bB]locchiMobili
	mob	= #[mM]
	fissi	= #[fF]
	goal	= #[gG] 
	soko	= #[Ss]
	
//espressioni, variabili
	ugualeuguale = "=="
	uguale	= "="
	min	= "<"
	mag	= ">"
	minug	= "<="
	magug	= ">="
	diverso = "!="
	increm	= "++"
	decr	= "--"
	to	= "("
	tc	= ")"
	int	= [1-9][0-9]*
	id	= [a-zA-Z_][a-zA-Z_0-9]*

//commenti
	comm	= "//"
	
//stati
%xstate COMMENTO

%%

<YYINITIAL> {
	{var}		{/*System.out.println("var");*/ return new Symbol(SokoParserSym.VAR);}
	{height}	{/*System.out.println("height");*/ return new Symbol(SokoParserSym.HEIGHT,new String(yytext()));}
	{width}		{/*System.out.println("width");*/ return new Symbol(SokoParserSym.WIDTH,new String(yytext()));}
	{for}		{/*System.out.println("for");*/ return new Symbol(SokoParserSym.FOR);}
	{if}		{/*System.out.println("if");*/ return new Symbol(SokoParserSym.IF);}
	{end}		{/*System.out.println("end");*/ return new Symbol(SokoParserSym.END);}
	{dim}		{/*System.out.println("dim");*/ return new Symbol(SokoParserSym.DIM);}
	{Nmob}		{/*System.out.println("Nmob");*/ return new Symbol(SokoParserSym.NMOB);}
	{mob}		{/*System.out.println("mob");*/ return new Symbol(SokoParserSym.MOB, TipoPiazzamento.BOX);}
	{fissi}		{/*System.out.println("fissi");*/ return new Symbol(SokoParserSym.FISSI, TipoPiazzamento.MURO);}
	{goal} 		{/*System.out.println("goal");*/ return new Symbol(SokoParserSym.GOAL, TipoPiazzamento.GOAL);}
	{soko} 		{/*System.out.println("soko");*/ return new Symbol(SokoParserSym.SOKO, TipoPiazzamento.SOKOBAN);}

	{ugualeuguale} {/*System.out.println("ugualeugulae");*/ return new Symbol(SokoParserSym.UGUALEUGUALE, TipoCondizione.UGUALEUGUALE);}
	{uguale}	{/*System.out.println("uguale");*/ return new Symbol(SokoParserSym.UGUALE, TipoCondizione.UGUALE);}
	{min}		{/*System.out.println("min");*/ return new Symbol(SokoParserSym.MIN, TipoCondizione.MIN);}
	{mag}		{/*System.out.println("mag");*/ return new Symbol(SokoParserSym.MAG, TipoCondizione.MAG);}
	{minug}		{/*System.out.println("minug");*/ return new Symbol(SokoParserSym.MINUG, TipoCondizione.MINUG);}
	{magug}		{/*System.out.println("magug");*/ return new Symbol(SokoParserSym.MAGUG, TipoCondizione.MAGUG);}
	{increm}	{/*System.out.println("increm");*/ return new Symbol(SokoParserSym.INCR);}
	{diverso}	{/*System.out.println("diverso");*/ return new Symbol(SokoParserSym.DIVERSO, TipoCondizione.DIVERSO);}
	{decr}		{/*System.out.println("decr");*/ return new Symbol(SokoParserSym.DECR);}
	{to} 		{/*System.out.println("to");*/ return new Symbol(SokoParserSym.TO);}
	{tc} 		{/*System.out.println("tc");*/ return new Symbol(SokoParserSym.TC);}
	{int} 		{/*System.out.println("int");*/ return new Symbol(SokoParserSym.INT, new Integer(yytext()));}
	{id} 		{/*System.out.println("id");*/ return new Symbol(SokoParserSym.ID,new String(yytext()));}

	{vir}		{/*System.out.println("virgola");*/ return new Symbol(SokoParserSym.VIR);}
	{pv}		{/*System.out.println("pv");*/ return new Symbol(SokoParserSym.PV);}

	{comm}		{/*System.out.println("commento");*/  yybegin(COMMENTO); }
	
	{nl}		{;}
	{spazi}		{;}
	}

<COMMENTO> {
	.*		{;} //salto la righa commentata
	{nl}		{yybegin(YYINITIAL);} 
	}	


