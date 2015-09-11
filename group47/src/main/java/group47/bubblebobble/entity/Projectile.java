package group47.bubblebobble.entity;

import group47.bubblebobble.tilemap.TileMap;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

/**
 * The Class Projectile.
 */
public class Projectile extends MapObject {

	/** The life time in ms */
	private int lifeTime;

	/** The float delay. */
	private int floatDelay;

	/** The last update time. */
	private long lastUpdateTime;

	/** The floating. */
	private boolean floating;

	/** The float speed. */
	private double floatSpeed;

	/**
	 * Instantiates a new projectile.
	 *
	 * @param tm
	 *            the tm
	 */
	public Projectile(TileMap tm) {
		super(tm);
		isAlive = true;
		width = 20;
		height = 20;
		cwidth = 20;
		cheight = 20;
		dx = 5;

		lifeTime = 7500;
		floatDelay = 700;
		lastUpdateTime = System.currentTimeMillis();
		floating = false;
		floatSpeed = .1;

		// Load sprite
		try {
			BufferedImage spritesheet = ImageIO.read(getClass()
					.getResourceAsStream("/Tiles/Bubble_Tile.gif"));
			sprite = spritesheet.getSubimage(0, 30, 30, 30);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Draw projectile
	 */
	@Override
	public void draw(Graphics2D g) {
		g.drawImage(sprite, (int) (x - width / 2), (int) (y - height / 2),
				width, height, null);
	}

	/**
	 * Update.
	 */
	public void update() {
		long timePassed = System.currentTimeMillis() - lastUpdateTime;
		floatDelay -= timePassed;
		lifeTime -= timePassed;
		lastUpdateTime = System.currentTimeMillis();
		if (floatDelay <= 0) {
			dx = 0;
			dy -= floatSpeed;
		}
		if (lifeTime <= 0) {
			isAlive = false;
			return;
		}

		checkTileMapCollision();
		setPosition(xtemp, ytemp);
	}

}
