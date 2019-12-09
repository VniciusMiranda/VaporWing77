package game;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class Audio {
	
	
	// VARIABLES *******************************************************************************************************************************************
	

	private Timer tempo = new Timer();

	
	// LOADING AND PLAYING METHOD *******************************************************************************************************************************************
	
	
	private void play(String path){  
		  
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Erro ao executar SOM!");
            ex.printStackTrace();
        }
		 
	}
	
	
	// PLAYING ONCE METHOD *******************************************************************************************************************************************
	
	
	public void playingOnce(String path) {
		
		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				play(path);		
			}
		};
		
		tempo.schedule(task, 0);
		
	}
	
	
	// PLAYING FOREVER METHOD *******************************************************************************************************************************************
	
	
	public void playingForever(String path, int audioTimeMillis) {
		
		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				play(path);		
			}
		};
		
		tempo.schedule(task, 0, audioTimeMillis);
		
	}
	
	
	// PLAYING AFTER SOME TIME AND FOREVER METHOD *******************************************************************************************************************************************
	
	
	public void playingForeverAfterSomeTime(String path, int audioTimeMillis, int delayTimeMillis) {
		
		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				play(path);		
			}
		};
		
		tempo.schedule(task, delayTimeMillis, audioTimeMillis);
		
	}
	
	
	// PLAYING ONCE BUT AFTER SOME TIME METHOD *******************************************************************************************************************************************
	
	
	public void playingOnceAfterSomeTime(String path, int delayTimeMillis) {
		
		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				play(path);		
			}
		};
		
		tempo.schedule(task, delayTimeMillis);
		
	}
	

}
