package View;

import Model.Movie;
import Model.MovieCreator;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.*;
import javafx.fxml.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller {
    @FXML private ComboBox<String> comboBox;
    @FXML private TextField textField;
    @FXML private VBox movieBox;

    @FXML
    private void changeSceneToMovies(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("movies_scene.fxml"));
        VBox movieBox = (VBox) root.lookup("#movieBox");
        TilePane p = makeTilePane();
        movieBox.getChildren().add(p);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 800, 500);
        scene.getStylesheets().add(getClass().getResource("demo.css").toExternalForm());
        stage.setScene(scene);
    }
    @FXML
    private void changeSceneToHome(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("home_scene.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 800, 500);
        scene.getStylesheets().add(getClass().getResource("demo.css").toExternalForm());
        stage.setScene(scene);
    }
    @FXML
    private void changeSceneToAccount(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("account_scene.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 800, 500);
        scene.getStylesheets().add(getClass().getResource("demo.css").toExternalForm());
        stage.setScene(scene);
    }
    @FXML
    private void changeSceneToSeries(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("series_scene.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 800, 500);
        scene.getStylesheets().add(getClass().getResource("demo.css").toExternalForm());
        stage.setScene(scene);
    }
    @FXML
    private void searchComboBoxMovies(ActionEvent event) throws Exception {
        String text = textField.getText();
        String value = (String) comboBox.getValue();
        System.out.println("Movies: " + text + " " + value);

        Parent root = FXMLLoader.load(getClass().getResource("search_scene.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 800, 500);
        scene.getStylesheets().add(getClass().getResource("demo.css").toExternalForm());
        stage.setScene(scene);
    }
    @FXML
    private void searchComboBoxSeries(ActionEvent event) throws Exception {
        String text = textField.getText();
        String value = (String) comboBox.getValue();
        System.out.println("Series: " + text + " " + value);

        Parent root = FXMLLoader.load(getClass().getResource("search_scene.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 800, 500);
        scene.getStylesheets().add(getClass().getResource("demo.css").toExternalForm());
        stage.setScene(scene);
    }
    @FXML
    private void searchComboBox(ActionEvent event) throws Exception {
        String text = textField.getText();
        String value = (String) comboBox.getValue();

        Parent root = FXMLLoader.load(getClass().getResource("search_scene.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        System.out.println("All: " + text +  " " + value);

        Scene scene = new Scene(root, 800, 500);
        scene.getStylesheets().add(getClass().getResource("demo.css").toExternalForm());
        stage.setScene(scene);

        //Change search result display:
        Label searchLabel = (Label) root.lookup("#searchLabel");
        /*System.out.println(searchLabel.getText());*/
        searchLabel.setText("newtext");
        /*System.out.println(searchLabel.getText());*/

    }

    private TilePane makeTilePane()
    {
        TilePane tile_pane = new TilePane();
        try {
            ArrayList<Movie> movies = MovieCreator.createMovies();
            tile_pane.setVgap(10);
            tile_pane.setHgap(50);

            ScrollPane scrollPane = new ScrollPane(tile_pane);
            scrollPane.setFitToHeight(true);

            BorderPane root = new BorderPane(scrollPane);
            //root.setPadding(new Insets(15));


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


        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return tile_pane;
    }
}

