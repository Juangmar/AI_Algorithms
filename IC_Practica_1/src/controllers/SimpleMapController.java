package controllers;

import java.util.ArrayList;
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
		
		if(board.getStart()==null || board.getEnd()==null) return null;
		
		
		ArrayList<Cell> open = new ArrayList<Cell>(); 
		open.add(board.getStart());
		
		ArrayList<Cell> closed = board.getObstacles();
		
		Map<Cell, Cell> cameFrom = new HashMap<Cell, Cell>();
		
		final Map<Cell, Double> g = new HashMap<Cell, Double>();
		g.put(board.getStart(), 0.00);
		
		final Map<Cell, Double> f = new HashMap<Cell, Double>();
		f.put(board.getStart(), distanceTo(board.getStart(),board.getEnd()));
		
		
		while(!open.isEmpty()) {
			final Cell current = getMinimum(open, f);
			if ((current)==board.getEnd()) return reconstructedPath(board.getEnd(), cameFrom);
			else {
				open.remove(current);
				closed.add(current);
				
				for (Cell ady : board.getAdyacentes(current)) {
					
					if (closed.contains(ady)) continue;
					
					double tempG = g.get(current) + distanceTo(current,ady);
					
					if(!open.contains(ady)) open.add(ady);
					
					else if (tempG >= g.get(ady)) continue;
					
					cameFrom.put(ady, current);
					
					g.put(ady, tempG);
					
					double estimatedH = distanceTo(ady, board.getEnd());
					
					double estimatedF = g.get(ady) + estimatedH;
					f.put(ady, estimatedF);
					
					
				}
			}
		}
		
		return null;
	}
	
public static ArrayList<Cell> aStar(SimpleMap board, Cell start, Cell finish) {
		
		if(start==null || finish==null) return null;
		
		
		ArrayList<Cell> open = new ArrayList<Cell>(); 
		open.add(start);
		
		ArrayList<Cell> closed = board.getObstacles();
		
		Map<Cell, Cell> cameFrom = new HashMap<Cell, Cell>();
		
		final Map<Cell, Double> g = new HashMap<Cell, Double>();
		g.put(start, 0.00);
		
		final Map<Cell, Double> f = new HashMap<Cell, Double>();
		f.put(start, distanceTo(start,finish));
		
		
		while(!open.isEmpty()) {
			final Cell current = getMinimum(open, f);
			if ((current)==finish) return reconstructedPath(finish, cameFrom);
			else {
				open.remove(current);
				closed.add(current);
				
				for (Cell ady : board.getAdyacentes(current)) {
					
					if (closed.contains(ady)) continue;
					
					double tempG = g.get(current) + distanceTo(current,ady);
					
					if(!open.contains(ady)) open.add(ady);
					
					else if (tempG >= g.get(ady)) continue;
					
					cameFrom.put(ady, current);
					
					g.put(ady, tempG);
					
					double estimatedH = distanceTo(ady, finish);
					
					double estimatedF = g.get(ady) + estimatedH;
					f.put(ady, estimatedF);
					
					
				}
			}
		}
		
		return null;
	}

	protected static Cell getMinimum(ArrayList<Cell> open, Map<Cell, Double> f ) {
		double min = f.get(open.get(0));
		Cell mincell = open.get(0);
		for(Cell e : open) {
			if (f.get(e) < min) {
				mincell = e;
				min = f.get(e);
			}
		}		
		return mincell;
	}
	
	protected static double distanceTo(Cell or, Cell dest) {
		double dist = Math.hypot(dest.getX()-or.getX(), dest.getY()-or.getY());
		
		return dist;
	}
	
	protected static ArrayList<Cell> reconstructedPath(Cell current, Map<Cell, Cell> cameFrom){
		ArrayList<Cell> result = new ArrayList<Cell>();
		
        while (current != null) {
            Cell previous = cameFrom.get(current);
            result.add(current);
            current = previous;
        }	
		return result;
	}
}