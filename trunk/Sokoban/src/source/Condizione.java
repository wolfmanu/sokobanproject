package source;

import java.util.Map;

public class Condizione {
	
	public element el1, el2;
	public TipoCondizione cond;
	
	public Condizione(Object o1, Object o2, Object c){
		if(o1 instanceof Integer)
			el1=new intVar(o1);	
		else if(o1 instanceof String)
			el1=new strVar(o1);	
		if(o2 instanceof Integer)
			el2=new intVar(o2);	
		else if(o2 instanceof String)
			el2=new strVar(o2);	
		cond=(TipoCondizione)c;
 	}
	
	public boolean valute(Map<String, Integer> varMap){

		int x=0, y=0;
		
		x=element.traduci(el1, varMap);
		y=element.traduci(el2, varMap);
		
		switch(cond){
			case UGUALEUGUALE:	return (x==y);
			case MAGUG:	return (x>=y);
			case MINUG:	return (x<=y);
			case MAG:	return (x>y);
			case MIN:	return (x<y);
			case DIVERSO:	return (x!=y);
		}
		return false;
	}
}
