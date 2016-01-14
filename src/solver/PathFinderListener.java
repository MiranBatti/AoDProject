package solver;

import java.util.LinkedList;
import java.util.List;

import model.Cell;

public interface PathFinderListener {

    public void cellGetsPickable(int index);

    public void takenCell(int index);

    public void endCellFound(LinkedList<Cell> path);

}
