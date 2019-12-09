package screens;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import game.Audio;
import game.Game;
import game.ID;
import game.Paint;
import game.RenderHandler;
import game.Score;
import game_objects.Player;


public class TryAgain extends MouseAdapter{

	
	// VARIABLES *******************************************************************************************************************************************
	
	
	private RenderHandler handler;
	private boolean menuButton, playButton;
	private Audio audio = new Audio();
	private int sizePlayButton = 40, xPlay = 960, yPlay= 611;
	private int sizeMenuButton = 40, xMenu = 81, yMenu = 636;
	
	
	// CONSTRUCTOR *******************************************************************************************************************************************
	
	
	public TryAgain(RenderHandler handler) {
		
		this.handler = handler;
		
	}
	
	
	// MOUSE PRESSED METHOD *******************************************************************************************************************************************
	
	
	public void mousePressed(MouseEvent e) {
		
		
		// This variables return a integer value of mouse position 
		
		
		int mx = e.getX();
		int my = e.getY();
		
		
		// This condition prevents the buttons to be accessed in others screens
		
		
		if(Game.gameState == ID.TryAgain) {
			
			
			// Play button .........................................................................
			
			
			if(mouseOver(mx, my, 933, 550, 300, 100)) {
				
				playButton = true;
				sizePlayButton = 35; 
				xPlay = 976; 
				yPlay= 610;
				
			}
			
			
			// Menu button .........................................................................
			
			
			if(mouseOver(mx, my, 40, 600, 200, 50)) {
				
				menuButton = true;
				sizeMenuButton = 30;
				xMenu = 96;
				yMenu = 634;
				
			}
			
			
		}
	}
	
	
	// MOUSE REALEASED METHOD *******************************************************************************************************************************************
	
	
	public void mouseReleased(MouseEvent e) {
			

		// This variables return a integer value of mouse position 
			
			
		int mx = e.getX();
		int my = e.getY();
			
			
		// This condition prevents the buttons to be accessed in other screens
			
			
		if(Game.gameState == ID.TryAgain) {
				
				
			// Play button .........................................................................
				
				
			if(mouseOver(mx, my, 933, 550, 300, 100)) {
					
				if(playButton) {
						
					Score.score = 0;
						
					handler.addObject(new Player(380, 220, ID.Player, handler));
						
					audio.playingOnce("data/sound_effects/fire_sound.wav");
						
					Game.gameState = ID.Game;
	
				}
					
			}
				
				
			// Menu button .........................................................................
				
				
			if(mouseOver(mx, my, 40, 600, 200, 50)) {
					
				if(menuButton) {
					
					Game.gameState = ID.Menu;
					audio.playingOnce("data/sound_effects/button1.wav");
					
				}
					
			}	
				
				
			// Buttons
				
				
			menuButton = false;
			playButton = false;

				
			//	Menu Button
				
				
			sizeMenuButton = 40;
			xMenu = 81;
			yMenu = 636;
				
				
			//	Play Button
				
				
			sizePlayButton = 40; 
			xPlay = 960; 
			yPlay= 611;
				
				
		}
		

	}
		
		
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
		g2.drawImage(Paint.tryAgainBackground, 0, 0, Game.width, Game.height,  null);
		g2.setColor(new Color(86,0,0));
		g2.setFont(Paint.fonte("data/airstrike.ttf", 150));
		g2.drawString("GAME OVER", 177, 150);
		
		
		// Score .........................................................................
		
		
		g2.setColor(Color.RED);
		g2.setFont(Paint.fonte("data/airstrike.ttf",80));
		g2.drawString("Score: " + Score.score, 383, 380);
				
		
		// HighScore .........................................................................
		
		
		g2.setColor(new Color(86,0,0));
		g2.setFont(Paint.fonte("data/airstrike.ttf",40));
		g2.drawString("HighScore: " + Score.highscore, 457, 500);
				
		
		// Play button .........................................................................
		
		
		g2.setColor( Color.ORANGE );
		g2.drawRect(933, 550, 300, 100);
		g2.drawRect(928, 545, 310, 110);
		g2.fillRect(928, 545, 310, 5);
		g2.fillRect(928, 650, 310, 5);
		g2.fillRect(928, 545, 5, 110);
		g2.fillRect(1233, 545, 5, 110);
		g2.drawRect(923, 540, 320, 120);
		g2.drawRect(938, 555, 290, 90);
		g2.setColor(Color.MAGENTA);
		g2.setFont(Paint.fonte("data/airstrike.ttf",sizePlayButton));
		g2.drawString("Play Again", xPlay, yPlay);

		
		// Menu button .........................................................................
		

		g2.setColor( new Color(0,0,255) );
		g2.drawRect(40, 600, 200, 50);
		g2.drawRect(35, 595, 210, 60);
		g2.fillRect(35, 595, 210, 5);
		g2.fillRect(35, 595, 5, 60);
		g2.fillRect(240, 595, 5, 60);
		g2.fillRect(35, 650, 210, 5);
		g2.setColor(new Color(86,0,0));
		g2.setFont(Paint.fonte("data/airstrike.ttf", sizeMenuButton));
		g2.drawString("Menu", xMenu, yMenu);
		
		
	}

	
}
