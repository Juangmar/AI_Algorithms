package business;

public class SimpleMap extends Map {

	public SimpleMap(int i, int j) {
		super(i,j);
		randomize();
	}

	private void randomize() {
		for (int i = 0; i < this.width(); i++) {
			for(int j = 0; j < this.height(); j++) {
				double r = Math.random();
				Cell c;
				if(r>=0.3) c = new CellGround();
				else c = new CellWall();
				this.setCell(c, i, j);
			}
		}
	}
}
