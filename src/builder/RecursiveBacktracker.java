package builder;

import java.util.ArrayList;
import java.util.Random;

import model.Cell;
import util.ArrayStack;
/**
 * 
 * @author Miran Batti
 * @author Fredrik Lindorf
 *
 */
public class RecursiveBacktracker {

	private int width, height;
	private ArrayList<Cell> cells; 
	private ArrayList<Cell> paths;
	private ArrayList<Boolean> visited;
	private ArrayStack<Cell> stack;
	private Random rnd;

	
	public RecursiveBacktracker(int width, int height) {
		this.width = width;
		this.height = height;
		initCells(width * height);
		initPathCells(width * height);
		initVisited(width * height);
		generateMaze();
	}
	
	private void generateMaze() {
		rnd = new Random();		
		stack = new ArrayStack<Cell>();		
		
		Cell cell = getCellIndex(0);
		stack.push(cell);
		
		while(!stack.isEmpty()) {
			cell = stack.pop();
			visited.set(cell.getIndex(), true);
			cell.setConnectedCells(getNeighbours(cell));
			updateCell(cell);
			
			if(cell.getConnectedCells().size() > 0) {
				ArrayList<Cell> neighbours = cell.getConnectedCells();
				Cell rndNeighbour = neighbours.get(rnd.nextInt(neighbours.size()));
				cell.removeFromConnectedCells(rndNeighbour);
				updateCell(cell);
				
				Cell newCell = getPathsIndex(cell.getIndex());
				newCell.addToConnectedCells(rndNeighbour);
				updatePaths(newCell);
				
				Cell rndNewCell = getPathsIndex(rndNeighbour.getIndex());
				rndNewCell.addToConnectedCells(newCell);
				updatePaths(rndNewCell);
				
				stack.push(cell);
				stack.push(rndNeighbour);
			}
		}
	}
	
	private ArrayList<Cell> getNeighbours(Cell cell) {
		ArrayList<Cell> neighbours = new ArrayList<Cell>();
		int index = cell.getIndex();
		int size = width * height;
		
		if(index >= width && !visited.get(index - width)) // North
			neighbours.add(getCellIndex(index - width));
		if(index < size - width && !visited.get(index + width)) // South
			neighbours.add(getCellIndex(index + width));
		if(index % width != 0 && !visited.get(index - 1)) // East
			neighbours.add(getCellIndex(index - 1));
		if(index % width != (width - 1) && !visited.get(index + 1)) // West // kontrollera arg..
			neighbours.add(getCellIndex(index + 1));
		
		return neighbours;
	}
	
	public Cell getCellIndex(int index) {
        return cells.get(index);
    }
	
	private Cell getPathsIndex(int index) {
        return paths.get(index);
    }
	
	private void updateCell(Cell newCell) {
		cells.set(newCell.getIndex(), newCell);
	}
	
	private void updatePaths(Cell newCell) {
		paths.set(newCell.getIndex(), newCell);
	}
	
	private void initCells(int nbrOfCells) {
		cells = new ArrayList<Cell>();
		
        for (int i = 0; i < nbrOfCells; i++) 
            cells.add(new Cell(i));
	}
	
	private void initPathCells(int nbrOfCells) {
		paths = new ArrayList<Cell>();
		
        for (int i = 0; i < nbrOfCells; i++) 
            paths.add(new Cell(i));
    }
	
	private void initVisited(int nbrOfCells) {
		visited = new ArrayList<Boolean>();
		
        for (int i = 0; i < nbrOfCells; i++) 
            visited.add(false);
	}
	
	public boolean isLeftEdge(Cell cell) {
        return (cell.getIndex() % width == 0);
    }

    public boolean hasRight(Cell cell) {
        int rightIndex = cell.getIndex() + 1;
        return cellIsConnectedToIndex(cell, rightIndex);
    }

    public boolean hasDown(Cell cell) {
        int downIndex = cell.getIndex() + width;
        return cellIsConnectedToIndex(cell, downIndex);
    }

    private boolean cellIsConnectedToIndex(Cell cell, int matchIndex) {
        ArrayList<Cell> neighbours = cell.getConnectedCells();
        for (Cell neighbour : neighbours) {
            if (neighbour.getIndex() == matchIndex) {
                return true;
            }
        }
        return false;
    }
    
    public ArrayList<Cell> getPaths() {
    	return paths;
    }
    
    public Cell getCellInAvailablePaths(int index) {
    	return paths.get(index);
    }
    
    public int getWidth() {
    	return width;
    }
    
    public int getHeight() {
    	return height;
    }
	
}
