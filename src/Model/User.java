package Model;

import java.util.ArrayList;

public class User {

    String name;
    ArrayList<Watchable> saved;
    ArrayList<Watchable> watched;
    ArrayList<Episode> epWatched;
    public static ArrayList<User> users = new ArrayList<>();

    public User (String name){
        this.name = name;
        saved = new ArrayList<>();
        watched = new ArrayList<>();
        epWatched = new ArrayList<>();
    }
    public void addSaved(Watchable watchable){
        saved.add(watchable);
    }
    public void addWatched(Watchable watchable){
        watched.add(watchable);
    }
    public void addEpWatched(Episode episode){
        epWatched.add(episode);
    }

    public ArrayList<Watchable> getSaved() {return saved;}

    public ArrayList<Watchable> getWatched() {return watched;}

    public ArrayList<Episode> getEpWatched() {return epWatched;}

    public void changeName(String name){this.name = name;}
    public String getName() {
        return name;
    }

    //TODO: Add to ”Saved”
    //TODO: Add film and series to a list of “watched”
    //TODO: Change Account
}
