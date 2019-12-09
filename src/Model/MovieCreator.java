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

public class MovieCreator {

    public static ArrayList<Movie> createMovies() throws Exception {
        Scanner input = new Scanner(new File("Film.txt"));
        input.useDelimiter(";|/n");
        ArrayList<Movie> movies = new ArrayList<>();

        while (input.hasNext()) {
            String title = input.next();
            if (title.contains("\r\n"))
            {
                title = title.replace("\r\n","");
            }
            String year = input.next();
            String genre = input.next();
            String rating = input.next();
            if (rating.contains(","))
            {
                rating = rating.replace(",",".");
            }
            double ratingDouble = Double.parseDouble(rating);
            String filePath = "FilmBilleder/" + title + ".jpg";

            Image img = new Image(new FileInputStream(filePath));

            Movie movieToAdd = new Movie(title, ratingDouble,year,genre,img);
            movies.add(movieToAdd);
        }
        input.close();
        return movies;
        //Ved ikke om vi har brug for mere end det her?
    }
}
