package Model;

public class Test {

    public static void main(String[] args) {

        try {
            MovieCreator.createMovies();
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
