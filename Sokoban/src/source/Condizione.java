package source;

public class Condizione {
	
	interface element{}
	
	class intVar implements element{
		int x;
		intVar(Object o){
			x=(Integer)o;
		}
	}
	class strVar implements element{
		String x;
		strVar(Object o){
			x=(String)o;
		}
	}
	
	element el1, el2;
	TipoCondizione cond;
	
	public Condizione(Object o1, Object o2, Object c){
		if(o1 instanceof Integer)
			el1=new intVar(o1);	
		else if(o1 instanceof String)
			el1=new strVar(o1);	
		if(o2 instanceof Integer)
			el1=new intVar(o2);	
		else if(o2 instanceof String)
			el1=new strVar(o2);	
		cond=(TipoCondizione)c;
	}
	
}
