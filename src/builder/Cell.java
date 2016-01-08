package builder;

import java.util.ArrayList;

public class Cell {
	private boolean visited;
	private boolean wall;
	private boolean open;
//	private boolean[] walls;
	private int row, column;
	ArrayList<Cell> neighbours;
	
	public Cell(int row, int column) {
		visited = false;
//		walls = new boolean[3];
		this.row = row;
		this.column = column;
		neighbours = new ArrayList<Cell>();
	}
	
	/*
	public void setNorthWall(boolean isOpen) {
		walls[0] = isOpen;
	}
	
	public void setSouthWall(boolean isOpen) {
		walls[1] = isOpen;
	}
	public void setEastWall(boolean isOpen) {
		walls[2] = isOpen;
	}
	public void setWestWall(boolean isOpen) {
		walls[3] = isOpen;
	}*/
	
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}
	
	public void addNeighbour(Cell neighbours) {
		this.neighbours.add(neighbours);
	}
	
	@Override
	public String toString() {
		return "X: " + row + " Y: " + column;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null || getClass() != o) 
			return false;
		
		Cell cell = (Cell) o;
		
		return this.column == cell.column && this.row == cell.row || this == o; // om x, y koordinaterna stämmer: return true.
	}

}
