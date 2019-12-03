package View;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class Main extends Application {

    @Override
    public void start(Stage window) throws Exception{
        Button movieButton = new Button("Movies");
        Button seriesButton = new Button("Series");
        Button settingsButton = new Button("Settings");
        HBox layoutMain = new HBox();
        layoutMain.getChildren().addAll(movieButton,seriesButton,settingsButton);
        Scene sceneMain = new Scene(layoutMain,1000,650);
        window.setScene(sceneMain);
        window.setTitle("Streaming Heaven");
        window.show();

        movieButton.setOnAction(e -> System.exit(1));

    }

    public static void main(String[] args) {
        launch(args);
    }
}
