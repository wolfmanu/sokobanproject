package generated;

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
                throw new IOException("Illegal text '"+yytext()+"' at line = "+yyline+", column = "+yycolumn);
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
	piu = "+"
	meno = "-"
	ugualeuguale = "=="
	uguale	= "="
	min	= "<"
	mag	= ">"
	minug	= "<="
	magug	= ">="
	diverso = "!="
	//increm	= piu piu
	//decr	= meno meno
	to	= "("
	tc	= ")"
	int	= [1-9][0-9]*
	id	= [a-zA-Z_][a-zA-Z_0-9]*

//commenti
	comm	= "//"
	
//any
	any	=	.
	
//stati
%xstate COMMENTO

%%

<YYINITIAL> {
	{var}		{/*System.out.println("var");*/try {return sym(SokoParserSym.VAR);} catch (Exception e) { error(); } }
	{height}	{/*System.out.println("height");*/ try { return sym(SokoParserSym.HEIGHT,new String(yytext()));} catch (Exception e) { error(); } }
	{width}		{/*System.out.println("width");*/ try { return sym(SokoParserSym.WIDTH,new String(yytext()));} catch (Exception e) { error(); } }
	{for}		{/*System.out.println("for");*/ try { return sym(SokoParserSym.FOR);} catch (Exception e) { error(); } }
	{if}		{/*System.out.println("if");*/ try { return sym(SokoParserSym.IF);} catch (Exception e) { error(); } }
	{end}		{/*System.out.println("end");*/ try { return sym(SokoParserSym.END);} catch (Exception e) { error(); } }
	{dim}		{/*System.out.println("dim");*/ try { return sym(SokoParserSym.DIM);} catch (Exception e) { error(); } }
	{Nmob}		{/*System.out.println("Nmob");*/ try { return sym(SokoParserSym.NMOB);} catch (Exception e) { error(); } }
	{mob}		{/*System.out.println("mob");*/ try { return sym(SokoParserSym.MOB, TipoPiazzamento.BOX);} catch (Exception e) { error(); } }
	{fissi}		{/*System.out.println("fissi");*/ try { return sym(SokoParserSym.FISSI, TipoPiazzamento.MURO);} catch (Exception e) { error(); } }
	{goal} 		{/*System.out.println("goal");*/ try { return sym(SokoParserSym.GOAL, TipoPiazzamento.GOAL);} catch (Exception e) { error(); } }
	{soko} 		{/*System.out.println("soko");*/ try { return sym(SokoParserSym.SOKO, TipoPiazzamento.SOKOBAN);} catch (Exception e) { error(); } }

	{ugualeuguale} {/*System.out.println("ugualeugulae");*/ try { return sym(SokoParserSym.UGUALEUGUALE, TipoCondizione.UGUALEUGUALE);} catch (Exception e) { error(); } }
	{uguale}	{/*System.out.println("uguale");*/ try { return sym(SokoParserSym.UGUALE, TipoCondizione.UGUALE);} catch (Exception e) { error(); } }
	{min}		{/*System.out.println("min");*/ try { return sym(SokoParserSym.MIN, TipoCondizione.MIN);} catch (Exception e) { error(); } }
	{mag}		{/*System.out.println("mag");*/ try { return sym(SokoParserSym.MAG, TipoCondizione.MAG);} catch (Exception e) { error(); } }
	{minug}		{/*System.out.println("minug");*/ try { return sym(SokoParserSym.MINUG, TipoCondizione.MINUG);} catch (Exception e) { error(); } }
	{magug}		{/*System.out.println("magug");*/ try { return sym(SokoParserSym.MAGUG, TipoCondizione.MAGUG);} catch (Exception e) { error(); } }
	{piu}		{/*System.out.println("increm");*/ try { return sym(SokoParserSym.PIU);} catch (Exception e) { error(); } }
	{diverso}	{/*System.out.println("diverso");*/ try { return sym(SokoParserSym.DIVERSO, TipoCondizione.DIVERSO);} catch (Exception e) { error(); } }
	{meno}		{/*System.out.println("decr");*/ try { return sym(SokoParserSym.MENO);} catch (Exception e) { error(); } }
	{to} 		{/*System.out.println("to");*/ try { return sym(SokoParserSym.TO);} catch (Exception e) { error(); } }
	{tc} 		{/*System.out.println("tc");*/ try { return sym(SokoParserSym.TC);} catch (Exception e) { error(); } }
	{int} 		{/*System.out.println("int");*/ try { return sym(SokoParserSym.INT, new Integer(yytext()));} catch (Exception e) { error(); } }
	{id} 		{/*System.out.println("id");*/ try { return sym(SokoParserSym.ID,new String(yytext()));} catch (Exception e) { error(); } }

	{vir}		{/*System.out.println("virgola");*/ try { return sym(SokoParserSym.VIR);} catch (Exception e) { error(); } }
	{pv}		{/*System.out.println("pv");*/ try { return sym(SokoParserSym.PV);} catch (Exception e) { error(); } }

	{comm}		{/*System.out.println("commento");*/  yybegin(COMMENTO); }
	
	{nl}		{;}
	{spazi}		{;}
	{any}		{error();}
	}

<COMMENTO> {
	{any}*		{;} //salto la righa commentata
	{nl}		{yybegin(YYINITIAL);} 
	}	


