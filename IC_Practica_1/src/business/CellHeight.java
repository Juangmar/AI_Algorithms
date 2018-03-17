package business;

public class CellHeight extends CellGround {

	private int z;
	
	public CellHeight(int i, int j) {
		super(i, j);
		this.setHeight(0);
	}
	
	public CellHeight(int i, int j, int t) {
		super(i,j);
		this.setHeight(t);
	}

	public int getHeight() {
		return z;
	}

	public void setHeight(int z) {
		this.z = z;
	}

}
