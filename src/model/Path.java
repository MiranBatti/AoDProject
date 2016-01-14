package model;

import java.util.LinkedList;

/**
 *
 */
public class Path implements Comparable<Path>{
    private LinkedList<Cell> cells;

    public Path() {
        cells = new LinkedList<Cell>();
    }

    public Path(LinkedList<Cell> cells) {
        this.cells = cells;
    }

    public LinkedList<Cell> getNodes() {
        return cells;
    }

    public void setCells(LinkedList<Cell> cells) {
        this.cells = cells;
    }

    public double getLength() {
        return cells.size();
    }

    @Override
    public int compareTo(Path path) {
        return (int)Math.signum(getLength() - path.getLength());
    }

    public Path clone() {
        return new Path(new LinkedList<Cell>(cells));
    }

    public void add(Cell node) {
        cells.addLast(node);
    }
}
