package group47.bubblebobble.entity;

import java.awt.Rectangle;
import group47.bubblebobble.tilemap.*;

/**
 * MapObject, SuperClass for all movable and interactable objects inside the gameworld
 */

public abstract class MapObject {
	
	//tileMap
	protected TileMap tileMap;
	protected int tileSize;
	
	//position and vector
	protected double x;
	protected double y;
	protected double dx;
	protected double dy;
	
	//sprite dimension
	protected int width;
	protected int height;
	
	//collision
	protected int cwidth;
	protected int cheight;
	protected int currRow;
	protected int currCol;
	protected double xdest;
	protected double ydest;
	protected double xtemp;
	protected double ytemp;
	protected boolean topLeft;
	protected boolean topRight;
	protected boolean bottomLeft;
	protected boolean bottomRight;
	
	//animation
	//protected Animation animation;
	protected int currentAction;
	protected int previousAction;
	protected boolean facingRight;
	
	//movement
	protected boolean left;
	protected boolean right; 
	protected boolean up;
	protected boolean down;
	protected boolean jumping;
	protected boolean falling;
	
	//movementAttributes
	protected double movSpeed;
	protected double maxSpeed;
	protected double stopSpeed;
	protected double fallSpeed;
	protected double maxFallSpeed;
	protected double jumpStart;
	protected double stopJumpSpeed;
	
	/**
	 * Constructor
	 * @param tm TileMap in which this object lives
	 */
	public MapObject(TileMap tm) {
		tileMap = tm;
		tileSize = tm.getTileSize();
	}
	
	/**
	 * Determines if this MapObject intersects with another object
	 * @param o the other MapObject
	 * @return true if they intersect
	 */
	public boolean intersects(MapObject o) {
		return this.getRectangle().intersects(o.getRectangle());
	}
	
	/**
	 * returns a Rectangle object describing the collisionbox of the MapObject
	 * @return Rectangle
	 */
	public Rectangle getRectangle() {
		return new Rectangle(
				(int) x - cwidth / 2,
				(int) y - cheight / 2,
				cwidth,
				cheight
			);
	}
	
	/**
	 * modifies xtemp and ytemp so that they don't intersect with the tileMap
	 */
	public void checkTileMapCollision() {
		//Determine current collum and row
		currCol = (int) x / tileSize;
		currRow = (int) y / tileSize;
		
		//Destination coordinates
		xdest = x + dx;
		ydest = y + dy;
		
		//Temporary coordinates
		xtemp = x;
		ytemp = y;
		
		//Check for collisions in the y direction
		calculateCorners(x, ydest);
		if(dy < 0) { //If we are moving upwards
			if(topLeft || topRight) { //And our topLeft or topRight corner is colliding with a 'BLOCKED' tile
				dy = 0; //Our vertical movement vector becomes 0
				ytemp = currRow * tileSize + cheight / 2; //Our new position becomes just below the tile above we are colliding with
			} else { //If we are not colliding
				ytemp += dy; //Our y vector is added to our temporary y position
			}
		}
		else if(dy > 0) { //If we are moving downwards
			if(bottomLeft || bottomRight) { //And our bottomLeft or bottomRight corner is colliding with a 'BLOCKED' tile
				dy = 0; //Our horizontal movement vector becomes 0
				falling = false; //We are standing on a solid tile, so we are not falling
				ytemp = (currRow + 1) * tileSize - cheight / 2; //Our new position becomes just on top of the tile we are colliding with
			} else { //If we are not colliding
				ytemp += dy; //Our y vector is added to our temporary y position
			}
		}
		
		//Check for collisions in the x direction
		calculateCorners(xdest, y);
		if(dx < 0) { //If we are moving to the left
			if(topLeft || bottomLeft) { //And we are colliding on the left
				dx = 0; //Our horizontal movement vector becomes 0
				xtemp = currCol * tileSize + cwidth / 2; //Our new position is set to just to the right of the blocking tile
			} else { //If we are not colliding
				xtemp += dx; //Apply the horizontal movement vector
			}
		}
		else if(dx > 0) { //If we are moving to the right
			if(topRight || bottomRight) { //And we are colliding on the right
				dx = 0; //Our horizontal movement vector becomes 0
				xtemp = (currCol + 1) * tileSize - cwidth / 2; //Our new position is set just to the left of the blocking tile
			} else { //If we are not colliding
				xtemp += dx; //Apply the horizontal movement vector;
			}
		}
		
		//Check if we are falling
		if(!falling) {
			calculateCorners(x, ydest +1); //See if we move 1 pixel downwards if we are colliding
			if(!bottomLeft && ! bottomRight) { //If we do not collide we are falling
				falling = true;
			}
		}
	}
	
	/**
	 * calculates if any of the corners of the object intersect with tiles and sets the topLeft, topRight, bottomLeft and bottomRight boolean accordingly
	 * @param x xposition to check
	 * @param y yposition to check
	 */
	public void calculateCorners(double x, double y) {
		int leftTile = (int) (x - cwidth / 2) / tileSize;
		int rightTile = (int) (x + cwidth / 2  - 1) / tileSize;
		int topTile = (int) (y - cheight / 2) / tileSize;
		int bottomTile = (int) (y + cwidth /2 - 1) / tileSize;
		
		int tl = tileMap.getType(topTile, leftTile);
		int tr = tileMap.getType(topTile, rightTile);
		int bl = tileMap.getType(bottomTile, leftTile);
		int br = tileMap.getType(bottomTile, rightTile);
		
		//Flags for whether we are colliding
		topLeft		= tl == Tile.BLOCKED;
		topRight	= tr == Tile.BLOCKED;
		bottomLeft	= bl == Tile.BLOCKED;
		bottomRight	= br == Tile.BLOCKED;
	}
	
	
	//Get functions
	public double getx() { return x; }
	public double gety() { return y; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	public int getCWidth() { return cwidth; }
	public int getCHeight() { return cheight; }
	
	
	//Set functions
	public void setPosition(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public void setVector(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}
	
	public void setLeft(boolean b) { left = b; }
	public void setRight(boolean b) { right = b; }
	public void setUp(boolean b) { up = b; }
	public void setDown(boolean b) { down = b; }
}
