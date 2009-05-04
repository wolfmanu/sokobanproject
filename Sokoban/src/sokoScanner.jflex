import java_cup.runtime.*;


%%

%class Lexer
%unicode
//%standalone
%cup
//%line
//%column

//%{
//      private Symbol symbol(int type)
//      {
 //           return new Symbol(type, yyline, yycolumn);
 //     }
//
 //     private Symbol symbol(int type, Object value)
 //     {
 //           return new Symbol(type, yyline, yycolumn, value);	
 //     }
//%}*/

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
	uguale	= "="
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
	{var}		{/*System.out.println("var");*/ return new Symbol(sym.VAR);}
	{height}	{/*System.out.println("height");*/ return new Symbol(sym.HEIGHT);}
	{width}		{/*System.out.println("width");*/ return new Symbol(sym.WIDTH);}
	{for}		{/*System.out.println("for");*/ return new Symbol(sym.FOR);}
	{if}		{/*System.out.println("if");*/ return new Symbol(sym.IF);}
	{end}		{/*System.out.println("end");*/ return new Symbol(sym.END);}
	{dim}		{/*System.out.println("dim");*/ return new Symbol(sym.DIM);}
	{Nmob}		{/*System.out.println("Nmob");*/ return new Symbol(sym.NMOB);}
	{mob}		{/*System.out.println("mob");*/ return new Symbol(sym.MOB);}
	{fissi}		{/*System.out.println("fissi");*/ return new Symbol(sym.FISSI);}
	{goal} 		{/*System.out.println("goal");*/ return new Symbol(sym.GOAL);}
	{soko} 		{/*System.out.println("soko");*/ return new Symbol(sym.SOKO);}

	{uguale}	{/*System.out.println("uguale");*/ return new Symbol(sym.UGUALE);}
	{minug}		{/*System.out.println("minug");*/ return new Symbol(sym.MINUG);}
	{magug}		{/*System.out.println("magug");*/ return new Symbol(sym.MAGUG);}
	{increm}	{/*System.out.println("increm");*/ return new Symbol(sym.INCR);}
	{diverso}	{/*System.out.println("diverso");*/ return new Symbol(sym.DIVERSO);}
	{decr}		{/*System.out.println("decr");*/ return new Symbol(sym.DECR);}
	{to} 		{/*System.out.println("to");*/ return new Symbol(sym.TO);}
	{tc} 		{/*System.out.println("tc");*/ return new Symbol(sym.TC);}
	{int} 		{/*System.out.println("int");*/ return new Symbol(sym.INT);}
	{id} 		{/*System.out.println("id");*/ return new Symbol(sym.ID);}

	{vir}		{/*System.out.println("virgola");*/ return new Symbol(sym.VIR);}
	{pv}		{/*System.out.println("pv");*/ return new Symbol(sym.PV);}

	{comm}		{/*System.out.println("commento");*/  yybegin(COMMENTO); }
	
	{nl}		{;}
	{spazi}		{;}
	}

<COMMENTO> {
	.*		{;} //salto la righa commentata
	{nl}		{yybegin(YYINITIAL);} 
	}	


