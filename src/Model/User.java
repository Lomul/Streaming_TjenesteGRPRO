package Model;

import java.util.ArrayList;

public class User {

    String name;
    ArrayList<Watchable> saved;
    ArrayList<Watchable> watched;
    public static ArrayList<String> users = new ArrayList<>();

    public User (String name){
        this.name = name;
        saved = new ArrayList<>();
        watched = new ArrayList<>();
    }
    public void addSaved(Watchable watchable){
        saved.add(watchable);
    }
    public void addWatched(Watchable watchable){
        watched.add(watchable);
    }
    public void changeName(String name){
        this.name = name;
    }
    public String getName() {
        return name;
    }

    //TODO: Add to ”Saved”
    //TODO: Add film and series to a list of “watched”
    //TODO: Change Account
}
