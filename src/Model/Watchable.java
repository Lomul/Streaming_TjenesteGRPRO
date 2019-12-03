package Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.List;

public class Watchable {
    String title;
    double rating;
    String year;
    List<String> genre;
    BufferedImage img;

    public Watchable(String title, double rating, String year, String imagePath) throws Exception
                //laver vi en string af genre også ændre dem i konstruktor
                //eller laver vi en liste af genre også smider det ind som parameter?
    {
        this.title = title;
        this.rating = rating;
        img = ImageIO.read(getClass().getResource("C:\\Users\\elmel\\IdeaProjects\\Streaming_TjenesteGRPRO\\test.png"));
    }

    public BufferedImage getImg() {
        return img;
    }
}
