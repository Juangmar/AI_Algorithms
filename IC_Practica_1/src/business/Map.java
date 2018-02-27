package business;

public class Map {

	protected Cell[][] map;
	protected int x;
	protected int y;
	
	public Map() {
		x = 1;
		y = 1;
		map[0][0] = null;
	}
	
	public Map(int dimX, int dimY) {
		this.x = dimX;
		this.y = dimY;
		map = new Cell[x][y];
		
		for (int i = 0; i < x; i++) {
			for(int j = 0; j < y; j++) {
				map[i][j] = null;
			}
		}
	}
	public void clearMap() {
		for (int i = 0; i < x; i++) {
			for(int j = 0; j < y; j++) {
				map[i][j] = null;
			}
		}
	}
	public void setCell(Cell c, int posX, int posY) {
		if((posX>=x)||(posY>=y)||(posX<0)||(posY<0)) {
			
		}else {
			map[posX][posY]=c;
		}
	}

	public Cell getCell(int posX, int posY) {
		if((posX>=x)||(posY>=y)||(posX<0)||(posY<0)) {
			return null;
		}else {
			return map[posX][posY];
		}
	}
	public int width() {
		return x;
	}
	public int height() {
		return y;
	}
	public String toString() {
		String result = "";
		String jump = System.lineSeparator();
		for (int i = 0; i < x; i++) {
			for(int j = 0; j < y; j++) {
				result += map[i][j].toString();
				if(j!=(y-1))result += " ";
			}
			result += jump;
		}
		return result;
	}
}