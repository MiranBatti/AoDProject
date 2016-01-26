package model;

import java.util.ArrayList;

/**
 * Cell represents a single chamber in a maze. One maze can hold a reference to neighbour cells, 
 * the cells are then connected and form a path between eachother.
 * 
 * @author Miran Batti
 * @author Fredrik Lindorf
 * 
 * @version 2016-01-06
 *
 */
public class Cell {	 
	private ArrayList<Cell> connectedCells = new ArrayList<Cell>();
	private int index;

	/**
	 * Cell representing a chamber from a maze
	 * 
	 * @param index
	 * @param connectedCells
	 */
	public Cell(int index, ArrayList<Cell> connectedCells) {	 
		this.index = index;
		this.connectedCells = connectedCells;	    
	}
	
	/**
	 * Constructor with no connected cells. Calls standard constructor with a new arraylist
	 * @param index
	 */
	public Cell(int index) {
		this(index, new ArrayList<Cell>());	    
	}

	/**
	 * Returns connected neighbour
	 * @return a list of connected cells
	 */
	public ArrayList<Cell> getConnectedCells() {
		return connectedCells;	    
	}
	
	/**
	 * Set the connected cells for this cell
	 * @param connectedCells
	 */
	public void setConnectedCells(ArrayList<Cell> connectedCells) {	
		this.connectedCells = connectedCells;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Cell index(" + index + ") Connected Cells: ( ");
	        
		if (connectedCells.size() == 0) 
			return builder.toString() + ")";
	        
		for (int i = 0; i < connectedCells.size(); i++) 
			builder.append(connectedCells.get(i).getIndex() + " ");
	        
		builder.append(")");
		return builder.toString();
	}

	/**
	 * get the index of this cell
	 * @return index of this cell
	 */
	public int getIndex() {
		return index;
	}
}
