package it.polito.let;


public class intVar extends element{
	
	public int val;
	
	public intVar(Object o){
		val=((Integer)o).intValue();
	}

}
