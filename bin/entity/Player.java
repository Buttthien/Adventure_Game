package entity;

//import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

	GamePanel gp;
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		
		this.gp = gp;
		this.keyH = keyH;
		
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidArea.width = 30;
		solidArea.height = 30;
		
		setDefaultValues();
		getPlayerImage();
	}
	public void setDefaultValues() {
		
		worldX = gp.tileSize * 5;
		worldY = gp.tileSize * 6;
		speed = 4;
		direction = "down";
	}
	public void getPlayerImage() {
		try {
			
			up1 = ImageIO.read(getClass().getResourceAsStream("/res/player/New Piskel1.png.gif"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/res/player/New Piskel1.png.gif"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/res/player/New Piskel2.png.gif"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/res/player/New Piskel2.png.gif"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/res/player/New Piskel3.png.gif"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/res/player/New Piskel3.png.gif"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/res/player/New Piskel4.png.gif"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/res/player/New Piskel4.png.gif"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void update() {	
	if(keyH.upPressed == true || keyH.downPressed == true ||
	keyH.leftPressed == true || keyH.rightPressed == true)
		{
		if(keyH.upPressed == true) { 
			direction = "up";
		}
		else if(keyH.downPressed == true) {
			direction = "down";
		}
		else if(keyH.leftPressed == true) {
			direction = "left";
		}
		else if(keyH.rightPressed == true) {
			direction = "right";
		}

		collisionOn = false;
		gp.cChecker.checkTile(this);

		if(collisionOn == false){
			switch (direction) {
				case "up": worldY -= speed; break;
				case "down": worldY += speed; break;
				case "left": worldX -= speed; break;
				case "right": worldX += speed; break;			
			}
		}
	}
	
	}
	public void draw(Graphics2D g2) { 
		
		//g2.setColor(Color.white);
		//g2.fillRect(x, y, gp.tileSize, gp.tileSize);
		BufferedImage image = null;
		switch(direction) {
		case "up":
			image = up1;
			break;
		case "down":
			image = down1;
			break;
		case "left":
			image = left1;
			break;
		case "right":
			image = right1;
			break;

		}
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
	}
}
      









