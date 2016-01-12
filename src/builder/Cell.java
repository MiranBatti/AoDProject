package builder;

import java.util.ArrayList;

public class Cell {
	 private ArrayList<Cell> cellOut = new ArrayList<Cell>();
	    private int index;

	    public Cell(int index, ArrayList<Cell> outerCell) {
	        this.index = index;
	        this.cellOut = outerCell;
	    }

	    public Cell(int index) {
	        this(index, new ArrayList<Cell>());
	    }

	    public ArrayList<Cell> getCellOut() {
	        return cellOut;
	    }

	    public void setOutNodes(ArrayList<Cell> outerCell) {
	        this.cellOut = outerCell;
	    }

	    public int getNumberOfNeighbours() {
	        return cellOut.size();
	    }

	    public void addToNodes(Cell cell) {
	        cellOut.add(cell);
	    }

	    public void removeFromNodes(Cell cell) {
	        cellOut.remove(cell);
	    }

	    public String toString() {
	        String res = "Vertex(" + index + "): {";
	        if (cellOut.size() == 0) {
	            return res + "}";
	        }
	        for (int i = 0; i < cellOut.size(); i++) {
	            res = res + cellOut.get(i).getIndex() + " ";
	        }
	        return res + "}";
	    }

	    public int getIndex() {
	        return index;
	    }

	    public int neighbourSize() {
	        return cellOut.size();
	    }

	    public boolean compareChildren(Cell other) {
	        if (index != other.getIndex()) {
	            return false;
	        }
	        for (Cell child : cellOut) {
	            boolean isMatched = false;
	            for (Cell otherChild : other.getCellOut()) {
	                if (child.getIndex() == otherChild.getIndex()) {
	                    isMatched = true;
	                    break;
	                }
	            }
	            if (!isMatched) {
	                return isMatched;
	            }
	        }
	        return true;
	    }

	    @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;

	        Cell vertex = (Cell) o;

	        if (index != vertex.index) return false;

	        return true;
	    }

	    @Override
	    public int hashCode() {
	        return index;
	    }
}
