package controllers;

import java.util.ArrayList;

import business.Cell;
import business.CellWayPoint;
import business.WayPointMap;

/**
 * @author Juan Gómez-Martinho González
 *
 */
public class WayMapController extends SimpleMapController {


	public static ArrayList<Cell> stepAStar(WayPointMap board) {
		ArrayList<CellWayPoint> list = board.getWayPoints();
		ArrayList<Cell> path = new ArrayList<Cell>();
		int nextIndex = 1;
		if (list.size()<2) return null;
		
		else {	
			CellWayPoint second = list.get(0);
			CellWayPoint first = null;
			
			while (nextIndex < list.size()) {
				first = second;
				second = list.get(nextIndex);
				ArrayList<Cell> temp = new ArrayList<Cell>();
				temp = SimpleMapController.aStar(board, first, second);
				if(temp == null) return null;
				else {
					path.addAll(temp);
					nextIndex++;
				}
				
			}
			return path;
		}
	}
}