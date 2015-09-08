package group47.bubblebobble.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import TileMap.TileMap;

public class Player extends MapObject {
	
	private int health;
	private int maxHealth;
	
	private BufferedImage sprite;
	
	public Player(TileMap tm) {
		super(tm);
		width = 30;
		height = 30;
		cwidth = 30;
		cheight = 30;
		movSpeed = 0.3;
		maxSpeed = 1.6;
		stopSpeed = .4;
		
		fallSpeed = .15;
		maxFallSpeed = 4.0;
		jumpStart = -4.8;
		stopJumpSpeed = .3;
		
		facingRight = true;
		
		health = maxHealth = 5;
		
		//Load sprite
		try {
			BufferedImage spritesheet = ImageIO.read(
				getClass().getResourceAsStream(
						"/Tiles/Bubble_Tile.gif"
						)
			);
			sprite = spritesheet.getSubimage(0, 30, 30, 30);
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Called every frame. Updates player position, looks for collision and then puts the player in the new position
	 */
	public void update() {
		getNextPosition();
		checkTileMapCollision();
		setPosition(xtemp, ytemp);
	}
	
	
	/*
	 * Applies all movement logic based on gravity and key input
	 */
	public void getNextPosition() {
		if(left) { //If move left is pressed
			dx -= movSpeed;
			if(dx < -maxSpeed)
				dx = -maxSpeed;
		}
		else if(right) { //If move right is pressed
			dx += movSpeed;
			if(dx > maxSpeed)
				dx = maxSpeed;
		}
		else {
			dx = 0;
		}
		
		if(up) jumping = true; //If jump is pressed
		
		if(jumping && !falling) {
			dy = jumpStart;
			falling = true;
		}
		
		if(falling) {
			dy += fallSpeed;
			if(dy > 0) jumping = false;
			if(dy < 0 && !jumping) dy += stopJumpSpeed;
			if(dy > maxFallSpeed)
				dy = maxFallSpeed;
		}
	}
	
	/*
	 * Draws the player
	 */
	public void draw(Graphics2D g) {
		//draw player
		g.drawImage(
				sprite, 
				(int) (x - width / 2), 
				(int) (y - height / 2), 
				null
		);
	}
	
}
