package game;

import java.awt.image.BufferedImage;

public class SpriteSheet {

	
	// VARIABLES *******************************************************************************************************************************************
	
	
	private BufferedImage sheet;
	
	
	// CONSTRUCTOR *******************************************************************************************************************************************
	
	
	public SpriteSheet(BufferedImage sheet) {
		
		this.sheet = sheet;
		
	}
	
	
	// CROP METHOD *******************************************************************************************************************************************
	// This use a BufferedImage method to cut the spritesheets
	
	
	public BufferedImage crop(int x, int y, int width, int height) {
		return sheet.getSubimage(x, y, width, height);
	}
	
	
}
