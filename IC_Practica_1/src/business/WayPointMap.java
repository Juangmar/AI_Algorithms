package business;

import java.util.ArrayList;

public class WayPointMap extends SimpleMap {

	private ArrayList<CellWayPoint> wayPoints;
	
	public WayPointMap(int i, int j) {
		super(i, j);
		wayPoints = new ArrayList<CellWayPoint>();
	}
	
	public void addWayPoint(int i, int j) {
		CellWayPoint c = new CellWayPoint(i,j);
		this.setCell(c, i, j);
		wayPoints.add(c);
	}

}
