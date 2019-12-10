package Model;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Series extends  Watchable{
    List<Season> seasons;
    int[] yearArray;


    public Series(String title, double rating, int[] yearArray, String genre, ArrayList<Season> seasons, Image img) throws Exception {

        super(title, rating, genre, img);
        this.seasons = seasons;
        this.yearArray = yearArray;
    }
    public List<Season> getSeasons(){
        return seasons;
    }

    public int[] getYearArray(){
        return yearArray;
    }

    public String getDisplayYear(){
        return yearArray[0] + "-" + yearArray[yearArray.length-1];
    }

    public void addSeason(Season season)
    {
        seasons.add(season);
    }
//TODO genre skal bruges her men vi skal finde ud af hvordan
}
