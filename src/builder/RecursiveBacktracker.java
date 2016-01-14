package builder;

import java.util.ArrayList;
import java.util.Random;

import model.Cell;
import util.ArrayStack;

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
			cell.setOutNodes(getNeighbours(cell));
			updateCell(cell);
			
			if(cell.getCellOut().size() > 0) {
				ArrayList<Cell> neighbours = cell.getCellOut();
				Cell rndNeighbour = neighbours.get(rnd.nextInt(neighbours.size()));
				cell.removeFromNodes(rndNeighbour);
				updateCell(cell);
				
				Cell newCell = getCell2Index(cell.getIndex());
				newCell.addToNodes(rndNeighbour);
				updateCell2(newCell);
				
				Cell rndNewCell = getCell2Index(rndNeighbour.getIndex());
				rndNewCell.addToNodes(newCell);
				updateCell2(rndNewCell);
				
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
	
	private Cell getCell2Index(int index) {
        return paths.get(index);
    }
	
	private void updateCell(Cell newCell) {
		cells.set(newCell.getIndex(), newCell);
	}
	
	private void updateCell2(Cell newCell) {
		paths.set(newCell.getIndex(), newCell);
	}
	
	private void initCells(int nbrOfCells) {
		cells = new ArrayList<Cell>();
        for (int i = 0; i < nbrOfCells; i++) {
            cells.add(new Cell(i));
        }
	}
	
	private void initPathCells(int nbrOfCells) {
		paths = new ArrayList<Cell>();
        for (int i = 0; i < nbrOfCells; i++) {
            paths.add(new Cell(i));
        }
	}
	
	private void initVisited(int nbrOfCells) {
		visited = new ArrayList<Boolean>();
        for (int i = 0; i < nbrOfCells; i++) {
            visited.add(false);
        }
	}
	
	public void printMaze() {
        System.out.print(" ");
        for (int i = 0; i < width; i++) {
            System.out.print("_ ");
        }
        System.out.println("");
        for (int i = 0; i < (width * height); i++) {
            Cell currentCell = paths.get(i);
            if (isLeftEdge(currentCell)) {
                System.out.print("|");
            }
            System.out.print(hasDown(currentCell) ? " " : "_");
            System.out.print(hasRight(currentCell) ? (!isRightEdge(currentCell) ? " " : "") : "|");
            if(i % width == width - 1) {
                System.out.println("");
            }
        }
	}
	
	public boolean isLeftEdge(Cell cell) {
        return (cell.getIndex() % width == 0);
    }

    private boolean isRightEdge(Cell cell) {
        return (cell.getIndex() % width == width - 1);
    }

    /*
    private boolean hasUp(Cell cell) {
        int upIndex = cell.getIndex() - width;
        return hasPathToIndex(cell, upIndex);
    }

    private boolean hasLeft(Cell cell) {
        int leftIndex = cell.getIndex() - 1;
        return hasPathToIndex(cell, leftIndex);
    }*/

    public boolean hasRight(Cell cell) {
        int rightIndex = cell.getIndex() + 1;
        return hasPathToIndex(cell, rightIndex);
    }

    public boolean hasDown(Cell cell) {
        int downIndex = cell.getIndex() + width;
        return hasPathToIndex(cell, downIndex);
    }

    private boolean hasPathToIndex(Cell cell, int matchIndex) {
        ArrayList<Cell> neighbours = cell.getCellOut();
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
    
    public Cell getCellAtPaths(int index) {
    	return paths.get(index);
    }
    
    public int getWidth() {
    	return width;
    }
    
    public int getHeight() {
    	return height;
    }
	
}
