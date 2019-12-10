package View;

import Exceptions.NoSearchMatched;
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

import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {

    public void start(Stage stage) throws IOException {
        Parent root1 = FXMLLoader.load(getClass().getResource("home_scene.fxml"));

        try {
            stage.setTitle("Streaming Heaven");

            // create a scene
            Scene scene = new Scene(root1, 800, 500); //tile_pane i stedet for root for at fixe det med at den kun bruger halvdelen af skærmen til billeder
            scene.getStylesheets().add(getClass().getResource("demo.css").toExternalForm());
            // set the scene
            stage.setScene(scene);
            stage.setMaximized(true);

            stage.show();

        } catch (NoSearchMatched e) {

            Label searchLabel = (Label) root1.lookup("#searchLabel");
            searchLabel.setText("No results for: " + e.getSearch());

        } catch (Exception e) {

            System.out.println(e.getMessage());

        }
    }

        public static void main(String[] args) {
        launch(args); //Den her er kun så jeg kan pushe
    }

}
