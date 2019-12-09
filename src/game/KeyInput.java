package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import game_objects.GameObject;


public class KeyInput extends KeyAdapter {
	
	
	// VARIABLES *******************************************************************************************************************************************
	
	
	private RenderHandler handler;
	private boolean[] key = new boolean[4];
	
	
	// CONSTRUCTOR *******************************************************************************************************************************************
	
	
	public KeyInput(RenderHandler handler) {
		this.handler = handler;
		
		key[0] = false;
		key[1] = false;
		key[2] = false;
		key[3] = false;
		
	}
	
	
	// KEY_PRESSED METHOD *******************************************************************************************************************************************
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		for(int i=0; i< handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
		
		
			if(tempObject.getId() == ID.Player) {

				if(e.getKeyCode() == KeyEvent.VK_W) { tempObject.setVelY(-5); key[0] = true;} 
				if(e.getKeyCode() == KeyEvent.VK_S) { tempObject.setVelY(5); key[1] = true; }
				if(e.getKeyCode() == KeyEvent.VK_A) { tempObject.setVelX(-5); key[2] = true;} 
				if(e.getKeyCode() == KeyEvent.VK_D) { tempObject.setVelX(5); key[3] = true; }
				
			}

		}
		
	}
	
	
	// KEY_RELEASED METHOD *******************************************************************************************************************************************
	

	@Override
	public void keyReleased(KeyEvent e) {
		
		for(int i=0; i< handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
		
		
			if(tempObject.getId() == ID.Player) {
				if(e.getKeyCode() == KeyEvent.VK_W) key[0] = false; 
				if(e.getKeyCode() == KeyEvent.VK_S) key[1] = false; 
				if(e.getKeyCode() == KeyEvent.VK_A) key[2] = false;
				if(e.getKeyCode() == KeyEvent.VK_D) key[3] = false;
				
				if(!key[0] && !key[1]) tempObject.setVelY(0);
				if(!key[2] && !key[3]) tempObject.setVelX(0);
				
			}
			
		}
		
	}
	
	
}
