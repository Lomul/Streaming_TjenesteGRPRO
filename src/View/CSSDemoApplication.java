package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class CSSDemoApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Streaming Heaven");

        //Scene start home
        Parent root = FXMLLoader.load(getClass().getResource("home_scene.fxml"));
        Scene sceneHome = new Scene(root, 800, 500);
        sceneHome.getStylesheets().add(getClass().getResource("demo.css").toExternalForm());

        primaryStage.setScene(sceneHome);
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
