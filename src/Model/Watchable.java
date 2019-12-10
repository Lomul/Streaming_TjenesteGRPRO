package Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.List;
import javafx.scene.image.Image;

public class Watchable {
    String title;
    double rating;
    String genre;
    Image img;

    public Watchable(String title, double rating, String genre, Image img) throws Exception
    {
        this.title = title;
        this.rating = rating;
        this.genre = genre;
        this.img = img;

    }

    public Image getImg() {
        return img;
    }

    public String getTitle(){
        return title;
    }

    public String getGenre(){
        return genre;
    }

    public double getRating() {
        return rating;
    }

}
