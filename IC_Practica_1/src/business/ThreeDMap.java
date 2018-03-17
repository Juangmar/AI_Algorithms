package business;

import java.util.ArrayList;

public class ThreeDMap extends SimpleMap {

	private ArrayList<CellHeightWayPoint> wayPoints;
	

	public ThreeDMap(int i, int j) {
		super(i, j);
		wayPoints = new ArrayList<CellHeightWayPoint>();
	}
	
	public ArrayList<CellHeightWayPoint> getWayPoints() {
		return wayPoints;
	}

	public void setWayPoints(ArrayList<CellHeightWayPoint> wayPoints) {
		this.wayPoints = wayPoints;
	}
	
	public int getIndexOfPoint(CellHeightWayPoint e) {
		return wayPoints.indexOf(e);
	}
	
	public boolean addWayPoint(int i, int j) {
		if(i<0 || j<0 || i>this.x || j>this.y) return false;
		else {
			if((this.map[i][j] instanceof CellHeightWayPoint) || !this.map[i][j].isWalkable()) return false;
			else {
				CellHeightWayPoint c = new CellHeightWayPoint(i,j,((CellHeight) this.map[i][j]).getHeight());
				boolean posible = wayPoints.add(c);
				if(!posible) return false;
				else {
					this.setCell(c, i, j);
					return true;
				}
			}
				
		}
	}
	
	@Override
	public void clearMap() {
		start = null;
		end = null;
		
		for (int i = 0; i < this.width(); i++) {
			for(int j = 0; j < this.height(); j++) {
				CellHeight c = new CellHeight(i, j, 0);
				this.setCell(c, i, j);
			}
		}
		this.wayPoints.clear();
	}
	
	public boolean deleteWayPoint(int i, int j) {
		if(i<0 || j<0 || i>this.x || j>this.y) return false;
		Cell c = this.map[i][j];
		if(c instanceof CellHeightWayPoint) {
			boolean posible = wayPoints.remove(c);
			if(!posible) return false;
			else {
				this.setCell(new CellHeight(i,j, ((CellHeight) this.map[i][j]).getHeight()), i, j);
				return true;
			}	
		}else {
			return false;
		}
	}
	
	@Override
	public void randomize() {
				
		for (int i = 0; i < this.width(); i++) {
			for(int j = 0; j < this.height(); j++) {
				int r = (int) (Math.random()*100);
				CellHeight c = new CellHeight(i, j, r);
				this.setCell(c, i, j);
			}
		}
	}

}
