package it.polito.let;

import java.util.ArrayList;



public class ListaAzioni {

	ArrayList<Azione> actions=null;
	
	public ListaAzioni(Object az){
		if (actions==null)
			actions=new ArrayList<Azione>();
		AddAzione((Azione)az);
	}
	
	public void AddAzione(Object az){
		actions.add((Azione)az);
	}
	
	public ArrayList<Azione> getActions(){
		return actions;
	}
}
