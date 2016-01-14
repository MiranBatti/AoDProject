package gui;

import java.net.URL;
import java.util.ResourceBundle;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Node;

public class menuController implements Initializable {
	@FXML
	private Button smallButton;
	@FXML
	private Button mediumButton;
	@FXML
	private Button largeButton;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
	
	public void smallButtonClick(ActionEvent e) {
		try {
			mazeController.setHeight(10);
			mazeController.setWidth(10);
			Parent parent = FXMLLoader.load(getClass().getResource("mazeController.fxml"));
			Scene mazeScene = new Scene(parent, 1200, 700);
			Stage primaryStage = (Stage) ((Node)e.getSource()).getScene().getWindow();
			primaryStage.hide();
			primaryStage.setScene(mazeScene);
			primaryStage.show();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	
	public void mediumButtonClick(ActionEvent e) {
		try {
			mazeController.setHeight(50);
			mazeController.setWidth(50);
			Parent parent = FXMLLoader.load(getClass().getResource("mazeController.fxml"));
			Scene mazeScene = new Scene(parent, 1200, 700);
			Stage primaryStage = (Stage) ((Node)e.getSource()).getScene().getWindow();
			primaryStage.hide();
			primaryStage.setScene(mazeScene);
			primaryStage.show();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	
	public void largeButtonClick(ActionEvent e) {
		try {
			mazeController.setHeight(150);
			mazeController.setWidth(150);
			Parent parent = FXMLLoader.load(getClass().getResource("mazeController.fxml"));
			Scene mazeScene = new Scene(parent, 1200, 700);
			Stage primaryStage = (Stage) ((Node)e.getSource()).getScene().getWindow();
			primaryStage.hide();
			primaryStage.setScene(mazeScene);
			primaryStage.show();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}

}
