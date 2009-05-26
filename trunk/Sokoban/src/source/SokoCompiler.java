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
	
    static public void main(String argv[])
    {
    try {
        // Istanzio lo scanner aprendo il file di ingresso argv[0]
        Lexer l = new Lexer(new FileReader("Maps/mappa.map"));
        // Istanzio il parser
        SokoParserCup p = new SokoParserCup(l);
        // Avvio il parser
        Object result = p.parse();
        
        if(!p.HasError()){
	    	sokoapplet =new Sokoban(p.getMap());
	    	sokoframe = new JFrame(); 
	    	sokoframe.setSize(800,600); 
	    	sokoframe.setTitle("Sokoban");
	    	
	    	sokoframe.getContentPane().add(sokoapplet, BorderLayout.CENTER); 
	    	 
			sokoapplet.init(); 
			sokoapplet.start(); 
			
			
			sokoframe.setVisible(true); 
        }
        else
        	System.err.println("Errore");
    }
    catch (Exception e) {
        e.printStackTrace();
        }
    }
}
