package source;


public class AzionePiazzamento implements Azione{

		public TipoPiazzamento type;
		public int x,y;
		
		public AzionePiazzamento(Object x, Object y){
			this.x=(Integer)x;
			this.y=(Integer)y;	
		}
		
		public void setType(Object t){
			this.type=(TipoPiazzamento)t;
		}
}
