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


public class BasicEnemy2 extends GameObject {
	
	
	// VARIABLES *******************************************************************************************************************************************


	private Random random = new Random();
	private RenderHandler handler;
	private Animation anime = new Animation(50);
	private Audio audio = new Audio();
	private boolean load, soundEffect = true;;
	private Timer time = new Timer();
	private BufferedImage image = Paint.asteroide2;
	private int intSound;
	private String audioName;

	
	// CONSTRUCTOR *******************************************************************************************************************************************
	
	
	public BasicEnemy2(int x, int y, ID id, RenderHandler handler){
		super(x, y, id);
		this.handler = handler;
		velY = random.nextInt(4) + 1;
		
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
		
		y += velY;
		
		if(y >= Game.height) {
			y=random.nextInt(Game.height)-(Game.height+200);
			x = random.nextInt(Game.width - (Paint.asteroide2.getWidth())/2);
			velY = random.nextInt(4) + 1;
		}		
		
		collision();

	}
	
	
	// COLLISION METHOD *******************************************************************************************************************************************
	// This is the method that kills the object if it has been hit and increases the game score
	
	
	public void collision() {		
		

		if(this.collisionCount >= 4) {
	
			image = anime.getFrame();
			anime.update();
			velY = 0;
			
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
				Score.score+=40;
				Score.scoreKeep+=40;
				
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
		return new Rectangle((int)x+10,(int)y,Paint.asteroide2.getWidth()-20,Paint.asteroide2.getHeight());
	}

	
}
