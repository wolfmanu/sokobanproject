package source;

import java.util.Map;

public abstract class element {
	
	public static int traduci(element e,Map<String, Integer> varMap){
		if(e instanceof intVar)
			return ((intVar)e).val;	
		else if(e instanceof strVar)
			return (varMap.get(e)).intValue();
		return -1;
	}
	
}
