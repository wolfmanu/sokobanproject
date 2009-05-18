package Generated;
import java.awt.BorderLayout;
import java.io.*;

import javax.swing.JFrame;



public class SokoCompiler
{
	static Sokoban sokoapplet;
	static JFrame sokoframe;
	
    static public void main(String argv[])
    {
    try {
       /* // Istanzio lo scanner aprendo il file di ingresso argv[0]
        Lexer l = new Lexer(new FileReader("Maps/mappa.map"));
        // Istanzio il parser
        SokoParserCup p = new SokoParserCup(l);
        // Avvio il parser
        Object result = p.parse();*/
    	sokoapplet =new Sokoban();
    	sokoframe = new JFrame(); 
    	sokoframe.setTitle("Pagina Studente");
    	sokoframe.getContentPane().add(sokoapplet, BorderLayout.CENTER); 

		sokoapplet.init(); 
		sokoapplet.start(); 
		
		sokoframe.setSize(800,600); 
		sokoframe.setVisible(true); 
        
        }
    catch (Exception e) {
        e.printStackTrace();
        }
    }
}
