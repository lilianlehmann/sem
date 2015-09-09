package group47.bubblebobble.entity;

import group47.bubblebobble.tilemap.TileMap;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Projectile extends MapObject {
	
	public Projectile(TileMap tm) {
		super(tm);
		width = 20;
		height = 20;
		cwidth = 20;
		cheight = 20;
		dx = 5;
		
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
	
	public void draw(Graphics2D g) {
		g.drawImage(sprite, (int) (x - width / 2), (int) (y - height / 2), width, height, null);
	}
	
	public void update() {
		checkTileMapCollision();
		setPosition(xtemp, ytemp);
	}
}
