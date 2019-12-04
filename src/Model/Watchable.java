package Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.List;
import javafx.scene.image.Image;

public class Watchable {
    String title;
    String rating;
    String year;
    List<String> genre;
    Image img;

    public Watchable(String title, String rating, String year, String imagePath) throws Exception
                //laver vi en string af genre også ændre dem i konstruktor
                //eller laver vi en liste af genre også smider det ind som parameter?
    {
        this.title = title;
        this.rating = rating;

    }

    /*public BufferedImage getImg() {
        return img;
    }*/
}
