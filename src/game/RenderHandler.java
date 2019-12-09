package game;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import game_objects.GameObject;

public class RenderHandler {
	
	
	// CREATING A LIST VARIABLE OF TYPE GAMEOBJECT *******************************************************************************************************************************************
	
	
	public List<GameObject> object = new ArrayList<GameObject>();
	
	
	// ALL UPDATE METHODS *******************************************************************************************************************************************
	
	
	public void update() {
		for(int i=0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			tempObject.update();
		}
		
	}
    
	
	// ALL RENDER METHODS *******************************************************************************************************************************************
	
	
	public void render(Graphics g) {
		for(int i=0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			tempObject.render(g);
		}
		
	}
	
	
	// TO ADD OBJECTS TO THE LIST *******************************************************************************************************************************************
	
	
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	
	
	// TO REMOVE OBJECTS OF THE LIST *******************************************************************************************************************************************
	
	
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
	

}


