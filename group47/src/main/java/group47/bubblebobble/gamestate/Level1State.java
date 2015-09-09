package group47.bubblebobble.gamestate;

import group47.bubblebobble.entity.Enemy;
import group47.bubblebobble.entity.Player;
import group47.bubblebobble.entity.enemies.Level1Enemy;
import group47.bubblebobble.main.GamePanel;
import group47.bubblebobble.tilemap.TileMap;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * The Class Level1State.
 */
public class Level1State extends GameState {
	
	private Player player;
	private ArrayList<Enemy> enemies;
	
	/** The tile map. */
	private TileMap tileMap;
	
	/**
	 * Instantiates a new level1 state.
	 * 
	 * @param gsm
	 *           the gsm
	 */
	public Level1State(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}
	
	/*
	 * Init
	 */
	@Override
	public void init() {
		
		// tile width/ height of 30px
		tileMap = new TileMap(30);
		tileMap.loadTiles("/Tiles/Bubble_Tile.gif");
		tileMap.loadMap("/Test/Test_Open_Floorr.map");
		player = new Player(tileMap);
		player.setPosition(100d, 100d);
		
		enemies = new ArrayList<Enemy>();
		Level1Enemy e1;
		Level1Enemy e2;
		Level1Enemy e3;
		Level1Enemy e4;
		Level1Enemy e5;
		e1 = new Level1Enemy(tileMap);
		e2 = new Level1Enemy(tileMap);
		e3 = new Level1Enemy(tileMap);
		e4 = new Level1Enemy(tileMap);
		e5 = new Level1Enemy(tileMap);
		e1.setPosition(300d, 100d);
		e2.setPosition(500d, 100d);
		e3.setPosition(300d, 250d);
		e4.setPosition(500d, 400d);
		e5.setPosition(100d, 550d);
		enemies.add(e1);
		enemies.add(e2);
		enemies.add(e3);
		enemies.add(e4);
		enemies.add(e5);
	}
	
	/*
	 * Update
	 */
	@Override
	public void update() {
		player.update();
		
		// update all enemies
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).update();
		}
		
		// collision check between player and enemies
		for (int i = 0; i < enemies.size(); i++) {
			if (player.intersects(enemies.get(i))) {
				if (enemies.get(i).isCaught()) {
					// kill enemy
					enemies.remove(i);
				} else {
					// kill player
					System.out.println("Player Should die now");
				}
			}
		}
		// collision check between projectiles and enemies
		for (int i = 0; i < enemies.size(); i++) {
			for (int j = 0; j < player.getProjectiles().size(); j++) {
				if (player.getProjectiles().get(j).intersects(enemies.get(i))) {
					player.getProjectiles().remove(j);
					j--;
					enemies.get(i).setCaught();
					
				}
			}
		}
		
	}
	
	/*
	 * Draw everything of level 1
	 */
	@Override
	public void draw(Graphics2D g) {
		
		// set background color of a clear screen
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		
		// draw tilemap
		tileMap.draw(g);
		player.draw(g);
		
		// draw all enemies
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).draw(g);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see GameState.GameState#keyPressed(int)
	 */
	@Override
	public void keyPressed(int k) {
		if (k == KeyEvent.VK_LEFT)
			player.setLeft(true);
		if (k == KeyEvent.VK_RIGHT)
			player.setRight(true);
		if (k == KeyEvent.VK_UP)
			player.setUp(true);
		if (k == KeyEvent.VK_DOWN)
			player.setDown(true);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see GameState.GameState#keyReleased(int)
	 */
	@Override
	public void keyReleased(int k) {
		if (k == KeyEvent.VK_LEFT)
			player.setLeft(false);
		if (k == KeyEvent.VK_RIGHT)
			player.setRight(false);
		if (k == KeyEvent.VK_UP)
			player.setUp(false);
		if (k == KeyEvent.VK_DOWN)
			player.setDown(false);
	}
	
}
