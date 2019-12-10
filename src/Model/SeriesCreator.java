package Model;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;
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
            String year = input.next();
            int[] yearArray = yearArrayMaker(year);
            String genre = input.next();
            String rating = input.next();
            double ratingDouble = convertStringToDouble(rating);
            String seasons = input.next();
            ArrayList<Season> seasonArray = seasonCreator(seasons);
            String filePath = "SerieBilleder/" + title + ".jpg";

            Image img = new Image(new FileInputStream(filePath));

            Series seriesToAdd = new Series(title,ratingDouble,year,genre,seasonArray,img); //Udskift year med year array???
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

    private static int[] yearArrayMaker(String s){
        int[] year = new int[0];
        String regex = "(?<yearStart>\\d\\d\\d\\d)-(?<yearEnd>\\d\\d\\d\\d)";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(s);
        while (m.find()){
            int startYear = Integer.parseInt(m.group("yearStart"));

            int endYear = 2020;

            if(!m.group("yearEnd").isEmpty()){ //Mads help, ved ikke om det er 0 eller null eller whatever help
                endYear = Integer.parseInt(m.group("yearEnd"));
            }

            int totalSeasons = (endYear - startYear)+1;
            year = new int[totalSeasons];

            for(int i = 0; i < totalSeasons; i++){
                year[i] = startYear + i;
            }

        }

        return year;
    }

}

