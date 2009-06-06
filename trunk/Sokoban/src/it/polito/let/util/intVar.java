package it.polito.let.util;


public class intVar extends element{
	
	public int val;
	
	public intVar(Object o){
		val=((Integer)o).intValue();
	}

}
