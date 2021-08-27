package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class CalculatorMain extends Application {
	
	Stage window;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		// Loads FXML file
		Parent root = FXMLLoader.load(getClass().getResource("Calculator.fxml"));
		window.setTitle("Basic Calculator");
		Scene scene = new Scene(root, 500, 425);
		
		// Loads CSS file
		scene.getStylesheets().add(getClass().getResource("Calculator.css").toExternalForm());
		window.setScene(scene);
		// Locks the window in place with the given screen size
		window.setResizable(false);
		window.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
