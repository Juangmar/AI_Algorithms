package business;

import java.util.ArrayList;

public class WayPointMap extends SimpleMap {

	private ArrayList<CellWayPoint> wayPoints;
	

	public WayPointMap(int i, int j) {
		super(i, j);
		wayPoints = new ArrayList<CellWayPoint>();
	}
	
	public ArrayList<CellWayPoint> getWayPoints() {
		return wayPoints;
	}

	public void setWayPoints(ArrayList<CellWayPoint> wayPoints) {
		this.wayPoints = wayPoints;
	}
	
	public int getIndexOfPoint(CellWayPoint e) {
		return wayPoints.indexOf(e);
	}
	
	public boolean addWayPoint(int i, int j) {
		if(i<0 || j<0 || i>this.x || j>this.y) return false;
		else {
			if((this.map[i][j] instanceof CellWayPoint) || !this.map[i][j].isWalkable()) return false;
			else {
				CellWayPoint c = new CellWayPoint(i,j);
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
				CellGround c = new CellGround(i, j);
				this.setCell(c, i, j);
			}
		}
		this.wayPoints.clear();
	}
	
	public boolean deleteWayPoint(int i, int j) {
		if(i<0 || j<0 || i>this.x || j>this.y) return false;
		Cell c = this.map[i][j];
		if(c instanceof CellWayPoint) {
			boolean posible = wayPoints.remove(c);
			if(!posible) return false;
			else {
				this.setCell(new CellGround(i,j), i, j);
				return true;
			}	
		}else {
			return false;
		}
	}

}
