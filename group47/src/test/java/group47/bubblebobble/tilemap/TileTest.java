package group47.bubblebobble.tilemap;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TileTest {
  
  private Tile tile;
  
  @Mock
  private BufferedImage img;

  @Before
  public void setUp() throws Exception {
    tile = new Tile(img, 1);
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void testGetImage() {
    assertEquals(tile.getImage(), img);
  }
  
  @Test
  public void testGetType() {
    assertEquals(tile.getType(), 1);
  }

}
