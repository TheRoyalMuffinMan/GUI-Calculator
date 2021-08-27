package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * <h1> Calculator Main Class <h1>
 * The following class is the main calculator class that setups the "stage" or in other words
 * the GUI for the calculator. It consist of one instance variable that represents the window
 * of the calculator and the class extends the Application class which allows for the usage
 * of the JavaFX libraries.
 * @author Andy
 * @since 8/27/2021
 */
public class CalculatorMain extends Application {
	private Stage window;
	/**
	 * The start method is overrided method from the Application class that is the 
	 * "main" for the Simple Calculator with regards to JavaFX applications.
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		// Loads FXML file
		Parent root = FXMLLoader.load(getClass().getResource("Calculator.fxml"));
		window.setTitle("Simple Calculator");
		Scene scene = new Scene(root, 500, 425);
		
		// Loads CSS file
		scene.getStylesheets().add(getClass().getResource("Calculator.css").toExternalForm());
		window.setScene(scene);
		// Locks the window in place with the given screen size
		window.setResizable(false);
		// Shows the window
		window.show();
	}
	public static void main(String[] args) {
		// Launches the application from the start() method
		launch(args);
	}
}
