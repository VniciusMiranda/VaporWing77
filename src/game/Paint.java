package game;


import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Paint {
    
	
	// VARIABLES *******************************************************************************************************************************************
	
	
    public static BufferedImage nave, naveGrande, background, bullet, asteroide, asteroide2, asteroide3, asteroide4, explosion1, explosion2, ufg;
    public static BufferedImage menuBackground, menuBackground2, right, left, background1, background2, background3, tryAgainBackground;
    public static BufferedImage exp[] = new BufferedImage[10], explosion3, enemy2, explosionVortex, vectorExplosion3[] = new BufferedImage[12];
    public static BufferedImage vectorExplosion2[] = new BufferedImage[16], vectorExplosion1[] = new BufferedImage[56], vectorExplosion4[] = new BufferedImage[13];
    private static Font fonte;
    private static int n1=0, n2=0, n3=0, n4=0;
    
    
    // PAINT METHOD *******************************************************************************************************************************************
    
    
	public void paints(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g.create();
		g2.drawImage(background, -1, 0,Game.width,Game.height, null);
		g2.setColor(Color.YELLOW);
		g2.setFont(fonte("data/airstrike.ttf", 25));
		g2.drawString("Score: " + Score.score, 1080, 680);
		g2.drawString("HighScore: " + Score.highscore, 10, 680);
		g2.drawString("Level: " + Score.level, 1120, 30);
		g2.setFont(fonte("data/airstrike.ttf", 15));
		g2.drawString("FPS: " + Game.FRAMES, 10, 65);
		
	}
	
	
	// RENDER IMAGES METHOD *******************************************************************************************************************************************
	// All the sprites and images in the game are rendered by this method that initialize with the game
	// This is very very important because prevents the game from lagging at the spawn objects time or during the player movement
	
	
	public static void renderImages() {
		
		ufg = loadImage("data/UFG.png");
		naveGrande = loadImage("data/naveMaior.png");
		nave = loadImage("data/nave.png");
		bullet = loadImage("data/bullet.png");
		
		background1 = loadImage("data/backgrounds/bg1.jpg");
		background2 = loadImage("data/backgrounds/bg2.jpg");
		background3 = loadImage("data/backgrounds/bg3.jpg");
		tryAgainBackground = loadImage("data/backgrounds/bg4.jpg");
		menuBackground = loadImage("data/backgrounds/bg5.png");
		menuBackground2 = loadImage("data/backgrounds/bg6.jpg");
		
		asteroide = loadImage("data/enemies/asteroide.png");
		asteroide2 = loadImage("data/enemies/asteroide2.png");
		asteroide3 = loadImage("data/enemies/asteroide3.png");
		asteroide4 = loadImage("data/enemies/asteroide4.png");
		enemy2 = loadImage("data/enemies/vortex.png");
		
		right = loadImage("data/right.png");
		left = loadImage("data/left.png");

		explosion2 = loadImage("data/explosions/explosion2.png");
		explosion3 = loadImage("data/explosions/explosion3.png");
		explosionVortex = loadImage("data/explosions/vortexExplosion.png");
		explosion1 = loadImage("data/explosions/explosion.png");
		
		exp[0] = loadImage("data/explosion-nave/exp1.png");
		exp[1] = loadImage("data/explosion-nave/exp2.png"); 
		exp[2] = loadImage("data/explosion-nave/exp3.png"); 
		exp[3] = loadImage("data/explosion-nave/exp4.png");
		exp[4] = loadImage("data/explosion-nave/exp5.png"); 
		exp[5] = loadImage("data/explosion-nave/exp6.png"); 
		exp[6] = loadImage("data/explosion-nave/exp7.png"); 
		exp[7] = loadImage("data/explosion-nave/exp8.png"); 
		exp[8] = loadImage("data/explosion-nave/exp9.png");
		exp[9] = loadImage("data/explosion-nave/exp10.png");
		
		SpriteSheet sheet1 = new SpriteSheet(Paint.explosion1);
		SpriteSheet sheet2 = new SpriteSheet(Paint.explosion2);
		SpriteSheet sheet3 = new SpriteSheet(Paint.explosionVortex);
		SpriteSheet sheet4 = new SpriteSheet(Paint.explosion3);
		
		for(int z = 20; z<670;z+=96) {
			for(int i = 20; i < 670; i += 96) {		
				vectorExplosion1[n1] = sheet1.crop(i, z, 90, 70);
				n1++;
			}
		}
		
		for(int z = 0; z<260; z+=65) {
			for(int i = 0; i <= 256; i += 65) {
				vectorExplosion2[n2] = sheet2.crop(i, z, 60, 60);
				n2++;
			}
		}
		
		for(int i = 15; i < 1150; i += 95) {
			vectorExplosion3[n3] = sheet3.crop(i, 0, 80, 96);
			n3++;
		}
		
		for(int i = 50; i < 2400; i += 190) {
			vectorExplosion4[n4] = sheet4.crop(i, 0, 180, 190);
			n4++;
		}
		
	}
	
	
	// FONT METHOD *******************************************************************************************************************************************
	// Only return a font that can be a external font
	
	
	public static Font fonte(String path, int size) {
    
		try {
			fonte = Font.createFont(Font.TRUETYPE_FONT,new File(path)).deriveFont(Font.PLAIN, size);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return fonte;
	}
	
	
	// LOAD IMAGE METHOD *******************************************************************************************************************************************
	// This method loads and treats the images using BufferedImage
	
	
	public static BufferedImage loadImage(String path){   
		try{
			BufferedImage loadedImage = ImageIO.read(new File(path)); 
			BufferedImage formattedImage = new BufferedImage(loadedImage.getWidth(), loadedImage.getHeight(), BufferedImage.TYPE_INT_ARGB); 
	        
			formattedImage.getGraphics().drawImage(loadedImage, 0, 0, null); 
			
			return formattedImage;
		}
		catch(IOException exception){
			exception.printStackTrace();
			
			return null;
		}
	}
	
	
	// ROTATE METHOD *******************************************************************************************************************************************
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
	    
	    // Aplica a translação para evitar cortes na imagem
	    AffineTransform translationTransform = new AffineTransform();
	    translationTransform.translate(-dTransX, -dTransY);
	    tx.preConcatenate(translationTransform);

	    return new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR).filter(rotateImage, null);
	}
	
	
}	
