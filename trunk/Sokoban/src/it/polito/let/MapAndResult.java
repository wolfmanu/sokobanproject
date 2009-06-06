package it.polito.let;


public class MapAndResult {

	public Result result;
	public SokoPieces[][] mappa;
	public String ErrMsg;
	
	public void setErrMsg(String msg, TipoPiazzamento sp, int x, int y){
		ErrMsg=msg+" "+sp.name()+"("+x+","+y+")\n";
	}
	public void setErrMsg(String msg){
		ErrMsg=msg;
	}
	
}
