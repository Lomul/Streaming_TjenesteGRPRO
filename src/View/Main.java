package View;

import Model.Movie;
import Model.MovieCreator;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.util.ArrayList;

public class Main extends Application {

    public void start(Stage stage) {
        try {
            stage.setTitle("Streaming Heaven");
            Parent root1 = FXMLLoader.load(getClass().getResource("home_scene.fxml"));

            // create a scene
            Scene scene = new Scene(root1, 800, 500); //tile_pane i stedet for root for at fixe det med at den kun bruger halvdelen af skærmen til billeder
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
