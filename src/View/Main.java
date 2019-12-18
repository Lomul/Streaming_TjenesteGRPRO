package View;

import Model.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {

    public static boolean loggedIn;
    public static String loggedInAs;
    public static User loggedInAsUser;

    public void start(Stage stage) throws IOException {
        loggedIn = false;
        loggedInAs = "";
        loggedInAsUser = null;

        Parent root1 = FXMLLoader.load(getClass().getResource("home_scene.fxml"));

        try {
            stage.setTitle("Streaming Heaven");

            // create a scene
            Scene scene = new Scene(root1, 800, 500);
            scene.getStylesheets().add(getClass().getResource("demo.css").toExternalForm());
            // set the scene
            stage.setScene(scene);
            stage.setMaximized(true);

            stage.show();

        } catch (Exception e) {

            System.out.println(e.getMessage());

        }
    }

        public static void main(String[] args) {
        launch(args);
    }

}
