package group47.bubblebobble.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import group47.bubblebobble.tilemap.TileMap;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

/**
 * The Class PlayerTest.
 */
public class PlayerTest {

	/** The tile map. */
	private TileMap tileMap;

	/** The tile size. */
	private int tileSize = 30;

	/** The num of cols. */
	private int numOfCols = 2;

	/** The num of rows. */
	private int numOfRows = 2;

	/** The player. */
	private Player player;

	/** The projectile. */
	private Projectile projectile;

	/**
	 * SetUp.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Before
	public void setUp() throws IOException {

		tileMap = new TileMap(tileSize);

		tileMap.loadTiles("/Test/Test_Tile.gif");
		tileMap.loadMap("/Test/Test_Map.map");
		player = new Player(tileMap);
		projectile = new Projectile(tileMap);
	}

	/**
	 * Update test.
	 */
	@Test
	public void updateTest() {
		player.setDown(true);
		player.update();
		assertEquals(player.getProjectiles().size(), 1);

	}

	/**
	 * Hit test.
	 */
	@Test
	public void hitTest() {
		player.hit(1);
		assertEquals(player.getLives(), 2);
	}

	/**
	 * Hit dead test.
	 */
	@Test
	public void hitDeadTest() {
		player.hit(3);
		assertEquals(player.getIsAlive(), false);
		assertEquals(player.getLives(), 0);
	}

	/**
	 * Hit out of bounds test.
	 */
	@Test
	public void hitOutOfBoundsTest() {
		player.hit(4);
		assertEquals(player.getIsAlive(), false);
		assertEquals(player.getLives(), 0);
	}

	/**
	 * Hit flinch test.
	 */
	@Test
	public void hitFlinchTest() {
		player.setFlinch(true);
		player.hit(1);
		assertEquals(player.getLives(), 3);
	}

	/**
	 * Next position left test.
	 */
	@Test
	public void nextPositionLeftTest() {
		player.setLeft(true);
		player.setMovSpeed(3.0);
		player.setMaxSpeed(2.0);
		player.getNextPosition();
		assertEquals(player.getDx(), -2.0, 0.1);
	}

	/**
	 * Next position right test.
	 */
	@Test
	public void nextPositionRightTest() {
		player.setRight(true);
		player.setMovSpeed(3.0);
		player.setMaxSpeed(2.0);
		player.getNextPosition();
		assertEquals(player.getDx(), 2.0, 0.1);
	}

	/**
	 * Next position stop test.
	 */
	@Test
	public void nextPositionStopTest() {
		player.setRight(false);
		player.setLeft(false);
		player.getNextPosition();
		assertEquals(player.getDx(), 0, 0);
	}

	/**
	 * Next position up test.
	 */
	@Test
	public void nextPositionUpTest() {
		player.setUp(true);
		player.getNextPosition();
		assertTrue(player.getJumping());
	}

	/**
	 * Next position falling test.
	 */
	@Test
	public void nextPositionFallingTest() {
		player.setFallSpeed(1);
		player.setFalling(true);
		player.getNextPosition();
		assertTrue(!player.getJumping());
	}
}
