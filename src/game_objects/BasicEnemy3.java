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


public class BasicEnemy3 extends GameObject{
	
	
	// VARIABLES *******************************************************************************************************************************************
	
	
	private Random random = new Random();
	private RenderHandler handler;
	private Animation anime = new Animation(50);
	private Audio audio = new Audio();
	private boolean load, soundEffect = true;;
	private Timer time = new Timer();
	private BufferedImage image = Paint.asteroide3;
	private int intSound;
	private String audioName;
	
	
	// CONSTRUCTOR *******************************************************************************************************************************************
	
	
	public BasicEnemy3(int x, int y, ID id, RenderHandler handler){
		super(x, y, id);
		this.handler = handler;
		velX = -(random.nextInt(4) + 1);
		
		for(int i = 0; i<16; i++) {
			anime.addFrame(Paint.vectorExplosion2[i]);
		}
		
		intSound = random.nextInt(5);
		if(intSound == 1) {
			audioName = "explosion_sound1";
		}else if(intSound == 2) {
			audioName = "explosion_sound2";
		}else if(intSound == 3) {
			audioName = "explosion_sound3";
		}else if(intSound == 4) {
			audioName = "explosion_sound5";
		}else {
			audioName = "explosion_sound4";
		}

	}
	
	
	// UPDATE METHOD *******************************************************************************************************************************************
	// Is the common update function among all the GameObejcts 
	

	@Override
	public void update() {
		
		x += velX;
		
		if(x <= -100) {
			x=random.nextInt(Game.width)+Game.width;
			y=random.nextInt(Game.height - Paint.asteroide3.getHeight());
			velX = -(random.nextInt(3) + 1);
		}		
		
		collision();

	}
	
	
	// COLLISION METHOD *******************************************************************************************************************************************
	// This is the method that kills the object if it has been hit and increases the game score
	
	
	public void collision() {		
		

		if(this.collisionCount >=5) {
	
			image = anime.getFrame();
			anime.update();
			velX = 0;
			
			if(soundEffect) {
			audio.playingOnce("data/sound_effects/" + audioName + ".wav");
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
				Score.score+=50;
				Score.scoreKeep+=50;
				
			}
			
			time.schedule(task,800);

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
		return new Rectangle((int)x,(int)y+2,Paint.asteroide3.getWidth(),Paint.asteroide3.getHeight()-6);
	}

	
}
