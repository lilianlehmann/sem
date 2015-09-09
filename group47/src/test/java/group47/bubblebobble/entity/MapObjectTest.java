package group47.bubblebobble.entity;

import static org.junit.Assert.*;
import group47.bubblebobble.tilemap.TileMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public abstract class MapObjectTest {
  
  private TileMap tileMap;
  
  private int tileSize = 30;
  private int numOfCols = 5;
  private int numOfRows = 5;
  
  private MapObject object;
  
  @Before
  public void setUp() throws Exception {
    tileMap = new TileMap(tileSize);
    tileMap.loadTiles("/Test/Test_Tile.gif");
    tileMap.loadMap("/Test/Test_MapObject.map");
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void test() {
    fail("Not yet implemented");
  }

}
