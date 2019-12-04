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

            /*int id = input.nextInt();
            String department = input.next();
            String name = input.next();
            double price = Double.valueOf(input.next().substring(1));
            int stock = input.nextInt(); */



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
