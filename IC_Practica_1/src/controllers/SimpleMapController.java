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
		
		final Map<Cell, Integer> g = new HashMap<Cell, Integer>();
		g.put(board.getStart(), 0);
		
		final Map<Cell, Integer> f = new HashMap<Cell, Integer>();
		
		boolean goalAchived = false;
		boolean fail = false;
		
		while(!goalAchived && (!fail)) {
			if(open.isEmpty()) fail = true;
			else if ((closed.get(closed.size()-1))==board.getEnd()) goalAchived=true; 
			else {
				
				
				
			}
		}
		
		if(goalAchived) {
			return reconstructedPath(board, pathFollowed);
		}
		else {
			return new ArrayList<Cell>();
		}
	}
	
	private static ArrayList<Cell> reconstructedPath(SimpleMap board, ArrayList<Cell> pathFollowed){
		ArrayList<Cell> result = new ArrayList<Cell>();
		
		
		return result;
	}
	
	
	
	/****************
	 * https://github.com/phishman3579/java-algorithms-implementation/blob/master/src/com/jwetherell/algorithms/graph/AStar.java
	 * 


        // Estimated total cost from start to goal through y.
        final Map<Graph.Vertex<T>,Integer> fScore = new HashMap<Graph.Vertex<T>,Integer>();
        for (Graph.Vertex<T> v : graph.getVertices())
            fScore.put(v, Integer.MAX_VALUE);
        fScore.put(start, heuristicCostEstimate(start,goal));

        final Comparator<Graph.Vertex<T>> comparator = new Comparator<Graph.Vertex<T>>() {
            /**
             * {@inheritDoc}
             *
            @Override
            public int compare(Vertex<T> o1, Vertex<T> o2) {
                if (fScore.get(o1) < fScore.get(o2))
                    return -1;
                if (fScore.get(o2) < fScore.get(o1))
                    return 1;
                return 0;
            }
        };

        while (!openSet.isEmpty()) {
            final Graph.Vertex<T> current = openSet.get(0);
            if (current.equals(goal))
                return reconstructPath(cameFrom, goal);

            openSet.remove(0);
            closedSet.add(current);
            for (Graph.Edge<T> edge : current.getEdges()) {
                final Graph.Vertex<T> neighbor = edge.getToVertex();
                if (closedSet.contains(neighbor))
                    continue; // Ignore the neighbor which is already evaluated.

                final int tenativeGScore = gScore.get(current) + distanceBetween(current,neighbor); // length of this path.
                if (!openSet.contains(neighbor))
                    openSet.add(neighbor); // Discover a new node
                else if (tenativeGScore >= gScore.get(neighbor))
                    continue;

                // This path is the best until now. Record it!
                cameFrom.put(neighbor, current);
                gScore.put(neighbor, tenativeGScore);
                final int estimatedFScore = gScore.get(neighbor) + heuristicCostEstimate(neighbor, goal);
                fScore.put(neighbor, estimatedFScore);

                // fScore has changed, re-sort the list
                Collections.sort(openSet,comparator);
            }
        }

        return null;
    }

    /**
     * Default distance is the edge cost. If there is no edge between the start and next then
     * it returns Integer.MAX_VALUE;
     *
    protected int distanceBetween(Graph.Vertex<T> start, Graph.Vertex<T> next) {
        for (Edge<T> e : start.getEdges()) {
            if (e.getToVertex().equals(next))
                return e.getCost();
        }
        return Integer.MAX_VALUE;
    }

    /**
     * Default heuristic: cost to each vertex is 1.
     *
    @SuppressWarnings("unused") 
    protected int heuristicCostEstimate(Graph.Vertex<T> start, Graph.Vertex<T> goal) {
        return 1;
    }

    private List<Graph.Edge<T>> reconstructPath(Map<Graph.Vertex<T>,Graph.Vertex<T>> cameFrom, Graph.Vertex<T> current) {
        final List<Graph.Edge<T>> totalPath = new ArrayList<Graph.Edge<T>>();

        while (current != null) {
            final Graph.Vertex<T> previous = current;
            current = cameFrom.get(current);
            if (current != null) {
                final Graph.Edge<T> edge = current.getEdge(previous);
                totalPath.add(edge);
            }
        }
        Collections.reverse(totalPath);
        return totalPath;
	}
	 
	 
	 
	 * 
	 * 
	 */
}