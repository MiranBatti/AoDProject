package builder;

import java.util.ArrayList;
import java.util.Random;

import model.Cell;
import util.ArrayStack;
/**
 * Maze generator, inspired by: http://weblog.jamisbuck.org/2010/12/27/maze-generation-recursive-backtracking 
 * and https://github.com/Solisol/labyrinth and https://en.wikipedia.org/wiki/Maze_generation_algorithm
 * 
 * @author Miran Batti
 * @author Fredrik Lindorf
 * 
 * @version 2016-01-08
 * 
 */
public class RecursiveBacktracker {

	private int width, height;
	private ArrayList<Cell> cells; 
	private ArrayList<Cell> paths;
	private ArrayList<Boolean> visited;
	private ArrayStack<Cell> stack;
	private Random rnd;

	/**
	 * Create maze when calling this constructor.
	 * 
	 * @param width
	 * @param height
	 */
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
		int startingCellIndex = 0; 
		
		Cell currentCell = cells.get(startingCellIndex); //TODO: pick random cell index instead
		stack.push(currentCell);
		
		while(!stack.isEmpty()) { //while there are unvisited cells...
			currentCell = stack.pop();
			visited.set(currentCell.getIndex(), true); //set current cell as visited
			currentCell.setConnectedCells(getNeighbours(currentCell));
			cells.set(currentCell.getIndex(), currentCell);
			
			if(currentCell.getConnectedCells().size() > 0) { //if current cell has neighbours
				ArrayList<Cell> neighbours = currentCell.getConnectedCells();
				Cell rndNeighbour = neighbours.get(rnd.nextInt(neighbours.size()));
				currentCell.getConnectedCells().remove(rndNeighbour);
				cells.set(currentCell.getIndex(), currentCell);
				
				Cell newCell = paths.get(currentCell.getIndex());
				newCell.getConnectedCells().add(rndNeighbour);
				setPaths(newCell);
				
				Cell rndNewCell = paths.get(rndNeighbour.getIndex());
				rndNewCell.getConnectedCells().add(newCell);
				setPaths(rndNewCell);
				
				stack.push(currentCell);
				stack.push(rndNeighbour); //pick a random neighbour
			}
		}
	}
	
	private ArrayList<Cell> getNeighbours(Cell cell) {
		ArrayList<Cell> neighbours = new ArrayList<Cell>();
		int index = cell.getIndex();
		int size = width * height;
		
		if(index >= width && !visited.get(index - width)) // North
			neighbours.add(cells.get(index - width));
		if(index < size - width && !visited.get(index + width)) // South
			neighbours.add(cells.get(index + width));
		if(index % width != 0 && !visited.get(index - 1)) // East
			neighbours.add(cells.get(index - 1));
		if(index % width != (width - 1) && !visited.get(index + 1)) // West // kontrollera arg..
			neighbours.add(cells.get(index + 1));
		
		return neighbours;
	}	
	
	private void setPaths(Cell newCell) {
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
	
	/**
	 * Check if cell is at the left edge of maze
	 * @param cell
	 * @return true if cell index is at the left edge, false otherwise
	 */
	public boolean isLeftEdge(Cell cell) {
        return (cell.getIndex() % width == 0);
    }

	/**
	 * Check if cell is connected to a cell to the right 
	 * 
	 * @param cell
	 * @return true if cell is connected to cell to the right, false otherwise
	 */
    public boolean hasRight(Cell cell) {
        int rightIndex = cell.getIndex() + 1;
        ArrayList<Cell> neighbours = cell.getConnectedCells();
        
        for (Cell neighbour : neighbours) { //foreach checks if cell is connected with cell to the right.
            if (neighbour.getIndex() == rightIndex) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if cell is connected with cell below
     * 
     * @param cell
     * @return true if cell is connected with cell below, false otherwise
     */
    public boolean hasDown(Cell cell) {
        int downIndex = cell.getIndex() + width;
        ArrayList<Cell> neighbours = cell.getConnectedCells();
        
        for (Cell neighbour : neighbours) { //foreach checks if cell is connected with cell below
            if (neighbour.getIndex() == downIndex) 
                return true;
        }
        return false;
    }
    
    /**
     * Returns an arraylist with cells
     * @return ArrayList path containing cells with connected cells
     */
    public ArrayList<Cell> getPaths() {
    	return paths;
    }
    
    /**
     * Returns the width of this maze
     * @return the width of this maze
     */
    public int getWidth() {
    	return width;
    }
    
    /**
     * Returns the height of this maze
     * @return the height of this maze
     */
    public int getHeight() {
    	return height;
    }
	
}
