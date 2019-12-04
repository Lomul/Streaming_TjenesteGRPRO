package Model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ImageExample extends Application {
    @Override
    public void start(Stage stage) throws FileNotFoundException {
        //Creating an image
        Image image = new Image(new FileInputStream("test.png"));
        //Image image1 = new Image(new FileInputStream("C:\\Users\\madsc\\Documents\\GitHub\\Streaming_TjenesteGRPRO\\test.png"));

        //Setting the image view
        ImageView imageView = new ImageView(image);
        //ImageView imageView1 = new ImageView(image1);

        //Creating a Group object
        VBox root = new VBox(imageView);

        //Creating a scene object
        Scene scene = new Scene(root, 600, 500);

        //Setting title to the Stage
        stage.setTitle("Loading an image");

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();
    }

    public static void main(String args[]) {
        launch(args);
    }
}
//write in commandprompt:
//Javac src\Model\ImageExample.java
//java ImageExample
//TODO: Java ImageExample virker ikke.

