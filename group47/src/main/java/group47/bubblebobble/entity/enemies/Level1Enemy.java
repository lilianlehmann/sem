package group47.bubblebobble.entity.enemies;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import group47.bubblebobble.entity.Enemy;
import group47.bubblebobble.tilemap.TileMap;

public class Level1Enemy extends Enemy {

	public Level1Enemy(TileMap tm) {
		
		super(tm);
		
		width = 30;
		height = 30;
		cwidth = 30;
		cheight = 30;
		movSpeed = 0.3;
		maxSpeed = 1.6;
		stopSpeed = .4;
		
		fallSpeed = .5;
		maxFallSpeed = 4.0;
		jumpStart = -4.8;
		stopJumpSpeed = .3;
		
		facingRight = true;
		
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
	
	private void getNextPosition() {
		dx = 0;
		
		if(falling) {
			dy += fallSpeed;
			if(dy > 0) jumping = false;
			if(dy < 0 && !jumping) dy += stopJumpSpeed;
			if(dy > maxFallSpeed)
				dy = maxFallSpeed;
		} else {
			dy = 0;
		}
	}
	
	public void update() {
		
		//update position
		getNextPosition();
		checkTileMapCollision();
		setPosition(xtemp, ytemp);
		
	}
	
	public void draw(Graphics2D g) {
		super.draw(g);
	}
	
	
	
}
