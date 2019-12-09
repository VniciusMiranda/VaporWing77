package game_objects;


import java.awt.Graphics;
import java.awt.Rectangle;
import game.Game;
import game.ID;
import game.Paint;
import game.RenderHandler;


public class Weapons extends GameObject{	

	
	// VARIABLES *******************************************************************************************************************************************
	
	
	Paint paint = new Paint();
	private RenderHandler handler;
	private boolean visible;
	private double angulo;
	public static int collisionCount = 0;

	
	// CONSTRUCTOR *******************************************************************************************************************************************
	
	
	public Weapons(float x, float y, ID id, RenderHandler handler) {
		super(x, y, id);
		this.handler = handler;
		visible = true;

		angulo  = Player.angulo;

	}
	
	
	// UPDATE METHOD *******************************************************************************************************************************************
	// Is the common update function among all the GameObejcts 
	

	@Override
	public void update() {
		
		if(visible) {
			
			if(x > Game.width || x < 0 || y < 0 || y > Game.height) {
				visible = false;
			}
			
			if(angulo == Math.PI/4) {
				velY = -10;
				velX = 10;
			}
			else if(angulo == 1.57) {
				velX = 10;
			}
			else if(angulo == 2.35) {
				velY = 10;
				velX = 10;
			}
			else if(angulo == 3.14) {
				velY = 10;
			}
			else if(angulo == 0) {
				velY = -10;
			}
			else if(angulo == -0.785) {
				velY = -10;
				velX = -10;
			}
			else if(angulo == -1.57) {
				velX = -10;
			}
			else if(angulo == -2.35) {
				velY = 10;
				velX = -10;
			}
			
			x += velX;
			y += velY;
			
		} else {
			
			handler.removeObject(this);
			
		}
		
		collision();
		
	}
	
	
	// COLLISION METHOD *******************************************************************************************************************************************
	// This method is about which objects this object can hit
	
	
	public void collision() {		
		
		for(int i=0; i< handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			try {
				if(tempObject.getId() == ID.BasicEnemy1 || tempObject.getId() == ID.BasicEnemy2 || tempObject.getId() == ID.BasicEnemy3 || tempObject.getId() == ID.Enemy2 || tempObject.getId() == ID.Enemy3) {
						if(getBounds().intersects(tempObject.getBounds())) {
							
							tempObject.collisionCount++;
							handler.removeObject(this);
							
							}
					
				}
			}catch(Exception e) {
				e.printStackTrace();
				handler.removeObject(this);
			}
			
			
		}
		
	}
	
	
	// RENDER METHOD *******************************************************************************************************************************************
	// Is the common render function among all the GameObejcts 


	@Override
	public void render(Graphics g) {
		
		g.drawImage(paint.rotateImage(Paint.bullet, angulo), (int)x, (int)y, null);
		
	}
	

	// RECTANGLE METHOD  *******************************************************************************************************************************************
	// Is the common rectangle function among all the GameObejcts 
	// This create a rectangle around the picture that controls the collisions by intersections 
	
	
	@Override
	public Rectangle getBounds() {
		
		return new Rectangle((int)x+7,(int)y+10,Paint.bullet.getWidth()-14,Paint.bullet.getHeight()-20);
		
	}
	

}
