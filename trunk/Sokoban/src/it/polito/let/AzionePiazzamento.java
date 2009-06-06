package it.polito.let;

import java.util.Map;


public class AzionePiazzamento implements Azione{

		public TipoPiazzamento type;
		public element x,y;
		
		public AzionePiazzamento(Object x, Object y){
			
			if(x instanceof Integer)
				this.x=new intVar(x);	
			else if(x instanceof String)
				this.x=new strVar(x);	
			if(y instanceof Integer)
				this.y=new intVar(y);	
			else if(y instanceof String)
				this.y=new strVar(y);	
	
		}
		
		public void setType(Object t){
			this.type=(TipoPiazzamento)t;
		}
		
		public MapAndResult executeAction(Map<String, Integer> varMap, SokoPieces[][] mappa){
			int x=0, y=0,xTmp, yTmp;
			MapAndResult mar=new MapAndResult();
			
			xTmp=element.traduci(this.x, varMap);
			yTmp=element.traduci(this.y, varMap);
			if(xTmp==-1 ){
				mar.result=Result.UndefinedVariable;
				mar.setErrMsg("Undefined_variable "+((strVar)this.x).x);
				return mar;
			}
			if(yTmp==-1 ){
				mar.result=Result.UndefinedVariable;
				mar.setErrMsg("Undefined_variable "+((strVar)this.y).x);
				return mar;
			}
			if(xTmp==-2 ){
				mar.result=Result.NotInitialized;
				mar.setErrMsg("Not initialized variable "+((strVar)this.x).x);
				return mar;
			}
			if(yTmp==-2 ){
				mar.result=Result.NotInitialized;
				mar.setErrMsg("Not initialized variable "+((strVar)this.y).x);
				return mar;
			}
			
			/**/System.out.println(type.name()+"("+(xTmp-1)+","+(yTmp-1)+")");
			
			if ( xTmp>mappa.length || yTmp > mappa[0].length || xTmp<1 || yTmp<1){
				mar.result=Result.IndexOutOfBound;
				mar.setErrMsg("Index out of bound at", type, xTmp, yTmp);
				return mar;
			}
			x=xTmp-1;
			y=yTmp-1;
			switch(type){
				case MURO:	if ( mappa[x][y]!=SokoPieces.wall && mappa[x][y]!=SokoPieces.floor){
								mar.result=Result.Overriding;
								mar.setErrMsg("Overriding "+ mappa[x][y]+" at", type, x, y);
								return mar;
							}
							mappa[x][y]= SokoPieces.wall;
							break;
				case GOAL:	if ( mappa[x][y]==SokoPieces.dollar){ //permetto sovrapposizione
								mappa[x][y]= SokoPieces.occupied;
								break;
							} 
							if ( mappa[x][y]==SokoPieces.me){ //permetto sovrapposizione
								mappa[x][y]= SokoPieces.megoal;
								break;
							}
							if ( mappa[x][y]!=SokoPieces.goal && mappa[x][y]!=SokoPieces.floor ){
								mar.result=Result.Overriding;
								mar.setErrMsg("Overriding "+ mappa[x][y]+" at", type, x, y);
								return mar;
							}
							mappa[x][y]= SokoPieces.goal;
							break;
				case BOX:	if ( mappa[x][y]==SokoPieces.goal){ //permetto sovrapposizione
								mappa[x][y]= SokoPieces.occupied;
								break;
							}
							if ( mappa[x][y]!=SokoPieces.dollar && mappa[x][y]!=SokoPieces.floor ){
								mar.result=Result.Overriding;
								mar.setErrMsg("Overriding "+ mappa[x][y]+" at", type, x, y);
								return mar;
							}
							mappa[x][y]= SokoPieces.dollar;
							break;
				case SOKOBAN:	
							if ( mappa[x][y]==SokoPieces.goal){ //permetto sovrapposizione
								mappa[x][y]= SokoPieces.megoal;
								break;
							}
							if (mappa[x][y]!=SokoPieces.floor ){
								mar.result=Result.Overriding;
								mar.setErrMsg("Overriding "+ mappa[x][y]+" at", type, x, y);
								return mar;
							}
							mappa[x][y]= SokoPieces.me;
							break;
				default:	mar.result=Result.TypeError;;
			}
			mar.result=Result.OK;
			mar.mappa=mappa;
			return mar;
		}		
}
