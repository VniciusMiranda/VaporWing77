package game_objects;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;
import game.Animation;
import game.Audio;
import game.Game;
import game.ID;
import game.Paint;
import game.RenderHandler;


public class Player extends GameObject{
	
	
	// VARIABLES *******************************************************************************************************************************************
	
	
	Paint paint = new Paint();
	private RenderHandler handler;
	public static int health=100, red=0, green=255;
	public static double angulo;
	private Animation anime = new Animation(150);
	private Audio audio = new Audio();
	public static boolean end;
	private boolean soundEffect = true, load, image, dead;
	private Timer time = new Timer();
	
	
	// CONSTRUCTOR *******************************************************************************************************************************************
	
	
	public Player(int x, int y, ID id, RenderHandler handler) {
		
		super(x, y, id);
		this.handler = handler;
		
		for(int i = 0; i < 10; i++) {
			
			anime.addFrame(Paint.exp[i]);
			
		}

	
	}	
	
	
	// UPDATE METHOD *******************************************************************************************************************************************
	// Is the common update function among all the GameObejcts 
	
	
	@Override
	public void update() {
		
		x += velX;
		y += velY;
		
		if(x<=0) {
			x=0;
		} else if(x>=Game.width-(Paint.nave.getWidth()+5)) {
			x=Game.width-(Paint.nave.getWidth()+5);
		}
		
		if(y<=0) {
			y=0;
		} else if(y>=Game.height-(Paint.nave.getHeight()+30)) {
			y=Game.height-(Paint.nave.getHeight()+30);
		}
		
		collision();
			
	}
	
	
	// COLLISION METHOD *******************************************************************************************************************************************
	// This method is about which objects can kill the player
	
	
	public void collision() {
		for(int i=0; i< handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if(tempObject.getId() == ID.BasicEnemy1 || tempObject.getId() == ID.BasicEnemy2 || tempObject.getId() == ID.BasicEnemy3 || tempObject.getId() == ID.Enemy2 || tempObject.getId() == ID.Enemy3) {
				
					if(getBounds().intersects(tempObject.getBounds())) {

							if(health<=0) {
								
								health = 0;
								dead = true;
								
							} else {
								
								health--;
								green-=2;
								red+=2.4;
								
							}
					}
			}

		}
		
		
		// Death Animation
		
		
		if(dead) {
			
			image = true;
			anime.update();
			velX = 0;
			velY = 0;
			
			if(soundEffect) {
				audio.playingOnce("data/sound_effects/doubleExplosion1.wav");
				soundEffect = false;
			}
			
			TimerTask task = new TimerTask() {
				@Override
				public void run() {
					load = true;	
				}
			};	
			
			if(load) {
				
				end = true;
				
			}
			
			time.schedule(task,1000);	
			
		}
		
			
	}
	
	
	// RENDER METHOD *******************************************************************************************************************************************
	// Is the common render function among all the GameObejcts 
	
	
	@Override
	public void render(Graphics g) {
		
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(10, 10, 100, 30);
		g.setColor(new Color(red,green,0));
		g.setFont((new Font("Splurge", Font.PLAIN, 28)));
		g.drawString("" + health, 120, 35);
		g.fillRect(10, 10, (int)health, 30);
		g.setColor(Color.YELLOW);
		g.drawRect(10, 10, 100, 30);
		g.drawImage(rotateImage(Paint.nave, angulo), (int)x, (int)y, null);
		if(image) {
			g.drawImage(anime.getFrame(), (int)x, (int)y, null);
		}

	}
	
	
	// ROTATE METHOD  *******************************************************************************************************************************************
	// This method treats a specific BufferedImage using linear transformation to rotate the image
	
	
	public BufferedImage rotateImage(BufferedImage rotateImage, double angle) {
	    AffineTransform tx = new AffineTransform();
	    tx.rotate(angle, (rotateImage.getWidth() / 2.0), (rotateImage.getHeight() / 2.0));

	    // Rotaciona as coordenadas dos cantos da imagem
	    Point2D[] aCorners = new Point2D[4];
	    aCorners[0] = tx.transform(new Point2D.Double(0.0, 0.0), null);
	    aCorners[1] = tx.transform(new Point2D.Double(rotateImage.getWidth(), 0.0), null);
	    aCorners[2] = tx.transform(new Point2D.Double(0.0, rotateImage.getHeight()), null);
	    aCorners[3] = tx.transform(new Point2D.Double(rotateImage.getWidth(), rotateImage.getHeight()), null);

	    // Obtém o valor de translação para cada eixo com um canto "escondido"
	    double dTransX = 0;
	    double dTransY = 0;

	    	if(angulo == 3.14 || angulo == 1.57 || angulo == -1.57 || angulo == 0 ) {
	    
		        if(aCorners[0].getX() < 0 && aCorners[0].getX() < dTransX)
		            dTransX = aCorners[0].getX();
		        if(aCorners[0].getY() < 0 && aCorners[0].getY() < dTransY)
		            dTransY = aCorners[0].getY();
		        
		        if(aCorners[1].getX() < 0 && aCorners[1].getX() < dTransX)
		            dTransX = aCorners[1].getX();
		        if(aCorners[1].getY() < 0 && aCorners[1].getY() < dTransY)
		            dTransY = aCorners[1].getY();
		        
		        if(aCorners[2].getX() < 0 && aCorners[2].getX() < dTransX)
		            dTransX = aCorners[2].getX();
		        if(aCorners[2].getY() < 0 && aCorners[2].getY() < dTransY)
		            dTransY = aCorners[2].getY();
		        
		        if(aCorners[3].getX() < 0 && aCorners[3].getX() < dTransX)
		            dTransX = aCorners[3].getX();
		        if(aCorners[3].getY() < 0 && aCorners[3].getY() < dTransY)
		            dTransY = aCorners[3].getY();
		}
	    	else if(angulo == 0.7853981633974483) {
	    		
		        if(aCorners[0].getX() < 0 && aCorners[0].getX() < dTransX)
		            dTransX = aCorners[0].getX()+3;
		        if(aCorners[0].getY() < 0 && aCorners[0].getY() < dTransY)
		            dTransY = aCorners[0].getY()+13;
		        
		        if(aCorners[1].getX() < 0 && aCorners[1].getX() < dTransX)
		            dTransX = aCorners[1].getX()+3;
		        if(aCorners[1].getY() < 0 && aCorners[1].getY() < dTransY)
		            dTransY = aCorners[1].getY()+13;
		        
		        if(aCorners[2].getX() < 0 && aCorners[2].getX() < dTransX)
		            dTransX = aCorners[2].getX()+3;
		        if(aCorners[2].getY() < 0 && aCorners[2].getY() < dTransY)
		            dTransY = aCorners[2].getY()+13;
		        
		        if(aCorners[3].getX() < 0 && aCorners[3].getX() < dTransX)
		            dTransX = aCorners[3].getX()+3;
		        if(aCorners[3].getY() < 0 && aCorners[3].getY() < dTransY)
		            dTransY = aCorners[3].getY()+13;
	    		
	    	}
	    	
	    	else if(angulo == -2.35) {
	    		
		        if(aCorners[0].getX() < 0 && aCorners[0].getX() < dTransX)
		            dTransX = aCorners[0].getX()+13;
		        if(aCorners[0].getY() < 0 && aCorners[0].getY() < dTransY)
		            dTransY = aCorners[0].getY()+3;
		        
		        if(aCorners[1].getX() < 0 && aCorners[1].getX() < dTransX)
		            dTransX = aCorners[1].getX()+13;
		        if(aCorners[1].getY() < 0 && aCorners[1].getY() < dTransY)
		            dTransY = aCorners[1].getY()+3;
		        
		        if(aCorners[2].getX() < 0 && aCorners[2].getX() < dTransX)
		            dTransX = aCorners[2].getX()+13;
		        if(aCorners[2].getY() < 0 && aCorners[2].getY() < dTransY)
		            dTransY = aCorners[2].getY()+3;
		        
		        if(aCorners[3].getX() < 0 && aCorners[3].getX() < dTransX)
		            dTransX = aCorners[3].getX()+13;
		        if(aCorners[3].getY() < 0 && aCorners[3].getY() < dTransY)
		            dTransY = aCorners[3].getY()+3;
	    		
	    	}
	    	
	    	else if(angulo == 2.35) {
	    		
		        if(aCorners[0].getX() < 0 && aCorners[0].getX() < dTransX)
		            dTransX = aCorners[0].getX()+3;
		        if(aCorners[0].getY() < 0 && aCorners[0].getY() < dTransY)
		            dTransY = aCorners[0].getY()+3;
		        
		        if(aCorners[1].getX() < 0 && aCorners[1].getX() < dTransX)
		            dTransX = aCorners[1].getX()+3;
		        if(aCorners[1].getY() < 0 && aCorners[1].getY() < dTransY)
		            dTransY = aCorners[1].getY()+3;
		        
		        if(aCorners[2].getX() < 0 && aCorners[2].getX() < dTransX)
		            dTransX = aCorners[2].getX()+3;
		        if(aCorners[2].getY() < 0 && aCorners[2].getY() < dTransY)
		            dTransY = aCorners[2].getY()+3;
		        
		        if(aCorners[3].getX() < 0 && aCorners[3].getX() < dTransX)
		            dTransX = aCorners[3].getX()+3;
		        if(aCorners[3].getY() < 0 && aCorners[3].getY() < dTransY)
		            dTransY = aCorners[3].getY()+3;
	    		
	    	}
	    	
	    	
	    	else if(angulo == -0.785) {
	    		
		        if(aCorners[0].getX() < 0 && aCorners[0].getX() < dTransX)
		            dTransX = aCorners[0].getX()+13;
		        if(aCorners[0].getY() < 0 && aCorners[0].getY() < dTransY)
		            dTransY = aCorners[0].getY()+13;
		        
		        if(aCorners[1].getX() < 0 && aCorners[1].getX() < dTransX)
		            dTransX = aCorners[1].getX()+13;
		        if(aCorners[1].getY() < 0 && aCorners[1].getY() < dTransY)
		            dTransY = aCorners[1].getY()+13;
		        
		        if(aCorners[2].getX() < 0 && aCorners[2].getX() < dTransX)
		            dTransX = aCorners[2].getX()+13;
		        if(aCorners[2].getY() < 0 && aCorners[2].getY() < dTransY)
		            dTransY = aCorners[2].getY()+13;
		        
		        if(aCorners[3].getX() < 0 && aCorners[3].getX() < dTransX)
		            dTransX = aCorners[3].getX()+13;
		        if(aCorners[3].getY() < 0 && aCorners[3].getY() < dTransY)
		            dTransY = aCorners[3].getY()+13;
	    		
	    	}

	    // Aplica a translação para evitar cortes na imagem
	    AffineTransform translationTransform = new AffineTransform();
	    translationTransform.translate(-dTransX, -dTransY);
	    tx.preConcatenate(translationTransform);

	    return new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR).filter(rotateImage, null);
	}

	
	// RECTANGLE METHOD  *******************************************************************************************************************************************
	// Is the common rectangle function among all the GameObejcts 
	// This create a rectangle around the picture that controls the collisions by intersections 
	
	
	@Override
	public Rectangle getBounds() {
		
		return new Rectangle((int)x,(int)y,Paint.nave.getWidth(),Paint.nave.getHeight());
		
	}
	

}
