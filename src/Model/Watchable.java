package Model;

import java.util.List;

public class Watchable {
    String title;
    double rating;
    String year;
    List<String> genre;

    public Watchable(String title, double rating, String year)
                //laver vi en string af genre også ændre dem i konstruktor
                //eller laver vi en liste af genre også smider det ind som parameter?
    {
        this.title = title;
        this.rating = rating;
    }
}
