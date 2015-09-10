package group47.bubblebobble.entity;

import group47.bubblebobble.tilemap.TileMap;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

/**
 * The Class Player.
 */
public class Player extends MapObject {

	/** The lives. */
	private int lives;

	/** The max lives. */
	private int maxLives;

	/** The score. */
	private int score;

	/** The extra live. */
	private int extraLive;

	/** The is alive. */
	private boolean isAlive;

	/** The flinching. */
	private boolean flinching;

	/** The flinch timer. */
	private long flinchTimer;

	/** The last fire time. */
	private long lastFireTime;

	/** The fire delay. */
	private int fireDelay; // ms

	/** The projectiles. */
	private ArrayList<Projectile> projectiles;

	/**
	 * Instantiates a new player.
	 *
	 * @param tm
	 *            the tm
	 */
	public Player(TileMap tm) {
		super(tm);
		width = 30;
		// height of player must be bigger than tile height, else the player can
		// come stuck between platform tiles
		height = 30;
		cwidth = 30;
		cheight = 31;
		movSpeed = 0.3;
		maxSpeed = 2.5;
		stopSpeed = .4;

		fallSpeed = .15;
		maxFallSpeed = 4.0;
		jumpStart = -7;
		stopJumpSpeed = .3;

		facingRight = true;

		lives = maxLives = 3;
		extraLive = 300;
		fireDelay = 500;

		projectiles = new ArrayList<Projectile>();

		// Load sprite
		try {
			BufferedImage spritesheet = ImageIO.read(getClass()
					.getResourceAsStream("/Tiles/Bubble_Tile.gif"));
			sprite = spritesheet.getSubimage(0, 30, 30, 30);
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets the projectiles.
	 *
	 * @return the projectiles
	 */
	public ArrayList<Projectile> getProjectiles() {
		return projectiles;
	}

	/**
	 * Update. Called every frame. Updates player position, looks for collision
	 * and then puts the player in the new position
	 */
	public void update() {
		for (int i = 0; i < projectiles.size(); i++)
			projectiles.get(i).update();

		getNextPosition();
		checkTileMapCollision();
		setPosition(xtemp, ytemp);

		if (down) {
			if (lastFireTime + fireDelay < System.currentTimeMillis()) {
				lastFireTime = System.currentTimeMillis();
				Projectile projectile = new Projectile(tileMap);
				projectile.setPosition(x, y);
				projectiles.add(projectile);
			}
		}

		// check done flinching
		if (flinching) {
			long elapsed = (System.nanoTime() - flinchTimer) / 1000000;
			if (elapsed > 1000) {
				flinching = false;
			}
		}
	}

	/**
	 * If player gets hit, one live will be removed and player gets into a
	 * flinching state. When in flinching state he cannot take damage.
	 *
	 * @param damage
	 *            the damage
	 */
	public void hit(int damage) {
		if (flinching) {
			return;
		}
		lives -= damage;
		if (lives < 0) {
			lives = 0;
		}
		if (lives == 0) {
			isAlive = false;
		}

		setPosition(100d, 100d);
		flinching = true;
		flinchTimer = System.nanoTime();

	}

	/**
	 * Gets the next position. Applies all movement logic based on gravity and
	 * key input
	 *
	 * @return the next position
	 */
	public void getNextPosition() {
		if (left) { // If move left is pressed
			dx -= movSpeed;
			if (dx < -maxSpeed)
				dx = -maxSpeed;
		} else if (right) { // If move right is pressed
			dx += movSpeed;
			if (dx > maxSpeed)
				dx = maxSpeed;
		} else {
			dx = 0;
		}

		if (up)
			jumping = true; // If jump is pressed

		if (jumping && !falling) {
			dy = jumpStart;
			falling = true;
		}

		if (falling) {
			dy += fallSpeed;
			if (dy > 0)
				jumping = false;
			if (dy < 0 && !jumping)
				dy += stopJumpSpeed;
			if (dy > maxFallSpeed)
				dy = maxFallSpeed;
		}
	}

	/*
	 * Draws the player
	 */
	@Override
	public void draw(Graphics2D g) {
		// draw player
		super.draw(g);
		for (int i = 0; i < projectiles.size(); i++)
			projectiles.get(i).draw(g);
	}

	/**
	 * Gets the lives.
	 *
	 * @return the lives
	 */
	public int getLives() {
		return lives;
	}

	/**
	 * Gets the max lives.
	 *
	 * @return the max lives
	 */
	public int getMaxLives() {
		return maxLives;
	}

	/**
	 * Gets the checks if is alive.
	 *
	 * @return the checks if is alive
	 */
	public boolean getIsAlive() {
		return isAlive;
	}

	/**
	 * Gets the score.
	 *
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Sets the score.
	 *
	 * @param points
	 *            the new score
	 */
	public void setScore(int points) {
		score += points;
		if (score == extraLive) {
			lives++;
			extraLive = 600;
		}
	}

	/**
	 * Gets the extra live.
	 *
	 * @return the extra live
	 */
	public int getExtraLive() {
		return extraLive;
	}
}
