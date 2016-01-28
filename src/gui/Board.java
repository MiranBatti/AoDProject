package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import javax.swing.JPanel;
import builder.RecursiveBacktracker;
import model.Cell;
import solver.DijkstraListener;

/**
 * 
 * Used to represent maze and the maze solution
 * Based on code from: https://github.com/Solisol/labyrinth
 * 
 * @author Miran Batti
 * @author Fredrik Lindorf
 * 
 * @version 2016-01-11
 *
 */
@SuppressWarnings("serial")
public class Board extends JPanel implements DijkstraListener {

    private int width;
    private int height;
    private int area;
    private RecursiveBacktracker maze;
    private Color[] tiles;

    public Board(RecursiveBacktracker maze) {
    	this.maze = maze;
        this.width = maze.getWidth();
        this.height = maze.getHeight();
        area = width * height;
        tiles = new Color[area];
        Arrays.fill(tiles, Color.white);
        repaint();
    }

    @Override
    public void paint(Graphics g) {
    	int tileWidth = getWidth() / width;
        int tileHeight = getHeight() / height;
    	
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        ArrayList<Cell> cells = maze.getPaths();

        for (int i = 0; i < area; i++) {
            int x = i % width;
            int y = i / width;

            g.setColor(tiles[i]);
            g.fillRect(tileWidth * x, tileHeight * y, tileWidth, tileHeight);

        }

        g.setColor(Color.black);

        for (int i = 0; i < area; i++) {
            int x = i % width;
            int y = i / width;

            Cell cell = cells.get(i);
            
            if (maze.isLeftEdge(cell)) 
                g.drawLine(tileWidth * x, tileHeight * y, tileWidth * x, tileHeight * (y + 1));          
            
            if (!maze.hasDown(cell)) 
                g.drawLine(tileWidth * x, tileHeight * (y + 1), tileWidth * (x + 1), tileHeight * (y + 1));
            
            if (!maze.hasRight(cell)) 
                g.drawLine(tileWidth * (x + 1), tileHeight * y, tileWidth * (x + 1), tileHeight * (y + 1));
        }
        g.drawLine(0, 0, tileWidth * width, 0);
    }

    @Override
    public void cellGetsPicked(int index) {
        tiles[index] = Color.red;
        repaint();
    }

    @Override
    public void usedCell(int index) {
        tiles[index] = new Color(230,105,180, 160);
        repaint();
    }

    @Override
    public void mazeSolved(LinkedList<Cell> path) {
        for (Cell cell : path) 
            tiles[cell.getIndex()] = new Color(0, 191, 255, 255); //Paint solution path blue
        
        tiles[path.getFirst().getIndex()] = Color.yellow; //Paint start cell yellow
        tiles[path.getLast().getIndex()] = Color.yellow; //Paint end cell yellow
        repaint();
    }
}
