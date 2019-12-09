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


public class Maps extends MouseAdapter{
	
	
	// VARIABLES *******************************************************************************************************************************************
	
	
	private boolean backButton, rightButton, leftButton, okButton;
	private int fase = 1, currentMap = 1;
	private int size1 = 50, size2 = 50, y1 = 620, y2 = 620, x2 = 542;
	private int sizeOkButton = 40, aOk = 603, bOk = 656;
	private int sizeBackButton = 40, aBack = 17, bBack = 666;	
	private Audio audio = new Audio();
	
	
	// MOUSE PRESSED METHOD *******************************************************************************************************************************************
	
	
	public void mousePressed(MouseEvent e) {
		
		
		// This variables return a integer value of mouse position 
		
		
		int mx = e.getX();
		int my = e.getY();
		
		
		// This condition prevents the buttons to be accessed in other screens
		
		
		if(Game.gameState == ID.Maps) {
			
			
			// OK button .........................................................................
			
			
			if(mouseOver(mx, my, 602, 620, 70, 50)) {
				
				sizeOkButton = 30;
				aOk = 611;
				bOk = 653;	
				okButton = true;
				
			}
			
			
			// Back button .........................................................................
			
			
			if(mouseOver(mx, my, 10, 630, 130, 50)) {
				
				sizeBackButton = 30;
				aBack = 31;
				bBack = 663;
				backButton = true;
				
			}	
			
			
			// Right button .........................................................................
			
			
			if(mouseOver(mx, my, 682, 620, 50, 50)) {
					
				size1 = 40;
				y1=625;
				rightButton = true;
				
			}
			
			
			// Left button .........................................................................
			
			
			if(mouseOver(mx, my, 542, 620, 50, 50)) {
				
				size2 = 40;
				y2=625;
				x2 = 552;
				leftButton = true;
				
			}
			
			
		}
	}
	

	// MOUSE REALEASED METHOD *******************************************************************************************************************************************
	
	
	public void mouseReleased(MouseEvent e) {
		

		// This variables return a integer value of mouse position 
		
		
		int mx = e.getX();
		int my = e.getY();
		
		
		// This condition prevents the buttons to be accessed in other screens
		
		
		if(Game.gameState == ID.Maps) {
			
			
			// OK button .........................................................................
			
			
			if(mouseOver(mx, my, 602, 620, 70, 50)) {
				
				if(okButton) {
				
					if(fase == 1){
						Paint.background = Paint.background1;
					}else if(fase == 2){
						Paint.background = Paint.background2;
					}else if(fase == 3){
						Paint.background = Paint.background3;
					}
					audio.playingOnce("data/sound_effects/button.wav");
					currentMap = fase;
					
				}
				
			}
			
			
			// Back button .........................................................................
			
			
			if(mouseOver(mx, my, 10, 630, 130, 50)) {
				
				if(backButton) {
				
					Game.gameState = ID.Menu;
					audio.playingOnce("data/sound_effects/button1.wav");
				
				}
				
			}	
			
			
			// Right button .........................................................................
			
			
			if(mouseOver(mx, my, 682, 620, 50, 50)) {
				
				if(rightButton) {
				
					fase++;
					if(fase>3) {
						fase=1;
					}
					audio.playingOnce("data/sound_effects/button2.wav");
				
				}
				
			}
			
			
			// Left button .........................................................................
			
			
			if(mouseOver(mx, my, 542, 620, 50, 50)) {
				
				if(leftButton) {
				
					fase--;
					if(fase<1) {
						fase=3;
					}
					audio.playingOnce("data/sound_effects/button2.wav");
				
				}
				
			}
			
			
			// Buttons
			
			
			okButton = false;
			backButton = false;
			rightButton = false;
			leftButton = false;
			
			
			// Arrows buttons
			
			
			size1 = 50;
			y1=620;
			size2 = 50;
			y2=620;
			x2 = 542;
		
			
			//	OK Button
			
			
			sizeOkButton = 40;
			aOk = 603;
			bOk = 656;		
			
			
			//	Back Button
			
			
			sizeBackButton = 40;
			aBack = 17;
			bBack = 666;		
			
			
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
		
		
		Graphics2D g2 = (Graphics2D) g.create();
		
		
		// Background .........................................................................
		
		
		g2.drawImage(Paint.menuBackground2, 0, 0, Game.width, Game.height, null);

		
		// Rectangles .........................................................................
		
		// Back Button
		g2.setColor( new Color(1,11,50) );
		g2.drawRect(10, 630, 130, 50);
		g2.drawRect(5, 625, 140, 60);
		g2.fillRect(5, 625, 140, 5);
		g2.fillRect(5, 625, 5, 60);
		g2.fillRect(5, 680, 140, 5);
		g2.fillRect(140, 625, 5, 60);
		
		// OK Button
		g2.setColor(Color.BLUE);
		g2.drawRect(597, 615, 80, 60);
		g2.drawRect(602, 620, 70, 50);
		g2.fillRect(597, 615, 80, 5);
		g2.fillRect(597, 670, 80, 5);
		g2.fillRect(597, 615, 5, 60);
		g2.fillRect(672, 615, 5, 60);
		
		// Frame
		g2.setColor(new Color(86,0,0));
		g2.drawRect(142, 95, 990, 510);
		g2.drawRect(147,100,980,500);
		g2.fillRect(142, 95, 990, 5);
		g2.fillRect(142, 95, 5, 510);
		g2.fillRect(142, 600, 990, 5);
		g2.fillRect(1127, 95, 5, 510);
		
		// Arrow Buttons
		g2.drawImage(Paint.right, 682, y1, size1, size1, null);
		g2.drawImage(Paint.left, x2, y2, size2, size2, null);

		
		// Strings .........................................................................
		
		
		g2.setColor(new Color(86,0,0));
		g2.setFont(Paint.fonte("data/airstrike.ttf", sizeOkButton));
		g2.drawString("OK", aOk, bOk);
		g2.setFont(Paint.fonte("data/airstrike.ttf", sizeBackButton));
		g2.drawString("Back", aBack, bBack);
		
		
		// Maps .........................................................................
		
		
		g2.setFont(Paint.fonte("data/airstrike.ttf",100));
		if(fase==1) {
			g2.drawImage(Paint.background1, 148, 101, 979, 499, null);
			g2.drawString("1", 608, 80);
		} else if(fase==2) {
			g2.drawImage(Paint.background2, 148, 101, 979, 499, null);
			g2.drawString("2", 595, 80);
		}else if(fase==3) {
			g2.drawImage(Paint.background3, 148, 101, 979, 499, null);
			g2.drawString("3", 598, 80);
		}
		
		
		// Current Map .........................................................................
		
		
		g2.setFont(Paint.fonte("data/airstrike.ttf", 40));
		g2.drawString("Current Map: " + currentMap, 761, 656);
		g2.setColor(new Color(255,80,0));
		g2.fillRect(750, 615, 360, 5);
		g2.fillRect(750, 670, 360, 5);

		
	}
	
	
}
