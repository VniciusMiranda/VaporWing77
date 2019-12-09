package game_objects;


import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import game.Animation;
import game.Audio;
import game.Game;
import game.ID;
import game.Paint;
import game.RenderHandler;
import game.Score;


public class BasicEnemy1 extends GameObject{
	
	
	// VARIABLES *******************************************************************************************************************************************
	
	
	private Random random = new Random();
	private RenderHandler handler;
	private Animation anime = new Animation(10);
	private Audio audio = new Audio();
	private boolean load, soundEffect = true;;
	private Timer time = new Timer();
	private BufferedImage image = Paint.asteroide;
	
	
	// CONSTRUCTOR *******************************************************************************************************************************************
	
	
	public BasicEnemy1(int x, int y, ID id, RenderHandler handler){
		super(x, y, id);
		this.handler = handler;
		velX = random.nextInt(3) + 1;

		for(int i = 0; i<56; i++) {
			anime.addFrame(Paint.vectorExplosion1[i]);
		}
			
	}
	
	
	// UPDATE METHOD *******************************************************************************************************************************************
	// Is the common update function among all the GameObejcts 
	

	@Override
	public void update() {
		
		x += velX;
		
		if(x >= (Game.width+100)) {
			x=random.nextInt(Game.width)-Game.width;
			y=random.nextInt(Game.height - Paint.asteroide.getHeight());
			
		}		
		
		collision();

	}
	
	
	// COLLISION METHOD *******************************************************************************************************************************************
	// This is the method that kills the object if it has been hit and increases the game score
	
	
	public void collision() {		
		

		if(this.collisionCount >= 3) {
			
			image = anime.getFrame();
			anime.update();
			velX = 0;
			
			if(soundEffect) {
			audio.playingOnce("data/sound_effects/doubleExplosion2.wav");
			soundEffect = false;
			}
			
			TimerTask task = new TimerTask() {
				@Override
				public void run() {
					load = true;	
				}
			};	
			
			if(load) {
				
				handler.removeObject(this);		
				Score.score+=30;
				Score.scoreKeep+=30;
				
			}
			
			time.schedule(task,600);

		}	
		
		
	}
	
	
	// RENDER METHOD *******************************************************************************************************************************************
	// Is the common render function among all the GameObejcts 

	
	@Override
	public void render(Graphics g) {

		g.drawImage(image, (int)x,(int)y, null);

		
	}

	
	// RECTANGLE METHOD  *******************************************************************************************************************************************
	// Is the common rectangle function among all the GameObejcts 
	// This create a rectangle around the picture that controls the collisions by intersections 
	
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y+1,Paint.asteroide.getWidth(),Paint.asteroide.getHeight()-4);
	}

	
}
