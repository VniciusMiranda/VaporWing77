package screens;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;
import game.Game;
import game.ID;
import game.Paint;


public class LoadScreen {
	
	
	// VARIABLES *******************************************************************************************************************************************
	
	
	private int red = 255, green = 255, blue = 255;
	private int x = -1000, y = 528, velX = 15;
	private boolean load2, load1, load3;
	private double ang = 1.57;
	private Timer tempo = new Timer();
	private Paint paint = new Paint();
	
	
	// VARIATION METHOD *******************************************************************************************************************************************
	
	
	public int variation(int var, int min, int max) {
		
		if(var >= max) {
			return var = max;
		}else if(var <= min){
			return var = min;
		}else {
			return var;
		}	
		
	}
	
	
	// RENDER METHOD *******************************************************************************************************************************************
	
	
	public void render(Graphics g) {
		
		
		g.setColor(new Color(red,green,blue));
		g.fillRect(0,0,Game.width,Game.height);
			
		
		// TimerTasks  .........................................................................
		
			
		TimerTask task1 = new TimerTask() {
			@Override
			public void run() {
				load1 = true;
			}
		};

		TimerTask task2 = new TimerTask() {
			@Override
			public void run() {		
				load2 = true;
			}
		};
		
		TimerTask task3 = new TimerTask() {
			@Override
			public void run() {		
				load3 = true;
			}
		};
				
		TimerTask task4 = new TimerTask() {

			@Override
			public void run() {
						
				red--;
				green--;
				blue--;
				red = variation(red, 100, 254);
				green = variation(green, 100 ,254);
				blue = variation(blue,100,254);
					
			}
		};
		
		
		// Conditions  .........................................................................
		
		
		if(load1) {
			g.drawImage(Paint.ufg, 100,175, null);
		}

		if(load2) {

			g.setColor(Color.BLACK);
			g.setFont((new Font("Cooper Black", Font.PLAIN, 24)));
			g.drawString("Engenharia de Computação", 834, 235);
			g.drawString("Alunos: ", 834, 300);
			g.drawString("Victor Eduardo Iscava Moreira", 834, 350);
			g.drawString("Victor Leal", 834, 390);
			g.drawString("Vinicius Medeiros", 834, 430);
			g.drawString("Gabriel Lagni", 834, 470);
			
			g.drawImage(paint.rotateImage(Paint.naveGrande, ang), x,y, 150,150,null);
			x+=velX;

		}
		
		
		if(load3) {
			
			ang = -1.57;
			y = 13;
			velX = -20;

		}
		
		
		// TIMERTASKS EXECUTIONS .........................................................................
		
		
		tempo.schedule(task1, 1000);
		tempo.schedule(task2, 2000);
		tempo.schedule(task3, 4500);
		tempo.schedule(task4, 6000);

		
		// Condition to change the screen .........................................................................
		
		
		if(red == 100) {
			Game.gameState = ID.Menu;
		}
		
		
	}

	
}
