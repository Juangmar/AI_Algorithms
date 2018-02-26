package business;

public class SimpleMap extends Map {

	private CellStart start;
	
	private CellEnd end;
	
	public SimpleMap(int i, int j) {
		super(i,j);
		
		start = null;
		end = null;
		
		randomize();
	}

	private void randomize() {
		for (int i = 0; i < this.width(); i++) {
			for(int j = 0; j < this.height(); j++) {
				double r = Math.random();
				Cell c;
				if(r>=0.3) c = new CellGround(i, j);
				else c = new CellWall(i, j);
				this.setCell(c, i, j);
			}
		}
	}
	
	public CellStart getStart(){
		return this.start;
	}
	public CellEnd getEnd(){
		return this.end;
	}
	
	public void setStart(int x, int y) {
		start = new CellStart(x,y);
		this.setCell(start, x, y);
	}
	
	public void setEnd(int x, int y) {
		end = new CellEnd(x,y);
		this.setCell(end, x, y);
	}
	
}
