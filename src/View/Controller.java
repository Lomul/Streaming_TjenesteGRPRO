package View;

import Model.*;
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
import java.util.List;
import java.util.ResourceBundle;

public class Controller {
    @FXML private ComboBox<String> comboBox;
    @FXML private TextField textField;
    @FXML public VBox watchBox;

    @FXML
    private void changeSceneToMovies(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("movies_scene.fxml"));
        setScene(event, root);

        VBox mB = (VBox) root.lookup("#watchBox");
        BorderPane p = makeBorderPane(MovieCreator.createMovies());
        mB.getChildren().add(p);
    }
    @FXML
    private void changeSceneToHome(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("home_scene.fxml"));
        setScene(event, root);
    }
    @FXML
    private void changeSceneToAccount(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("account_scene.fxml"));
        setScene(event, root);

    }
    @FXML
    private void changeSceneToSeries(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("series_scene.fxml"));
        setScene(event, root);

        VBox mB = (VBox) root.lookup("#watchBox");
        BorderPane p = makeBorderPane(SeriesCreator.createSeries());
        mB.getChildren().add(p);
    }

    @FXML
    private void setScene(ActionEvent event, Parent root){
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
        setScene(event, root);

        String newtext = "Searched for: \n" + value +  ": " + text + ", in all";
        System.out.println("All: " + text +  " " + value);
        //Change search result display:
        Label searchLabel = (Label) root.lookup("#searchLabel");
        /*System.out.println(searchLabel.getText());*/
        searchLabel.setText(newtext);
        /*System.out.println(searchLabel.getText());*/

    }

    private BorderPane makeBorderPane(ArrayList arrayList)
    {
        TilePane tile_pane = new TilePane();
        ScrollPane scrollPane = new ScrollPane(tile_pane);
        scrollPane.setFitToHeight(true);
        BorderPane root = new BorderPane(scrollPane);

        try {

            tile_pane.setVgap(10);
            tile_pane.setHgap(50);

            for (Object o : arrayList) {
                Watchable w = (Watchable) o;
                Label label1 = new Label (w.getTitle());
                label1.setFont(Font.font("Times New Roman",18));
                label1.setWrapText(true);
                label1.setMaxWidth(w.getImg().getWidth());
                ImageView iv = new ImageView(w.getImg());
                iv.setOnMouseClicked(e -> System.out.println(w.getTitle()));
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
        return root;
    }
}

