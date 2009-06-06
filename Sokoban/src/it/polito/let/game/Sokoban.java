package it.polito.let.game;


import it.polito.let.util.SokoPieces;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Rectangle;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class Sokoban extends Applet {

	/*String levels[] = {
	
		"M^^^^#####" +
		"M^^^^#   #" +
		"M^^^^#$  #" +
		"M^^###  $##" +
		"M^^#  $ $ #" +
		"M### # ## #^^^######" +
		"M#   # ## #####  ..#" +
		"M# $  $          ..#" +
		"M##### ### #@##  ..#" +
		"M^^^^#     #########" +
		"M^^^^#######",
		
		"M############" +
		"M#..  #     ###" +
		"M#..  # $  $  #" +
		"M#..  #$####  #" +
		"M#..    @ ##  #" +
		"M#..  # #  $ ##" +
		"M###### ##$ $ #" +
		"M^^# $  $ $ $ #" +
		"M^^#    #     #" +
		"M^^############",
		
		"M^^^^^^^^########" +
		"M^^^^^^^^#     @#" +
		"M^^^^^^^^# $#$ ##" +
		"M^^^^^^^^# $  $#" +
		"M^^^^^^^^##$ $ #" +
		"M######### $ # ###" +
		"M#....  ## $  $  #" +
		"M##...    $  $   #" +
		"M#....  ##########" +
		"M########M",
		
		"M^^^^^^^^^^^########" +
		"M^^^^^^^^^^^#  ....#" +
		"M############  ....#" +
		"M#    #  $ $   ....#" +
		"M# $$$#$  $ #  ....#" +
		"M#  $     $ #  ....#" +
		"M# $$ #$ $ $########" +
		"M#  $ #     #" +
		"M## #########" +
		"M#    #    ##" +
		"M#     $   ##" +
		"M#  $$#$$  @#" +
		"M#    #    ##" +
		"M###########",
		
		"M^^^^^^^^#####" +
		"M^^^^^^^^#   #####" +
		"M^^^^^^^^# #$##  #" +
		"M^^^^^^^^#     $ #" +
		"M######### ###   #" +
		"M#....  ## $  $###" +
		"M#....    $ $$ ##" +
		"M#....  ##$  $ @#" +
		"M#########  $  ##" +
		"M^^^^^^^^# $ $  #" +
		"M^^^^^^^^### ## #" +
		"M^^^^^^^^^^#    #" +
		"M^^^^^^^^^^######",
		
		"M######^^###" +
		"M#..  #^##@##" +
		"M#..  ###   #" +
		"M#..     $$ #" +
		"M#..  # # $ #" +
		"M#..### # $ #" +
		"M#### $ #$  #" +
		"M^^^#  $# $ #" +
		"M^^^# $  $  #" +
		"M^^^#  ##   #" +
		"M^^^#########",
		
		"M^^^^^^^#####" +
		"M^#######   ##" +
		"M## # @## $$ #" +
		"M#    $      #" +
		"M#  $  ###   #" +
		"M### #####$###" +
		"M# $  ### ..#" +
		"M# $ $ $ ...#" +
		"M#    ###...#" +
		"M# $$ #^#...#" +
		"M#  ###^#####" +
		"M####",
		
		"M^^^^^^^^^^#######" +
		"M^^^^^^^^^^#  ...#" +
		"M^^^^^^#####  ...#" +
		"M^^^^^^#      . .#" +
		"M^^^^^^#  ##  ...#" +
		"M^^^^^^## ##  ...#" +
		"M^^^^^### ########" +
		"M^^^^^# $$$ ##" +
		"M^#####  $ $ #####" +
		"M##   #$ $   #   #" +
		"M#@ $  $    $  $ #" +
		"M###### $$ $ #####" +
		"M^^^^^#      #" +
		"M^^^^^########",
		
		"M^###^^#############" +
		"M##@####       #   #" +
		"M# $$   $$  $ $ ...#" +
		"M#  $$$#    $  #...#" +
		"M# $   # $$ $$ #...#" +
		"M###   #  $    #...#" +
		"M#     # $ $ $ #...#" +
		"M#    ###### ###...#" +
		"M## #  #  $ $  #...#" +
		"M#  ## # $$ $ $##..#" +
		"M# ..# #  $      #.#" +
		"M# ..# # $$$ $$$ #.#" +
		"M##### #       # #.#" +
		"M^^^^# ######### #.#" +
		"M^^^^#           #.#" +
		"M^^^^###############",
		
		"M^^^^^^^^^^####" +
		"M^^^^^####^#  #" +
		"M^^^### @###$ #" +
		"M^^##      $  #" +
		"M^##  $ $$## ##" +
		"M^#  #$##     #" +
		"M^# # $ $$ # ###" +
		"M^#   $ #  # $ #####" +
		"M####    #  $$ #   #" +
		"M#### ## $         #" +
		"M#.    ###  ########" +
		"M#.. ..#^####" +
		"M#...#.#" +
		"M#.....#" +
		"M#######",
		
		"M^^####" +
		"M^^#  ###########" +
		"M^^#    $   $ $ #" +
		"M^^# $# $ #  $  #" +
		"M^^#  $ $  #    #" +
		"M### $# #  #### #" +
		"M#@#$ $ $  ##   #" +
		"M#    $ #$#   # #" +
		"M#   $    $ $ $ #" +
		"M^####  #########" +
		"M^^#      #" +
		"M^^#      #" +
		"M^^#......#" +
		"M^^#......#" +
		"M^^#......#" +
		"M^^########",

		"M################" +
		"M#              #" +
		"M# # ######     #" +
		"M# #  $ $ $ $#  #" +
		"M# #   $@$   ## ##" +
		"M# #  $ $ $###...#" +
		"M# #   $ $  ##...#" +
		"M# ###$$$ $ ##...#" +
		"M#     # ## ##...#" +
		"M#####   ## ##...#" +
		"M^^^^#####     ###" +
		"M^^^^^^^^#     #" +
		"M^^^^^^^^#######",

		"M^^^#########" +
		"M^^##   ##  #####" +
		"M###     #  #    ###" +
		"M#  $ #$ #  #  ... #" +
		"M# # $#@$## # #.#. #" +
		"M#  # #$  #    . . #" +
		"M# $    $ # # #.#. #" +
		"M#   ##  ##$ $ . . #" +
		"M# $ #   #  #$#.#. #" +
		"M## $  $   $  $... #" +
		"M^#$ ######    ##  #" +
		"M^#  #^^^^##########" +
		"M^####",

		"M^^^^^^^#######" +
		"M^#######     #" +
		"M^#     # $@$ #" +
		"M^#$$ #   #########" +
		"M^# ###......##   #" +
		"M^#   $......## # #" +
		"M^# ###......     #" +
		"M##   #### ### #$##" +
		"M#  #$   #  $  # #" +
		"M#  $ $$$  # $## #" +
		"M#   $ $ ###$$ # #" +
		"M#####     $   # #" +
		"M^^^^### ###   # #" +
		"M^^^^^^#     #   #" +
		"M^^^^^^########  #" +
		"M^^^^^^^^^^^^^####",

		"M^^^^#######" +
		"M^^^#   #  #" +
		"M^^^#  $   #" +
		"M^### #$   ####" +
		"M^#  $  ##$   #" +
		"M^#  # @ $ # $#" +
		"M^#  #      $ ####" +
		"M^## ####$##     #" +
		"M^# $#.....# #   #" +
		"M^#  $..**. $# ###" +
		"M##  #.....#   #" +
		"M#   ### #######" +
		"M# $$  #  #" +
		"M#  #     #" +
		"M######   #" +
		"M^^^^^#####",

		"M#####" +
		"M#   ##" +
		"M#    #^^####" +
		"M# $  ####  #" +
		"M#  $$ $   $#" +
		"M###@ #$    ##" +
		"M^#  ##  $ $ ##" +
		"M^# $  ## ## .#" +
		"M^#  #$##$  #.#" +
		"M^###   $..##.#" +
		"M^^#    #.*...#" +
		"M^^# $$ #.....#" +
		"M^^#  #########" +
		"M^^#  #" +
		"M^^####",

		"M^^^##########" +
		"M^^^#..  #   #" +
		"M^^^#..      #" +
		"M^^^#..  #  ####" +
		"M^^#######  #  ##" +
		"M^^#            #" +
		"M^^#  #  ##  #  #" +
		"M#### ##  #### ##" +
		"M#  $  ##### #  #" +
		"M# # $  $  # $  #" +
		"M# @$  $   #   ##" +
		"M#### ## #######" +
		"M^^^#    #" +
		"M^^^######",

		"M^^^^^###########" +
		"M^^^^^#  .  #   #" +
		"M^^^^^# #.    @ #" +
		"M^##### ##..# ####" +
		"M##  # ..###     ###" +
		"M# $ #...   $ #  $ #" +
		"M#    .. ##  ## ## #" +
		"M####$##$# $ #   # #" +
		"M^^## #    #$ $$ # #" +
		"M^^#  $ # #  # $## #" +
		"M^^#               #" +
		"M^^#  ###########  #" +
		"M^^####^^^^^^^^^####",

		"M^^######" +
		"M^^#   @####" +
		"M##### $   #" +
		"M#   ##    ####" +
		"M# $ #  ##    #" +
		"M# $ #  ##### #" +
		"M## $  $    # #" +
		"M## $ $ ### # #" +
		"M## #  $  # # #" +
		"M## # #$#   # #" +
		"M## ###   # # ######" +
		"M#  $  #### # #....#" +
		"M#    $    $   ..#.#" +
		"M####$  $# $   ....#" +
		"M#       #  ## ....#" +
		"M###################",

		"M^^^^##########" +
		"M#####        ####" +
		"M#     #   $  #@ #" +
		"M# #######$####  ###" +
		"M# #    ## #  #$ ..#" +
		"M# # $     #  #  #.#" +
		"M# # $  #     #$ ..#" +
		"M# #  ### ##     #.#" +
		"M# ###  #  #  #$ ..#" +
		"M# #    #  ####  #.#" +
		"M# #$   $  $  #$ ..#" +
		"M#    $ # $ $ #  #.#" +
		"M#### $###    #$ ..#" +
		"M^^^#    $$ ###....#" +
		"M^^^#      ##^######" +
		"M^^^########"
	
	};*/
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7627879419613904118L;


	SokoPieces levels[] /*= { 
							{//SokoPieces.cr,
							//SokoPieces.blank, 
							SokoPieces.wall, 
							SokoPieces.wall,
							SokoPieces.cr,
							SokoPieces.wall,
							SokoPieces.me,
							SokoPieces.cr,
							SokoPieces.goal,
							SokoPieces.goal},
							
							{//SokoPieces.cr,
							//SokoPieces.blank, 
							SokoPieces.wall, 
							SokoPieces.wall,
							//SokoPieces.cr,
							SokoPieces.wall,
							SokoPieces.me,
							SokoPieces.goal,
							SokoPieces.goal}
							}*/;
			
	
	final static String images[]= {	"muro_16.gif",
									"sokoban_16.gif",
									"ball_16.gif",
									"goal_16.gif",
									"floor.gif",
									"occupied_16.gif",
									"megoal_16.gif"
									};		
	/*final static char wall = '#';
	final static char floor = ' ';
	final static char me = '@';
	final static char megoal = '&';
	final static char occupied = '*';
	final static char dollar = '$'; //ball
	final static char cr = 'M';
	final static char blank = '^';
	final static char goal = '.';*/
	Image tiles[] = new Image[9];
	
	//AudioClip buzz, wow;
	InputStream in1 = null;
	AudioStream wow=null;
	InputStream in2 = null;
	AudioStream doh=null;
	
	
	SokoPieces levelS[];
	//char[] level;
	int currlevel, w, h, push, move;
	int lastcount, pos1, pos2, pos3;
	Rectangle lastrect;
	boolean uc;
	
	SokoPieces savelevelS[];
	int savecurrlevel, savew, saveh, savepush, savemove;
	boolean gamesaved = false;
	
	Font font = new Font("Helvetica", Font.PLAIN, 12);
	Font fontb = new Font("Helvetica", Font.BOLD, 12);
	
	public Sokoban(SokoPieces[][] mappa){
		levels = convertMap(mappa);
		
	}
	
	
	private SokoPieces[] convertMap(SokoPieces[][] mappa) {
		
		h=(mappa.length);
		w=(mappa[0].length+1);
		SokoPieces[] newMap=new SokoPieces[h*w];
		//System.out.println("creo mappa "+w+"x"+h);
		for(int i=0, k=0; i<mappa.length; i++){
			for(int j=0; j<mappa[i].length; j++, k++)
				newMap[k]=mappa[i][j];
			newMap[k]=SokoPieces.cr;
			k++;
			}
		
		/*for(int i=0; i<newMap.length; i++){
			System.out.println("newMap["+i/w+"]["+i%w+"]"+newMap[i]);
		}*/
		return newMap;
	}


	public void init() {
		setSize(800, 600);
		/************/
		
		try {
			//in1 = new FileInputStream("audio/doh.wav");
			//doh = new AudioStream(in1);   
			in2 = new FileInputStream("audio/woohoo.wav");
			wow = new AudioStream(in2);   
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	

		/************/
		//buzz = getAudioClip(getDocumentBase(), "doh.wav");
		//wow = getAudioClip(getDocumentBase(), "woohoo.wav");
		MediaTracker tracker = new MediaTracker(this);
		
		
		//String tile = "# @$.&*";
		SokoPieces tile[] ={SokoPieces.wall, 
							SokoPieces.me,
							SokoPieces.dollar,
							SokoPieces.goal,
							SokoPieces.floor,
							SokoPieces.occupied,
							SokoPieces.megoal};
		//Graphics g;
		
		//for (int i = 0; i < tile.length(); i++) {
		Image j=null;
		//URL url=null;
		/*try {
			String resource1 = images[0];
			// safest to use context class loader
			//url = Thread.currentThread().setContextClassLoader().getResource(resource1);

		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}*/
			//String tile = "#@$.";
			for (int i = 0; i < tile.length; i++) {
				//j = getImage(getCodeBase(),"../img/"+images[i]);
				//File f=new File("img/"+images[i]);
				//if(!f.exists()) System.out.println("err");
				ImageIcon ji = new ImageIcon("img/"+images[i]);
				//System.out.println(ji);
				j=ji.getImage();
					//System.out.println(j);
				tracker.addImage(j,i);
				try { tracker.waitForAll(); } catch (InterruptedException e) {}
				tiles[tile[i].ordinal()] =j;
				//tiles[tile[i].ordinal()] = createImage(16, 16);
				//Graphics g = tiles[tile[i].ordinal()].getGraphics();
				//g.drawImage(j, -i*16, 0, this);
			}			
			j.flush();
			
			newLevel(0);
			requestFocus();
		}
		
		public void start() {}
		
		public void stop() {}
		
		public void destroy() {}
			
		public void paint(Graphics g) {
			update(g);
			/*Image j=null, tiles[]=new Image[4];
			for (int i = 0; i < 4; i++) {
				j = getImage(getDocumentBase(),"../img/"+images[i]);
				MediaTracker tracker=new MediaTracker(this);
				tracker.addImage(j,0);
				try { tracker.waitForAll(); } catch (InterruptedException e) {}
				tiles[i]=j;
				g.drawImage(tiles[i],20+i*16,20,this);
			}*/
		}
		
		public synchronized void update(Graphics g) {
			Dimension d = getSize();
			//System.out.println("dim="+d);
			if (d.width * d.height == 0) return; // supposedly this can happen!
			Rectangle r = g.getClipBounds();
			//System.out.println("rect="+r);
			if (r.x < 72) { // only do this if necessary!
				g.setColor(Color.lightGray);
				g.fillRect(0, 0, d.width, d.height);
				g.setFont(fontb);
				g.setColor(Color.blue);
				g.drawString("Sokoban",0,16);
				g.setFont(font);
				g.setColor(Color.black);
				String help[] = { "h=Left", "j=Down", "k=Up", "l=Right", " (or Arrows)",
					"H,J,K,L=", " FastMove", "u=Undo", "A=Restart", "S=Save", "R=Restore",
					"+=UpLevel", "-=DownLevel" };
				for (int i = 0; i < help.length; i++)
					g.drawString(help[i], 0, 80 + 16 * i);
				g.setFont(fontb);
				g.drawString("Level:", 0, 32);
				g.drawString("Move:", 0, 48);
				g.drawString("Push:", 0, 64);
				drawStatus(g);
			}
			//System.out.println("h="+h+"; w="+w);
			int y = -16 + h, x = -16 + w;
			for (int i = 0; i < levelS.length; i++)
				if (levelS[i] == SokoPieces.cr) {
					x = -16 + w; y += 16;
				} else {
					x += 16;
					if (levelS[i] == SokoPieces.blank) continue;
					if (r.contains(x,y)) {// only draw the images necessary for move!
						//System.out.println("("+x/16+","+y/16+")="+levelS[i]);
						int k=levelS[i].ordinal();
						
						g.drawImage(tiles[k], x, y, this);
					}
				}
		}
		
		public void drawStatus(Graphics g) {
			g.setColor(Color.lightGray);
			g.fillRect(40, 16, 32, 48);
			g.setColor(Color.black);
			g.setFont(font);
			g.drawString("" + (currlevel + 1), 40, 32);
			g.drawString("" + move, 40, 48);
			g.drawString("" + push, 40, 64);
		}
		
		public void drawMove() {
			Graphics g = getGraphics();
			drawStatus(g); // order is important, since update munges the clipRect
			repaint(lastrect.x, lastrect.y, lastrect.width, lastrect.height);
		}
			
		public boolean keyDown(Event e, int key) {
			uc = false;
			int scelta;
			switch (e.key) {
				case 'H': uc = true;
				case 'h': case Event.LEFT: movearound(-1, 0); break;
				case 'L': uc = true;
				case 'l': case Event.RIGHT: movearound(1, 0); break;
				case 'K': uc = true;
				case 'k': case Event.UP: movearound(0, -1); break;
				case 'J': uc = true;
				case 'j': case Event.DOWN: movearound(0, 1); break;
				//case '+':
				/*case '-': currlevel += e.key == '+' ? 1 : -1;
							if (currlevel < 0) currlevel = 0;
							else if (currlevel == levels.length) currlevel = levels.length - 1;*/
				case 'a':
				case 'A': 
					scelta=JOptionPane.showConfirmDialog(this, "Vuoi davvero ricominciare da capo?");
					if(scelta==0){
						newLevel(currlevel); repaint(); 
					}
					break;
				case 'u': undomove(); break;
				case 'S': 
					saveGame(); 
					JOptionPane.showMessageDialog(this, "Salvataggio effettuato.",
							"Saving", JOptionPane.INFORMATION_MESSAGE);
					break;
				case 'R': 
					scelta=JOptionPane.showConfirmDialog(this, "Vuoi davvero caricare il salvataggio?");
					if(scelta==0){
						if (gamesaved) restoreGame(); 
						else
							JOptionPane.showMessageDialog(this, "Nessun salvataggio presente.",
									"Attenzione", JOptionPane.ERROR_MESSAGE);
					}
					break;
			}
			return true;
		}
		
		public void newLevel(int l) {
			currlevel = l; push = 0; move = 0;
			//w = 0; h = 0; levelS = levels[currlevel];
			w = 0; h = 0; 
			levelS=new SokoPieces[levels.length];
			System.arraycopy(levels ,0, levelS, 0, levels.length);
			lastcount = 0;
			int W = 0;
			for (int i = 0; i < levelS.length; i++)
				if (levelS[i] == SokoPieces.cr){ 
					if (W > w) 
						w = W; 
					W = 0; 
					h++; 
				}
				else W++;
			Dimension d = getSize();
			w = 72 + (d.width - 72 - 16 * w) / 2; h = (d.height - 16 * h) / 2;
		}
		
		public void restoreGame() {
			currlevel = savecurrlevel;
			w = savew; h = saveh; push = savepush; move = savemove;
			levelS = savelevelS; gamesaved = false;
			repaint();
		}
		
		public void saveGame() {
			savecurrlevel = currlevel;
			savew = w; saveh = h; savepush = push; savemove = move;
			savelevelS = new SokoPieces[levelS.length];
			System.arraycopy(levelS ,0, savelevelS, 0, levelS.length);
			gamesaved = true;
		}
		
		public int	moveone(int pos, int x, int y, int dx, int dy) {
			int i;
			if (dx != 0) return pos + dx;
			else if (dy == -1) for (i = pos - x - 2; levelS[i] != SokoPieces.cr; i--);
			else for (i = pos + 1; levelS[i] != SokoPieces.cr; i++);
			return i + x + 1;
		}
		
		public void movearound(int dx, int dy) {
			do {
				int x = 0, y = -1, savepos1 = pos1, savepos2 = pos2, savepos3 = pos3;
				for (pos1 = 0; pos1 < levelS.length; pos1++)
					if (levelS[pos1] == SokoPieces.cr) { x = 0; y++; }
					else if ((levelS[pos1] != SokoPieces.me) && (levelS[pos1] != SokoPieces.megoal)) x++; else break;
				pos2 = moveone(pos1, x, y, dx, dy);
				int count = 0;
				if (levelS[pos2] == SokoPieces.floor || levelS[pos2] == SokoPieces.goal) count = 1;
				else {
					if (uc) { lastcount = 1; pos1 = savepos1; pos2 = savepos2; break; }
					if (levelS[pos2] == SokoPieces.dollar || levelS[pos2] == SokoPieces.occupied) {
						pos3 = moveone(pos2, x, y, dx, dy);
						if (levelS[pos3] == SokoPieces.floor || levelS[pos3] == SokoPieces.goal) count = 2;
					}
				}
				if (count > 0) {
					levelS[pos1] = levelS[pos1] == SokoPieces.me ? SokoPieces.floor : SokoPieces.goal;
					levelS[pos2] = levelS[pos2] == SokoPieces.floor || levelS[pos2] == SokoPieces.dollar ? SokoPieces.me : SokoPieces.megoal;
					move++;
					if (count > 1) {
						levelS[pos3] = levelS[pos3] == SokoPieces.floor ? SokoPieces.dollar : SokoPieces.occupied;
						push++;
					}
					lastcount = count;
					int xo = x + dx * count, yo = y + dy * count;
					lastrect = new Rectangle(w + Math.min(xo, x) * 16, h + Math.min(yo, y) * 16,
						(Math.abs(xo - x) + 1) * 16, (Math.abs(yo - y) + 1) * 16);
					drawMove();
					boolean b = true;
					for (int i = 0; i < levelS.length; i++) if (levelS[i] == SokoPieces.dollar) b = false;
					if (b) {
						//wow.play();
						AudioPlayer.player.start(wow);
						//AudioPlayer.player.stop(wow); 
						//try { Thread.sleep(2000); } catch (InterruptedException e) {};
						//newLevel(currlevel + 1);
						//repaint();
						ImageIcon im=new ImageIcon("img/cup.png");
						JOptionPane.showMessageDialog(this, "Complimenti hai vinto!",
													"Vittoria!", JOptionPane.PLAIN_MESSAGE, im);
						this.stop();
						SokoCompiler.sokoframe.dispose();
					}
				} else {
					pos1 = savepos1; pos2 = savepos2; pos3 = savepos3;
				}
			} while (uc);
		}
		
		public void undomove() {
			if (lastcount > 0) {
				levelS[pos1] = levelS[pos1] == SokoPieces.floor ? SokoPieces.me : SokoPieces.megoal;
				move--;
				if (lastcount > 1) {
					levelS[pos2] = levelS[pos2] == SokoPieces.me ? SokoPieces.dollar : SokoPieces.occupied;
					levelS[pos3] = levelS[pos3] == SokoPieces.dollar ? SokoPieces.floor : SokoPieces.goal;
					push--;
				} else levelS[pos2] = levelS[pos2] == SokoPieces.me ? SokoPieces.floor : SokoPieces.goal;
				lastcount = 0;
				drawMove();
			}
		}
				
		public String getAppletInfo() {
			return "Sokoban 1.0, Written by me";
		}
}