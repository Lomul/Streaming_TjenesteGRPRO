package Model;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Series extends  Watchable{
    List<Season> seasons;


    public Series(String title, String year, String genre, String rating, ArrayList<Season> seasons, Image img) throws Exception {

        super(title, year,genre,rating,img);
        this.seasons = seasons;
    }

    public void addSeason(Season season)
    {
        seasons.add(season);
    }
//TODO genre skal bruges her men vi skal finde ud af hvordan
}
