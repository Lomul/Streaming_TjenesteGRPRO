package View;

import Model.MovieCreator;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class Main extends Application implements EventHandler<ActionEvent>{
    Button button;

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Streaming Heaven");
        button = new Button();
        button.setText("click");

        button.setOnAction(this);

        StackPane layout = new StackPane();
        layout.getChildren().add(button);

        primaryStage.setScene(new Scene(layout, 800, 750));
        primaryStage.show();
    }

    @Override
    public void handle(ActionEvent event) {
        if(event.getSource()==button)
        {
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
