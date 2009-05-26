package source;

import java.util.Map;

public abstract class element {
	
	public static int traduci(element e,Map<String, Integer> varMap){
		if(e instanceof intVar)
			return ((intVar)e).val;	
		else if(e instanceof strVar){
			if(varMap.containsKey(((strVar)e).x)){
				return (varMap.get(((strVar)e).x)).intValue();
			}
		}
		System.err.println("valueting error");
		return -1;
	}
}
