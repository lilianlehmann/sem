package group47.bubblebobble.entity;

import static org.junit.Assert.*;
import group47.bubblebobble.tilemap.TileMap;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class ProjectileTest {
	private TileMap tileMap;
	private int tileSize = 30;
	
	private Projectile projectile;
	
	@Before
	public void setUp() throws IOException {
		tileMap = new TileMap(tileSize);
		tileMap.loadTiles("/Test/Test_Tile.gif");
		tileMap.loadMap("/Test/Test_Map.map");
	}
	
	@Test
	public void testConstructor() {
		projectile = new Projectile(tileMap);
		assertEquals(projectile.getIsAlive(), true);
	}
	
	@Test
	public void testUpdate() {
		projectile = new Projectile(tileMap);
		projectile.update();
		assertTrue(projectile.dx > 0);
	}

}
