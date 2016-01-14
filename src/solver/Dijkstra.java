package solver;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;

import builder.RecursiveBacktracker;
import gui.Board;
import model.Cell;
import model.Path;

public class Dijkstra {
	private RecursiveBacktracker maze;
	private LinkedList<PathFinderListener> listenerList;
	
	public Dijkstra(RecursiveBacktracker maze) {
		this.maze = maze;
		listenerList = new LinkedList<PathFinderListener>();
	}
	
	public void solve() {
		HashSet<Cell> visited = new HashSet<Cell>();
		PriorityQueue<Path> availablePaths = new PriorityQueue<Path>();
		Path path = new Path();
		ArrayList<Cell> p = maze.getPaths();
		int size = maze.getHeight() * maze.getWidth();
		
		int start = 0;
		int end = size - 1;
		Cell currentCell = maze.getPaths().get(start);
		path.add(currentCell);
		availablePaths.add(path);
		
		while(!availablePaths.isEmpty()) {
			Path currentPath = availablePaths.poll();
			Cell endCell = currentPath.getNodes().getLast();
			
			if(visited.contains(endCell))
				continue;
			visited.add(endCell);
			raiseTakenCell(endCell.getIndex());
			
			if(endCell.getIndex() == end) {
				raiseEndCellFound(currentPath.getNodes());
				return;
			}
			
			for (Cell neighbours : endCell.getCellOut()) {
				if(!visited.contains(neighbours)) {
					Path tmp = currentPath.clone();
					tmp.add(maze.getCellAtPaths(neighbours.getIndex()));
					availablePaths.add(tmp);
					raiseCellGetsPickeable(neighbours.getIndex());
				}
			}
		}
	}
	
	private void raiseTakenCell(int index) {
		for (PathFinderListener pathFinderListener : listenerList) {
			pathFinderListener.takenCell(index);
		}
	}
	
	private void raiseEndCellFound(LinkedList<Cell> path) {
		for (PathFinderListener pathFinderListener : listenerList) {
			pathFinderListener.endCellFound(path);
		}
	}
	
	private void raiseCellGetsPickeable(int index) {
        for (PathFinderListener pathFinderListener : listenerList) {
			pathFinderListener.cellGetsPickable(index);
		}
    }
	
	public void addListener(PathFinderListener listener) {
        listenerList.add(listener);
    }
	
}
