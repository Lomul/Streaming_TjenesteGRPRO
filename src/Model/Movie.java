package Model;

public class Movie extends Watchable{

    public Movie (String title, String year, String genre, String rating) throws Exception {
        super(title,rating, year, "");
    }
//TODO genre bliver ikke brugt her men den skal vel stadig defineres
}
