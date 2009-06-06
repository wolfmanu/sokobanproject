package it.polito.let;

import java.util.Iterator;
import java.util.Map;

public class AzioneFor implements Azione{

	public String var;
	public int start;
	public Condizione cond;
	public Boolean incremento;
	public ListaAzioni la;
	
	public MapAndResult executeAction(Map<String, Integer> varMap, SokoPieces[][] mappa){
		
		MapAndResult mar=new MapAndResult();
		mar.mappa=mappa;
		varMap.put(var, new Integer(start));
		System.out.println("Putting "+var+"="+start);
		while(cond.valute(varMap)){
			Iterator<Azione> it=la.getActions().iterator();
			while(it.hasNext()){
				Azione az = it.next();
				if ( az instanceof AzioneFor){
					mar=((AzioneFor)az).executeAction(varMap, mar.mappa);
				}
				else if ( az instanceof AzioneIf){
					mar=((AzioneIf)az).executeAction(varMap, mar.mappa);
				}
				else if ( az instanceof AzionePiazzamento){
					mar=((AzionePiazzamento)az).executeAction(varMap, mar.mappa);
				}
				if (mar.result!=Result.OK)
					return mar;
			}
			if(incremento)
				varMap.put(var, varMap.get(var)+1);
			else
				varMap.put(var, varMap.get(var)-1);
		}
		return mar;
	}
	
}
