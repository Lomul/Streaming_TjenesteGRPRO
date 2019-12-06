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

public class Main extends Application implements EventHandler<ActionEvent>{

    @FXML VBox contentpics;
    public void start(Stage stage) {
        try {
            Parent root1 = FXMLLoader.load(getClass().getResource("home_scene.fxml"));


            ArrayList<Movie> movies = MovieCreator.createMovies();

            // set title for the stage
            stage.setTitle("Pos");

            // create a label
           // Label label = new Label("this is Pos example");

            // create a Tile pane
            TilePane tile_pane = new TilePane();

            tile_pane.setVgap(10);
            tile_pane.setHgap(50);

            ScrollPane scrollPane = new ScrollPane(tile_pane);
            scrollPane.setFitToHeight(true);

            BorderPane root = new BorderPane(scrollPane);
            //root.setPadding(new Insets(15)); grimme padding/box rundt om ting


            // create and add buttons to tilepane
            for (Movie m : movies) {
                Label label1 = new Label (m.getTitle());
                label1.setFont(Font.font("Times New Roman",18));
                label1.setWrapText(true);
                label1.setMaxWidth(m.getImg().getWidth());
                ImageView iv = new ImageView(m.getImg());
                iv.setOnMouseClicked(e -> System.out.println(m.getTitle()));
                VBox vBox = new VBox();
                vBox.getChildren().addAll(iv,label1);
                tile_pane.getChildren().add(vBox);

                iv.setOnMouseEntered(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        vBox.setScaleX(1.2);
                        vBox.setScaleY(1.2);
                    }
                });

                iv.setOnMouseExited(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        vBox.setScaleX(1);
                        vBox.setScaleY(1);
                    }
                });

            }

            // set Alignment of pane
            tile_pane.setAlignment(Pos.TOP_CENTER);
            //contentpics.getChildren().add(tile_pane);
            // create a scene
            Scene scene = new Scene(root1, 400, 300); //tile_pane i stedet for root for at fixe det med at den kun bruger halvdelen af skærmen til billeder
            scene.getStylesheets().add(getClass().getResource("demo.css").toExternalForm());
            // set the scene
            stage.setScene(scene);



            stage.show();
        } catch (Exception e) {

            System.out.println(e.getMessage());

        }
    }




        public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void handle(ActionEvent actionEvent) {

    }
}
