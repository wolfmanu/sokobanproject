package source;

public class Assegnazione {

	public String id1;
	public element id2;
	
	public Assegnazione(Object o1, Object o2){
		id1=(String)o1;
		if(o2 instanceof String){
			id2=new strVar(o2);
		}
		else if (o2 instanceof Integer){
			id2=new intVar(o2);
		}
	}
}
