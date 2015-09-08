package group47.bubblebobble.tilemap;

import static org.junit.Assert.assertEquals;

import java.awt.Graphics2D;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TileMapTest {

	/** The tile map. */
	private TileMap tileMap;

	@Mock
	private Graphics2D g;

	private int tileSize = 30;
	private int numOfCols = 2;
	private int numOfRows = 2;

	@Before
	public void setUp() throws IOException {
		tileMap = new TileMap(tileSize);
		tileMap.loadTiles("/Test/Test_Tile.gif");
		tileMap.loadMap("/Test/Test_Map.map");
	}

	@Test
	public void checkXandY() {
		assertEquals(tileMap.getx(), 0);
		assertEquals(tileMap.gety(), 0);
	}

	@Test
	public void checkWidthandHeigth() {
		assertEquals(tileMap.getWidth(), tileSize * numOfCols);
		assertEquals(tileMap.getHeight(), tileSize * numOfRows);
	}

	@Test
	public void checkTileSize() {
		assertEquals(tileMap.getTileSize(), tileSize);
	}

	@Test
	public void checkType() {
		// Tile 1
		assertEquals(tileMap.getType(0, 0), 0);
		// Tile 2
		assertEquals(tileMap.getType(0, 1), 0);
		// Tile 3
		assertEquals(tileMap.getType(1, 0), 1);
		// Tile 4
		assertEquals(tileMap.getType(1, 1), 1);
	}

	@Test
	public void checkNumOfRowsAndCols() {
		assertEquals(tileMap.getNumCols(), 2);
		assertEquals(tileMap.getNumRows(), 2);
	}

	@Test
	public void checkLoadMap() {
		assertEquals(tileMap.getMap().length, 2);
	}

	@Test
	public void checkLoadTiles() {
		assertEquals(tileMap.getTiles().length, 2);
	}

}
