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

    private static ArrayList<Season> seasonCreator(String s) {
        ArrayList<Season> result = new ArrayList<>();
        String regex = "(?<season>\\d\\d|\\d)-(?<episodes>\\d\\d|\\d)"; //Regular expression to capture the number of the season and the amount of episodes in that season
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(s);

        while (m.find()) {
            int seasonNumber = Integer.parseInt(m.group("season")); // We convert the string given by the regular expression into an integer.
            int episodes = Integer.parseInt(m.group("episodes"));
            Season seasonToAdd = new Season(seasonNumber);
            for (int i = 0; i < episodes; i++)
            {
                seasonToAdd.addEpisode(new Episode(i+1)); //We fill up the Episodes ArrayList of the current Season.
            }
            result.add(seasonToAdd);
        }
        return result;
    }
}
