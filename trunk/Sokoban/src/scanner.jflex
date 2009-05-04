import java_cup.runtime.*;



%%
%class c
%caseless
%unicode
%standalone

signed		= signed
unsigned	= unsigned
int			= int
long		= long
short		= short
float		= float
double		= double
char		= char
extern		= extern
register	= register
auto		= auto
static		= static
void		= void
if			= if
else		= else
while		= while
switch		= switch
case		= case
break		= break
for			= for
return		= return
default		= default

comm		= \/\* .* \*\/

go	=	\{
gc	=	\}
to	=	\(
tc	=	\)
qo	=	\[
qc	=	\]
piu	=	"+"
men	=	-
per	=	\*
div	=	\/
mod	=	\%
uguale	=	\=
pv	=	;
punto	=	\.
virg	=	,

num_int	=  [0-9][0-9]*
num_float	= ("+"|"-")? ( ([0-9]+ \. [0-9]*) | ([0-9]* \. [0-9]+) ) (E ("+"|"-")? [0-9]{2} )?
id		=	[a-zA-Z][a-zA-Z_0-9]*

and		= "&"
or		= "|"
not		= \!
gt		= >
lt		= <

include	= \#include{spazi}*<[^>]*>

spazi	= \t| " "
nl		=  \n|\r|\r\n

%%


{signed}	{System.out.print("SIGNED ");}
{unsigned}	{System.out.print("UNSIGNED ");}
{int}		{System.out.print("INT ");}
{long}		{System.out.print("LONG ");}
{short}		{System.out.print("SHORT ");}
{float}		{System.out.print("FLOAT ");}
{double}	{System.out.print("DOUBLE ");}
{char}		{System.out.print("CHAR ");}
{extern}	{System.out.print("EXTERN ");}
{register}	{System.out.print("REGISTER ");}
{auto}		{System.out.print("AUTO ");}
{static}	{System.out.print("STATIC ");}
{void}		{System.out.print("VOID ");}
{if}		{System.out.print("IF ");}
{else}		{System.out.print("ELSE ");}
{while}		{System.out.print("WHILE ");}
{switch}	{System.out.print("SWITCH" );}
{case}		{System.out.print("CASE ");}
{break}		{System.out.print("BREAK ");}
{for}		{System.out.print("FOR ");}
{return}	{System.out.print("RETURN ");}
{default}	{System.out.print("DEFAULT ");}


{go}		{System.out.print("GO ");}
{gc}		{System.out.print("GC ");}
{to}		{System.out.print("TO ");}
{tc}		{System.out.print("TC ");}
{qo}		{System.out.print("QO ");}
{qc}		{System.out.print("QC ");}
{piu}		{System.out.print("PIU ");}
{men}		{System.out.print("MENO ");}
{per}		{System.out.print("PER ");}
{div}		{System.out.print("DIV ");}
{mod}		{System.out.print("MOD ");}
{uguale}	{System.out.print("UGUALE ");}
{pv}		{System.out.print("PV ");}
{punto}		{System.out.print("PUNTO");}
{virg}		{System.out.print("VIRG");}

{num_int}	{System.out.print("NUM_INT:"+yytext()+" ");}
{num_float}	{System.out.print("NUM_FLOAT:"+yytext()+" ");}
{id}		{System.out.print("ID:"+yytext()+" ");}

{and}		{System.out.print("AND ");}
{or}		{System.out.print("OR ");}
{not}		{System.out.print("NOT ");}
{gt}		{System.out.print("MAGGIORE ");}
{lt}		{System.out.print("MINORE ");}

{comm}		{;}
{include}	{;}
{spazi}		{;}
{nl}		{;}

.		{System.out.println("Carattere errato:" + yytext());}

