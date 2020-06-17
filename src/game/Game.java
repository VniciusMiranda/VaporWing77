package game;


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.util.Timer;
import javax.swing.JFrame;
import game_objects.GameObject;
import game_objects.Player;
import game_objects.Weapons;
import screens.LoadScreen;
import screens.Maps;
import screens.Menu;
import screens.TryAgain;


public class Game extends Canvas implements Runnable{

	
	// VARIABLES *******************************************************************************************************************************************
	
	
	private static final long serialVersionUID = 213441524894808080L;
	
	
	// Thread variables
	private Thread thread;
	private boolean running;
	public static int FRAMES = 0;
	
	
	RenderHandler renderHandler = new RenderHandler();
	private GameObject player;
	private Score score = new Score(renderHandler);
	private TryAgain tryagain = new TryAgain(renderHandler);
	private Menu menu = new Menu(renderHandler);;
	private Maps maps = new Maps();
	private LoadScreen loads = new LoadScreen();
	
	
	public static double ang;
	public static int width = 1280, height = 720;
	private boolean pause = false;
	Paint paint = new Paint();
	Timer temp = new Timer();
	private Audio audio = new Audio();
	private JFrame frame;
	
	
	// Initial screen
    public static ID gameState = ID.LoadScreen;
	
    
    // CONSTRUCTOR *******************************************************************************************************************************************
    
    
	public Game() {
		
		
		// Frame configuration
		frame = new JFrame();
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);	
		frame.setTitle("Vapor Wing 77");
		frame.setResizable(false);
		frame.setVisible(true);
		frame.add(this);

		
		// Adding screens input method
		this.addKeyListener(new KeyInput(renderHandler));
		this.addMouseListener(menu);
		this.addMouseListener(maps);
		this.addMouseListener(tryagain);
		this.addMouseMotionListener(menu);
		
		
		// Player shooting method
		this.addMouseListener(new  MouseAdapter() {
			
			public void mousePressed(MouseEvent e) { 
				
				if(gameState == ID.Game) {
					if(!pause) {
						if(e.getButton() == MouseEvent.BUTTON1) {
		
							for(int i=0; i< renderHandler.object.size(); i++) {
								if(renderHandler.object.get(i).getId() == ID.Player) player = renderHandler.object.get(i) ;
							}
							
							renderHandler.addObject(new Weapons(player.getX(), player.getY(), ID.Weapon, renderHandler));
							audio.playingOnce("data/sound_effects/fire_sound.wav");
						
						}
					}
				}	
			}
			
		});
		
		
		// Sets the player and bullet angle
		this.addMouseMotionListener(new MouseAdapter() {
			
			public void mouseMoved(MouseEvent e) {
				
				if(gameState == ID.Game) {
					
					if(!pause) {
					
						for(int i=0; i< renderHandler.object.size(); i++) {
							if(renderHandler.object.get(i).getId() == ID.Player) player = renderHandler.object.get(i) ;
						}
						
						if(ang>0.35 && ang < 1.22) {
							Player.angulo = Math.PI/4; 
						}
						else if(ang>1.22 && ang < 1.92) {
							Player.angulo = 1.57; 
						}
						else if(ang>1.92 && ang < 2.79) {
							Player.angulo = 2.35; 
						}
						else if(ang>2.79 || ang < -2.79) {
							Player.angulo = 3.14; 
						}
						else if(ang>-0.35 && ang < 0.35) {
							Player.angulo = 0; 
						}
						else if(ang<-0.35 && ang > -1.22) {
							Player.angulo = -0.785; 
						}
						else if(ang<-1.22 && ang > -1.92) {
							Player.angulo = -1.57; 
						}
						else if(ang<-1.92 && ang > -2.79) {
							Player.angulo = -2.35; 
						}
							
					    double d = Point.distance(player.getX(), player.getY(), e.getX(), e.getY());
					    if (e.getX() - player.getX()> 0 ) {			 	
					        ang = Math.acos(((e.getY() - player.getY()) / d)*-1);
					        
					    } else if(e.getX() - player.getX() < 0){
					        ang =( Math.acos((e.getY() - player.getY())/(d*-1)))*-1;
					        
					    }
				    
					}
				}
			
			}
			
		});
		
		
		// Pause Method
		this.addKeyListener(new KeyAdapter() {	
			public void keyPressed(KeyEvent e) {
				if(gameState == ID.Game) {	
					if(e.getKeyCode() == KeyEvent.VK_P) {
						
						if(pause) {
							pause = false;
						}
						else{
							pause = true;
						}
							
					}
				}
			}	
		});
		
			
		start();
		
	    
	}
	
	
	// START METHOD *******************************************************************************************************************************************
	
	
	public void start() {
		
		thread = new Thread(this); 
		thread.start();
		running = true;

		audio.playingForeverAfterSomeTime("data/sound_effects/music.wav", 340000, 8700);
		Paint.renderImages();
		Paint.background = Paint.background1;
		
	}
	
	
	// THREAD RUN FUNCTION *******************************************************************************************************************************************
	
	
	@Override
	public void run() {
		
		
		this.requestFocus();
	
		
		running = true;
		boolean render;
		
		int FPS = 60;
		double UPDATE = 1000000000 / FPS;
		double firstTime;
		double lastTime = System.nanoTime();
		double passedTime = 0;
		double frameTime = 0;
		int frames = 0;
		
		
		// Game Loop .........................................................................
		
		
		while(running) {
			
			render = false;
			
			firstTime = System.nanoTime();
			passedTime += (firstTime - lastTime) / UPDATE;
			frameTime += firstTime - lastTime;
			lastTime = firstTime;

			
				if(passedTime >= 1) {
					
					
					render = true;
					
					update();
					
					
					passedTime--;	
				}
				
				if(frameTime >= 1000000000) {
					
					FRAMES = frames;
					frames = 0;
					frameTime = 0;	
					
				}
				
				if(render) {
					
					render();
					
					frames++;
					
				}else {
					
					try {
						
						Thread.sleep(1);
						
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}	
				
		}

		
	}	
	
	
	// UPDATE *******************************************************************************************************************************************
	
	
	public void update(){	
		
	    if(gameState == ID.Game) {
	    	if(!pause) {
	    		renderHandler.update();
				score.updateTotalScore();
	    	}
	    } 
	    
	}
	
	
	// RENDER *******************************************************************************************************************************************
	

	public void render(){	
		
		BufferStrategy bufferStrategy = this.getBufferStrategy(); 
		
		if(bufferStrategy==null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics graphics = bufferStrategy.getDrawGraphics();    
	    paint(graphics);                                 
	    
	    
	    if(gameState == ID.Game) {
	    	paint.paints(graphics);
	    	renderHandler.render(graphics);
	    	if(pause) {
	    		graphics.setColor(Color.YELLOW);
	    		graphics.setFont(Paint.fonte("data/airstrike.ttf", 100));
	    		graphics.drawString("GAME", 481, 323);
	    		graphics.drawString("PAUSED", 428, 423);
	    	}
	    } else if(gameState == ID.Menu) {
	    	menu.render(graphics);
	    }else if(gameState == ID.Maps) {
	    	maps.render(graphics);
	    }else if(gameState == ID.LoadScreen) {
	    	loads.render(graphics);
	    }else if(gameState == ID.TryAgain) {
			tryagain.render(graphics);
		}
		
	    
    	graphics.dispose(); 
    	bufferStrategy.show();
	
	}
	
	
	// Main Function *******************************************************************************************************************************************
	
	
	public static void main(String[] args) {
		
		new Game();
		
	}
	

}	
