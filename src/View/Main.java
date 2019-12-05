package View;

import Model.Movie;
import Model.MovieCreator;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;

public class Main extends Application implements EventHandler<ActionEvent>{

    public void start(Stage stage) {
        try {

            ArrayList<Movie> movies = MovieCreator.createMovies();

            // set title for the stage
            stage.setTitle("Pos");

            // create a label
            Label label = new Label("this is Pos example");

            // create a Tile pane
            TilePane tile_pane = new TilePane(label);

            ScrollPane scrollPane = new ScrollPane(tile_pane);
            scrollPane.setFitToHeight(true);

            BorderPane root = new BorderPane(scrollPane);
            root.setPadding(new Insets(15));


            // create and add buttons to tilepane
            for (Movie m : movies) {
                Label label1 = new Label (m.getTitle());
                ImageView iv = new ImageView(m.getImg());
                VBox vBox = new VBox();
                vBox.getChildren().addAll(iv,label1);
                tile_pane.getChildren().add(vBox);
            }

            // set Alignment of pane
            tile_pane.setAlignment(Pos.TOP_CENTER);

            // create a scene
            Scene scene = new Scene(root, 400, 300); //tile_pane i stedet for root for at fixe det med at den kun bruger halvdelen af skærmen til billeder

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
