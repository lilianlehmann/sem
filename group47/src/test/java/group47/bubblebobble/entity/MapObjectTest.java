package group47.bubblebobble.entity;

import static org.junit.Assert.*;

import java.awt.Rectangle;

import group47.bubblebobble.tilemap.TileMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito.*;
import org.mockito.Mockito;

public abstract class MapObjectTest {
  
  protected TileMap tileMap;
  
  private int tileSize = 30;
  private int numOfCols = 5;
  private int numOfRows = 5;
  
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
  public void testIntersectFalse() {
    MapObject o1 = Mockito.mock(MapObject.class, Mockito.CALLS_REAL_METHODS);
    Mockito.when(o1.getRectangle()).thenReturn(new Rectangle(
        30, 30, 30, 30
        ));
    MapObject o2 = Mockito.mock(MapObject.class, Mockito.CALLS_REAL_METHODS);
    Mockito.when(o1.getRectangle()).thenReturn(new Rectangle(
        60, 60, 30, 30
        ));
    assertFalse(o1.intersects(o2));
  };
  
  @Test
  public void testIntersectTrue() {
    MapObject o1 = Mockito.mock(MapObject.class, Mockito.CALLS_REAL_METHODS);
    Mockito.when(o1.getRectangle()).thenReturn(new Rectangle(
        30, 30, 30, 30
        ));
    MapObject o2 = Mockito.mock(MapObject.class, Mockito.CALLS_REAL_METHODS);
    Mockito.when(o1.getRectangle()).thenReturn(new Rectangle(
        30, 30, 30, 30
        ));
    assertTrue(o1.intersects(o2));
  };

}
