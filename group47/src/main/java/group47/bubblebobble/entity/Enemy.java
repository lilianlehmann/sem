package group47.bubblebobble.entity;
import group47.bubblebobble.tilemap.TileMap;

public class Enemy extends MapObject {
	
	protected boolean dead;
	protected boolean caught;
	
	protected double floatSpeed;

	public Enemy(TileMap tm) {
		super(tm);
	}
	
	public boolean isDead() {
		return dead;
	}
	
	public boolean isCaught() {
		return caught;
	}
	
	public void hit() {
		if(!caught)
			caught = true;
	}
	
	public void update() {}

	public void setCaught() {
		caught = true;
	}
	
}
