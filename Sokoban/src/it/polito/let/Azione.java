package it.polito.let;

import java.util.Map;

public interface Azione {

	public MapAndResult executeAction(Map<String, Integer> varMap, SokoPieces[][] mappa);
	
}
