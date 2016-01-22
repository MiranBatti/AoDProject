package model;

import java.util.ArrayList;

public class Cell {	 
	private ArrayList<Cell> connectedCell = new ArrayList<Cell>();
	private int index;

	public Cell(int index, ArrayList<Cell> connectedCell) {	 
		this.index = index;
		this.connectedCell = connectedCell;	    
	}
	
	public Cell(int index) {
		this(index, new ArrayList<Cell>());	    
	}

	public ArrayList<Cell> getConnectedCells() {
		return connectedCell;	    
	}
	
	public void setConnectedCells(ArrayList<Cell> outerCell) {	
		this.connectedCell = outerCell;
	}
	
	public void addToConnectedCells(Cell cell) {
		connectedCell.add(cell);	    
	}
	
	public void removeFromConnectedCells(Cell cell) {
		connectedCell.remove(cell);
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Cell index(" + index + ") Connected Cells: ( ");
	        
		if (connectedCell.size() == 0) 
			return builder.toString() + ")";
	        
		for (int i = 0; i < connectedCell.size(); i++) 
			builder.append(connectedCell.get(i).getIndex() + " ");
	        
		builder.append(")");
		return builder.toString();
	}

	public int getIndex() {
	
		return index;
	}
}
