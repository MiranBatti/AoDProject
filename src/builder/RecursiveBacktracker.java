package builder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;

public class RecursiveBacktracker {
	private final int mazeWidth, mazeHeight;
//	private final Cell[][] mazeGrid;
	private Random rnd;
	private Stack<StupidCell> stack;
	private ArrayList<StupidCell> visitedCells;
	private boolean visited = false;
	
	
	public RecursiveBacktracker(int width, int height) {
		mazeWidth = width;
		mazeHeight = height;
//		mazeGrid = new Cell[startX][startY];
		stack = new Stack<StupidCell>();
		visitedCells = new ArrayList<StupidCell>();
		generateMaze(mazeWidth, mazeHeight);
	}
	
	private void generateMaze(int width, int height) {
		StupidCell currentCell = new StupidCell(0, 0);
		StupidCell newCell = new StupidCell();		
		
		stack.push(currentCell); // add first cell to stack
		ArrayList<StupidCell> neighbours = new ArrayList<StupidCell>();
		
		while(!stack.isEmpty()) {
			neighbours = checkPossibleNeighbours(currentCell, neighbours);
			
			if(neighbours.size() > 0) {
				Collections.shuffle(neighbours);
				newCell = neighbours.get(0);  // get random neighbour
				stack.add(newCell);
				currentCell = newCell;				
			} else {
				currentCell = stack.pop();
			} // if else
		} // while
	}
	
	private ArrayList<StupidCell> checkPossibleNeighbours(StupidCell cell, ArrayList<StupidCell> neighbours) {
		
		//North
        if (cell.getY()-1 >= 0) {
            StupidCell tmpCell = new StupidCell();
            tmpCell.setX(cell.getX());
            tmpCell.setY(cell.getY()-1);
            if (!visitedCells.contains(tmpCell)){
                neighbours.add(tmpCell);
            }
        }
        //South
        if (cell.getY()+1 < mazeHeight) {
            StupidCell tmpCell = new StupidCell();
            tmpCell.setX(cell.getX());
            tmpCell.setY(cell.getY()+1);
            if (!visitedCells.contains(tmpCell)){
                neighbours.add(tmpCell);
            }
        }
        //East
        if (cell.getX()+1 < mazeWidth) {
            StupidCell tmpCell = new StupidCell();
            tmpCell.setX(cell.getX()+1);
            tmpCell.setY(cell.getY());
            if (!visitedCells.contains(tmpCell)){
                neighbours.add(tmpCell);
            }
        }
        //West
        if (cell.getX()-1 >= 0) {
            StupidCell tmpCell = new StupidCell();
            tmpCell.setX(cell.getX()-1);
            tmpCell.setY(cell.getY());
            if (!visitedCells.contains(tmpCell)){
                neighbours.add(tmpCell);
            }
        }
		
        return neighbours;
	}
	
	private StupidCell getRandomCell(int width, int height) {
		StupidCell rndCell = new StupidCell(rnd.nextInt(width), rnd.nextInt(height));
		return rndCell;
	}
	
	/*
	public void traverseGrids(int width, int height) {
		int row = height;
		int col = width;
		
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				mazeGrid[i][j] = new Cell(i, j);
			}
		}
		
	}*/
}
