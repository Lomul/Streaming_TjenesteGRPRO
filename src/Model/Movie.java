package Model;

public class Movie extends Watchable{
    String length;

    public Movie (String length, String title, double rating, String year){
        super(title,rating, year);
        this.length = length;
    }

}