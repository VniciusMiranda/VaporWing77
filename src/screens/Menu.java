package screens;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

import game.Audio;
import game.Game;
import game.ID;
import game.Paint;
import game.RenderHandler;
import game.Score;
import game_objects.Player;


public class Menu extends MouseAdapter {
	
	
	// VARIABLES *******************************************************************************************************************************************
	
	
	private RenderHandler handler;
	private Audio audio = new Audio();
	private String title = "data/airstrike.ttf";
	private boolean load1, load2, load3, spaceshipPosition = true, soundEffect = true;
	private int x = -400, stringY = -200, size = 10, velX = 60, y=537;
	private int sizeMapsButton = 50, xMaps = 113 , yMaps = 314;
	private int sizeQuitButton = 50, xQuit = 127, yQuit = 464;
	private int sizePlayButton = 50, xPlay = 120, yPlay = 164;
	private boolean mapsButton, playButton, quitButton;
	private double ang = 1.57;
	private Timer tempo = new Timer();
	private Paint paint = new Paint();
	
	
	// CONSTRUCTOR *******************************************************************************************************************************************
	
	
	// CONSTRUCTOR *******************************************************************************************************************************************
	
	
	public Menu(RenderHandler handler) {
		
		this.handler = handler;
		
	}
	
	
	// MOUSE PRESSED METHOD *******************************************************************************************************************************************
	
	
	public void mousePressed(MouseEvent e) {
		
		
		// This variables return a integer value of mouse position 
		
		
		int mx = e.getX();
		int my = e.getY();
		
		
		// This condition prevents the buttons to be accessed in others screens
		
		
		if(Game.gameState == ID.Menu) {
			
			
			// Play button .........................................................................
			
			
			if(mouseOver(mx, my, 100, 100, 180, 100)) {
				
				sizePlayButton = 40;
				xPlay = 133;
				yPlay = 161;
				playButton = true;
					
			}
			
			
			// Maps button .........................................................................
			
			
			if(mouseOver(mx, my, 100, 250, 180, 100)) {
				
				sizeMapsButton = 40;
				xMaps = 129;
				yMaps = 311;
				mapsButton = true;
				
			}
			
			
			// Quit button .........................................................................
			
			
			if(mouseOver(mx, my, 100, 400, 180, 100)) {
				
				sizeQuitButton = 40;
				xQuit = 138;
				yQuit = 461;
				quitButton = true;
				
			}
			
			
		}
	}
		// MOUSE CLICKED METHOD *******************************************************************************************************************************************
	
	

	
	// MOUSE RELEASED METHOD *******************************************************************************************************************************************
	
	
	public void mouseReleased(MouseEvent e) {

		
		// This variables return a integer value of mouse position 
		
		
		int mx = e.getX();
		int my = e.getY();
		
		
		// This condition prevents the buttons to be accessed in others screens
		
		
		if(Game.gameState == ID.Menu) {
			
			
			// Play button .........................................................................
			
			
			if(mouseOver(mx, my, 100, 100, 180, 100)) {
				
				if(playButton) {
				
					handler.addObject(new Player(720, 325, ID.Player, handler));	
					Score.score = 0;
					Game.gameState = ID.Game;
					audio.playingOnce("data/sound_effects/fire_sound.wav");
				
				}
			}
			
			
			// Maps button .........................................................................
			
			
			if(mouseOver(mx, my, 100, 250, 180, 100)) {
				
				if(mapsButton) {
					
					Game.gameState = ID.Maps;
					audio.playingOnce("data/sound_effects/button1.wav");
					
				}
				
			}
			
			
			// Quit button .........................................................................
			
			
			if(mouseOver(mx, my, 100, 400, 180, 100)) {
				
				if(quitButton) {
					
					audio.playingOnce("data/sound_effects/button1.wav");
					System.exit(0);
					
				}
			}
			
			
			mapsButton = false;
			playButton = false;
			quitButton = false;
			
			sizePlayButton = 50;
			xPlay = 120;
			yPlay = 164;
			
			sizeMapsButton = 50;
			xMaps = 113;
			yMaps = 314;
			
			sizeQuitButton = 50;
			xQuit = 127;
			yQuit = 464;
			
			
		}
		
		
	}
	
	
	// MOUSE MOVED METHOD *******************************************************************************************************************************************
	
	
	// MOUSE MOVED METHOD *******************************************************************************************************************************************
	
	
	public void mouseMoved(MouseEvent e){
		
		
		// This variables return a integer value of mouse position 
		
		
		int mx = e.getX();
		int my = e.getY();
		
		
		// This condition prevents the buttons to be accessed in others screens
		
		
		if(Game.gameState == ID.Menu) {
			
			
			// Condition to enable the buttons at right time
			
			
			if(load3) {
				
			
				// Play button .........................................................................
				
				
				if(mouseOver(mx, my, 100, 100, 180, 100)) {
					
					y = 75;
	
				}
				
				
				// Maps button .........................................................................
				
				
				if(mouseOver(mx, my, 100, 250, 180, 100)) {
						
					y = 225;
					
				}
				
				
				// Exit button .........................................................................
				
				
				if(mouseOver(mx, my, 100, 400, 180, 100)) {
					
					y = 375;
					
				}
			
			
			}
			
		}
		
		
	}
	
	
	// MOUSEOVER METHOD *******************************************************************************************************************************************
	
	
	// MOUSEOVER METHOD *******************************************************************************************************************************************
	// The function of this method is to limit a region in the plan of the frame and return true if a coordinate in the plan is in that specific range
	
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if(mx > x && mx < x + width) {
			if(my > y && my < y + height) {
				return true;
			}else return false;
		} else return false;
	}

	
	// RENDER METHOD *******************************************************************************************************************************************
	
	
	public void render(Graphics g) {
		
		
		// Background and Title .........................................................................
		
		
		Graphics2D g2 = (Graphics2D) g.create();
		g2.drawImage(Paint.menuBackground, 0, 0, Game.width, Game.height, null);
		g2.setColor(new Color(86,0,0));
		g2.setFont(Paint.fonte(title,size));
		g2.drawString("Vapor", 105, stringY);
		g2.drawString("Wing77", 611, stringY);
		
		
			// String
		
		
			stringY+=5;
			size++;
		
		
			if(stringY>=650) {
				stringY=650;
			}	
			if(size >= 140) {
				size = 140;
			}
			
			
			// Tasks
			
			
			TimerTask task1 = new TimerTask() {
				@Override
				public void run() {
					title = "data/airstrikegrad.ttf";
				}
			};
			TimerTask task2 = new TimerTask() {
				@Override
				public void run() {
					load1 = true;
				}
			};
			TimerTask task3 = new TimerTask() {
				@Override
				public void run() {
					load2 = true;
				}
			};
			
			
			// Loads
			
			
			// Draw the spaceShip and set the velocity
			if(load1) {
				g2.drawImage(paint.rotateImage(Paint.naveGrande, ang), x, y, 150, 150, null);
				x+=velX;
				if(soundEffect) {
					audio.playingOnce("data/sound_effects/swish.wav");
					soundEffect = false;
				}
			}
			
			// Change the spaceship angle and velocity
			if(load2) {
				
				ang = -1.57;
				velX = -10;
				
				// Keeps the spaceship in y = 75 until arrive in the button
				if(spaceshipPosition) {
				y = 75;
				spaceshipPosition = false;
				}
				
				// Stops the spaceship and enable the buttons
				if(x<=300) {
					x=300;
					load3 = true;
				}
				
			}
			
			
			// Executions
			
			
			tempo.schedule(task1, 3300);
			tempo.schedule(task2, 3000);
			tempo.schedule(task3, 3500);

		
		if(load3) {	
			
		
		// Play button .........................................................................
		
		
		g2.setColor(new Color(0,0,72));
		g2.drawRect(100, 100, 180, 100);
		g2.drawRect(105, 105, 170, 90);
		g2.fillRect(100, 100, 180, 5);
		g2.fillRect(100, 100, 5, 100);
		g2.fillRect(100, 195, 180, 5);
		g2.fillRect(275, 100, 5, 100);
		if(y == 75) {
			g2.setColor(new Color(255,255,255,50));
			g2.fillRect(105,105,170,90);
		}
		g2.setColor(Color.BLACK);
		g2.setFont(Paint.fonte("data/airstrikeacad.ttf", sizePlayButton));
		g2.drawString("Play", xPlay, yPlay);
		
		
		// Maps button .........................................................................
		
		
		g2.setColor(Color.BLUE);
		g2.drawRect(100, 250, 180, 100);
		g2.drawRect(105, 255, 170, 90);
		g2.fillRect(100, 250, 180, 5);
		g2.fillRect(100, 250, 5, 100);
		g2.fillRect(100, 345, 180, 5);
		g2.fillRect(275, 250, 5, 100);
		if(y == 225) {
			g2.setColor(new Color(255,255,255,50));
			g2.fillRect(105,255,170,90);
		}
		g2.setColor(Color.BLACK);
		g2.setFont(Paint.fonte("data/airstrikeacad.ttf", sizeMapsButton));
		g2.drawString("Maps", xMaps, yMaps);

		
		// Quit button .........................................................................
		

		g2.setColor(new Color(1,11,25));
		g2.drawRect(100, 400, 180, 100);
		g2.drawRect(105, 405, 170, 90);
		g2.fillRect(100, 400, 180, 5);
		g2.fillRect(100, 400, 5, 100);
		g2.fillRect(100, 495, 180, 5);
		g2.fillRect(275, 400, 5, 100);
		if(y == 375) {
			g2.setColor(new Color(255,255,255,50));
			g2.fillRect(105,405,170,90);
		}
		g2.setColor(Color.BLACK);
		g2.setFont(Paint.fonte("data/airstrikeacad.ttf", sizeQuitButton));
		g2.drawString("Quit", xQuit, yQuit);
		
		
		}
	
	
	}
	
	
}