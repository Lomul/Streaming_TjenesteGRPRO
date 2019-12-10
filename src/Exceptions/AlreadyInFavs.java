package Exceptions;

public class AlreadyInFavs extends RuntimeException {
    protected String title;

    public AlreadyInFavs(String title){
        super("Already in favorites!");
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

}
