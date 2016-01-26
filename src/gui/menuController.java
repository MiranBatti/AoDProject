package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Node;

/**
 * Main menu. Gives the user options for maze size.
 * 
 * @author Miran Batti
 * @author Fredrik Lindorf
 * 
 * @version 2016-01-12
 *
 */
public class menuController  {
	@FXML
	private Button smallButton;
	@FXML
	private Button mediumButton;
	@FXML
	private Button largeButton;
	
	/**
	 * Clicking small button will create 10x10 maze
	 * @param e
	 */
	public void smallButtonClick(ActionEvent e) {
		try {
			mazeController.setHeight(10);
			mazeController.setWidth(10);
			Parent parent = FXMLLoader.load(getClass().getResource("mazeController.fxml"));
			Scene mazeScene = new Scene(parent,700, 700);
			Stage primaryStage = (Stage) ((Node)e.getSource()).getScene().getWindow();
			primaryStage.hide();
			primaryStage.setScene(mazeScene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	
	/**
	 * Creates 50x50 maze
	 * @param e
	 */
	public void mediumButtonClick(ActionEvent e) {
		try {
			mazeController.setHeight(50);
			mazeController.setWidth(50);
			Parent parent = FXMLLoader.load(getClass().getResource("mazeController.fxml"));
			Scene mazeScene = new Scene(parent, 1200, 700);
			Stage primaryStage = (Stage) ((Node)e.getSource()).getScene().getWindow();
			primaryStage.hide();
			primaryStage.setScene(mazeScene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	
	/**
	 * Creates 150x150 maze
	 * @param e
	 */
	public void largeButtonClick(ActionEvent e) {
		try {
			mazeController.setHeight(150);
			mazeController.setWidth(150);
			Parent parent = FXMLLoader.load(getClass().getResource("mazeController.fxml"));
			Scene mazeScene = new Scene(parent, 1200, 900);
			Stage primaryStage = (Stage) ((Node)e.getSource()).getScene().getWindow();
			primaryStage.hide();
			primaryStage.setScene(mazeScene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}

}
