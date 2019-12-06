package View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.*;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.*;
import javafx.fxml.*;

public class Controller {
    @FXML private ComboBox<String> comboBox;
    @FXML private TextField textField;

    @FXML
    private void changeSceneToMovies(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("movies_scene.fxml"));
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

}

