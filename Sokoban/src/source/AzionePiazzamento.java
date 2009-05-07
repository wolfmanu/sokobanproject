package source;

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
		
		public MapAndResult eseguiAzione(Map<String, Integer> varMap, SokoPieces[][] mappa){
			int x=0, y=0;
			MapAndResult mar=new MapAndResult();
			
			x=element.traduci(this.x, varMap);
			y=element.traduci(this.y, varMap);
			
			if ( x>mappa.length || y > mappa[0].length)
				mar.result=Result.IndexOutOfBound;
			
			switch(type){
				case MURO:	mappa[x][y]= SokoPieces.wall;
							break;
				case GOAL:	mappa[x][y]= SokoPieces.goal;
							break;
				case BOX:	mappa[x][y]= SokoPieces.occupied;
							break;
				case SOKOBAN:	mappa[x][y]= SokoPieces.me;
							break;
				default:	mar.result=Result.TypeError;;
			}
			mar.result=Result.OK;
			mar.mappa=mappa;
			return mar;
		}
		
}
