package solver;

import java.util.LinkedList;
import model.Cell;

/**
 * 
 * Code based on https://github.com/Solisol/labyrinth
 * 
 * @author Miran Batti
 * @author Fredrik Lindorf
 * 
 * @version 2016-01-10
 * 
 *
 */
public interface DijkstraListener {

	/**
	 * Paint the current cell to try and find a path to the end of the maze
	 * @param index
	 */
    public void cellGetsPicked(int index);

    /**
     * Paint cells that have been tried in order to find a path
     * @param index
     */
    public void usedCell(int index);

    /**
     * Paint the solution path
     * @param path
     */
    public void mazeSolved(LinkedList<Cell> path);

}
