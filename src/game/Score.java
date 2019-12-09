package game;


import java.util.Random;
import game_objects.BasicEnemy1;
import game_objects.BasicEnemy2;
import game_objects.BasicEnemy3;
import game_objects.Enemy2;
import game_objects.Enemy3;
import game_objects.Player;


public class Score {
	

	// VARIABLES *******************************************************************************************************************************************
	
	
	public static int score = 0, level = 0, highscore = 0, scoreKeep = 0;
	private int oldScore = 0;
	private double scoreFactor = 0;
	private int a,b,c,d,e;
	private RenderHandler handler;
	private Random random = new Random();
	
	
	// CONSTRUCTOR *******************************************************************************************************************************************
	// This is very important for this class, because the RenderHandler variable must be the same in all the code
	
	
	public Score(RenderHandler handler) {
		this.handler = handler;
	}
	
	
	// SPAWNER_BASED_ON_SCORE METHOD *******************************************************************************************************************************************
	
	
	public void updateTotalScore() {
		
		
		// Condition to spawn enemies based on the current score .........................................................................
		// Every time this condition is true the scoreKeep variable(variable that keep the condition true) is 0 again, the level ups and the enemies spawn in the game running the specific level loop once.
		// This prevents the game from spawning objects forever
		
		
		if(scoreKeep>=scoreFactor) {	
			
			level += 1;
			
			scoreKeep = 0;
			
				
				// Level 1 .........................................................................
				// scoreFactor: 450 | To the current level: 30 enemies | To the next level: 15 enemies
			
				if(level == 1) {
				
				scoreFactor+=450;
				
					for(int i=0; i<30; i++) {
						
						handler.addObject(new BasicEnemy1(random.nextInt(Game.width)-Game.width, random.nextInt(Game.height - Paint.asteroide.getHeight()), ID.BasicEnemy1, handler));

					}	
	
				}	
				
					
				// Level 2 .........................................................................
				// scoreFactor: 750 | To the current level: 55 enemies | To the next level: 30 enemies
					
				else if(level == 2) {
					
					scoreFactor+=300;
					
					for(int i=0; i<40; i++) {
						
						handler.addObject(new BasicEnemy1(random.nextInt(Game.width)-Game.width, random.nextInt(Game.height - Paint.asteroide.getHeight()), ID.BasicEnemy1, handler));
						
					}
					
				}
				
				
				// Level 3 .........................................................................
				// scoreFactor: 900 | To the current level: 70 enemies | To the next level: 40 enemies
				 
				else if(level == 3) {
					
					scoreFactor+=150;
					
					for(int i=0; i<40; i++) {
						
						handler.addObject(new BasicEnemy1(random.nextInt(Game.width)-Game.width, random.nextInt(Game.height - Paint.asteroide.getHeight()), ID.BasicEnemy1, handler));
					
					}
							
				}
				
				
				// Level 4 .........................................................................
				// scoreFactor: 1000 | To the current level: about 80 enemies | To the next level: about 50 enemies
				
				else if(level == 4) {
					
					scoreFactor+=100;
					
					for(int i=0; i<20; i++) {
						
						handler.addObject(new BasicEnemy1(random.nextInt(Game.width)-Game.width, random.nextInt(Game.height - Paint.asteroide.getHeight()), ID.BasicEnemy1, handler));
					
					}
					
					for(int i=0; i<20; i++) {
						
						handler.addObject(new BasicEnemy2(random.nextInt(Game.width - (Paint.asteroide2.getWidth())/2), random.nextInt(Game.height)-(Game.height+200), ID.BasicEnemy2, handler));
						
					}
					
				}
				
				
				// Level 5 .........................................................................
				// scoreFactor: 1400 | To the current level: about 85 enemies | To the next level: about 50 enemies
				
				else if(level == 5) {
					
					scoreFactor+=400;
					
					for(int i=0; i<20; i++) {
						
						handler.addObject(new BasicEnemy1(random.nextInt(Game.width)-Game.width, random.nextInt(Game.height - Paint.asteroide.getHeight()), ID.BasicEnemy1, handler));
					
					}
					
					for(int i=0; i<15; i++) {
						
						handler.addObject(new BasicEnemy3(random.nextInt(Game.width)+Game.width, random.nextInt(Game.height - Paint.asteroide3.getHeight()), ID.BasicEnemy3, handler));
						
					}
					
				}
				
				
				// Level 6 .........................................................................
				// scoreFactor: 1750 | To the current level: about 81 enemies | To the next level: about 41 enemies
				
				else if(level == 6) {
					
					scoreFactor+=350;
					
					for(int i=0; i<15; i++) {
						
						handler.addObject(new BasicEnemy3(random.nextInt(Game.width)+Game.width, random.nextInt(Game.height - Paint.asteroide3.getHeight()), ID.BasicEnemy3, handler));
					
					}
					
					for(int i=0; i<15; i++) {
						
						handler.addObject(new BasicEnemy2(random.nextInt(Game.width - (Paint.asteroide2.getWidth())/2), random.nextInt(Game.height)-(Game.height+200), ID.BasicEnemy2, handler));
						
					}
					
					handler.addObject(new Enemy2(random.nextInt(Game.width), random.nextInt(Game.height)-Game.height, ID.Enemy2, handler));
					
				}
				
				
				// Level 7 .........................................................................
				// scoreFactor: 2250 | To the current level: about 88 enemies | To the next level: about 48 enemies
				
				else if(level == 7) {
					
					scoreFactor+=500;
					
					for(int i=0; i<15; i++) {
						
						handler.addObject(new BasicEnemy3(random.nextInt(Game.width)+Game.width, random.nextInt(Game.height - Paint.asteroide3.getHeight()), ID.BasicEnemy3, handler));
					
					}
					
					for(int i=0; i<15; i++) {
						
						handler.addObject(new BasicEnemy1(random.nextInt(Game.width)-Game.width, random.nextInt(Game.height - Paint.asteroide.getHeight()), ID.BasicEnemy1, handler));
						
					}
					
					for(int i=0; i<15; i++) {
						
						handler.addObject(new BasicEnemy2(random.nextInt(Game.width - (Paint.asteroide2.getWidth())/2), random.nextInt(Game.height)-(Game.height+200), ID.BasicEnemy2, handler));
						
					}
					
					handler.addObject(new Enemy2(random.nextInt(Game.width), random.nextInt(Game.height)-Game.height, ID.Enemy2, handler));
					handler.addObject(new Enemy2(random.nextInt(Game.width), random.nextInt(Game.height)-Game.height, ID.Enemy2, handler));
					
				}
				
				
				// Level 8 .........................................................................
				// scoreFactor: 2600 | To current level: about 83 enemies | To the next level: about 43 enemies
				
				else if(level == 8) {
					
					scoreFactor+=350;
			
					for(int i=0; i<20; i++) {
						
						handler.addObject(new BasicEnemy3(random.nextInt(Game.width)+Game.width, random.nextInt(Game.height - Paint.asteroide3.getHeight()), ID.BasicEnemy3, handler));
					
					}
					
					for(int i=0; i<10; i++) {
						
						handler.addObject(new BasicEnemy2(random.nextInt(Game.width - (Paint.asteroide2.getWidth())/2), random.nextInt(Game.height)-(Game.height+200), ID.BasicEnemy2, handler));
						
					}
					
					handler.addObject(new Enemy2(random.nextInt(Game.width), random.nextInt(Game.height)-Game.height, ID.Enemy2, handler));
					handler.addObject(new Enemy2(random.nextInt(Game.width), random.nextInt(Game.height)-Game.height, ID.Enemy2, handler));
					handler.addObject(new Enemy2(random.nextInt(Game.width), random.nextInt(Game.height)-Game.height, ID.Enemy2, handler));
					handler.addObject(new Enemy3(random.nextInt(Game.width)+Game.width, random.nextInt(Game.height - Paint.asteroide4.getHeight()), ID.Enemy3, handler));
					handler.addObject(new Enemy3(random.nextInt(Game.width)+Game.width, random.nextInt(Game.height - Paint.asteroide4.getHeight()), ID.Enemy3, handler));
					
				}
				
				
				// Level 9 .........................................................................
				// scoreFactor: 2600 | To current level: about 81 enemies | To the next level: about 41 enemies
				
				else if(level == 9) {
					
					
					for(int i=0; i<15; i++) {
						
						handler.addObject(new BasicEnemy3(random.nextInt(Game.width)+Game.width, random.nextInt(Game.height - Paint.asteroide3.getHeight()), ID.BasicEnemy3, handler));
					
					}
					
					for(int i=0; i<15; i++) {
						
						handler.addObject(new BasicEnemy1(random.nextInt(Game.width)-Game.width, random.nextInt(Game.height - Paint.asteroide.getHeight()), ID.BasicEnemy1, handler));
						
					}
					
					handler.addObject(new Enemy3(random.nextInt(Game.width)+Game.width, random.nextInt(Game.height - Paint.asteroide4.getHeight()), ID.Enemy3, handler));
					handler.addObject(new Enemy3(random.nextInt(Game.width)+Game.width, random.nextInt(Game.height - Paint.asteroide4.getHeight()), ID.Enemy3, handler));
					handler.addObject(new Enemy3(random.nextInt(Game.width)+Game.width, random.nextInt(Game.height - Paint.asteroide4.getHeight()), ID.Enemy3, handler));
					handler.addObject(new Enemy3(random.nextInt(Game.width)+Game.width, random.nextInt(Game.height - Paint.asteroide4.getHeight()), ID.Enemy3, handler));
					handler.addObject(new Enemy2(random.nextInt(Game.width), random.nextInt(Game.height)-Game.height, ID.Enemy2, handler));
					handler.addObject(new Enemy2(random.nextInt(Game.width), random.nextInt(Game.height)-Game.height, ID.Enemy2, handler));
					handler.addObject(new Enemy2(random.nextInt(Game.width), random.nextInt(Game.height)-Game.height, ID.Enemy2, handler));
					handler.addObject(new Enemy2(random.nextInt(Game.width), random.nextInt(Game.height)-Game.height, ID.Enemy2, handler));
					
				}
				
				
				// Level 10 .........................................................................
				// scoreFactor: 2300 | To current level: 83 enemies | To the next level: 43 enemies
				
				else if(level == 10) {
					
					scoreFactor-=300;
					
					for(int i=0; i<15; i++) {
						
						handler.addObject(new BasicEnemy3(random.nextInt(Game.width)+Game.width, random.nextInt(Game.height - Paint.asteroide3.getHeight()), ID.BasicEnemy3, handler));
					
					}
					
					for(int i=0; i<20; i++) {
						
						handler.addObject(new BasicEnemy2(random.nextInt(Game.width - (Paint.asteroide2.getWidth())/2), random.nextInt(Game.height)-(Game.height+200), ID.BasicEnemy2, handler));
						
					}
					
					handler.addObject(new Enemy3(random.nextInt(Game.width)+Game.width, random.nextInt(Game.height - Paint.asteroide4.getHeight()), ID.Enemy3, handler));
					handler.addObject(new Enemy3(random.nextInt(Game.width)+Game.width, random.nextInt(Game.height - Paint.asteroide4.getHeight()), ID.Enemy3, handler));
					handler.addObject(new Enemy3(random.nextInt(Game.width)+Game.width, random.nextInt(Game.height - Paint.asteroide4.getHeight()), ID.Enemy3, handler));
					handler.addObject(new Enemy2(random.nextInt(Game.width), random.nextInt(Game.height)-Game.height, ID.Enemy2, handler));
					handler.addObject(new Enemy2(random.nextInt(Game.width), random.nextInt(Game.height)-Game.height, ID.Enemy2, handler));
					handler.addObject(new Enemy2(random.nextInt(Game.width), random.nextInt(Game.height)-Game.height, ID.Enemy2, handler));
					handler.addObject(new Enemy2(random.nextInt(Game.width), random.nextInt(Game.height)-Game.height, ID.Enemy2, handler));
					
				}
				
				
				// Level 10 + .........................................................................
				// scoreFactor: 2300 | To current level: minimum 85 enemies | To the next level: minimum 40 enemies
				
				else if(level > 10) {
					
					
					a = random.nextInt(30);
					b = random.nextInt(30);
					c = random.nextInt(30);
					d = random.nextInt(7);
					e = random.nextInt(5);
							
					for(int i=0; i<a; i++) {
								
						handler.addObject(new BasicEnemy1(random.nextInt(Game.width)-Game.width, random.nextInt(Game.height - Paint.asteroide.getHeight()), ID.BasicEnemy1, handler));
							
					}
							
					for(int i=0; i<b; i++) {
							
						handler.addObject(new BasicEnemy2(random.nextInt(Game.width - (Paint.asteroide2.getWidth())/2), random.nextInt(Game.height)-(Game.height+200), ID.BasicEnemy2, handler));
								
					}
							
					for(int i=0; i<c; i++) {
								
						handler.addObject(new BasicEnemy3(random.nextInt(Game.width)+Game.width, random.nextInt(Game.height - Paint.asteroide3.getHeight()), ID.BasicEnemy3, handler));
							
					}
							
					for(int i=0; i<d; i++) {
							
						handler.addObject(new Enemy2(random.nextInt(Game.width), random.nextInt(Game.height)-Game.height, ID.Enemy2, handler));
								
					}
							
					for(int i=0; i<e; i++) {
								
						handler.addObject(new Enemy3(random.nextInt(Game.width)+Game.width, random.nextInt(Game.height - Paint.asteroide4.getHeight()), ID.Enemy3, handler));
								
					}	
							
					
				}
				
			
		}
		
		
		// Condition to spawn enemies at level 10+ .........................................................................
		// This condition only prevents the number of enemies from being less than 56
		
		
		if(level > 10 && handler.object.size()<85) {
			
			handler.addObject(new BasicEnemy1(random.nextInt(Game.width)-Game.width, random.nextInt(Game.height - Paint.asteroide.getHeight()), ID.BasicEnemy1, handler));
			handler.addObject(new BasicEnemy2(random.nextInt(Game.width - (Paint.asteroide2.getWidth())/2), random.nextInt(Game.height)-Game.height, ID.BasicEnemy2, handler));
			handler.addObject(new BasicEnemy3(random.nextInt(Game.width)+Game.width, random.nextInt(Game.height - Paint.asteroide3.getHeight()), ID.BasicEnemy3, handler));
			
		}	

		resetScore();
		
	}
	
	
	// RESET SCORE METHOD *******************************************************************************************************************************************
	// This reset all variables in game and the game objects
	
	
	private void resetScore() {
		
		
		if(Player.health == 0) {
			
			if(Player.end) {
			
				handler.object.clear();
				Player.health = 100;
				Player.green = 255;
				Player.red = 0;
				scoreKeep = 0;
				scoreFactor = 0;
				level = 0;
				highscore();
				Player.end = false;
				Game.gameState = ID.TryAgain;
					
			}
			
		}

		
	}
	
	
	// HIGHSCORE METHOD *******************************************************************************************************************************************
	
	
	public void highscore() {
		
		if(Score.score > oldScore) {
			
			highscore = Score.score;
			oldScore = Score.score;
			
		}
		
	}
	

}
