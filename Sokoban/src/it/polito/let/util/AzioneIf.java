package it.polito.let.util;


import java.util.Iterator;
import java.util.Map;

public class AzioneIf implements Azione{

	public Condizione cond;
	public ListaAzioni la;

	
	public MapAndResult executeAction(Map<String, Integer> varMap, SokoPieces[][] mappa) {
		MapAndResult mar=new MapAndResult();
		mar.mappa=mappa;

		if(cond.valute(varMap)){
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
		}
		else
			mar.result=Result.OK;
		return mar;
	}
	
}
