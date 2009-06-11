package it.polito.let.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import it.polito.let.generated.Lexer;
import it.polito.let.generated.SokoParserCup;
import it.polito.let.util.SokoPieces;

public class SokoCompiler
{
	static private Sokoban sokoapplet;
	static JFrame sokoframe;
	static private JFrame barraframe;
	static private SokoParserCup p;
	static private JTextArea msg;
	
    static public void main(String argv[])
    {
    try {
        // Istanzio lo scanner aprendo il file di ingresso argv[0]
    	System.out.println("Opening file "+argv[0]);
    	Lexer l = new Lexer(new FileReader(argv[0]));
        // Istanzio il parser
        p = new SokoParserCup(l);
        
        Dimension screenSize =Toolkit.getDefaultToolkit().getScreenSize();
        int dimScrX=screenSize.width, dimScrY=screenSize.height;
        barraframe = new JFrame(); 
		barraframe.setSize(400,400); 
		barraframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		barraframe.setLocation((dimScrX-400)/2, (dimScrY-400)/2);
		barraframe.setTitle("Loading...");
       
		// Avvio il parser
        try{	
        	p.parse();
        }catch(IOException ex){
        	JOptionPane.showMessageDialog(barraframe, "E' stato rilevato un errore",
					"Attenzione", JOptionPane.ERROR_MESSAGE);
        	JTextArea msg=new JTextArea(ex.getMessage());
    		barraframe.getContentPane().add(msg); 
    		msg.setEditable(false);
    		msg.setRows(1);
    		msg.setEditable(false);
        	barraframe.setVisible(true);
        	return;
        }
       
        SokoPieces[][] mappa = p.getMap();
		if(!p.HasError()){
        	if(checkIntegrity(mappa) && colorMap(mappa)){
        		barraframe.dispose();
		    	sokoapplet =new Sokoban(mappa);
		    	sokoframe = new JFrame(); 
		    	sokoframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    	sokoframe.setSize(800,600); 
		    	sokoframe.setLocation((dimScrX-800)/2, (dimScrY-600)/2);
		    	sokoframe.setTitle("Sokoban");
		    	
		    	sokoframe.getContentPane().add(sokoapplet, BorderLayout.CENTER); 
		    	 
				sokoapplet.init(); 
				sokoapplet.start(); 
				
				sokoframe.setVisible(true); 
        	}
        }
        else{
        	StringBuffer err=p.getErrList();
        	JOptionPane.showMessageDialog(barraframe, "E' stato rilevato un errore",
					"Attenzione", JOptionPane.ERROR_MESSAGE);
    		JTextArea msg=new JTextArea(err.toString());
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
   
	private static boolean colorMap(SokoPieces[][] map) {
		
		msg.append("Checkig map bounds...\n");
		barraframe.repaint();
		
		for(int i=0; i<map.length; i++){		//controllo a partire da tutti i punti del bordo
			if((map=checkBounds(map, i , 0))==null){ 
				msg.append("Checking bounds FAILED1!");
				return false;
			}
			if((map=checkBounds(map, i , map[0].length-1))==null){ 
				msg.append("Checking bounds FAILED2!");
				return false;
			}
			if((map=checkBounds(map, 0 , i))==null){ 
				msg.append("Checking bounds FAILED3!");
				return false;
			}
			if((map=checkBounds(map, map.length-1 , i))==null){ 
				msg.append("Checking bounds FAILED4!");
				return false;
			}
		}
		msg.append("Checking bounds completed!");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {}
		return true;
	}

	private static SokoPieces[][] checkBounds(SokoPieces[][] Map, int posx, int posy) {
		int[] offq={-1, 0, 0, 0, 1};
		int[] offr={0, -1, 0, 1, 0};

		if(Map[posx][posy]==SokoPieces.wall)
			return Map;
		
		for(int i=0; i<5; i++)
				if ( (posx+offq[i])>=0 && (posy+offr[i])>=0 && 
						(posx+offq[i])<Map.length && (posy+offr[i])<Map[0].length){
					if(Map[posx+offq[i]][posy+offr[i]]!=SokoPieces.blank && 
							Map[posx+offq[i]][posy+offr[i]]!=SokoPieces.wall){
						if(Map[posx+offq[i]][posy+offr[i]]==SokoPieces.floor){
							Map[posx+offq[i]][posy+offr[i]]=SokoPieces.blank;
							if((Map=checkBounds(Map, posx+offq[i], posy+offr[i]))==null)
								return null;
						}
						else {
							msg.append("Errore in Map["+(posx+offq[i])+"]["+(posy+offr[i])+"]="+
									Map[posx+offq[i]+1][posy+offr[i]+1].name()+"\n"); //"+1" user-friendly
							barraframe.repaint();
							return null;	//ho trovato qlcs fuori dal contorno		
						}
					}
				}
		return Map;
	}

	private static boolean checkIntegrity(SokoPieces[][] map) {
		
		int goal=0, box=0, me=0;
		boolean err=false;
		
		msg=new JTextArea("Checking map integrity...");
		msg.setEditable(false);
		barraframe.getContentPane().add(msg); 
		msg.setEditable(false);
    	barraframe.setVisible(true);
		
		for(int i=0; i<map.length; i++)
			for(int j=0; j<map[i].length; j++){
				if(map[i][j]==SokoPieces.dollar || map[i][j]==SokoPieces.occupied)
					box++;
				if(map[i][j]==SokoPieces.goal || map[i][j]==SokoPieces.occupied || map[i][j]==SokoPieces.megoal)
					goal++;
				if(map[i][j]==SokoPieces.me || map[i][j]==SokoPieces.megoal)
					me++;
			}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {}
		if(me<1){
			msg.append("Too fiew sokoban defined");
			err=true;
		}
		if(me>1){
			msg.append("Too many sokoban defined");
			err=true;
		}
		if(goal<p.blocchiMobili){
			msg.append("Too fiew goal defined");
			err=true;
		}
		if(goal>p.blocchiMobili){
			msg.append("Too many goal defined");
			err=true;
		}
		if(box<p.blocchiMobili){
			msg.append("Too fiew box defined");
			err=true;
		}
		if(box>p.blocchiMobili){
			msg.append("Too many box defined");
			err=true;
		}
		if(err){
			msg.setForeground(Color.RED);
			msg.append("\nMap integrity FAILED!");
			barraframe.repaint();
			return false;
		}
		msg.append("\nMap integrity completed\n");
		barraframe.repaint();
		return true;
	}
}
