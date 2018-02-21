package business;

public class Map {

	private Cell[][] map;
	private int x;
	private int y;
	
	public Map() {
		x = 1;
		y = 1;
		map[0][0] = null;
	}
	
	public Map(int dimX, int dimY) {
		this.x = dimX;
		this.y = dimY;
		
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
		if((x>=posX)||(y>=posY)||(posX<0)||(posY<0)) {
			
		}else {
			map[posX][posY]=c;
		}
	}

	public Cell getCell(int posX, int posY) {
		if((x>=posX)||(y>=posY)||(posX<0)||(posY<0)) {
			return null;
		}else {
			return map[posX][posY];
		}
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