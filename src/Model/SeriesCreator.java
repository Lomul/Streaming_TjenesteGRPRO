package Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import Exceptions.WatchableDuplicate;
import javafx.scene.image.Image;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static View.Controller.convertStringToDouble;

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
            String year = input.next().replaceAll("\\s","");
            int[] yearArray = yearArrayMaker(year);

            for (Series checkSeries : series){
                if(title.equals(checkSeries.title) && yearArray[0] == checkSeries.yearArray[0]){
                    throw new WatchableDuplicate(title, year);
                }
            }

            String genre = input.next();
            String rating = input.next();
            double ratingDouble = convertStringToDouble(rating);
            String seasons = input.next();
            ArrayList<Season> seasonArray = seasonCreator(seasons);
            String filePath = "SerieBilleder/" + title + ".jpg";

            Image img = new Image(new FileInputStream(filePath));

            Series seriesToAdd = new Series(title,ratingDouble,yearArray,genre,seasonArray,img);
            series.add(seriesToAdd);
        }
        input.close();
        return series;
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
            for (int i = 0; i < episodes; i++) {
                seasonToAdd.addEpisode(new Episode(i + 1)); //We fill up the Episodes ArrayList of the Season.
            }
            result.add(seasonToAdd);
        }
        return result;
    }

    private static int[] yearArrayMaker (String s){

        if(s.length() < 5){
            int[] year = new int[1];
            int yearInt = Integer.parseInt(s);
            year[0] = yearInt;

            return year;
        }

        int[] year = new int[0];
        String regex = "(?<yearStart>\\d\\d\\d\\d)-(?<yearEnd>\\d\\d\\d\\d)?";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(s);

        while (m.find()) {
            int startYear = Integer.parseInt(m.group("yearStart"));

            int endYear;

            if (s.charAt(s.length() - 1) == '-') {
                endYear = 2020;
            } else {
                endYear = Integer.parseInt(m.group("yearEnd"));
            }

            int totalYears = (endYear - startYear) + 1;
            year = new int[totalYears];

            for (int i = 0; i < totalYears; i++) {
                year[i] = startYear + i;
            }

        }


        return year;
    }

}

