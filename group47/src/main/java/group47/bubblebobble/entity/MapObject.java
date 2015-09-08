package group47.bubblebobble.entity;

import group47.bubblebobble.tilemap.TileMap;

import java.awt.Rectangle;

/*
 * 16 minuten in video tutorial 3
 * compileert niet omdat de TileMap mist
 */

public abstract class MapObject {
	
	//
	protected TileMap tileMap;
	protected int tileSize;
	
	protected double xmap;
	protected double ymap;
	
	//position and vector
	protected double x;
	protected double y;
	protected double dx;
	protected double dy;
	
	//dimensons
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
	
	constructor
	public MapObject(TileMap tm) {
		tileMap = tm;
		tileSize = tm.getTileSize();
	}
	
	public boolean intersects(MapObject o) {
		return this.getRectangel().intersects(o.getRectangel());
	}
	
	public Rectangle getRectangel() {
		return new Rectangle(
				(int) x - cwidth,
				(int) y - cheight,
				cwidth,
				cheight
			);
	}
	
	public void checkTileMapCollision() {
		currCol = (int) x / tileSize;
		currRow = (int) y / tileSize;
		
		xdest = x + dx;
		ydest = y + dy;
		
		xtemp = x;
		ytemp = y;
		
		calculateCorners(x, ydest);
	}
	
	public void calculateCorners(double x, double y) {
		
		int leftTile = (int) (x - cwidth / 2) / tileSize;
		int rightTile = (int) (x + cwidth / 2  - 1) / tileSize;
		int topTile = (int) (y - cheight / 2) / tileSize;
		int bottomTile = (int) (y + cwidth /2 - 1) / tileSize;
		
	}
	
}
