package game;

import java.util.List;
import java.util.ArrayList;
import java.awt.image.BufferedImage;

public class Animation {
	
	
	// VARIABLES *******************************************************************************************************************************************
	
	
	private List<BufferedImage> frames;
	private int frameIndex = 0;
	private int deltaTime;
	private long previousTime;
	
	
	// CONSTRUCTOR *******************************************************************************************************************************************

	
	public Animation(int deltaTime) {
	
		this.deltaTime = deltaTime;
		
		frames = new ArrayList<BufferedImage>();
		
	}
	
	
	// UPDATE METHOD *******************************************************************************************************************************************
	// This method will update the images of the list as a loop
	
	public void updateLoop() {
		
		if(System.currentTimeMillis() - previousTime > deltaTime) {

			frameIndex++;
			if(frameIndex >= frames.size()) {
				frameIndex = 0;
			}
			
			previousTime = System.currentTimeMillis();
		}
	}
	
	
	// UPDATE METHOD *******************************************************************************************************************************************
	// This method will update the images of the list once
	
	public void update() {
		
		if(System.currentTimeMillis() - previousTime > deltaTime) {

			
			if(frameIndex < frames.size()-1) {
				frameIndex++;
			}
			
			previousTime = System.currentTimeMillis();
		}
	}
	
	
	// ADD FRAMES METHOD *******************************************************************************************************************************************
	//This method appends a new BufferedImage to the BufferedImage list
	
	public void addFrame(BufferedImage frame) {
		frames.add(frame);
	}
	
	
	// GET FRAMES METHOD *******************************************************************************************************************************************
	//Returns the animation 
	
	public BufferedImage getFrame(){
		
		if(frames.size()>0) {
			return frames.get(frameIndex);
		}
		return null;
	}
	
}
