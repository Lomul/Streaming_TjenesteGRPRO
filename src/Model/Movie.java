package Model;

public class Movie extends Watchable{
    protected String length;

    public Movie (String length, String title, double rating, String year) throws Exception {
        super(title,rating, year, "");
        this.length = length;
    }

}
