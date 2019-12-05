package Model;

import javafx.scene.image.Image;

public class Movie extends Watchable{
    public boolean watched = false;

    public Movie (String title, String year, String genre, String rating, Image img) throws Exception {
        super(title,rating,year,genre,img);
    }

    public void setWatched(boolean watched)
    {
        this.watched = watched;
    }

    @Override
    public String toString() {
        return String.format("Title: %s\r\nYear: %s\r\nGenre: %s\r\nRating: %s\r\n",
                title, year, genre, rating);
    }
//TODO genre bliver ikke brugt her men den skal vel stadig defineres
}
