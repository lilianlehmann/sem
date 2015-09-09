package group47.bubblebobble.gamestate;

import group47.bubblebobble.main.GamePanel;
import group47.bubblebobble.tilemap.TileMap;
import group47.bubblebobble.entity.*;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

/**
 * The Class Level1State.
 */
public class Level1State extends GameState {
	
	private Player player;
	
	/** The tile map. */
	private TileMap tileMap;

	/**
	 * Instantiates a new level1 state.
	 *
	 * @param gsm
	 *            the gsm
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
		tileMap.loadMap("/Maps/level1-2.map");
		player = new Player(tileMap);
		player.setPosition(100d, 100d);
	}

	/*
	 * Update
	 */
	@Override
	public void update() {
		player.update();
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
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see GameState.GameState#keyPressed(int)
	 */
	@Override
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_LEFT) player.setLeft(true);
		if(k == KeyEvent.VK_RIGHT) player.setRight(true);
		if(k == KeyEvent.VK_UP) player.setUp(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see GameState.GameState#keyReleased(int)
	 */
	@Override
	public void keyReleased(int k) {
		if(k == KeyEvent.VK_LEFT) player.setLeft(false);
		if(k == KeyEvent.VK_RIGHT) player.setRight(false);
		if(k == KeyEvent.VK_UP) player.setUp(false);
	}

}
