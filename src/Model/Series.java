package Model;

import java.util.List;

public class Series extends  Watchable{
    List<Season> seasons;

    public Series(String title, String year, String genre, String rating) throws Exception {

        super(title, rating, year, "");
    }
//TODO genre skal bruges her men vi skal finde ud af hvordan
}
