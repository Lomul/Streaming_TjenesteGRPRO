package Model;

import java.util.List;

public class Series extends  Watchable{
    List<Season> seasons;

    public Series(String title, double rating, String year){
        super(title, rating, year);
    }

}
