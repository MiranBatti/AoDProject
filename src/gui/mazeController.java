package gui;

import java.net.URL;
import java.util.ResourceBundle;

import builder.RecursiveBacktracker;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import solver.Dijkstra;

/**
 * Window containing the maze. Needs SwingNode to represent Board.
 * 
 * @author Miran Batti
 * @author Fredrik Lindorf
 * 
 * @version 2016-01-12
 *
 */
public class mazeController implements Initializable {

	private Board board;
	private RecursiveBacktracker maze;
	private Dijkstra solver;
	private static int width, height;
	@FXML
	private final SwingNode node = new SwingNode();
	@FXML
	private StackPane stack;
	@FXML
	private Button start;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		maze = new RecursiveBacktracker(width, height);
		solver = new Dijkstra(maze);		
		board = new Board(maze);
		solver.addListener(board);
		solver.solve();
		node.setContent(board);
		node.setVisible(true);
		stack.getChildren().add(node);
	}
	
	/**
	 * Set maze width from menuController
	 * @param width
	 */
	public static void setWidth(int width) {
		mazeController.width = width;
	}
	
	/**
	 * Set maze height from menuController
	 * @param height
	 */
	public static void setHeight(int height) {
		mazeController.height = height;
	}
	
}
