package Model;

import javafx.scene.image.Image;

public class Movie extends Watchable{
    String year;

    public Movie (String title, double rating, String year, String genre, Image img) throws Exception {
        super(title,rating,genre,img);
        this.year = year;
    }

    public String getYear(){
        return year;
    }

    @Override
    public String toString() {
        return String.format("Title: %s\r\nYear: %s\r\nGenre: %s\r\nRating: %s\r\n",
                title, year, genre, rating);
    }
}
