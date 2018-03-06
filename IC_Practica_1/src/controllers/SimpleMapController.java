package controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import business.Cell;
import business.SimpleMap;

/**
 * @author Juan Gómez-Martinho González
 *
 */
public class SimpleMapController {

	
	/**
	 * 
	 * This method takes a SimpleMap object, checks if it has a
	 * beginning and an end and executes the A* search algorithm to
	 * return the path.
	 * 
	 * Inspired by:	https://github.com/phishman3579/java-algorithms-implementation/blob/master/src/com/jwetherell/algorithms/graph/AStar.java
	 * 
	 * @param board Object with the map to use 
	 * @return if board has no start, end or path possible, 
	 * or arraylist with the cells that forms the path between the two points.
	 */
	public static ArrayList<Cell> aStar(SimpleMap board) {
		//ArrayList<Cell> result = new ArrayList<Cell>();
		
		ArrayList<Cell> open = new ArrayList<Cell>();
			open.add(board.getStart());
		ArrayList<Cell> closed = board.getObstacles();
		
		ArrayList<Cell> pathFollowed = new ArrayList<Cell>();
		
		final Map<Cell, Double> g = new HashMap<Cell, Double>();
		g.put(board.getStart(), 0.00);
		
		final Map<Cell, Double> f = new HashMap<Cell, Double>();
		
		boolean goalAchived = false;
		boolean fail = false;
		
		final Comparator<Cell> comparator = new Comparator<Cell>() {
	        /**
	         * {@inheritDoc}
	         */
	        @Override
	        public int compare(Cell o1, Cell o2) {
	            if (f.get(o1) < f.get(o2))
	                return -1;
	            if (f.get(o2) < f.get(o1))
	                return 1;
	            return 0;
	        }
		};
		
		
		while(!goalAchived && (!fail)) {
			final Cell current = open.get(0);
			if(open.isEmpty()) fail = true;
			else if ((current)==board.getEnd()) goalAchived=true; 
			else {
				open.remove(0);
				closed.add(current);
				
				for (Cell ady : board.getAdyacentes(current)) {
					if (closed.contains(ady)) continue;
					
					final double tenativeG = g.get(current) + distanceTo(current,ady);
					
					if(!open.contains(ady)) open.add(ady);
					else if (tenativeG >= g.get(ady)) continue;
					
					pathFollowed.add(ady);
					g.put(ady, tenativeG);
					final double estimatedF = g.get(ady) + distanceTo(ady,board.getEnd());
					f.put(ady, estimatedF);
					
					Collections.sort(open, comparator);
					
					
				}
			}
		}
		
		if(goalAchived) {
			return reconstructedPath(board, pathFollowed);
		}
		else {
			return new ArrayList<Cell>();
		}
	}
	

	
	private static double distanceTo(Cell or, Cell dest) {
		double dist = Math.hypot(dest.getX()-or.getX(), dest.getY()-or.getY());
		
		return dist;
	}
	
	private static ArrayList<Cell> reconstructedPath(SimpleMap board, ArrayList<Cell> pathFollowed){
		ArrayList<Cell> result = new ArrayList<Cell>();
		
		
		return result;
	}
}