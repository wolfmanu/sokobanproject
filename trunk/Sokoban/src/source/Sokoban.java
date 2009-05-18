package source;

import java.io.File;
import java.io.IOException;
import java.lang.*;
import java.net.URL;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.applet.*;

import javax.imageio.ImageIO;

public class Sokoban extends Applet {

	String levels[] = {
	
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
	
	};
	
	final static String images[]= {"muro_16.gif",
									"sokoban_16.gif",
									"ball_16.gif",
									"goal_16.gif"
									};		//manca soko_goal, occupato, 
	final static char wall = '#';
	final static char floor = ' ';
	final static char me = '@';
	final static char megoal = '&';
	final static char occupied = '*';
	final static char dollar = '$'; //ball
	final static char cr = 'M';
	final static char blank = '^';
	final static char goal = '.';
	Image tiles[] = new Image[128];
	
	AudioClip buzz, wow;
	
	char[] level;
	int currlevel, w, h, push, move;
	int lastcount, pos1, pos2, pos3;
	Rectangle lastrect;
	boolean uc;
	
	char savelevel[];
	int savecurrlevel, savew, saveh, savepush, savemove;
	boolean gamesaved = false;
	
	Font font = new Font("Helvetica", Font.PLAIN, 12);
	Font fontb = new Font("Helvetica", Font.BOLD, 12);
	
	public void init() {
		//buzz = getAudioClip(getDocumentBase(), "buzz.au");
		//wow = getAudioClip(getDocumentBase(), "wow.au");
		MediaTracker tracker = new MediaTracker(this);
		
		
		//String tile = "# @$.&*";
		String tile = "#@$.";
		//Graphics g;
		//int i = 0;
		//for (int i = 0; i < tile.length(); i++) {
		//TODO Applet cannot return code base if it does not have an AppletStub. Only AppletStub has information about code base. So you should imlement your own AppletStub and set it for your Applet. For this a special method Applet.setStub(AppletStub stub) exists.
		Image j=null;
		
		j = getImage(getDocumentBase(),"../img/"+images[0]);
		
	
		tracker.addImage(j,0);
		try { tracker.waitForAll(); } catch (InterruptedException e) {}
			//String tile = "#@$.";
			for (int i = 1; i < tile.length(); i++) {
				
				tiles[(int) tile.charAt(i)] = createImage(16, 16);
				Graphics g = tiles[(int) tile.charAt(i)].getGraphics();
				g.drawImage(j, -i*16, 0, this);
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
			Dimension d = size();
			if (d.width * d.height == 0) return; // supposedly this can happen!
			Rectangle r = g.getClipRect();

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

			int y = -16 + h, x = -16 + w;
			for (int i = 0; i < level.length; i++)
				if (level[i] == cr) {
					x = -16 + w; y += 16;
				} else {
					x += 16;
					if (level[i] == blank) continue;
					if (r.inside(x,y)) // only draw the images necessary for move!
						g.drawImage(tiles[(int) level[i]], x, y, this);
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
			switch (e.key) {
				case 'H': uc = true;
				case 'h': case Event.LEFT: movearound(-1, 0); break;
				case 'L': uc = true;
				case 'l': case Event.RIGHT: movearound(1, 0); break;
				case 'K': uc = true;
				case 'k': case Event.UP: movearound(0, -1); break;
				case 'J': uc = true;
				case 'j': case Event.DOWN: movearound(0, 1); break;
				case '+':
				case '-': currlevel += e.key == '+' ? 1 : -1;
							if (currlevel < 0) currlevel = 0;
							else if (currlevel == levels.length) currlevel = levels.length - 1;
				case 'A': newLevel(currlevel); repaint(); break;
				case 'u': undomove(); break;
				case 'S': saveGame(); break;
				case 'R': if (gamesaved) restoreGame(); break;
			}
			return true;
		}
		
		public void newLevel(int l) {
			currlevel = l; push = 0; move = 0;
			w = 0; h = 0; level = levels[currlevel].toCharArray();
			lastcount = 0;
			int W = 0;
			for (int i = 0; i < level.length; i++)
				if (level[i] == cr)
					{ if (W > w) w = W; W = 0; h++; }
				else W++;
			Dimension d = size();
			w = 72 + (d.width - 72 - 16 * w) / 2; h = (d.height - 16 * h) / 2;
		}
		
		public void restoreGame() {
			currlevel = savecurrlevel;
			w = savew; h = saveh; push = savepush; move = savemove;
			level = savelevel; gamesaved = false;
			repaint();
		}
		
		public void saveGame() {
			savecurrlevel = currlevel;
			savew = w; saveh = h; savepush = push; savemove = move;
			savelevel = new char[level.length];
			System.arraycopy(level ,0, savelevel, 0, level.length);
			gamesaved = true;
		}
		
		public int	moveone(int pos, int x, int y, int dx, int dy) {
			int i;
			if (dx != 0) return pos + dx;
			else if (dy == -1) for (i = pos - x - 2; level[i] != cr; i--);
			else for (i = pos + 1; level[i] != cr; i++);
			return i + x + 1;
		}
		
		public void movearound(int dx, int dy) {
			do {
				int x = 0, y = -1, savepos1 = pos1, savepos2 = pos2, savepos3 = pos3;
				for (pos1 = 0; pos1 < level.length; pos1++)
					if (level[pos1] == cr) { x = 0; y++; }
					else if ((level[pos1] != me) && (level[pos1] != megoal)) x++; else break;
				pos2 = moveone(pos1, x, y, dx, dy);
				int count = 0;
				if (level[pos2] == floor || level[pos2] == goal) count = 1;
				else {
					if (uc) { lastcount = 1; pos1 = savepos1; pos2 = savepos2; break; }
					if (level[pos2] == dollar || level[pos2] == occupied) {
						pos3 = moveone(pos2, x, y, dx, dy);
						if (level[pos3] == floor || level[pos3] == goal) count = 2;
					}
				}
				if (count > 0) {
					level[pos1] = level[pos1] == me ? floor : goal;
					level[pos2] = level[pos2] == floor || level[pos2] == dollar ? me : megoal;
					move++;
					if (count > 1) {
						level[pos3] = level[pos3] == floor ? dollar : occupied;
						push++;
					}
					lastcount = count;
					int xo = x + dx * count, yo = y + dy * count;
					lastrect = new Rectangle(w + Math.min(xo, x) * 16, h + Math.min(yo, y) * 16,
						(Math.abs(xo - x) + 1) * 16, (Math.abs(yo - y) + 1) * 16);
					drawMove();
					boolean b = true;
					for (int i = 0; i < level.length; i++) if (level[i] == dollar) b = false;
					if (b) {
						wow.play();
						try { Thread.sleep(2000); } catch (InterruptedException e) {};
						newLevel(currlevel + 1);
						repaint();
					}
				} else {
					pos1 = savepos1; pos2 = savepos2; pos3 = savepos3;
					buzz.play();
				}
			} while (uc);
		}
		
		public void undomove() {
			if (lastcount > 0) {
				level[pos1] = level[pos1] == floor ? me : megoal;
				move--;
				if (lastcount > 1) {
					level[pos2] = level[pos2] == me ? dollar : occupied;
					level[pos3] = level[pos3] == dollar ? floor : goal;
					push--;
				} else level[pos2] = level[pos2] == me ? floor : goal;
				lastcount = 0;
				drawMove();
			}
		}
				
		public String getAppletInfo() {
			return "Sokoban 1.0, Written by me";
		}
}