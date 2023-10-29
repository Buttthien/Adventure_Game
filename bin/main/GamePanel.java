package main;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
	// Screen Setting
	final int originalTileSize =16;
	final int scale = 3;
	
	public final int tileSize = originalTileSize * scale;
	
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	
	public final int screenWidth = tileSize * maxScreenCol;
	public final int screenHeight = tileSize * maxScreenRow;
	
	//WORLD_MAP
	public final int maxWorldCol = 16;
	public final int maxWorldRow = 12;
	public final int worldWidth	= tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow ;
	
	//FPS FBI
	int FPS = 60;
	
	TileManager tileM = new TileManager(this);
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;

	public CollisionChecker cChecker = new CollisionChecker(this);
	public Player player = new Player(this,keyH);
	
	//player's default position

	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth,screenHeight));
		this.setBackground(Color.black);
		
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		
		this.setFocusable(true);
	}
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
/*   
	@Override
	
	public void run() {
		
		//long currentTime = System.nanoTime();
		double drawInterval = 1000000000/FPS; 	//0.01666666
		double nextDrawTime = System.nanoTime() + drawInterval;
		
		while(gameThread != null) {
				
		//1 : UPDATE
		update();	
		//2 : DRAW
		repaint();
		
		
		
		try {
			double remainingTime = nextDrawTime - System.nanoTime();
			remainingTime /= 1000000;
			
			if(remainingTime < 0)
				remainingTime = 0;
			
			Thread.sleep((long) remainingTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		}
		
	}
	*/
	@Override
	public void run() {
		
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime; 
		
		while(gameThread != null) {
			
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime)/ drawInterval;
			
			lastTime = currentTime;
			
			if(delta >= 1) {
				 update();
				 repaint();
				 delta--;
			}
			
		}
	}

	public void update() {	

		player.update();
	
	}

	public void paintComponent(Graphics g) {
		
			super.paintComponent(g);
			
			Graphics2D g2 =(Graphics2D)g;
			
			tileM.draw(g2);
			
			player.draw(g2);
			
			g2.dispose();
			
		}
}