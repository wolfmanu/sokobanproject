package source;


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
		
		
}
