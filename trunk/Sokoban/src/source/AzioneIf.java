package source;

import java.util.Iterator;
import java.util.Map;

public class AzioneIf implements Azione{

	public Condizione cond;
	public ListaAzioni la;

	
	public MapAndResult executeAction(Map<String, Integer> varMap, SokoPieces[][] mappa) {
		MapAndResult mar=new MapAndResult();
		mar.mappa=mappa;
		/**/System.out.println("provo condizione");
		if(cond.valute(varMap)){
			Iterator<Azione> it=la.getActions().iterator();
			System.out.println("Azioneif");
			while(it.hasNext()){
				System.out.println("Azione if");
				Azione az = it.next();
				if ( az instanceof AzioneFor){
					/**/System.out.println(((AzioneFor)az).var);
					mar=((AzioneFor)az).executeAction(varMap, mar.mappa);
				}
				else if ( az instanceof AzioneIf){
					/**/System.out.println(((AzioneIf)az).cond);
					mar=((AzioneIf)az).executeAction(varMap, mar.mappa);
				}
				else if ( az instanceof AzionePiazzamento){
					/**/System.out.println(((AzionePiazzamento)az).type);
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
