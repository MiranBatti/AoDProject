package solver;

import java.util.HashSet;
import java.util.LinkedList;
import builder.RecursiveBacktracker;
import model.Cell;
import model.Path;
import util.MyHeapPriorityQueue;

/**
 * Maze solver using Dijkstra's algorithm
 * 
 * Inspired by: https://github.com/Solisol/labyrinth and https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
 * 
 * @author Miran Batti
 * @author Fredrik Lindorf
 * 
 * @version 2016-01-10
 *
 */
public class Dijkstra {
	private RecursiveBacktracker maze;
	private LinkedList<DijkstraListener> listenerList;
	
	/**
	 * Dijkstra's Algorithm, finds shortest path to the end of given maze
	 * 
	 * @param maze
	 */
	public Dijkstra(RecursiveBacktracker maze) {
		this.maze = maze;
		listenerList = new LinkedList<DijkstraListener>();
	}
	
	/**
	 * Start solving maze
	 */
	public void solve() {
		HashSet<Cell> visited = new HashSet<Cell>();
		MyHeapPriorityQueue<Path> availablePaths = new MyHeapPriorityQueue<Path>();
		Path path = new Path();
		int area = maze.getHeight() * maze.getWidth();
		int start = 0;
		int end = area - 1;
		Cell currentCell = maze.getPaths().get(start);
		
		path.add(currentCell);
		availablePaths.enqueue(path);
		
		while(true) {
			Path currentPath = availablePaths.dequeue();
			currentCell = currentPath.getNodes().getLast();
			
			if(currentCell.getIndex() == end) { //When the end of the maze has been found
				mazeSolved(currentPath.getNodes());
				break;
			}
			
			visited.add(currentCell);
			raiseUsedCell(currentCell.getIndex());
			
			for (Cell neighbours : currentCell.getConnectedCells()) {
				if(!visited.contains(neighbours)) {
					Path tmp = currentPath.clone(); 
					tmp.add(maze.getPaths().get(neighbours.getIndex()));
					availablePaths.enqueue(tmp);
					raisePickedCell(neighbours.getIndex());
				}
			}
		}
	}
	
	private void raiseUsedCell(int index) {
		for (DijkstraListener pathFinderListener : listenerList) {
			pathFinderListener.usedCell(index);
		}
	}
	
	private void mazeSolved(LinkedList<Cell> path) {
		for (DijkstraListener pathFinderListener : listenerList) {
			pathFinderListener.mazeSolved(path);
		}
	}
	
	private void raisePickedCell(int index) {
        for (DijkstraListener pathFinderListener : listenerList) {
			pathFinderListener.cellGetsPicked(index);
		}
    }
	
	/**
	 * Add object of the type of PathFinderListener
	 * @param listener
	 */
	public void addListener(DijkstraListener listener) {
        listenerList.add(listener);
    }
	
}
