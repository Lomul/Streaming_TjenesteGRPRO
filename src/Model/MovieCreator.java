package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;

public class MovieCreator {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("Film.txt"));
        input.useDelimiter(";|/n");
//The Godfather; 1972; Crime, Drama; 9,2;
        Movie[] movies = new Movie[0];

        while(input.hasNext()) {
            String title = input.next();
            String year = input.next();
            String genre = input.next();
            String rating = input.next();

            /*int id = input.nextInt();
            String department = input.next();
            String name = input.next();
            double price = Double.valueOf(input.next().substring(1));
            int stock = input.nextInt(); */



            Movie newMovie = new Movie(title, year, genre, rating);
            movies = addMovie(movies, newMovie);
        }

        for (Movie movie : movies) {
            System.out.println(movie);
        }
        input.close();
    }

    private static Movie[] addMovie(Movie[] movies, Movie movieToAdd) {
        Movie[] newMovies = new Movie[movies.length + 1];
        System.arraycopy(movies, 0, newMovies, 0, movies.length);
        newMovies[newMovies.length - 1] = movieToAdd;

        return newMovies;
    }

    public static class Movie {
        protected String title;
        protected String year;
        protected String genre;
        protected String rating;

        private static NumberFormat formatter = new DecimalFormat("#0.00");

        public Movie(String t, String y, String g, String r) {
            title = t;
            rating = r;
            year = y;
            genre = g;

        }

        @Override
        public String toString() {
            return String.format("Title: %s\r\nYear: %s\r\nGenre: %s\r\nRating: %s\r\n",
                    title, year, genre, rating);
        }
    }

}
