package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * Launch application from here.
 * GUI created with JavaFX in conjunction with SceneBuilder.
 *
 * @author Miran Batti
 * @author Fredrik Lindorf
 * 
 * @version 2016-01-12
 */
public class App extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
			Scene scene = new Scene(root, 500, 500);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Maze");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
