package source;
import java.awt.BorderLayout;
import java.awt.Color;
import java.io.*;

import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Generated.Lexer;
import Generated.SokoParserCup;




public class SokoCompiler
{
	static Sokoban sokoapplet;
	static JFrame sokoframe;
	static JFrame barraframe;
	static SokoParserCup p;
	
    static public void main(String argv[])
    {
    try {
        // Istanzio lo scanner aprendo il file di ingresso argv[0]
        Lexer l = new Lexer(new FileReader("Maps/mappa1.map"));
        // Istanzio il parser
        p = new SokoParserCup(l);
        // Avvio il parser
        Object result = p.parse();
        
        barraframe = new JFrame(); 
		barraframe.setSize(400,100); 
		barraframe.setTitle("Loading...");
        
		if(!p.HasError()){
        	if(checkIntegrity(p.getMap())){
        		barraframe.dispose();
		    	sokoapplet =new Sokoban(p.getMap());
		    	sokoframe = new JFrame(); 
		    	sokoframe.setSize(800,600); 
		    	sokoframe.setTitle("Sokoban");
		    	
		    	sokoframe.getContentPane().add(sokoapplet, BorderLayout.CENTER); 
		    	 
				sokoapplet.init(); 
				sokoapplet.start(); 
				
				sokoframe.setVisible(true); 
        	}
        }
        else{
    		JTextArea msg=new JTextArea("Error");
    		barraframe.getContentPane().add(msg); 
    		msg.setEditable(false);
    		msg.setRows(1);
    		msg.setEditable(false);
        	barraframe.setVisible(true);
        }
        	
    }
    catch (Exception e) {
        e.printStackTrace();
        }
    }

	private static boolean checkIntegrity(SokoPieces[][] map) {
		
		int goal=0, box=0, me=0, k=0;
		boolean err=false;
		
		JTextArea msg=new JTextArea("Checking map integrity...");
		msg.setEditable(false);
		msg.setRows(1);
		//JProgressBar progressBar = new JProgressBar(0, 100);
		//barraframe.getContentPane().add(progressBar); 
		barraframe.getContentPane().add(msg); 
		//JTextField msg=new JTextField();
		msg.setEditable(false);
    	barraframe.setVisible(true);
		
		//progressBar.setValue(k);
		
		for(int i=0; i<map.length; i++)
			for(int j=0; j<map[i].length; j++){
				if(map[i][j]==SokoPieces.dollar || map[i][j]==SokoPieces.occupied)
					box++;
				if(map[i][j]==SokoPieces.goal || map[i][j]==SokoPieces.occupied || map[i][j]==SokoPieces.megoal)
					goal++;
				if(map[i][j]==SokoPieces.me || map[i][j]==SokoPieces.megoal)
					me++;
				//progressBar.setValue((k++)/(map.length*map[0].length));
			}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {}
		if(me<1){
			msg.setText("Too fiew sokoban defined");
			//System.err.println("Too many sokoban defined");
			err=true;
		}
		if(me>1){
			msg.setText("Too many sokoban defined");
			//System.err.println("Too many sokoban defined");
			err=true;
		}
		if(goal<p.blocchiMobili){
			msg.setText("Too fiew goal defined");
			//System.err.println("Too fiew goal defined");
			err=true;
		}
		if(goal>p.blocchiMobili){
			msg.setText("Too many goal defined");
			//System.err.println("Too may goal defined");
			err=true;
		}
		if(box<p.blocchiMobili){
			msg.setText("Too fiew box defined");
			//System.err.println("Too fiew box defined "+box+ goal);
			err=true;
		}
		if(box>p.blocchiMobili){
			msg.setText("Too many box defined");
			//System.err.println("Too may box defined");
			err=true;
		}
		if(err){
			msg.setForeground(Color.RED);
			barraframe.getContentPane().add(msg);
			barraframe.repaint();
			return false;
		}
			
		return true;
	}
}
