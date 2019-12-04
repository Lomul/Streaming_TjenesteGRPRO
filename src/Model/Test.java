package Model;

public class Test {

    public static void main(String[] args) {

        try {
            SeriesCreator.createSeries();
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
