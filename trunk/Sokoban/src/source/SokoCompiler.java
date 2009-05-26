package source;
import java.awt.BorderLayout;
import java.io.*;

import javax.swing.JFrame;

import Generated.Lexer;
import Generated.SokoParserCup;




public class SokoCompiler
{
	static Sokoban sokoapplet;
	static JFrame sokoframe;
	static SokoParserCup p;
	
    static public void main(String argv[])
    {
    try {
        // Istanzio lo scanner aprendo il file di ingresso argv[0]
        Lexer l = new Lexer(new FileReader("Maps/mappa.map"));
        // Istanzio il parser
        p = new SokoParserCup(l);
        // Avvio il parser
        Object result = p.parse();
        
        if(!p.HasError()){
        	//if(checkIntegrity(p.getMap())){
		    	sokoapplet =new Sokoban(p.getMap());
		    	sokoframe = new JFrame(); 
		    	sokoframe.setSize(800,600); 
		    	sokoframe.setTitle("Sokoban");
		    	
		    	sokoframe.getContentPane().add(sokoapplet, BorderLayout.CENTER); 
		    	 
				sokoapplet.init(); 
				sokoapplet.start(); 
				
				
				sokoframe.setVisible(true); 
        	//}
        }
        else
        	System.err.println("Errore");
    }
    catch (Exception e) {
        e.printStackTrace();
        }
    }

	private static boolean checkIntegrity(SokoPieces[][] map) {
		
		int goal=0, box=0, me=0;
		for(int i=0; i<map.length; i++)
			for(int j=0; j<map[i].length; j++){
				if(map[i][j]==SokoPieces.dollar || map[i][j]==SokoPieces.occupied)
					box++;
				if(map[i][j]==SokoPieces.goal || map[i][j]==SokoPieces.occupied || map[i][j]==SokoPieces.megoal)
					goal++;
				if(map[i][j]==SokoPieces.me || map[i][j]==SokoPieces.megoal)
					me++;
			}
		if(me!=1){
			System.err.println("Too many sokoban defined");
			return false;
		}
		if(goal<p.blocchiMobili){
			System.err.println("Too fiew goal defined");
			return false;
		}
		if(goal>p.blocchiMobili){
			System.err.println("Too may goal defined");
			return false;
		}
		if(box<p.blocchiMobili){
			System.err.println("Too fiew box defined "+box+ goal);
			return false;
		}
		if(box>p.blocchiMobili){
			System.err.println("Too may box defined");
			return false;
		}
		return true;
	}
}
