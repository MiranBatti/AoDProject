package gui;

import javax.swing.*;

import builder.RecursiveBacktracker;
import model.Cell;
import solver.PathFinderListener;

import java.awt.*;
import java.util.*;

@SuppressWarnings("serial")
public class Board extends JPanel implements PathFinderListener {

    private int width;
    private int height;
    private int length;

    private RecursiveBacktracker maze;

    private Color[] tiles;

    public Board(RecursiveBacktracker maze) {
    	this.maze = maze;
        this.width = maze.getWidth();
        this.height = maze.getHeight();
        length = width * height;
        clearBoard();
    }

    public void clearBoard() {
        tiles = new Color[length];
        Arrays.fill(tiles, Color.white);
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.green);

        int tileWidth = getWidth() / width;
        int tileHeight = getHeight() / height;

        //Top line of maze
        g.drawLine(0, 0, tileWidth * width, 0);

        ArrayList<Cell> cells = maze.getPaths();

        for (int i = 0; i < length; i++) {
            int x = i % width;
            int y = i / width;

            g.setColor(tiles[i]);
            g.fillRect(tileWidth * x, tileHeight * y, tileWidth, tileHeight);

        }

        g.setColor(Color.black);

        for (int i = 0; i < length; i++) {
            int x = i % width;
            int y = i / width;

            Cell node = cells.get(i);
            if (maze.isLeftEdge(node)) {
                g.drawLine(tileWidth * x, tileHeight * y, tileWidth * x, tileHeight * (y + 1));          
            }
            if (!maze.hasDown(node)) {
                g.drawLine(tileWidth * x, tileHeight * (y + 1), tileWidth * (x + 1), tileHeight * (y + 1));
            }
            if (!maze.hasRight(node)) {
                g.drawLine(tileWidth * (x + 1), tileHeight * y, tileWidth * (x + 1), tileHeight * (y + 1));
            }
        }
    }

    @Override
    public void cellGetsPickable(int index) {
        tiles[index] = Color.red;
        this.repaint();
    }

    @Override
    public void takenCell(int index) {
        tiles[index] = Color.pink;
        this.repaint();
    }

    @Override
    public void endCellFound(LinkedList<Cell> path) {
        for (Cell node : path) {
            tiles[node.getIndex()] = Color.blue;
        }
        tiles[path.getFirst().getIndex()] = Color.green;
        tiles[path.getLast().getIndex()] = Color.green;
        this.repaint();
    }

    public RecursiveBacktracker getMaze() {
        return maze;
    }
}
