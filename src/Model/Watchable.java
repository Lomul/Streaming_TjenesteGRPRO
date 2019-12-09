package Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.List;
import javafx.scene.image.Image;

public class Watchable {
    String title;
    double rating;
    String year;
    String genre;
    Image img;

    public Watchable(String title, double rating, String year, String genre, Image img) throws Exception
                //laver vi en string af genre også ændre dem i konstruktor
                //eller laver vi en liste af genre også smider det ind som parameter?
    {
        this.title = title;
        this.rating = rating;
        this.year = year;
        this.genre = genre;
        this.img = img;

    }

    public Image getImg() {
        return img;
    }

    public String getTitle(){
        return title;
    }
    /*public BufferedImage getImg() {
        return img;
    }*/
}
