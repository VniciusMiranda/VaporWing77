package game_objects;


import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;
import game.Animation;
import game.Audio;
import game.ID;
import game.Paint;
import game.RenderHandler;
import game.Score;


public class Enemy2 extends GameObject {
	
	
	// VARIABLES *******************************************************************************************************************************************
	
	
	private GameObject player;
	private RenderHandler handler;
	private Animation anime = new Animation(70);
	private Audio audio = new Audio();
	private boolean load, soundEffect = true;
	private Timer time = new Timer();
	private BufferedImage image = Paint.enemy2;

	
	// CONSTRUCTOR *******************************************************************************************************************************************
	
	
	public Enemy2(int x, int y, ID id, RenderHandler handler){
		super(x, y, id);
		this.handler = handler;
		
		for(int i=0; i< handler.object.size(); i++) {
			if(handler.object.get(i).getId() == ID.Player) player = handler.object.get(i) ;
		}
		
		for(int i=0; i<12; i++) {
			anime.addFrame(Paint.vectorExplosion3[i]);
		}
		
	}

	
	// UPDATE METHOD *******************************************************************************************************************************************
	// Is the common update function among all the GameObejcts 
	
	
	@Override
	public void update() {
		
		x += velX;
		y += velY;
		
		
		// This make the enemy follows the player .........................................................................
		
		float diffX = (float)x - player.getX()+10;
		float diffY = (float)y - player.getY()+5;
		float distance = (float) Math.sqrt((x-player.getX())*(x-player.getX()) + (y-player.getY())*(y-player.getY()));
		

		velX = ((float) ((-1.0/distance) * diffX)*2);
		velY = ((float) ((-1.0/distance) * diffY)*2);
		
		collision();

	}
	
	
	// COLLISION METHOD *******************************************************************************************************************************************
	// This is the method that kills the object if it has been hit and increases the game score
	
	
	public void collision() {		
		

		if(this.collisionCount >= 15) {
	
			image = anime.getFrame();
			anime.update();
			velY = 0;
			velX = 0;
			
			if(soundEffect) {
			audio.playingOnce("data/sound_effects/bass.wav");
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
				Score.score+=150;
				Score.scoreKeep+=150;
				
			}
			
			time.schedule(task,700);


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
		return new Rectangle((int)x+5,(int)y+5,Paint.enemy2.getWidth()-10,Paint.enemy2.getHeight()-10);
	}


}
