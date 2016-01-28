package model;

import java.util.LinkedList;

public class Path implements Comparable<Path>{
    private LinkedList<Cell> cells;

    public Path() {
        cells = new LinkedList<Cell>();
    }

    public Path(LinkedList<Cell> cells) {
        this.cells = cells;
    }

    public LinkedList<Cell> getCells() {
        return cells;
    }

    public double getSize() {
        return cells.size();
    }

    @Override
    public int compareTo(Path path) {
        return (int)Math.signum(getSize() - path.getSize());
    }

    public Path clone() {
        return new Path(new LinkedList<Cell>(cells));
    }

    public void add(Cell cell) {
        cells.addLast(cell);
    }
}
