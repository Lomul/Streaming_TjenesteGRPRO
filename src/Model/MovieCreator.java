package Model;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import Exceptions.WatchableDuplicate;
import javafx.scene.image.Image;
import static View.Controller.convertStringToDouble;

public class MovieCreator {

    public static ArrayList<Movie> createMovies() throws Exception {
        Scanner input = new Scanner(new File("C:\\Users\\madsc\\Documents\\GitHub\\Streaming_TjenesteGRPRO\\Film.txt"));
        input.useDelimiter(";|/n");
        ArrayList<Movie> movies = new ArrayList<>();

        while (input.hasNext()) {
            String title = input.next();
            if (title.contains("\r\n"))
            {
                title = title.replace("\r\n","");
            }
            String year = input.next();

            for(Movie checkMovies : movies){
                if (title.equals(checkMovies.title) && year.equals(checkMovies.year)){
                    throw new WatchableDuplicate(title,year);
                }
            }

            String genre = input.next();
            String rating = input.next();
            double ratingDouble = convertStringToDouble(rating);
            String filePath = "FilmBilleder/" + title + ".jpg";

            Image img = new Image(new FileInputStream(filePath));

            Movie movieToAdd = new Movie(title, ratingDouble,year,genre,img);
            movies.add(movieToAdd);
        }
        input.close();
        return movies;
    }
}
