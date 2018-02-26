package business;

public abstract class Cell {

	@SuppressWarnings("unused")
	private boolean walkable;
	private int x;
	private int y;

	public Cell(int i, int j) {
		this.x = i;
		this.y = j;
	}
	
	public abstract boolean isWalkable();

	public void setWalkable(boolean walkable) {
		this.walkable = walkable;
	}
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}
	public void setX(int i) {
		x = i;
	}
	public void setY(int i) {
		y = i;
	}

}
