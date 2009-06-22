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

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class Sokoban extends Applet {

	/* Esempi di livelli in formato di stringa
	
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
	

	private static final long serialVersionUID = -7627879419613904118L;


	SokoPieces levels[] ;
			

	final static String images[]= {	"muro_16.gif",
									"sokoban_16.gif",
									"ball_16.jpg",
									"hole_16.jpg",
									"floor.gif",
									"hole_ball_16.jpg",
									"hole_soko_16.jpg"
									};		

	Image tiles[] = new Image[9];
	
	SokoPieces levelS[];
	
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
		
		for(int i=0, k=0; i<mappa.length; i++){
			for(int j=0; j<mappa[i].length; j++, k++)
				newMap[k]=mappa[i][j];
			newMap[k]=SokoPieces.cr;
			k++;
			}
		
		return newMap;
	}


	public void init() {
		setSize(800, 600);
		
		MediaTracker tracker = new MediaTracker(this);
		
		SokoPieces tile[] ={SokoPieces.wall, 
							SokoPieces.me,
							SokoPieces.dollar,
							SokoPieces.goal,
							SokoPieces.floor,
							SokoPieces.occupied,
							SokoPieces.megoal};
		Image j=null;
		
		for (int i = 0; i < tile.length; i++) {
			ImageIcon ji = new ImageIcon("img/"+images[i]);
			j=ji.getImage();
			tracker.addImage(j,i);
			try { tracker.waitForAll(); } catch (InterruptedException e) {}
			tiles[tile[i].ordinal()] =j;
		
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
		}
		
		public synchronized void update(Graphics g) {
			Dimension d = getSize();
			
			if (d.width * d.height == 0) return; 
			Rectangle r = g.getClipBounds();
			
			if (r.x < 72) { 
				g.setColor(Color.lightGray);
				g.fillRect(0, 0, d.width, d.height);
				g.setFont(fontb);
				g.setColor(Color.blue);
				g.drawString("Sokoban",0,16);
				g.setFont(font);
				g.setColor(Color.black);
				String help[] = { "h=Left", "j=Down", "k=Up", "l=Right", " (or Arrows)",
					"H,J,K,L=", " FastMove", "u=Undo", "A=Restart", "S=Save", "R=Restore"};
				for (int i = 0; i < help.length; i++)
					g.drawString(help[i], 0, 80 + 16 * i);
				g.setFont(fontb);
				g.drawString("Level:", 0, 32);
				g.drawString("Move:", 0, 48);
				g.drawString("Push:", 0, 64);
				drawStatus(g);
			}
			
			int y = -16 + h, x = -16 + w;
			for (int i = 0; i < levelS.length; i++)
				if (levelS[i] == SokoPieces.cr) {
					x = -16 + w; y += 16;
				} else {
					x += 16;
					if (levelS[i] == SokoPieces.blank) continue;
					if (r.contains(x,y)) {
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
			drawStatus(g); 
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
						ImageIcon im=new ImageIcon("img/smile-logo.png");
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