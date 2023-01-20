package petrizzi.c482;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 *
 * @author
 * Massimiliano Petrizzi
 * mpetriz@wgu.edu
 * Student ID: 001386173
 */

/** FUTURE ENHANCEMENT
 * If I were to extend the functionality of this application in the
 * future, I would allow the application to save and load information
 * locally or from a server, and have it populate all fields accordingly.
 * Additionally, as the data pool increases in size, I would optimize the search
 * filters to only filter tables on a search button action event to make sure
 * the program isn't slowed down by searching after every keypress.
 *
 * Main class that starts the application.*/
public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("main-form-view.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}