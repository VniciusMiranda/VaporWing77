package game_objects;

import java.awt.Graphics;
import java.awt.Rectangle;

import game.ID;

public abstract class GameObject {
	
	
	// VARIABLES *******************************************************************************************************************************************
	
	
	protected float x, y;
	protected float velX, velY;
	protected ID id;
	protected int collisionCount = 0;
	protected int level;
	
	
	// CONSTRUCTOR *******************************************************************************************************************************************
	
	
	public GameObject(float x, float y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	
	// ABSTRACT METHODS *******************************************************************************************************************************************
	
	
	public abstract void update();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();

	
	// GETS *******************************************************************************************************************************************
	
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public ID getId() {
		return id;
	}
		
	public float getVelX() {
		return velX;
	}
	
	public float getVelY() {
		return velY;
	}
	
	public int getCollisionCount() {
		return collisionCount;
	}
	
	
	// SETS *******************************************************************************************************************************************
	
	
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}
	
	public void setCollisionCount(int collisionCount) {
		this.collisionCount = collisionCount;
	}

	
}
