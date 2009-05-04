package source;

public class AzionePiazzamento implements Azione{

		TipoPiazzamento type;
		int x,y;
		
		public AzionePiazzamento(Object x, Object y){
			this.x=(Integer)x;
			this.y=(Integer)y;	
		}
	
}
