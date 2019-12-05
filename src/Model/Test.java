package Model;

import java.util.ArrayList;

public class Test {

    public static void main(String[] args) {
        try {
            ArrayList<Series> serier = SeriesCreator.createSeries();
            for (Series s : serier)
            {
                System.out.println(s.title);
                for (Season ses : s.seasons)
                {
                    System.out.println("Season: " + ses.seasonNumber + " Episodes: " + ses.episodes.size());
                }
                System.out.println("");
            }

        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
