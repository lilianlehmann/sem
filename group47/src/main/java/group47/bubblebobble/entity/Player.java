package group47.bubblebobble.entity;

import group47.bubblebobble.tilemap.TileMap;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Player extends MapObject {
	
	private int health;
	private int maxHealth;
	
	private long lastFireTime;
	private int fireDelay; //ms
	private ArrayList<Projectile> projectiles;
	
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
		fireDelay = 500;
		
		projectiles = new ArrayList<Projectile>();
		
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
	
	public ArrayList<Projectile> getProjectiles() {
		return projectiles;
	}
	
	/*
	 * Called every frame. Updates player position, looks for collision and then puts the player in the new position
	 */
	public void update() {
		for(int i = 0; i < projectiles.size(); i++)
			projectiles.get(i).update();
		
		getNextPosition();
		checkTileMapCollision();
		setPosition(xtemp, ytemp);
		
		if(down) {
			if(lastFireTime + fireDelay < System.currentTimeMillis()) {
				lastFireTime = System.currentTimeMillis();
				Projectile projectile = new Projectile(tileMap);
				projectile.setPosition(x, y);
				projectiles.add(projectile);
			}
		}
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
		super.draw(g);
		for(int i = 0; i < projectiles.size(); i++)
			projectiles.get(i).draw(g);
	}
	
}
