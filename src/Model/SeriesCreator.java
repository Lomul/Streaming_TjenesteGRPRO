package Model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.scene.image.Image;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SeriesCreator {

    public static ArrayList<Series> createSeries() throws Exception {
        Scanner input = new Scanner(new File("Serier.txt"));
        input.useDelimiter(";|/n");
        ArrayList<Series> series = new ArrayList<>();

        while (input.hasNext()) {
            String title = input.next();
            if (title.contains("\r\n"))
            {
                title = title.replace("\r\n","");
            }
            String year = input.next();
            String genre = input.next();
            String rating = input.next();
            String seasons = input.next();
            ArrayList<Season> seasonArray = seasonCreator(seasons);
            String filePath = "SerieBilleder/" + title + ".jpg";

            Image img = new Image(new FileInputStream(filePath));

            Series seriesToAdd = new Series(title, year, rating, genre, seasonArray, img);
            series.add(seriesToAdd);
        }
        input.close();
        return series;
        //Ved ikke om vi har brug for mere end det her?
    }

    private static ArrayList<Season> seasonCreator(String s)
    {
        ArrayList<Season> result = new ArrayList<>();
        String regex = "(?<season>\\d\\d|\\d)-(?<episodes>\\d\\d|\\d)"; //Regular expression to capture the number of the season and the amount of episodes in that season
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(s);
        while (m.find())
        {
            int seasonNumber = Integer.parseInt(m.group("season")); // We convert the string given by the regular expression into an integer.
            int episodes = Integer.parseInt(m.group("episodes"));
            Season seasonToAdd = new Season(seasonNumber);
            for (int i = 0; i < episodes; i++)
            {
                seasonToAdd.addEpisode(new Episode(i+1)); //We fill up the Episodes ArrayList of the Season.
            }
            result.add(seasonToAdd);
        }
        return result;
    }
}



/* Gammel kode >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;

public class SeriesCreator {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("Serier.txt"));
        input.useDelimiter(";|/n");
//Twin Peaks; 1990-1991; Crime, Drama, Mystery; 8,8; 1-8, 2-22;
        Serie[] series = new Serie[0];

        while(input.hasNext()) {
            String title = input.next();
            String year = input.next();
            String genre = input.next();
            String rating = input.next();
            String seasons = input.next();


            Serie newSerie = new Serie(title, year, genre, rating, seasons);
            series = addSerie(series, newSerie);
        }

        for (Serie serie : series) {
            System.out.println(serie);
        }
        input.close();
    }

    private static Serie[] addSerie(Serie[] series, Serie serieToAdd) {
        Serie[] newSeries = new Serie[series.length + 1];
        System.arraycopy(series, 0, newSeries, 0, series.length);
        newSeries[newSeries.length - 1] = serieToAdd;

        return newSeries;
    }

    public static class Serie {
        protected String title;
        protected String year;
        protected String genre;
        protected String rating;
        protected String seasons;

        private static NumberFormat formatter = new DecimalFormat("#0.00");

        public Serie(String t, String y, String g, String r, String s) {
            title = t;
            rating = r;
            year = y;
            genre = g;
            seasons = s;

        }

        @Override
        public String toString() {
            return String.format("Title: %s\r\nYear: %s\r\nGenre: %s\r\nRating: %s\r\nSeasons: %s\n",
                    title, year, genre, rating, seasons);
        }
    }

}
*/